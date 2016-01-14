package com.team1389.base.util.control;

public abstract class MotionProfile {
	public abstract double getDuration();
	public abstract double providePower(double time);
	
	public double getPower(double time){
		double power = providePower(time);
		if (time > getDuration()){
			System.out.println("warning: in MotionProfile, time is called greater than duration");
			return 0;
		}
		return power;
	}
	
}
