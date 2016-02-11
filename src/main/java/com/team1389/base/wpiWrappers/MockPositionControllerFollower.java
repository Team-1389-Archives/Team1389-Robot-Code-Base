package com.team1389.base.wpiWrappers;

public class MockPositionControllerFollower implements FollowerMotor{
	PositionController toFollow;
	
	public MockPositionControllerFollower(PositionController toFollow) {
		this.toFollow = toFollow;
	}
}
