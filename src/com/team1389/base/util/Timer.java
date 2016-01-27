package com.team1389.base.util;

import org.strongback.Strongback;

public class Timer{
	double startSeconds;
	
	public Timer() {
		zero();
	}
	
	public void zero(){
		startSeconds = getClockSeconds();
	}
	
	public double get(){
		return getClockSeconds() - startSeconds;
	}
	
	
	public static double getClockSeconds(){
		double nanos = (double) Strongback.timeSystem().currentTimeInNanos();
		return nanos / 1000000000.0;
	}
}
