package com.team1389.base.util.control;

import org.strongback.command.Command;

public class SpeedControlSetCommandSetCommand extends Command{
	
	SpeedControllerSetCommand speed;
	double value;

	public SpeedControlSetCommandSetCommand(SpeedControllerSetCommand speed, double value) {
		this.speed = speed;
		this.value = value;
	}
	
	@Override
	public void initialize() {
		speed.setSpeed(value);
	}

	@Override
	public boolean execute() {
		return true;
	}

}
