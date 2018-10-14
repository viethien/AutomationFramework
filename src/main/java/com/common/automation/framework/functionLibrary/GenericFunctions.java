package com.common.automation.framework.functionLibrary;

import java.util.List;

import org.testng.Assert;

public class GenericFunctions {

	public void assertListOrdered(List<String> expected, List<String> actual) {

		Assert.assertEquals(expected.size(), actual.size(), "Size of expected and actual lists did not matched");

		String comparisionErrors = "";
		int index = 0;
		for (String value : expected) {

			if (!value.trim().equals(actual.get(index).trim()))

				comparisionErrors = comparisionErrors + "Expected [" + value.trim() + "] but found ["
						+ actual.get(index).trim() + "]\n";

			index++;
		}

		Assert.assertTrue(comparisionErrors.length() == 0, comparisionErrors);

	}

	public void assertListOrdered(String[] expected, List<String> actual) throws Exception {

		Assert.assertEquals(expected.length, actual.size(), "Size of expected and actual lists did not matched");

		String comparisionErrors = "";
		int index = 0;
		for (String value : expected) {

			if (!value.trim().equals(actual.get(index).trim()))

				comparisionErrors = comparisionErrors + "Expected [" + value.trim() + "] but found ["
						+ actual.get(index).trim() + "]\n";

			index++;
		}

		if (!(comparisionErrors.length() == 0)) {

			throw new AssertionError(comparisionErrors);
		}

	}

	public void assertListOrdered(String[] expected, String[] actual) {

		Assert.assertEquals(expected.length, actual.length, "Size of expected and actual lists did not matched");

		String comparisionErrors = "";
		int index = 0;
		for (String value : expected) {

			if (!value.trim().equals(actual[index].trim()))

				comparisionErrors = comparisionErrors + "Expected [" + value.trim() + "] but found ["
						+ actual[index].trim() + "]\n";

			index++;
		}

		if (!(comparisionErrors.length() == 0)) {

			throw new AssertionError(comparisionErrors);
		}
	}

	public void assertListOrdered(List<String> expected, String[] actual) {

		Assert.assertEquals(expected.size(), actual.length, "Size of expected and actual lists did not matched");

		String comparisionErrors = "";
		int index = 0;
		for (String value : expected) {

			if (!value.trim().equals(actual[index].trim()))

				comparisionErrors = comparisionErrors + "Expected [" + value.trim() + "] but found ["
						+ actual[index].trim() + "]\n";

			index++;
		}

		if (!(comparisionErrors.length() == 0)) {

			throw new AssertionError(comparisionErrors);
		}
	}
}