import sys
sys.path.append('../src')
import pygame
import unittest
import map
import const
import cell
import vehicle


class TestMapFunctions(unittest.TestCase):
    # Set up elements needed for testing map - updateMap in particular
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
                for j in range(sizex):
                    if line[j] == 'x' : #building
                        self.cells[j][i] = None
                    elif line[j] == 'i' : #intersection block
                        lb = cell.RoadCell(j,i,False, False, False, True, background = (0,0,0))
                        self.cells[j][i] = lb
                    elif line[j] == 'H' : # busy (high traffic)
                        lb = cell.RoadCell(j,i,True,background = (0,0,0))
                        self.cells[j][i] = lb
                    elif line[j] == 'L' : # not busy (low traffic)
                        lb = cell.RoadCell(j,i,background = (0,0,0))
                        self.cells[j][i] = lb
                    elif line[j] == 'S' : # start cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True,True,background = (0,0,0))
                        const.busyStart.append((j, i))
                        self.cells[j][i] = lb
                    elif line[j] == 's' : # start cell and not busy (lwo traffic)
                        lb = cell.RoadCell(j,i,False,True,background = (0,0,0))
                        const.lowStart.append((j, i))
                        self.cells[j][i] = lb
                    elif line[j] == 'E' : # end cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True, False, True,background = (0,0,0))
                        self.cells[j][i] = lb
                    elif line[j] == 'e' : # end cell and not busy (low traffic)
                        lb = cell.RoadCell(j,i,False,False,True,background = (0,0,0))
                        self.cells[j][i] = lb
                    elif line[j] == '+' : #intersection cell busy
                        lb = cell.IntersectionCell(j,i,True,background = (0,0,0))
                        self.cells[j][i] = lb
		    elif line[j] == '#' : #intersection cell not busy
                        lb = cell.IntersectionCell(j,i,False,background = (0,0,0))
                        self.cells[j][i] = lb
        except IOError:
            print "The file does not exist, exiting"
            
            
        self.vehicles = []
        self.map = map.Map(self.cells, self.vehicles)
        for i in range(50):
            self.map.cars.append(vehicle.Vehicle(self.cells, 10000, 500))
        

    # Test update
    def testGroupCell(self):
        """ checks whether or not the intersection cells are grouped together after the function was called """
        self.map.groupCells()
        
        
        self.assertTrue((self.cells[10][9].block == self.cells[12][10].block and
         self.cells[12][10].block == self.cells[11][12].block and
         self.cells[11][12].block == self.cells[9][11].block),
            'intersection cells that belong to the same block are not grouped together')

        
        
        
        

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestMapFunctions)
    unittest.TextTestRunner(verbosity=2).run(suite)
