package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.wpiWrappers.PositionController;

public class PositionControllerControlCommand extends Command{
	SetpointProvider setpointProvider;
	PositionController controller;
	
	public PositionControllerControlCommand(SetpointProvider setpointProvider, PositionController controller) {
		this.setpointProvider = setpointProvider;
		this.controller = controller;
	}
	
	@Override
	public void initialize() {
		setpointProvider.init();
		if (setpointProvider.shouldResetPosition()){
			controller.setCurrentPositionAs(0);
		}
	}

	@Override
	public boolean execute() {
		double setpoint = setpointProvider.getSetpoint();
		System.out.println("setpoint is " + setpoint);
		controller.setPosition(setpoint);
		return setpointProvider.isFinished();
	}
	
	@Override
	public void end() {
		controller.disable();
	}

}
