package com.v2solutions.taf.common.logs;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public interface ExcelCompatibleXMLRowIFace {
	
	public abstract ArrayList<String> getColumnDefinitions();

	public abstract ArrayList<String> getHeaderRow();
	
	public abstract ArrayList<String> getExcelCompatibleXMLRow(ArrayList rawRow);

	public abstract Logger getLogger();

}
