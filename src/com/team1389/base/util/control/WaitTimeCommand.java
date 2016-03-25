package com.team1389.base.util.control;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;

public class WaitTimeCommand extends Command{
	Timer timer;
	double amount;
	
	public WaitTimeCommand(double amount) {
		this.timer = new Timer();
		this.amount = amount;
	}
	
	@Override
	public void initialize() {
		timer.zero();
	}

	@Override
	public boolean execute() {
		return timer.get() > amount;
	}
	
	

}
