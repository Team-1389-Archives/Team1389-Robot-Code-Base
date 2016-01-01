package com.team1389.base.webserver;

import com.team1389.base.BaseGlobals;

public class AutonModeSetServlet extends JSONPostServlet<AutonModeSetServlet.Mode, AutonModeSetServlet.Status>{
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
		BaseGlobals.robotCode.getAutonomousBase().setSelectedAuton(fromClient.name);
		return new Status(true);
	}
	@Override
	public Class<Mode> whatClassIsFromClient() {
		return Mode.class;
	}

}
