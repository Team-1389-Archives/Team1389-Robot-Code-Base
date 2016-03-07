package com.team1389.base.wtfStrongback;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.strongback.command.Command;

/**
 * This class exists because as far as i can figure, strongback crashes when creating a
 * Simultaneous command group inside of a sequential one 
 */
public class SequentialCommand extends Command{
	
	Command[] startCommands;
	Stack<Command> commands;
	Command current;
	
	public SequentialCommand(Command...commands) {
		this.startCommands = commands;
	}
	
	@Override
	public void initialize() {
		this.commands = new Stack<Command>();
		for (int i = startCommands.length - 1; i >= 0; i -= 1){
			if (startCommands[i] == null){
				throw new IllegalArgumentException("argument to SequentialCommand can't be null");
			}
			this.commands.push(startCommands[i]);
		}
		nextCommand();
	}
	
	//return true if last command
	private boolean nextCommand(){
		if (current != null){
			current.end();
		}
		if (commands.isEmpty()){
			return true;
		} else {
			current = commands.pop();
			current.initialize();
			return false;
		}
	}
	
	@Override
	public boolean execute() {
		if (current == null){
			return true;
		}

		boolean isFinished = current.execute();
		
		if (isFinished){
			boolean isTotalFinished = nextCommand();
			return isTotalFinished;
		} else {
			return false;
		}
	}
	
	@Override
	public void interrupted() {
		if (current != null){
			current.interrupted();
		}
	}

}
