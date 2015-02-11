import pygame
import map
import vehicle
import sign
import selectlevel
import gamestate
import mainmenu
import statescreen
import const

from pgu import gui
from pygame.locals import *
class MapCell(gui.Label):
    def __init__(self, xpos, ypos, busy=False, start=False, end=False, middle=False, **params):
        gui.Label.__init__(self,"",**params)
        self.xpos = xpos      # x position
        self.ypos = ypos      # y position
        self.busy = busy      # high-volume road
        self.start = start    # is the starting point?
        self.end = end        # is the end point?
        self.middle = middle  # is the cell one of the four middle cells
        #self.multicars = multicars  # is multiple cars allowed in the cell?
        self.vehicleCount = 0 # number of vehicles currently in the cell
        self.vehicle = [False, False, False, False, False, False] # [current number of vehicles in the cell, is time-out]
        """
        self.vehicle = [up:boolean, right:boolean, down:boolean, left:boolean, timeout:boolean, firstOnThePath?:boolean]
        the first four elements of represent whether or not the cell contains a vehicle from the direction.
        timeout means whether or not the cell contains a timeout vehicle (for display purpose)
        """
        

    def getX(self):
        """
        gets the X position of the cell in the map
        """
        return self.xpos

    def getY(self):
        """
        gets the Y position of the cell in the map
        """
        return self.ypos
          
    
    def getVehicleCount(self):
        return self.vehicleCount

    
    def isMiddle(self):
        return self.isMiddle
    
    def isNextFree(self, veh):
        """
        check if the vehicle can move to the next cell
        """
        next = veh.getCell(1)
        
        # assume vehicle.computePath works correctly
        if self.getY() < next.getY():  #0
            return not next.vehicle[0]
        elif self.getX() > next.getX(): #1
            return not next.vehicle[1]

        elif self.getY() > next.getY(): #2
            return not next.vehicle[2]

        elif self.getX() < next.getX(): #3
            return not next.vehicle[3]

        else:
            print "error in compute path (cell.isFree())"
                 

    def addVehicle(self, veh):
        """
        adds the count of the vehicles in the cell and
        sets the timeout status to the vehicle's status if veh passed as the param is a time-out car
        returns true if multiple vehicles are in the cell (i.e. crash happens)
        """
        
        if veh.passedpath > 0:
            previous = veh.getCell(-1)
        
            # assume that isNextFree was called before addVehicle
            if previous.getY() < self.getY():  #0
                self.vehicle[0] = True
            elif previous.getX() > self.getX(): #1
                self.vehicle[1] = True
            elif previous.getY() > self.getY(): #2
                self.vehicle[2] = True
            elif previous.getX() < self.getX(): #3
                self.vehicle[3] = True
            else:
                print "error in compute path (cell.isFree())"
            self.vehicle[5] = False
        elif veh.passedpath == 0:
            self.vehicle[5] = True
            
        self.vehicleCount += 1
        
        
        if veh.isOut():
            self.vehicle[4] = True # set is timeout
        return self.vehicleCount > 1
        
    # change display of vehicle's timeout status while waiting in the cell
    def changeVehicleStatusDisplay(self):
        """
        changes the cell status to containing a time-out vehicle
        """
        self.vehicle[4] = True
        
    def removeVehicle(self, veh):
        """
        Subtracts the count of the vehicles in the cell and set the timeout status to be false (default)
        """
        
        if veh.passedpath > 0:
            previous = veh.getCell(-1)
        
            if previous.getY() < self.getY():  #0
                self.vehicle[0] = False
            elif previous.getX() > self.getX(): #1
                self.vehicle[1] = False
            elif previous.getY() > self.getY(): #2
                self.vehicle[2] = False
            elif previous.getX() < self.getX(): #3
                self.vehicle[3] = False
            else:
                print "error in compute path (cell.isFree())"
            self.vehicle[5] = False
        else:
            for veh in self.vehicle:
                veh = False
            self.vehicle[5] = True
        self.vehicle[4] = False
        self.vehicleCount -= 1



    def repaint(self):
        """
        paints the mapcell
        """
        width = self.style.width
        height = self.style.height
        radius = height / 2 - 2

        if width < height:
            w = width
        else:
            w = height

        pos = (15+ self.xpos * width, 15 + self.ypos * height)
        # self.vehicle = [False, False, False, False, False]
        # 0. incYPos
        # 1. decXPos
        # 2. decYPos
        # 3. incXPos
        # 4. Vehicle's status
        # 5. At the starting
        if self.vehicleCount == 1:
            if self.vehicle[5] and self.xpos == 0: # going on x pos
                if self.vehicle[4]: # time-out
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
                else:
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
            elif self.vehicle[5] and self.ypos == 0: # going on y pos
                if self.vehicle[4]: # time-out
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
                else:
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
            elif self.vehicle[5] and self.ypos > self.xpos: # going on y pos
                if self.vehicle[4]: # time-out
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
                else:
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
            elif self.vehicle[5] and self.xpos > self.ypos: # going on x pos
                if self.vehicle[4]: # time-out
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
                else:
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
            elif self.vehicle[4]: # time-out
                # draw timeout vehicle
                if self.vehicle[0] or self.vehicle[2]: # inc or dec Y pos
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
                else: # inc or dec X pos
                    pygame.draw.rect(const.screen, const.timeOutColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
            else:
                # paint vehicle
                if self.vehicle[0] or self.vehicle[2]: # inc or dec Y pos
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/2, pos[1]+w/8, 3*w/8, 3*w/4), 0)
                else: # inc or dec X pos
                    pygame.draw.rect(const.screen, const.carColor, (pos[0]+w/4, pos[1]+w/3, 3*w/4, 3*w/8), 0)
        elif self.vehicleCount == 0:
            gui.Label.repaint(self)
        elif self.vehicleCount > 1:
            if ((self.vehicle[0] and self.vehicle[1]) or
                  (self.vehicle[0] and self.vehicle[3]) or
                  (self.vehicle[2] and self.vehicle[1]) or
                  (self.vehicle[2] and self.vehicle[3])):
                gui.Label.repaint(self)
                pygame.draw.circle(const.screen, const.crashColor, (pos[0]+4*w/6, pos[1]+w/2), radius, 0)
        else:
            gui.Label.repaint(self)
        

