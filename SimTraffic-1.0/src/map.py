import pygame
import cell
import vehicle
import sign
import block

class Map():
    """
    This is a class that holds all the mapcells and all the vehicles in the game
    """
    def __init__(self, cells, cars):
        # Atributes: List of cells, List of cars, Set up and simulation time,
        #            and the number of timeout cars
        self.cells = cells
        self.cars = cars
        self.timeoutcars = 0
        self.groupCells()

    # returns the mapcell
    def getCell(self, xpos, ypos):
        """
        takes x and y position and returns the MapCell 
        """
        return self.cells[xpos][ypos]

    def updateMap(self):
        """
        updates the position of the vehicles in the map
        """
        for car in self.cars:
            if car.isActive:
                car.updateVehicle()
            else:
                car.resetVehicle()
                    
    def groupCells(self):
        """
        groups neighboring intersection cells together into a block
        """
        for cellcol in self.cells:
            for c in cellcol:
                if (c and isinstance(c, cell.IntersectionCell) and (not c.block)):
                    x = c.getX()
                    y = c.getY()
                    width = c.style.width
                    height = c.style.height
                    # The cell is on the left of the intersection
                    if (self.getCell(x+1,y)) and self.getCell(x+1,y).isMiddle():
                        block.IntersectionBlock(x, y-2, self.cells)
                        #print "inter", x, y-2
                    # The cell is on the top of the intersection
                    elif (self.getCell(x,y+1)) and self.getCell(x,y+1).isMiddle():
                        block.IntersectionBlock(x-1, y, self.cells)
                        #print "inter", x-1, y
                    # The cell is on the right of the intersection
                    elif (self.getCell(x-1,y)) and self.getCell(x-1,y).isMiddle():
                        block.IntersectionBlock(x-3, y-1, self.cells)
                        #print "inter", x-3, y-1
                    # The cell is on the bottom of the intersection
                    elif (self.getCell(x,y-1)) and self.getCell(x, y-1).isMiddle():
                        block.IntersectionBlock(x-2, y-3, self.cells)
                        #print "inter", x, y-1
                



    

        
