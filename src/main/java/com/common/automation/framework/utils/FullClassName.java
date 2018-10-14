package com.common.automation.framework.utils;

import java.io.File;

/**
 * The class will get the full class name of an automation script according to
 * the Jira Test Case ID
 * 
 * @author vibhor
 *
 */
public class FullClassName {

	private String fullClassName;

	private String fileNameToSearch;

	private String absoluteFilePath;

	/**
	 * This reqires the Jira Test Case Key to search the file and get full class
	 * name
	 * 
	 * @param className
	 * @return {@link String}
	 */
	public String getName(String className) {

		this.fileNameToSearch = className.replace("-", "_") + ".java";

		this.searchDirectory(new File("src\\main\\java\\com\\mydesq\\automation\\test"), className);

		this.fullClassName = absoluteFilePath.replace("\\", ".").split("java.")[1].split(".java")[0];

		return this.fullClassName;

	}

	private void searchDirectory(File directory, String fileNameToSearch) {

		if (directory.isDirectory()) {
			this.search(directory);
		}
	}

	private void search(File file) {

		if (file.isDirectory()) {
			// do you have permission to read this directory?
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						this.search(temp);
					} else {
						if (temp.getName().contains(this.fileNameToSearch)) {
							this.absoluteFilePath = temp.getAbsoluteFile().toString();
							return;
						}
					}
				}
			}
		}
	}
}
