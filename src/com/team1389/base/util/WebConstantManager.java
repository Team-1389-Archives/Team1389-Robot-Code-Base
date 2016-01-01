package com.team1389.base.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebConstantManager {
	private static WebConstantManager constantManager;
	public static WebConstantManager getInstance(){
		if (constantManager == null){
			constantManager = new WebConstantManager();
		}
		return constantManager;
	}
	
	
	Map<String, String> constants;
	
	private WebConstantManager() {
		constants = new HashMap<String, String>();
	}
	public void setConstant(String name, String value){
		constants.put(name, value);
	}
	public String getConstant(String name){
		return constants.get(name);
	}
	public Map<String, String> getAllConstants(){
		return Collections.unmodifiableMap(constants);
	}
	
}
