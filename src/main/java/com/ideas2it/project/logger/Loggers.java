package com.ideas2it.project.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggers {

	private Logger logger;
	
	public Loggers(Class<?> className) {
		logger = LogManager.getLogger(className);
	}
	
	/**
	 * 
	 * @param message
	 */
	public void logError(String message, Object e) {
		logger.error(message, e);
	}
	
	/**
	 * 
	 * @param message
	 */
	public void logInfo(String message) {
		logger.info(message);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void logDebug(Object e) {
		logger.debug(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void logWarn(Object e) {
		logger.warn(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void logFatal(Object e) {
		logger.fatal(e);
	}
	
	/**
	 * 
	 * @param e
	 */
	public void logInfo(Object e) {
		logger.info(e);
	}
}
