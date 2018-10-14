package com.common.automation.framework.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerTestNG implements ITestListener {

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Step Passed: " + result.getName()
				+ (result.getParameters().length > 1 ? ": " + result.getParameters()[1].toString() : ""));

	}

	public void onTestFailure(ITestResult result) {
		System.out.println("Step Failed: " + result.getName()
				+ (result.getParameters().length > 1 ? ": " + result.getParameters()[1].toString() : ""));

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Step Skipped: " + result.getName()
				+ (result.getParameters().length > 1 ? ": " + result.getParameters()[1].toString() : ""));

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println();

	}

	public void onStart(ITestContext context) {
		System.out.println("Test Started: " + context.getName());

	}

	public void onFinish(ITestContext context) {
		System.out.println("Test Finished: " + context.getName());

	}

	public static void main(String[] args) {
		System.out.println();

	}

}
