package com.team1389.base.util.testing;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;

import edu.wpi.first.wpilibj.CANTalon;

public class TalonMonitorCommand extends Command{
	CANTalon talon;
	String name;
	Timer timer;
	final double TIME_BETWEEN_UPDATES = .75;
	
	
	public TalonMonitorCommand(CANTalon talon, String name) {
		this.talon = talon;
		this.name = name;
		this.timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.zero();
	}


	@Override
	public boolean execute() {
		if (timer.get() > TIME_BETWEEN_UPDATES){
			System.out.println("Talon " + name + " s: " + talon.getSpeed() + "p: " + talon.getPosition());
			timer.zero();
		}
		return false;
	}


}
