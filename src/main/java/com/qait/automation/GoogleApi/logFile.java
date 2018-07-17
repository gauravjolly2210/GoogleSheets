package com.qait.automation.GoogleApi;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class logFile {

	   /* Get actual class name to be printed on */
	   static Logger log = Logger.getLogger(logFile.class);
	   
	   
	   public static void main(String[] args){
		   String log4jConfPath = "src/main/java/com/qait/automation/GoogleApi/log4j.properties";
		   PropertyConfigurator.configure(log4jConfPath);
		   System.out.println("bfdwq");
		  log.error("error message");
	      log.debug("Hello this is a debug message");
	      log.info("Hello this is an info message");
	      }
}
