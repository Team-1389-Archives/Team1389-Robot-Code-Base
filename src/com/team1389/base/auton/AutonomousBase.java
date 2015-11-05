package com.team1389.base.auton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AutonomousBase {
	Map<String, AutonMode> modes;

	String selectedAutonName;
	AutonMode runningAutonMode;
	Command runningCommand;

	public AutonomousBase() {
		System.out.println("constructing");
		modes = new HashMap<String, AutonMode>();
		List<AutonMode> modesList = provideAutonModes();
		modesList.add(new DoNothingAuton());

		for (AutonMode mode : modesList){
			modes.put(mode.getName(), mode);
		}
		runningCommand = null;
		setSelectedAuton("nothing");
	}
	public String getSelectedAuton(){
		return selectedAutonName;
	}
	public void setSelectedAuton(String selectedAuton){
		this.selectedAutonName = selectedAuton;
		System.out.println("setting, now == =" + this.selectedAutonName);
	}
	public List<AutonMode> getAutonModes(){
		return new ArrayList<AutonMode>(modes.values());
	}
	protected abstract List<AutonMode> provideAutonModes();
	public void autonStart(){
		runningAutonMode = modes.get(selectedAutonName);
		runningCommand = runningAutonMode.getCommand();
		runningCommand.start();
	}
	public void autonEnd(){
		if (runningAutonMode.shouldCancelCommandsOnAutonEnd()){
			runningCommand.cancel();
		}
	}
}
