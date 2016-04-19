package com.team1389.base.controllers;

import com.team1389.base.wpiWrappers.PositionController;

import edu.wpi.first.wpilibj.PIDOutput;

public class VelocityPIDOutputWithPositionController implements PIDOutput{
	
	PositionController position;
	
	public VelocityPIDOutputWithPositionController(PositionController position) {
		this.position = position;
	}

	@Override
	public void pidWrite(double output) {
		double current = position.getPosition();
		position.setPosition(current + output);
	}

}
