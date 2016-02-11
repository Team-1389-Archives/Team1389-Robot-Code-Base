package com.team1389.base.util.control;

public abstract class MotionProfile {
	public abstract double getDuration();
	public abstract double provideVelocity(double time);
	public abstract double provideAcceleration(double time);
	
	public double getVelocity(double time){
		double power = provideVelocity(time);
		if (time > getDuration()){
			System.out.println("warning: in MotionProfile, time is called greater than duration");
			return 0;
		} else {
			return power;
		}
	}
	
	public double getAcceleration(double time){
		double power = provideVelocity(time);
		if (time > getDuration()){
			System.out.println("warning: in MotionProfile, time is called greater than duration");
			return 0;
		} else {
			return power;
		}
	}
	
}
