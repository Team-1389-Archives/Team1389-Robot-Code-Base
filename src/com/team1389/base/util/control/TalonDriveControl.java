package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.CommandsUtil;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.util.control.PositionControllerRampCommand.SetpointProvider;
import com.team1389.base.wpiWrappers.TalonSRXPositionHardware;

public class TalonDriveControl{
	TalonSRXPositionHardware left, right;
	double maxVel, maxAcc, turnMod;
	PIDConstants pid;
	
	public TalonDriveControl(TalonSRXPositionHardware left, TalonSRXPositionHardware right, double maxVel, double maxAcc, double wheelTurnsPerRotation, PIDConstants pid) {
		this.left = left;
		this.right = right;
		this.maxVel = maxVel;
		this.maxAcc = maxAcc;
		this.pid = pid;
		this.turnMod = wheelTurnsPerRotation;
	}
	
	public Command driveDistanceCommand(double distance){
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, maxVel, maxAcc);

		SetpointProvider provider = new MotionProfileSetpointProvider(profile);

		Command leftCommand = new PositionControllerRampCommand(left, provider, pid);
		Command rightCommand = new PositionControllerRampCommand(right, provider, pid);
		
		return CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
	}
	
	public Command turnAmount(double rotations){
		MotionProfile leftProfile = new ConstantAccellerationMotionProfile(rotations * turnMod, maxVel, maxAcc);
		MotionProfile rightProfile = new InvertMotionProfile(leftProfile);

		SetpointProvider leftProvider = new MotionProfileSetpointProvider(leftProfile);
		SetpointProvider rightProvider = new MotionProfileSetpointProvider(rightProfile);

		Command leftCommand = new PositionControllerRampCommand(left, leftProvider, pid);
		Command rightCommand = new PositionControllerRampCommand(right, rightProvider, pid);
		
		return CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
	}
}
