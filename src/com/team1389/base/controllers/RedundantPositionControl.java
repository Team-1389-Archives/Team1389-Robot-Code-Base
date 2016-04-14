package com.team1389.base.controllers;

import com.team1389.base.util.Scaling;
import com.team1389.base.util.control.ConfigurablePid.PIDConstants;
import com.team1389.base.wpiWrappers.PositionController;
import com.team1389.base.wpiWrappers.SmartCANTalon;
import com.team1389.base.wpiWrappers.SmartTalonConfiguration;

public class RedundantPositionControl implements PositionController{
	SmartCANTalon[] talons;
	
	int whosInCharge;
	
	Scaling scaling;

	public RedundantPositionControl(SmartCANTalon[] talons, double ticksPerRotation) {
		this.talons = talons;
		whosInCharge = 0;
		if (!configureWhosInCharge()){
			System.out.println("no talons work in RedundantPositionControl on initialize!");
		}

		scaling = new Scaling(0.0);
		scaling.setInputUnitsPerOutputUnit(ticksPerRotation);
		for (SmartCANTalon talon : talons){
			talon.setSensorPosition(0);
		}
	}
	
	private SmartCANTalon getControlTalon(){
		return talons[whosInCharge];
	}
	
	private void setupFollowers(){
		int mainId = talons[whosInCharge].getId();
		for (int i = 0; i < talons.length; i += 1){
			if (i != whosInCharge){
				talons[i].setFollowing(mainId);
			}
		}
	}
	
	private boolean configureWhosInCharge(){
		boolean anyWork = false;
		int works = 0;
		for (int i = 0; i < talons.length; i += 1){
			if (talons[i].isEncoderConnected()){
				anyWork = true;
				works = i;
				System.out.println(i + " works");
				break;
			}
		}

		if (anyWork){
			whosInCharge = works;
		} else {
			for (SmartCANTalon talon : talons){
				talon.setPVoltage(0);
			}
		}
		
		setupFollowers();

		return anyWork;
	}
	
	private void updateFollowerEncoderPositions(){
		double pos = getControlTalon().getPosition();
		for (int i = 0; i < talons.length; i += 1){
			if (i != whosInCharge){
				talons[i].setSensorPosition(pos);
			}
		}
	}
	
	public void setPosition(double position){
		if (configureWhosInCharge()){
			getControlTalon().setPosition(position);
			updateFollowerEncoderPositions();
		} else {
			System.out.println("no encoders are connected!");
		}
	}
	
	public SmartCANTalon getTalonInCharge(){
		configureWhosInCharge();
		return getControlTalon();
	}

	@Override
	public void setCurrentPositionAs(double as) {
		SmartCANTalon talon = getTalonInCharge();
		scaling.setVal(talon.getPosition());
		scaling.modifyOffsetSoThatCurrentValueIs(as);
	}

	@Override
	public double getPosition() {
		double pos = getTalonInCharge().getPosition();
		updateFollowerEncoderPositions();
		return pos;
	}

	@Override
	public void setPID(PIDConstants constants) {
		for (SmartCANTalon talon : talons){
			talon.setPID(constants);
		}
	}

	@Override
	public void disable() {
		getTalonInCharge().setPVoltage(0);
	}
}
