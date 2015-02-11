import sys
sys.path.append('../src')
import pygame
import unittest
import vehicle
import cell
import const
import map
import sign
import block
from pgu import gui


class TestVehicleFunctions(unittest.TestCase):

    def setUp(self):
        
        
        
        try:
            fileHandle = open('test.txt','r')
            
            """
            for blank in range(5):
                fileHandle.readline()
            """
            
            setTime = float(fileHandle.readline())
            simTime = float(fileHandle.readline())
            timeoutTime = int(fileHandle.readline())
            const.timeoutAllowed = int(fileHandle.readline())
            const.timeoutCount = 0
            const.numTimeout = gui.Label(str('%(count)02d/%(tot)02d' % {'count':const.timeoutCount, 'tot':const.timeoutAllowed }),
                                     font=const.statusFont)
            cars = int(fileHandle.readline())
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
            
            self.vehicle = vehicle.Vehicle(self.cells, 500) #pass random large value for timeouttime for testing

            self.map = const.map = map.Map(self.cells,(self.vehicle))
            
        except IOError:
            print "The file does not exist, exiting"

        

    def testComputePath(self):
        """ Tests if the vehicle.computePath works correctly.
            1. vehicles move on the road cell or intersection cell
            2. vehicles start the path on the edge of the map (so it doesn't suddenly appear out of nowhere)
            3. vehicles don't move in diagonal direction
            4. vehicles always move forward  """

        # vihecles never get out of the road (roadcell or intersectioncell)
        for p in self.vehicle.path:
            x = p.getX()
            y = p.getY()
            self.assertTrue(self.cells[x][y],
                                 'vehicle path not on the road')
        print "Passed first test."
        
        # the first and the last on the path is on the edge of the map 
        startCell = self.vehicle.path[0]
        endCell = self.vehicle.path[len(self.vehicle.path)-1]
        x = startCell.getX()
        y = startCell.getY()

        self.assertTrue(self.cells[x][y].start,
                        'Path doesn\'t start or end on the edge of the map')
                   
        x = endCell.getX()
        y = endCell.getY()
            
        self.assertTrue(self.cells[x][y].end,
                        'Path doesn\'t end on the edge of the map')

        print "Passed second test."
        
        # vehicles only move to the neighboring cell            
        pBefore = self.vehicle.path[0]
        for i in range(1, len(self.vehicle.path)):
            pAfter = self.vehicle.path[i]

            x1 = pBefore.getX()
            y1 = pBefore.getY()
            
            x2 = pAfter.getX()
            y2 = pAfter.getY()

            xDiff = abs(x1 - x2)
            yDiff = abs(y1 - y2)
            
            self.assertTrue((((xDiff == 1) and (yDiff == 0)) or ((yDiff == 1) and (xDiff == 0))),
                'Vehicles don\'t move to the neighboring cell')
            pBefore = pAfter
        
        print "Passed third test."
        
        # vehicles always move forward
        # before current after
        pBefore = self.vehicle.path[0]
        pCurrent = self.vehicle.path[1]
        for i in range(2, len(self.vehicle.path)):
            pAfter = self.vehicle.path[i]
            
            x1 = pBefore.getX()
            y1 = pBefore.getY()
            
            x2 = pCurrent.getX()
            y2 = pCurrent.getY()
                               
            x3 = pAfter.getX()
            y3 = pAfter.getY()
            
            if(x1 < x2 and y2 == y3): #assert(y1 == y2)
                self.assertTrue(x2 < x3,
                                'Vehicles don\'t move forward(1)')
            elif(x1 > x2 and y2 == y3): #assert(y1 == y2)
                self.assertTrue(x2 > x3,
                                'Vehicles don\'t move forward(2)')
            elif(y1 < y2 and x2 == x3): #assert(x1 == x2)
                self.assertTrue(y2 < y3,
                                'Vehicles don\'t move forward(3)')
            elif(y1 > y2 and x2 == x3): #assert(x1 == x2)
                self.assertTrue(y2 > y3,
                                'Vehicles don\'t move forward(3)')
            pBefore = pCurrent
            pCurrent = pAfter            
                              
        print "Passed fourth test."
        
        
    def testTimeOutTimer(self):
        """ Test if vehicle.setWaiting()
            * time increases when the vehicle is waiting, stays the same if not
        """
        eachTimeWait = 100
        
        self.vehicle.setWaiting(False)
        tBefore = self.vehicle.timeoutTime
        pygame.time.wait(eachTimeWait)
        self.vehicle.setWaiting(False)
        print "timeouttime 1", self.vehicle.timeoutTime
        
        self.assertEqual(tBefore, self.vehicle.timeoutTime, 'Time should\'t increase when not waiting')
        
        
        tBefore = self.vehicle.timeoutTime
        pygame.time.wait(eachTimeWait)
        self.vehicle.setWaiting(True)
        print "timeouttime 2", self.vehicle.timeoutTime
        
        self.assertEqual(tBefore, self.vehicle.timeoutTime, 'Time should\'t increase when it just started waiting')
        
        tBefore =self.vehicle.timeoutTime
        pygame.time.wait(eachTimeWait)
        
        self.vehicle.setWaiting(False)
        print "timeouttime 3", self.vehicle.timeoutTime
        self.assertNotEqual(tBefore, self.vehicle.timeoutTime, 'Time didn\'t increase when vehicle is waiting')
        
        self.vehicle.setWaiting(True)
        tBefore = self.vehicle.timeoutTime
        pygame.time.wait(eachTimeWait)
        print "timeouttime 4", self.vehicle.timeoutTime
        
        self.vehicle.setWaiting(True)
        print "timeouttime 5", self.vehicle.timeoutTime
        self.assertNotEqual(tBefore, self.vehicle.timeoutTime, 'Time didn\'t increase when vehicle is waiting')
        
        print "Passed the first test"

    def testTimeOutTimer2(self):
        """
            Test if vehicle's increaseTime() works correctly
            * status set to time-out if it exceeds limit
        """
        eachTimeWait = 100
        self.vehicle.setWaiting(False)
        tBefore = self.vehicle.timeoutTime
        
        timesBeforeTimeout = (self.vehicle.time - tBefore + eachTimeWait)/ eachTimeWait
        
        for i in range(timesBeforeTimeout):

            self.vehicle.setWaiting(True)
            
            pygame.time.wait(eachTimeWait)

            self.vehicle.increaseTime()
            
            print "timeouttime: ", self.vehicle.timeoutTime
            
            
        self.assertTrue(self.vehicle.timeout, 'Vehicle should show timeout after the wait')
        
        print "Passed the second test"
        


    def testmoveCheck(self):
        """
        test if vehicle's moveCheck() works properly.
        1. vehicle should wait on red light
        2. vehicle should wait on green light if the intersection is not clear
        3. vehicle should not wait on green light if the intersection is clear
        """
        
        # TEST 1:
        self.vehicle.passedpath = 0
        i = 0
        while i < len(self.vehicle.path):
            if isinstance(self.vehicle.getCell(i), cell.IntersectionCell):
                x = self.vehicle.getCell(i).getX()
                y = self.vehicle.getCell(i).getY()
                self.vehicle.getCell(i).setTrafficSign(1000, 0)
                break
            i += 1

        self.vehicle.passedpath = i
        self.vehicle.moveCheck()
        self.assertTrue(self.vehicle.isWaiting(), 'Vehicle should have wait on red light')

        print "Passed the first test"
        
        
        
        # TEST 2:
        self.vehicle.passedpath = 0
        i = 0
        while i < len(self.vehicle.path):
            if isinstance(self.vehicle.getCell(i), cell.IntersectionCell):
                self.vehicle.getCell(i).setTrafficSign(0, 1000)
                const.map.getCell(self.vehicle.getCell(i).block.intersectioncells[0].getX()+1, self.vehicle.getCell(i).block.intersectioncells[0].getY()).vehicleCount = 1
                self.vehicle.passedpath = i
                self.vehicle.moveCheck()
                self.assertTrue(self.vehicle.isWaiting(), 'Vehicle should have wait on green light if the intersection is not clear')
                break
            i += 1

        print "Passed the second test"
        

        # TEST 3:
        self.vehicle.passedpath = 0
        i = 0
        while i < len(self.vehicle.path):
            if isinstance(self.vehicle.getCell(i), cell.IntersectionCell):
                x = self.vehicle.getCell(i).getX()
                y = self.vehicle.getCell(i).getY()
                self.vehicle.getCell(i).setTrafficSign(0, 1000)
                break
            i += 1
        self.vehicle.moveCheck()
        self.assertFalse(self.vehicle.isWaiting(), 'Vehicle should not wait on green light if the intersection is clear')
        
        print "Passed the third test" 
    
    def testremoveVehicle(self):
        """
        test if vehicle's removeVehicle() works properly.
        """
        self.vehicle.passedpath = 0
        previous = self.vehicle.getCell(0).vehicleCount
        self.vehicle.move()

        self.assertEqual(self.vehicle.getCell(-1).vehicleCount, previous-1, 'Vehicle counter should decrease on the previous cell after the vehicle move')
        
    
if __name__ == '__main__':
    
    suite = unittest.TestLoader().loadTestsFromTestCase(TestVehicleFunctions)

    unittest.TextTestRunner(verbosity=2).run(suite)

