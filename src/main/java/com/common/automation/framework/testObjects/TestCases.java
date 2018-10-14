package com.common.automation.framework.testObjects;

import java.util.ArrayList;
import java.util.List;

public class TestCases {

	private int id;

	private String status;

	private List<TestSteps> steps;

	public TestCases(int id, String status) {

		this.id = id;
		this.status = status;
		this.steps = new ArrayList<TestSteps>();
	}

	public int getId() {

		return id;
	}

	public String getStatus() {

		return status;
	}

	public void setSteps() {

		this.steps.add(new TestSteps());
	}

	public TestSteps getSteps(int testCaseIndex) {

		return steps.get(testCaseIndex);
	}
}
