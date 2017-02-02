package com.v2solutions.taf.common.logs;
import java.util.ArrayList;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class SkippedITestResultParser extends ITestResultParser {
	
	public SkippedITestResultParser(ITestResult tr, ITestContext iTestContext) {
		super(tr, iTestContext);
	}
	
	public ArrayList getExcelCompatibleLogs() {
		ArrayList<String> row = new ArrayList<String>();
		
		String srNo = String.valueOf(getSerialNumber());
		ITestNGMethod method = this.getITestResult().getMethod();
		String testMethodName = method.getMethodName();
		String meaningfulTestMethodName = method.getDescription();
		if((meaningfulTestMethodName == null) || (meaningfulTestMethodName.equals(""))) {
			meaningfulTestMethodName = LoggingConstants.TEST_METHOD_MEANINGFUL_NAME;
		}
		String fqmn = method.getRealClass().getCanonicalName()+"."+testMethodName;
		
		String testName = this.getITestContext().getName();
		
		row.add(srNo);
		row.add(testName);
		row.add(meaningfulTestMethodName);
		row.add(fqmn);
		System.out.println("Skipped Methods = "+row);
		return row;
	}
	
}
