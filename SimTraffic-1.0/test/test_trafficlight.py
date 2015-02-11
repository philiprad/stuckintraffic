import sys
sys.path.append('../src')
import pygame
import unittest
import sign
import cell
import block
import const
import map


class TestTrafficLightFunctions(unittest.TestCase):
    """ Unit Test for the traffic light module """
    def setUp(self):
        """ Set up elements needed for testing sign """
        # Dummy cells
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
                        self.cells[j][i] = lb
                        print '1',
                    elif line[j] == 's' : # start cell and not busy (lwo traffic)
                        lb = cell.RoadCell(j,i,False,True,background = (0,0,0))
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

            print "\n"

        except IOError:
            print "The file does not exist, exiting"
            

        # dummy block
        self.myBlock = block.IntersectionBlock(9, 9, self.cells) 

        # Set up block's intersection cells
        self.myBlock.intersectioncells[0] = self.cells[9][11]
        self.myBlock.intersectioncells[1] = self.cells[10][9]
        self.myBlock.intersectioncells[2] = self.cells[12][10]
        self.myBlock.intersectioncells[3] = self.cells[11][12]

        # Create a new traffic light object
        self.mySign = sign.TrafficLight(2, 4)
        self.mySign.redLight = True

        self.cells[9][11].sign = self.mySign

        const.map = map.Map(self.cells,None)

    def testupdate(self):
        """ Test the update method by simulating a dummy game loop. This test
            will check the traffic light's status at certain time interval to
            ensure traffic light behaves properly according to its time
            parameters.
        """
        # Set the traffic light to red
        self.mySign.redLight = True

        # Clock to count the looping time
        myClock = pygame.time.Clock()
        myClock.tick()
        # How long the loop has run
        currentTime = 0

        # Test checkpoints
        test1 = False
        test2 = False
        test3 = False

        # Game loop dummy
        while(True):
            # Update the traffic light
            self.mySign.update(self.cells[9][11])

            # Tests
            if(currentTime > 0 and currentTime < 2000 and not test1):
                self.assertTrue(self.mySign.isRed(), "MySign's isRed should be True")
                test1 = True
            if(currentTime > 2000 and currentTime < 6000 and not test2):
                self.assertFalse(self.mySign.isRed(), "MySign's isRed should be False")
                test2 = True
            if(currentTime > 6000 and currentTime < 8000 and not test3):
                self.assertTrue(self.mySign.isRed(), "MySign's isRed should be True")
                test3 = True

            # Update loop time
            currentTime += myClock.tick()
            # Adjust frame rate so it mimics the real loop (not really needed here)
            pygame.time.wait(10)

            # Done with testing
            if( currentTime > 10000 ):
                break
                

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestTrafficLightFunctions)
    unittest.TextTestRunner(verbosity=2).run(suite)