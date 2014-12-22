package tryagain.listener;

import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.paypal.test.utilities.logging.SimpleLogger;


import tryagain.objects.TryAgainParams;


public final class TryAgainHelper {

	private static final String NOTAVAILABLE = "NOTAVAILABLE";
	private SimpleLogger logger;

	private TryAgainHelper() {

	}

	/**
	 * Method to get the configuration parameters from testng XmlTest file, vm
	 * args or environment variables. This method first reads the environmental
	 * variables, then the VM arguments and finally the suite parameters. This
	 * should be used to read any Bluefin specific parameters.
	 * 
	 * @param param
	 *            - the parameter name {@link TryAgainParams}
	 * @param xmlTest
	 *            - the {@link XmlTest} object.
	 * @return the value of the configuration parameter.
	 */
	public static String getBluefinConfigParameter(TryAgainParams param, XmlTest xmlTest) {

		String value = NOTAVAILABLE, vmArg = null, paramValue = null;

		value = System.getenv(param.getVMArg());

		vmArg = System.getProperty(param.getVMArg());
		if (vmArg != null && !vmArg.trim().isEmpty()) {
			value = vmArg;
		}

		paramValue = xmlTest.getParameter(param.getTagName());
		if (paramValue != null && !paramValue.trim().isEmpty()) {
			value = paramValue;
		}

		return value;

	}

	/**
	 * Method to get the configuration parameters from testng suite, vm args or
	 * environment variables. This method reads the suite parameters, then the
	 * vm arguments and finally the environmental variables. This should be used
	 * to read only for VM arguments which are specific to Metrics reporter.
	 * 
	 * @param param
	 *            - the parameter name {@link TryAgainParams}
	 * @param xmlSuite
	 *            - the {@link XmlSuite} object
	 * @return the value of the configuration parameter.
	 */
	public static String getConfigParameter(TryAgainParams param, XmlSuite xmlSuite) {

		String value = NOTAVAILABLE, vmArg = null, envValue = null;

		value = xmlSuite.getParameter(param.getTagName());

		vmArg = System.getProperty(param.getVMArg());
		if (vmArg != null && !vmArg.trim().isEmpty()) {
			value = vmArg;
		}

		envValue = System.getenv(param.getVMArg());
		if (envValue != null && !envValue.trim().isEmpty()) {
			value = envValue;
		}

		return value;

	}


}
