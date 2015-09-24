package com.team1389.robot;

import com.team1389.base.util.XboxJoystick;

import edu.wpi.first.wpilibj.Joystick;

public class Inputs {
	//use this class to define inputs. for example:
	//public static DigitalInput limit1;
	//public static Joystick stick;
	
	public static XboxJoystick driveStick;
	public static XboxJoystick manipStick;
	
	public static void setup(){
		//initialize variables here. For example:
		//limit1 = new DigitalInput(RobotMap.limit1);
		//stick = new DigitalInput(RobotMap.operator);
		
		driveStick = new XboxJoystick(0);
		manipStick = new XboxJoystick(1);
	}
}
