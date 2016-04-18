package com.team1389.base.util.testing;

import com.kauailabs.navx.frc.AHRS;

public class MonitorNavXCommand extends PeriodicCommand{
	
	AHRS  imu;
	
	public MonitorNavXCommand(AHRS imu) {
		this.imu = imu;
	}

	@Override
	double secondsBetweenTicks() {
		return 1;
	}

	@Override
	void tick() {
		System.out.println("Angle: " + imu.getAngle());
	}

}
