package com.team1389.base.util.control;

import com.team1389.base.util.control.SetpointProvider;

public class SetableSetpointProvider implements SetpointProvider{
	double setpoint;
	boolean shouldReset = true;
	
	public SetableSetpointProvider() {
		this.setpoint = 0.0;
	}
	
	public SetableSetpointProvider(boolean shouldReset){
		this();
		this.shouldReset = shouldReset;
	}

	@Override
	public double getSetpoint() {
		return setpoint;
	}
	
	public void setSetpoint(double setpoint){
		this.setpoint = setpoint;
	}
	
	@Override
	public boolean shouldResetPosition() {
		return shouldReset;
	}
}
