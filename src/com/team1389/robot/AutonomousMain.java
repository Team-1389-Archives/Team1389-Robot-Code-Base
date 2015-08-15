package com.team1389.robot;

import java.util.AbstractList;
import java.util.ArrayList;

import com.team1389.base.AutonMode;
import com.team1389.base.AutonomousBase;

/**
 * This class defines which autonomous modes are available to be run. The first in the
 * list returned will be the default.
 */
public class AutonomousMain extends AutonomousBase{
	@Override
	public AbstractList<AutonMode> getAutonModes(){
		ArrayList<AutonMode> modes = new ArrayList<AutonMode>();
		//add modes to list here
		return modes;
	}
}
