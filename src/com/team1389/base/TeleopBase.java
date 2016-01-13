package com.team1389.base;

import edu.wpi.first.wpilibj.command.Command;

public abstract class TeleopBase {
	Command command;

	TeleopBase(){
		command = provideCommand();
	}
	public void start(){
		command.start();
	}
	public void disable() {
		command.cancel();
	}
	public abstract Command provideCommand();
}
