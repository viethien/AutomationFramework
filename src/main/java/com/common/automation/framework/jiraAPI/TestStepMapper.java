package com.common.automation.framework.jiraAPI;

import java.util.HashMap;

import javax.security.sasl.AuthenticationException;

import com.common.automation.framework.testObjects.TestCases;
import com.common.automation.framework.testObjects.TestSteps;
import com.common.automation.framework.utils.Logging;

import io.restassured.http.Method;
import io.restassured.response.Response;

/**
 * This maps the Test Case and Test Step details with {@link TestCases} and
 * {@link TestSteps} and extends {@link XrayAuthentication}
 * 
 * @author vibhor
 *
 */
public class TestStepMapper extends XrayAuthentication {

	HashMap<Integer, String> stepsDataProvider;
	private static Logging logger = new Logging();

	/**
	 * Pass the Test Run ID to get the Test Case and Step Details and map them with
	 * the respective classes
	 * 
	 * @param testRunID - {@link Integer}
	 * @return {@link TestCases} object
	 * @throws AuthenticationException
	 */
	public TestCases getTestCaseDetails(int testRunID) throws AuthenticationException {

		boolean condition = true;
		int index = 0;
		stepsDataProvider = new HashMap<Integer, String>();
		int id = testRunID;
		TestCases testCase;

		try {
			Response response = authentication().request(Method.GET, "/testrun/" + testRunID);

			String status = response.path("status").toString();

			testCase = new TestCases(id, status);

			while (condition) {

				int stepID = 0;
				int stepIndex = 0;
				String stepStatus = null;
				String stepDescription = null;

				testCase.setSteps();
				TestSteps step = testCase.getSteps(index);

				try {

					stepID = response.path("steps[" + index + "].id");
				} catch (NullPointerException e) {

					break;
				}
				stepStatus = response.path("steps[" + index + "].status");
				step.setId(stepID);
				step.setStatus(stepStatus);

				stepDescription = response.path("steps[" + index + "].step.raw");
				stepIndex = response.path("steps[" + index + "].index");

				setStepsDataProvider(stepIndex, stepDescription);

				index++;

			}
		} catch (Exception e) {

			logger.logError(
					"Error retrieving test case details for " + testRunID + ". Test status will not be udated on Jira.",
					e);
			return null;
		}
		return testCase;
	}

	/**
	 * Data to be passed in data provider.
	 * 
	 * @return {@link HashMap}
	 */
	public HashMap<Integer, String> getStepsDataProvider() {
		return stepsDataProvider;
	}

	private void setStepsDataProvider(int index, String stepDescription) {
		this.stepsDataProvider.put(index, stepDescription);
	}

}
