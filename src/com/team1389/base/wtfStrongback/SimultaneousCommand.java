package com.team1389.base.wtfStrongback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.strongback.command.Command;

//also part of stupid strongback workwaround where you cant put a sequential command group around a simultaneous one
//WHY DO THESE THINGS HAPPEN TO ME
//I JUST WANT TO PLAY LEAGUE
public class SimultaneousCommand extends Command{
	
	Command[] startCommands;
	List<Command> commands;
	
	public SimultaneousCommand(Command... commands) {
		this.startCommands = commands;
	}
	
	@Override
	public void initialize() {
		this.commands = new ArrayList<Command>(Arrays.asList(startCommands));
		for (Command c: commands){
			c.initialize();
		}
	}

	@Override
	public boolean execute() {
		for (Iterator<Command> iter = commands.iterator(); iter.hasNext();){
			Command c = iter.next();
			boolean isFinished = c.execute();
			if (isFinished){
				c.end();
				iter.remove();
			}
		}
		return commands.isEmpty();
	}
	
	@Override
	public void interrupted() {
		for (Command c : commands){
			c.interrupted();
		}
	}


}
