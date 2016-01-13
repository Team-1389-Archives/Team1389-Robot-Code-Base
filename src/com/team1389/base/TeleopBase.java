package com.team1389.base;

import edu.wpi.first.wpilibj.command.Command;

public abstract class TeleopBase {
	Command command;

	public TeleopBase(){
		command = provideCommand();
	}
	public void start(){
		if (command != null){
			command.start();
		}
	}
	public void disable() {
		if (command != null){
			command.cancel();
		}
	}
	public abstract Command provideCommand();
}
