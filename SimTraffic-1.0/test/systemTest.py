import sys
sys.path.append('../src')
sys.path.append('../src/maps')
import pygame
import unittest
import sign
import cell
#import gamestate
import map
import statescreen
import block


class TestSystem(unittest.TestCase):
     # Set up elements needed for system testing
    def setUp(self):
        try:
            fileHandle = open('test.txt','r')

            for blank in range(5):
                fileHandle.readline()

            size = fileHandle.readline().split(',')
            self.sizex = int(size[0])
            self.sizey = int(size[1])
            fileHandle.readline()

            self.cells=[]

            for i in range(self.sizey):
                self.cells.append([])
                for j in range(self.sizex):
                    self.cells[i].append(0)


            for i in range(self.sizey):
                line = fileHandle.readline()
                for j in range(self.sizex):
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
                        self.cells[j][i] = lb
                    elif line[j] == 's' : # start cell and not busy (lwo traffic)
                        lb = cell.RoadCell(j,i,False,True,background = (0,0,0))
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
        self.map.groupCells()

    #test placing a traffic light
    def testPlacingTrafficLight(self):
        """ test whether a traffic light is sucessfully being placed on a specific intersection cell or not """
        x = 0
        y = 0
        check = True
        for i in range(self.sizey):
            if check :
                for j in range(self.sizex) :
                    if isinstance(self.cells[j][i], cell.IntersectionCell):
                       x = j
                       y = i
                       check = False
                       break
            else :
                 break

        testCell = self.cells[x][y]
        testCell.setTrafficSign(5,10)
        testSign = testCell.sign
        self.assertTrue(isinstance(testSign, sign.TrafficLight), "Sign at these intersection cell should be a traffic light, not None or NULL or Stop Sign")


    #test placing a 4-way stop sign and 2-way stop sign
    def testPlacingStopSign(self):
         """ test whether a 4-way stop sign is sucessfully being placed on a specific intersection cell or not """
         x = 0
         y = 0
         check = True
         for i in range(self.sizey):
            if check :
                for j in range(self.sizex) :
                    if isinstance(self.cells[j][i], cell.IntersectionCell):
                       x = j
                       y = i
                       check = False
                       break
            else :
                 break

         testCell = self.cells[x][y]
         
         # test placing a 4-way stop sign
         testCell.removeSign()
         testCell.block.add4StopSigns()
         testSign = testCell.sign
         self.assertTrue(isinstance(testSign, sign.StopSign), "Sign at these intersection cell should be a stop sign, not None or NULL or Traffic Light")
         
         # test placing a 2-way stop sign
         testCell.removeSign()
         testCell.block.add2StopSigns(1)
         testSign = testCell.sign
         self.assertTrue(isinstance(testSign, sign.StopSign), "Sign at these intersection cell should be a stop sign, not None or NULL or Traffic Light")


    # test removing a traffic sign, both traffic light and stop sign
    def testRemoveTrafficSign(self):
        """ test whether a traffic sign is actually being removed from an intersection cell or not """
        x = 0
        y = 0
        check = True
        for i in range(self.sizey):
            if check :
                for j in range(self.sizex) :
                    if isinstance(self.cells[j][i], cell.IntersectionCell):
                       x = j
                       y = i
                       check = False
                       break
            else :
                 break

        testCell = self.cells[x][y]
        
        # test removing a traffic light
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight), "Sign should be traffic light")
        testCell.removeSign()
        self.assertTrue(testCell.sign == None, "Sign should be None after being removed")

        # test removing a 4-way stop sign
        testCell.block.add4StopSigns()
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        self.assertTrue(testCell.sign == None, "Sign should be None after being removed")
        
        # test removing a 2-way stop sign
        testCell.block.add2StopSigns(1)
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        self.assertTrue(testCell.sign == None, "Sign should be None after being removed")


    # test replacing a traffic sign, traffic light to stop sign and vice versa. Also testing for replacing with same type of traffic sign.
    def testReplaceTrafficSign(self):
        """ test whether a traffic sign on a specific intersection cell is being replaced by another traffic sign or not """
        x = 0
        y = 0
        check = True
        for i in range(self.sizey):
            if check :
                for j in range(self.sizex) :
                    if isinstance(self.cells[j][i], cell.IntersectionCell):
                       x = j
                       y = i
                       check = False
                       break
            else :
                 break

        testCell = self.cells[x][y]
        
        # replacing a traffic light with 4-way stop sign
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight), "Sign should be traffic light")
        testCell.removeSign()
        testCell.block.add4StopSigns()
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Now sign should be a stop sign, since we already replaced traffic light with a stop sign")
        
        testCell.removeSign()

        # replacing a traffic light with 2-way stop sign
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight), "Sign should be traffic light")
        testCell.removeSign()
        testCell.block.add2StopSigns(1)
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Now sign should be a stop sign, since we already replaced traffic light with a stop sign")

        testCell.removeSign()

        #replacing a 4-way stop sign with a traffic light
        testCell.block.add4StopSigns()
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight), "Now, sign should be traffic light after being replaced")

        testCell.removeSign()

        #replacing a 2-way stop sign with a traffic light
        testCell.block.add2StopSigns(1)
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight), "Now, sign should be traffic light after being replaced")
        
        testCell.removeSign()

        # replacing a 2-way stop sign with 4-way stop sign
        testCell.block.add2StopSigns(1)
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        testCell.block.add4StopSigns()
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be still a stop sign")

        testCell.removeSign()

        # replacing a 4-way stop sign with 2-way stop sign
        testCell.block.add4StopSigns()
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be a stop sign")
        testCell.removeSign()
        testCell.block.add2StopSigns(1)
        self.assertTrue(isinstance(testCell.sign, sign.StopSign), "Sign should be still a stop sign")

        testCell.removeSign()
        
        # replacing a traffic light with another traffic light
        testCell.setTrafficSign(5,10)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight) and testCell.sign.getRedTime() == 5 and testCell.sign.getGreenTime() == 10, "Sign should be a traffic light with red time = 5 and green time = 10")
        testCell.removeSign()
        testCell.setTrafficSign(7,11)
        self.assertTrue(isinstance(testCell.sign, sign.TrafficLight) and testCell.sign.getRedTime() == 7 and testCell.sign.getGreenTime() == 11, "Now, sign should be a traffic light with red time = 7 and green time = 11")


    # test configuring a traffic light timer
    def testConfigureTrafficLight(self):
        """ test whether a traffic light timer is being changed or not if we try to configure it """
        x = 0
        y = 0
        check = True
        for i in range(self.sizey):
            if check :
                for j in range(self.sizex) :
                    if isinstance(self.cells[j][i], cell.IntersectionCell):
                       x = j
                       y = i
                       check = False
                       break
            else :
                 break

        testCell = self.cells[x][y]
        testCell.setTrafficSign(5,10)
        self.assertTrue(testCell.sign.getRedTime() == 5 and testCell.sign.getGreenTime() == 10, "Red time should be 5 and Green time should be 10")

        #configure the red time to be 8 and green time to be 6
        testCell.setTrafficSign(8,6)
        self.assertTrue(testCell.sign.getRedTime() == 8 and testCell.sign.getGreenTime() == 6, "After being configured, now the red time should be 8 and green time should be 6")

if __name__ == '__main__':

    suite = unittest.TestLoader().loadTestsFromTestCase(TestSystem)

    unittest.TextTestRunner(verbosity=2).run(suite)

