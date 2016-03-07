package com.team1389.base.wpiWrappers;

import org.strongback.command.Requirable;

import com.team1389.base.util.control.ConfigurablePid.PIDConstants;

/**
 * This interface represents a controller that can directly control its position, like a TalonSRX in position mode.
 * Normally, you would use Strongback for these sorts of things. However, Strongback doesn't currently support
 * position mode on a TalonSRX.
 */
public interface PositionController extends Requirable{
	void setPosition(double position);
	void setCurrentPositionAs(double as);
	double getPosition();
	void setPID(PIDConstants constants);
	void disable();
}
