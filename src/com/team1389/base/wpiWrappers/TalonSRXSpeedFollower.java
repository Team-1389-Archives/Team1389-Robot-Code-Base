package com.team1389.base.wpiWrappers;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class TalonSRXSpeedFollower implements FollowerMotor{
	
	CANTalon talon;
	
	public TalonSRXSpeedFollower(CANTalon talon, int idToFollow) {
		this.talon = talon;
		this.talon.changeControlMode(TalonControlMode.Follower);
		this.talon.set(idToFollow);
	}
}