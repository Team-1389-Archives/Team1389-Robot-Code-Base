package com.team1389.base;

import java.util.AbstractList;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AutonomousBase {
	AutonMode selectedAuton;
	Command runningCommand;

	public AutonomousBase() {
		runningCommand = null;
		setSelectedAuton(getAutonModes().get(0));
	}
	public AutonMode getSelectedAuton(){
		return selectedAuton;
	}
	public AutonMode setSelectedAuton(AutonMode selectedAuton){
		return this.selectedAuton = selectedAuton;
	}
	public abstract AbstractList<AutonMode> getAutonModes();
	public void autonStart(){
		runningCommand = selectedAuton.getCommand();
	}
	public void autonEnd(){
		if (selectedAuton.shouldCancelCommandsOnAutonEnd()){
			runningCommand.cancel();
		}
	}
}
