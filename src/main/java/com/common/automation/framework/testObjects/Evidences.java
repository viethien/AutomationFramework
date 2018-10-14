package com.common.automation.framework.testObjects;

public class Evidences {

	String filename = null;
	String contentType = null;
	String data = null;

	public String getFilename() {

		return filename;
	}

	public String getContentType() {

		return contentType;
	}

	public String getData() {

		return data;
	}

	public Evidences(String data) {

		this.filename = "error.png";
		this.data = data;
		this.contentType = "plain/text";
	}

}
