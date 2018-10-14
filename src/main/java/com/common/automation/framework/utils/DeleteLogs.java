package com.common.automation.framework.utils;

import java.io.File;
import java.util.TreeSet;

/**
 * 
 * @author vibhor
 *
 */
public class DeleteLogs {

	private static Logging logger = new Logging();
	TreeSet<File> listOfLogFiles = null;
	File folder = null;
	File[] listOfFiles = null;;

	public void deleteFiles(String pathOfDirectory) {

		try {

			listOfLogFiles = new TreeSet<File>();
			folder = new File(pathOfDirectory);
			listOfFiles = folder.listFiles();
		} catch (Exception e) {

			logger.logError("Not able to get list of files", e);
		}
		if (listOfFiles.length > 10) {
			for (File file : listOfFiles) {

				listOfLogFiles.add(file);
			}

			try {
				while (listOfLogFiles.size() > 10) {

					listOfLogFiles.pollFirst().delete();
					logger.logInfo("Log file deleted successfully");
				}
			} catch (Exception e) {

				logger.logError("Error deleting log file", e);
			}
		}
	}
}
