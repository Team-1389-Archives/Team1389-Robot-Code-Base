package com.team1389.base.smartdashboard;

import java.util.List;

import com.team1389.base.RobotCode;
import com.team1389.base.auton.AutonMode;
import com.team1389.base.auton.AutonomousBase;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonModesSmartDashboard {
	final String SELECTION_NAME = "mode";
	
	List<AutonMode> modes;
	
	SendableChooser chooser;
	
	
	public AutonModesSmartDashboard(List<AutonMode> modes, String defaultMode) {
		this.modes = modes;
		
		chooser = new SendableChooser();
		
		for (AutonMode mode : modes){
			chooser.addObject(mode.getName(), mode.getName());
		}
		chooser.addDefault(defaultMode, defaultMode);
		
		SmartDashboard.putData("Auton Mode", chooser);;
	}
	
	public String getSelected(){
		String selected = (String) chooser.getSelected();
		System.out.println("In AutonModesSmardDashboard running mode is " + selected);
		return selected;
	}
}
