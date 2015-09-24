package com.team1389.robot.commands;

import com.team1389.base.BaseGlobals;
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
		double joyValue = Inputs.manipStick.getLeftY();
		if (Inputs.manipStick.getRawButton(2)){
			joyValue *= .5;
		}
		Subsystems.elevator.set(joyValue);
	}

	@Override
	protected boolean isFinished() {
		return !BaseGlobals.robotBase.isOperatorControl();
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
