package retry;

import org.apache.logging.log4j.Logger;
//import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utils.LoggerUtil;

public class RetryListener {

	private static final Logger logger = LoggerUtil.getLogger(RetryListener.class);

	public static void logRetry(ITestResult result, int retryCount) {

		logger.warn("Retrying test: {} | Retry: {}", result.getMethod().getMethodName(), retryCount);
	}
}
