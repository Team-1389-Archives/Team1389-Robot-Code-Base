
package com.team1389.base;

import java.util.List;

import org.eclipse.jetty.server.Server;

import com.team1389.base.webserver.WebServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/*
 * This class is the base of all of the code. Everything else is called from here.
 */

public abstract class Team1389RobotBase extends IterativeRobot {
	
//	public static void main(String[] args){
//		Command c = new CommandGroup();
//		System.out.println("didnt crash");
//	}
	
	enum Mode{
		AUTON,
		TELEOP,
		TEST,
		DISABLED
	}
	private RobotCode projectSpecificCode;
	
	private WebServer server;
	
	private AutonomousBase autonBase;
	private TeleopBase teleBase;

	private Mode mode;
	
	//auton
	private List<AutonMode> autonModes;
   	private AutonMode selected;
   	private Command autonCommand;
   	
   	public abstract RobotCode getCode();
   	
    public void robotInit() {    	
    	projectSpecificCode = getCode();
    	BaseGlobals.robotCode = projectSpecificCode;
    	BaseGlobals.robotBase = this;

    	//will it crash here?
    	Server a = new Server();
    	
    	
    	projectSpecificCode.setup();
    	autonBase = projectSpecificCode.getAutonomousBase();
    	teleBase = projectSpecificCode.getTeleopBase();
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
    	projectSpecificCode.getTeleopBase().start();
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

	/*Each of these functions is called periodically in their respective modes.
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
