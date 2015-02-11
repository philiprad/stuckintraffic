import sys
sys.path.append('../src')
import pygame
import unittest
import cell
import const
import vehicle

class TestCellFunctions(unittest.TestCase):
    # Set up elements needed for testing cell
    def setUp(self):
        try:
            fileHandle = open('test.txt','r')
            
            for blank in range(5):
                fileHandle.readline()

            size = fileHandle.readline().split(',')
            sizex = int(size[0])
            sizey = int(size[1])
            fileHandle.readline()

            self.cells=[]
            
            for i in range(sizey):
                self.cells.append([])
                for j in range(sizex):
                    self.cells[i].append(0)
            
            
            for i in range(sizey):
                line = fileHandle.readline()
                print ''
                for j in range(sizex):
                    if line[j] == 'x' : #building
                        self.cells[j][i] = None
                       	print '0',
                    elif line[j] == 'i' : #intersection block
                        lb = cell.RoadCell(j,i,False, False, False, True, background = (0,0,0))
                        self.cells[j][i] = lb
                        
                       	print 'i',
                    elif line[j] == 'H' : # busy (high traffic)
                        lb = cell.RoadCell(j,i,True,background = (0,0,0))
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == 'L' : # not busy (low traffic)
                        lb = cell.RoadCell(j,i,background = (0,0,0))
                        self.cells[j][i] = lb
                       	print '1',
                    elif line[j] == 'S' : # start cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True,True,background = (0,0,0))
                        const.busyStart.append((j, i))
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == 's' : # start cell and not busy (lwo traffic)
                        lb = cell.RoadCell(j,i,False,True,background = (0,0,0))
                        const.lowStart.append((j, i))
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == 'E' : # end cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True, False, True,background = (0,0,0))
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == 'e' : # end cell and not busy (low traffic)
                        lb = cell.RoadCell(j,i,False,False,True,background = (0,0,0))
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == '+' : #intersection cell busy
                        lb = cell.IntersectionCell(j,i,True,background = (0,0,0))
                        self.cells[j][i] = lb
                        print 'o',
		    elif line[j] == '#' : #intersection cell not busy
                        lb = cell.IntersectionCell(j,i,False,background = (0,0,0))
                        self.cells[j][i] = lb
			print 'o',
            
            self.vehicle = vehicle.Vehicle(self.cells, 500) #pass random large value for timeouttime for testing
            self.vehicle2 = vehicle.Vehicle(self.cells, 500) #pass random large value for timeouttime for testing
            self.vehicle2.path = self.vehicle.path
            
        except IOError:
            print "The file does not exist, exiting"

    # Test addVehicle
    def testisNextFree(self):
        self.vehicle.passedpath = 0
        self.vehicle2.passedpath = 1
        if self.vehicle.getCell(1) == self.vehicle2.getCell(0):
            x = self.vehicle.getCell(0).getX()
            y = self.vehicle.getCell(0).getY()
            xNext = self.vehicle.getCell(1).getX()
            yNext = self.vehicle.getCell(1).getY()
            self.cells[xNext][yNext].addVehicle(self.vehicle2)
            self.assertFalse(self.cells[x][y].isNextFree(self.vehicle),
                             'FAIL - cell.py - isNextFree(Vehicle)')
        print "Passed first test."
        
    # Test addVehicle
    def testaddVehicle(self):
        # Test the direction of the vehicle
        self.vehicle.passedpath = self.vehicle2.passedpath = 1
        previous = self.vehicle.getCell(-1)
        current = self.vehicle.getCell(0)
        x = current.getX()
        y = current.getY()
        first = (previous.getY() < current.getY())
        second = (previous.getX() > current.getX())
        third = (previous.getY() > current.getY())
        fourth = (previous.getX() < current.getX())
        self.cells[x][y].addVehicle(self.vehicle)
        self.assertEqual(self.cells[x][y].vehicle[0], first, 'first')
        self.assertEqual(self.cells[x][y].vehicle[1], second, 'second')        
        self.assertEqual(self.cells[x][y].vehicle[2], third, 'third')       
        self.assertEqual(self.cells[x][y].vehicle[3], fourth, 'fourth')

        # Test the number of vehicle in the cell
        self.cells[x][y].addVehicle(self.vehicle2)
        self.assertEqual(self.cells[x][y].vehicleCount, 2, 'number of vehicle')

    # Test removeVehicle
    def testremoveVehicle(self):
        self.vehicle.passedpath = self.vehicle2.passedpath = 1
        previous = self.vehicle.getCell(-1)
        current = self.vehicle.getCell(0)
        x = current.getX()
        y = current.getY()
        first = (previous.getY() < current.getY())
        second = (previous.getX() > current.getX())
        third = (previous.getY() > current.getY())
        fourth = (previous.getX() < current.getX())
        self.cells[x][y].addVehicle(self.vehicle)
        self.cells[x][y].addVehicle(self.vehicle2)
        self.cells[x][y].removeVehicle(self.vehicle)
        self.assertEqual(self.cells[x][y].vehicleCount, 1, 'number of vehicle')


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestCellFunctions)
    unittest.TextTestRunner(verbosity=2).run(suite)
