import pygame
import selectlevel
import statescreen
import const
from pygame.locals import *
from pgu import gui

class AboutDialog(gui.Dialog):
    """ Dialog window showing some details about Sim Traffic. Extends pgu's gui.Dialog """
    
    def __init__(self,**params):
        """ Initiates the Dialog window. The contents are based on the file about.txt """
        title = gui.Label("About Sim Traffic")
        width = 550
        height = 200
        doc = gui.Document(width=550)

        space = title.style.font.size(" ")

        doc.block(align = 0)
        with open("../media/about.txt") as lines:
            for line in lines:
                doc.add(gui.Label(line[0:len(line)-1], font=const.aboutFont))
                doc.br(space[1])
            
        gui.Dialog.__init__(self,title,gui.ScrollArea(doc,width,height))
                
        
class MainMenu(statescreen.StateScreen):
    """ The state of screen that represents the Main page of the game. Extends pgu's gui.App """
    
    def __init__(self, screen, bgcolor=(255,255,255),**params):
        """ Initiates the MainMenu and assigns it to the main screen. Sets up the widget for this screen.
            Parameters: main screen, background color (default white), and other parameters applicable for StateScreen
        """
        statescreen.StateScreen.__init__(self, screen, bgcolor,**params)
        
        #Banner
        self.title = gui.Image("../media/title.jpg")
        
        #Buttons
        self.startbutton = gui.Image("../media/startgame.jpg")
        self.aboutbutton = gui.Image("../media/about.jpg")
        self.quitbutton = gui.Image("../media/quit.jpg")

         #Dimensions of title
        titlewidth = self.title.style.width
        titleheight = self.title.style.height
        
        #Dimensions of buttons
        buttonwidth = self.startbutton.style.width
        buttonheight = self.startbutton.style.height

        #Dimensions of screen
        screenwidth = self.screen.get_width()
        screenheight = self.screen.get_height()

        #Mouse Position
        x = y = 0

        #Positions of title and buttons (not fixed. changed mainly based on screensize)
        xTitle = (screenwidth-titlewidth)/2
        yTitle = 0
        xStartgame = xAbout = xQuit = (screenwidth-buttonwidth)/2
        yStartgame = 2*(screenheight)/3
        yAbout = yStartgame + 50
        yQuit = yAbout + 50

        #Handling button events and putting buttons to the main container (cont)
        self.startbutton.connect(gui.CLICK, const.toSelectLevel, self)
        self.aboutbutton.connect(gui.CLICK,AboutDialog().open, None)
        self.quitbutton.connect(gui.CLICK,self.quit)
        self.addCustomizedButton(self.startbutton,xStartgame,yStartgame)
        self.addCustomizedButton(self.aboutbutton,xAbout,yAbout)
        self.addCustomizedButton(self.quitbutton,xQuit,yQuit)
        
        self.cont.add(self.title,xTitle,yTitle)

