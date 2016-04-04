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
	}

	@Override
	public boolean execute() {
		controller.setSpeed(speed);
		System.out.println("speed: " + speed);
		return false;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	public TalonSRXSpeedHardware getController(){
		return controller;
	}

}
