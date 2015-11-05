package com.team1389.robot;

import com.team1389.base.RobotCode;
import com.team1389.base.Simulator;
import com.team1389.base.TeleopBase;
import com.team1389.base.auton.AutonomousBase;

/**
 * This class defines where the teleop and auton bases are.
 * The code in this file wony usually have to be changed.
 */
public class Robot implements RobotCode{

	public TeleopBase getTeleopBase() {
		return new TeleopMain();
	}

	public AutonomousBase getAutonomousBase() {
		return new AutonomousMain();
	}

	@Override
	public void setup() {
		Inputs.setup();
		Subsystems.setup();
		Globals.setup();
		System.out.println("Robot is initialized");
	}

	//for running a lightweight simulation
	public static void main(String[] args){
		Simulator.simulate(new Robot());
	}
}
