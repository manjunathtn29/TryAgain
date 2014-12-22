TryAgain
========
TryAgain is a listener is an implementation of interface - IRetryAnalyzer, that reruns the failed test cases specified number of times.
All teams who sees false failures in their automation can make use of this feature in order to rerun the failed test cases and increase the pass rate
Follow the below two simple steps in order to use TryAgain
<br>
1. Add dependency to pom file
To start re-running the failed test cases, you must first add the following dependency to your pom.xml file to begin using the new listener:
 <dependency>
 <groupId>com.paypal.test.bluefin</groupId>
 <artifactId>TryAgain</artifactId>
 <version>1.0-SNAPSHOT</version>
 </dependency>
</br>
<br>
2. Enable TryAgain 
By default Failed Test Retry will be disabled and will not run failed test cases. 
The following parameter need to be added in to the TestNG suite.xml OR as a VM argument in order to re-run the test cases.
VM argument: -DRETRY_COUNT=1
Suite file parameter: <parameter name="retrycount" value="1"></parameter>
</br>
