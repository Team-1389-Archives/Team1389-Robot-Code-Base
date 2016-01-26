package com.team1389.base;

import org.strongback.Strongback;
import org.strongback.command.Command;

public abstract class TeleopBase<IOLayoutType extends IO>{
	Command command;
	public void start(IOLayoutType io){
		setupTeleop(io);
		command = provideCommand(io);
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
	public abstract Command provideCommand(IOLayoutType io);
	public abstract void setupTeleop(IOLayoutType io);
}