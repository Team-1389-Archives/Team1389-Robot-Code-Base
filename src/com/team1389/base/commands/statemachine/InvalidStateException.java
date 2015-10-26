package com.team1389.base.commands.statemachine;

public class InvalidStateException extends RuntimeException{

	public InvalidStateException(String string) {
		super(string);
	}
	
}
