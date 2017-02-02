package com.v2solutions.taf.common.logs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelCompatibleXMLWorkSheet {
	
	private String sheetName;
	private int numberOfRows;
	private int numberOfColumns;
	
	protected FileOutputStream outputCompletedXMLFile;
	
	private ExcelCompatibleXMLWorksheetIFace excelCompatibleXML;

	public void setOutputCompletedXMLFile(FileOutputStream outputCompletedXMLFile) {
		this.outputCompletedXMLFile = outputCompletedXMLFile;
	}

	public ExcelCompatibleXMLWorkSheet(ExcelCompatibleXMLWorksheetIFace excelCompatibleXML) {
		this.excelCompatibleXML = excelCompatibleXML;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	
	public void startWorkSheetTag() throws IOException {
		String lineWithSheetName = " <Worksheet ss:Name=\""+sheetName+"\">\r\n";
		outputCompletedXMLFile.write(lineWithSheetName.getBytes());
	}
	
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}
	
	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}
	
	
	public void appendStartTableTag() throws IOException {
		String tableStartTagLine =  this.excelCompatibleXML.getStartWorksheetTableTagLine();
		tableStartTagLine = tableStartTagLine.replaceAll("numberOfRows", String.valueOf(numberOfRows));
		tableStartTagLine = tableStartTagLine.replaceAll("numberOfColumns", String.valueOf(numberOfColumns));
		outputCompletedXMLFile.write(tableStartTagLine.getBytes());
	}
	
	public void appendEndTableTag() throws IOException {
		String tableEndTagLine = this.excelCompatibleXML.getEndWorksheetTableTagLine();
	    outputCompletedXMLFile.write(tableEndTagLine.getBytes());
	}
	
	public void appendWorksheetOptionsTagLines() throws IOException {
		ArrayList<String> worksheetOptionTagLines = this.excelCompatibleXML.getWorksheetOptionsTagLines();
		for(int i = 0; i < worksheetOptionTagLines.size(); i++) {
			outputCompletedXMLFile.write((worksheetOptionTagLines.get(i)).getBytes());
		}
	}
	
	public void endWorkSheet() throws IOException {
		outputCompletedXMLFile.write(" </Worksheet>\r\n".getBytes());
	}
	
	
}
