----------------------------------------------------------------------------------
SIM TRAFFIC: TRAFFIC SIMULATION GAME 						 /
DOCUMENTATION for Map/Level file (such as lvl0.txt, lvl1.txt, etc)              /
--------------------------------------------------------------------------------
EXTENSION: ".txt". *No other extension is allowed
WHITE SPACE: Extra whitespaces, other than specified ones, are not allowed

FORMATS:
	FIRST Line: 
		SETUP TIME, in MINUTES. *It can also be set as fractions (e.g: 0.5 => 30 seconds).
	SECOND Line: 
		SIMULATION TIME, in MINUTES. *It can also be set as fractions (e.g: 0.5 => 30 seconds).
	THIRD Line: 
		TIME before a car gets TIMED OUT, in MILISECONDS. *Must be integer
	FOURTH Line: 
		NUMBER of ALLOWED TIME OUT CARS. *Must be integer
	FIFTH Line: 
		TOTAL NUMBER of CARS. *Must be integer
	SIXTH Line: 
		DIMENSIONS of the MAP (x and y). (e.g: 20,20 => Map with cells 20(horizontally)x20(vertically) (total 400 cells))
	SEVENTH Line: 
		--- Line break, please leave it blank ---
	EIGHTH Line to EOF: 
		The figure of the MAPS based on ASCII characters. Here is each ASCII's character representation:
			- x represents blank cell (building)
			- S represents high traffic start cell
			- E represents high traffic start cell
			- H represents high traffic normal road cell
			- + represents high traffic intersection cell
			- s represents low traffic start cell
			- e represents low traffic end cell
			- L represents low traffic normal road cell
			- # represents low traffic intersection cell
			- i represents road cell in the middle of intersections

----------------------------------------------------------------------------------
DOCUMENTATION for log.xml/init.xml					         /
---------------------------------------------------------------------------------
This file keeps record on the map files and the maps available for the users based on game completion.
	<game> tag: The root tag
		attributes:
			available: number of maps available for the users based on the game completion.
			total: total number of maps implemented
	<game/stage> tag: The tag representing each level of the game
		attributes:
			level: the difficulty level of the map
		innertext represents the map file associated with the level
