package com.team1389.base.util;

/**
 * This class creates a constant of type T that the user can configure on the SmartDashboard
 * as well as a page in the webserver.
 */
//TODO find a way to make these constants stay saved in a file on the RoboRio and/or Driver station - done
public class ConfigurableConstant<T>{
	public static String randomString = "SDKFJASLDFJASDIFSDKFJjfakdshfaksdfjaoijsfdksj";
	
	public String name;
	public String oldValue;
	public StringSerializer<T> serializer;
	
	private ConfigurableConstant(){} // DON'T TOUCH
	
	public ConfigurableConstant(String name, T defaultVal, StringSerializer<T> serializer){
		this.name = name;
		this.serializer = serializer;
		System.out.println("setting...");
		if (!isSet()){
			set(defaultVal);
		}
	}
	public boolean isSet(){
//		String currentValue = SmartDashboard.getString(name, randomString);
//		boolean onSmartDashboard = !currentValue.equals(randomString);
		boolean onWebConstants = WebConstantManager.getInstance().getConstant(name) != null;
//		return onSmartDashboard || onWebConstants;
		return onWebConstants;
	}
	public T get(){
//		String dashboardValue = SmartDashboard.getString(name);
		String webValue = WebConstantManager.getInstance().getConstant(name);
//		String usedValue;
//		if (webValue != null && !webValue.equals(oldValue)){
//			if (!dashboardValue.equals(oldValue)){
//				System.out.println("warning: constant " + name + " set from both smartdashboard and"
//						+ "web dashboard, using value from webdashboard.");
//			}
//			usedValue = webValue;
//		} else {
//			usedValue = dashboardValue;
//		}
//		
//		T value;
//		try{
//			value = serializer.toT(usedValue);
//		} catch (Exception e){
//			System.out.println("error reading constant " + name + ": " + e.getMessage());
//			//TODO: display that fact that there was an error to user in some way
//			value = serializer.toT(oldValue);
//		}
//		set(value);
//		return value;
		return serializer.toT(webValue);
	}
	public void set(T value){
		String asString = serializer.toString(value);
//		SmartDashboard.putString(name, asString);
		WebConstantManager.getInstance().setConstant(name, asString);
		oldValue = asString;
	}
	
	private static String savingDirectory = "Resources/configurableConstants/";
	
	/**
	 * Writes this <tt>ConfigurableConstant</tt> as a JSON object to <tt>Resources/configurableConstants/[insert constant's name here].json</tt>
	 */
	public void save(){
		JsonFile<ConfigurableConstant<T>> json = new JsonFile<>
			(new java.io.File(savingDirectory+name+".json"), "ConfigurableConstant:"+name, this);
		json.save(this); // Wow, this was really succinct
	}
	
	/**
	 * Loads a <tt>ConfigurableConstant<tt> from <tt>Resources/configurableConstants/[insert constant's name here].json</tt> and assigns its fields to <tt>this</tt>
	 */
	public void load(){
		JsonFile<ConfigurableConstant<T>> json = new JsonFile<>
			(new java.io.File(savingDirectory+name+".json"), "ConfigurableConstant:"+name, this);
		ConfigurableConstant<T> temp;
		try{
			temp = json.load();
		}catch(NullPointerException e){
			System.out.println("The ConfigurableConstant "+name+" was never saved before");
			save();
			return;
		}
		this.oldValue = temp.oldValue;
		this.serializer = temp.serializer;
	}
	/**
	 * @param name
	 * @param typeParameter
	 * @return a <tt>ConfigurableConstant</tt> from <tt>Resources/configurableConstants/[the name parameter].json
	 */
	@SuppressWarnings("unused")
	public static <T_> ConfigurableConstant<T_> getConstantFromFile(String name, Class<T_> typeParameter) {
		ConfigurableConstant<T_> returned = new ConfigurableConstant<>();
		returned.name = name;
		returned.load();
		return returned;
	}
	
	/**
	 * TESTING!!!
	 * @param args 
	 */
	public static void main(String[] args) {
		ConfigurableConstant<Double> constant = new ConfigurableConstant<>();
		constant.name = randomString;
		constant.oldValue = "123456789";
		constant.serializer = new StringSerializer<Double>(){
			@Override public String toString(Double value) { return value.toString(); }
			@Override public Double toT(String str) 	   { return Double.parseDouble(str); }
		};
		constant.save();
	}
}
