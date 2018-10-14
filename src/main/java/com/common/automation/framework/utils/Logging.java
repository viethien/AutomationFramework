package com.common.automation.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author vibhor
 * 
 *         <p>
 *         The class will initialize logger
 *         </p>
 *
 */
public class Logging {

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
		System.setProperty("current.date", dateFormat.format(new Date()));
		PropertyConfigurator.configure("logging.properties");
	}
	final static Logger log = Logger.getLogger(new Object() {
	}.getClass().getEnclosingClass());

	public void setConfiguration() {

		log.info("Starting framework");

	}

	public void logDebug(String message) {

		log.debug(message);
	}

	public void logInfo(String message) {

		log.info(message);
	}

	public void logError(String message) {

		log.error(message);
	}

	public void logError(String message, Throwable t) {

		log.error(message, t);
	}

	public void logWarn(String message) {

		log.warn(message);
	}

}
