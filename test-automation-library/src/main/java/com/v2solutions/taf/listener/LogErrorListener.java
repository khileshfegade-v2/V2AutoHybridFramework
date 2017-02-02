package com.v2solutions.taf.listener;


import org.apache.commons.logging.Log;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.v2solutions.taf.util.LogUtil;

/**
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class LogErrorListener extends TestListenerAdapter {

	private static Log log = LogUtil.getLog(LogErrorListener.class);

	
	private ITestContext testContext;
	
	public LogErrorListener() {
		log.info("Log error listener object is created.");
	}
	
	public void onTestFailure(ITestResult tr) {
		SuiteListener.excelXlsTestResultLogger.addFailedTestResult(tr, testContext);
	}
	

	public void onTestSkipped(ITestResult tr) {
		SuiteListener.excelXlsTestResultLogger.addSkippedTestResult(tr, testContext);
	}

	public void onTestSuccess(ITestResult tr) {
		SuiteListener.excelXlsTestResultLogger.addPassedFailedTestResult(tr, testContext);
	}

	
	public void onStart(ITestContext testContext) {
		this.testContext = testContext;
	}	
	
	public void onFinish(ITestContext testContext) {
		SuiteListener.excelXlsTestResultLogger.logFinalResult(testContext);
		System.out.println("**********Finished log of "+testContext.getName());

	}
	
	
}
