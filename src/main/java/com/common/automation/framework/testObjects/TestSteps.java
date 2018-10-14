package com.common.automation.framework.testObjects;

import java.util.ArrayList;
import java.util.List;

public class TestSteps {

	private int id;

	private String status;

	private String comment = null;

	private List<Evidences> evidences = new ArrayList<Evidences>();

	public TestSteps() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Evidences> getEvidences() {
		return evidences;
	}

	public void setEvidences(String data) {
		this.evidences.add(new Evidences(data));
	}

	public TestSteps(int id, String status, int index, String step) {

		this.id = id;
		this.status = status;
	}

}
