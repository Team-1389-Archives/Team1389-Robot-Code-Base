package com.team1389.robot;

import com.team1389.base.AutonomousBase;
import com.team1389.base.Team1389RobotBase;
import com.team1389.base.TeleopBase;

public class Robot extends Team1389RobotBase{

	@Override
	public TeleopBase getTeleopBase() {
		return new TeleopMain();
	}

	@Override
	public AutonomousBase getAutonomousBase() {
		return new AutonomousMain();
	}

	@Override
	public void setup() {
		Inputs.setup();
		System.out.println("this is running");
	}

}
