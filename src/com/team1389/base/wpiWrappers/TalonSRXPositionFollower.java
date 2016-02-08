package com.team1389.base.wpiWrappers;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class TalonSRXPositionFollower implements FollowerMotor{
	
	CANTalon talon;
	
	public TalonSRXPositionFollower(CANTalon talon, int idToFollow, boolean isInverted) {
		this.talon = talon;
		this.talon.changeControlMode(TalonControlMode.Follower);
		this.talon.set(idToFollow);
		this.talon.setInverted(isInverted);
	}
}
