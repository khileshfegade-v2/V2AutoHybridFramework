package com.v2solutions.taf.common.logs;

import java.io.FileNotFoundException;
import org.apache.log4j.Logger;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;


/**
 * This class uses log4j API to write data to the files.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class SkippedLogsWithLog4j {

	public Logger logger = null;
	
	public SkippedLogsWithLog4j() {
	}


	/**This method adds initial configuration of Excel Sheet in terms of XML.
	 */
	public void log(ITestResult tr) {
		ITestNGMethod method = tr.getMethod();
		String testMethodName = method.getMethodName();
		String fQTestMethodName = method.getRealClass().getCanonicalName()+"."+testMethodName;
		
		this.logger.info("Test Method Name: "+fQTestMethodName);
		Throwable throwable = tr.getThrowable();
		String throwableMessage = null;
		if(throwable != null) {
			throwableMessage = throwable.getMessage();
		}
		this.logger.error(throwableMessage, throwable);
		this.logger.info("--------------------------");
	}

	/**This method retrieves logger which is defined into log4j.xml.
	 */
	public void openOutputStream() throws FileNotFoundException {
		// TODO Auto-generated method stub
		this.logger = Logger.getLogger(LoggingConstants.LOG4J_SKIP_ANALYSIS_LOGGER);
	}

	
}
