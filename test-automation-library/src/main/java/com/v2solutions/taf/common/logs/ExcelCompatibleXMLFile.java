package com.v2solutions.taf.common.logs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.v2solutions.taf.core.TestBed;
import com.v2solutions.taf.util.BrowserInfoUtil;


public class ExcelCompatibleXMLFile {
	
	private void generateClientFailAnalysisXML() throws IOException {
		FileOutputStream outputCompletedXMLFile = null;
		String logFileNameStartsWith = LoggingConstants.CLIENT_FAIL_ANALYSIS_LOG_FILE_NAME;
		File completedXMLFile = null;
		File logFilesParentDir = null;		
		
		
		//Get log files
	    String currentDir = System.getProperty("user.dir")+File.separator+"target";
	    logFilesParentDir = new File(currentDir);
		
		ArrayList filenamesList = this.getOrderedLogFiles(logFileNameStartsWith, logFilesParentDir);
		
		//start workbook
		FailAnalysisClientExcelCompatibleXMLWorkbook failAnalysisClientExcelCompatibleXMLFile =
				new FailAnalysisClientExcelCompatibleXMLWorkbook();
		ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheet = failAnalysisClientExcelCompatibleXMLFile;
		ExcelCompatibleXMLWorkbookIFace excelCompatibleXMLWorkbook =  failAnalysisClientExcelCompatibleXMLFile;
		ExcelCompatibleXMLRowIFace excelCompatibleRowIFace = failAnalysisClientExcelCompatibleXMLFile;
		
		ExcelCompatibleXMLWorkbook workbook = new ExcelCompatibleXMLWorkbook(excelCompatibleXMLWorksheet, excelCompatibleXMLWorkbook);
		
		ExcelCompatibleXMLWorkSheet worksheet = new ExcelCompatibleXMLWorkSheet(excelCompatibleXMLWorksheet);
		worksheet.setSheetName(LoggingConstants.CLIENT_FAIL_ANALYSIS_SHEET_NAME);
		worksheet.setNumberOfRows(10000);
		worksheet.setNumberOfColumns(25);
		
		for(int j = 0; j < filenamesList.size(); j++) {
			String customerFileName = (String)filenamesList.get(j);
			File customerFile = new File(logFilesParentDir.getPath()+File.separator+customerFileName);
			if(j == 0) {
				String xmlFileName = getExcelCompatibleXMLFileName(customerFile, LoggingConstants.CLIENT_FAIL_ANALYSIS_XML_FILE_NAME_START);
				completedXMLFile = new File(logFilesParentDir.getPath()+File.separator+xmlFileName);
				outputCompletedXMLFile = new FileOutputStream(completedXMLFile,true);
				
				workbook.setOutputCompletedXMLFile(outputCompletedXMLFile);
				
				workbook.appendStartWorkbookTag();
					//add style
				workbook.appendStylesTag();
					//start worksheet
				worksheet.setOutputCompletedXMLFile(outputCompletedXMLFile);
				
				worksheet.startWorkSheetTag();
						//start table
				worksheet.appendStartTableTag();
						//add column definitions
				ExcelCompatibleXMLRow row = new ExcelCompatibleXMLRow(excelCompatibleXMLWorksheet, excelCompatibleRowIFace);
				row.setOutputCompletedXMLFile(outputCompletedXMLFile);
				row.appendColumnDefinitions();
						//add header row
				row.appendHeaderRow();
			}
						//add rows
			System.out.println("***** "+customerFile.getPath());
			FileInputStream inputStream = new FileInputStream(customerFile);
//				IOUtils.copy(inputStream, outputCompletedXMLFile);
			copyFile(inputStream, outputCompletedXMLFile);
			outputCompletedXMLFile.flush();
			inputStream.close();
		}
		
			//end table
		worksheet.appendEndTableTag();
			//add worksheet options
		worksheet.appendWorksheetOptionsTagLines();
			//end worksheet
		worksheet.endWorkSheet();
		//end workbook
		workbook.appendEndWorkbookTag();
		
	}
	
