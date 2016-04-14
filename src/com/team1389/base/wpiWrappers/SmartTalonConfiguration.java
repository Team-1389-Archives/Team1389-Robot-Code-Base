package com.team1389.base.wpiWrappers;

public abstract class SmartTalonConfiguration {

	private int port;
	private SmartTalonConfiguration(int port){
		this.port = port;
	}
	public int getPort(){
		return port;
	}
	
	
	public SmartTalonConfiguration voltageControl(int port){
		return new VoltageControlTalon(port);
	}
	private static class VoltageControlTalon extends SmartTalonConfiguration{
		public VoltageControlTalon(int port) {
			super(port);
		}
	}
}
