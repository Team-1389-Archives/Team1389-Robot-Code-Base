
package com.team1389.base;

import org.eclipse.jetty.server.Server;

import com.team1389.base.webserver.WebServer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/*
 * This class is the base of all of the code. Everything else is called from here.
 */

public abstract class Team1389RobotBase extends IterativeRobot {
	
	enum Mode{
		AUTON,
		TELEOP,
		TEST,
		DISABLED
	}
	
	private WebServer server;
	
	private Mode mode;
	
   	public abstract RobotCode getCode();
   	
    public void robotInit() {    	
    	RobotCode robotCode = getCode();
    	BaseGlobals.robotCode = new RobotCodeHolder(robotCode);
    	robotCode.setup();
    	BaseGlobals.robotBase = this;

    	BaseGlobals.robotCode.getTeleopBase().setupCommands();

    	Server a = new Server();
    	
    	mode = Mode.DISABLED;
    	
    	//start webserver
    	server = new WebServer();
    	server.start();
    	
    }
    
    @Override
    public void autonomousInit() {
    	mode = Mode.AUTON;
    	BaseGlobals.robotCode.getAutonomousBase().autonStart();
    }
    private void disabledAuton() {
    	BaseGlobals.robotCode.getAutonomousBase().autonEnd();
	}


    @Override
    public void teleopInit() {
    	mode = Mode.TELEOP;
    	BaseGlobals.robotCode.getTeleopBase().start();
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
