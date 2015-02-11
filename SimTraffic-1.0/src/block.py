import pygame
import cell
import sign
import const

class IntersectionBlock():
    """ Serves to group associated Intersection Cells together. That way,
        adding/configuring/removing traffic sign in one intersection
        will also affect the other intersections in the same block """
    def __init__(self, pivotX, pivotY ,cells):
        """ Initiates the block and the location (pixels for graphics) for signs.
            Parameters: pivotX and pivotY the coordinate of the pivot point for
                        this Intersection Block.
                        cells the 2D array representing the whole map.
        """
        self.intersectioncells = [None,None,None,None]
        self.signLocation = [None,None,None,None]
        self.initiateBlock(pivotX, pivotY, cells)

    def isClear(self):
        """ This checks if the intersection block has cars
            return True if it's clear, false otherwise """
        return (const.map.getCell(self.intersectioncells[0].getX()+1, self.intersectioncells[0].getY()).vehicleCount == 0 and
                const.map.getCell(self.intersectioncells[1].getX(), self.intersectioncells[1].getY()+1).vehicleCount == 0 and
                const.map.getCell(self.intersectioncells[2].getX()-1, self.intersectioncells[2].getY()).vehicleCount == 0 and
                const.map.getCell(self.intersectioncells[3].getX(), self.intersectioncells[3].getY()-1).vehicleCount == 0)

    def initiateBlock(self, pivotX, pivotY, cells):
        """ Initiates this block. Takes the intersection cells to the block
            based on the block's pivot point """
        self.intersectioncells[0] = cells[pivotX][pivotY+2]
        self.intersectioncells[1] = cells[pivotX+1][pivotY]
        self.intersectioncells[2] = cells[pivotX+3][pivotY+1]
        self.intersectioncells[3] = cells[pivotX+2][pivotY+3]
        self.intersectioncells[0].direction =  self.intersectioncells[2].direction = 0
        self.intersectioncells[1].direction =  self.intersectioncells[3].direction = 1
        for icell in self.intersectioncells:
            icell.block = self
        width = self.intersectioncells[0].style.width
        height = self.intersectioncells[0].style.height
        self.signLocation[0] = 15+(pivotX+0.5)*width, 15+(pivotY+3)*height
        self.signLocation[1] = 15+(pivotX+0.5)*width, 15+(pivotY+0.5)*height
        self.signLocation[2] = 15+(pivotX+3)*width, 15+(pivotY+0.5)*height
        self.signLocation[3] = 15+(pivotX+3)*width, 15+(pivotY+3)*height
        
    def updateBlock(self, red,green,direction):
        """ Updates all intersection cells in the whole block when one of
            the intersection cells in the block is updated """
        if (direction == 0):
            red0 = green1 = red
            red1 = green0 = green
            isRed = True
        else:
            red0 = green1 = green
            red1 = green0 = red
            isRed = False
        if (not self.intersectioncells[0].sign):
            self.intersectioncells[0].sign = sign.TrafficLight(red0,green0,self.signLocation[0])
            self.intersectioncells[1].sign = sign.TrafficLight(red1,green1,self.signLocation[1])
            self.intersectioncells[2].sign = sign.TrafficLight(red0,green0,self.signLocation[2])
            self.intersectioncells[3].sign = sign.TrafficLight(red1,green1,self.signLocation[3])
        else:
            self.intersectioncells[0].sign.setRedTime(red0)
            self.intersectioncells[1].sign.setRedTime(red1)
            self.intersectioncells[2].sign.setRedTime(red0)
            self.intersectioncells[3].sign.setRedTime(red1)
            self.intersectioncells[0].sign.setGreenTime(green0)
            self.intersectioncells[1].sign.setGreenTime(green1)
            self.intersectioncells[2].sign.setGreenTime(green0)
            self.intersectioncells[3].sign.setGreenTime(green1)
        self.intersectioncells[0].sign.redLight = self.intersectioncells[2].sign.redLight = isRed
        self.intersectioncells[1].sign.redLight = self.intersectioncells[3].sign.redLight = not isRed

    def add2StopSigns(self, direction):
        """ Add 2 horizontal/vertical pairs of intersection cell
            depending on the specified direction, 0 for horizonal, 1 for vertical
        """
        self.removeSigns()
        if( direction == 0 ):
            self.intersectioncells[0].sign = sign.StopSign(self.signLocation[0], self)
            self.intersectioncells[2].sign = sign.StopSign(self.signLocation[2], self)
        elif( direction == 1 ):
            self.intersectioncells[1].sign = sign.StopSign(self.signLocation[1], self)
            self.intersectioncells[3].sign = sign.StopSign(self.signLocation[3], self)

    def add4StopSigns(self):
        """ Add 4 stop signs to the intersection """
        self.removeSigns()
        self.intersectioncells[0].sign = sign.StopSign(self.signLocation[0], self)
        self.intersectioncells[1].sign = sign.StopSign(self.signLocation[1], self)
        self.intersectioncells[2].sign = sign.StopSign(self.signLocation[2], self)
        self.intersectioncells[3].sign = sign.StopSign(self.signLocation[3], self)

    def removeSigns(self):
        """ Remove all signs from the intersection cells in the block """
        for icell in self.intersectioncells:
            icell.sign = None
            
    def getIntersection():
        """ Returns the intersectioncells in this block """
        return self.intersectioncells
