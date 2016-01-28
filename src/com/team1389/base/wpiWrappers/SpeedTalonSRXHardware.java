package com.team1389.base.wpiWrappers;

import org.strongback.components.Motor;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class SpeedTalonSRXHardware implements Motor{
	
	CANTalon talon;
	
	public SpeedTalonSRXHardware(CANTalon talon) {
		this.talon = talon;
		this.talon.changeControlMode(TalonControlMode.Speed);
	}

	@Override
	public void stop() {
		talon.setSetpoint(0.0);
	}

	@Override
	public double getSpeed() {
		return talon.get();
	}

	@Override
	public Motor setSpeed(double speed) {
		talon.set(speed);
		return this;
	}

}
