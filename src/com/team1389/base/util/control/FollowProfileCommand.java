package com.team1389.base.util.control;

import java.time.Duration;

import com.team1389.base.webserver.chart.Chart;
import com.team1389.base.webserver.chart.DataPoint;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class FollowProfileCommand extends Command{
	MotionProfile profile;
	Timer timer;
	PIDOutput device;
	double kv, ka;
	
	Chart chart;
	
	public FollowProfileCommand(MotionProfile profile, PIDOutput device, double kv, double ka) {
		this.profile = profile;
		this.device = device;
		timer = new Timer();
		this.kv = kv;
		this.ka = ka;
		
		chart = new Chart(.1, .1, "time", "velocity");
	}

	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		double time = timer.get();
		double velocity = profile.getVelocity(time);
		double acceleration = profile.getAcceleration(time);
		device.pidWrite(kv * velocity + ka * acceleration);
		
		chart.addPoint(new DataPoint(time, velocity));
	}

	@Override
	protected boolean isFinished() {
		return timer.get() >= profile.getDuration();
	}

	@Override
	protected void end() {
		timer.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
