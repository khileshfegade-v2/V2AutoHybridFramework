package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class FinalResultExcelCompatibleXMLWorksheet implements ExcelCompatibleXMLRowIFace, ExcelCompatibleXMLWorksheetIFace {

	@Override
	public String getStartWorksheetTableTagLine() {
		String startTableTagLine = "  <Table ss:ExpandedColumnCount=\"numberOfColumns\" ss:ExpandedRowCount=\"numberOfRows\" x:FullColumns=\"1\"\r\n"
	      +"   x:FullRows=\"1\" ss:StyleID=\"s64\" ss:DefaultRowHeight=\"15\">\r\n";
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
		worksheetOptionsTagLines.add("   <Panes>\r\n");
		worksheetOptionsTagLines.add("    <Pane>\r\n");
		worksheetOptionsTagLines.add("     <Number>3</Number>\r\n");
		worksheetOptionsTagLines.add("     <ActiveRow>9</ActiveRow>\r\n");
		worksheetOptionsTagLines.add("    </Pane>\r\n");
		worksheetOptionsTagLines.add("   </Panes>\r\n");
		worksheetOptionsTagLines.add("   <ProtectObjects>False</ProtectObjects>\r\n");
		worksheetOptionsTagLines.add("   <ProtectScenarios>False</ProtectScenarios>\r\n");
		worksheetOptionsTagLines.add("  </WorksheetOptions>\r\n");
		return worksheetOptionsTagLines;
	}

	@Override
	public Logger getLogger() {
		return Logger.getLogger(LoggingConstants.LOG4J_FINAL_RESULT_LOGGER);
	}

	@Override
	public ArrayList<String> getColumnDefinitions() {
		ArrayList<String> columnDefinitions = new ArrayList<String>();
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"204\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"203.25\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"183.75\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"185.25\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"183\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"111.75\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"124.5\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"88.5\"/>\r\n");
		columnDefinitions.add("   <Column ss:StyleID=\"s64\" ss:AutoFitWidth=\"0\" ss:Width=\"101.25\"/>\r\n");
	    return columnDefinitions;
	}

	@Override
	public ArrayList<String> getHeaderRow() {
		ArrayList<String> headerRow = new ArrayList<String>();
		headerRow.add("   <Row>\r\n");
		
	    String line0 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TEST_NAME+"</Data></Cell>\r\n";
	    String line1 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TOTAL_PASSED_TEST_METHODS+"</Data></Cell>\r\n";
	    String line2 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TOTAL_FAILED_TEST_METHODS+"</Data></Cell>\r\n";
	    String line3 = "    <Cell ss:StyleID=\"s66\"><Data ss:Type=\"String\">"+LoggingConstants.COLUMN_HEADING_TOTAL_SKIPPED_TEST_METHODS+"</Data></Cell>\r\n";
	    String line4 = "   </Row>\r\n";
	    headerRow.add(line0);
	    headerRow.add(line1);
	    headerRow.add(line2);
	    headerRow.add(line3);
	    headerRow.add(line4);
	    return headerRow;
	}

	@Override
	public ArrayList<String> getExcelCompatibleXMLRow(ArrayList rawRow) {
		String cell0 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(0)+"</Data></Cell>\r\n";
		String cell1 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(1)+"</Data></Cell>\r\n";
		String cell2 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(2)+"</Data></Cell>\r\n";
		String cell3 = "    <Cell><Data ss:Type=\"String\">"+rawRow.get(3)+"</Data></Cell>\r\n";
		ArrayList<String> headerRow = new ArrayList<String>();
		headerRow.add("   <Row>\r\n");
		headerRow.add(cell0);
		headerRow.add(cell1);
		headerRow.add(cell2);
		headerRow.add(cell3);
		headerRow.add("   </Row>\r\n");
		return headerRow;
	}

}
