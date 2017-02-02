package com.v2solutions.taf.common.logs;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public interface ExcelCompatibleXMLWorksheetIFace {
	
	/**
	 * <Table ............ >
	 * 
	 * @return String
	 */
	public abstract String getStartWorksheetTableTagLine();
	
	/**
	 * </Table>
	 * 
	 * @return String
	 */
	public abstract String getEndWorksheetTableTagLine();
	
	/**
	 * <WorksheetOptions...>...</WorksheetOptions>
	 * 
	 * @return ArrayList<String>
	 */
	public abstract ArrayList<String> getWorksheetOptionsTagLines();
	

	
}
