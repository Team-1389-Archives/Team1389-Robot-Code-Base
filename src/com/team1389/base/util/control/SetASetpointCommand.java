package com.team1389.base.util.control;

import org.strongback.command.Command;

public class SetASetpointCommand extends Command{
	SetableSetpointProvider setpoint;
	double value;
	
	public SetASetpointCommand(SetableSetpointProvider setpoint, double value) {
		this.setpoint = setpoint;
		this.value = value;
	}
	
	@Override
	public void initialize() {
		setpoint.setSetpoint(value);
	}

	@Override
	public boolean execute() {
		return true;
	}

}
