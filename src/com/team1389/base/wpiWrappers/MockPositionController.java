package com.team1389.base.wpiWrappers;

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
	public void setPID(double p, double i, double d) {
		//no op
	}

}
