package com.team1389.base.control;

import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.util.testing.PeriodicCommand;
import com.team1389.base.wpiWrappers.PositionController;

import edu.wpi.first.wpilibj.PIDOutput;

public class DummyPositionController extends PeriodicCommand implements PositionController, PIDOutput{
	
	double position;
	
	Timer msgTime;
	
	public DummyPositionController() {
		msgTime = new Timer();
		position = 0.0;
	}

	@Override
	public void setPosition(double position) {
		this.position = position;
	}

	@Override
	public void setCurrentPositionAs(double as) {
		this.position = as;//not really but good enough for testing most things...
	}

	@Override
	public double getPosition() {
		return position;
	}

	@Override
	public void setPID(PIDConstants constants) {
		//no op
	}

	@Override
	public void disable() {
		//no op
	}

	@Override
	public double secondsBetweenTicks() {
		return 0.5;
	}

	@Override
	public void tick() {
		System.out.println("Dummy Position: " + position);
	}

	@Override
	public void pidWrite(double output) {
		if (msgTime.get() > 0.5){
			System.out.println("getting pidwritten with output = " + output);
			msgTime.zero();
		}
		position = output;
	}

}
