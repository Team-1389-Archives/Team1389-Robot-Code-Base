package com.team1389.base.util;

import org.strongback.command.Command;
import org.strongback.command.CommandGroup;

import com.team1389.base.wtfStrongback.SequentialCommand;
import com.team1389.base.wtfStrongback.SimultaneousCommand;

public class CommandsUtil {
	public static Command combineSimultaneous(Command...commands){
		return new SimultaneousCommand(commands);
	}
	
	public static Command combineSequential(Command...commands){
		return new SequentialCommand(commands);
	}
}
