import pygame
import gamestate
import cell
import sign
import pygame
import const
from pygame.locals import *

from pgu import gui

class ComponentWindow(gui.Table):
    """ Displays the status of Traffic Sign in the selected intersection cell. Extends gui.Label """
    def __init__(self, gstate):
        """ Initiates the ComponentWindow's layout and widgets.
            Parameter: the GameState associated with this ComponentWindow
        """
        gui.Table.__init__(self)
        self.gstate = gstate
        self.style.width = 200
        self.cell = None
        self.signimage = gui.Image(const.noneImage) #Example
        self.signinfo = {}

        self.signinfo["location"] = gui.Label("",align=-0.8)
        self.signinfo["activated"] = gui.Label("", align=-0.8)
        self.signinfo["location"].style.width = self.signinfo["activated"].style.width = 100
        self.signinfo["red"] = gui.Label("    ")
        self.signinfo["green"] = gui.Label("    ")

        # Replace button
        self.replacebutton = gui.Button("Replace", disabled = True, width = 80, height = 20)
        self.replacebutton.connect(gui.CLICK, self.replaceSign)
        # Remove button
        self.removebutton = gui.Button("Remove",disabled=True, width = 80, height=20)
        self.removebutton.connect(gui.CLICK, self.reset)
        # Configure button
        self.configurebutton = gui.Button("Configure",disabled=True, width = 80, height=20)
        self.configurebutton.connect(gui.CLICK, self.gstate.controldialog.open)
        self.windowSetup()

    def replaceSign(self):
        self.configurebutton.disabled = True
        self.gstate.picksigndialog.cell = self.cell
        self.gstate.picksigndialog.connectButtons()
        self.gstate.picksigndialog.open()

    def windowSetup(self):
        """ Sets up the widgets for this Component Window """
        imgcontainer = gui.Container(width=2*self.style.width/5,height=200, background=(255,255,255))
        infotable = gui.Table(width=3*self.style.width/5,height=200, background=(235,235,235))
        
        fontBig = pygame.font.SysFont("default", 25, True)
        infotable.tr()
        infotable.td(gui.Label("Status", font=fontBig), height=25)
        infotable.tr()
        infotable.td(gui.Label("Location:", color=(0,0,255)), align=-1)
        infotable.tr()
        infotable.td(self.signinfo["location"])
        infotable.tr()
        infotable.td(gui.Label("Status:", color=(0,0,255)), align=-1)
        infotable.tr()
        infotable.td(self.signinfo["activated"])
        infotable.tr()
        infotable.td(self.replacebutton, align=-0.8, height=30)
        infotable.tr()
        infotable.td(self.removebutton, align=-0.8, height=30)
        infotable.tr()
        infotable.td(self.configurebutton, align=-0.8, height=30)

        interval_table = gui.Table(width=200,height=50, background=(235,235,235))
        interval_table.tr()
        interval_table.td(gui.Label("Red:", color=(255,0,0)), align=-1)
        interval_table.tr()
        interval_table.td(self.signinfo["red"])
        interval_table.tr()
        interval_table.td(gui.Label("Green:", color=(0,255,0)), align=-1)
        interval_table.tr()
        interval_table.td(self.signinfo["green"])
        
        imgcontainer.add(self.signimage, 0, 10)
        self.tr()
        self.td(gui.Label("Traffic Sign",font=fontBig,color=(255,255,255)),
                height=30, background=(0,0,51),colspan=2)
        self.tr()
        self.td(imgcontainer)
        self.td(infotable)
        self.tr()
        self.td(gui.Label("Time Interval",font=fontBig,color=(255,255,255)),
                height=30, background=(0,0,51),colspan=2)
        self.tr()
        self.td(interval_table,colspan=2)  
        
    def storeSignInfo(self, cell):
        """ Stores the info of the Traffic Sign of the selected Intersection Cell """
        self.cell = cell
        self.replacebutton.disabled = False
        self.removebutton.disabled = False
        self.configurebutton.disabled = False
        self.repaint()

    def reset(self):
        """ Disconnects the association between this ComponentWindow to any previously
            selected Intersection Cell """
        self.gstate.removeSign()
        self.repaint()
        
    def repaint(self):
        """ Adapts gui.Table.repaint() by adding the functionality to check if sign exists or not
            Disables buttons if there is no sign in the currently clicked intersection cell """
        a_sign = self.cell.sign
        if (not a_sign==None):
            if (isinstance(a_sign, sign.TrafficLight)):
                self.signinfo["activated"].value = "Activated"
                self.signinfo["red"].value = str(self.cell.sign.redTime)
                self.signinfo["green"].value = str(self.cell.sign.greenTime)
                self.signimage.value = const.lightImage
                self.configurebutton.disabled = False
            elif (isinstance(a_sign, sign.StopSign)):
                self.signimage.value = const.stopImage
                self.configurebutton.disabled = True
            self.replacebutton.disabled = False
            self.removebutton.disabled = False                
                
        else:
            self.signinfo["activated"].value = "Unactivated"
            self.signimage.value = const.noneImage
            self.replacebutton.disabled = True
            self.removebutton.disabled = True
            self.configurebutton.disabled = True

        gui.Table.repaint(self)
