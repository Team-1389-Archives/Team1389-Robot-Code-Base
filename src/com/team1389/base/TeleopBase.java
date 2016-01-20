package com.team1389.base;

import edu.wpi.first.wpilibj.command.Command;

public abstract class TeleopBase {
	Command command;

	public TeleopBase(){
	}
	public void start(){
		command = provideCommand();
		if (command != null){
			command.start();
		} else {
			System.out.println("teleop command is null");
		}
	}
	public void disable() {
		if (command != null){
			command.cancel();
		}
	}
	public abstract Command provideCommand();
}