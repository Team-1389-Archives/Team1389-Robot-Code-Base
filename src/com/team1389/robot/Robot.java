package com.team1389.robot;

import com.team1389.base.AutonomousBase;
import com.team1389.base.RobotCode;
import com.team1389.base.Simulator;
import com.team1389.base.TeleopBase;

/**
 * This class defines where the teleop and auton bases are.
 * The code in this file wony usually have to be changed.
 */
public class Robot implements RobotCode{

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
		Subsystems.setup();
		System.out.println("Robot is initialized");
	}

	//for running a lightweight simulation
	public static void main(String[] args){
		Simulator.simulate(new Robot());
	}
}
