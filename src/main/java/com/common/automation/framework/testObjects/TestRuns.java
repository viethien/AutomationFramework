package com.common.automation.framework.testObjects;

/**
 * Stores the values to be passed in Dynamic TestNG suite
 * 
 * @author vibhor
 *
 */
public class TestRuns {

	private String testCaseID;
	private String testCaseKey;

	public String getTestCaseID() {
		return testCaseID;
	}

	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}

	public String getTestCaseKey() {

		return testCaseKey;
	}

	public void setTestCaseKey(String testCaseKey) {

		this.testCaseKey = testCaseKey;
	}

	@Override
	public String toString() {
		return "TestRuns [testCaseID=" + testCaseID + ", testCaseKey=" + testCaseKey;
	}

}
