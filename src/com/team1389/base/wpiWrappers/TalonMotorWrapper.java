package com.team1389.base.wpiWrappers;

import org.strongback.components.Motor;

import edu.wpi.first.wpilibj.CANTalon;

public class TalonMotorWrapper implements Motor{
	CANTalon talon;
	
	public TalonMotorWrapper(CANTalon talon) {
		this.talon = talon;
	}

	@Override
	public void stop() {
		talon.set(0);
	}

	@Override
	public double getSpeed() {
		return talon.getSpeed();
	}

	@Override
	public Motor setSpeed(double speed) {
		talon.set(speed);
		return this;
	}

}
