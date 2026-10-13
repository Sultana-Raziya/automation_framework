package retry;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utils.LoggerUtil;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static final int MAX_RETRY_COUNT = 2;

	private int retryCount = 0;

	private final Logger logger = LoggerUtil.getLogger(RetryAnalyzer.class);

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < MAX_RETRY_COUNT) {

			retryCount++;

			logger.warn("Retrying test: {} | Attempt: {} of {}", result.getMethod().getMethodName(), retryCount + 1,
					MAX_RETRY_COUNT + 1);

			return true;
		}

		logger.error("Test failed after {} attempts: {}", MAX_RETRY_COUNT + 1, result.getMethod().getMethodName());

		return false;
	}
}
