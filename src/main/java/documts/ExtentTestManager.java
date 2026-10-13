package documts;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>(); // it is used to support parallel
																					// execution

	private ExtentTestManager() {
	}

	public static void setTest(ExtentTest test) {
		extentTest.set(test);
	}

	public static ExtentTest getTest() {
		return extentTest.get();
	}

	public static void removeTest() {
		extentTest.remove();
	}
}
