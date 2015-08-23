package com.team1389.base.webserver;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.xml.sax.HandlerBase;

import com.team1389.base.BaseConstants;

public class WebServer {

	Server server;
	ServletContextHandler servletHandler;

	public WebServer(){
		server = new Server(5800);//5800 is one of the ports FRC allows us to use for our own purposes
		servletHandler = new ServletContextHandler();
		servletHandler.setContextPath("/servlet");
		
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase(BaseConstants.resourceFolder + "/webapp");
		
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.addHandler(servletHandler);
		handlerCollection.addHandler(resourceHandler);
		
		server.setHandler(handlerCollection);
		
		addServlet("/autonModes", new AutonModesServlet());
	}

	public void addServlet(String path, Servlet servlet){
		ServletHolder holder = new ServletHolder(servlet);
		servletHandler.addServlet(holder, path);
	}

	public void start(){
		Thread serverThread = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("server thread running...");

				try {
					server.start();
					server.join();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}});

		serverThread.start();
	}
}
