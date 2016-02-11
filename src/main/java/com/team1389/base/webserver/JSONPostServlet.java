package com.team1389.base.webserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class JSONPostServlet<FromClient, ToClient> extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		
        Gson gson = new Gson();
        
        java.lang.reflect.Type type = new TypeToken<FromClient>(){}.getType();
//        FromClient reqObj = gson.fromJson(req.getReader(), type);
        FromClient reqObj = gson.fromJson(req.getReader(), whatClassIsFromClient());
        
        ToClient respObj = onPost(reqObj);
        
        gson.toJson(respObj, resp.getWriter());
	}
	
	abstract public ToClient onPost(FromClient fromClient);
	abstract public Class<FromClient> whatClassIsFromClient();
}