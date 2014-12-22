package tryagain.sample.testng;


public class UnitTest7 extends Thread {
	
	@Override
	public void run() {
	      for (int i = 0; i < 10; i++) {
	         printMsg();
	      }
	   }
	
	public void printMsg() {
	      Thread t = Thread.currentThread();
	      String name = t.getName();
	      System.out.println("name=" + name);
	  } 
	
	public static void main(String[] args) {
	      UnitTest7 tt = new UnitTest7();
	      tt.start();
	      for (int i = 0; i < 10; i++) {
	         tt.printMsg();
	      }
	   }
	}
