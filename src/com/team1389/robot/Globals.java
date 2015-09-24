package com.team1389.robot;

import com.team1389.base.util.DoubleConstant;

public class Globals {
	public static DoubleConstant liftSpeed;
	
	public static void setup(){
		liftSpeed = new DoubleConstant("liftSpeed", 0.75);
	}
}
