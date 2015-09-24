package com.team1389.base.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constant<T>{
	public static String randomString = "SDKFJASLDFJASDIFSDKFJjfakdshfaksdfjaoijsfdksj";
	
	public String name;
	public StringSerializer<T> serializer;
	
	public Constant(String name, T defaultVal, StringSerializer<T> serializer){
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
