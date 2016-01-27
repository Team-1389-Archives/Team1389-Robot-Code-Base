package com.team1389.base.webserver.chart;

import java.util.Map;

import com.team1389.base.webserver.JSONGetServlet;

public class ChartGetServlet extends JSONGetServlet<Map<String, Chart>>{
	@Override
	public Map<String, Chart> onGet() {
		return WebChartManager.getInstance().getAllCharts();
	}

}
