package com.team1389.base;

import java.util.AbstractList;

public abstract class AutonomousBase {
	AutonMode selectedAuton;

	public AutonomousBase() {
		selectedAuton = getAutonModes().get(0);
	}
	public AutonMode getSelectedAuton(){
		return selectedAuton;
	}
	public AutonMode setSelectedAuton(AutonMode selectedAuton){
		return this.selectedAuton = selectedAuton;
	}
	public abstract AbstractList<AutonMode> getAutonModes();
}
