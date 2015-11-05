package com.team1389.robot;

import java.util.ArrayList;
import java.util.List;

import com.team1389.base.auton.AutonMode;
import com.team1389.base.auton.AutonomousBase;
import com.team1389.robot.commands.DriveForwardCommand;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class defines which autonomous modes are available to be run. The first in the
 * list returned will be the default.
 */
public class AutonomousMain extends AutonomousBase{
	@Override
	public List<AutonMode> provideAutonModes(){
		ArrayList<AutonMode> modes = new ArrayList<AutonMode>();
		//add modes to list here
		
		modes.add(new AutonMode(){

			@Override
			public Command getCommand() {
				return new DriveForwardCommand();
			}

			@Override
			public String getName() {
				return "drive forward";
			}
			
		});
		
		return modes;
	}
}
