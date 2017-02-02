package com.v2solutions.taf.common.logs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

public class ExcelCompatibleXMLRow {
	
	private Logger logger = null;
	private FileOutputStream outputCompletedXMLFile;
	
	private ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheet;
	private ExcelCompatibleXMLRowIFace excelCompatibleXMLRow;
	
	public ExcelCompatibleXMLRow(ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheet,
			ExcelCompatibleXMLRowIFace excelCompatibleXMLRow) {
		this.excelCompatibleXMLWorksheet = excelCompatibleXMLWorksheet;
		this.excelCompatibleXMLRow = excelCompatibleXMLRow;
	}
	
	public void setOutputCompletedXMLFile(FileOutputStream outputCompletedXMLFile) {
		this.outputCompletedXMLFile = outputCompletedXMLFile;
	}
	
	public void setLogger() {
		this.logger = excelCompatibleXMLRow.getLogger();
	}

	public void appendColumnDefinitions() throws IOException {
		ArrayList<String> columnDefinitions = this.excelCompatibleXMLRow.getColumnDefinitions();
		for(int i = 0; i < columnDefinitions.size(); i++) {
			String columnHeaderInfomation = columnDefinitions.get(i);
			outputCompletedXMLFile.write(columnHeaderInfomation.getBytes());
		}
	}
	
	/**@throws IOException 
	 */
	public void appendRow(ArrayList rawRow) throws IOException {
		ArrayList<String> excelCompatibleXMLRow = this.excelCompatibleXMLRow.getExcelCompatibleXMLRow(rawRow);
		for(int i = 0; i < excelCompatibleXMLRow.size(); i++) {
			this.logger.info(excelCompatibleXMLRow.get(i));
		}
	}
	
	public void appendHeaderRow() throws IOException {
		ArrayList<String> headerRow = this.excelCompatibleXMLRow.getHeaderRow();
		for(int i = 0; i < headerRow.size(); i++) {
			String columnHeaderInfomation = headerRow.get(i);
			outputCompletedXMLFile.write(columnHeaderInfomation.getBytes());
		}
	}
	
	public void openOutputStream() {
		this.logger = Logger.getLogger(LoggingConstants.LOG4J_FAIL_ANALYSIS_CLIENT_LOGGER);
	}
	
	
}
