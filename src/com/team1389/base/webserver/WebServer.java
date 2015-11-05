package com.team1389.base.webserver;

import java.io.File;
import java.net.URL;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.team1389.base.BaseConstants;

/**
 * runs a Jetty Webserver. See package-info.java file for more information.
 */
public class WebServer {

	Server server;
	ServletContextHandler servletHandler;

	public WebServer(){
		
		server = new Server(5800);//5800 is one of the ports FRC allows us to use for our own purposes
		servletHandler = new ServletContextHandler();
		servletHandler.setContextPath("/servlet");
		
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setResourceBase(getAppPath());
		
		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.addHandler(servletHandler);
		handlerCollection.addHandler(resourceHandler);
		
		server.setHandler(handlerCollection);
		
		addServlet("/autonModes", new AutonModesServlet());
		addServlet("/getConstants", new ConstantGetServlet());
		addServlet("/setConstant", new ConstantSetServlet());
	}
	
	/*
	 * Finds the path to where the webapp folder is. The result will be different depending on if this is being run directly from
	 * the ide or from inside a jar file. (if being run from a jar file then the resources are inside the jar file)
	 */
	private String getAppPath(){
		String appDir;
		File dirInIde = new File(BaseConstants.resourceFolder);//this is where the resources are found if it is run from the ide
        URL dirInJar = WebServer.class.getClassLoader().getResource(BaseConstants.webappFolder);//this is where they are found if run from a jar
        if (dirInIde.exists()){
        	appDir = dirInIde.getAbsolutePath() + "/" + BaseConstants.webappFolder;
        } else if (dirInJar != null){
        	appDir = dirInJar.toExternalForm();
        } else {
        	throw new RuntimeException("could not find server resources");
        }
        return appDir;
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
