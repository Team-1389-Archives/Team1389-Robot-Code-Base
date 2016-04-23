package com.team1389.base.webserver.vision;

import com.team1389.base.control.OnOffController;
import com.team1389.base.webserver.JSONPostServlet;

public class LightControllerServlet extends JSONPostServlet<LightControllerServlet.OnOrOff, LightControllerServlet.SuccessMessage>{
	public static class OnOrOff{
		public boolean onOrOff;
	}
	
	public static class SuccessMessage{
		boolean success = true;
	}
	
	OnOffController light;
	
	public LightControllerServlet(OnOffController light) {
		this.light = light;
	}

	@Override
	public SuccessMessage onPost(OnOrOff fromClient) {
		light.set(fromClient.onOrOff);
		return new SuccessMessage();
	}

	@Override
	public Class<OnOrOff> whatClassIsFromClient() {
		return OnOrOff.class;
	}
}
