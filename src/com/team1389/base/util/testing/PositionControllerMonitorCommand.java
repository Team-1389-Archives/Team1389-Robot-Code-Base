package com.team1389.base.util.testing;

import org.strongback.command.Command;

import com.team1389.base.util.Timer;
import com.team1389.base.wpiWrappers.PositionController;

public class PositionControllerMonitorCommand extends Command {
	private final double TIME_BETWEEN_UPDATES = 0.75;
	
	String name;
	PositionController controller;
	Timer timer;
	double lastPos;
	
	public PositionControllerMonitorCommand(PositionController controller, String name) {
		this.name = name;
		this.controller = controller;
		this.timer = new Timer();
	}
	
	@Override
	public void initialize() {
		timer.zero();
		lastPos = controller.getPosition();
	}

	@Override
	public boolean execute() {
		if (timer.get() > TIME_BETWEEN_UPDATES){
			double pos = controller.getPosition();
			double speed = (pos - lastPos) / timer.get();
			System.out.println("Talon " + name + " s: " + speed + "p: " + controller.getPosition());
			lastPos = pos;
			timer.zero();
		}
		return false;
	}

}
