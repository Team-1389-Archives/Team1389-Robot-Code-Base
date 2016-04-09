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
	PIDConstants forwardPid;
	PIDConstants turnPid;
	Command configTalonsForward;
	Command configTalonsTurn;
	
	
	public TalonDriveControl(TalonSRXPositionHardware left, TalonSRXPositionHardware right, double maxVel, double maxAcc, double wheelTurnsPerRotation,
			PIDConstants forwardPid, PIDConstants turnPid) {
		this.left = left;
		this.right = right;
		this.maxVel = maxVel;
		this.maxAcc = maxAcc;
		this.turnMod = wheelTurnsPerRotation;
		setPids(forwardPid, turnPid);
	}
	
	public void setPids(PIDConstants straightPid, PIDConstants turnPid){
		this.forwardPid = straightPid;
		this.turnPid = turnPid;
		configTalonsForward = new SetPid(left, right, forwardPid);
		configTalonsTurn = new SetPid(left, right, turnPid);
	}
	
	public Command driveDistanceCommand(double distance){
		return driveDistanceCommand(distance, 1.0, 1.0);
	}
	
	public Command driveDistanceCommand(double distance, double maxVelMultiplyer, double maxAccMultiplyer){
		double finalMaxAcc = maxAcc * maxAccMultiplyer;
		double finalMaxVel = maxVel * maxVelMultiplyer;
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, finalMaxVel, finalMaxAcc);

		SetpointProvider provider = new MotionProfileSetpointProvider(profile);

		Command leftCommand = new PositionControllerControlCommand(provider, left);
		Command rightCommand = new PositionControllerControlCommand(provider, right);
		
		Command all = CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalonsForward, all);
	}
	
	public Command driveArcCommand(double leftDistance, double rightDistance){
		return driveArcCommand(leftDistance, rightDistance, 1.0, 1.0);
	}

	public Command driveArcCommand(double leftDistance, double rightDistance, double maxVelMultiplyer, double maxAccMultiplyer){
		double smallDist, largeDist;
		boolean leftOrRight = leftDistance > rightDistance;
		if (leftOrRight) {
			smallDist = rightDistance;
			largeDist = leftDistance;
		} else {
			smallDist = leftDistance;
			largeDist = rightDistance;
		}

		double finalMaxAcc = maxAcc * maxAccMultiplyer;
		double finalMaxVel = maxVel * maxVelMultiplyer;
		
		double mod = smallDist / largeDist;
		MotionProfile largeProfile = new ConstantAccellerationMotionProfile(largeDist, finalMaxVel, finalMaxAcc);
		MotionProfile smallProfile = new ScalingMotionProfile(largeProfile, mod);

		SetpointProvider largeProvider = new MotionProfileSetpointProvider(largeProfile);
		SetpointProvider smallProvider = new MotionProfileSetpointProvider(smallProfile);

		
		TalonSRXPositionHardware smallTalon, largeTalon;

		if (leftOrRight){
			smallTalon = right;
			largeTalon = left;
		} else {
			smallTalon = left;
			largeTalon = right;
		}

		Command smallCommand = new PositionControllerControlCommand(smallProvider, smallTalon);
		Command largeCommand = new PositionControllerControlCommand(largeProvider, largeTalon);
		
		Command all = CommandsUtil.combineSimultaneous(smallCommand, largeCommand);
		return CommandsUtil.combineSequential(configTalonsForward, all);
	}
	
	public double timeForDistance (double distance){
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, maxVel, maxAcc);
		return profile.getDuration();
	}
	public double timeForDistance (double distance, double maxVel,double maxAcc){
		MotionProfile profile = new ConstantAccellerationMotionProfile(distance, maxVel, maxAcc);
		return profile.getDuration();
	}
	
	public Command turnAmount(double rotations){
		MotionProfile leftProfile = new ConstantAccellerationMotionProfile(rotations * turnMod, maxVel, maxAcc * 2);
		MotionProfile rightProfile = new InvertMotionProfile(leftProfile);

		SetpointProvider leftProvider = new MotionProfileSetpointProvider(leftProfile);
		SetpointProvider rightProvider = new MotionProfileSetpointProvider(rightProfile);

		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		
		Command all = CommandsUtil.combineSimultaneous(leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalonsTurn, all);
	}
	
	public Command teleopControl(InputDevice joystick, double maxSpeed){
		SetableSetpointProvider leftProvider = new SetableSetpointProvider();
		SetableSetpointProvider rightProvider = new SetableSetpointProvider();
		
		Command leftCommand = new PositionControllerControlCommand(leftProvider, left);
		Command rightCommand = new PositionControllerControlCommand(rightProvider, right);
		Command driveControl = new DriveControl(leftProvider, rightProvider, joystick, maxSpeed);
		
		Command all = CommandsUtil.combineSimultaneous(driveControl, leftCommand, rightCommand);
		return CommandsUtil.combineSequential(configTalonsForward, all);
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
			double x = joystick.getAxis(0).read() * .55;
			double y = -joystick.getAxis(1).read();
			
			x = powerWithSign(x, 2);
			y = powerWithSign(y, 2);
			
			double maxSpeed = 2.0 * timer.get();
			
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

