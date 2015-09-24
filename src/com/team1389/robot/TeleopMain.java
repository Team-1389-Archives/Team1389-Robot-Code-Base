package com.team1389.robot;

import com.team1389.base.TeleopBase;
import com.team1389.robot.commands.TeleopDrive;
import com.team1389.robot.commands.TeleopElevator;

import edu.wpi.first.wpilibj.buttons.Button;

public class TeleopMain extends TeleopBase{
	/**
	 * This function is used to setup which command gets run when the driver presses which button.
	 */
	@Override
	public void setupCommands(){
		//add code here to setup what happens when you press joystick buttons. For example:
		//Inputs.aButton.whenPressed(new ExtendArmCommand());
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		new TeleopDrive().start();
		new TeleopElevator().start();
	}
}
