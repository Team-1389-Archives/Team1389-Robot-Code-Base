package com.team1389.base.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebServer {
	public WebServer(){
		
	}
	
	public void start(){
		Thread serverThread = new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println("server thread running...");
				
				Server server = new Server(5800);//5800 is one of the ports FRC allows us to use for our own purposes
				ServletContextHandler handler = new ServletContextHandler();
				server.setHandler(handler);
				
				ServletHolder index = new ServletHolder("index", new IndexServlet());
				handler.addServlet(index, "");
				
				ServletHolder yolo = new ServletHolder("yolo", new HTMLServlet("web/test.html"));
				handler.addServlet(yolo, "/yolo");
				
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
