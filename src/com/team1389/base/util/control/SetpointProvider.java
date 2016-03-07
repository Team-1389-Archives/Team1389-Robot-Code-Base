package com.team1389.base.util.control;

@FunctionalInterface
public interface SetpointProvider{
	public double getSetpoint();
	public default void init(){}
	public default boolean isFinished(){return false;}
	public default boolean shouldResetPosition(){return true;}
}