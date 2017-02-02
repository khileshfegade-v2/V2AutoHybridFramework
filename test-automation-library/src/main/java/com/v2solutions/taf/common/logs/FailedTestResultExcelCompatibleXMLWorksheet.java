package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class FailedTestResultExcelCompatibleXMLWorksheet implements ExcelCompatibleXMLRowIFace, ExcelCompatibleXMLWorksheetIFace {

	@Override
	public String getStartWorksheetTableTagLine() {
		String startTableTagLine = "  <Table ss:ExpandedColumnCount=\"numberOfColumns\" ss:ExpandedRowCount=\"numberOfRows\" x:FullColumns=\"1\"\r\n" + 
				"   x:FullRows=\"1\" ss:StyleID=\"s64\" ss:DefaultRowHeight=\"15\">\r\n";
		return startTableTagLine;
	}

	@Override
	public String getEndWorksheetTableTagLine() {
		String endTableTagLine = "  </Table>\r\n";
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
		worksheetOptionsTagLines.add("   <Panes>\r\n");
		worksheetOptionsTagLines.add("    <Pane>\r\n");
		worksheetOptionsTagLines.add("     <Number>3</Number>\r\n");
		worksheetOptionsTagLines.add("     <ActiveRow>19</ActiveRow>\r\n");
		worksheetOptionsTagLines.add("     <ActiveCol>2</ActiveCol>\r\n");
		worksheetOptionsTagLines.add("    </Pane>\r\n");
		worksheetOptionsTagLines.add("   </Panes>\r\n");
		worksheetOptionsTagLines.add("   <ProtectObjects>False</ProtectObjects>\r\n");
		worksheetOptionsTagLines.add("   <ProtectScenarios>False</ProtectScenarios>\r\n");
		worksheetOptionsTagLines.add("  </WorksheetOptions>\r\n");
		
		return worksheetOptionsTagLines;
	}

	@Override
	public Logger getLogger() {
		return Logger.getLogger(LoggingConstants.LOG4J_FAILED_TEST_LOGGER);
	}

	@Override
	public ArrayList<String> getColumnDefinitions() {
		ArrayList<String> columnDefinitions = new ArrayList<String>();
	    columnDefinitions.add("   <Column ss:Index=\"2\" ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"132\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"91.5\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"180\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"180\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"119.25\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"91.5\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"148.5\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"132\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"274.5\"/>\r\n");
	    return columnDefinitions;
	}

	@Override
	public ArrayList<String> getHeaderRow() {
		ArrayList<String> headerRow = new ArrayList<String>();
		String line1 = "   <Row ss:StyleID=\"s71\">\r\n";
		String line2 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_SR_NO+"</Data></Cell>\r\n";
		String line3 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TEST_NAME+"</Data></Cell>\r\n";
		String line4 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_MEANINGFUL_NAME+"</Data></Cell>\r\n";
		String line5 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADER_DURATION+"</Data></Cell>\r\n";
		String line6 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADER_PARAMETER_NAME_VALUE+"</Data></Cell>\r\n";
		String line7 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_MEANINGFUL_MESSAGE+"</Data></Cell>\r\n";
		String line8 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_URL+"</Data></Cell>\r\n";
		String line9 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_ELEMENT_LABEL+"</Data></Cell>\r\n";
		String line10 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.EXCEPTION_PAGE_ELEMENT+"</Data></Cell>\r\n";
		String line11 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_NAME+"</Data></Cell>\r\n";
		String line12 = "  </Row>\r\n";
		
	    headerRow.add(line1);
	    headerRow.add(line2);
	    headerRow.add(line3);
	    headerRow.add(line4);
	    headerRow.add(line5);
	    headerRow.add(line6);
	    headerRow.add(line7);
	    headerRow.add(line8);
	    headerRow.add(line9);
	    headerRow.add(line10);
	    headerRow.add(line11);
	    headerRow.add(line12);
	    
	    return headerRow;
	}

	@Override
	public ArrayList<String> getExcelCompatibleXMLRow(ArrayList rawRow) {
		ArrayList<String> row = new ArrayList<String>();
		
		String line1 = "  <Row>\r\n";
		String line2 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(0)+"</Data></Cell>\r\n";
		String line3 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(1)+"</Data></Cell>\r\n";
		String line4 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(2)+"</Data></Cell>\r\n";
		String line5 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(3)+"</Data></Cell>\r\n";
		String line6 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(4)+"</Data></Cell>\r\n";
		String line7 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(5)+"</Data></Cell>\r\n";
		String line8 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(6)+"</Data></Cell>\r\n";
		String line9 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(7)+"</Data></Cell>\r\n";
		String line10 = "   <Cell><Data ss:Type=\"String\">"+rawRow.get(8)+"</Data></Cell>\r\n";
		String line11 = "   <Cell><Data ss:Type=\"String\">"+rawRow.get(9)+"</Data></Cell>\r\n";
		String line12 = "  </Row>\r\n";
		
		row.add(line1);
		row.add(line2);
		row.add(line3);
		row.add(line4);
		row.add(line5);
		row.add(line6);
		row.add(line7);
		row.add(line8);
		row.add(line9);
		row.add(line10);
		row.add(line11);
		row.add(line12);
		
		return row;
	}

}
