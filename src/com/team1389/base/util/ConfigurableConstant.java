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
		if (currentValue.equals(randomString)){
			return false;
		} else {
			return true;
		}
	}
	public T get(){
		String value = SmartDashboard.getString(name);
		return serializer.toT(value);
	}
	public void set(T value){
		SmartDashboard.putString(name, serializer.toString(value));
	}
}
