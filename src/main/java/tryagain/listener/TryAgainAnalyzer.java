package tryagain.listener;

import java.util.ArrayList;
import java.util.List;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.paypal.test.utilities.logging.SimpleLogger;

import tryagain.objects.TestNGXmlSuiteData;

/**
 * 
 * @author tmanjunath This method will be invoked when a test mehtod fails and
 *         re-runs specified number of times
 * 
 */
public class TryAgainAnalyzer implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 0;
	private SimpleLogger logger;
	private static List<ITestResult> failedResults = new ArrayList<ITestResult>();

	public static List<ITestResult> getFailedResults() {
		return failedResults;
	}

	public boolean retry(ITestResult result) {
		if (TestNGXmlSuiteData.getInstance().getRetrycount() != null) {
			maxRetryCount = Integer.parseInt(TestNGXmlSuiteData.getInstance().getRetrycount());
			if (retryCount < maxRetryCount) {
				String message = "TestCase : " + result.getName()
						+ " failed. Hence FaledTestRetry is triggered. Retry count is " + retryCount + 1;
				logger.warning(message);
				failedResults.add(result);
				retryCount++;
				return true;
			}
		}
		retryCount = 0;
		return false;
	}
}
