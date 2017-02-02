package com.v2solutions.taf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * This class contains utility methods for reading excel data.
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class ExcelUtil {

	private static Log log = LogUtil.getLog(ExcelUtil.class);

	/**
	 * CommonMethod(readExcelData) which reads the data from the excel sheet
	 *
	 */
	public static String[][] readExcelData(String filePath, String sheetName,
			String tableName) {
		String[][] testData = null;

		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					filePath));
			HSSFSheet sheet = workbook.getSheet(sheetName);
			log.info("sheetName------------------" + sheetName);
			HSSFCell[] boundaryCells = findCell(sheet, tableName);
			log.info("tableName------------------" + tableName);
			HSSFCell startCell = boundaryCells[0];
			HSSFCell endCell = boundaryCells[1];
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex();
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;
            testData = new String[endRow - startRow + 1][endCol - startCol + 1];

			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					if (null == sheet.getRow(i).getCell(j)
							|| sheet.getRow(i).getCell(j).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
						testData[i - startRow][j - startCol] = "";
					} else {

						if (sheet.getRow(i).getCell(j).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							testData[i - startRow][j - startCol] = sheet
									.getRow(i).getCell(j).getStringCellValue();
						} else if (sheet.getRow(i).getCell(j).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							Double temp = sheet.getRow(i).getCell(j)
									.getNumericCellValue();
							testData[i - startRow][j - startCol] = String
									.valueOf(temp.intValue());
						}
					}

				}
			}
		} catch (FileNotFoundException e) {
			log.info("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return testData;
	}



	public static HSSFCell[] findCell(HSSFSheet sheet, String text) {
		String pos = "start";
		HSSFCell[] cells = new HSSFCell[2];
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING
						&& text.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("start")) {
						cells[0] = (HSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (HSSFCell) cell;
					}
				}
			}
		}
		return cells;
	}

	public static HSSFCell[] findRange(HSSFSheet sheet, String text) {
		String pos = "start";
		HSSFCell[] cells = new HSSFCell[2];
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (text.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("start")) {
						cells[0] = (HSSFCell) cell;
						pos = "end";
					} else {
						cells[1] = (HSSFCell) cell;
					}
				}
			}
		}
		return cells;
	}
}
