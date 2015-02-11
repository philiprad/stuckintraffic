import pygame
from pygame.locals import *
from pgu import gui

class StateScreen(gui.App):
    """ A class representing the three states. Thus, MainMenu, SelectLevel, and GameState extends this class.
        This class extends pgu's gui.App so that it can be easily run, stopped, and transitioned to other states.
    """
    
    def __init__(self,screen,bgcolor=(255,255,255),**params):
        """ Initiates Statescreen, assigns it to the screen, and sets up its main container in which widgets will be added. """
        gui.App.__init__(self,**params)
        self.connect(gui.QUIT,self.quit,None)
        self.screen = screen
        
        #Container
        self.cont = gui.Container(width=screen.get_width(),height=screen.get_height(),background=bgcolor)

    def addCustomizedButton(self,button, x,y):
        """ Customize a button and put in on the main container """
        button.connect(gui.ENTER, self.hoveredButton, button, y+button.style.height/2)
        button.connect(gui.EXIT, self.leftButton)
        self.cont.add(button,x,y)
  
    def hoveredButton(self, button,y):
        """ Customize a hovered button """  
        pygame.draw.line(self.screen,(0,0,0),(0,y-1),(self.screen.get_width(),y-1),4)
        button.repaint()
        pygame.display.flip()
     
    def leftButton(self):
        """ Clear the hover effects mouse cursor leaves a customized button"""    
        self.cont.repaint()
        pygame.display.flip()
    
    def start(self):
        """ Start this screen/state. This basically runs the app on the screen based on its main container. """
        self.run(self.cont,self.screen)
    
        
