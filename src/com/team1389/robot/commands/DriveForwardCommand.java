package com.team1389.robot.commands;

import com.team1389.robot.Globals;
import com.team1389.robot.Subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveForwardCommand extends Command{
	Timer timer;
	
	public DriveForwardCommand() {
		requires(Subsystems.driveTrain);
		timer = new Timer();
	}

	@Override
	protected void initialize() {
		timer.reset();
		Subsystems.driveTrain.set(Globals.autonDriveSpeed.get(), Globals.autonDriveSpeed.get());
		timer.start();
	}

	@Override
	protected void execute() {}

	@Override
	protected boolean isFinished() {
		return timer.get() >= Globals.autonLength.get();
	}

	@Override
	protected void end() {
		Subsystems.driveTrain.set(0.0, 0.0);
		timer.stop();
	}

	@Override
	protected void interrupted() {
		Subsystems.driveTrain.set(0.0, 0.0);
		timer.stop();
	}

}
