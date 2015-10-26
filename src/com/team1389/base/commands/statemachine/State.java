package com.team1389.base.commands.statemachine;

import edu.wpi.first.wpilibj.command.Command;

public interface State {
	/**
	 * @return command that runs during this state
	 */
	Command getCommand();
	
	/**
	 * @return the name of the state that should be run next or null if no state should run
	 */
	String nextState();
	
	/**
	 * @return name of state
	 */
	String getName();
}
