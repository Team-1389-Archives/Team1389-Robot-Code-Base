package com.team1389.base.util;

public class DoubleConstant extends Constant<Double>{

	private static class DoubleSerializer implements StringSerializer<Double>{

		@Override
		public String toString(Double value) {
			return Double.toString(value);
		}

		@Override
		public Double toT(String str) {
			return Double.valueOf(str);
		}
		
	}
	public DoubleConstant(String name, Double defaultVal) {
		super(name, defaultVal, new DoubleSerializer());
	}

}
