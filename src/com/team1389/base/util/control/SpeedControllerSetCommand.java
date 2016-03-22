package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.wpiWrappers.TalonSRXSpeedHardware;

public class SpeedControllerSetCommand extends Command{
	
	TalonSRXSpeedHardware controller;
	double speed;
	
	public SpeedControllerSetCommand(TalonSRXSpeedHardware controller, double speed) {
		this.controller = controller;
		this.speed = speed;
	}
	
	@Override
	public void initialize() {
		controller.setSpeed(speed);
	}

	@Override
	public boolean execute() {
		return true;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}

}
