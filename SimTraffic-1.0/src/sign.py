import pygame
import const

class Sign():
    """ Traffic Sign Interface
        Defines the interface for traffic signs which have different
        behaviors in handling cars
    """
    def __init__(self):
        """ Constructor that does nothing """
        pass

    def update(self, cell):
        """ Update the traffic sign's status 
            cell : which cell calls update on this traffic light
        """
        pass

    def draw(self, width, height):
        """ Draw the traffic sign """
        pass
    

class TrafficLight(Sign):
    """ Traffic Light
        Alternate between red and green depending on the speficied time parameters
        Vehicles may proceed on green and wait on red
    """
    def __init__(self, redtime=None, greentime=None, signLocation = None, redLight=True):
        Sign.__init__(self)

        # Internal properties
        self.trafficClock = 0                       # traffic light clock
        self.clock = pygame.time.Clock()            # general purpose clock
        self.redTime = redtime                      # red light time
        self.greenTime = greentime                  # green light time
        self.redLight = redLight                    # true if red light is on
        self.signLocation = signLocation            # signLocation[0]
        
        self.clock.tick()                           # Start clock

    def update(self, cell):
        """ Update its light status depending of the green and red timer
            cell : which cell calls update on this traffic light
        """
        if(self.redLight):
            self.trafficClock += self.clock.tick()
            if( ( self.trafficClock / 1000.0 ) > self.redTime ):
                #print "CHANGE TO GREEN!!!"
                self.redLight = False
                # add: set image to green light
                self.trafficClock = 0

            # set the cell's status so that it ( the car in it ) should
            # wait until block is clear
            cell.setWaitClear(True)

        else:
            self.trafficClock += self.clock.tick()
                
            if( ( self.trafficClock / 1000.0 ) > self.greenTime ):
                #print "CHANGE TO RED!!!"
                self.redLight = True
                # add: set image to red light
                self.trafficClock = 0

            # if the block is clear, then set the cell's wait clear status
            # to false ( car in that cell has to wait until block is clear )
            if(cell.block.isClear()):
                cell.setWaitClear(False)


    def draw(self, width, height):
        """ Draw itself according to its status. """
        # Decide light's color
        if( self.redLight ):
            color = const.red
        else:
            color = const.green

        # Call pygame's draw rect method
        pygame.draw.rect(const.screen, color,
                        (self.signLocation[0],
                        self.signLocation[1],
                        width / 2,
                        height / 2))


    def setRedTime(self, redTime):
        """ Red light time setter """
        self.redTime = redTime

    def setGreenTime(self, greenTime):
        """ Green light time setter """
        self.greenTime = greenTime

    def getRedTime(self):
        """ Red light time getter """
        return self.redTime

    def getGreenTime(self):
        """ Green light time getter """
        return self.greenTime

    def isRed(self):
        """ Returns true if red light is on """
        return self.redLight
    

class StopSign(Sign):
    """ Stop sign handles vehicles such that they will stop for 2 seconds
        as they enter the cell containing the stop sign. In addition, cars
        from different direction will take turn in proceeding based on their
        order of arrival
    """
    def __init__(self, signLocation = None, stopBlock = None):
        """ Initialize the stop sign with default parameters.
            Initialy it has no car, so no stop time has elapsed. It's rank
            (priority on proceeding) should be 3.
        """
        Sign.__init__(self)
        self.signLocation = signLocation
        self.clock = pygame.time.Clock()
        self.stopSignClock = 0.0
        self.hasCar = False
        self.elapsed = False
        self.rank = 4
        self.stopBlock = stopBlock


    def setRank(self, checkRank = 0):
        """ Set this stop sign's rank to the lowest possible by checking against
            other stop signs in the intersection. If it detects any stop sign
            with lower rank, it will recursively check for higher ranks.
        """
        # if it already has this rank, don't do anything
        if ( self.rank == checkRank ):
            return
        # check other stop signs in the intersection
        for ic in self.stopBlock.intersectioncells:
            # if there is a sign
            if( not ic.sign == None ):
                if( ic.sign.rank == checkRank ):
                    # check for bigger ranks
                    self.setRank(checkRank + 1)
                    return
        # if no stop sign match this rank, set this stop sign's rank to checkRank
        self.rank = checkRank
        return

    def carEnter(self):
        """ A car approach the stop sign. Set stop sign's
            hasCar to "True". Start counting how long
            the car has been at the stop sign.
        """
        self.hasCar = True
        self.clock.tick()

    def update(self, cell):
        """ Update stop sign's properties. If it has a car, it will
            count how long the car has been there. And set elapsed
            to true if car has waited for 1 second.
            cell : which cell calls update on this stop sign
        """
        # if there is a car at the stop sign
        if( self.hasCar ):
            self.stopSignClock += self.clock.tick() # keep clock running
            #print self.stopSignClock
            if( self.stopSignClock > 1000.0 ):
                self.elapsed = True # 1 secs has elapsed
                self.stopSignClock = 0.0

    def draw(self, width, height):
        """ Draw itself according to its status. """
        radius = height / 4
        pygame.draw.circle(const.screen, const.red,
                            (self.signLocation[0] + radius,
                            self.signLocation[1] + radius),
                            radius)
