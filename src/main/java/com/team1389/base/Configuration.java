package com.team1389.base;

import org.strongback.Logger;
import org.strongback.Strongback;

public class Configuration {
	public static void configureStrongback(){
    	Strongback.configure().recordNoData()
    		.recordNoEvents()
    		.recordNoCommands()
    		.recordNoData()
//    		.useCustomLogger((String name) -> new Configuration.NoLogging())//TODO: this is a sort of hacky way to get rid of
			//annoying "could not execute in 20 milliseconds" messages. Find a better solution later.
    		.initialize();
    	
	}
	
	static private class NoLogging implements Logger{
		@Override
		public void error(Throwable t) {
		}

		@Override
		public void error(Throwable t, String message) {
		}

		@Override
		public void error(String message) {
		}

		@Override
		public void warn(String message) {
		}

		@Override
		public void info(String message) {
		}

		@Override
		public void debug(String message) {
		}

		@Override
		public void trace(String message) {
		}
	}
}
