package com.team1389.base.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class creates a constant of type T that the user can configure on the SmartDashboard
 * as well as a page in the webserver.
 */
//TODO find a way to make these constants stay saved in a file on the RoboRio and/or Driver station
public class ConfigurableConstant<T>{
	public static String randomString = "SDKFJASLDFJASDIFSDKFJjfakdshfaksdfjaoijsfdksj";
	
	public String name;
	public StringSerializer<T> serializer;
	
	public ConfigurableConstant(String name, T defaultVal, StringSerializer<T> serializer){
		this.name = name;
		this.serializer = serializer;
		if (!isSet()){
			set(defaultVal);
		}
	}
	public boolean isSet(){
		String currentValue = SmartDashboard.getString(name, randomString);
		boolean onSmartDashboard = !currentValue.equals(randomString);
		boolean onWebConstants = WebConstantManager.getInstance().getConstant(name) != null;
		return onSmartDashboard || onWebConstants;
	}
	public T get(){
		String dashboardValue = SmartDashboard.getString(name);
		String webValue = WebConstantManager.getInstance().getConstant(name);
		if (webValue != null){
			if (dashboardValue != null){
				System.out.println("warning: constant " + name + " set from both smartdashboard and"
						+ "web dashboard, using value from webdashboard.");
			}
			return serializer.toT(webValue);
		} else {
			return serializer.toT(dashboardValue);
		}
	}
	public void set(T value){
		String asString = serializer.toString(value);
		SmartDashboard.putString(name, asString);
		WebConstantManager.getInstance().setConstant(name, asString);
	}
}
