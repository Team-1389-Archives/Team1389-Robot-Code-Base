package com.team1389.base.wpiWrappers;

import org.strongback.components.Motor;

public class MockMotorFollower implements FollowerMotor{
	Motor toFollowe;
	
	public MockMotorFollower(Motor toFollow) {
		this.toFollowe = toFollow;
	}
}
