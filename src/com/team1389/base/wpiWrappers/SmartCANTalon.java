package com.team1389.base.wpiWrappers;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class SmartCANTalon {
	CANTalon wpiTalon;
	
	public SmartCANTalon(CANTalon wpiTalon) {
		this.wpiTalon = wpiTalon;
	}
	
	public void setPVoltage(double pVoltage){
		wpiTalon.changeControlMode(TalonControlMode.PercentVbus);
		wpiTalon.set(pVoltage);
	}
	
	public void setSpeed(double speed){
		wpiTalon.changeControlMode(TalonControlMode.Speed);
		wpiTalon.set(speed);
	}
	
	public void setPosition(double position){
		wpiTalon.changeControlMode(TalonControlMode.Position);
		wpiTalon.set(position);
	}
	
	public void setFollowing(int talonId){
		wpiTalon.changeControlMode(TalonControlMode.Follower);
		wpiTalon.set((double) talonId);
	}
	
	public int getId(){
		return wpiTalon.getDeviceID();
	}
	
	public double getSpeed(){
		return wpiTalon.getSpeed();
	}
	
	public double getPosition(){
		return wpiTalon.getPosition();
	}
	
	public boolean isEncoderConnected(){
		return wpiTalon.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusPresent;
	}
	
	public CANTalon unsafeAccessCANTalon(){
		return wpiTalon;
	}
	
	public void setPID(PIDConstants pidc){
		wpiTalon.setPID(pidc.p, pidc.i, pidc.d);
	}
	
	public void setSensorPosition(double pos){
		wpiTalon.setPosition(pos);
	}
}
