package tryagain.sample.testng;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.paypal.test.utilities.logging.SimpleLogger;

/**
 * testNG dataprovider
 */
public class UnitTest4 extends Thread {
	private SimpleLogger logger;

	@DataProvider(name = "myDataProvider")
	public Object[][] data() {
		return new Object[][] {

		{ "uk", "GBP", 4.0f }, { "com", "USD", 5.0f }, { "ind", "GBP", 6.0f }

		};
	}

	@DataProvider(name = "myDataProvider1")
	public Object[][] data1() {
		return new Object[][] {

		{ "uk", "GBP1", 4.0f }, { "com", "USD1", 5.0f }, { "ind", "GBP1", 6.0f }

		};
	}

	@Test(dataProvider = "myDataProvider", groups = { "datatest" })
	public void method1(String site, String currency, float price) {
		logger.info("site" + site);
		logger.info("price" + price + "(" + currency + ")");
		Assert.assertEquals(false, true);
	}

	@Test(dataProvider = "myDataProvider1", groups = { "datatest" })
	public void method2(String site, String currency, float price) {
		logger.info("site" + site);
		logger.info("price" + price + "(" + currency + ")");
		Assert.assertEquals(false, true);
	}

}
