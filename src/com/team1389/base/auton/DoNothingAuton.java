package com.team1389.base.auton;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DoNothingAuton implements AutonMode{

	@Override
	public Command getCommand() {
		return new CommandGroup();
	}

	@Override
	public String getName() {
		return "nothing";
	}

}
