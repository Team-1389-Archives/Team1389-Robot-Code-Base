
package com.team1389.base;

import java.util.AbstractList;

import com.team1389.base.webserver.WebServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public abstract class Team1389RobotBase extends IterativeRobot {
	enum Mode{
		AUTON,
		TELEOP,
		TEST,
		DISABLED
	}
	
	private WebServer server;
	
	private AutonomousBase autonBase;
	private TeleopBase teleBase;

	private Mode mode;
	
	//auton
	private AbstractList<AutonMode> autonModes;
   	private AutonMode selected;
   	private Command autonCommand;
   	
   	public abstract TeleopBase getTeleopBase();
   	public abstract AutonomousBase getAutonomousBase();
   	public abstract void setup();

    public void robotInit() {
    	setup();
    	autonBase = getAutonomousBase();
    	teleBase = getTeleopBase();
    	teleBase.setupCommands();
    	selectAuton(0);
    	//TODO is next line necessary?
    	mode = Mode.DISABLED;
    	
    	//start webserver
    	server = new WebServer();
    	server.start();
    	
    }
    
	private void selectAuton(int id){
    	autonModes = autonBase.getAutonModes();
    	if(id < autonModes.size()){//for now, need to make a way that it is displayed as not having an auton mode
    		selected = autonModes.get(id);
    		autonCommand = selected.getCommand();
    	}
    }
	
    @Override
    public void autonomousInit() {
    	mode = Mode.AUTON;
    	autonCommand.start();
    }
    private void disabledAuton() {
    	if (selected.shouldCancelCommandsOnAutonEnd()){
    		autonCommand.cancel();
    	}
	}


    @Override
    public void teleopInit() {
    	mode = Mode.TELEOP;
    }
    @Override
    public void testInit(){
    	mode = Mode.TEST;
    }
    public void disabledInit(){
    	switch(mode){
		case AUTON:
			disabledAuton();
			break;
		case DISABLED:
			break;
		case TELEOP:
			break;
		case TEST:
			disabledTest();
			break;
    	}
    	mode = Mode.DISABLED;
    }

	private void disabledTest() {
    	//TODO cancel whatever command was running
	}

	/*Each of there function is called periodically in their respective modes.
     * The scheduler needs to be run so that Commands which are scheduled will run.*/
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }
    public void testPeriodic() {
        LiveWindow.run();//TODO figure out what this is, should it happen in other modes?
        Scheduler.getInstance().run();
    }
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
}
