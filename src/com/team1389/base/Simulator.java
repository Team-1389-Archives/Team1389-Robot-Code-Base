package com.team1389.base;

import com.team1389.base.webserver.WebServer;

public class Simulator {
	/*
	 * simple simulator for the purpose of testing the webserver
	 */
	public static void simulate(RobotCode robotCode){
		//setup globals
		BaseGlobals.robotCode = new RobotCodeHolder(robotCode);
		
		WebServer server = new WebServer();
		server.start();
	}
}
