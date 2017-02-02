package com.v2solutions.taf.common.logs;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class ITestResultParser {
	
	/**This will help identify ITestResult by unique number in one application execution span. 
	 * Always starts with 1.
	 */
	private static int srNo = 1;
	
	private ITestResult tr;
	private ITestContext iTestContext;
	
	public ITestResultParser(ITestResult tr, ITestContext iTestContext) {
		this.tr = tr;
		this.iTestContext = iTestContext;
	}
	
	public ITestResult getITestResult() {
		return this.tr;
	}
	
	public ITestContext getITestContext() {
		return this.iTestContext;
	}
	
	
	/**Must be called after log is saved.
	 * */
	public static void incrementSerialNumber() {
		srNo++;
	}
	
	public static int getSerialNumber() {
		return srNo;
	}

}
