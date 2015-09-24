package com.team1389.robot;

import com.team1389.robot.subsystems.DriveTrain;
import com.team1389.robot.subsystems.Elevator;

public class Subsystems {
	//use this class to define subsystems. For example:
	//public static DriveSystem drive;
	
	public static DriveTrain driveTrain;
	public static Elevator elevator;
	
	public static void setup(){
		//initialize variables here. For example:
		//drive = new DriveSystem();
		
		driveTrain = new DriveTrain();
		elevator = new Elevator();
	}
}
