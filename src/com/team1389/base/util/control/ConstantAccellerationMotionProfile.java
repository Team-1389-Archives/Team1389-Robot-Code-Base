package com.team1389.base.util.control;

public class ConstantAccellerationMotionProfile extends MotionProfile{
	private static interface DoubleFunction{
		double run(double in);
	}
	
	DoubleFunction velocity;
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
		}
	}

	@Override
	public double getDuration() {
		return duration;
	}

	@Override
	public double providePower(double time) {
		return velocity.run(time);
	}
	
	public static void main(String[] args){
		MotionProfile profile = 
				new ConstantAccellerationMotionProfile(1, 10, 1);
		
		System.out.println("duration: " + profile.getDuration());
		
		System.out.println(profile.getPower(0));
		System.out.println(profile.getPower(1));
		System.out.println(profile.getPower(2));
		System.out.println(profile.getPower(3));
		System.out.println(profile.getPower(4));
		System.out.println(profile.getPower(5));
		System.out.println(profile.getPower(6));
		System.out.println(profile.getPower(7));
		System.out.println(profile.getPower(8));
		System.out.println(profile.getPower(9));
	}

}
