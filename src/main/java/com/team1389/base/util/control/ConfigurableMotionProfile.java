package com.team1389.base.util.control;

import com.google.gson.Gson;
import com.team1389.base.util.ConfigurableConstant;
import com.team1389.base.util.StringSerializer;

public class ConfigurableMotionProfile extends ConfigurableConstant<ConfigurableMotionProfile.ProfileConstants>{
	public static class ProfileConstants{
		double kv, ka;
		public ProfileConstants(double kv, double ka) {
			this.kv = kv;
			this.ka = ka;
		}
		public double getKv(){return kv;}
		public double getKa(){return ka;}
	}

	public static class ProfileConstantsSerializer implements StringSerializer<ConfigurableMotionProfile.ProfileConstants>{
		
		Gson gson;
		
		public ProfileConstantsSerializer() {
			gson = new Gson();
		}

		@Override
		public String toString(ProfileConstants value) {
			return gson.toJson(value);
		}

		@Override
		public ProfileConstants toT(String str) {
			return gson.fromJson(str, ProfileConstants.class);
		}
	}

	public ConfigurableMotionProfile(String name, ProfileConstants defaultVal) {

		super(name, defaultVal, new ProfileConstantsSerializer());
	}
}
