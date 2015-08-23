package com.team1389.base.webserver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public abstract class JSONGetServlet<ToClient> extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		
        ToClient respObj = onGet();
        
        Gson gson = new Gson();

        gson.toJson(respObj, resp.getWriter());

	}
	
	abstract ToClient onGet();
	
}
