package com.team1389.base.util.testing;

import com.kauailabs.navx.frc.AHRS;

public class MonitorNavXCommand extends PeriodicCommand{
	
	AHRS  imu;
	
	public MonitorNavXCommand(AHRS imu) {
		this.imu = imu;
	}

	@Override
	public double secondsBetweenTicks() {
		return 1;
	}

	@Override
	public void tick() {
		System.out.println("Angle: " + imu.getAngle());
	}

}
