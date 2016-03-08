package com.team1389.base.util.testing;

import org.strongback.command.Command;
import org.strongback.components.ui.ContinuousRange;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.SpeedController;

public class JoystickSpeedControllerCommand extends Command{
	SpeedController motor;
	ContinuousRange joyAxis;
	double speedMod;
	
	public JoystickSpeedControllerCommand(SpeedController motor, ContinuousRange joyAxis, double speedMod) {
		this.joyAxis = joyAxis;
		this.motor = motor;
		this.speedMod = speedMod;
	}

	@Override
	public boolean execute() {
		motor.set(joyAxis.read() * speedMod);
		return false;
	}

}
