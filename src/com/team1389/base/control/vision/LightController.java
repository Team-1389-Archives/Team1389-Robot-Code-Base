package com.team1389.base.control.vision;

import com.team1389.base.control.OnOffController;

import edu.wpi.first.wpilibj.Solenoid;

public class LightController implements OnOffController{
	Solenoid light;
	
	public LightController(Solenoid light) {
		this.light = light;
	}
	
	@Override
	public void set(boolean onOrOff){
		light.set(onOrOff);
	}
}
