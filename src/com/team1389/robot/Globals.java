package com.team1389.robot;

import com.team1389.base.util.DoubleConstant;

public class Globals {
	public static DoubleConstant liftSpeed;
	public static DoubleConstant autonDriveSpeed;
	public static DoubleConstant autonLength;
	
	public static void setup(){
		liftSpeed = new DoubleConstant("liftSpeed", 0.75);
		autonDriveSpeed = new DoubleConstant("autonDriveSpeed", -0.5);
		autonLength = new DoubleConstant("autonLength", 3.0);
	}
}
