package com.team1389.base.webserver;

import java.util.ArrayList;
import java.util.List;

import com.team1389.base.AutonMode;
import com.team1389.base.Global;

public class AutonModesServlet extends JSONGetServlet<List<AutonModesServlet.AutonInfo>>{
	static class AutonInfo{
		AutonInfo(String autonName){
			this.autonName = autonName;
		}
		String autonName;
	}

	@Override
	List<AutonInfo> onGet() {
		ArrayList<AutonInfo> autons = new ArrayList<AutonInfo>();
		
		List<AutonMode> modes = Global.robot.getAutonModes();
		
		for (AutonMode mode: modes){
			AutonInfo info = new AutonInfo(mode.getName());
			autons.add(info);
		}
		
		System.out.println("there are " + modes.size() + " modes");
		
		return autons;
	}
}
