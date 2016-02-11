package com.team1389.base.webserver;

import com.team1389.base.util.WebConstantManager;

public class ConstantSetServlet extends JSONPostServlet<ConstantSetServlet.Constant, ConstantSetServlet.Status>{
	static class Constant{ 
		String name;
		String value;
		Constant(String name, String value){
			this.name = name;
			this.value = value;
		}
	}
	static class Status{
		boolean succeeded;
		public Status(boolean succeeded){
			this.succeeded = succeeded;
		}
	}
	@Override
	public Status onPost(Constant fromClient) {
		WebConstantManager.getInstance().setConstant(fromClient.name, fromClient.value);
		return new Status(true);
	}
	@Override
	public Class<Constant> whatClassIsFromClient() {
		return Constant.class;
	}
}
