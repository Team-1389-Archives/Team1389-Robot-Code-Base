package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.CommandsUtil;
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
}
