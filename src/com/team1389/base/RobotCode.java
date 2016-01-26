package com.team1389.base;

import com.team1389.base.auton.AutonomousBase;

public interface RobotCode<IOLayoutType extends IO>{
   	public TeleopBase<IOLayoutType> getTeleopBase();
   	public AutonomousBase<IOLayoutType> getAutonomousBase();
   	public IOLayoutType getIO();
   	public void setup();
}
