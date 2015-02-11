""" This file contains global variables, shared functions, and constants """
import pygame
import gamestate
import mainmenu
import selectlevel
from xml.etree.ElementTree import ElementTree
from os import path
from pygame.locals import *
import shutil
#Global
screen = None

map = None

"""
number displayed on the bottom left for number of vehicles that are currently time-out
"""

"""STATUS"""
timeoutAllowed = 0
timeoutCount = 0
numTimeout = None
win = 1
lose = 0

"""STARTING CELL"""
busyStart = []
lowStart = []

""" COLORS"""
#ROADS
noneColor = (245,163,97)
blockColor = (150,150,150)
busyColor = (97,97,97)
notBusyColor = (176,176,176)
iBusyColor = (120,120,120)
iNotBusyColor = (200,200,200)
selectedIntersection = (72, 55, 165)
#LIGHT
red = (255,0,0)
green = (0,255,0)
#STOP SIGN
stopsign_color = (180,200,220)
#CARS
carColor = (0,0,0)
timeOutColor = (51, 51, 102)
crashColor = red

""" FONTS """
pygame.font.init()
aboutFont = pygame.font.SysFont("Georgia,Garamond", 15)
selTitleFont = pygame.font.SysFont("Garamond", 35, True)
fontBig = pygame.font.SysFont("default", 22, True)
labelFont = pygame.font.SysFont("default", 15, True)
statusFont = pygame.font.SysFont("Courier", 14)

""" IMAGES """
noneImage = pygame.image.load("../media/iconnone.png")
stopImage = pygame.image.load("../media/iconstop.png")
lightImage = pygame.image.load("../media/iconlight.png")

""" THE DOM OBJECT FOR MAP XML FILE """
if (path.exists("maps")):
    if (path.exists("maps/log.xml")):
        pass
    elif (path.exists("maps/init.xml")):
        shutil.copyfile("maps/init.xml","maps/log.xml")
    TREE = ElementTree()
    TREE.parse("maps/log.xml")
    GAME = TREE.getroot()
    NUM_AVAIL = int(GAME.attrib["available"])
    STAGES = TREE.getiterator("stage")
"""
Functions
"""

def toMainMenu(origin):
    """ Transitions to Main Menu """
    newMainMenu = mainmenu.MainMenu(screen)
    origin.quit()
    newMainMenu.start()

def toSelectLevel(origin):
    """ Transitions to Select Level mode """
    newSelectLevel = selectlevel.SelectLevel(screen)
    origin.quit()
    newSelectLevel.start()

def toGameState(origin, level):
    """ Transitions to Game State mode """
    newGameState = gamestate.GameState(screen,level=level)
    origin.quit()
    newGameState.start()

def setBorder(widget, t):
    """ Set border of specified thickness around the specified widget """
    widget.style.border_left = t
    widget.style.border_right = t
    widget.style.border_top = t
    widget.style.border_bottom = t

def updateData(completedStage):
    """ Saves and/or Reloads the game DOM xml file """
    if completedStage == NUM_AVAIL-1 and NUM_AVAIL < int(GAME.attrib["total"]):
        GAME.attrib["available"] = str(NUM_AVAIL+1)
        TREE.write("maps/log.xml")

    
    
    
    
