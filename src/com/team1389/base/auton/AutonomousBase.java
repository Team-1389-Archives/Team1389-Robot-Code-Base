package com.team1389.base.auton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.strongback.Strongback;
import org.strongback.command.Command;

import com.team1389.base.IO;

public abstract class AutonomousBase <IOLayoutType extends IO>{
	Map<String, AutonMode> modes;

	String selectedAutonName;
	AutonMode runningAutonMode;
	Command runningCommand;

	public AutonomousBase(IOLayoutType io) {
		modes = new HashMap<String, AutonMode>();
		List<AutonMode> modesList = provideAutonModes(io);
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
	public void autonStart(IO io){
		runningAutonMode = modes.get(selectedAutonName);
		runningCommand = runningAutonMode.getCommand(io);
		Strongback.restart();
		Strongback.submit(runningCommand);
	}
	public void autonEnd(){
		if (runningAutonMode.shouldCancelCommandsOnAutonEnd()){
			Strongback.killAllCommands();
		}
	}

	protected abstract List<AutonMode> provideAutonModes(IOLayoutType io);
}
