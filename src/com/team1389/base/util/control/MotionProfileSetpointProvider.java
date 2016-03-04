package com.team1389.base.util.control;

import com.team1389.base.util.Timer;
import com.team1389.base.util.control.PositionControllerRampCommand.SetpointProvider;

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

}
