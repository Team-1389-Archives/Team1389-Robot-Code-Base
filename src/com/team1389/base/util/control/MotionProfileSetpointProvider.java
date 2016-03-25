package com.team1389.base.util.control;

import com.team1389.base.util.Timer;

public class MotionProfileSetpointProvider implements SetpointProvider{
	
	Timer timer;
	MotionProfile profile;
	
	public MotionProfileSetpointProvider(MotionProfile profile) {
		timer = new Timer();
		this.profile = profile;
	}

	@Override
	public double getSetpoint() {
		return profile.getPosition(timer.get());
	}
	
	@Override
	public void init() {
		timer.zero();
	}
	
	@Override
	public boolean isFinished() {
		return timer.get() >= Math.max(profile.getDuration(), 0.0);//hack
	}

}
