import pygame
import statescreen
import componentwindow
import logwindow
import map
import cell
import vehicle
import block
import const
from statescreen import *
from pygame.locals import *
from random import *
from pgu import gui

class MenuDialog(gui.Dialog):
    """ The MenuDialog trigerred when the Menu button is pressed. Extends pgu's gui.Dialog """
    
    def __init__(self,gstate,**params):
        """ Initiates the dialog by a fixed title label and dimensions. Sets up the buttons in the MenuDialog
            Parameters: Associated GameState and other parameters applicable for gui.Dialog"""
        title = gui.Label("Game Menu")
        width = 400
        height = 200
        self.gstate = gstate
        # Table containing the contents (buttons) of the dialog window
        self.buttons = gui.Table(width=width,height=height)
        self.buttonSetup()
        gui.Dialog.__init__(self,title,self.buttons)

    
    def buttonSetup(self):
        """ Setup the buttons in the dialog window.
            Parameter: the container
        """
        resume_button = gui.Button("Back to the Game")
        restart_button = gui.Button("Restart Game")
        selectlevel_button = gui.Button("Select another level")
        mainmenu_button = gui.Button("Go to Main Menu",width=300)

        for button in [resume_button,restart_button,selectlevel_button,mainmenu_button]:
            button.connect(gui.CLICK, self.close)
            button.style.height = 35
            button.style.width = 300
            self.buttons.tr()
            self.buttons.td(button)

        restart_button.connect(gui.CLICK, const.toGameState, self.gstate, self.gstate.level)
        selectlevel_button.connect(gui.CLICK, const.toSelectLevel, self.gstate)
        mainmenu_button.connect(gui.CLICK, const.toMainMenu,self.gstate)

    def close(self, other=None):
        """ Works just the same as gui.Dialog's close() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = False
        gui.Dialog.close(self)

    def open(self, args0=None, args1=None, args2=None):
        """ Works just the same as gui.Dialog's open() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = True
        gui.Dialog.open(self)

class PickSignDialog(gui.Dialog):
    """ Pick Traffic Sign pop up window. Player will use this window to
        choose desired traffic sign to be placed/replaced on the intersections
    """
    def __init__(self,gstate,**params):
        self.title = gui.Label("Pick Sign")
        width = 400
        height = 200
        self.gstate = gstate
        self.cell = None
        # Table containing the contents (buttons) of the dialog window
        self.buttons = gui.Table(width=width,height=height)
        self.trafficlight_button = gui.Button("Traffic Light")
        self.stopsign2_button = gui.Button("2 Stop Signs")
        self.stopsign4_button = gui.Button("4 Stop Signs")
        self.cancel_button = gui.Button("Cancel", width=300)

        for button in [self.trafficlight_button, self.stopsign2_button, self.stopsign4_button, self.cancel_button]:
            button.connect(gui.CLICK, self.close)
            button.style.height = 35
            button.style.width = 300
            self.buttons.tr()
            self.buttons.td(button)

        #self.connectButtons()

        gui.Dialog.__init__(self,self.title,self.buttons)

    def connectButtons(self):
        """ Connect PickSignDialog buttons to the corresponding events (functions) """
        for button in [self.trafficlight_button, self.stopsign2_button, self.stopsign4_button, self.cancel_button]:
            button.disconnect(gui.CLICK)
        self.trafficlight_button.connect(gui.CLICK, self.close)
        self.stopsign2_button.connect(gui.CLICK, self.close)
        self.stopsign4_button.connect(gui.CLICK, self.close)
        self.cancel_button.connect(gui.CLICK, self.close)
        if( self.cell and self.cell.block ):
            self.trafficlight_button.connect(gui.CLICK, self.gstate.controldialog.open)
            self.stopsign2_button.connect(gui.CLICK, self.cell.block.add2StopSigns, self.cell.direction)
            self.stopsign4_button.connect(gui.CLICK, self.cell.block.add4StopSigns)
            self.stopsign2_button.connect(gui.CLICK, self.submitQuery)
            self.stopsign4_button.connect(gui.CLICK, self.submitQuery)

    def submitQuery(self):
        """ Submit query that stop sign has been added """
        self.gstate.logwindow.storeInfo("Added Stop Signs")
        self.gstate.componentwindow.repaint()
        self.close()
        
    def close(self, other=None):
        """ Works just the same as gui.Dialog's close() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = False
        gui.Dialog.close(self)

    def open(self, args0=None, args1=None, args2=None):
        """ Works just the same as gui.Dialog's open() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = True
        gui.Dialog.open(self)
        
