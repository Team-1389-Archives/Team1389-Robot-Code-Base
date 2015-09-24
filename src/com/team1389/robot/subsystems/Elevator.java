package com.team1389.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	
	SpeedController rightMotor;
	SpeedController leftMotor;

	@Override
	protected void initDefaultCommand() {
		rightMotor = new Victor(2);
		leftMotor = new Victor(3);
	}
	
	public void set(double power){
		rightMotor.set(-power);
		leftMotor.set(power);
	}

}
