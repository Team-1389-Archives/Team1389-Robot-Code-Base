package com.team1389.robot.subsystems;

import com.team1389.robot.Globals;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem{
	
	SpeedController motor;

	@Override
	protected void initDefaultCommand() {
		motor = new Victor(2);
	}
	
	public void set(double power){
		double newPower;
		if(power > 0){
			newPower = power * Globals.liftSpeed.get() * .6;//down
		} else {
			newPower = power * Globals.liftSpeed.get();
		}
		motor.set(-newPower);
	}

}
