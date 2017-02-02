package com.v2solutions.taf.common.logs;

public interface LoggingConstants {
	
	public static final String LOG4J_FAIL_ANALYSIS_CLIENT_LOGGER = "FailAnalysisClientLogger";
	public static final String LOG4J_FAIL_ANALYSIS_QA_ENGINEER_LOGGER = "FailAnalysisQaEngineerLogger";
	public static final String LOG4J_SKIP_ANALYSIS_LOGGER = "SkipAnalysisLogger";
	public static final String LOG4J_FINAL_RESULT_LOGGER = "FinalResultLogger";
	public static final String LOG4J_PASSED_TEST_LOGGER = "PassedTestLogger";
	public static final String LOG4J_SKIPPED_TEST_LOGGER = "SkippedTestLogger";
	public static final String LOG4J_FAILED_TEST_LOGGER = "FailedTestLogger";

//-----------------------------------------------------------------------------------------------------	
	
	public static final String CLIENT_FAIL_ANALYSIS_SHEET_NAME = "Failed Analysis For Client";
	public static final String CLIENT_FAIL_ANALYSIS_LOG_FILE_NAME = "ClientFailAnalysis.log";
	public static final String CLIENT_FAIL_ANALYSIS_XML_FILE_NAME_START = "ClientFailAnalysis";
	
//-----------------------------------------------------------------------------------------------------
	
	public static final String QA_ENGINEER_FAIL_ANALYSIS_SHEET_NAME = "Failed Analysis For QA-Engineer";
	public static final String QA_ENGINEER_FAIL_ANALYSIS_LOG_FILE_NAME = "QA-EngineerFailAnalysis.log";
	public static final String QA_ENGINEER_FAIL_ANALYSIS_XML_FILE_NAME_START = "QA-EngineerFailAnalysis";
	
//-----------------------------------------------------------------------------------------------------
	
	public static final String FINAL_RESULT_SHEET_NAME = "Final Result";
	public static final String FINAL_RESULT_LOG_FILE_NAME = "FinalResult.log";
	
	public static final String PASSED_TEST_METHODS_SHEET_NAME = "Passed";
	public static final String PASSED_TEST_METHODS_LOG_FILE_NAME = "Passed.log";
	
	public static final String SKIPPED_TEST_METHODS_SHEET_NAME = "Skipped";
	public static final String SKIPPED_TEST_METHODS_LOG_FILE_NAME = "Skipped.log";
	
	public static final String FAILED_TEST_METHODS_SHEET_NAME = "Failed";
	public static final String FAILED_TEST_METHODS_LOG_FILE_NAME = "Failed.log";
	
	public static final String ALL_RESULT_XML_FILE = "allResult.xml";
	
	
	public static final String COLUMN_HEADING_TEST_NAME = "Test Name";
	public static final String COLUMN_HEADING_TOTAL_PASSED_TEST_METHODS = "Total passed test methods";
	public static final String COLUMN_HEADING_TOTAL_FAILED_TEST_METHODS = "Total failed test methods";
	public static final String COLUMN_HEADING_TOTAL_SKIPPED_TEST_METHODS = "Total skipped test methods";
//-----------------------------------------------------------------------------------------------------	
	
	public static final String TEST_METHOD_SR_NO="Sr. No.";
	public static final String TEST_METHOD_MEANINGFUL_NAME="Meaningful Method Name";
	public static final String TEST_METHOD_NAME="Test Method Name";
	public static final String TEST_METHOD_PARAMETERS="Parameters";
	public static final String COLUMN_HEADER_DURATION = "Duration";
	public static final String COLUMN_HEADER_PARAMETER_NAME_VALUE = "Parameter Name=Parameter Value";
	
	public static final String EXCEPTION_TEST_STACK_TRACE="STACK_TRACE";
	public static final String EXCEPTION_CLASS_NAME="System Error Class";
	public static final String EXCEPTION_MESSAGE="System Error Message";
	
	public static final String EXCEPTION_MEANINGFUL_MESSAGE="Meaningful Message";
	public static final String EXCEPTION_ANALYSIS="Analysis";
	public static final String EXCEPTION_PAGE_URL="Page URL";
	public static final String EXCEPTION_PAGE_ELEMENT_LABEL="Page Element Label";
	public static final String EXCEPTION_PAGE_ELEMENT="Page Element";
	public static final String EXCEPTION_PAGE_ELEMENT_ATTRIBUTE_NAME="Attribute Name";
	public static final String EXCEPTION_ACTUAL_PAGE_ELEMENT_ATTRIBUTE_VALUE="Actual Attribute Value";
	public static final String EXCEPTION_EXPECTED_PAGE_ELEMENT_ATTRIBUTE_VALUE="Expected Attribute Value";
	public static final String EXCEPTION_DEFECT_NAME="DefectName";
	public static final String EXCEPTION_SCREENSHOT_PATH="Screenshot Path";

	public static final String EXCEPTION_STACK_METHOD_NAME="Error Method Name";
	public static final String EXCEPTION_STACK_JAVA_FILE_NAME="Error Java File";
	public static final String EXCEPTION_STACK_LINE_NUMBER="Error Line Number";
}
