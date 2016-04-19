package com.team1389.base.controllers;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.PositionController;

public class InvertedPositionController implements PositionController{
	
	PositionController controller;
	
	public InvertedPositionController(PositionController controller) {
		this.controller = controller;
	}

	@Override
	public void setPosition(double position) {
		controller.setPosition(-position);
	}

	@Override
	public void setCurrentPositionAs(double as) {
		controller.setCurrentPositionAs(-as);
	}

	@Override
	public double getPosition() {
		return -controller.getPosition();
	}

	@Override
	public void setPID(PIDConstants constants) {
		controller.setPID(constants);
	}

	@Override
	public void disable() {
		controller.disable();
	}

}
