package com.team1389.base;

import com.team1389.base.webserver.WebServer;

public class Simulator{
	/*
	 * simple simulator for the purpose of testing the webserver
	 */
	public static void simulate(RobotCode robotCode, Mode mode, WebServer server){
    	//configure Strongback
		Configuration.configureStrongback();

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
			robotCode.getTestBase().start();
			break;
		default:
			break;
		}
		
	}
}
