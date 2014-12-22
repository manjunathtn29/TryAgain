package tryagain.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.IAnnotationTransformer;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.xml.XmlSuite;

import com.paypal.test.utilities.logging.SimpleLogger;

import tryagain.objects.TestNGXmlSuiteData;
import tryagain.objects.TryAgainParams;

public class TryAgainListener implements IAnnotationTransformer, ISuiteListener, IInvokedMethodListener {
	private String retryCount = null;
	private SimpleLogger logger;

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if (retry == null) {
			annotation.setRetryAnalyzer(TryAgainAnalyzer.class);
		}
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		// Get the values of the configuration parameters retrycount and
		// retryflag before the start of the suite.
		XmlSuite xmlSuite = suite.getXmlSuite();

		retryCount = TryAgainHelper.getConfigParameter(TryAgainParams.RETRY_COUNT, xmlSuite);
		if (retryCount != null && !retryCount.trim().isEmpty()) {
			TestNGXmlSuiteData.getInstance().setRetrycount(retryCount);
		} else {
			logger.info("Default value of retryCount parameter is 0, please set the value for retryCount");

		}
	}

	// This will be invoked to remove duplicate entries from failed tests
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		List<IInvokedMethod> allInvokedMethods = suite.getAllInvokedMethods();
		List<ITestResult> failedResults = TryAgainAnalyzer.getFailedResults();

		logger.info("Removing the duplicate entries from the TestNG failed test results");
		for (IInvokedMethod eachMethod : allInvokedMethods) {
			if (!eachMethod.isTestMethod()) {
				continue;
			}
			for (ITestResult eachResult : failedResults) {
				if (eachResult.equals(eachMethod.getTestResult())) {
					pruneDuplicate(eachResult);
				}
			}
		}
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param result
	 */
	private void pruneDuplicate(ITestResult result) {
		ITestContext ctx = result.getTestContext();
		if (ctx.getFailedTests().getAllResults().contains(result)) {
			ctx.getFailedTests().getAllResults().remove(result);
		}
	}
}
