package com.team1389.base.util;

/**
 * This class creates a constant of type Double that can be set by the user
 */
public class DoubleConstant extends ConfigurableConstant<Double>{

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
