package com.team1389.base;

import java.util.ArrayList;
import java.util.Arrays;

import org.strongback.command.Requirable;

public class Subsystem implements Requirable{
	ArrayList<Requirable> required;
	public Subsystem(Object...required) {
		this.required = new ArrayList<Requirable>();
		for (Object requirement : required){
			if (requirement instanceof Requirable){
				this.required.add((Requirable)requirement);
			} else if (requirement instanceof Subsystem){
				Subsystem ss = (Subsystem) requirement;
				this.required.addAll(Arrays.asList(ss.getRequirements()));
			} else {
				throw new RuntimeException("Arguments to Subsystem constructor must be either Requireable or Subsystem");
			}
		}
	}
	
	public Requirable[] getRequirements(){
		return (Requirable[]) required.toArray();
	}
}
