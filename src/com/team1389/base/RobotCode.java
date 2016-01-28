package com.team1389.base;

import com.team1389.base.auton.AutonomousBase;

public interface RobotCode{
   	public TeleopBase getTeleopBase();
   	public AutonomousBase getAutonomousBase();
   	public void setup();
}
