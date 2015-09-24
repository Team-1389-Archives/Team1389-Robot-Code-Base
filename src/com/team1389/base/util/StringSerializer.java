package com.team1389.base.util;

public interface StringSerializer<T>{
	String toString(T value);
	
	T toT(String str);
}
