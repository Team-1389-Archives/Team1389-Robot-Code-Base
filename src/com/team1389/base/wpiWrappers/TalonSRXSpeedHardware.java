package com.team1389.base.wpiWrappers;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDeviceStatus;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class TalonSRXSpeedHardware{
	CANTalon wpiTalon;
	
	public TalonSRXSpeedHardware(CANTalon wpiTalon) {
		this.wpiTalon = wpiTalon;
	}

	public void setSpeed(double speed) {
		this.wpiTalon.changeControlMode(TalonControlMode.Speed);
		if (wpiTalon.isSensorPresent(FeedbackDevice.PulseWidth).equals(FeedbackDeviceStatus.FeedbackStatusPresent)){
			wpiTalon.set(speed);
		} else {
			System.out.println("speed encoder disconnected, current value is " + wpiTalon.getPosition());
			disable();
		}
	}

	public void setPID(PIDConstants pidC) {
		wpiTalon.setProfile(0);//sets which pid gains to use
		wpiTalon.setP(pidC.p);
		wpiTalon.setI(pidC.i);
		wpiTalon.setD(pidC.d);
		wpiTalon.setF(pidC.kv);
	}

	public void disable(){
		wpiTalon.changeControlMode(TalonControlMode.PercentVbus);
		wpiTalon.set(0);
	}
	
}
