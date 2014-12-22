package tryagain.objects;


public final class TestNGXmlSuiteData {

	private static TestNGXmlSuiteData testNGXmlSuiteData = null;
	private String retrycount = null;

	public String getRetrycount() {
		return retrycount;
	}

	public void setRetrycount(String retrycount) {
		this.retrycount = retrycount;
	}

	public static TestNGXmlSuiteData getInstance() {

		if (testNGXmlSuiteData != null) {
			return testNGXmlSuiteData;
		}

		testNGXmlSuiteData = new TestNGXmlSuiteData();
		return testNGXmlSuiteData;
	}

}