	private void generateQAEngineerFailAnalysisXML() throws IOException {
		FileOutputStream outputCompletedXMLFile = null;	
		String logFileNameStartsWith = LoggingConstants.QA_ENGINEER_FAIL_ANALYSIS_LOG_FILE_NAME;
		File completedXMLFile = null;
		File logFilesParentDir = null;		
		
		//Get log files
	    String currentDir = System.getProperty("user.dir")+File.separator+"target";

	    logFilesParentDir = new File(currentDir);
	    
		//start workbook
		FailAnalysisQAEngineerExcelCompatibleXMLWorkbook failAnalysisQAEngineerExcelCompatibleXMLFile =
				new FailAnalysisQAEngineerExcelCompatibleXMLWorkbook();
		
		ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheet = failAnalysisQAEngineerExcelCompatibleXMLFile;
		ExcelCompatibleXMLWorkbookIFace excelCompatibleXMLWorkbook =  failAnalysisQAEngineerExcelCompatibleXMLFile;
		ExcelCompatibleXMLRowIFace excelCompatibleRowIFace = failAnalysisQAEngineerExcelCompatibleXMLFile;
		
		ExcelCompatibleXMLWorkbook workbook = new ExcelCompatibleXMLWorkbook(excelCompatibleXMLWorksheet,
				excelCompatibleXMLWorkbook);
	    
		ExcelCompatibleXMLWorkSheet worksheet = new ExcelCompatibleXMLWorkSheet(excelCompatibleXMLWorksheet);
		worksheet.setSheetName(LoggingConstants.QA_ENGINEER_FAIL_ANALYSIS_SHEET_NAME);
		worksheet.setNumberOfRows(10000);
		worksheet.setNumberOfColumns(25);
		
		ArrayList filenamesList = this.getOrderedLogFiles(logFileNameStartsWith, logFilesParentDir);
		for(int j = 0; j < filenamesList.size(); j++) {
			String customerFileName = (String)filenamesList.get(j);
			File customerFile = new File(logFilesParentDir.getPath()+File.separator+customerFileName);
			if(j == 0) {
				String xmlFileName = getExcelCompatibleXMLFileName(customerFile, LoggingConstants.QA_ENGINEER_FAIL_ANALYSIS_XML_FILE_NAME_START);
				completedXMLFile = new File(logFilesParentDir.getPath()+File.separator+xmlFileName);
				outputCompletedXMLFile = new FileOutputStream(completedXMLFile,true);
				workbook.setOutputCompletedXMLFile(outputCompletedXMLFile);
				
				workbook.appendStartWorkbookTag();
					//add style
				workbook.appendStylesTag();
					//start worksheet
				worksheet.setOutputCompletedXMLFile(outputCompletedXMLFile);
				
				worksheet.startWorkSheetTag();
						//start table
				worksheet.appendStartTableTag();
						//add column definitions
				ExcelCompatibleXMLRow row = new ExcelCompatibleXMLRow(excelCompatibleXMLWorksheet, excelCompatibleRowIFace);
				row.setOutputCompletedXMLFile(outputCompletedXMLFile);
				row.appendColumnDefinitions();
						//add header row
				row.appendHeaderRow();
			}
					//add rows
			System.out.println("***** "+customerFile.getPath());
			FileInputStream inputStream = new FileInputStream(customerFile);
//				IOUtils.copy(inputStream, outputCompletedXMLFile);
			copyFile(inputStream, outputCompletedXMLFile);
			outputCompletedXMLFile.flush();
			inputStream.close();
		}
			//end table
		worksheet.appendEndTableTag();
			//add worksheet options
		worksheet.appendWorksheetOptionsTagLines();
			//end worksheet
		worksheet.endWorkSheet();
		//end workbook
		workbook.appendEndWorkbookTag();
	}
	

