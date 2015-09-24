package com.team1389.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem{
	
	Victor leftDrive;
	Victor rightDrive;
	
	public DriveTrain(){
		leftDrive = new Victor(0);
		rightDrive = new Victor(1);
	}
	
	public void set(double left, double right){
		leftDrive.set(left);
		rightDrive.set(right);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
