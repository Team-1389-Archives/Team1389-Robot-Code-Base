package com.team1389.base.util.control;

import com.google.gson.Gson;
import com.team1389.base.util.ConfigurableConstant;
import com.team1389.base.util.StringSerializer;

public class ConfigurablePid extends ConfigurableConstant<ConfigurablePid.PIDConstants>{
	public static class PIDConstants{
		public final double p;
		public final double i;
		public final double d;
		public final double kv;
		public final double ka;
		public PIDConstants(double p, double i, double d, double kv, double ka) {
			this.p = p;
			this.i = i;
			this.d = d;
			this.kv = kv;
			this.ka = ka;
		}
	}

	public static class PIDConstantsSerializer implements StringSerializer<PIDConstants>{
		
		Gson gson;
		
		public PIDConstantsSerializer() {
			gson = new Gson();
		}

		@Override
		public String toString(PIDConstants value) {
			return gson.toJson(value);
		}

		@Override
		public PIDConstants toT(String str) {
			return gson.fromJson(str, PIDConstants.class);
		}
		
	}

	public ConfigurablePid(String name, PIDConstants defaultVal) {

		super(name, defaultVal, new PIDConstantsSerializer());
		// TODO Auto-generated constructor stub
	}
}
