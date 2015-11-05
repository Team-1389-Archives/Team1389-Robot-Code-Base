package com.team1389.base.webserver;

import java.util.Map;

import com.team1389.base.util.WebConstantManager;

public class ConstantGetServlet extends JSONGetServlet<ConstantGetServlet.Constants>{
	static class Constants{
		Map<String, String> constants;
		public Constants(Map<String, String> constants){
			this.constants = constants;
		}
	}

	@Override
	Constants onGet() {
		return new Constants(WebConstantManager.getInstance().getAllConstants());
	}
}
