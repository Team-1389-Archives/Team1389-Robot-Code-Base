/**
 * This package contains things to run a webserver on the roborio.
 * This allows information to be exchanged between the roborio and the driver's station using HTTP.
 * 
 * How It Works:
 * The webserver uses the Jetty server framework. The main code is located in the WebServer class,
 * and everything alse is called from there.
 * 
 * How To Connect To It:
 * If the code is running on a roborio, make sure that your computer is connected to the router and put the following url in your browser:
 * 10.13.89.2:5800
 * 
 * If the code is running in a simulator, try both if the following URLs:
 * 0.0.0.0:5800
 * localhost:5800
 */
package com.team1389.base.webserver;