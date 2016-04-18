package com.team1389.base.util.testing;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;

public abstract class PeriodicCommand extends Command{
	
	Timer timer;
	double seconds;
	
	public PeriodicCommand() {
		timer = new Timer();
		seconds = secondsBetweenTicks();
	}
	
	@Override
	public void initialize() {
		timer.zero();
	}

	@Override
	public boolean execute() {
		if (timer.get() > seconds){
			tick();
			timer.zero();
		}
		return false;
	}
	
	abstract double secondsBetweenTicks();
	
	abstract void tick();

}
