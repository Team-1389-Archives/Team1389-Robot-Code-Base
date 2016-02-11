package com.team1389.base.webserver.chart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebChartManager {
	private static WebChartManager chartManager;
	public static WebChartManager getInstance(){
		if (chartManager == null){
			chartManager = new WebChartManager();
		}
		return chartManager;
	}
	
	Map<String, Chart> charts;

	public WebChartManager() {
		charts = new HashMap<String, Chart>();
	}
	
	public void addChart(String name, Chart chart){
		charts.put(name, chart);
	}
	
	public Map<String, Chart> getAllCharts(){
		return Collections.unmodifiableMap(charts);
	}

}
