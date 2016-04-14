package com.team1389.base.util;

/*
 * Can linearly scale a number
 */
public class Scaling {
	double offset;
	double mod;
	double val;
	
	public Scaling(double val) {
		this.val = val;
		offset = 0;
		mod = 1.0;
	}
	
	public void setInputUnitsPerOutputUnit(double mod){
		this.mod = mod;
	}
	
	public void modifyOffsetSoThatCurrentValueIs(double thinkOfThisAsTheCurrentPosition){
		offset = thinkOfThisAsTheCurrentPosition - val;
	}
	
	public void setVal(double val){
		this.val = val;
	}
	
	public double getScaledVal(){
//		return val / mod - offset;
		return (val + offset) / mod;
	}
	
	public static void main (String[] args){
		Scaling s = new Scaling(0);
		s.setVal(5);
		System.out.println(s.getScaledVal());
		s.setInputUnitsPerOutputUnit(2);
		System.out.println(s.getScaledVal());
		s.modifyOffsetSoThatCurrentValueIs(10);
		System.out.println(s.getScaledVal());
		s.setVal(0);
		System.out.println(s.getScaledVal());
	}
}
