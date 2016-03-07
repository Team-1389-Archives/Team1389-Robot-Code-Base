package com.team1389.base.util.control;

import com.team1389.base.util.Timer;

public class RampingSetpointProvider implements SetpointProvider{
	
	Timer timer;
	double min, max, maxSpeed, start;
	double setpoint, goalPoint;
	
	public RampingSetpointProvider(double min, double max, double maxSpeed, double start) {
		timer = new Timer();
		this.min = min;
		this.max = max;
		this.maxSpeed = maxSpeed;
		this.start = start;
	}
	
	@Override
	public void init() {
		timer.zero();
		setpoint = start;
		goalPoint = start;
	}

	@Override
	public double getSetpoint() {

		setpoint = getNextSetpoint(goalPoint, timer.get());
		timer.zero();
		
		System.out.println("setpoint:" + setpoint + " goalpoint" + goalPoint);
		return setpoint;
	}

	private double getNextSetpoint(double goalPoint, double timeDiff){
		double maxChangeInSetpoint = maxSpeed * timeDiff;
		double newSetpoint;
		
		if (Math.abs(goalPoint - setpoint) < maxChangeInSetpoint){
			newSetpoint = goalPoint;
		} else if (goalPoint > setpoint){
			newSetpoint = setpoint + maxChangeInSetpoint;
		} else {
			newSetpoint = setpoint - maxChangeInSetpoint;
		}
		
		if (newSetpoint > max){
			newSetpoint = max;
		} else if (newSetpoint < min){
			newSetpoint = min;
		}
		
		return newSetpoint;
	}

	public void setGoalPoint(double goal){
		goalPoint = goal;
	}
}
