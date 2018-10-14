package com.common.automation.framework.jiraAPI;

import javax.security.sasl.AuthenticationException;

import com.mydesq.automation.framework.utils.Decrypter;
import com.mydesq.automation.framework.utils.Logging;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

/**
 * To perform the basic authentication process of Jira
 * 
 * @author vibhor
 *
 */
public abstract class XrayAuthentication {

	private static Logging logger = new Logging();
	private RequestSpecification httpRequest;

	/**
	 * The API request need to be appended with the authentication.
	 * 
	 * @return {@link RequestSpecification}
	 * @throws AuthenticationException
	 */
	protected RequestSpecification authentication() throws AuthenticationException {

		try {
			RestAssured.baseURI = "http://localhost:8080/rest/raven/1.0/api";

			this.httpRequest = RestAssured.given().auth().preemptive().basic("admin",
					(new Decrypter()).decrypt("L86vqDwSFOjj6RzM+OMv0A=="));
		} catch (Exception e) {

			logger.logError("Failed to authenticate Jira for Xray", e);
			throw new AuthenticationException("Failed to authenticate Jira");
		}
		return httpRequest;

	}
}
