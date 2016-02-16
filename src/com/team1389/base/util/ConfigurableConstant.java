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
	public String oldValue;
	public StringSerializer<T> serializer;
	
	public ConfigurableConstant(String name, T defaultVal, StringSerializer<T> serializer){
		this.name = name;
		this.serializer = serializer;
		System.out.println("setting...");
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
		String usedValue;
		if (webValue != null && !webValue.equals(oldValue)){
			if (!dashboardValue.equals(oldValue)){
				System.out.println("warning: constant " + name + " set from both smartdashboard and"
						+ "web dashboard, using value from webdashboard.");
			}
			usedValue = webValue;
		} else {
			usedValue = dashboardValue;
		}
		
		T value;
		try{
			value = serializer.toT(usedValue);
		} catch (Exception e){
			System.out.println("error reading constant " + name + ": " + e.getMessage());
			//TODO: display that fact that there was an error to user in some way
			value = serializer.toT(oldValue);
		}
		set(value);
		return value;
	}
	public void set(T value){
		String asString = serializer.toString(value);
		SmartDashboard.putString(name, asString);
		WebConstantManager.getInstance().setConstant(name, asString);
		oldValue = asString;
	}
}
