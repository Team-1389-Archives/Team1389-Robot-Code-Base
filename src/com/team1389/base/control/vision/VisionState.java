package com.team1389.base.control.vision;

public enum VisionState{
	OFF("off"),
	DONE("done"),
	REQUESTED("requested"),
	LIGHTS("lights"),
	LIGHTS_ON("lights on"),
	ERROR("error"),
	NOT_CONNECTED("not connected");

	public final String name;
	
	private VisionState(String name) {
		this.name = name;
	}
	
	public static VisionState stringToEnum(String name){
		for (VisionState state : VisionState.values()){
			if(state.name.equals(name)){
				return state;
			}
		}
		
		return ERROR;
	}
	
}
