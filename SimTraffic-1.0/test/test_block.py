import sys
sys.path.append('../src')
import pygame
import unittest
import sign
import cell
import vehicle
import block
import const
import random
import map

class TestIntersectionBlock(unittest.TestCase):
    def setUp(self):
        """ Setting up 5 small maps with different intersection cell locations """
        self.cells1 = [[None,cell.RoadCell(0,1), cell.IntersectionCell(0,2), None],
                       [cell.IntersectionCell(1,0),cell.RoadCell(1,1), cell.RoadCell(1,2), cell.RoadCell(1,3)],
                       [cell.RoadCell(2,0),cell.RoadCell(2,1), cell.RoadCell(2,2), cell.IntersectionCell(2,3)],
                       [None,cell.IntersectionCell(3,1), cell.RoadCell(3,2), None]]
        self.cells2 = [[None, None,cell.RoadCell(0,2), cell.IntersectionCell(0,3), None],
                       [None, cell.IntersectionCell(1,1),cell.RoadCell(1,2), cell.RoadCell(1,3), cell.RoadCell(1,4)],
                       [None, cell.RoadCell(2,1),cell.RoadCell(2,2), cell.RoadCell(2,3), cell.IntersectionCell(2,4)],
                       [None, None,cell.IntersectionCell(3,2), cell.RoadCell(3,3), None]]
        self.cells3 = [[None, None,cell.RoadCell(0,2), cell.IntersectionCell(0,3), None],
                       [None, cell.IntersectionCell(1,1),cell.RoadCell(1,2), cell.RoadCell(1,3), cell.RoadCell(1,4)],
                       [None, cell.RoadCell(2,1),cell.RoadCell(2,2), cell.RoadCell(2,3), cell.IntersectionCell(2,4)],
                       [None, None,cell.IntersectionCell(3,2), cell.RoadCell(3,3), None],
                       [None, None, None, None, None]]
        self.cells4 = [[None, None, None, None, None],
                       [None, None,cell.RoadCell(1,2), cell.IntersectionCell(1,3), None],
                       [None, cell.IntersectionCell(2,1),cell.RoadCell(2,2), cell.RoadCell(2,3), cell.RoadCell(2,4)],
                       [None, cell.RoadCell(3,1),cell.RoadCell(3,2), cell.RoadCell(3,3), cell.IntersectionCell(3,4)],
                       [None, None,cell.IntersectionCell(4,2), cell.RoadCell(4,3), None],
                       [None, None, None, None, None]]
        self.cells5 = [[None,cell.RoadCell(0,1), cell.IntersectionCell(0,2), None, None],
                       [cell.IntersectionCell(1,0),cell.RoadCell(1,1), cell.RoadCell(1,2), cell.RoadCell(1,3), None],
                       [cell.RoadCell(2,0),cell.RoadCell(2,1), cell.RoadCell(2,2), cell.IntersectionCell(2,3), None],
                       [None,cell.IntersectionCell(3,1), cell.RoadCell(3,2), None, None]]

        const.map = map.Map(self.cells3,None)
        
        self.block1 = block.IntersectionBlock(0,0,self.cells1)
        self.block2 = block.IntersectionBlock(0,1,self.cells2)
        self.block3 = block.IntersectionBlock(0,1,self.cells3)
        self.block4 = block.IntersectionBlock(1,1,self.cells4)
        self.block5 = block.IntersectionBlock(0,0,self.cells5)

    def testInit(self):
        """ Test if the blocks group all intersections in the map properly """
        block1coord = []
        for icell in self.block1.intersectioncells:
            block1coord.append((icell.getX(),icell.getY()))
        block2coord = []
        for icell in self.block2.intersectioncells:
            block2coord.append((icell.getX(),icell.getY()))
        block3coord = []
        for icell in self.block3.intersectioncells:
            block3coord.append((icell.getX(),icell.getY()))
        block4coord = []
        for icell in self.block4.intersectioncells:
            block4coord.append((icell.getX(),icell.getY()))
        block5coord = []
        for icell in self.block5.intersectioncells:
            block5coord.append((icell.getX(),icell.getY()))
        self.assertEqual(block1coord, [(0,2),(1,0),(3,1),(2,3)])
        self.assertEqual(block2coord, [(0,3),(1,1),(3,2),(2,4)])
        self.assertEqual(block3coord, [(0,3),(1,1),(3,2),(2,4)])
        self.assertEqual(block4coord, [(1,3),(2,1),(4,2),(3,4)])
        self.assertEqual(block5coord, [(0,2),(1,0),(3,1),(2,3)])
        
    def testIsClear(self):
        """ Tests if the isClear method works as specified."""
        self.cells3[1][2].vehicleCount += 1
        self.assertFalse(self.block3.isClear(), 'Block 3 should not be clear')
        self.cells3[1][2].vehicleCount -= 1
        self.assertTrue(self.block3.isClear(), 'Block 3 should be clear')
        self.cells3[1][3].vehicleCount += 1
        self.assertFalse(self.block3.isClear(), 'Block 3 should not be clear')
        self.cells3[1][3].vehicleCount -= 1
        self.assertTrue(self.block3.isClear(), 'Block 3 should be clear')
        self.cells3[2][2].vehicleCount += 1
        self.assertFalse(self.block3.isClear(), 'Block 3 should not be clear')
        self.cells3[2][2].vehicleCount -= 1
        self.assertTrue(self.block3.isClear(), 'Block 3 should be clear')
        self.cells3[2][3].vehicleCount += 1
        self.assertFalse(self.block3.isClear(), 'Block 3 should not be clear')
        self.cells3[2][3].vehicleCount -= 1
        self.assertTrue(self.block3.isClear(), 'Block 3 should be clear')

    def testUpdateBlock(self):
        """ Tests if the updateBlock method works as specified.
            Configuring/adding traffic lights to an intersection
            cells in the specified alignment will also auto-configure
            the intersection cells in the other alignment"""
        red1 = int(random.random()*30)
        green1 = int(random.random()*30)
        self.block1.updateBlock(red1,green1,1)
        self.assertEqual(self.cells1[0][2].sign.redTime, green1)
        self.assertEqual(self.cells1[3][1].sign.redTime, green1)
        self.assertEqual(self.cells1[0][2].sign.greenTime, red1)
        self.assertEqual(self.cells1[3][1].sign.greenTime, red1)
        self.assertEqual(self.cells1[1][0].sign.redTime, red1)
        self.assertEqual(self.cells1[2][3].sign.redTime, red1)
        self.assertEqual(self.cells1[1][0].sign.greenTime, green1)
        self.assertEqual(self.cells1[2][3].sign.greenTime, green1)
        red2 = int(random.random()*30)
        green2 = int(random.random()*30)
        self.block2.updateBlock(red2,green2,0)
        self.assertEqual(self.cells2[0][3].sign.redTime, red2)
        self.assertEqual(self.cells2[3][2].sign.redTime, red2)
        self.assertEqual(self.cells2[0][3].sign.greenTime, green2)
        self.assertEqual(self.cells2[3][2].sign.greenTime, green2)
        self.assertEqual(self.cells2[1][1].sign.redTime, green2)
        self.assertEqual(self.cells2[2][4].sign.redTime, green2)
        self.assertEqual(self.cells2[1][1].sign.greenTime, red2)
        self.assertEqual(self.cells2[2][4].sign.greenTime, red2)

    def testAdd2StopSigns(self):
        """ Tests if the add2StopSigns method works as specified
            Adding stop signs to intersection cells in either
            horizontal/vertical alignment"""               
        self.block3.add2StopSigns(0)
        self.assertTrue(self.cells3[0][3].sign)
        self.assertTrue(self.cells3[3][2].sign)
        self.assertFalse(self.cells3[1][1].sign)
        self.assertFalse(self.cells3[2][4].sign)
        self.block4.add2StopSigns(1)
        self.assertFalse(self.cells4[1][3].sign)
        self.assertFalse(self.cells4[4][2].sign)
        self.assertTrue(self.cells4[2][1].sign)
        self.assertTrue(self.cells4[3][4].sign)

    def testAdd4StopSigns(self):
        """ Tests if the add4StopSigns method works as specified
            Adding stop signs to all intersection cells in the block"""    
        self.block5.add4StopSigns()
        self.assertTrue(self.cells5[0][2].sign)
        self.assertTrue(self.cells5[3][1].sign)
        self.assertTrue(self.cells5[1][0].sign)
        self.assertTrue(self.cells5[2][3].sign)
    
    def testRemoveSigns(self):
        """ Tests if the removeSigns method works as specified
            i.e: no signs would exist in all intersection cells in a block
            where the method is called on"""    
        red1 = int(random.random()*30)
        green1 = int(random.random()*30)
        self.block1.updateBlock(red1,green1,1)
        red2 = int(random.random()*30)
        green2 = int(random.random()*30)
        self.block2.updateBlock(red2,green2,0)
        self.block3.add2StopSigns(0)
        self.block4.add2StopSigns(1)
        self.block5.add4StopSigns()
        for block in [self.block1,self.block2,self.block3,self.block4,self.block5]:
            block.removeSigns()
        self.assertFalse(self.cells1[0][2].sign)
        self.assertFalse(self.cells1[3][1].sign)
        self.assertFalse(self.cells1[0][2].sign)
        self.assertFalse(self.cells1[3][1].sign)
        self.assertFalse(self.cells2[0][3].sign)
        self.assertFalse(self.cells2[3][2].sign)
        self.assertFalse(self.cells2[0][3].sign)
        self.assertFalse(self.cells2[3][2].sign)
        self.assertFalse(self.cells3[0][3].sign)
        self.assertFalse(self.cells3[3][2].sign)
        self.assertFalse(self.cells3[1][1].sign)
        self.assertFalse(self.cells3[2][4].sign)
        self.assertFalse(self.cells4[1][3].sign)
        self.assertFalse(self.cells4[4][2].sign)
        self.assertFalse(self.cells4[2][1].sign)
        self.assertFalse(self.cells4[3][4].sign)
        self.assertFalse(self.cells5[0][2].sign)
        self.assertFalse(self.cells5[3][1].sign)
        self.assertFalse(self.cells5[1][0].sign)
        self.assertFalse(self.cells5[2][3].sign)
        
if __name__ == '__main__':
    
    suite = unittest.TestLoader().loadTestsFromTestCase(TestIntersectionBlock)

    unittest.TextTestRunner(verbosity=2).run(suite)

    
    
