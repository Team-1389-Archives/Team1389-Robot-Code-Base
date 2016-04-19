package com.team1389.base.controllers;

import javax.management.RuntimeErrorException;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.PositionController;

public class CombinePositionController implements PositionController{
	
	PositionController[] controllers;
	
	public CombinePositionController(PositionController... controllers) {
		this.controllers = controllers;
	}

	@Override
	public void setPosition(double position) {
		for (PositionController controller : controllers){
			controller.setPosition(position);
		}
	}

	@Override
	public void setCurrentPositionAs(double as) {
		for (PositionController controller : controllers){
			controller.setCurrentPositionAs(as);
		}
		
	}

	@Override
	public double getPosition() {
		if (controllers.length <= 0){
			return 0.0;
		} else {
			return controllers[0].getPosition();
		}
	}

	@Override
	public void setPID(PIDConstants constants) {
		for (PositionController controller : controllers){
			controller.setPID(constants);
		}
	}

	@Override
	public void disable() {
		for (PositionController controller : controllers){
			controller.disable();
		}
	}

}
