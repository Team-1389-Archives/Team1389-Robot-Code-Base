package com.team1389.base.util.control;

public class InvertMotionProfile extends MotionProfile{
	
	MotionProfile profile;

	public InvertMotionProfile(MotionProfile profile) {
		this.profile = profile;
	}

	@Override
	public double getDuration() {
		return profile.getDuration();
	}

	@Override
	public double providePosition(double time) {
		return -profile.providePosition(time);
	}

	@Override
	public double provideVelocity(double time) {
		return -profile.provideVelocity(time);
	}

	@Override
	public double provideAcceleration(double time) {
		return -profile.provideAcceleration(time);
	}

}
