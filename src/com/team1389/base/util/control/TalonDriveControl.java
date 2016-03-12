package com.team1389.base.util.control;

import org.strongback.command.Command;
import org.strongback.components.ui.InputDevice;

import com.team1389.base.util.CommandsUtil;
import com.team1389.base.util.DoubleConstant;
import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.TalonSRXPositionHardware;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.TalonSRX;

public class TalonDriveControl{
	TalonSRXPositionHardware left, right;
	double maxVel, maxAcc, turnMod;
	PIDConstants pid;
	Command configTalons;
	DoubleConstant turnModEditor;
	DoubleConstant straightDriveFunctionEditor;
	DoubleConstant turnDriveFunctionEditor;
	DoubleConstant driveModEditor;
	DoubleConstant throttleAxis;
	DoubleConstant yawAxis;

	
	
	public TalonDriveControl(TalonSRXPositionHardware left, TalonSRXPositionHardware right, double maxVel, double maxAcc, double wheelTurnsPerRotation, PIDConstants pid) {
		this.left = left;
		this.right = right;
		this.maxVel = maxVel;
		this.maxAcc = maxAcc;
		this.turnMod = wheelTurnsPerRotation;
		this.pid = pid;
		configTalons = new SetPid(left, right, pid);
		turnModEditor = new DoubleConstant("turnMod", 0.5);
		straightDriveFunctionEditor = new DoubleConstant("straightDriveFunction", 2.0);
		turnDriveFunctionEditor = new DoubleConstant("turnDriveFunction",2.0);
		driveModEditor = new DoubleConstant("driveMod",0.8);
		}
	
	public Command driveDistanceCommand(double distance){
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, maxVel, maxAcc);

		SetpointProvider provider = new MotionProfileSetpointProvider(profile);

		Command leftCommand = new PositionControllerControlCommand(provider, left);
		Command rightCommand = new PositionControllerControlCommand(provider, right);
		
		Command all = CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalons, all);
	}
	
	public Command turnAmount(double rotations){
		MotionProfile leftProfile = new ConstantAccellerationMotionProfile(rotations * turnMod, maxVel, maxAcc);
		MotionProfile rightProfile = new InvertMotionProfile(leftProfile);

		SetpointProvider leftProvider = new MotionProfileSetpointProvider(leftProfile);
		SetpointProvider rightProvider = new MotionProfileSetpointProvider(rightProfile);

		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		
		Command all = CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalons, all);
	}
	
	public Command teleopControl(InputDevice joystick, double maxSpeed){
		SetableSetpointProvider leftProvider = new SetableSetpointProvider();
		SetableSetpointProvider rightProvider = new SetableSetpointProvider();
		
		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		Command driveControl = new DriveControl(leftProvider, rightProvider, joystick, maxSpeed);
		
		Command all = CommandsUtil.combineSimultaneous(driveControl, leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalons, all);
	}
	
	class SetPid extends Command{
		TalonSRXPositionHardware left, right;
		PIDConstants pid;
		
		public SetPid(TalonSRXPositionHardware left, TalonSRXPositionHardware right, PIDConstants pid) {
			this.left = left;
			this.right = right;
			this.pid = pid;
		}
		
		@Override
		public void initialize() {
			left.setPID(pid);
			right.setPID(pid);
		}

		@Override
		public boolean execute() {
			return true;
		}
		
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
			double x = joystick.getAxis(0).read() *turnModEditor.get();
			double y = -joystick.getAxis(1).read()*driveModEditor.get();
			
			x = powerWithSign(x, 2);
			y = powerWithSign(y, 2);
			
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

	public double absMax(double a, double b){
		if (Math.abs(a) > Math.abs(b)){
			return a;
		} else {
			return b;
		}
	}
	
	private double powerWithSign(double in, double ex){
		double posRes = Math.pow(Math.abs(in), ex);
		if (in > 0){
			return posRes;
		} else {
			return -posRes;
		}
	}
}

