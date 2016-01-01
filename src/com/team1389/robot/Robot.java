package com.team1389.robot;

import com.team1389.base.RobotCode;
import com.team1389.base.Simulator;
import com.team1389.base.TeleopBase;
import com.team1389.base.auton.AutonomousBase;
import com.team1389.base.util.WebChartManager;
import com.team1389.base.util.WebConstantManager;
import com.team1389.base.webserver.chart.Chart;
import com.team1389.base.webserver.chart.DataPoint;

/**
 * This class defines where the teleop and auton bases are.
 * The code in this file wony usually have to be changed.
 */
public class Robot implements RobotCode{

	public TeleopBase getTeleopBase() {
		return new TeleopMain();
	}

	public AutonomousBase getAutonomousBase() {
		return new AutonomousMain();
	}

	@Override
	public void setup() {
		Inputs.setup();
		Subsystems.setup();
		Globals.setup();
		System.out.println("Robot is initialized");
	}

	//for running a lightweight simulation
	public static void main(String[] args){
		Simulator.simulate(new Robot());
		
		
		WebConstantManager.getInstance().setConstant("yolo", "swag");
		
		
		Chart test = new Chart(10, 10, "bloops", "blops");
		test.addPoint(new DataPoint(1, 10));
		test.addPoint(new DataPoint(9, 10));
		test.addPoint(new DataPoint(2, 10));
		test.addPoint(new DataPoint(4, 6));
		test.addPoint(new DataPoint(7, 10));
		test.addPoint(new DataPoint(2, 10));
		test.addPoint(new DataPoint(9, 10));
		test.addPoint(new DataPoint(3, 0));
		test.addPoint(new DataPoint(2, 23));
		test.addPoint(new DataPoint(9, 10));
		test.addPoint(new DataPoint(4, 10));
		test.addPoint(new DataPoint(8, 10));
		WebChartManager.getInstance().addChart("testy", test);
	}
}
