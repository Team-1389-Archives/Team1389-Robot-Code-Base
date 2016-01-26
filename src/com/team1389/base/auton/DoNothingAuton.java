package com.team1389.base.auton;

import org.strongback.command.Command;

import com.team1389.base.IO;

public class DoNothingAuton implements AutonMode{

	@Override
	public Command getCommand(IO io) {
		return Command.create(() -> {});
	}

	@Override
	public String getName() {
		return "nothing";
	}

}
