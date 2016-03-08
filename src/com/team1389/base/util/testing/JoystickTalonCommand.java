package com.team1389.base.util.testing;

import org.strongback.command.Command;
import org.strongback.components.ui.ContinuousRange;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class JoystickTalonCommand extends Command{
	CANTalon motor;
	ContinuousRange joyAxis;
	double speedMod;
	
	public JoystickTalonCommand(CANTalon motor, ContinuousRange joyAxis, double speedMod) {
		this.joyAxis = joyAxis;
		this.motor = motor;
		this.speedMod = speedMod;
	}

	@Override
	public boolean execute() {
		motor.changeControlMode(TalonControlMode.PercentVbus);
		motor.set(joyAxis.read() * speedMod);
		return false;
	}

}
