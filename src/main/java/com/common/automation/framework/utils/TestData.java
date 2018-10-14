package com.common.automation.framework.utils;

import java.io.FileReader;
import java.util.HashMap;

import com.opencsv.CSVReader;

/**
 * The class contains the test data to be passed or selected
 * 
 * @author vibhor
 *
 */
public final class TestData {

	private HashMap<String, String> genericTestData;
	private Object[][] testSpecificTestData;
	private static Logging logger = new Logging();

	/**
	 * This will retun the test data in key value pair
	 * 
	 * @param filePath - path of test data file
	 * @return {@link HashMap}
	 */
	public HashMap<String, String> getGenericTestData(String fileName) {

		genericTestData = new HashMap<String, String>();

		try {
			FileReader filereader = new FileReader("testData\\" + fileName);

			// create csvReader object passing
			// file reader as a parameter
			@SuppressWarnings("resource")
			CSVReader csvReader = new CSVReader(filereader);
			String[] record;

			while ((record = csvReader.readNext()) != null) {
				genericTestData.put(record[0], record[1]);
			}
		} catch (Exception e) {

			logger.logError("Unable to read test data csv", e);
			return null;
		}

		if (genericTestData.size() == 0)
			logger.logWarn("No data found in test data csv");

		else
			logger.logInfo("Data retrieved from test data csv successfully");

		return genericTestData;
	}

	/**
	 * This will retun the test data in key value pair
	 * 
	 * @param filePath - path of test data file
	 * @return {@link StringArrays}
	 */
	public Object[][] getTestSpecificTestData(String fileName) {

		int counter = 0;
		try {
			FileReader filereader = new FileReader(fileName);

			// create csvReader object passing
			// file reader as a parameter
			@SuppressWarnings("resource")
			CSVReader csvReader = new CSVReader(filereader);
			String[] record;

			// we are going to read data line by line
			while ((record = csvReader.readNext()) != null) {
				testSpecificTestData[counter] = record;
			}
		} catch (Exception e) {

			logger.logError("Unable to read test data csv", e);
			return null;
		}

		if (genericTestData.size() == 0)
			logger.logWarn("No data found in test data csv");

		else
			logger.logInfo("Data retrieved from test data csv successfully");

		return testSpecificTestData;
	}
}
