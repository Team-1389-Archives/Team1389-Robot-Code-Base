package com.team1389.base.util;

/**
 * This class defines how to convert type T to and from a string.
 * 
 * It is used so that any information that the user might need to set can be entered by the user
 * as a String as well as displayed as a string.
 */
public interface StringSerializer<T>{
	String toString(T value);
	
	T toT(String str);
}
