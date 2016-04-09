package com.team1389.base.util.control;

import java.awt.event.MouseMotionListener;

public class ScalingMotionProfile extends MotionProfile {
	
	MotionProfile profile;
	double mod;
	
	public ScalingMotionProfile(MotionProfile profile, double mod) {
		this.profile = profile;
		this.mod = mod;
	}

	@Override
	public double getDuration() {
		return profile.getDuration();
	}

	@Override
	public double providePosition(double time) {
		return mod * profile.providePosition(time);
	}

	@Override
	public double provideVelocity(double time) {
		return mod * profile.provideVelocity(time);
	}

	@Override
	public double provideAcceleration(double time) {
		return mod* profile.provideAcceleration(time);
	}

}
