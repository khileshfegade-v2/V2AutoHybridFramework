package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.v2solutions.taf.common.logs.ExcelCompatibleXMLWorksheetIFace;
import com.v2solutions.taf.common.logs.LoggingConstants;

public class FailAnalysisQAEngineerExcelCompatibleXMLWorkbook implements ExcelCompatibleXMLWorksheetIFace, 
ExcelCompatibleXMLRowIFace, ExcelCompatibleXMLWorkbookIFace {

	@Override
	public ArrayList<String> getColumnDefinitions() {
		ArrayList<String> columnDefinitions = new ArrayList<String>();
		
		columnDefinitions.add("   <Column ss:StyleID=\"s63\" ss:AutoFitWidth=\"0\" ss:Width=\"18.75\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"230.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"230.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"230.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"182.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"19.5\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"162.75\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"52.5\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"229.5\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"74.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"213\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"122.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"36\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"30.75\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"179.25\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"106.5\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"229.5\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"75\"/>\r\n" );
		columnDefinitions.add("   <Column ss:StyleID=\"s62\" ss:AutoFitWidth=\"0\" ss:Width=\"101.25\"/>\r\n" );
		
		return columnDefinitions;
	}

	@Override
	public ArrayList<String> getHeaderRow() {
		String headerLine1 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_SR_NO+"</Data></Cell>";
		String headerLine19 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TEST_NAME+"</Data></Cell>";
		String headerLine18 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_MEANINGFUL_NAME+"</Data></Cell>";
		String headerLine2 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_NAME+"</Data></Cell>";
		String headerLine3 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_MESSAGE+"</Data></Cell>";
		String headerLine4 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_CLASS_NAME+"</Data></Cell>";
		String headerLine5 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_MEANINGFUL_MESSAGE+"</Data></Cell>";
		String headerLine6 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_ANALYSIS+"</Data></Cell>";
		String headerLine7 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_URL+"</Data></Cell>";
		String headerLine8 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_ELEMENT_LABEL+"</Data></Cell>";
		String headerLine9 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_ELEMENT+"</Data></Cell>";
		String headerLine10 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_ELEMENT_ATTRIBUTE_NAME+"</Data></Cell>";
		String headerLine11 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_ACTUAL_PAGE_ELEMENT_ATTRIBUTE_VALUE+"</Data></Cell>";
		String headerLine12 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_EXPECTED_PAGE_ELEMENT_ATTRIBUTE_VALUE+"</Data></Cell>";
		String headerLine13 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_DEFECT_NAME+"</Data></Cell>";
		String headerLine14 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_SCREENSHOT_PATH+"</Data></Cell>";
		String headerLine15 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_STACK_METHOD_NAME+"</Data></Cell>";
		String headerLine16 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_STACK_JAVA_FILE_NAME+"</Data></Cell>";
		String headerLine17 = "<Cell ss:StyleID=\"s64\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_STACK_LINE_NUMBER+"</Data></Cell>";
		
		ArrayList<String> headerRow = new ArrayList();
		headerRow.add("<Row ss:AutoFitHeight=\"0\">");
		
		headerRow.add(headerLine1);
		headerRow.add(headerLine19);
		headerRow.add(headerLine18);
		headerRow.add(headerLine2);
		headerRow.add(headerLine3);
		headerRow.add(headerLine4);
		headerRow.add(headerLine5);
		headerRow.add(headerLine6);
		headerRow.add(headerLine7);
		headerRow.add(headerLine8);
		headerRow.add(headerLine9);
		headerRow.add(headerLine10);
		headerRow.add(headerLine11);
		headerRow.add(headerLine12);
		headerRow.add(headerLine13);
		headerRow.add(headerLine14);
		headerRow.add(headerLine15);
		headerRow.add(headerLine16);
		headerRow.add(headerLine17);
		
		headerRow.add("</Row>");
		return headerRow;
	}

	@Override
	public ArrayList<String> getExcelCompatibleXMLRow(ArrayList rawRow) {
		ArrayList<String> xmlRow = new ArrayList<String>();
		xmlRow.add("<Row ss:AutoFitHeight=\"0\">");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(0)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(1)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(2)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(3)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(4)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(5)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(6)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(7)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(8)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(9)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(10)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(11)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(12)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(13)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(14)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(15)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(16)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(17)+"</Data></Cell>");
		xmlRow.add("<Cell><Data ss:Type=\"String\">"+rawRow.get(18)+"</Data></Cell>");
		xmlRow.add("</Row>");
		return xmlRow;
	}

	@Override
	public ArrayList<String> getStartWorkbookTagLines() {
		ArrayList<String> startWorkbookTagLines = new ArrayList<String>();
		startWorkbookTagLines.add("<?xml version=\"1.0\"?>\r\n");
		startWorkbookTagLines.add("<?mso-application progid=\"Excel.Sheet\"?>\r\n");
		startWorkbookTagLines.add("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"\r\n");
		startWorkbookTagLines.add(" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n");
		startWorkbookTagLines.add(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"\r\n");
		startWorkbookTagLines.add(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"\r\n");
		startWorkbookTagLines.add(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">\r\n");
		startWorkbookTagLines.add(" <DocumentProperties xmlns=\"urn:schemas-microsoft-com:office:office\">\r\n");
		startWorkbookTagLines.add("  <Author>spunde</Author>\r\n");
		startWorkbookTagLines.add("  <LastAuthor>spunde</LastAuthor>\r\n");
		startWorkbookTagLines.add("  <Created>2014-11-20T10:58:12Z</Created>\r\n");
		startWorkbookTagLines.add("  <Version>14.00</Version>\r\n");
		startWorkbookTagLines.add(" </DocumentProperties>\r\n");
		startWorkbookTagLines.add(" <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\r\n");
		startWorkbookTagLines.add("  <AllowPNG/>\r\n");
		startWorkbookTagLines.add(" </OfficeDocumentSettings>\r\n");
		startWorkbookTagLines.add(" <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\r\n");
		startWorkbookTagLines.add("  <WindowHeight>7485</WindowHeight>\r\n");
		startWorkbookTagLines.add("  <WindowWidth>20115</WindowWidth>\r\n");
		startWorkbookTagLines.add("  <WindowTopX>240</WindowTopX>\r\n");
		startWorkbookTagLines.add("  <WindowTopY>30</WindowTopY>\r\n");
		startWorkbookTagLines.add("  <TabRatio>281</TabRatio>\r\n");
		startWorkbookTagLines.add("  <ProtectStructure>False</ProtectStructure>\r\n");
		startWorkbookTagLines.add("  <ProtectWindows>False</ProtectWindows>\r\n");
		startWorkbookTagLines.add(" </ExcelWorkbook>\r\n");
		return startWorkbookTagLines;
	}

	@Override
	public ArrayList<String> getStylesTagLines() {
		ArrayList<String> stylesTagLines = new ArrayList<String>();
		stylesTagLines.add(" <Styles>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\r\n");
		stylesTagLines.add("   <Alignment ss:Vertical=\"Bottom\"/>\r\n");
		stylesTagLines.add("   <Borders/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"/>\r\n");
		stylesTagLines.add("   <Interior/>\r\n");
		stylesTagLines.add("   <NumberFormat/>\r\n");
		stylesTagLines.add("   <Protection/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		
		stylesTagLines.add("  <Style ss:ID=\"s62\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		
		stylesTagLines.add("  <Style ss:ID=\"s63\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Right\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		
		stylesTagLines.add("  <Style ss:ID=\"s64\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior ss:Color=\"#FFFF00\" ss:Pattern=\"Solid\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add(" </Style>\r\n");
		stylesTagLines.add(" </Styles>\r\n");
		return stylesTagLines;
	}

	@Override
	public String getStartWorksheetTableTagLine() {
		String startTableTagLine =  "  <Table ss:ExpandedColumnCount=\"numberOfColumns\" ss:ExpandedRowCount=\"numberOfRows\" x:FullColumns=\"1\"\r\n"+
		"   x:FullRows=\"1\" ss:StyleID=\"s62\" ss:DefaultRowHeight=\"15\">\r\n";
		return startTableTagLine;
	}

	@Override
	public String getEndWorksheetTableTagLine() {
		String endTableTagLine =  "  </Table>\r\n";
		return endTableTagLine;
	}

	@Override
	public ArrayList<String> getWorksheetOptionsTagLines() {
		ArrayList<String> worksheetOptionsTagLines = new ArrayList<String>();
		worksheetOptionsTagLines.add("  <WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">\r\n");
		worksheetOptionsTagLines.add("   <PageSetup>\r\n");
		worksheetOptionsTagLines.add("    <Header x:Margin=\"0.3\"/>\r\n");
		worksheetOptionsTagLines.add("    <Footer x:Margin=\"0.3\"/>\r\n");
		worksheetOptionsTagLines.add("    <PageMargins x:Bottom=\"0.75\" x:Left=\"0.7\" x:Right=\"0.7\" x:Top=\"0.75\"/>\r\n");
		worksheetOptionsTagLines.add("   </PageSetup>\r\n");
		worksheetOptionsTagLines.add("   <Unsynced/>\r\n");
		worksheetOptionsTagLines.add("   <Print>\r\n");
		worksheetOptionsTagLines.add("    <ValidPrinterInfo/>\r\n");
		worksheetOptionsTagLines.add("    <PaperSizeIndex>9</PaperSizeIndex>\r\n");
		worksheetOptionsTagLines.add("    <HorizontalResolution>200</HorizontalResolution>\r\n");
		worksheetOptionsTagLines.add("    <VerticalResolution>200</VerticalResolution>\r\n");
		worksheetOptionsTagLines.add("   </Print>\r\n");
		worksheetOptionsTagLines.add("   <Selected/>\r\n");
		worksheetOptionsTagLines.add("   <Panes>\r\n");
		worksheetOptionsTagLines.add("    <Pane>\r\n");
		worksheetOptionsTagLines.add("     <Number>3</Number>\r\n");
		worksheetOptionsTagLines.add("     <ActiveRow>17</ActiveRow>\r\n");
		worksheetOptionsTagLines.add("     <ActiveCol>1</ActiveCol>\r\n");
		worksheetOptionsTagLines.add("    </Pane>\r\n");
		worksheetOptionsTagLines.add("   </Panes>\r\n");
		worksheetOptionsTagLines.add("   <ProtectObjects>False</ProtectObjects>\r\n");
		worksheetOptionsTagLines.add("   <ProtectScenarios>False</ProtectScenarios>\r\n");
		worksheetOptionsTagLines.add("  </WorksheetOptions>\r\n");
		return worksheetOptionsTagLines;
	}

	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return Logger.getLogger(LoggingConstants.LOG4J_FAIL_ANALYSIS_QA_ENGINEER_LOGGER);
	}
	

}
