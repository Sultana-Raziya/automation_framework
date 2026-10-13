package retry;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryTest {

	private static int attempts = 0;

	@Test(groups = { "regression" }, retryAnalyzer = RetryAnalyzer.class)
	public void retryDemo() {

		attempts++;

		System.out.println("Attempt number: " + attempts);

		Assert.assertTrue(attempts >= 3, "Intentional failure for retry testing");
	}
}
