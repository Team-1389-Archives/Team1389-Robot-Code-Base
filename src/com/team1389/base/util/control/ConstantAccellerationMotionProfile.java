package com.team1389.base.util.control;

public class ConstantAccellerationMotionProfile extends MotionProfile{
	private static interface DoubleFunction{
		double run(double in);
	}
	
	DoubleFunction velocity;
	DoubleFunction acceleration;
	double duration;
	
	
	//black magic: do not touch
	public ConstantAccellerationMotionProfile(double distance, double maxVel, double maxAcc) {
		double rampDist = maxVel * maxVel / (2 * maxAcc);
		
		if (2 * rampDist <= distance){//trapezoidal motion profile function
			double rampTime = maxVel / maxAcc;
			duration = (distance / maxVel) + (maxVel / maxAcc);
			double rampDownStartTime = duration - rampTime;
			velocity = (t) -> {
				if (t <= rampTime){
					return t * maxAcc;
				} else if (t <= rampDownStartTime){
					return maxVel;
				} else {
					return maxVel - (maxAcc * (t - rampDownStartTime));
				}
			};
			acceleration = (t) -> {
				if (t <= rampTime){
					return maxAcc;
				} else if (t <= rampDownStartTime){
					return 0;
				} else {
					return -maxAcc;
				}
			};
		} else {//triangular motion profile function
			duration = 2 * Math.sqrt(distance / maxAcc);
			double maxActualVel = (maxAcc * duration) / 2;
			double halfTime = duration / 2;
			velocity = (t) -> {
				if (t <= halfTime){
					return t * maxAcc;
				} else {
					return maxActualVel - (maxAcc * (t - halfTime));
				}
			};
			
			acceleration = (t) -> {
				if (t <= halfTime){
					return maxAcc;
				} else {
					return -maxAcc;
				}
			};
		}
	}

	@Override
	public double getDuration() {
		return duration;
	}

	@Override
	public double provideVelocity(double time) {
		return velocity.run(time);
	}
	
	public static void main(String[] args){
		MotionProfile profile = 
				new ConstantAccellerationMotionProfile(1, 10, 1);
		
		System.out.println("duration: " + profile.getDuration());
		
		System.out.println(profile.getVelocity(0));
		System.out.println(profile.getVelocity(1));
		System.out.println(profile.getVelocity(2));
		System.out.println(profile.getVelocity(3));
		System.out.println(profile.getVelocity(4));
		System.out.println(profile.getVelocity(5));
		System.out.println(profile.getVelocity(6));
		System.out.println(profile.getVelocity(7));
		System.out.println(profile.getVelocity(8));
		System.out.println(profile.getVelocity(9));
	}

	@Override
	public double provideAcceleration(double time) {
		return acceleration.run(time);
	}

}
