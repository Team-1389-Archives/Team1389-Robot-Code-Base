package com.team1389.base.util;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class represents an xbox joystick. It adds some functionality to the default
 * wpilib Joystick class to make using Xbox joysticks easier.
 *
 */
public class XboxJoystick extends Joystick{

	public XboxJoystick(int port) {
		super(port);
	}
	protected XboxJoystick(int port, int numAxisTypes, int numButtonTypes) {
		super(port, numAxisTypes, numButtonTypes);
	}
	
	private double buffer(double value){
		double result = value;
		if (Math.abs(value) < .1){
			result = 0;
		}
		return result;
	}
	
	public double getLeftX(){
		
		return getRawAxis(0);
	}
	
	public double getLeftY(){
		return buffer(getRawAxis(1));
	}
	public double getRightX(){
		return buffer(getRawAxis(2));
	}
	public double getRightY(){
		return buffer(getRawAxis(3));
	}
	///TODO finish this

}
