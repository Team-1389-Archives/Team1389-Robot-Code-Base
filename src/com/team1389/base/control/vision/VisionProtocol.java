package com.team1389.base.control.vision;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class VisionProtocol{
	ITable table;
	private final String RUNNING = "running";
	private final String RESULTS = "results";
	
	public VisionProtocol() {
		table = NetworkTable.getTable("vision");
		
		table.putString(RUNNING, "off");
	}
	
	public VisionState getState(){
		String stateName = table.getString(RUNNING, "not connected");
		
		return VisionState.stringToEnum(stateName);
	}
	
	public void setState(VisionState state){
		table.putString(RUNNING, state.name);
	}
	
	public double getResult(){
		String res = table.getString(RESULTS, "none");
		try {
			return Double.parseDouble(res);
		} catch (Throwable t) {
			System.out.println("invalid results field in vision protocol: " + res);
			return 0.0;
		}
	}
}