class RoadCell(MapCell):
    def __init__(self,xpos,ypos,busy=False, start=False, end=False, middle=False, **params):
        MapCell.__init__(self, xpos, ypos, busy, start, end, middle, **params)


# Intersection cell, may contain traffic sign
class IntersectionCell(MapCell):
    def __init__(self, xpos,ypos,busy=False, start=False, end=False, sign=None,**params):
        MapCell.__init__(self, xpos, ypos, busy, start, end, **params)
        self.sign = sign
        self.block = None
        self.direction = -1       # 0 for horizontal, 1 for vertical, -1 for unblocked
        self.waitClear = False                      # Wait until the intersection is clear


    def getWaitClear(self):
        """ Get whether or not to wait until the intersection is clear """
        return self.waitClear
        
    def setWaitClear(self, wait):
        """ Set waitClear to the specified parameter """
        self.waitClear = wait

    def getSign(self):
        return self.sign

    def setTrafficSign(self,red, green):
        self.block.updateBlock(red,green,self.direction)

    def removeSign(self):
        self.block.removeSigns()

    def repaint(self):
        """ Draw cell and also the corresponding traffic sign it contains """
        if (self.sign): 
            if (isinstance(self.sign, sign.TrafficLight)):
                self.sign.update(self)
                if (self.sign.isRed()):
                    color = const.red
                    self.setWaitClear(True)    
                else:
                    color = const.green
                    if(self.block.isClear()):
                        self.setWaitClear(False)
                
                pygame.draw.rect(const.screen,color,
                                (self.sign.signLocation[0],
                                    self.sign.signLocation[1],
                                    self.style.width/2,
                                    self.style.height/2))
            elif (isinstance(self.sign, sign.StopSign)):
                self.sign.update(self)
                radius = self.style.height / 4
                pygame.draw.circle(const.screen, const.red, (int(self.sign.signLocation[0]+radius),
                                    int(self.sign.signLocation[1]+radius)), radius)
        MapCell.repaint(self)
        pygame.display.flip()
        