class ControlDialog(gui.Dialog):
    """ The ControlDialog used to control the attributes of traffic lights """
    
    def __init__(self,gstate,**params):
        """ Initiates the dialog by a fixed title label and dimensions. Sets up the widgets in the ControlDialog.
            Parameters: Associated GameState and other parameters applicable for gui.Dialog"""
        title = gui.Label("Traffic Sign Configuration")
        width=400
        height=200
        self.gstate = gstate
        self.rSlider = gui.HSlider(10,1,30,20,width=100)
        self.rLabel = gui.Label("", color=(255,0,0), background=(255,255,255))
        self.rSlider.connect(gui.CHANGE, self.updateLabel)
        self.gLabel = gui.Label("", color=(0,255,0), background=(255,255,255))
        self.gSlider = gui.HSlider(10,1,30,20,width=100)
        self.gSlider.connect(gui.CHANGE, self.updateLabel)

        self.rLabel.style.width = self.gLabel.style.width = 25
        self.updateLabel()
        # Table containing the content of the dialog window
        table = gui.Table()
        self.controlSetup(table)

        gui.Dialog.__init__(self,title,table)

    def controlSetup(self, table):
        """ Setup the contents of the dialog window, mainly sliders for red/green light intervals and buttons to submit query """
        submit = gui.Button("OK",width=60, height=25)
        submit.connect(gui.CLICK,self.submitQuery)
        cancel = gui.Button("Cancel",width=60, height=25)
        cancel.connect(gui.CLICK,self.close)
        
        table.tr()
        table.td(gui.Label("Setup Time Interval"),height=35, colspan=2)
        table.tr()
        table.td(gui.Label("Red Interval:", color=(255,0,0)), width=150, height=25, align=-1)
        table.td(self.rSlider)
        table.tr()
        table.td(gui.Spacer(1,1))
        table.td(self.rLabel, width=20,align=-0.8)
        table.tr()
        table.td(gui.Label("Green Interval:", color=(0,255,0)), width=150, height=25, align=-1)
        table.td(self.gSlider)
        table.tr()
        table.td(gui.Spacer(1,1))
        table.td(self.gLabel,width=20,align=-0.8)
        table.tr()
        table.td(gui.Spacer(200,30), colspan=2)
        table.tr()
        table.td(submit)
        table.td(cancel)

    def updateLabel(self):
        """ Updates the indicator labels when the slider(s) is changed """
        self.rLabel.value = ('%(val)02d' % \
                            {'val':self.rSlider.value})
        self.gLabel.value = ('%(val)02d' % \
                            {'val':self.gSlider.value})
        self.rLabel.repaint()
        self.gLabel.repaint()
        
    def submitQuery(self):
        """ Submit the user query to the gamestate. Updates the configuration
            of the sign of the clicked intersection based on the input to the
            control dialog
        """
        self.gstate.logwindow.storeInfo("Configured interval:")
        self.gstate.logwindow.storeInfo("Red = "+str(self.rSlider.value)+" Green = "+str(self.gSlider.value))
        tempCell = self.gstate.componentwindow.cell
        tempCell.removeSign()
        tempCell.setTrafficSign(self.rSlider.value,self.gSlider.value)
        self.gstate.componentwindow.repaint()
        self.close()

    def close(self, other=None):
        """ Works just the same as gui.Dialog's close() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = False
        gui.Dialog.close(self)

    def open(self, args0=None, args1=None, args2=None):
        """ Works just the same as gui.Dialog's open() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = True
        gui.Dialog.open(self)

