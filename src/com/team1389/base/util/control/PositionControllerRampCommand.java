package com.team1389.base.util.control;

import org.strongback.command.Command;
import org.strongback.components.ui.InputDevice;

import com.team1389.base.util.Timer;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.TalonSRXPositionHardware;

import edu.wpi.first.wpilibj.CANTalon;

public class PositionControllerRampCommand extends Command{
	TalonSRXPositionHardware controller;
	SetpointProvider setpointProvider;
	double maxPos, minPos, maxChange;
	Timer timer;
	PIDConstants pidC;
	double setpoint, goalPoint;

	public PositionControllerRampCommand(TalonSRXPositionHardware controller, SetpointProvider setpointProvider,
			PIDConstants pidC, double maxPos, double minPos, double maxChange) {
		this.controller = controller;
		this.maxPos = maxPos;
		this.minPos = minPos;
		this.maxChange = maxChange;
		this.pidC = pidC;
		this.setpointProvider = setpointProvider;
		
		timer = new Timer();
		
	}
	
	public PositionControllerRampCommand(TalonSRXPositionHardware controller, SetpointProvider provider, PIDConstants pidC) {
		this(controller, provider, pidC, Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE);
		//just looking at this constructor makes me cringe... its so hacked
	}
	
	@Override
	public void initialize() {
		CANTalon talon = controller.getTalon();

		talon.configNominalOutputVoltage(0, 0);
		talon.configMaxOutputVoltage(12);

		this.controller.setPID(pidC);
		
		if (setpointProvider.shouldResetPosition()){
			controller.setCurrentPositionAs(0);
		}
		setpoint = controller.getPosition();
		
		setpointProvider.init();

		timer.zero();
	}

	@Override
	public boolean execute() {
		goalPoint = setpointProvider.getSetpoint();
		
		setpoint = getNextSetpoint(goalPoint, timer.get());
		
		controller.setPosition(setpoint);
	
		timer.zero();
		return setpointProvider.isFinished();
	}
	
	private double getNextSetpoint(double goalPoint, double timeDiff){
		double maxChangeInSetpoint = maxChange * timeDiff;
		double newSetpoint;
		
		if (Math.abs(goalPoint - setpoint) < maxChangeInSetpoint){
			newSetpoint = goalPoint;
		} else if (goalPoint > setpoint){
			newSetpoint = setpoint + maxChangeInSetpoint;
		} else {
			newSetpoint = setpoint - maxChangeInSetpoint;
		}
		
		if (newSetpoint > maxPos){
			newSetpoint = maxPos;
		} else if (newSetpoint < minPos){
			newSetpoint = minPos;
		}
		
		return newSetpoint;
	}
	
	@Override
	public void end() {
		controller.disable();
	}
	
	@Override
	public void interrupted() {
		end();
	}

}