	public static void main(String args[]) {
		try {
			ExcelCompatibleXMLFile xmlFile = new ExcelCompatibleXMLFile();
			xmlFile.generateClientFailAnalysisXML();
			xmlFile.generateQAEngineerFailAnalysisXML();
			xmlFile.generateAllResultXML();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void generateAllResultXML() throws IOException {
		FileOutputStream outputCompletedXMLFile = null;
		File completedXMLFile = null;
		File logFilesParentDir = null;		
		
	    String currentDir = System.getProperty("user.dir")+File.separator+"target";
	    logFilesParentDir = new File(currentDir);
	    
		completedXMLFile = new File(logFilesParentDir.getPath()+File.separator+LoggingConstants.ALL_RESULT_XML_FILE);
		outputCompletedXMLFile = new FileOutputStream(completedXMLFile,true);
		
		//Write workbook starting
		AllTestResultWorkbook xmlWorkbook =
				new AllTestResultWorkbook();
		
		ExcelCompatibleXMLWorkbookIFace excelCompatibleXMLWorkbook =  xmlWorkbook;
		
		ExcelCompatibleXMLWorkbook workbook = new ExcelCompatibleXMLWorkbook(null, excelCompatibleXMLWorkbook);
		workbook.setOutputCompletedXMLFile(outputCompletedXMLFile);
		
		workbook.appendStartWorkbookTag();
			//add style
		workbook.appendStylesTag();
			//start worksheet

		FinalResultExcelCompatibleXMLWorksheet finalResultExcelCompatibleXMLWorksheet = 
				new FinalResultExcelCompatibleXMLWorksheet();
		PassedTestResultExcelCompatibleXMLWorksheet passedTestResultExcelCompatibleXMLWorksheet =
				new PassedTestResultExcelCompatibleXMLWorksheet();
		FailedTestResultExcelCompatibleXMLWorksheet failedTestResultExcelCompatibleXMLWorksheet =
				new FailedTestResultExcelCompatibleXMLWorksheet();
		SkippedTestResultExcelCompatibleXMLWorksheet skippedTestResultExcelCompatibleXMLWorksheet =
				new SkippedTestResultExcelCompatibleXMLWorksheet();
		
		addWorksheet(finalResultExcelCompatibleXMLWorksheet, outputCompletedXMLFile, finalResultExcelCompatibleXMLWorksheet, logFilesParentDir,
				LoggingConstants.FINAL_RESULT_SHEET_NAME, 10000, 25, LoggingConstants.FINAL_RESULT_LOG_FILE_NAME);
		addWorksheet(passedTestResultExcelCompatibleXMLWorksheet, outputCompletedXMLFile, passedTestResultExcelCompatibleXMLWorksheet, logFilesParentDir, 
				LoggingConstants.PASSED_TEST_METHODS_SHEET_NAME, 10000, 25, LoggingConstants.PASSED_TEST_METHODS_LOG_FILE_NAME);
		addWorksheet(failedTestResultExcelCompatibleXMLWorksheet, outputCompletedXMLFile, failedTestResultExcelCompatibleXMLWorksheet, logFilesParentDir, 
				LoggingConstants.FAILED_TEST_METHODS_SHEET_NAME, 10000, 25, LoggingConstants.FAILED_TEST_METHODS_LOG_FILE_NAME);
		addWorksheet(skippedTestResultExcelCompatibleXMLWorksheet, outputCompletedXMLFile, skippedTestResultExcelCompatibleXMLWorksheet, logFilesParentDir, 
				LoggingConstants.SKIPPED_TEST_METHODS_SHEET_NAME, 10000, 25, LoggingConstants.SKIPPED_TEST_METHODS_LOG_FILE_NAME);
		
		//end workbook
		workbook.appendEndWorkbookTag();
	}
	
	private void addWorksheet(ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheetIFace,
			FileOutputStream outputCompletedXMLFile,
			ExcelCompatibleXMLRowIFace excelCompatibleXMLRowIFace,
			File logFilesParentDir, String sheetName, int numberOfRows, int numberOfColumns,
			String logFileName) throws IOException {
		
		ExcelCompatibleXMLWorkSheet worksheet = new ExcelCompatibleXMLWorkSheet(excelCompatibleXMLWorksheetIFace);
		worksheet.setOutputCompletedXMLFile(outputCompletedXMLFile);
		worksheet.setSheetName(sheetName);
		worksheet.setNumberOfRows(numberOfRows);
		worksheet.setNumberOfColumns(numberOfColumns);
		
		worksheet.startWorkSheetTag();
				//start table
		worksheet.appendStartTableTag();
		
				//add column definitions
		ExcelCompatibleXMLRow row = new ExcelCompatibleXMLRow(excelCompatibleXMLWorksheetIFace, excelCompatibleXMLRowIFace);
		row.setOutputCompletedXMLFile(outputCompletedXMLFile);
		row.appendColumnDefinitions();
				//add header row
		row.appendHeaderRow();
		
		ArrayList filenamesList = this.getOrderedLogFiles(logFileName, logFilesParentDir);
		for(int j = 0; j < filenamesList.size(); j++) {
			String customerFileName = (String)filenamesList.get(j);
			File customerFile = new File(logFilesParentDir.getPath()+File.separator+customerFileName);
			//add rows
			System.out.println("***** "+customerFile.getPath());
			FileInputStream inputStream = new FileInputStream(customerFile);
//			IOUtils.copyLarge(inputStream, outputCompletedXMLFile);
			copyFile(inputStream, outputCompletedXMLFile);
			outputCompletedXMLFile.flush();
			
			inputStream.close();
		}
			//end table
		worksheet.appendEndTableTag();
			//add worksheet options
		worksheet.appendWorksheetOptionsTagLines();
			//end worksheet
		worksheet.endWorkSheet();
	}
	
	private void copyFile(FileInputStream inputStream, FileOutputStream outputCompletedXMLFile) throws IOException {
		byte b[] = new byte[100000];
		int readInt = -1;
		long total = -1;
		while((readInt = inputStream.read(b)) != -1) {
			total = total + readInt;
			System.out.println("*************************** "+total);
			outputCompletedXMLFile.write(b, 0, readInt);

		}
	}
		
	
	protected ArrayList getOrderedLogFiles(String logFileNameStartsWith, File logFilesParentDir) {
		ArrayList filenamesList = new ArrayList();
		File customerFiles[] = logFilesParentDir.listFiles();
		if(customerFiles != null) {
			for(int i = 0; i < customerFiles.length; i++) {
				File anyFile = customerFiles[i];
				if(anyFile.getName().startsWith(logFileNameStartsWith)) {
					filenamesList.add(anyFile.getName());
				}
			}
		}
		
		//Sort array in alphabetical reverse order.
		Comparator cmp = Collections.reverseOrder();  
		
		// sort the list
		Collections.sort(filenamesList, cmp);  
		System.out.println(filenamesList);
		return filenamesList;
	}
	
	
	protected String getExcelCompatibleXMLFileName(File logFile, String forUser) throws IOException {
		long lastModifiedTime = logFile.lastModified();
		Date utcDate = new Date(lastModifiedTime);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd-HH-mm-ss");
		
		String browserDetails = TestBed.INSTANCE.getProfile().getBrowser();
		
		String xmlFileName = forUser+"-"+browserDetails+"-"+df.format(utcDate)+".xml";
		return xmlFileName;
	}
	
}
