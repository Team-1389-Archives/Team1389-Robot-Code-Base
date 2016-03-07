package com.team1389.base.util;

import org.strongback.command.Command;
import org.strongback.command.CommandGroup;

import com.team1389.base.wtfStrongback.SequentialCommand;
import com.team1389.base.wtfStrongback.SimultaneousCommand;

public class CommandsUtil {
	public static Command combineSimultaneous(Command...commands){
//		return new SimultaneousCommands(commands);
		return new SimultaneousCommand(commands);
	}
//	private static class SimultaneousCommands extends CommandGroup{
//		public SimultaneousCommands(Command[] commands) {
//			simultaneously(commands);
//		}
//	}
	
	public static Command combineSequential(Command...commands){
		return new SequentialCommand(commands);
	}
//	private static class SequentialCommands extends CommandGroup{
//		public SequentialCommands(Command[] commands) {
//			sequentially(commands);
//		}
//	}

}
