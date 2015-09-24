package com.team1389.base.util;

import edu.wpi.first.wpilibj.Joystick;

public class XboxJoystick extends Joystick{

	public XboxJoystick(int port) {
		super(port);
	}
	protected XboxJoystick(int port, int numAxisTypes, int numButtonTypes) {
		super(port, numAxisTypes, numButtonTypes);
	}
	
	public double getLeftX(){
		return getRawAxis(0);
	}
	
	public double getLeftY(){
		return getRawAxis(1);
	}
	
	///TODO finish this

}
