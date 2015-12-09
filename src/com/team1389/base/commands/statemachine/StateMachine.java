package com.team1389.base.commands.statemachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class creates a command that represents a state machine.
 */
public abstract class StateMachine extends Command{
	
	Map<String, State> states;
	State state;
	Command currentCommand;
	boolean finished;
	
	@Override
	protected void initialize() {
		finished = false;
		states = new HashMap<String, State>();
		List<State> stateList = getStates();
		System.out.println("initializing state machine, states are:");
		for (State s : stateList){
			states.put(s.getName(), s);
			System.out.println(s.getName());
		}
		initializeState(startState());
	}

	@Override
	protected void execute() {
		if(!currentCommand.isRunning()){
			nextState();
		}
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
	private void nextState(){
		String nextStateName = state.nextState();
		if (nextStateName == null){
			finished = true;
		} else {
			initializeState(nextStateName);
		}
	}
	
	private void initializeState(String name){
		state = states.get(name);
		if (state == null){
			throw new InvalidStateException(name + " is not a valid state name");
		}
		currentCommand = state;
		currentCommand.start();
	}
	
	public abstract List<State> getStates();
	public abstract String startState();

}
