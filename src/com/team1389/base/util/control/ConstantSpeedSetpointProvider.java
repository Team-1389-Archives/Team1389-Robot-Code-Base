package com.team1389.base.util.control;

import com.team1389.base.util.Timer;

public class ConstantSpeedSetpointProvider implements SetpointProvider{
	
	Timer timer;
	double speed;
	double pos;
	
	public ConstantSpeedSetpointProvider(double speed) {
		timer = new Timer();
		this.speed = speed;
	}
	
	@Override
	public void init() {
		timer.zero();
		pos = 0.0;
	}

	@Override
	public double getSetpoint() {
		double change = timer.get() * speed;
		pos += change;
		timer.zero();
		return pos;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}

}
