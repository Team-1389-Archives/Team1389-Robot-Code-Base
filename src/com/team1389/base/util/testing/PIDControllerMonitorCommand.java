package com.team1389.base.util.testing;

import edu.wpi.first.wpilibj.PIDController;

public class PIDControllerMonitorCommand extends PeriodicCommand{
	
	PIDController pidController;
	
	public PIDControllerMonitorCommand(PIDController pidController) {
		this.pidController = pidController;
	}

	@Override
	public double secondsBetweenTicks() {
		return 0.5;
	}

	@Override
	public void tick() {
		System.out.println("pid error: " + pidController.getError());
//		System.out.println("pid on target" + pidController.onTarget());
	}

}
