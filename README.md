#Overview
This project contains base code for team1389 robot projects. The code is general and useful for all FRC robot programs.

To make a project using this base, copy the project, delete git from it, and reinitialize git as a new repository.

#Features
- basic command based framework setup, with a place to put teleop commands and a way to create auton modes
- webserver that lets user configure auton mode and constants from visual interface in browser
- automatically include libraries in lib folder

#Planned Features
- utiliy commands like the DoTimes command
- code for common robot setups like tank drive

#How Stuff Works

###External libraries
The jars for external (third party) libraries are located in the 'lib' folder.

To add a new library:
- get the required jars (more info soon on how to do that) and put them in the lib folder
- right click on the project, select properties, click Java Build Path, select the Libraries tab, and add the jars from the lib folder

To see how the libraries are used, look at the documentation in the build.xml file

###Webserver
The webserver is an embedded jetty webserver. Its code is in the com.team1389.base.webserver package.
For more information, look at the package-info file in that package.

###Resources
Any files that the program needs to access can be placed in the Resources folder, which is copied to the roborio when the code is deployed.