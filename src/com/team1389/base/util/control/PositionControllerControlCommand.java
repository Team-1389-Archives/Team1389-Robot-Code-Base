package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.PositionController;

public class PositionControllerControlCommand extends Command{
	SetpointProvider setpointProvider;
	PositionController controller;
	PIDConstants pid;
	
	public PositionControllerControlCommand(SetpointProvider setpointProvider, PositionController controller, PIDConstants pid) {
		this.setpointProvider = setpointProvider;
		this.controller = controller;
		this.pid = pid;
	}
	
	public PositionControllerControlCommand(SetpointProvider setpointProvider, PositionController controller) {
		this(setpointProvider, controller, null);
	}
	
	@Override
	public void initialize() {
		if (pid != null){
			controller.setPID(pid);
		}
		setpointProvider.init();
		if (setpointProvider.shouldResetPosition()){
			controller.setCurrentPositionAs(0);
		}
	}

	@Override
	public boolean execute() {
		double setpoint = setpointProvider.getSetpoint();
		controller.setPosition(setpoint);
		return setpointProvider.isFinished();
	}
	
	@Override
	public void end() {
		controller.disable();
	}

}
