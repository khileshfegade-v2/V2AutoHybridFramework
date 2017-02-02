package com.v2solutions.taf.common.logs;

import java.util.ArrayList;

public class AllTestResultWorkbook implements ExcelCompatibleXMLWorkbookIFace {

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
	    startWorkbookTagLines.add("  <Author>v2solutions</Author>\r\n");
	    startWorkbookTagLines.add("  <LastAuthor>v2solutions</LastAuthor>\r\n");
	    startWorkbookTagLines.add("  <Created>2016-11-28T12:12:57Z</Created>\r\n");
	    startWorkbookTagLines.add("  <LastSaved>2016-11-28T12:12:57Z</LastSaved>\r\n");
	    startWorkbookTagLines.add("  <Version>11.00</Version>\r\n");
	    startWorkbookTagLines.add(" </DocumentProperties>\r\n");
	    startWorkbookTagLines.add(" <OfficeDocumentSettings xmlns=\"urn:schemas-microsoft-com:office:office\">\r\n");
	    startWorkbookTagLines.add("  <AllowPNG/>\r\n");
	    startWorkbookTagLines.add(" </OfficeDocumentSettings>\r\n");
	    startWorkbookTagLines.add(" <ExcelWorkbook xmlns=\"urn:schemas-microsoft-com:office:excel\">\r\n");
	    startWorkbookTagLines.add("  <WindowHeight>7995</WindowHeight>\r\n");
	    startWorkbookTagLines.add("  <WindowWidth>20115</WindowWidth>\r\n");
	    startWorkbookTagLines.add("  <WindowTopX>240</WindowTopX>\r\n");
	    startWorkbookTagLines.add("  <WindowTopY>75</WindowTopY>\r\n");
	    startWorkbookTagLines.add("  <TabRatio>317</TabRatio>\r\n");
	    startWorkbookTagLines.add("  <ActiveSheet>1</ActiveSheet>\r\n");
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
		stylesTagLines.add("  <Style ss:ID=\"s53\" ss:Name=\"Hyperlink\">\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#0000FF\"\r\n");
		stylesTagLines.add("    ss:Underline=\"Single\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s57\" ss:Name=\"Normal 2\">\r\n");
		stylesTagLines.add("   <Alignment ss:Vertical=\"Bottom\"/>\r\n");
		stylesTagLines.add("   <Borders/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"/>\r\n");
		stylesTagLines.add("   <Interior/>\r\n");
		stylesTagLines.add("   <NumberFormat/>\r\n");
		stylesTagLines.add("   <Protection/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s64\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s65\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s66\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior ss:Color=\"#FFFF00\" ss:Pattern=\"Solid\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s67\" ss:Parent=\"s57\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior ss:Color=\"#FFFF00\" ss:Pattern=\"Solid\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s68\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s69\" ss:Parent=\"s53\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s70\" ss:Parent=\"s57\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add("  <Style ss:ID=\"s71\">\r\n");
		stylesTagLines.add("   <Alignment ss:Horizontal=\"Left\" ss:Vertical=\"Top\"/>\r\n");
		stylesTagLines.add("   <Font ss:FontName=\"Calibri\" x:Family=\"Swiss\" ss:Size=\"11\" ss:Color=\"#000000\"\r\n");
		stylesTagLines.add("    ss:Bold=\"1\"/>\r\n");
		stylesTagLines.add("   <Interior/>\r\n");
		stylesTagLines.add("   <NumberFormat ss:Format=\"@\"/>\r\n");
		stylesTagLines.add("  </Style>\r\n");
		stylesTagLines.add(" </Styles>\r\n");
		
		return stylesTagLines;
	}

}
