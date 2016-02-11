package com.team1389.base.util.control;

import org.strongback.command.Command;
import org.strongback.control.PIDController;

import com.team1389.base.util.Timer;
import com.team1389.base.webserver.chart.Chart;
import com.team1389.base.webserver.chart.DataPoint;
import com.team1389.base.webserver.chart.WebChartManager;

/**
 * A command that will make a PIDController follow a motion profile 
 */
public class FollowProfileCommand extends Command{
	Timer timer;
	MotionProfile profile;
	PIDController device;
	double kv, ka;
	
	Chart chart;

	public FollowProfileCommand(MotionProfile profile, PIDController device, double kv, double ka) {
		timer = new Timer();
		this.profile = profile;
		this.device = device;
		this.kv = kv;
		this.ka = ka;

		chart = new Chart(.1, .1, "time", "velocity");
		WebChartManager.getInstance().addChart("FollowProfileCommand", chart);
	}
	
	@Override
	public void initialize() {
		timer.zero();
	}

	@Override
	public boolean execute() {
		double time = timer.get();
		double velocity = profile.getVelocity(time);
		double acceleration = profile.getAcceleration(time);
		device.withTarget(kv * velocity + ka * acceleration);
		
		chart.addPoint(new DataPoint(time, velocity));

		return timer.get() >= profile.getDuration();
	}
}
