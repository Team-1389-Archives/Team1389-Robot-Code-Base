package com.team1389.base.wpiWrappers;

import org.strongback.command.Requirable;

/**
 * This interface represents a controller that can directly control its position, like a TalonSRX in position mode.
 * Normally, you would use Strongback for these sorts of things. However, Strongback doesn't currently support
 * position mode on a TalonSRX.
 */
public interface PositionController extends Requirable{
	void setPosition(double position);
	double getPosition();
	void setPID(double p, double i, double d);
	
	public static PositionController compose(PositionController...controllers){
		return new PositionController() {
			
			@Override
			public void setPosition(double position) {
				for (int i = 0; i < controllers.length; ++i){
					controllers[i].setPosition(position);
				}
			}
			
			@Override
			public void setPID(double p, double i, double d) {
				for (int n = 0; i < controllers.length; ++n){
					controllers[n].setPID(p, i, d);
				}
			}
			
			@Override
			public double getPosition() {
				return controllers[0].getPosition();
			}
		};
	}
	
	public static PositionController invert(PositionController controller){
		return new PositionController(){
			@Override
			public void setPosition(double position) {
				controller.setPosition(-1 * position);
			}
			@Override
			public double getPosition() {
				return controller.getPosition() * -1;
			}

			@Override
			public void setPID(double p, double i, double d) {
				controller.setPID(p, i, d);
			}
			
		};
	}
}
