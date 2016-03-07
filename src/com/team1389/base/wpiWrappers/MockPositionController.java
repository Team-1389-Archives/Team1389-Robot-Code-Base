package com.team1389.base.wpiWrappers;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

public class MockPositionController implements PositionController{
	double position;
	
	public MockPositionController(double startPos) {
		position = startPos;
	}

	@Override
	public void setPosition(double position) {
		this.position = position;
	}

	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public void setPID(PIDConstants pidC) {
		//no op
	}

	@Override
	public void setCurrentPositionAs(double as) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

}
