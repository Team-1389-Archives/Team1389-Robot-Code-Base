package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.wpiWrappers.TalonSRXPositionHardware;

public class PositionControllerControlCommand extends Command{
	SetpointProvider setpointProvider;
	TalonSRXPositionHardware controller;
	
	public PositionControllerControlCommand(SetpointProvider setpointProvider, TalonSRXPositionHardware controller) {
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
		controller.setPosition(setpointProvider.getSetpoint());
		return setpointProvider.isFinished();
	}
	
	@Override
	public void end() {
		controller.disable();
	}

}
