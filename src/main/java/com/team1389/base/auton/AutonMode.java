package com.team1389.base.auton;

import org.strongback.command.Command;

/**
 * Represents one mode for what the robot can do in the autonomous stage of the competition. 
 */
public interface AutonMode {
	/**
	 * @return the command that gets run when the autonomous mode runs
	 */
	public Command getCommand();
	/**
	 * @return the human readable name of the mode
	 */
	public String getName();
	/**
	 * This function tells the Team1389 base weather or not it should cancel any auton
	 * commands that haven't finished when autonomous mode ends.
	 * 
	 * This function is run when auton mode ends.
	 * The default implementation does cancel commands.
	 * @return true to cancel, false to not cancel.
	 */
	public default boolean shouldCancelCommandsOnAutonEnd(){
		return true;
	}
	
	/**
	 * Runs when autonomous mode ends. Default implementation does nothing.
	 */
	public default void onAutonEnd(){}
}
