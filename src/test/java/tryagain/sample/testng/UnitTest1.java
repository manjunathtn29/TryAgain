package tryagain.sample.testng;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;
import org.testng.asserts.LoggingAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;
import com.paypal.test.utilities.logging.SimpleLogger;




/**
 * TestNG basic.
 */
// code to print thread name 



public class UnitTest1 extends Thread  {
	
	private SimpleLogger logger;
	

    @Test(groups = ("pass"),invocationCount=2)
    public void testDemo11() {
    	logger.info("The current thread is :: - " + Thread.currentThread().getName());
        Assert.assertEquals(1, 2);
        
    }

    @Test(groups = ("pass"))
    public void TestDemo12() {
    	
    	logger.info("The current thread is :: - " + Thread.currentThread().getName());
        Assert.assertEquals(1, 1);
    }

	@Test(groups = ("fail"))
    public void testDemo3()  {
    	logger.info("The current thread is :: - " + Thread.currentThread().getName());
        Assert.assertEquals(1, 2);
    }

    // Soft Assert Example.  Sometimes a soft assert is more appropriate then "hard" assert.
    // Note: Don't "abuse" soft asserts. Test methods should test "one thing".
    @Test(groups = ("fail"))
    public void testDemo14() {
    	logger.info("The current thread testDemo4 is  :: - " + Thread.currentThread().getName());
        SoftAssert softAssert = new SoftAssert();

        // Soft asserts allow a test to continue even on any assertion failure.
        softAssert.assertTrue(true, "Assert 1 - condition (true) is true");
        softAssert.assertFalse(false, "Assert 2 - condition (false) is false.");
        softAssert.assertEquals(1, 2, "Assert 3 - 1 equals 2.");

        // Now fail the test on any soft assertion failures (i.e throw AssertionError).
        // You must do this in the test method to fail test on soft assert failure.
        softAssert.assertAll();
    }

    // Logging Assert Example.
    @Test(groups = ("pass"))
    public void testDemo15() {
        // Note: Use TestNG version 6.8.7
    	logger.info("The current thread is :: - " + Thread.currentThread().getName());
        LoggingAssert loggingAssert = new LoggingAssert();

        // Allow test to log assertion message to the logger.
        loggingAssert.assertTrue(true, "Assert 1 - condition (true) is true.");
        loggingAssert.assertFalse(false, "Assert 2 - condition (false) is false.");

        // Now also add assert messages to log.
        // Don't "abuse" the console by reporting "normal progress".
//        AppLogger.getLogger().fine(loggingAssert.getMessages().toString());
    }

    // Custom Assertion Example
    @Test(groups = ("fail"))
    public void testDemo16() {
        CustomAssert customAssert = new CustomAssert();
        customAssert.assertEquals(1, 2, "Failure #1");
        customAssert.assertEquals(1, 1, "Success #2");
        customAssert.assertEquals(1, 2, "Failure #3");
        customAssert.assertAll();
    }
    
    @Test(groups = ("pass"))
    public void testDemo17() {
    	//logger.info("Inside test case 7");
    	Assert.assertEquals(false, false);
    }
    
    @Test(groups = ("pass"))
    public void testDemo18() {
    	//logger.info("Inside test case 8");
    	Assert.assertEquals(false, false);

    }
    
    @Test(groups = ("pass"))
    public void testDemo19() {
    	//logger.info("Inside test case 9");
    	Assert.assertEquals(false, false);

    }
    
    @Test(groups = ("pass"))
    public void testDemo110() {
    	//logger.info("Inside test case 10");
    	Assert.assertEquals(false, false);

    }
    

    // Behave like a soft assert but also immediately log successful asserts to log.
    public static class CustomAssert extends Assertion {

        private Map<AssertionError, IAssert> m_errors = Maps.newHashMap();

        @Override
        public void executeAssert(IAssert a) {
            try {
                a.doAssert();
                // the App Logger here is preferable if every successful assert is logged (versus console / Reporter)
//                AppLogger.getLogger().fine(a.getMessage().toString());
            } catch (AssertionError ex) {
                onAssertFailure(a, ex);
                m_errors.put(ex, a);
            }
        }

        public void assertAll() {
            if (! m_errors.isEmpty()) {
                StringBuilder sb = new StringBuilder("The following asserts failed:\n");
                for (Map.Entry<AssertionError, IAssert> ae : m_errors.entrySet()) {
                    sb.append(ae.getValue().getMessage()).append("\n");
                    sb.append(getStackTrace(ae.getKey()));
                }
                throw new AssertionError(sb.toString());
            }
        }

        private String getStackTrace(Throwable t){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            return sw.toString();
        }
    }

}
