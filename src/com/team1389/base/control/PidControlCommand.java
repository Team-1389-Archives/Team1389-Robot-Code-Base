package com.team1389.base.control;

import org.strongback.command.Command;

import edu.wpi.first.wpilibj.PIDController;

public class PidControlCommand extends Command{
	
	PIDController controller;
	
	public PidControlCommand(PIDController controller) {
		this.controller = controller;
	}
	
	public PIDController getController(){
		return controller;
	}
	
	@Override
	public void initialize() {
		controller.enable();
	}

	@Override
	public boolean execute() {
		return false;
	}
	
	@Override
	public void end() {
		controller.disable();
	}
	
	@Override
	public void interrupted() {
		end();
	}

}
