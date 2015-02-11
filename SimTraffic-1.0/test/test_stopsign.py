import sys
sys.path.append('../src')
import pygame
import unittest
import sign
import block
import cell
import const
import vehicle

class TestStopSignFunctions(unittest.TestCase):
    """ Unit Test for the stop sign module """

    def setUp(self):
        """ Set up elements needed for testing sign """
        # Create cells
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

            print "\n"

        except IOError:
            print "The file does not exist, exiting"

        # Create a new intersection block
        self.myBlock = block.IntersectionBlock(9, 9, self.cells)
        self.myBlock.signLocation[0] = 0
        self.myBlock.signLocation[1] = 0
        self.myBlock.signLocation[2] = 0
        self.myBlock.signLocation[3] = 0

        # Create stop sign objects
        self.myStopSign1 = sign.StopSign(self.myBlock.signLocation[0], self.myBlock)
        self.myStopSign2 = sign.StopSign(self.myBlock.signLocation[1], self.myBlock)
        self.myStopSign3 = sign.StopSign(self.myBlock.signLocation[2], self.myBlock)
        self.myStopSign4 = sign.StopSign(self.myBlock.signLocation[3], self.myBlock)

        # Assign stop signs to intersection cells
        self.cells[9][11].sign = self.myStopSign1
        self.cells[10][9].sign = self.myStopSign2
        self.cells[12][10].sign = self.myStopSign3
        self.cells[11][12].sign = self.myStopSign4

        # Set up block's intersection cells
        self.myBlock.intersectioncells[0] = self.cells[9][11]
        self.myBlock.intersectioncells[1] = self.cells[10][9]
        self.myBlock.intersectioncells[2] = self.cells[12][10]
        self.myBlock.intersectioncells[3] = self.cells[11][12]


    def testsetRank(self):
        """ Test if the stop sign updates vehicles' ranks properly. We
            want the right behavior where when a car approaches a stop sign,
            it will set it's priority rank with respect to other cars at
            the stop signs on that intersection.
        """

        # A sequence of set ranks from initial condition ( all stop signs
        # have rank = 4
        self.myStopSign1.setRank()
        self.assertEqual(self.myStopSign1.rank, 0)
        self.myStopSign2.setRank()
        self.assertEqual(self.myStopSign2.rank, 1)
        self.myStopSign3.setRank()
        self.assertEqual(self.myStopSign3.rank, 2)
        self.myStopSign4.setRank()
        self.assertEqual(self.myStopSign4.rank, 3)

        # Resetting a stop sign's rank back to 4 and use setRank to set some
        # other stop sign's rank
        self.myStopSign2.rank = 4
        self.myStopSign4.setRank()
        self.assertEqual(self.myStopSign4.rank, 1)
        self.myStopSign2.setRank()
        self.assertEqual(self.myStopSign2.rank, 3)

        # When all ranks 0-3 are occupied, setRank shouldn't be able
        # to change anything
        self.myStopSign3.setRank()
        self.assertEqual(self.myStopSign3.rank, 2)

    def testupdate(self):
        """ Test stop sign's update method to ensure that if there is a car
            "in" it, it will count how long the car has been there and set
            member variable elapsed to true if a certain amount of time has
            passed (in this case 1 second)
        """

        testClock = pygame.time.Clock()
        testTime = 0
        testClock.tick()

        # Test the case where there are no cars on the stop sign. In this case
        # no update should be performed
        self.myStopSign1.hasCar = False
        while testTime < 3000.0:
            testTime += testClock.tick()
            self.myStopSign1.update(self.cells[9][11])
        self.assertEqual( self.myStopSign1.stopSignClock, 0 )

        # Test the case where car has been on the stop sign for less then
        # one second. The elapsed member variable should stay False.
        testTime = 0
        self.myStopSign2.stopSignClock = 0
        self.myStopSign2.hasCar = True
        self.myStopSign2.update(self.cells[10][9])
        self.myStopSign2.elapsed = False
        while testTime <= 300.0:
            testTime += testClock.tick()
            self.myStopSign2.update(self.cells[10][9])
        self.assertFalse( self.myStopSign2.elapsed )

        # Test the case where car has been on the stop sign for more then
        # one second. The elapsed member variable should become True.
        testTime = 0
        self.myStopSign1.stopSignClock = 0
        self.myStopSign1.hasCar = True
        self.myStopSign1.elapsed = False
        while testTime <= 1000.0:
            testTime += testClock.tick()
            self.myStopSign1.update(self.cells[9][11])
        self.assertTrue( self.myStopSign1.elapsed )


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestStopSignFunctions)
    unittest.TextTestRunner(verbosity=2).run(suite)
