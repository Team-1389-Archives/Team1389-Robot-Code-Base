package com.team1389.base.util.control;

import org.strongback.command.Command;
import org.strongback.components.ui.InputDevice;

import com.team1389.base.util.CommandsUtil;
import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.TalonSRXPositionHardware;

public class TalonDriveControl{
	TalonSRXPositionHardware left, right;
	double maxVel, maxAcc, turnMod;
	
	public TalonDriveControl(TalonSRXPositionHardware left, TalonSRXPositionHardware right, double maxVel, double maxAcc, double wheelTurnsPerRotation, PIDConstants pid) {
		this.left = left;
		this.right = right;
		this.maxVel = maxVel;
		this.maxAcc = maxAcc;
		this.turnMod = wheelTurnsPerRotation;
		
		left.setPID(pid);
		right.setPID(pid);
	}
	
	public Command driveDistanceCommand(double distance){
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, maxVel, maxAcc);

		SetpointProvider provider = new MotionProfileSetpointProvider(profile);

		Command leftCommand = new PositionControllerControlCommand(provider, left);
		Command rightCommand = new PositionControllerControlCommand(provider, right);
		
		return CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
	}
	
	public Command turnAmount(double rotations){
		MotionProfile leftProfile = new ConstantAccellerationMotionProfile(rotations * turnMod, maxVel, maxAcc);
		MotionProfile rightProfile = new InvertMotionProfile(leftProfile);

		SetpointProvider leftProvider = new MotionProfileSetpointProvider(leftProfile);
		SetpointProvider rightProvider = new MotionProfileSetpointProvider(rightProfile);

		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		
		return CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
	}
	
	public Command teleopControl(InputDevice joystick, double maxSpeed){
		SetableSetpointProvider leftProvider = new SetableSetpointProvider();
		SetableSetpointProvider rightProvider = new SetableSetpointProvider();
		
		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		Command driveControl = new DriveControl(leftProvider, rightProvider, joystick, maxSpeed);
		
		return CommandsUtil.combineSimultaneous(driveControl, leftCommand, rightCommand);
	}
	
	class DriveControl extends Command{
		
		double leftPos, rightPos;
		double speed;
		SetableSetpointProvider right, left;
		InputDevice joystick;
		Timer timer;
		
		public DriveControl(SetableSetpointProvider left, SetableSetpointProvider right, InputDevice joystick, double speed) {
			leftPos = 0;
			rightPos = 0;
			timer = new Timer();
			this.speed = speed;
			this.left = left;
			this.right = right;
			this.joystick = joystick;
		}

		
		@Override
		public void initialize() {
			left.setSetpoint(0);
			right.setSetpoint(0);
			leftPos = 0.0;
			rightPos = 0.0;
			timer.zero();
		}
		
		@Override
		public boolean execute() {
			double x = joystick.getAxis(0).read();
			double y = -joystick.getAxis(1).read();
			
			double maxSpeed = speed * timer.get();
			
			double changeInLeftPos = (y + x) * maxSpeed;
			double changeInRightPos = (y - x) * maxSpeed;
			
			leftPos += changeInLeftPos;
			rightPos += changeInRightPos;
			
			left.setSetpoint(leftPos);
			right.setSetpoint(rightPos);

			timer.zero();
			return false;
		}
		
	}
}

