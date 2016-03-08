package com.team1389.base.util.control;

import com.team1389.base.util.control.SetpointProvider;

public class SetableSetpointProvider implements SetpointProvider{
	double setpoint;
	
	public SetableSetpointProvider() {
		this.setpoint = 0.0;
	}

	@Override
	public double getSetpoint() {
		return setpoint;
	}
	
	public void setSetpoint(double setpoint){
		this.setpoint = setpoint;
	}

}
