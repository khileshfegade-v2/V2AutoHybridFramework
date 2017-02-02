package com.v2solutions.taf.common.logs;

import java.util.ArrayList;

public interface ExcelCompatibleXMLWorkbookIFace {

	/**<?xml version="1.0"?>
	 * <?mso-application progid="Excel.Sheet"?>
	 * <Workbook ............ upto excluding <Styles>
	 * 
	 * @return ArrayList<String>
	 */
	public abstract ArrayList<String> getStartWorkbookTagLines();
	
	
	/**<Styles>...</Styles>
	 * 
	 * @return ArrayList<String>
	 */
	public abstract ArrayList<String> getStylesTagLines();
	
	
}
