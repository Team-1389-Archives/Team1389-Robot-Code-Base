package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;

import edu.wpi.first.wpilibj.CANTalon;

public class TalonCurrentMonitor extends Command{

	CANTalon[] talons;
	Timer timer;
	
	public TalonCurrentMonitor(CANTalon[] talons) {
		this.talons = talons;
		timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.zero();
	}
	
	@Override
	public boolean execute() {
		if (timer.get() >= 2.0){
			String msg = "";
			for (CANTalon talon : talons){
				msg += talon.getDeviceID() + ":" + talon.getOutputCurrent() + " ";
			}
			timer.zero();
		}
		return false;
	}

}
