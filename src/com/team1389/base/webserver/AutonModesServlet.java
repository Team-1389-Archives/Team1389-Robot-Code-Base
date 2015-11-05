package com.team1389.base.webserver;

import java.util.ArrayList;
import java.util.List;

import com.team1389.base.BaseGlobals;
import com.team1389.base.auton.AutonMode;

public class AutonModesServlet extends JSONGetServlet<AutonModesServlet.AutonModesList>{
	static class AutonInfo{
		String autonName;
		AutonInfo(String autonName){
			this.autonName = autonName;
		}
	}
	static class AutonModesList{
		List<AutonInfo> autonModes;
		String selectedAuton;
		public AutonModesList(List<AutonInfo> autonModes, String selectedAuton) {
			this.autonModes = autonModes;
			this.selectedAuton = selectedAuton;
		}
	}

	@Override
	AutonModesList onGet() {
		ArrayList<AutonInfo> autons = new ArrayList<AutonInfo>();

		List<AutonMode> modes = BaseGlobals.robotCode.getAutonomousBase().getAutonModes();
		String selectedAuton = BaseGlobals.robotCode.getAutonomousBase().getSelectedAuton();

		for (AutonMode mode: modes){
			AutonInfo info = new AutonInfo(mode.getName());
			autons.add(info);
		}
		
		System.out.println("there are " + modes.size() + " modes");
		
		AutonModesList modesList = new AutonModesList(autons, selectedAuton);

		return modesList;
	}
}
