package com.team1389.base;

import org.strongback.command.Requirable;

public class Subsystem implements Requirable{
	Requirable[] required;
	
	public Subsystem(Requirable...required) {
		this.required = required;
	}
	
	public Requirable[] getRequirements(){//TODO: is broken
		return required;
	}
}
