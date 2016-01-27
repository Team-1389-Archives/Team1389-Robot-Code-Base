package com.team1389.base.wpiWrappers;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * A representation on a TalonSRX in position mode as a {@link PositionController} 
 */
public class TalonSRXPositionHardware implements PositionController{
	CANTalon wpiTalon;
	
	public TalonSRXPositionHardware(CANTalon wpiTalon) {
		this.wpiTalon = wpiTalon;
		this.wpiTalon.changeControlMode(TalonControlMode.Position);
	}

	@Override
	public void setPosition(double position) {
		wpiTalon.set(position);
	}

	@Override
	public double getPosition() {
		return wpiTalon.getPosition();
	}

	@Override
	public void setPID(double p, double i, double d) {
		wpiTalon.setPID(p, i, d);
	}

}
