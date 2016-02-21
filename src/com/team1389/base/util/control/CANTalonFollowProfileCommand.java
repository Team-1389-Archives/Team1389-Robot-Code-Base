package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.CANTalon.TrajectoryPoint;

public class CANTalonFollowProfileCommand extends Command{
	PIDConstants pidConstants;
	CANTalon talon;
	TalonControlMode previousControlMode;
	MotionProfile profile;
	Timer timer;
	
	public CANTalonFollowProfileCommand(MotionProfile profile, PIDConstants pidConstants, CANTalon talon) {
		this.pidConstants = pidConstants;
		this.profile = profile;
		previousControlMode = talon.getControlMode();
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		sendTrajectory(talon, profile, pidConstants);
	}

	@Override
	public boolean execute() {
		System.out.println("running PID on CANTalon with p="+pidConstants.p+" i="+pidConstants.i+
				" d="+pidConstants.d+" kv="+pidConstants.kv+" ka="+pidConstants.ka);
		timer.zero();
		return timer.get() >= profile.getDuration();
	}
	
	@Override
	public void end() {
		talon.clearMotionProfileTrajectories();
		talon.changeControlMode(previousControlMode);
	}
	
	final double timeBetweenTicks = 5;//5 milliseconds per tick
	private void sendTrajectory(CANTalon talon, MotionProfile profile, PIDConstants constants){
		final int durationInMillis = (int) (profile.getDuration() * 1000);
		
		TrajectoryPoint point = new TrajectoryPoint();
		for (int millis = 0; millis < durationInMillis; millis += 1){
			double seconds = millis * 0.001;
			if (millis == 0){
				point.zeroPos = true;
			} else {
				point.zeroPos = false;
			}
//			point.position = 
		}
	}

}
