import pygame
import gamestate
import mainmenu
import statescreen
import const
from pygame.locals import *
from pgu import gui

class SelectLevel(statescreen.StateScreen):
    """ The state of screen where the user chooses a difficulty level. This class
        is a subclass of StateScreen
    """
    def __init__(self, screen, bgcolor=(255,255,255),**params):
        """ Initiates the state based on StateScreen's configuration, and adds
            widgets to the containers. One of the main widgets is the MapSelector.
            Parameters: main screen, backgroundcolor (default white), and other params that
            are applicable for statescreen.StateScreen
        """
        statescreen.StateScreen.__init__(self, screen, bgcolor,**params)
        
        # Images, Buttons, and MapSelector
        self.mapimage = gui.Image("../media/map0.png")
        self.mapselector = MapSelector(self)
        self.startbutton = gui.Image("../media/startgame.jpg")
        self.backbutton = gui.Image("../media/back.png")
        
        #Dimensions of the screen
        screenWidth = self.screen.get_width()
        screenHeight = self.screen.get_height()

        #Handling button events and putting buttons to the main container (cont)
        self.backbutton.connect(gui.CLICK,const.toMainMenu, self)
        self.cont.add(self.mapselector, (screenWidth - self.mapselector.style.width)/2, screenHeight/10)
        self.addCustomizedButton(self.startbutton,(screenWidth - self.startbutton.style.width)/2,screenHeight*5/6)
        self.startbutton.connect(gui.CLICK,self.toGameState)
        self.cont.add(self.backbutton, 15,(screenHeight - self.backbutton.style.height - 15))
        self.cont.add(self.mapimage, (screenWidth - 300)/2, 185)
        titleLabel = gui.Label("Select a Difficulty Level", font=const.selTitleFont)
        self.cont.add(titleLabel, (screenWidth-titleLabel.style.width)/2, 10)

    def toGameState(self):
        """ Transitions to GameState by calling const.toGameState with inputfile based on
            the map selected by the user """
        level = self.mapselector.selectedmap
        const.toGameState(self,level)
        
class MapSelector(gui.Container):
    """ This class extends gui.Container so that it will be able to contain other widgets.
        It contains 2 arrows to go to the other level ranges. It also contains thumbnails for
        each map. The map can be chosen either by clicking on the arrows until the desired map
        is chosen and displayed in the select level screen, or simply by clicking on the thumbnail
    """
    
    def __init__(self,slevel,**params):
        """ Initiates the MapSelector.
            Parameters: SelectLevel state associated with this MapSelector, and other params
            applicable for gui.Container
        """
        gui.Container.__init__(self,**params)

        self.selectedmap = 0       #index of map that's clicked by the user
        self.maplevels = []
        self.slevel = slevel
        self.leftarrow = gui.Image("../media/left.png")
        self.leftarrow.connect(gui.CLICK,self.previousRange)
        self.rightarrow = gui.Image("../media/right.png")
        self.rightarrow.connect(gui.CLICK,self.nextRange)
        self.style.width = 800
        self.style.height = 120
        self.add(self.leftarrow,0,0)
        self.add(self.rightarrow,self.style.width-self.rightarrow.style.width,0)
        thumbWidth = (self.style.width-self.leftarrow.style.width-self.rightarrow.style.width-20)/5
        avail = int(const.GAME.attrib["available"])
        for i in range(avail):
            levelThumb = gui.Image("../media/thumb" + str(i) +".png")
            self.maplevels.append(levelThumb)
            self.add(levelThumb, 10+self.leftarrow.style.width+i*thumbWidth, 10)
            levelThumb.connect(gui.CLICK,self.pickMap,i)
            const.setBorder(levelThumb,2)
        for i in range(5-avail):
            unAvail = gui.Image("../media/gray" + str(i+avail) +".png")
            self.add(unAvail, 10+self.leftarrow.style.width+(i+avail)*thumbWidth, 10)
        self.pickMap(self.selectedmap)
        
    def pickMap(self, selectedmap):
        """ Pick a map and set it as the new selected map.
            Parameter: index of the newly selected map
        """
        for lvl in self.maplevels:
            lvl.style.border_color = (255,255,255)
        self.selectedmap = selectedmap;
        self.maplevels[selectedmap].style.border_color = (0,0,0)
        self.repaint()
        self.slevel.mapimage.value = pygame.image.load("../media/map" + str(self.selectedmap) +".png")
        self.slevel.repaint()
        
    def nextRange(self):
        """ Goes to the next level range. Do nothing if there is no next range """
        if (self.selectedmap < len(self.maplevels)-1):
            self.pickMap(self.selectedmap+1)
            

    def previousRange(self):
        """ Goes to the previous level range. Do nothing if there is no previous range """
        if (self.selectedmap > 0):
            self.pickMap(self.selectedmap-1)

