



For updated information about Sim Traffic for developers and users, 

visit our website: http://cubist.cs.washington.edu/projects/09sp/cse403/traffic/





Documentations for Users

 

Description of Project


Sim Traffic is a puzzle game that simulates traffic situations. The users will manage the traffic and make sure that the number of cars that have waited for too long and that have crashed does not exceed the preset limit for each level within a certain time period. After the users complete a level, they can then proceed to the next level.


Unlike most of the traffic simulation software games out there, Sim Traffic is more similar to real-world traffic situations, and at the same time is not as complicated as the real traffic simulation software that the traffic planners use. The alternative software such as Traffic Control game or the real traffic simulation software are either too simple or too complicated to be interesting or too expensive to purchase.

Users intend to use it because the software concept is unprecedented. Sim Traffic can be a brain twister for the users because traffic management is actually intellectually challenging. Since each level is more difficult than the previous one, users will get a sense of achievement every time they accomplish a level.


 


How to Obtain and Install the Software and Run the Software


1. Go to http://cubist.cs.washington.edu/projects/09sp/cse403/traffic/

2. Click download

3. Click the latest version to download the .tar file

4. Untar the file

5. Go to src directory on the terminal and type "python main.py"

 



High-level Description of the User View of Your Software


Sim Traffic is divided into levels of increasing difficulty. In each level, player needs to survive for a certain amount of time without having too many timed-out cars (cars that have waited too long or have collided with other car(s)). In other words, player has to keep traffic in control – not jammed around intersections. The time limit and the number of timed-out cars allowed vary among different levels. Typically, the higher the level, shorter time limit is given and less timed-out cars are allowed. Moreover, the map’s (road and traffic layout) complexity also increases as the level increases. Higher levels will involve larger maps, more vehicles, and more intersections.

 

In order to reach the objective of each level, players are able to place traffic signs on road intersections. There are two choices of traffic signs: stop sign and traffic light. While stop signs are just static traffic sign, traffic lights can be configured. Players can change the timers of the red and green light of every pair of traffic light. The challenge here is for player to decide which traffic signs to place on each intersection. Vehicles will respond to the stop signs by taking turn in order of their arrival at the intersection in proceeding through the intersection. On the other hand, traffic light’s red light and green light timers will be set by the user. Like in real-life traffic simulation, during red light, cars will stop and cars will have to yield if they want to turn right; during green light, cars that want to go straight or turn right will run like usual and if they will have to yield to turn left.


The major challenge of this game is analysis. In order to proceed to the next level, users will have to analyze each road's condition, whether it has low or high traffic, in a limited time. Based on their analysis, they will have to decide where to place the traffic signs; users also have to decide the red light and green light timers for each traffic light. For example, it is better to use stop signs instead of traffic lights in order to manage an intersection that has low traffic on its surrounding roads. Furthermore, after the simulation starts, users can do more analysis and make changes on the traffic signs, if desired.




How to Play the Game


Users will first be directed to the main page. To proceed to the level selection, users select the "Start The Game" button. A level selection page will then be shown for users to choose a desired level they want to play by viewing the map layout and clicking on "Start Game." The highest level that users have completed will be saved and the available levels the user can play will be from level 0 up to one level higher than the highest accomplished level. At this point, users will be able to manage the traffic signs for intersections; there will be various time limits for each level where users can analyze and set traffic signs before the simulation starts. However, users still have the chance to change them after the simulation starts. In addition, users can also start the simulation immediately as soon as they are ready without having to wait until the countdown reaches 0.


To add traffic signs, users have to click on an intersection and choose the desired traffic sign (traffic light or stop sign) to be placed on from the component pop-up window. To set the timer for traffic light, user will have to click on the corresponding traffic light, click on "Configure" button on the traffic sign window and insert the time interval for red and green light in the configuration pop-up window; before changing or setting timers, user can click on a traffic sign and its status will show up on the bottom right corner. User can also replace traffic lights with stop signs and vise versa by clicking on "Replace" button, which then will display a pop-up window of what kind of traffic sign they want to replace it with. Finally, removing traffic signs also can be done by simply click on the "Remove" button and confirm it from configuration pop-up window.


 


How to Report a Bug

Before reporting a bug, users can look up from the bug list to see whether the bug has been reported. This step can reduce the number of duplicate bug reports. Users can report a bug by filling out a form on the website - http://students.washington.edu/apai/simtraffic/bug_report.html. The form has the following fields:

    * game version
    * operating system version
    * problem statement
    * steps to reproduce the bug
    * result
    * expected result.

Once the user has submitted the form, the information will be entered to the database, which is available to view on the webpage - http://cubist.cs.washington.edu/projects/09sp/cse403/traffic/.

