package com.team1389.base;

import com.team1389.base.webserver.WebServer;

public class Simulator{
	/*
	 * simple simulator for the purpose of testing the webserver
	 */
	public static <IOLayoutType extends IO> void simulate(RobotCode<IOLayoutType> robotCode, Mode mode){
		//setup globals
		
//		WebServer server = new WebServer(robotCode);
//		server.start();
		
		switch (mode) {
		case AUTON:
			robotCode.getAutonomousBase().autonStart(robotCode.getIO());
			break;
		case DISABLED:
			break;
		case TELEOP:
			robotCode.getTeleopBase().start(robotCode.getIO());
			break;
		case TEST:
			//TODO: make test mode
			break;
		default:
			break;
		}
		
	}
}
