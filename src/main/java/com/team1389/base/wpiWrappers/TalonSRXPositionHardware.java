package com.team1389.base.wpiWrappers;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * A representation on a TalonSRX in position mode as a {@link PositionController} 
 */
public class TalonSRXPositionHardware implements PositionController{
	CANTalon wpiTalon;
	double ticksPerDegree;
	
	public TalonSRXPositionHardware(CANTalon wpiTalon, double ticksPerDegree, boolean invert) {
		this.wpiTalon = wpiTalon;
		this.wpiTalon.changeControlMode(TalonControlMode.Position);
		this.ticksPerDegree = ticksPerDegree;
		this.wpiTalon.setInverted(invert);
	}

	@Override
	public void setPosition(double position) {
		double hardwarePosition = position * ticksPerDegree;
		wpiTalon.set(hardwarePosition);
	}

	@Override
	public double getPosition() {
		double hardwarePosition = wpiTalon.getPosition();
		return hardwarePosition / ticksPerDegree;
	}

	@Override
	public void setPID(double p, double i, double d) {
		wpiTalon.setPID(p, i, d);
	}

}
