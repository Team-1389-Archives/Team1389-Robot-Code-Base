package com.team1389.base.control;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class IMUAnglePIDSource implements PIDSource{
	
	PIDSourceType pidType;
	AHRS imu;
	
	public IMUAnglePIDSource(AHRS imu) {
		this.imu = imu;
		setPIDSourceType(PIDSourceType.kDisplacement);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidType;
	}

	@Override
	public double pidGet() {
		switch (pidType){
		case kDisplacement:
			return imu.getAngle();
		case kRate:
			System.out.println("in IMUAnglePIDSource this is not guarunteed to work properly");
			return imu.getRawAccelZ();//is this the right thing?
		default:
			throw new RuntimeException("this shouldn't be able to run ever");
		
		}
	}

}
