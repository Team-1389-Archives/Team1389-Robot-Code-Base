package com.team1389.robot.commands;

import com.team1389.base.BaseGlobals;
import com.team1389.robot.Inputs;
import com.team1389.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopDrive extends Command{
	public TeleopDrive() {
		requires(Subsystems.driveTrain);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		double leftx = Inputs.driveStick.getLeftX();
		double lefty = Inputs.driveStick.getLeftY();
		
		double leftSpeed = lefty + leftx;
		double rightSpeed = lefty - leftx;
		
		if (Inputs.driveStick.getRawButton(2)){
			leftSpeed *= .5;
			rightSpeed *= .5;
		}
		
		Subsystems.driveTrain.set(leftSpeed, rightSpeed);
	}

	@Override
	protected boolean isFinished() {
		return !BaseGlobals.robotBase.isOperatorControl();
	}

	@Override
	protected void end() {
		Subsystems.driveTrain.set(0,0);
	}

	@Override
	protected void interrupted() {
		Subsystems.driveTrain.set(0,0);
		
	}

}
