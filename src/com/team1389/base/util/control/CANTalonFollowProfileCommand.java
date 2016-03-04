package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.MotionProfileStatus;
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
		this.talon = talon;
		previousControlMode = talon.getControlMode();
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		System.out.println("running PID on CANTalon with p="+pidConstants.p+" i="+pidConstants.i+
				" d="+pidConstants.d+" kv="+pidConstants.kv+" ka="+pidConstants.ka);
		
		timer.zero();
		talon.setPosition(0);

		talon.changeControlMode(TalonControlMode.MotionProfile);
		talon.clearMotionProfileTrajectories();
		talon.setProfile(0);
		talon.setPID(pidConstants.p, pidConstants.i, pidConstants.d);
		sendTrajectory(talon, profile, pidConstants);
		
		
		talon.processMotionProfileBuffer();
		System.out.println("after send, top buffer count is " + getTalonStatus(talon).topBufferCnt);
	}

	@Override
	public boolean execute() {
		talon.processMotionProfileBuffer();
		
		return timer.get() >= profile.getDuration();
	}

	@Override
	public void end() {
		talon.clearMotionProfileTrajectories();
		talon.changeControlMode(previousControlMode);
	}
	
	final int timeBetweenTicks = 20;//5 milliseconds per tick
	private void sendTrajectory(CANTalon talon, MotionProfile profile, PIDConstants constants){
		final int durationInMillis = (int) (profile.getDuration() * 1000);
		
		TrajectoryPoint point = new TrajectoryPoint();
		for (int millis = 0; millis < durationInMillis; millis += timeBetweenTicks){
			double seconds = millis * 0.001;
			if (millis == 0){
				point.zeroPos = true;
			} else {
				point.zeroPos = false;
			}
			
			if (millis + timeBetweenTicks < durationInMillis){
				point.isLastPoint = true;
			} else {
				point.isLastPoint = false;
			}
			point.position = profile.getPosition(seconds);
			point.velocity = profile.getVelocity(seconds);
			point.timeDurMs = timeBetweenTicks;
			point.profileSlotSelect = 0;
			
			System.out.println("pushing trajectory point");
			
			talon.pushMotionProfileTrajectory(point);
		}
	}
	
	private MotionProfileStatus getTalonStatus(CANTalon talon){
		MotionProfileStatus status = new MotionProfileStatus();
		talon.getMotionProfileStatus(status);
		return status;
	}

}
