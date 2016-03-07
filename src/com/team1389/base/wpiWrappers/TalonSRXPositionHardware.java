package com.team1389.base.wpiWrappers;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * A representation on a TalonSRX in position mode as a {@link PositionController} 
 */
public class TalonSRXPositionHardware implements PositionController{
	CANTalon wpiTalon;
	double ticksPerRotation;
	double offset;
	
	public TalonSRXPositionHardware(CANTalon wpiTalon, double ticksPerRotation) {
		this.wpiTalon = wpiTalon;
		this.ticksPerRotation = ticksPerRotation;
		this.offset = 0;
	}

	@Override
	public void setPosition(double position) {
		this.wpiTalon.changeControlMode(TalonControlMode.Position); double hardwarePosition = (position + offset) * ticksPerRotation;
		if (wpiTalon.isSensorPresent(FeedbackDevice.PulseWidth).equals(FeedbackDeviceStatus.FeedbackStatusPresent)){
			wpiTalon.set(hardwarePosition);
		} else {
			System.out.println("encoder disconnected, current value is " + wpiTalon.getPosition());
			disable();
		}
	}

	@Override
	public double getPosition() {
		return getScaledPos();
	}

	private double getScaledPos() {
		return wpiTalon.getPosition() / ticksPerRotation - offset;
	}

	@Override
	public void setPID(PIDConstants pidC) {
		wpiTalon.setProfile(0);//sets which pid gains to use
		wpiTalon.setP(pidC.p);
		wpiTalon.setI(pidC.i);
		wpiTalon.setD(pidC.d);
		wpiTalon.setF(pidC.kv);
	}

	@Override
	public void setCurrentPositionAs(double as) {
		offset = - as;
		wpiTalon.changeControlMode(TalonControlMode.Position);
		wpiTalon.setPosition(0);
		wpiTalon.set(0);
	}
	
	/**
	 * TODO: delete this eventually 
	 */
	public CANTalon getTalon(){
		return wpiTalon;
	}
	
	public void disable(){
		wpiTalon.changeControlMode(TalonControlMode.PercentVbus);
		wpiTalon.set(0);
	}
	
}
