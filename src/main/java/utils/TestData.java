package utils;

import config.ConfigReader;

public final class TestData {

	private TestData() {
	}

	public static String getStandardUsername() {

		return ConfigReader.getProperty("standardUsername");
	}

	public static String getStandardPassword() {

		return ConfigReader.getProperty("standardPassword");
	}
}
