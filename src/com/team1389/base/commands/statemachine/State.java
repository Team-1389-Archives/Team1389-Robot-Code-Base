package com.team1389.base.commands.statemachine;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is a command that can run as a state in a state machine. 
 */
public abstract class State extends Command{
	/**
	 * This method runs when the State finishes running, and determines which state will be run next
	 */
	public abstract String nextState();

	/**
	 * @return the name of the state, is how other states can refer to this one
	 */
	public abstract String getName();
}
