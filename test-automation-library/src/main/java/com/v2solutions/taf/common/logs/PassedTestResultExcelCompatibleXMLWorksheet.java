package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class PassedTestResultExcelCompatibleXMLWorksheet implements ExcelCompatibleXMLRowIFace, ExcelCompatibleXMLWorksheetIFace {

	@Override
	public String getStartWorksheetTableTagLine() {
		String startTableTagLine = "  <Table ss:ExpandedColumnCount=\"numberOfColumns\" ss:ExpandedRowCount=\"numberOfRows\" x:FullColumns=\"1\"\r\n"+
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
		worksheetOptionsTagLines.add("     <ActiveRow>21</ActiveRow>\r\n");
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
		return Logger.getLogger(LoggingConstants.LOG4J_PASSED_TEST_LOGGER);
	}

	@Override
	public ArrayList<String> getColumnDefinitions() {
		ArrayList<String> columnDefinitions = new ArrayList<String>();
	    columnDefinitions.add("   <Column ss:Index=\"2\" ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"137.25\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"155.25\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"155.25\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"155.25\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"193.5\"/>\r\n");
	    columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"413.25\"/>\r\n");
	    return columnDefinitions;
	}

	@Override
	public ArrayList<String> getHeaderRow() {
		ArrayList<String> headerRow = new ArrayList<String>();
		String line1 = "   <Row ss:StyleID=\"s68\">\r\n";
		String line2 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_SR_NO+"</Data></Cell>\r\n";
		String line3 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TEST_NAME+"</Data></Cell>\r\n";
		String line4 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_MEANINGFUL_NAME+"</Data></Cell>\r\n";
		String line5 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADER_DURATION+"</Data></Cell>\r\n";
		String line6 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADER_PARAMETER_NAME_VALUE+"</Data></Cell>\r\n";
		String line7 = "    <Cell ss:StyleID=\"s67\"><Data ss:Type=\"String\">"+LoggingConstants.TEST_METHOD_NAME+"</Data></Cell>\r\n";
		String line8 = "   </Row>\r\n";
		
	    headerRow.add(line1);
	    headerRow.add(line2);
	    headerRow.add(line3);
	    headerRow.add(line4);
	    headerRow.add(line5);
	    headerRow.add(line6);
	    headerRow.add(line7);
	    headerRow.add(line8);
	    
	    return headerRow;
	}

	@Override
	public ArrayList<String> getExcelCompatibleXMLRow(ArrayList rawRow) {
		ArrayList<String> row = new ArrayList<String>();
		row.add("   <Row>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(0)+"</Data></Cell>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(1)+"</Data></Cell>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(2)+"</Data></Cell>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(3)+"</Data></Cell>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(4)+"</Data></Cell>\r\n");
	    row.add("    <Cell><Data ss:Type=\"String\">"+rawRow.get(5)+"</Data></Cell>\r\n");
	    row.add("   </Row>\r\n");
		return row;
	}

}
