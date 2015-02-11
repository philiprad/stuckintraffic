import pygame
from pygame.locals import *
from pgu import gui


class Scroller(gui.Spacer):
    """ Automatically keeps the LogWindow scrolled at the bottom. Code reference: pgu example, gui17.py """
    def __init__(self,logw,**params):
        gui.Spacer.__init__(self,**params)
        self.logw = logw
        
    def resize(self,width=None,height=None):
        self.logw.set_vertical_scroll(50000)
        return 1,1  
        
class LogWindow(gui.ScrollArea):
    """ Logs user's actions and other significant events of the game. Extends gui.ScrollArea """
    def __init__(self, width=300,height=110):
        """ Initiates the logwindow with specified width (default 300) and height (default 100)
            Parameters: width (default 300) and height (default 110) """
        self.log = gui.Table()
        gui.ScrollArea.__init__(self, self.log, width,height,False)
        
        self.log.td(Scroller(self,width=1,height=1))

    def storeInfo(self, info):
        """ Stores the info in this LogWindow.
            Parameter: info, the string to be stored
        """
        self.log.tr()
        logFont = pygame.font.SysFont("Courier New,Courier", 14)
        self.log.td(gui.Label(info,font=logFont),align=-1)

    
