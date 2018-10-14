package com.common.automation.framework.jiraAPI;

import java.util.HashMap;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import com.common.automation.framework.testObjects.TestRuns;
import com.common.automation.framework.utils.Logging;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * This will get the list of all Test Case keys along with their Test Run ID
 * linked to the Test Execution in the form of HashMap
 * 
 * @author vibhor
 *
 */
public class TestRunsMapper extends XrayAuthentication {

	private HashMap<String, TestRuns> testRunClassMapper;
	private static Logging logger = new Logging();

	/**
	 * 
	 * @param testExecutionID - Test Execution key
	 * @return {@link HashMap}
	 * @throws AuthenticationException
	 */
	public HashMap<String, TestRuns> getTestRunJson(String testExecutionID) throws AuthenticationException {

		try {

			Response response = authentication().request(Method.GET, "/testexec/" + testExecutionID + "/test");

			List<Integer> ranks = JsonPath.from(response.getBody().asString()).get("rank");

			testRunClassMapper = new HashMap<String, TestRuns>();
			for (int rank : ranks) {

				int counter = rank - 1;
				String key = response.path("[" + counter + "].key").toString();
				TestRuns runs = new TestRuns();
				runs.setTestCaseKey(key);
				runs.setTestCaseID(response.path("[" + counter + "].id").toString());

				testRunClassMapper.put(key, runs);
			}
		} catch (Exception e) {

			logger.logError("Error retrieving test execution details", e);
			return null;

		}

		if (testRunClassMapper.size() == 0)
			logger.logWarn("No test cases are linked with test execution - " + testExecutionID);

		else
			logger.logInfo("Test cases successfully mapped from test execution - " + testExecutionID);

		return testRunClassMapper;
	}
}
