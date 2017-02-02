package com.v2solutions.taf.common.logs;

import java.util.ArrayList;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class PassedITestResultParser extends ITestResultParser {
	
	public PassedITestResultParser(ITestResult tr, ITestContext iTestContext) {
		super(tr, iTestContext);
	}
	
	public ArrayList getExcelCompatibleLogs() {
		ArrayList<ArrayList> rows = new ArrayList<ArrayList>();
		
		String srNo = String.valueOf(getSerialNumber());
		
		long startTime = this.getITestResult().getStartMillis();
		long endTime = this.getITestResult().getEndMillis();
		long duration = endTime - startTime;
		double durationInSeconds = duration / 1000.0;
		
		ITestNGMethod method = this.getITestResult().getMethod();
		
		String testName = this.getITestContext().getName();
		
		String testMethodName = method.getMethodName();
		String meaningfulTestMethodName = method.getDescription();
		if((meaningfulTestMethodName == null) || (meaningfulTestMethodName.equals(""))) {
			meaningfulTestMethodName = LoggingConstants.TEST_METHOD_MEANINGFUL_NAME;
		}
		
		String fqmn = method.getRealClass().getCanonicalName()+"."+testMethodName;
		
		
		Object params[] = this.getITestResult().getParameters();
		if(params != null) {
			for(int i = 0; i < params.length; i++) {
				ArrayList<String> row = new ArrayList<String>();
				if(i == 0) {
					row.add(srNo);
					row.add(testName);
					row.add(meaningfulTestMethodName);
					row.add(String.valueOf(durationInSeconds));
				}
				else {
					row.add("");
					row.add("");
					row.add("");
					row.add("");
				}
				row.add(params[i].toString());
				if(i == 0) {
					row.add(fqmn);
				}
				else {
					row.add("");
				}
				rows.add(row);
			}
		}
		else {
			ArrayList<String> row = new ArrayList<String>();
			row.add(srNo);
			row.add(testName);
			row.add(meaningfulTestMethodName);
			row.add(String.valueOf(durationInSeconds));
			row.add("");
			row.add(fqmn);
			rows.add(row);
		}
		System.out.println("Passed Methods = "+rows);
		return rows;
	}
	
}
