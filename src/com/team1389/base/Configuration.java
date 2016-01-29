package com.team1389.base;

import org.strongback.Strongback;

public class Configuration {
	public static void configureStrongback(){
    	Strongback.configure().recordNoData()
    		.recordNoEvents()
    		.initialize();
	}
}
