#Overview
This project contains base code for team1389 robot projects. The code is general and useful for all FRC robot programs.

To make a project using this base, copy the project, delete git from it, and reinitialize git as a new repository.

#Features
- basic command based framework setup, with a place to put teleop commands and a way to create auton modes

#Planned Features
- webserver that lets user configure auton mode and constants from visual interface in browser
- utiliy commands like the DoTimes command

#How Stuff Works

###External libraries
The jars for external (third party) libraries are located in the 'lib' folder.

To add a new library:
- get the required jars (more info soon on how to do that) and put them in the lib folder
- right click on the project, select properties, click Java Build Path, select the Libraries tab, and add the jars from the lib folder

###Webserver
The webserver is an embedded jetty webserver. More when this exists.