package com.v2solutions.taf.common.logs;

import org.testng.ITestContext;
import org.testng.ITestResult;

public class ContextResultCapsule {
	
	private ITestResult tr;
	private ITestContext testContext;
	
	ContextResultCapsule(ITestResult tr,  ITestContext testContext) {
		this.setITestResult(tr);
		this.setTestContext(testContext);
	}
	
	public ITestResult getITestResult() {
		return tr;
	}
	public void setITestResult(ITestResult tr) {
		this.tr = tr;
	}
	
	
	public ITestContext getTestContext() {
		return testContext;
	}
	public void setTestContext(ITestContext testContext) {
		this.testContext = testContext;
	}
	
	

}
