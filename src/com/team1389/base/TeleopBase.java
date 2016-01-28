package com.team1389.base;

import org.strongback.Strongback;
import org.strongback.command.Command;

public abstract class TeleopBase{
	Command command;
	public void start(){
		setupTeleop();
		command = provideCommand();
		Strongback.restart();
		if (command != null){
			Strongback.submit(command);
		} else {
			System.out.println("teleop command is null");
		}
	}
	public void disable() {
		Strongback.killAllCommands();
	}
	public abstract Command provideCommand();
	public abstract void setupTeleop();
}