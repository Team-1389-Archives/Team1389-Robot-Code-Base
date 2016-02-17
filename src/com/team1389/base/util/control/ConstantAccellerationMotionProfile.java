package com.team1389.base.util.control;

public class ConstantAccellerationMotionProfile extends MotionProfile{
	private static interface DoubleFunction{
		double run(double in);
	}
	
	DoubleFunction position;
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
			
			double positionAtRampTime = .5 * maxAcc * rampTime * rampTime;
			double positionAtRampStartTime = positionAtRampTime + maxVel * (rampDownStartTime - rampTime);
			position = (t) -> {
				if (t <= rampTime){
					return .5 * maxAcc * t * t;
				} else if (t <= rampDownStartTime) {
					return positionAtRampTime + maxVel * (t - rampTime);
				} else {
					double timeSince = t - rampDownStartTime;
					return positionAtRampStartTime + timeSince * maxVel - 0.5 * maxAcc * timeSince * timeSince;
				}
			};
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
			double positionAtHalfTime = .5 * maxActualVel * halfTime * halfTime;
			position = (t) -> {
				if (t <= halfTime){
					return .5 * maxActualVel * t * t;
				} else {
					double timeSince = t - halfTime;
					return positionAtHalfTime + timeSince * maxActualVel  - .5 * maxAcc * timeSince * timeSince;
				}
			};
			
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
				new ConstantAccellerationMotionProfile(10, 3, 1);
		
		System.out.println("duration: " + profile.getDuration());
		
		System.out.println(profile.getPosition(0));
		System.out.println(profile.getPosition(1));
		System.out.println(profile.getPosition(2));
		System.out.println(profile.getPosition(3));
		System.out.println(profile.getPosition(4));
		System.out.println(profile.getPosition(5));
		System.out.println(profile.getPosition(6));
		System.out.println(profile.getPosition(7));
		System.out.println(profile.getPosition(8));
		System.out.println(profile.getPosition(9));
		System.out.println(profile.getPosition(10));
	}

	@Override
	public double provideAcceleration(double time) {
		return acceleration.run(time);
	}

	@Override
	public double providePosition(double time) {
		return position.run(time);
	}

}