class FinishDialog(gui.Dialog):
    """ The dialog window that is triggered when the game ends. Extends pgu's gui.Dialog """
    
    def __init__(self,gstate, winOrLose, **params):
        """ Initiates the dialog by a fixed title label and dimensions. Sets up the widgets in the FinishDialog.
            Displayed widget is based on the status of the game end (win or lose)
            Parameters: Associated GameState and other parameters applicable for gui.Dialog """
        self.title = None
        width = 400
        height = 200
        self.gstate = gstate

        # Table containing the contents (buttons) of the dialog window
        message = gui.Table(width=width,height=height)
        if winOrLose == const.win:
            self.winSetup(message)
        else:
            self.loseSetup(message)
        gui.Dialog.__init__(self,self.title,message)

    def winSetup(self,message):
        """ Sets up the window indicating that the user has won the game """
        self.title = gui.Label("You Win!")
        msg = gui.Label("Good Job!", font=const.fontBig);
        stat = gui.Label("You managed to finish with only " +
                         str(const.timeoutCount)+ " time out cars")
        selectlevel_button = gui.Button("Select New Level", width=60, height=25)
        restart_button = gui.Button("Play again", width=60, height=25)
        selectlevel_button.connect(gui.CLICK, self.close)
        selectlevel_button.connect(gui.CLICK, const.toSelectLevel, self.gstate)
        restart_button.connect(gui.CLICK, self.close)
        restart_button.connect(gui.CLICK, const.toGameState, self.gstate, self.gstate.level)
        message.tr()
        message.td(msg, colspan=2)
        message.tr()
        message.td(stat, colspan=2)
        message.tr()
        message.td(selectlevel_button)
        message.td(restart_button)

    def loseSetup(self,message):
        """ Sets up the window indicating that the user has lost the game """
        self.title = gui.Label("You Lose!")
        msg = gui.Label("Too bad. Try again?", font=const.fontBig);
        stat = gui.Label("You exceeded the allowed number of " +
                         str(const.timeoutAllowed)+ " time out cars")
        restart_button = gui.Button("Retry", width=60, height=25)
        mainmenu_button = gui.Button("To Main Menu", width=60, height=25)
        restart_button.connect(gui.CLICK, const.toGameState, self.gstate, self.gstate.level)
        restart_button.connect(gui.CLICK, self.close)
        mainmenu_button.connect(gui.CLICK, const.toMainMenu, self.gstate)
        mainmenu_button.connect(gui.CLICK, self.close)
        message.tr()
        message.td(msg, colspan=2)
        message.tr()
        message.td(stat, colspan=2)
        message.tr()
        message.td(restart_button)
        message.td(mainmenu_button)

    def close(self, other=None):
        """ Works just the same as gui.Dialog's close() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = False
        gui.Dialog.close(self)

    def open(self, args0=None, args1=None, args2=None):
        """ Works just the same as gui.Dialog's open() method except that it changes the dialogopen field of the gamestate"""
        self.gstate.dialogopen = True
        gui.Dialog.open(self)

        

