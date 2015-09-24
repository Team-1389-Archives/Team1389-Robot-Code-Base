package com.team1389.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	//use this class to define inputs. for example:
	//public static DigitalInput limit1;
	//public static Joystick stick;
	
	public static Joystick driveStick;
	
	public static void setup(){
		//initialize variables here. For example:
		//limit1 = new DigitalInput(RobotMap.limit1);
		//stick = new DigitalInput(RobotMap.operator);
		
		driveStick = new Joystick(0);
	}
}
