package com.ideas2it.project.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggers {

	private Logger log;
	
	public Loggers(Class<?> className) {
		log = LogManager.getLogger(className);
	}
	
	public void logError(String message, Object error) {
		log.error(message, error);
	}
	
	public void logError(String message) {
		log.error(message);
	}
	
	public void logInfo(String message) {
		log.info(message);
	}
	
	public void logDebug(String message) {
		log.debug(message);
	}
}