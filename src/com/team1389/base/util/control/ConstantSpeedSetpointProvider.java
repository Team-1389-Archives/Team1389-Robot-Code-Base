package com.team1389.base.util.control;

import com.team1389.base.util.Timer;

public class ConstantSpeedSetpointProvider implements SetpointProvider{
	
	Timer timer;
	double speed;
	
	public ConstantSpeedSetpointProvider(double speed) {
		timer = new Timer();
		this.speed = speed;
	}
	
	@Override
	public void init() {
		timer.zero();
	}

	@Override
	public double getSetpoint() {
		return timer.get() * speed;
	}

}
