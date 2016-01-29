package com.team1389.base;

import com.team1389.base.webserver.WebServer;

public class Simulator{
	/*
	 * simple simulator for the purpose of testing the webserver
	 */
	public static void simulate(RobotCode robotCode, Mode mode){
    	//configure Strongback
		Configuration.configureStrongback();

		
		WebServer server = new WebServer(robotCode);
		server.start();
		
		switch (mode) {
		case AUTON:
			robotCode.getAutonomousBase().autonStart();
			break;
		case DISABLED:
			break;
		case TELEOP:
			robotCode.getTeleopBase().start();
			break;
		case TEST:
			//TODO: make test mode
			break;
		default:
			break;
		}
		
	}
}
