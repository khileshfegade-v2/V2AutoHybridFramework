package com.v2solutions.taf.common.logs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelCompatibleXMLWorkbook {
	
	private FileOutputStream outputCompletedXMLFile;

	private ExcelCompatibleXMLWorkbookIFace excelCompatibleXMLWorkbook;
	
	public ExcelCompatibleXMLWorkbook(ExcelCompatibleXMLWorksheetIFace excelCompatibleXMLWorksheet,
			ExcelCompatibleXMLWorkbookIFace excelCompatibleXMLWorkbook) {
		this.excelCompatibleXMLWorkbook = excelCompatibleXMLWorkbook;
	}
	
	
	public void setOutputCompletedXMLFile(FileOutputStream outputCompletedXMLFile) {
		this.outputCompletedXMLFile = outputCompletedXMLFile;
	}
	
	public void appendStartWorkbookTag() throws IOException {
		ArrayList<String> workbookStartTagLines = this.excelCompatibleXMLWorkbook.getStartWorkbookTagLines();
		for(int i = 0; i < workbookStartTagLines.size(); i++) {
			outputCompletedXMLFile.write((workbookStartTagLines.get(i)).getBytes());
		}
	}
	
	public void appendEndWorkbookTag() throws IOException {
	      outputCompletedXMLFile.write("</Workbook>\r\n".getBytes());
	      outputCompletedXMLFile.close();
	}
	
	public void appendStylesTag() throws IOException {
		ArrayList<String> stylesTagLines = this.excelCompatibleXMLWorkbook.getStylesTagLines();
		for(int i = 0; i < stylesTagLines.size(); i++) {
			outputCompletedXMLFile.write((stylesTagLines.get(i)).getBytes());
		}
	}

	
	
}
