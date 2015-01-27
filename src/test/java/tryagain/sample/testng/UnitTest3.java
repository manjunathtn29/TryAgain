package tryagain.sample.testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;



/**
 * testNG dependency 
 */
public class UnitTest3 extends Thread {
	
	
	
	@Test(groups={"dependencytest"})
	public void demo1() {
		assertEquals(1, 2);
		System.out.println("The current thread for demo1 is  :: - " + Thread.currentThread().getName());

	}

	@Test(groups={"dependencytest"})
	public void demo2() {
		//assertEquals(1, 2);
		System.out.println("The current thread is :: - " + Thread.currentThread().getName());

	}

	@Test(dependsOnMethods = { "demo1", "demo2" },groups={"dependencytest"})
	public void demo3() {
		// real test here.
		System.out.println("The current thread is :: - " + Thread.currentThread().getName());

	}
	
}
