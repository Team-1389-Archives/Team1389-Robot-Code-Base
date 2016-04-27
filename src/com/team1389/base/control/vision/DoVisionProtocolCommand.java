package com.team1389.base.control.vision;

import org.strongback.command.Command;

import com.team1389.base.control.OnOffController;

public class DoVisionProtocolCommand extends Command{
	VisionProtocol proto;
	OnOffController lights;
	double result;
	
	public DoVisionProtocolCommand(VisionProtocol proto, OnOffController lights) {
		this.proto = proto;
		this.lights = lights;
		result = 0.0;
	}
	
	@Override
	public void initialize() {
		proto.setState(VisionState.REQUESTED);
	}

	@Override
	public boolean execute() {
		VisionState state = proto.getState();
		if (state == VisionState.LIGHTS){
			lights.set(true);
			proto.setState(VisionState.LIGHTS_ON);
			return false;
		} else if (state == VisionState.DONE){
			result = proto.getResult();
			lights.set(false);
			return true;
		} else if (state == VisionState.ERROR){
				System.out.println("recieved a vision state error in DoVisionProtocolCommand");
				result = 0.0;
				return true;
		} else {
			return false;
		}
	}
	
	public double getResult(){
		return result;
	}
}

