package com.team1389.base.webserver.chart;

public class DataPoint {
	double x;
	double y;
	public DataPoint(double x, double y){
		this.x = x;
		this.y = y;
	}
	double getX(){
		return x;
	}
	double getY(){
		return y;
	}
}
