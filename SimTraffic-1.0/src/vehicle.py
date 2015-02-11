import pygame
import cell
import sign
import types
import const
import map
import gamestate
from pygame.locals import *
from random import *
from pgu import gui

class Vehicle:
    """
        A class representing the vehicle. Thus, GameState extends this class.
    """
    def __init__(self, cells, timeoutTime, speed=500):
        self.time = timeoutTime             # get the timeout time limit from gamestate class
        self.timeoutTime = 0                # Keep track how long the vehicle has waited on a stop sign or red light
        self.timeout = False                # Determines whether the vehicle is timed-out or not
        self.path = []                      # List of mapcells
        self.passedpath = -1                # Index to iterate through the path list, determines where the vehicle's position at the moment
        self.iswaiting = False              # Determines whether the vehicle is currently waiting at a stop sign or for red light
        self.isActive = True                # Determines whether the vehicle has reached its destination or not
        #self.color
        self.speed = speed                  # How many seconds the vehicle takes to move to the next cell
        self.clock = pygame.time.Clock()    # General purpose clock to count time
        self.clockTimeOut = pygame.time.Clock()    # Clock for keeping track how long the vehicle has waited for stop sign or red traffic light
        self.vehTimer = 0                   # How long the clock
        self.clock.tick()
        self.clockTimeOut.tick()
	self.firstWaiting = False	    # this is a helper variable for increaseTime function
        self.computePath(cells)
	


    def getCell(self, steps):
	"""
	gets the mapcell with number of steps over the current cell on the path
	"""
	#print "passedpath = ", self.passedpath, "length of path = ", len(self.path)
	if (self.passedpath + steps) < len(self.path) and (self.passedpath + steps) >= 0:
	    return self.path[self.passedpath + steps]
        
    def isOut(self):
        """
        Returns true if the vehicle is timeouted (waited too long or crashed)
        """
        return self.timeout
      
    def isWaiting(self):
        """
        Return true is the vehicle is currently waiting at a stop sign or red light
        """
        return self.iswaiting
    
    def setWaiting( self, waiting ):
        """
        Set the vehicle's waiting status
        """
	if not self.isOut():
            self.iswaiting = waiting
	    self.increaseTime()

    def increaseTime(self):
        """
        Set how long the vehicle has waited for the traffic sign.
        Update the vehicle's timeout status if it waits for too long.
        Update the number of time-out vehicle if there is more timed-out vehicle.
        """
        if self.isWaiting():
	    if not self.firstWaiting:
		self.clockTimeOut.tick()
		self.firstWaiting = True
	    else:	
		self.timeoutTime += self.clockTimeOut.tick()
	else:
	    if self.firstWaiting:
		self.timeoutTime += self.clockTimeOut.tick()
		self.firstWaiting = False
	    
        if self.timeoutTime > self.time:
	    self.timeout = True
	    #print "waiting timeout"
	    if self.getCell(0):
		self.getCell(0).changeVehicleStatusDisplay()
	    self.addNumTimeout()

    def setCrashed(self):
        """
        Set the vehicle to timeout if crashed.
        Update the number of timed-out vehicle by calling method addNumTimeOut().
        """
        self.timeout = True
        self.addNumTimeout()

    def addNumTimeout(self):
        """
        Update the total number of timed-out vehicle.
        Increment the count label on the status bar.
        """
        const.timeoutCount += 1
        const.numTimeout.value = str('%(count)02d/%(tot)02d' % {'count':const.timeoutCount, 'tot':const.timeoutAllowed })
        const.numTimeout.repaint()

    def updateVehicle(self):
        """
        Update the vehicle's status.
        The vehicle will try to move to its next cell (based on its precomputed path) if:
            1. Its timer has passed its speed timer and
            2. The vehicle is not waiting at a stop sign or red light
        """
        if(self.vehTimer > self.speed): #and not(self.isWaiting)):
            self.moveCheck()
            self.vehTimer = 0
  
        self.vehTimer += self.clock.tick()

    def moveCheck(self):
        """
        Moves the vehicle to the next cell after checking the traffic signs and the availability of the next cell.
        Keeps counts of the vehicles in the cell it's currently in and deletes the vehicle if it has reached its destination.
        """
        if self.passedpath+1 == len(self.path):
            #print "REACHED"
            self.getCell(0).removeVehicle(self)
            self.isActive = False
        else:
            # TRAFFIC SIGN CHECK HERE
                # if it's intersection cell
            if self.passedpath == -1:
		if self.getCell(1).getVehicleCount()==0:
		    self.setWaiting(False)
		    self.passedpath += 1
		    self.getCell(0).addVehicle(self)
		else:
		    pass
	    elif isinstance(self.getCell(0), cell.IntersectionCell):
                temp_sign = self.getCell(0).getSign()
		# if it is a traffic light
		if (isinstance(temp_sign, sign.TrafficLight)):
		    if ((temp_sign and temp_sign.isRed()) or
			self.getCell(0).getWaitClear() or
			not self.getCell(0).isNextFree(self)):
			self.setWaiting(True)
		    else:
			self.move()
		elif (isinstance(temp_sign, sign.StopSign)):
                    # if it hasn't enter (approaches) the stop sign
                    if( not temp_sign.hasCar ):
                        self.setWaiting(True)
                        #print "CAR ENTERS STOP SIGN CELL"
                        temp_sign.carEnter()
                        #print "SET CARS INITIAL RANK"
                    # update the stop sign's rank
                    temp_sign.setRank()
                    # if 2 seconds of stop sign has elapsed
                    if( temp_sign.elapsed ):
			ranks = []
			if temp_sign.stopBlock.intersectioncells[0].sign:
			    ranks.append(temp_sign.stopBlock.intersectioncells[0].sign.rank)
			if temp_sign.stopBlock.intersectioncells[1].sign:
			    ranks.append(temp_sign.stopBlock.intersectioncells[1].sign.rank)
			if temp_sign.stopBlock.intersectioncells[2].sign:
			    ranks.append(temp_sign.stopBlock.intersectioncells[2].sign.rank)
			if temp_sign.stopBlock.intersectioncells[3].sign:
			    ranks.append(temp_sign.stopBlock.intersectioncells[3].sign.rank)
                        min_rank = min(ranks)

                        if( temp_sign.rank <= min_rank and self.getCell(0).block.isClear()):
                            #print "TIME ELAPSED, MOVE!"
                            self.move()
                            temp_sign.elapsed = False
                            temp_sign.hasCar = False
                            temp_sign.rank = 4
			else:
			    self.setWaiting(True)

		else:
		    # if there is no sign
		    if not self.getCell(0).isNextFree(self):
			self.setWaiting(True)
		    else:
			self.move()
            # if road cell but next cell is not free      
            elif not self.getCell(0).isNextFree(self):
                self.setWaiting(True)
            else:
                self.move()


    def move(self):
	"""
	moves the vehicle to the next cell on the path after checking the opposite direction if turning left
	"""
        if not self.waitTurn(): # if no traffic from the opposite direction
	    gui.Label.repaint(self.getCell(0))
            self.setWaiting(False) 
            self.getCell(0).removeVehicle(self)
            self.passedpath += 1
            if self.getCell(0).addVehicle(self): # if the car crashed
                if not self.isOut():
                    self.setCrashed()
        else:
	    #print "wait turn"
            self.setWaiting(True)

    def waitTurn(self):
	"""
	this checks whether or not there is a vehicle from the opposite direction into the intersection before turning left
	"""
        
        if self.passedpath > 0 and self.passedpath < len(self.path)-1:
            if self.getCell(-1).getY() == self.getCell(0).getY():
		if (self.getCell(-1).getX() > self.getCell(0).getX() and 
		    self.getCell(0).getY() < self.getCell(1).getY()):
		    cell = const.map.getCell(self.getCell(1).getX()-1, self.getCell(1).getY())
		    if cell and cell.getSign() and isinstance(cell.getSign(), sign.StopSign):
			return False
                    if (cell and not cell.vehicleCount==0 and (not cell.getSign() or not cell.getSign().isRed())):
                        return True
                elif (self.getCell(-1).getX() < self.getCell(0).getX() and
		      self.getCell(0).getY() > self.getCell(1).getY()):
                    cell = const.map.getCell(self.getCell(1).getX()+1, self.getCell(1).getY())
		    if cell and cell.getSign() and isinstance(cell.getSign(), sign.StopSign):
			return False
		    if (cell and not cell.vehicleCount==0 and (not cell.getSign() or not cell.getSign().isRed())):
                        return True
                return False # going straight
            elif self.getCell(-1).getX() == self.getCell(0).getX():
		if (self.getCell(-1).getY() > self.getCell(0).getY() and
		    self.getCell(0).getX() > self.getCell(1).getX()):
		    cell = const.map.getCell(self.getCell(1).getX(), self.getCell(1).getY()-1)
		    if cell and cell.getSign() and isinstance(cell.getSign(), sign.StopSign):
			return False
                    if ( cell and not cell.vehicleCount==0 and (not cell.getSign() or not cell.getSign().isRed())):
                        return True
                elif (self.getCell(-1).getY() < self.getCell(0).getY() and
		    self.getCell(0).getX() < self.getCell(1).getX()):
		    cell = const.map.getCell(self.getCell(1).getX(), self.getCell(1).getY()+1)
		    if cell and cell.getSign() and isinstance(cell.getSign(), sign.StopSign):
			return False
                    if ( cell and not cell.vehicleCount==0 and (not cell.getSign() or not cell.getSign().isRed())):
                        return True
                return False # going straight
            else:
                print "Error with the path: going in diagonal direction"
                
               
    def resetVehicle(self):
        """
        Have the vehicle respawns after it reaches the destination 
        """
        self.isActive = True
        self.passedpath = -1
        self.timeout = False
        self.iswaiting = False
        self.firstWaiting = False
        self.vehTime = 0
        self.timeoutTime = 0

    def computePath(self, cells):	# Cells = map.cells
        """
        Precompute vehicle's path for the specified map
        """
        incYpos = decYpos = incXpos = decXpos = True	# Default: vehicle can go to any direction
        right = left = straight = False			# Default: each road is Low-traffic
        nextHigh = []   # List of cells with high-traffic
        nextLow = []    # List of cells with low-traffic
        #startHigh = []	# List of start cells with high traffic
        #startLow = []	# List of start cells with low traffic
        nextPath = ""
        indPrint = 0	# Index for testing, printing
        rand = Random()
        secondCell = False  # Determines whether it currently compute the vehicle's second cell

        
        # Randomly choose the vehicle's starting cell based on the road's status.
        #print ""
        if rand.randint(0, 4) > 0:
            random = rand.randint(0, len(const.busyStart)-1)
            xpos = const.busyStart[random][0]
            ypos = const.busyStart[random][1]
            self.path.append(cells[xpos][ypos])
            #print xpos, ypos
        else:
            random = rand.randint(0, len(const.lowStart)-1)
            xpos = const.lowStart[random][0]
            ypos = const.lowStart[random][1]
            self.path.append(cells[xpos][ypos])
            #print xpos, ypos

        # Randomly compute the vehicle's path based on the road's status
        while True:
            if incXpos:	# when a vehicle goes on incremented x-axis
                if ((xpos+1) < len(cells)) and cells[xpos+1][ypos] and isinstance(cells[xpos+1][ypos], cell.RoadCell):	# at a road cell    
                    if not cells[xpos+1][ypos].end:
                        self.path.append(cells[xpos+1][ypos])
                        xpos += 1
                        #print xpos, ypos
                        secondCell = True
                        incXpos = True
                        decXpos = False
                        incYpos = False
                        decYpos = False
                    elif cells[xpos+1][ypos].end:  # If the vehicle reach an end point
                        if secondCell:
                            self.path.append(cells[xpos+1][ypos])
                            xpos += 1
                            #print xpos, ypos
	                    break
                elif xpos + 1 < len(cells) and cells[xpos+1][ypos] and isinstance(cells[xpos+1][ypos], cell.IntersectionCell):  # at an intersection cell
                    xpos += 1
                    self.path.append(cells[xpos][ypos])
                    #print xpos, ypos
                    secondCell = True
                    
                    # to get the traffic condition of going straight, left, or right
                    if cells[xpos+1][ypos+1] and cells[xpos+1][ypos+1].busy:
                        nextHigh.append("right")
                    else:
                        nextLow.append("right")
                    if cells[xpos+2][ypos-2] and cells[xpos+2][ypos-2].busy:
                        nextHigh.append("left")
                    else:
                        nextLow.append("left")
                    if cells[xpos+3][ypos] and cells[xpos+3][ypos].busy:
                        nextHigh.append("straight")
                    else:
                        nextLow.append("straight")
                        
                    if len(nextLow) == 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    elif len(nextHigh) == 0:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                    elif rand.randint(0, 4) > 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    else:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                        
                    ####CHOOSE WHICH WAY###
                    if nextPath == "right":
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        incXpos = False
                        incYpos = True
                    elif nextPath == "left":
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        incXpos = False
                        decYpos = True
                    elif nextPath == "straight":
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
            if decXpos:	# when a vehicle goes on decremented x-axis
                if (xpos - 1 >= 0) and cells[xpos-1][ypos] and isinstance(cells[xpos-1][ypos], cell.RoadCell):	# at a road cell
                    if not cells[xpos-1][ypos].end:
                        self.path.append(cells[xpos-1][ypos])
                        xpos -= 1
                        #print xpos, ypos
                        secondCell = True
                        incXpos = False
                        decXpos = True
                        incYpos = False
                        decYpos = False
                    elif cells[xpos-1][ypos].end:  # If the vehicle reach an end point
                        if secondCell:
                            self.path.append(cells[xpos-1][ypos])
                            xpos -= 1
                            #print xpos, ypos
                            break
                elif xpos - 1 >= 0 and cells[xpos-1][ypos] and isinstance(cells[xpos-1][ypos], cell.IntersectionCell):	# at an intersection cell
                    xpos -= 1
                    self.path.append(cells[xpos][ypos])
                    #print xpos, ypos
                    secondCell = True
                    
		    # to get the traffic condition of going straight, left, or right
		    if cells[xpos-1][ypos-1] and cells[xpos-1][ypos-1].busy:
		        nextHigh.append("right")
		    else:
		        nextLow.append("right")
		    if cells[xpos-2][ypos+2] and cells[xpos-2][ypos+2].busy:
		        nextHigh.append("left")
		    else:
		        nextLow.append("left")
		    if cells[xpos-3][ypos] and cells[xpos-3][ypos].busy:
		        nextHigh.append("straight")
		    else:
                        nextLow.append("straight")
                        
                    if len(nextLow) == 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    elif len(nextHigh) == 0:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                    elif rand.randint(0, 4) > 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    else:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                        
                    ####CHOOSE WHICH WAY###
                    if nextPath == "right":
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        decXpos = False
                        decYpos = True
                    elif nextPath == "left":
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        decXpos = False
                        incYpos = True
                    elif nextPath == "straight":
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
            if incYpos:	# when a vehicle goes on incremented y-axis
                if (ypos + 1 < len(cells[0])) and cells[xpos][ypos+1] and isinstance(cells[xpos][ypos+1], cell.RoadCell):	# at a road cell     
                    if not cells[xpos][ypos+1].end:
                        self.path.append(cells[xpos][ypos+1])
                        ypos += 1
                        #print xpos, ypos
                        secondCell = True
                        incXpos = False
                        decXpos = False
                        incYpos = True
                        decYpos = False
                    elif cells[xpos][ypos+1].end:  # If the vehicle has reached an end point.
                        if secondCell:
	                    self.path.append(cells[xpos][ypos+1])
	                    ypos += 1
	                    #print xpos, ypos
                            break
                elif ypos + 1 < len(cells[0]) and cells[xpos][ypos+1] and isinstance(cells[xpos][ypos+1], cell.IntersectionCell):	# at an intersection cell
                    ypos += 1
                    self.path.append(cells[xpos][ypos])
                    #print xpos, ypos
                    secondCell = True
                    
		    # to get the traffic condition of going straight, left, or right
		    if cells[xpos-1][ypos+1] and cells[xpos-1][ypos+1].busy:
			nextHigh.append("right")
		    else:
			nextLow.append("right")
		    if cells[xpos+2][ypos+2] and cells[xpos+2][ypos+2].busy:
			nextHigh.append("left")
		    else:
			nextLow.append("left")
		    if cells[xpos][ypos+3] and cells[xpos][ypos+3].busy:
			nextHigh.append("straight")
		    else:
                        nextLow.append("straight")
                        
                    if len(nextLow) == 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    elif len(nextHigh) == 0:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                    elif rand.randint(0, 4) > 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    else:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                        
                    ####CHOOSE WHICH WAY###
                    if nextPath == "right":
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        incYpos = False
                        decXpos = True
                    elif nextPath == "left":
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        incYpos = False
                        incXpos = True
                    elif nextPath == "straight":
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
            if decYpos:	# when a vehicle goes on decremented y-axis
                if (ypos - 1 >= 0) and cells[xpos][ypos-1] and isinstance(cells[xpos][ypos-1], cell.RoadCell):	# at a road cell     
                    if not cells[xpos][ypos-1].end:
	                self.path.append(cells[xpos][ypos-1])
	                ypos -= 1
	                #print xpos, ypos
	                secondCell = True
                        incXpos = False
                        decXpos = False
                        incYpos = False
                        decYpos = True
                    elif cells[xpos][ypos-1].end:  # if end point
                        if secondCell:
                                self.path.append(cells[xpos][ypos-1])
                                ypos -= 1
                                #print xpos, ypos
                                break
                elif ypos - 1 >= 0 and cells[xpos][ypos-1] and isinstance(cells[xpos][ypos-1], cell.IntersectionCell):	# at an intersection cell
                    ypos -= 1
                    self.path.append(cells[xpos][ypos])
                    #print xpos, ypos
                    secondCell = True
                    
                    # to get the traffic condition of going straight, left, or right
		    if cells[xpos+1][ypos-1] and cells[xpos+1][ypos-1].busy:
		    	nextHigh.append("right")
		    else:
		    	nextLow.append("right")
		    if cells[xpos-2][ypos-2] and cells[xpos-2][ypos-2].busy:
		    	nextHigh.append("left")
		    else:
		    	nextLow.append("left")
		    if cells[xpos][ypos-3] and cells[xpos][ypos-3].busy:
		    	nextHigh.append("straight")
		    else:
                        nextLow.append("straight")
                        
                    if len(nextLow) == 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    elif len(nextHigh) == 0:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                    elif rand.randint(0, 4) > 0:
                        nextPath = nextHigh[rand.randint(0, len(nextHigh) - 1)]
                    else:
                        nextPath = nextLow[rand.randint(0, len(nextLow) - 1)]
                        
                    ####CHOOSE WHICH WAY###
                    if nextPath == "right":
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos += 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        decYpos = False
                        incXpos = True
                    elif nextPath == "left":
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        xpos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        decYpos = False
                        decXpos = True
                    elif nextPath == "straight":
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
                        ypos -= 1
                        self.path.append(cells[xpos][ypos])
                        #print xpos, ypos
            # Remove the content of next possible cells in current position.
            for x in nextHigh:
                nextHigh.remove(x)
            for x in nextLow:
                nextLow.remove(x)
            



