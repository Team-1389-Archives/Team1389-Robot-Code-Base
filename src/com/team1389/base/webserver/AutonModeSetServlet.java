package com.team1389.base.webserver;

import com.team1389.base.RobotCode;

public class AutonModeSetServlet extends JSONPostServlet<AutonModeSetServlet.Mode, AutonModeSetServlet.Status>{
	
	RobotCode robotCode;
	
	public AutonModeSetServlet(RobotCode code) {
		this.robotCode = code;
	}
	
	static class Status{
		boolean success;
		public Status(boolean success){
			this.success = success;
		}
	}
	static class Mode{
		String name;
		public Mode(String name){
			this.name = name;
		}
	}
	@Override
	public Status onPost(Mode fromClient) {
		robotCode.getAutonomousBase().setSelectedAuton(fromClient.name);
		return new Status(true);
	}
	@Override
	public Class<Mode> whatClassIsFromClient() {
		return Mode.class;
	}

}
