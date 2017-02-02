package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

/**
 * This class object holds a ITestResult.  Provides methods to retrieve ITestResult object detail in terms
 * of logs for various users in the system like qa-engineers, clients.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class FailedITestResultParser extends ITestResultParser {
	
	
	/**Only the StackTraceElements whose class name starts with any/all elements from this will be added
	 * into logs.*/
	private static ArrayList<String> customPackageNames;
	
	/**Holds logs for qa-engineers.
	 */
	private ArrayList qaLogs;
	
	/**Holds logs for customers.
	 */
	private ArrayList customerLogs;
	private ArrayList failedLogs;
	
	
	public FailedITestResultParser(ITestResult tr, ITestContext iTestContext) {
		super(tr, iTestContext);
		setToRetainStacktracePackages();
		qaLogs = new ArrayList();
		customerLogs = new ArrayList();
		failedLogs = new ArrayList();
		createExcelCompatibleClientLogs();
		createExcelCompatibleAnalysisLogs();
	}
	
	public static void setToRetainStacktracePackages() {
		if(customPackageNames == null) {
			customPackageNames = new ArrayList<String>();
			customPackageNames.add("com.v2solutions.taf.");
			customPackageNames.add("com.v2solutions.");
			customPackageNames.add("test.v2solutions.");
		}
	}
	
	public ArrayList getQaLogs() {
		return qaLogs;
	}
	
	public ArrayList getCustomerLogs() {
		return customerLogs;
	}
	
	public ArrayList getFailedLogs() {
		System.out.println("Failed Logs: "+failedLogs);
		return failedLogs;
	}
	
	private void createExcelCompatibleClientLogs() {
		
		HashMap testMethodHashMap = parse();
		String srNo = String.valueOf(getSerialNumber());
		String testName = this.getITestContext().getName();
		String meaningfulTestMethodName = (String)testMethodHashMap.get(LoggingConstants.TEST_METHOD_MEANINGFUL_NAME);
		String duration = (String)testMethodHashMap.get(LoggingConstants.COLUMN_HEADER_DURATION);
		
		String meaninFulMessage = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_MEANINGFUL_MESSAGE);
		if(meaninFulMessage != null) {
			meaninFulMessage = meaninFulMessage.replaceAll("<", "&lt");
			meaninFulMessage = meaninFulMessage.replaceAll(">", "&gt");
		}
		
		String pageUrl = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_URL);
		if(pageUrl == null)
			pageUrl = "";
		
		String pageElementLabel = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_ELEMENT_LABEL);
		if(pageElementLabel != null) {
			pageElementLabel = pageElementLabel.replaceAll("<", "&lt");
			pageElementLabel = pageElementLabel.replaceAll(">", "&gt");
		}
		
		String pageElement = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_ELEMENT);
		if(pageElement != null) {
			pageElement = pageElement.replaceAll("<", "&lt");
			pageElement = pageElement.replaceAll(">", "&gt");
		}
		
		String testMethodName = (String)testMethodHashMap.get(LoggingConstants.TEST_METHOD_NAME);
		
		ArrayList<String> parameters = (ArrayList<String>)testMethodHashMap.get(LoggingConstants.TEST_METHOD_PARAMETERS);
		
		ArrayList<String> row = new ArrayList<String>();
		row.add(srNo);
		row.add(testName);
		row.add(meaningfulTestMethodName);
		row.add(duration);
		if(parameters.size() > 0) {
			row.add(parameters.get(0));
		}
		else {
			row.add("");
		}
		
		if(meaninFulMessage == null) {
			row.add("");
		}
		else {
			row.add(meaninFulMessage);
		}
		
		if(pageUrl == null) {
			row.add("");
		}
		else { 
			row.add(pageUrl);
		}
		
		if(pageElementLabel == null) {
			row.add("");
		}
		else {
			row.add(pageElementLabel);
		}
		
		if(pageElement == null) {
			row.add("");
		}
		else {
			row.add(pageElement);
		}
		
		if(testMethodName == null) {
			row.add("");
		}
		else {
			row.add(testMethodName);
		}
		
		
		failedLogs.add(row);
		
		for(int i = 1; i < parameters.size(); i++) {
			ArrayList<String> row1 = new ArrayList<String>();
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add(parameters.get(i));
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add("");
			row1.add("");
			failedLogs.add(row1);
		}
		
	}
	
	private void createExcelCompatibleAnalysisLogs() {
		String srNo = String.valueOf(getSerialNumber());
		String testName = this.getITestContext().getName();
		String stackMethodName = "";
		String stackJavaFileName = "";
		String stackLineNumber = "";
		
		HashMap testMethodHashMap = parse();
		String testMethodName = (String)testMethodHashMap.get(LoggingConstants.TEST_METHOD_NAME);
		String meaningfulTestMethodName = (String)testMethodHashMap.get(LoggingConstants.TEST_METHOD_MEANINGFUL_NAME);
		String errorMessage = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_MESSAGE);
		if(errorMessage != null) {
			errorMessage = errorMessage.replaceAll("<", "&lt");
			errorMessage = errorMessage.replaceAll(">", "&gt");
		}
		else {
			errorMessage = "";
		}
		
		String className = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_CLASS_NAME);
		
		String meaninFulMessage = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_MEANINGFUL_MESSAGE);
		if(meaninFulMessage != null) {
			meaninFulMessage = meaninFulMessage.replaceAll("<", "&lt");
			meaninFulMessage = meaninFulMessage.replaceAll(">", "&gt");
		}
		else {
			meaninFulMessage = "";
		}
		
		String analysis = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_ANALYSIS);
		if(analysis == null) {
			analysis = "";
		}
		
		String pageUrl = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_URL);
		if(pageUrl == null) {
			pageUrl = "";
		}
		
		String pageElementLabel = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_ELEMENT_LABEL);
		if(pageElementLabel != null) {
			pageElementLabel = pageElementLabel.replaceAll("<", "&lt");
			pageElementLabel = pageElementLabel.replaceAll(">", "&gt");
		}
		else {
			pageElementLabel = "";
		}
		
		String pageElement = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_ELEMENT);
		if(pageElement != null) {
			pageElement = pageElement.replaceAll("<", "&lt");
			pageElement = pageElement.replaceAll(">", "&gt");
		}
		else {
			pageElement = "";
		}
		
		String pageElementAttributeName = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_PAGE_ELEMENT_ATTRIBUTE_NAME);
		if(pageElementAttributeName != null) {
			pageElementAttributeName = pageElementAttributeName.replaceAll("<", "&lt");
			pageElementAttributeName = pageElementAttributeName.replaceAll(">", "&gt");
		}
		else {
			pageElementAttributeName = "";
		}
		
		String pageElementAttributeActualValue = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_ACTUAL_PAGE_ELEMENT_ATTRIBUTE_VALUE);
		if(pageElementAttributeActualValue != null) {
			pageElementAttributeActualValue = pageElementAttributeActualValue.replaceAll("<", "&lt");
			pageElementAttributeActualValue = pageElementAttributeActualValue.replaceAll(">", "&gt");
		}
		else {
			pageElementAttributeActualValue = "";
		}
		
		String pageElementAttributeExpectedValue = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_EXPECTED_PAGE_ELEMENT_ATTRIBUTE_VALUE);
		if(pageElementAttributeExpectedValue != null) {
			pageElementAttributeExpectedValue = pageElementAttributeExpectedValue.replaceAll("<", "&lt");
			pageElementAttributeExpectedValue = pageElementAttributeExpectedValue.replaceAll(">", "&gt");
		}
		else {
			pageElementAttributeExpectedValue = "";
		}
		
		String defectName = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_DEFECT_NAME);
		if(defectName == null)
			defectName = "";
		String screenShotPath = (String)testMethodHashMap.get(LoggingConstants.EXCEPTION_SCREENSHOT_PATH);
		if(screenShotPath == null)
			screenShotPath = "";
		
		//Create row for customer
		customerLogs.add(String.valueOf(getSerialNumber()));
		customerLogs.add(testName);
		customerLogs.add(meaningfulTestMethodName);
		customerLogs.add(testMethodName);
		customerLogs.add(meaninFulMessage);
		customerLogs.add(analysis);
		customerLogs.add(pageUrl);
		customerLogs.add(pageElementLabel);
		customerLogs.add(pageElement);

		//Get stack trace details for qa logs
		ArrayList stackTraceArrayList = (ArrayList)testMethodHashMap.get(LoggingConstants.EXCEPTION_TEST_STACK_TRACE);
		int straceSize = stackTraceArrayList.size();
		for(int i = 0; i < straceSize; i++) {
			if(i > 0) {
				testName = "";
				meaningfulTestMethodName = "";
				testMethodName = "";
				errorMessage = "";
				className = "";
				
				meaninFulMessage = "";
				analysis = "";
				pageUrl = "";
				pageElementLabel = "";
				pageElement = "";
				pageElementAttributeName = "";
				pageElementAttributeActualValue = "";
				pageElementAttributeExpectedValue = "";
				defectName = "";
				screenShotPath = "";
				
			}
			HashMap stackHashMap = (HashMap)stackTraceArrayList.get(i);
			stackMethodName = (String)stackHashMap.get(LoggingConstants.EXCEPTION_STACK_METHOD_NAME);
			stackJavaFileName = (String)stackHashMap.get(LoggingConstants.EXCEPTION_STACK_JAVA_FILE_NAME);
			stackLineNumber = (String)stackHashMap.get(LoggingConstants.EXCEPTION_STACK_LINE_NUMBER);
			
			
			//Create row
			ArrayList<String> qarow = new ArrayList<String>();
			qarow.add(srNo);
			qarow.add(testName);
			qarow.add(meaningfulTestMethodName);
			qarow.add(testMethodName);
			qarow.add(errorMessage);
			qarow.add(className);
			
			qarow.add(meaninFulMessage);
			qarow.add(analysis);
			qarow.add(pageUrl);
			qarow.add(pageElementLabel);
			qarow.add(pageElement);
			qarow.add(pageElementAttributeName);
			qarow.add(pageElementAttributeActualValue);
			qarow.add(pageElementAttributeExpectedValue);
			qarow.add(defectName);
			qarow.add(screenShotPath);
			
			qarow.add(stackMethodName);
			qarow.add(stackJavaFileName);
			qarow.add(stackLineNumber);
			
			qaLogs.add(qarow);
		}
	}
	
	private HashMap parse() {
		Throwable causeObj = null;
		HashMap testMethodHashMap = null;
		ITestNGMethod method = null;
		ArrayList stackTraceArrayList = null;
		String testMethodName = null;
		String meaningfulTestMethodName = null;
		Throwable errorOrException = null;
		
		if(this.getITestResult() == null) {
			return null;
		}
		
		method = this.getITestResult().getMethod();
		if(method == null) {
			return null;
		}
		
		//Get exception
		errorOrException = this.getITestResult().getThrowable();
		if(errorOrException == null) {
			return null;
		}
		

		//testMethodHashMap will contain logs for a test.
		testMethodHashMap = new HashMap();
		testMethodName = method.getMethodName();
		meaningfulTestMethodName = method.getDescription();
		if((meaningfulTestMethodName == null) || (meaningfulTestMethodName.equals(""))) {
			meaningfulTestMethodName = "Meaningful method name";
		}
		String fqmn = method.getRealClass().getCanonicalName()+"."+testMethodName;
		
		//Add method name
		testMethodHashMap.put(LoggingConstants.TEST_METHOD_SR_NO, String.valueOf(getSerialNumber()));
		testMethodHashMap.put(LoggingConstants.TEST_METHOD_MEANINGFUL_NAME, meaningfulTestMethodName);
		testMethodHashMap.put(LoggingConstants.TEST_METHOD_NAME, fqmn);
		
		ArrayList parametersList = new ArrayList();
		Object parameters[] = this.getITestResult().getParameters();
		if(parameters != null) {
			for(int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				if(parameter != null) {
					parametersList.add(parameter.toString());
				}
				else {
					parametersList.add("");
				}
			}
		}
		testMethodHashMap.put(LoggingConstants.TEST_METHOD_PARAMETERS, parametersList);
		
		long startTime = this.getITestResult().getStartMillis();
		long endTime = this.getITestResult().getEndMillis();
		long duration = endTime - startTime;
		double durationInSeconds = duration / 1000.0;
		testMethodHashMap.put(LoggingConstants.COLUMN_HEADER_DURATION, String.valueOf(durationInSeconds));
		
		causeObj = errorOrException.getCause();

		if(causeObj instanceof AnalizerException) {
			AnalizerException analizerException = (AnalizerException)causeObj;
			addAnalizerException(analizerException, testMethodHashMap);
			//Get real system level cause
			causeObj = causeObj.getCause();
		}
		
		//stackTraceArrayList will be filled with stacktrace.
		stackTraceArrayList = new ArrayList();
		//Add empty stack trace
		testMethodHashMap.put(LoggingConstants.EXCEPTION_TEST_STACK_TRACE, stackTraceArrayList);
		
		if(causeObj == null) {
			causeObj = errorOrException;
		}
		
		if(causeObj != null) {
			parseErrorClassNErrorMessage(causeObj, testMethodHashMap);
			parseStacktrace(causeObj, stackTraceArrayList);
		}
		
		System.out.println(testMethodHashMap);
		return testMethodHashMap;
	}
	
	private void addAnalizerException(AnalizerException analizerException, HashMap testMethodHashMap) {
		String meaninFulMessage = analizerException.getMeaningfulMessage();
		String analysis = analizerException.getAnalizedType();
		String pageUrl = analizerException.getPageURL();
		String pageElementLabel = analizerException.getPageElementLabel();
		String pageElement = analizerException.getPageElementLocator();
		String pageElementAttributeName = analizerException.getPageElementAttributeName();
		String pageElementAttributeActualValue = analizerException.getActualPageElementAttributeValue();
		String pageElementAttributeExpectedValue = analizerException.getExpectedPageElementAttributeValue();
		String defectName = analizerException.getDefectName();
		
		String screenShotPath = analizerException.getScreenShotPath();
		
		testMethodHashMap.put(LoggingConstants.EXCEPTION_MEANINGFUL_MESSAGE,meaninFulMessage);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_ANALYSIS,analysis);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_PAGE_URL,pageUrl);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_PAGE_ELEMENT_LABEL,pageElementLabel);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_PAGE_ELEMENT,pageElement);
		
		testMethodHashMap.put(LoggingConstants.EXCEPTION_PAGE_ELEMENT_ATTRIBUTE_NAME,pageElementAttributeName);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_ACTUAL_PAGE_ELEMENT_ATTRIBUTE_VALUE,pageElementAttributeActualValue);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_EXPECTED_PAGE_ELEMENT_ATTRIBUTE_VALUE,pageElementAttributeExpectedValue);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_DEFECT_NAME,defectName);
		testMethodHashMap.put(LoggingConstants.EXCEPTION_SCREENSHOT_PATH,screenShotPath); 
	}
	
	private void parseErrorClassNErrorMessage(Throwable throwable, HashMap hashMap) {
		String errorClassName = "Not Found";
		String errorMessage = "Not Found";
		if(throwable != null) {
			Class errorClass = throwable.getClass();
			if(errorClass != null) {
				errorClassName =  errorClass.getName();
			}
			errorMessage = throwable.getMessage();
		}
		if(hashMap != null) {
			hashMap.put(LoggingConstants.EXCEPTION_CLASS_NAME, errorClassName);
			hashMap.put(LoggingConstants.EXCEPTION_MESSAGE, errorMessage);
		}
	}
	
	private void parseStacktrace(Throwable throwableStackTrace, ArrayList stackTraceArrayList) {
		HashMap stackTraceMap = null;
		
		String stackClassName = "Not Found";
		String stackFileName = "Not Found";
		String stackMethodName = "Not Found";
		String stackLineNumber = "Not Found";
		
		String customPackageName = "";
		boolean packageNameFound = false;
		int lineNumber = -1;
		int customPackageNamesSize = -1;
		StackTraceElement stackTraceLineObj = null;
		
		StackTraceElement errorStackTrace[] = throwableStackTrace.getStackTrace();
		if(errorStackTrace != null) {
			if(errorStackTrace.length > 0) {
				for(int i = 0; i < errorStackTrace.length; i++) {
					packageNameFound = false;
					lineNumber = -1;
					stackTraceLineObj = errorStackTrace[i];
					if(stackTraceLineObj != null) {
						if(!stackTraceLineObj.isNativeMethod()) {
							stackClassName = stackTraceLineObj.getClassName();
							if(stackClassName != null) {
								customPackageNamesSize = customPackageNames.size();
								for(int j = 0; j < customPackageNamesSize && (!packageNameFound); j++) {
									customPackageName = customPackageNames.get(j);
									if(stackClassName.startsWith(customPackageName)) {
										packageNameFound = true;
										stackFileName = stackTraceLineObj.getFileName();
										if(stackFileName == null) {
											stackFileName = "Not Found";
										}
										
										stackMethodName= stackTraceLineObj.getMethodName();
										if(stackMethodName == null) {
											stackMethodName = "Not Found";
										}
										
										lineNumber = stackTraceLineObj.getLineNumber();
										stackLineNumber = String.valueOf(lineNumber);
										
//										System.out.print("Stack Class Name:"+stackClassName+" File Name:"+stackFileName);
//										System.out.println("  Method Name:"+stackMethodName+" Line Number:"+stackLineNumber);
										
										stackTraceMap = new HashMap();
										stackTraceMap.put(LoggingConstants.EXCEPTION_STACK_METHOD_NAME, stackClassName+"."+stackMethodName);
										stackTraceMap.put(LoggingConstants.EXCEPTION_STACK_JAVA_FILE_NAME, stackFileName);
										stackTraceMap.put(LoggingConstants.EXCEPTION_STACK_LINE_NUMBER, stackLineNumber);
										stackTraceArrayList.add(stackTraceMap);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
