package com.team1389.base;

import com.team1389.base.auton.AutonomousBase;

public class RobotCodeHolder {
	TeleopBase teleopBase;
	AutonomousBase autonomousBase;
	public RobotCodeHolder(RobotCode code){
		this.teleopBase = code.getTeleopBase();
		this.autonomousBase = code.getAutonomousBase();
	}
	public TeleopBase getTeleopBase(){
		return teleopBase;
	}
	public AutonomousBase getAutonomousBase(){
		return autonomousBase;
	}
}