class GameState (statescreen.StateScreen):
    """ The main class of the module. A state where the traffic set up and simulations occur.
        Extends StateScreen """
    def __init__(self, screen, bgcolor=(0,0,0), level=0,**params):
        """ Initiates the GameState and assigns it to the main screen.
            Parameters: the main screen
                        background color (default black)
                        input file that will determine the map to be played (default lvl0)
                        other parameters applicable for statescreen.StateScreen
        """
        const.map = None
        const.busyStart = []
        const.lowStart = []
        statescreen.StateScreen.__init__(self, screen, bgcolor,**params)
        self.level = level
        self.menudialog = MenuDialog(self)
        self.controldialog = ControlDialog(self)
        self.picksigndialog = PickSignDialog(self)
        self.dialogopen = False              # Indicates if a dialog window is opened
        self.logwindow = logwindow.LogWindow()
        self.fdialog = None
        self.startsim = gui.Button("Start Simulation", width=135, height=20)
        self.startsim.connect(gui.CLICK, self.startImmediateSimulation)
        
        const.setBorder(self.logwindow,1)
        self.componentwindow = componentwindow.ComponentWindow(self)
        const.setBorder(self.componentwindow,1)         
        self.gr = gui.Container(width=screen.get_width()-self.componentwindow.style.width-30,
                                height=screen.get_height()-self.logwindow.style.height-30)
        self.minigr = gui.Container(width=self.componentwindow.style.width-5,
                                    height=self.componentwindow.style.width-5)
        const.setBorder(self.minigr, 5)
        data = self.parseFile()
        self.setTime = data[0]        # total set up time
        self.simTime = data[1]        # total simulation time
        self.timeoutTime = data[2]    # total waiting time for each vehicle before time-out
        const.timeoutAllowed = data[3] # number of time-out cars allowed
        const.timeoutCount = 0
        self.cars = data[4]           # number of vehicles that appear on the map
        self.sizex = data[5]
        self.sizey = data[6]
        self.cellwidth= data[7]
        self.cellheight = data[8]
        cells = data[9]

        # START MAKING CARS!!!
        vehicles = []
        const.map = map.Map(cells,vehicles)
        for i in range(self.cars):
            randomSpeed = randint(500, 800)
            const.map.cars.append(vehicle.Vehicle(const.map.cells, self.timeoutTime, randomSpeed))

        setMin = self.setTime
        setSec = 0

        self.setuptime = gui.Label(str('%(min)02d:%(sec)02d' % {'min':self.setTime,'sec':0}),font=const.statusFont)
        self.simtime = gui.Label(str('%(min)02d:%(sec)02d' % {'min':self.simTime,'sec': 0}),font=const.statusFont)
        
        const.numTimeout = gui.Label(str('%(count)02d/%(tot)02d' % {'count':const.timeoutCount, 'tot':const.timeoutAllowed }),
                                     font=const.statusFont)
        
        self.setuptime.style.width = self.simtime.style.width = const.numTimeout.style.width = 50
        self.logwindow.storeInfo("Sim Traffic - Level "+str(self.level))

        self.cont.add(self.gr,15,15)
        self.guiSetup()

    def parseFile(self):
        """ Parses the inputfile and applies the contents to the game. Also sets up the initial graphics of the map
            Returns a tuple of 10 elements:
                - The setuptime
                - The simulation time
                - The period of time before a car is timedout
                - Number of timeout cars allowed
                - Number of cars
                - Dimensions of the number of cells in the map 
                - Dimensions of each cell (in pixels)
                - The 2D array containing cells that representsthe map
            Throws IOerror if file does not exist
        """
        try:
            fileHandle = open("maps/"+const.STAGES[self.level].text,'r')

            #get setup time
            setTime = float(fileHandle.readline())

            #get the simtime
            simTime = float(fileHandle.readline())

            #get timeout time for each vehicle
            timeoutTime = int(fileHandle.readline())

            #get # of timeout cars allowed
            timeoutAllowed = int(fileHandle.readline())

            #get # of cars
            cars = int(fileHandle.readline())

            #get the size of map
            size = fileHandle.readline().split(',')
            sizex = int(size[0])
            sizey = int(size[1])

            # Dimensions of cells
            cellwidth = self.gr.style.width/(sizex)
            cellheight = self.gr.style.height/(sizey)

            minisize = self.minigr.style.width/sizex+1
            cells=[]
            for i in range(sizey):
                cells.append([])
                for j in range(sizex):
                    cells[i].append(0)
            
	    #cells = [[0.0] * self.sizey] * self.sizex
            fileHandle.readline()
            for i in range(sizey):
                line = fileHandle.readline()
                #print ''
                for j in range(sizex):
                    # lb is a label which is the GUI representation of each cell
                    lb = gui.Label("",background=(0,0,0))
                    minilb = gui.Label("",background=(0,0,0))
                    if line[j] == 'x' : #building
                        cells[j][i] = None
                        minilb.style.background = lb.style.background = const.noneColor
                       	#print '0',
                    elif line[j] == 'i' : #intersection block
                        lb = cell.RoadCell(j,i,False, False, False, True, background = (0,0,0))
                        minilb.style.background = lb.style.background =const.blockColor
                        cells[j][i] = lb
                       	#print 'i',
                    elif line[j] == 'H' : # busy (high traffic)
                        lb = cell.RoadCell(j,i,True,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.busyColor
                        cells[j][i] = lb
                        #print '1',
                    elif line[j] == 'L' : # not busy (low traffic)
                        lb = cell.RoadCell(j,i,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.notBusyColor
                        cells[j][i] = lb
                       	#print '1',
                    elif line[j] == 'S' : # start cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True,True,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.busyColor
                        const.busyStart.append((j, i))
                        cells[j][i] = lb
                        #print '1',
                    elif line[j] == 's' : # start cell and not busy (lwo traffic)
                        lb = cell.RoadCell(j,i,False,True,background = (0,0,0))
                        minilb.style.background = lb.style.background = const.notBusyColor
                        const.lowStart.append((j, i))
                        cells[j][i] = lb
                        #print '1',
                    elif line[j] == 'E' : # end cell and busy (high traffic)
                        lb = cell.RoadCell(j,i,True, False, True,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.busyColor
                        cells[j][i] = lb
                        #print '1',
                    elif line[j] == 'e' : # end cell and not busy (low traffic)
                        lb = cell.RoadCell(j,i,False,False,True,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.notBusyColor
                        cells[j][i] = lb
                        #print '1',
                    elif line[j] == '+' : #intersection cell busy
                        lb = cell.IntersectionCell(j,i,True,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.iBusyColor
                        cells[j][i] = lb
                        lb.connect(gui.CLICK,self.respond, lb)
                        #print 'o',
		    elif line[j] == '#' : #intersection cell not busy
                        lb = cell.IntersectionCell(j,i,False,background = (0,0,0))
                        minilb.style.background = lb.style.background =const.iNotBusyColor
                        cells[j][i] = lb
			lb.connect(gui.CLICK,self.respond, lb)
			#print 'o',
		    lb.style.width = cellwidth
                    lb.style.height = cellheight
                    minilb.style.width = minilb.style.height = minisize
		    self.gr.add(lb,cellwidth*j,cellheight*i)
		    self.minigr.add(minilb,minisize*j, minisize*i)
		    
	    return setTime,simTime,timeoutTime,timeoutAllowed,cars,sizex,sizey,cellwidth,cellheight,cells

        except IOError:
               print "The file does not exist, exiting"
        
        
    def run(self,widget=None,screen=None):
        """ Overrides run method of gui.App so that it will support the animations and some counter widgets in the GameState """
        self.init(widget,screen)
        
        initialTime = pygame.time.get_ticks();
        self.timer = (pygame.time.get_ticks()-initialTime)/1000;
        self.time_counter = 0
        self.set_time_minute = int(self.setTime)
        self.set_time_second = (self.setTime-self.set_time_minute)*60+1
        total_setTime_secs = self.set_time_minute*60+self.set_time_second
        self.sim_time_minute = int(self.simTime)
        self.sim_time_second = (self.simTime-self.sim_time_minute)*60+1
        self.setTimeFinish = False
        self.simTimeFinish = False
        total_simTime_secs = self.sim_time_minute*60+self.sim_time_second
        self.setuptime.value = str('%(min)02d:%(sec)02d' % {'min':self.set_time_minute,'sec':self.set_time_second-1})
        self.simtime.value = str('%(min)02d:%(sec)02d' % {'min':self.sim_time_minute,'sec':self.sim_time_second-1})
        while not self._quit:
            """ Update the maps and vehicles """
            if not self.dialogopen:
                for cellrow in const.map.cells:
                    for cell in cellrow:
                        if (cell):
                            cell.repaint()
            """ Setup time running """
            if self.timer <= total_setTime_secs and (not self.setTimeFinish):
                  self.setTimeCountdown(self.setuptime,initialTime)
            elif (not self.setTimeFinish):
                  self.setTimeFinish = True
                  self.startsim.disabled = True
                  initialTime = pygame.time.get_ticks();
                  self.timer = (pygame.time.get_ticks() - initialTime)/1000;
                  self.time_counter = 0

            """ Simulation time running """
            if self.setTimeFinish and not self.simTimeFinish:
                  const.map.updateMap()
                  if self.timer <= total_simTime_secs and (not self.simTimeFinish):
                        self.simTimeCountdown(self.simtime, initialTime)
                  else :
                       self.simTimeFinish = True

            if not self.fdialog:
                if (const.timeoutCount > const.timeoutAllowed):
                    self.simTimeFinish = True
                    self.fdialog = FinishDialog(self,const.lose)
                elif (self.simTimeFinish):
                    self.fdialog = FinishDialog(self,const.win)
                    const.updateData(self.level)
            elif not self.dialogopen:
                self.fdialog.open()
            self.loop()
            pygame.time.wait(10)
    
    def setTimeCountdown(self,label,initialTime):
         """ Count down for setup time """
         if (self.timer- self.time_counter >= 1):
                 self.time_counter += 1
                 self.set_time_second -= 1
                 label.value = str('%(min)02d:%(sec)02d' % {'min':self.set_time_minute,'sec':self.set_time_second})
                 label.repaint()

                 if self.set_time_second == 0:
                    self.set_time_minute -= 1
                    self.set_time_second = 60

         self.timer = (pygame.time.get_ticks()-initialTime)/1000;

    def simTimeCountdown(self,label,initialTime):
         """ Count down for simulation time """
         if (self.timer- self.time_counter >= 1):
                self.time_counter += 1
                self.sim_time_second -= 1
                label.value = str('%(min)02d:%(sec)02d' % {'min':self.sim_time_minute,'sec':self.sim_time_second})
                label.repaint()
                if self.sim_time_second == 0:

                    self.sim_time_minute -= 1
                    self.sim_time_second = 60

         self.timer = (pygame.time.get_ticks()-initialTime)/1000;
    
    def guiSetup(self):
        """ Setup the interfaces and widgets of the GameState """
        #Right panel
        rpanel = gui.Table(width=self.componentwindow.style.width+20,
                           height=self.screen.get_height(),
                           background=(165,165,165),
                           valign=-1)
        rpanel.tr()
        rpanel.td(self.minigr)
        rpanel.tr()
        rpanel.td(self.componentwindow)
        rpanel.style.border_left=2

        #Bottom panel
        bpanel = gui.Container(width = self.screen.get_width()-rpanel.style.width,
                               height=self.logwindow.style.height+20,
                               background=(225,225,225))

        stat_table = gui.Table()
        stat_table.tr()
        stat_table.td(gui.Label("Time left before simulation starts ", font=const.labelFont),width=210, align=-1)
        stat_table.td(self.setuptime, align=-1)
        stat_table.tr()
        stat_table.td(gui.Label("Time left before simulation ends", font=const.labelFont),width=210,align=-1)
        stat_table.td(self.simtime, align=-1)
        stat_table.tr()
        stat_table.td(gui.Label("Total time out cars", font=const.labelFont),width=210, align=-1)
        stat_table.td(const.numTimeout, align=-1)

        bpanel.add(self.logwindow, bpanel.style.width-self.logwindow.style.width-10, 10)
        bpanel.add(stat_table,5,10)
        bpanel.style.border_top=2

        menuButton = gui.Button("Menu")
        menuButton.connect(gui.CLICK,self.menudialog.open)
        
        #Adds the panels to the container
        self.cont.add(rpanel,self.screen.get_width()-rpanel.style.width,0)
        self.cont.add(bpanel,0,self.screen.get_height()-bpanel.style.height)
        self.cont.add(menuButton,0,0)
        self.cont.add(self.startsim, self.screen.get_width()-rpanel.style.width-150,
                      self.screen.get_height()-bpanel.style.height-15)

    def respond(self, cell):
        """ Have an intersection cells to open PickSignDialog when clicked """
        self.componentwindow.storeSignInfo(cell)
        if cell.sign == None:
            #print "PICK SIGN DIALOG - SET CELL"
            self.picksigndialog.cell = self.componentwindow.cell
            #print "PICK SIGN DIALOG - CONNECT BUTTONS"
            self.picksigndialog.connectButtons()
            #print "PICK SIGN DIALOG - OPEN"
            self.picksigndialog.open()

    def removeSign(self):
        """ Removes signs from the selected intersection, and other intersections associated with it and its sign """
        self.componentwindow.cell.removeSign()
        self.logwindow.storeInfo("Removed a traffic sign")

    def startImmediateSimulation(self):
        """ Starts simulation when the start simulation button is clicked (does not wait for time) """
        self.timer = 10000000;                # Set the time to a value that is guaranteed to exceed the simtime
        
