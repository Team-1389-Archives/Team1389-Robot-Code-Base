package com.team1389.robot.commands;

import com.team1389.base.Global;
import com.team1389.robot.Inputs;
import com.team1389.robot.Subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopElevator extends Command{
	public TeleopElevator() {
		requires(Subsystems.elevator);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		Subsystems.elevator.set(Inputs.driveStick.getRawAxis(5));
	}

	@Override
	protected boolean isFinished() {
		return !Global.robotBase.isOperatorControl();
	}

	@Override
	protected void end() {
		Subsystems.elevator.set(0);
	}

	@Override
	protected void interrupted() {
		Subsystems.elevator.set(0);		
	}

}
