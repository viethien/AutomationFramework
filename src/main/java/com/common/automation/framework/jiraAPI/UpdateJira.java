package com.common.automation.framework.jiraAPI;

import javax.security.sasl.AuthenticationException;

import com.common.automation.framework.testObjects.TestCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

/**
 * This will update the Test's status, comment and screenshot on Jira
 * 
 * @author vibhor
 *
 */
public class UpdateJira extends XrayAuthentication {

	/**
	 * Updates the status of a Test Run on Jira
	 * 
	 * @param testCaseID - {@link Integer}
	 * @param status     - {@link String}
	 * @throws AuthenticationException
	 */
	public void updateTestCaseStatus(TestCases testCaseDetails, String status, String comment)
			throws AuthenticationException {

		Response response = authentication().request(Method.PUT,
				"/testrun/" + testCaseDetails.getId() + "/status?status=" + status);

		if (status.equals("SKIP")) {

			response = authentication().given().body("{\"comment\":\"" + comment.replace("\\", "/") + "\"}").when()
					.contentType(ContentType.JSON).put("/testrun/" + testCaseDetails.getId());

		}

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Add logger to log for successful comment update
		}
	}

	/**
	 * Updates the status of passed test step
	 * 
	 * @param testCase  - {@link TestCases} object
	 * @param stepIndex - {@link Integer} index of step
	 * @throws AuthenticationException
	 */
	public void updatePassedStep(TestCases testCase, int stepIndex) throws AuthenticationException {

		int testCaseID = testCase.getId();
		int stepID = testCase.getSteps(stepIndex - 1).getId();

		Response response = authentication().request(Method.PUT,
				"/testrun/" + testCaseID + "/step/" + stepID + "/status?status=PASS");

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Add logger to log for successful comment update
		}
	}

	/**
	 * Updates the status of failed test step
	 * 
	 * @param testCase         - {@link TestCases} object
	 * @param stepIndex        - {@link Integer} step index
	 * @param exception        - {@link String} - exection due to which test failed
	 * @param base64ScreenShot - {@link String} - Screenshot in base64 format
	 * @throws JsonProcessingException
	 * @throws AuthenticationException
	 */
	public void updateFailedStep(TestCases testCase, int stepIndex, String exception, String base64ScreenShot)
			throws AuthenticationException {

		int testCaseID = testCase.getId();
		int stepID = testCase.getSteps(stepIndex - 1).getId();

		testCase.getSteps(stepIndex - 1).setComment(exception);
		testCase.getSteps(stepIndex - 1).setStatus("FAIL");
		testCase.getSteps(stepIndex - 1).setEvidences(base64ScreenShot);

		ObjectMapper mapper = new ObjectMapper();
		String stepJsonString = "";
		try {
			stepJsonString = mapper.writeValueAsString(testCase.getSteps(stepIndex - 1));
		} catch (JsonProcessingException e) {

		}

		Response res = authentication().given().body(stepJsonString).when().contentType(ContentType.JSON)
				.put("/testrun/" + testCaseID + "/step/" + stepID);
		int statusCode = res.getStatusCode();

		if (statusCode == 200) {
			// Add logger to log for successful comment update
		}

	}

	/**
	 * Updates the status of skipped test step
	 * 
	 * @param testCase  - {@link TestCases} object
	 * @param stepIndex - {@link Integer} index of step
	 * @throws AuthenticationException
	 */
	public void updateSkippedStep(TestCases testCase, int stepIndex) throws AuthenticationException {

		int testCaseID = testCase.getId();
		int stepID = testCase.getSteps(stepIndex - 1).getId();
		testCase.getSteps(stepIndex).setComment("Comments");

		Response response = authentication().request(Method.PUT,
				"/testrun/" + testCaseID + "/step/" + stepID + "/status?status=SKIP");

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Add logger to log for successful comment update
		}

	}
}
