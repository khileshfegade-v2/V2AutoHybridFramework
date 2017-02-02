import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import org.xml.sax.InputSource;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.log4j.Logger;
import org.apache.poi.ss.util.CellRangeAddress;
import java.util.Scanner;
import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;

//set classpath=.;d:\v2solutions\target\NewReportFormat-1.0.jar;d:\v2solutions\poi-3.13-beta1.jar;d:\eTouch\poi-ooxml-3.13-beta1.jar;d:\v2solutions\xmlbeans-xpath-2.3.0.jar;d:\v2solutions\log4j-1.2.17.jar;d:\eTouch\xmlbeans-2.6.0.jar;d:\v2solutions\ooxml-schemas-1.0.jar;C:\Users\MahendraK\.m2\repository\org\apache\httpcomponents\httpclient\4.5\httpclient-4.5.jar;C:\Users\MahendraK\.m2\repository\org\apache\httpcomponents\httpcore\4.4.4\httpcore-4.4.4.jar;C:\Users\MahendraK\.m2\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar
//javac ReadXMLFile.java
//java ReadXMLFile JobName BuildNumber Smoke/Regression $WORKSPACE NumberofSubJobs Browser BaseURL(no ending /) GoogleDocXLSXFileLocation
//java ReadXMLFile ScriptName 1 Regression workspace 4 Chrome http://www.v2solutions.com
//mvn exec:java -Dexec.mainClass="ReadXMLFile"
//java -cp %classpath% ReadXMLFile Test_Smoke_Prod_Job 1 Regression workspace 4 Chrome http://www.v2solutions.com

//location of nextBuildNumber file
///home/test-automation/.jenkins/jobs/Test_FireFox_1
//location of console log file as below where 58 is build number -1 
///home/test-automation/.jenkins/jobs/Test_FireFox_1/builds/58
//curl "http://10.177.4.241:8081/job/Test_FireFox_1/lastBuild/consoleText

	
public class ReadXMLFile 
{	
	int failedHrefIndex = 1;
	int skippedHrefIndex = 1;
	int passedHrefIndex = 1;
	int mainHrefIndex = 0;
	
	String exceptionMessage = null;
	String exceptionStackTrace = null;
	
	boolean exceptionStart = false;
	boolean messageStart = false;
	boolean stackTraceStart = false;
	
	HeaderDetails headerDetails = new HeaderDetails();
	MethodDetails methodDetails = new MethodDetails();
	
	String[] argvs = null;
	String currentClassName = null;
	boolean readMethodAndClassDetailsFromFileDone = false;
	
	BufferedWriter mainHTMLFile = null;
	BufferedWriter passMethodFile = null;
	BufferedWriter failMethodFile = null;
	BufferedWriter skippedMethodFile = null;
	
	BufferedWriter passMethodStackTraceFile = null;
	BufferedWriter failMethodStackTraceFile = null;
	BufferedWriter skippedMethodStackTraceFile = null;
	
	Hashtable methodThatNeedToReadFromFile = new Hashtable();
	Hashtable methodAndHref = new Hashtable();
	
	boolean valueStart = true;
	
	XSSFWorkbook workbook = null;
	String globalCurrentJobImageLocation = null;
	int numberOfSubJobs = 0;
	String failedJobNames[] = null;
	String subjobNames[] = null;
	boolean excludeSucessMethods = false;
	String scriptName = "";
	String buildNumber = "";
	String numberOfSubJobsStr = "";
	String jobType = "";
	String workSpaceLocation = "";
	String browserName = "";
	String baseURL = "";
	String googleDocXLSXFileLocation = "/home/test-automation/checkoutxls/FSCOM Regression Suite.xlsx";
	
	public void openExcelFile()
	{
		String xlsFileLocation = null;
		try
		{	
			try
			{
				numberOfSubJobs = Integer.parseInt(numberOfSubJobsStr);
				
			}
			catch(Exception exp)
			{
				System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
			}

			if( googleDocXLSXFileLocation != null )
				xlsFileLocation = googleDocXLSXFileLocation;
			else
			{
				if(numberOfSubJobs == 1 )
				{
					//xlsFileLocation = argv[3]+"/job/test-automation-scripts/target/test-classes/testdata/watch/FSCOM Regression Suite.xlsx";
					//xlsFileLocation = argv[3]+"/FSCOM Regression Suite.xlsx";
					//xlsFileLocation = "/home/test-automation/FSCOM Regression Suite.xlsx";
					xlsFileLocation = "/home/test-automation/checkoutxls/FSCOM Regression Suite.xlsx";
				}
				else
				{
					//System.out.println(" Opening file "+argv[3]+"/job"+(i+1)+"/test-automation-scripts/target/surefire-reports/testng-results.xml");
					//xlsFileLocation = argv[3]+"/job1/test-automation-scripts/target/test-classes/testdata/watch/FSCOM Regression Suite.xlsx";
					//xlsFileLocation = argv[3]+"/FSCOM Regression Suite.xlsx";
					xlsFileLocation = "/home/test-automation/checkoutxls/FSCOM Regression Suite.xlsx";
				}		
			}
			xlsFileLocation = xlsFileLocation.replace("//","/");
			
			System.out.println("Excel Work book location: "+xlsFileLocation);
			FileInputStream file = new FileInputStream(new File(xlsFileLocation));

			//Get the workbook instance for XLS file 
			workbook = new XSSFWorkbook (file);		
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage()+" "+xlsFileLocation);
			exp.printStackTrace()	;
		}
	}
   public ReadXMLFile(String[] pargv)
   {		
		argvs = pargv;
		
		for(int i=0;i < argvs.length;i++)
		{			
			if(argvs[i] == null ) continue;

			if(argvs[i].equals("--excludeSucessMethods") == true )			
			{	excludeSucessMethods = true; continue;}
			
			if(argvs[i].equals("--scriptName") == true && argvs[i+1] != null)			
			{	scriptName = new String(argvs[i+1]); i++;continue;}
			
			if(argvs[i].equals("--buildNumber") == true && argvs[i+1] != null)			
			{	buildNumber = new String(argvs[i+1]); i++;continue;}
			
			if(argvs[i].equals("--numberOfSubJobs") == true && argvs[i+1] != null)			
			{	numberOfSubJobsStr = new String(argvs[i+1]); i++;continue;}
			
			if(argvs[i].equals("--jobType") == true && argvs[i+1] != null)			
			{	jobType = new String(argvs[i+1]); i++;continue;}
		
			if(argvs[i].equals("--workSpaceLocation") == true && argvs[i+1] != null)			
			{	workSpaceLocation = new String(argvs[i+1]); i++;continue;}
			
			if(argvs[i].equals("--browserName") == true && argvs[i+1] != null)			
			{	browserName = new String(argvs[i+1]);	i++;continue;}
			
			if(argvs[i].equals("--baseURL") == true && argvs[i+1] != null)			
			{	baseURL = new String(argvs[i+1]);	i++;continue;}
			
			if(argvs[i].equals("--googleDocXLSXFileLocation") == true && argvs[i+1] != null)			
			{	googleDocXLSXFileLocation = new String(argvs[i+1]); i++;continue;}
		
			if(argvs[i].equals("--subjobNames") == true && argvs[i+1] != null)			
			{	
				String subjobnames = new String(argvs[i+1]);
				
				if(subjobnames != null && subjobnames.indexOf(",") < 0 )
				{
					subjobNames = new String[1];
					failedJobNames = new String[1];
					subjobNames[0] = subjobnames;
				}
				else
				{
					if(subjobnames != null && subjobnames.indexOf(",") >= 0 )
					{
						subjobNames = subjobnames.split(","); 
						failedJobNames = new String[subjobNames.length];
					}
				}
				
				i++;
				continue;
			}
		}
		
		if( jobType != null ) jobType = jobType.toLowerCase();
		
		isAnyJobFailed();
		openMainHTMLFile();
		openPassMethodFile();
		openFailedMethodFile();
		openSkippedMethodFile();
		openExcelFile();
   }
   
   
   public boolean isAnyJobFailed()
   {
	   String jobPathName = null;
	   File nextBuildNumberFile = null;
	   int buildNumber = 0;
	   Scanner nextBuildNumberScanner = null;
	   RandomAccessFile consoleLogFile = null;
	   String fileInputLine = null;
	   int failedJobIndex = -1;
	   
	   
	   try
	   {
			java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.FINEST);
			java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.FINEST);
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
			System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
			System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
			System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
		   
		   for(int i=0;i < subjobNames.length;i++)
		   {
			   try
			   {
				   /*jobPathName = null;
				   jobPathName = "/home/test-automation/.jenkins/jobs/";
				   jobPathName = jobPathName + subjobNames[i]+ "/nextBuildNumber";
				   
				   nextBuildNumberFile = new File(jobPathName);
				   
				   nextBuildNumberScanner = new Scanner(nextBuildNumberFile);
				   
				   buildNumber = nextBuildNumberScanner.nextInt();
				   
				   buildNumber = buildNumber - 1;				   
				   
				   jobPathName = null;
				   jobPathName = "/home/test-automation/.jenkins/jobs/";
				   jobPathName = jobPathName + subjobNames[i]+ "/" + "builds/"+buildNumber+"/log";
				   
				   
				   consoleLogFile = new RandomAccessFile(jobPathName,"r");
				   
				   while(true)
				   {
					   fileInputLine = null;
					   
					   fileInputLine =  consoleLogFile.readLine();
					   
					   if(fileInputLine == null ) break;					   
					   
					   if(fileInputLine.startsWith("[ERROR] Failed to execute goal org.apache.maven.plugins:maven-install-plugin:2.3.1:install (default-install) on project test-automation-library: Failed to install metadata") == true )
					   {
						   failedJobIndex++;
						   
						   failedJobNames[failedJobIndex] = subjobNames[i];
						   
						   break;
					   }
				   }
				   
				   consoleLogFile.close();*/
				   
				   //while(true)
				   //{
						jobPathName = null;
						jobPathName = "http://10.177.4.241:8081/job/"+subjobNames[i]+"/lastBuild/consoleText";
						System.out.println(">>>> Checking : "+jobPathName);
						CloseableHttpClient httpclient = HttpClients.createDefault();
						HttpGet httpGet = new HttpGet(jobPathName);
						CloseableHttpResponse response1 = httpclient.execute(httpGet);
						BufferedReader reader = null;
						
						try 
						{
							System.out.println(response1.getStatusLine());
							
							HttpEntity entity1 = response1.getEntity();
							
							reader = new BufferedReader(new InputStreamReader(entity1.getContent()));
							
							while (true) 
							{
								fileInputLine = null;
								
								fileInputLine = reader.readLine();
								
								if(fileInputLine == null ) break;					   
						   
								//System.out.println(">>>> "+fileInputLine);
								
								if(
									fileInputLine.startsWith("[ERROR] Failed to execute goal org.apache.maven.plugins:maven-install-plugin:2.3.1:install (default-install) on project test-automation-library: Failed to install metadata") == true ||
									fileInputLine.startsWith("Build step 'Invoke top-level Maven targets' marked build as failure") == true ||
									fileInputLine.startsWith("[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.16:test (default-test) on project test-automation-scripts: Execution default-test of goal") == true
									)
								{
									failedJobIndex++;
							   
									failedJobNames[failedJobIndex] = subjobNames[i];
							   
									break;
								}
							}
							EntityUtils.consume(entity1);
						} 
						finally 
						{
							response1.close();
							httpclient = null;
							httpGet = null;
							response1 = null;
							reader = null;
						}
				   //}
			   }
			   catch(Exception exp)
			   {
				   System.out.println("Exception occured: "+exp.getMessage());
				   exp.printStackTrace();
			   }
		   }
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception occured: "+exp.getMessage());
		   exp.printStackTrace();
	   }
	   finally
	   {
			jobPathName = null;
			nextBuildNumberFile = null;
			buildNumber = 0;
			nextBuildNumberScanner = null;
			consoleLogFile = null;
			fileInputLine = null;			
	   }
	   if (failedJobIndex == -1 )
		   return false;
	   
	   return true;
	   //xx
   }
   public void closeFiles()
   {
	   try
	   {
			passMethodFile.write("\t</table>");
			failMethodFile.write("\t</table>");
			skippedMethodFile.write("\t</table>");
			
			passMethodStackTraceFile.close();
			failMethodStackTraceFile.close();
			skippedMethodStackTraceFile.close();
	
			mainHTMLFile.close();
			passMethodFile.close();
			failMethodFile.close();
			skippedMethodFile.close();						
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }
   }
   
   public void combineFiles()
   {
	   try
	   {
			String fileNames[] = new String[] {"failed.html","skipped.html","pass.html","failedstacktrace.html","skippedstacktrace.html","passstacktrace.html"};
		   
		    File file = new File("report_"+buildNumber+".html");
			System.out.println("---------------------------------------------- "+file);
			BufferedWriter out = new BufferedWriter(new FileWriter("report_"+buildNumber+".html", true));

			
			for(int i=0;i < fileNames.length;i++)
			{
				if(excludeSucessMethods == true && fileNames[i].equals("pass.html") == true ) 
				{
					continue;
				}
				BufferedReader in = new BufferedReader(new FileReader(fileNames[i]));
				
				String str = null;
				
				while ((str = in.readLine()) != null) 
				{
					out.write(str+"\n");
					str = null;
				}
				in.close();
			}
			
			out.write("</body>\n");
			out.write("</html>\n");
			
			out.close();		   
	   }
	   catch(Exception exp)
	   {
	       System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }		
   }
   public void openMainHTMLFile()
   {
	   try
	   {
			File fout = new File("report_"+buildNumber+".html");
			FileOutputStream fos = new FileOutputStream(fout);

			mainHTMLFile = new BufferedWriter(new OutputStreamWriter(fos));
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }
   }
   public void openPassMethodFile()
   {
	   try
	   {
			File fout = new File("pass.html");
			FileOutputStream fos = new FileOutputStream(fout);

			passMethodFile = new BufferedWriter(new OutputStreamWriter(fos));
			
			passMethodFile.write("\t<table cellspacing=0 cellpadding=0 class=\"passed\" width=\"100%\">\n");
			passMethodFile.write("\t\t<tr><th colspan=\"10\"><font color=\"green\">Test Passed</font></th></tr>\n");
			passMethodFile.write("\t\t<tr>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">TC# + Layout</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Test Case</th>\n");
			//passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Layout</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">URL</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Description</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Browser</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Expected</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Actual</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Known</th>\n");
			passMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Pri</th>\n");
			passMethodFile.write("\t\t</tr>\n");
			
			File fout1 = new File("passstacktrace.html");
			FileOutputStream fos1 = new FileOutputStream(fout1);

			passMethodStackTraceFile = new BufferedWriter(new OutputStreamWriter(fos1));
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }	   
   }
   public void openFailedMethodFile()
   {
	   try
	   {
			File fout = new File("failed.html");
			FileOutputStream fos = new FileOutputStream(fout);

			failMethodFile = new BufferedWriter(new OutputStreamWriter(fos));	   
			
			failMethodFile.write("\t<table cellspacing=0 cellpadding=0 class=\"passed\" width=\"100%\">\n");
			failMethodFile.write("\t\t<tr><th colspan=\"10\"><font color=\"red\">Test Failed</font></th></tr>\n");
			failMethodFile.write("\t\t<tr>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">TC# + Layout</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Test Case</th>\n");
			//failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Layout</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">URL</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Description</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Browser</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Expected</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Actual</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Known</th>\n");
			failMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Pri</th>\n");
			failMethodFile.write("\t\t</tr>\n");
			
			
			
			File fout1 = new File("failedstacktrace.html");
			FileOutputStream fos1 = new FileOutputStream(fout1);

			failMethodStackTraceFile = new BufferedWriter(new OutputStreamWriter(fos1));	   
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }
		
   }
   public void openSkippedMethodFile()
   {
	   try
	   {
			File fout = new File("skipped.html");
			FileOutputStream fos = new FileOutputStream(fout);

			skippedMethodFile  = new BufferedWriter(new OutputStreamWriter(fos));	   	   
			
			skippedMethodFile.write("\t<table cellspacing=0 cellpadding=0 class=\"passed\" width=\"100%\">\n");
			skippedMethodFile.write("\t\t<tr><th colspan=\"10\"><font color=\"grey\">Test Skipped</font></th></tr>\n");
			skippedMethodFile.write("\t\t<tr>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">TC# + Layout</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Test Case</th>\n");
			//skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Layout</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">URL</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Description</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Browser</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Expected</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Actual</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Known</th>\n");
			skippedMethodFile.write("\t\t\t<th bgcolor=\"#6699FF\">Pri</th>\n");
			skippedMethodFile.write("\t\t</tr>\n");			
			
			File fout1 = new File("skippedstacktrace.html");
			FileOutputStream fos1 = new FileOutputStream(fout1);

			skippedMethodStackTraceFile = new BufferedWriter(new OutputStreamWriter(fos1));	   
	   }
	   catch(Exception exp)
	   {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	   }	   
   }

   
	MethodDetailsFromFile methodDetailsFromFile = null;
	public void readMethodAndClassDetailsFromFile()
	{
		try
		{
			
			//FileInputStream file = new FileInputStream(new File("C:\\Users\\MahendraK\\Downloads\\FSCOM Regression Suite.xlsx"));

			//Get the workbook instance for XLS file 
			//XSSFWorkbook workbook = new XSSFWorkbook (file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				
				Cell classCell = row.getCell(10);
				Cell methodCell = row.getCell(11);
				
				String className = getCellData(sheet,rowIterator,row.getRowNum(),10,row,classCell);
				String methodName = getCellData(sheet,rowIterator,row.getRowNum(),11,row,methodCell);
				
				
				if(className != null && methodName != null )
				{
					className = className.trim();
					methodName = methodName.trim();
										
					if(className.length() > 0 && methodName.length() > 0 )
					{
						className = className.replace(".java","");
						
						MethodDetailsFromFile tempMethodDetailsFromFile = (MethodDetailsFromFile) methodThatNeedToReadFromFile.get(className + "." + methodName);
						
						if(tempMethodDetailsFromFile != null && tempMethodDetailsFromFile.alreadyRead == false)
						{												
							tempMethodDetailsFromFile.testCaseNumber = getCellData(sheet,rowIterator,row.getRowNum(),0,row,row.getCell(0));							
							tempMethodDetailsFromFile.userStory = getCellData(sheet,rowIterator,row.getRowNum(),1,row,row.getCell(1));
							tempMethodDetailsFromFile.layout = getCellData(sheet,rowIterator,row.getRowNum(),3,row,row.getCell(3));
							tempMethodDetailsFromFile.url = getCellData(sheet,rowIterator,row.getRowNum(),4,row,row.getCell(4));
							tempMethodDetailsFromFile.known = getCellData(sheet,rowIterator,row.getRowNum(),14,row,row.getCell(14));
							tempMethodDetailsFromFile.pri = getCellData(sheet,rowIterator,row.getRowNum(),8,row,row.getCell(8));
							tempMethodDetailsFromFile.description = getCellData(sheet,rowIterator,row.getRowNum(),5,row,row.getCell(5));
							tempMethodDetailsFromFile.expected = getCellData(sheet,rowIterator,row.getRowNum(),6,row,row.getCell(6));
							tempMethodDetailsFromFile.alreadyRead = true;
						}
					}
				}
			}
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	
	public String getCellData(XSSFSheet sheet,Iterator<Row> rowIterator,int prowNum ,int columnIndex,Row row,Cell cell)
	{
		//first column value which is test case number like FA-TC-125
		if(cell == null ) return "";
		String stringValue = cell.getStringCellValue();
		if(stringValue == null )
			stringValue = cell.getRichStringCellValue().getString();
					
		if(stringValue != null)
		{
			stringValue = stringValue.trim();
			
			//if(stringValue.equals(rowID) == true )
			{						
				Cell cell1 = row.getCell(columnIndex);
				
				if(cell1 != null )
				{														
					
					stringValue = cell1.getStringCellValue();
					
					if(stringValue == null )
						stringValue = cell.getRichStringCellValue().getString();
					
					if(stringValue == null)
					{																						
						stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
						if(stringValue == null)								
							return "";								
						return stringValue;
					}			
					else								
					{
						stringValue = stringValue.trim();
						return stringValue;																
					}				
				}
				else
				{										
					stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
												
					if(stringValue == null)
						return "";
					return stringValue;
				}
				
			}
		}		
		
		return null;
	}
	/*public void readMethodAndClassDetailsFromFile()
	{
		try
		{
			File fout = new File("ClassMethodDetails.txt");
			FileInputStream fos = new FileInputStream(fout);

			BufferedReader fileReader = new BufferedReader(new InputStreamReader(fos));
			
			String line = null;
			
			while(true)
			{
				line = null;
				
				line = fileReader.readLine();
				
				if(line == null ) break;
				
				String plitLine[] = line.split(",");
				
				MethodDetailsFromFile tempMethodDetailsFromFile = null;
				
				if(plitLine != null && plitLine.length >= 2 && plitLine[0] != null && plitLine[1] != null)
				{					
					tempMethodDetailsFromFile = (MethodDetailsFromFile) methodThatNeedToReadFromFile.get(plitLine[0]+"."+plitLine[1]);
					
					if(tempMethodDetailsFromFile != null )
					{
						if( plitLine.length >= 3 && plitLine[2] != null )
							tempMethodDetailsFromFile.testCaseNumber = plitLine[2];
						
						if( plitLine.length >= 4 && plitLine[3] != null )
							tempMethodDetailsFromFile.userStory = plitLine[3];
												
						if( plitLine.length >= 5 && plitLine[4] != null )
							tempMethodDetailsFromFile.layout = plitLine[4];

						if( plitLine.length >= 6 && plitLine[5] != null )
						{							
							tempMethodDetailsFromFile.url = argv[6]+plitLine[5];
						}
													
						if( plitLine.length >= 7 && plitLine[6] != null )
							tempMethodDetailsFromFile.known = plitLine[6];

						if( plitLine.length >= 8 && plitLine[7] != null )
							tempMethodDetailsFromFile.pri = plitLine[7];
						
						if( plitLine.length >= 9 && plitLine[8] != null )
							tempMethodDetailsFromFile.description = plitLine[8];
					}
				}	
			}
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}*/
   public void parseReport() 
   {
		
		printHTMLHeader();		
		try 
		{
			DefaultHandler handler = new DefaultHandler() 
			{
				public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException 
				{					
					
					if( qName != null && qName.length() > 0 && qName.equals("method") == true )
					{
						methodDetailsFromFile = null;
						methodDetailsFromFile = new MethodDetailsFromFile();
						methodDetailsFromFile.className = attributes.getValue("class");
						methodDetailsFromFile.methodName = attributes.getValue("name");
						
						methodDetailsFromFile.className = methodDetailsFromFile.className.trim();
						methodDetailsFromFile.methodName = methodDetailsFromFile.methodName.trim();
						
						try
						{
							methodThatNeedToReadFromFile.put(methodDetailsFromFile.className+"."+methodDetailsFromFile.methodName,methodDetailsFromFile);
						}
						catch(Exception exp)
						{
							
						}
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("testng-results") == true && headerDetails != null)
					{						
						/*headerDetails.skipped = attributes.getValue("skipped");
						headerDetails.failed = attributes.getValue("failed");
						headerDetails.total = attributes.getValue("total");
						headerDetails.passed = attributes.getValue("passed");*/
						
						headerDetails.addSkipped(attributes.getValue("skipped"));
						headerDetails.addFailed(attributes.getValue("failed"));
						headerDetails.addPassed(attributes.getValue("passed"));
						headerDetails.addTotal(attributes.getValue("total"));
						//headerDetails.addDuration(String pStrNumber)
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("suite") == true && headerDetails != null)
					{
						headerDetails.suitename = attributes.getValue("suitename");
						//headerDetails.duration_ms = attributes.getValue("duration-ms");
						headerDetails.addDuration(attributes.getValue("duration-ms"));
						headerDetails.started_at = attributes.getValue("started-at");
						headerDetails.finished_at = attributes.getValue("finished-at");
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("group") == true && headerDetails != null)
					{
						if(headerDetails != null)
							headerDetails.addGroupName(attributes.getValue("name"));
							//headerDetails.groupdName.add(attributes.getValue("name"));
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("test") == true )
					{
						//if(headerDetails != null)
							//printResult();
						
						if(readMethodAndClassDetailsFromFileDone == false )
						{
							readMethodAndClassDetailsFromFileDone = true;
							readMethodAndClassDetailsFromFile();		
						}
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("class") == true )
						currentClassName = attributes.getValue("name");
					
					if( qName != null && qName.length() > 0 && qName.equals("test-method") == true )
					{
						methodDetails = null;
						if(attributes.getValue("signature") != null 
							&& ( attributes.getValue("signature").startsWith("prepareBeforeClass") == false 
							&& attributes.getValue("signature").startsWith("printTime") == false 
							&& attributes.getValue("signature").startsWith("tearDown") == false
							&& attributes.getValue("signature").startsWith("close") == false
							&& attributes.getValue("signature").startsWith("shutDownDriver") == false
							) )
							{					
								methodDetails = null;
								methodDetails = new MethodDetails();
								
								methodDetails.className = currentClassName;
	
								methodDetails.status = attributes.getValue("status");
								methodDetails.signature = attributes.getValue("signature");
								methodDetails.name = attributes.getValue("name");
								methodDetails.duration_ms = attributes.getValue("duration-ms");
								methodDetails.started_at = attributes.getValue("started-at");
								methodDetails.description = attributes.getValue("description");
								methodDetails.finished_at = attributes.getValue("finished-at");				

								methodDetailsFromFile = null;
								methodDetailsFromFile = new MethodDetailsFromFile();
								methodDetailsFromFile.className = currentClassName;
								methodDetailsFromFile.methodName = methodDetails.name;
								
								methodDetailsFromFile.className = methodDetailsFromFile.className.trim();
								methodDetailsFromFile.methodName = methodDetailsFromFile.methodName.trim();
								try
								{
									//MethodDetailsFromFile tempMethodDetailsFromFile = (MethodDetailsFromFile) methodThatNeedToReadFromFile.get(methodDetails.className + "." + methodDetailsFromFile.methodName);
						
									//if(tempMethodDetailsFromFile != null && tempMethodDetailsFromFile.alreadyRead == false)
									{
										methodThatNeedToReadFromFile.put(methodDetailsFromFile.className+"."+methodDetailsFromFile.methodName,methodDetailsFromFile);
										readMethodAndClassDetailsFromFile();
									}
								}
								catch(Exception exp)
								{
									
								}
							}
							else
								methodDetails = null;
					}
					
					if( qName != null && qName.length() > 0 && qName.equals("value") == true )
						valueStart = true;
					
					if( qName != null && qName.length() > 0 && qName.equals("exception") == true )
						exceptionStart = true;
					
					if( qName != null && qName.length() > 0 && exceptionStart == true && qName.equals("message") == true )
						messageStart = true;
					
					if( qName != null && qName.length() > 0 && exceptionStart == true && qName.equals("full-stacktrace") == true )
						stackTraceStart = true;

				}
				public void endElement(String uri, String localName,String qName) throws SAXException 
				{
					if( qName != null && qName.length() > 0 && qName.equals("exception") == true && exceptionStart == true)
						exceptionStart = false;
					
					if( qName != null && qName.length() > 0 && exceptionStart == true && qName.equals("message") == true && messageStart == true )
						messageStart = false;
					
					if( qName != null && qName.length() > 0 && exceptionStart == true && qName.equals("full-stacktrace") == true && stackTraceStart == true)
						stackTraceStart = false;
					
					if( qName != null && qName.length() > 0 && qName.equals("value") == true )
						valueStart = false;
					
					if( qName != null && qName.length() > 0 && qName.equals("class") == true )
						currentClassName = "";
					
					if( qName != null && qName.length() > 0 && qName.equals("test-method") == true && methodDetails != null)
					{									
						printMethodDetails();
						printStackTrace();
						methodDetails = null;						
					}
					if( qName != null && qName.length() > 0 && qName.equals("method") == true && methodDetailsFromFile != null )
					{						
						//methodThatNeedToReadFromFile.put(methodDetailsFromFile.className+"."+methodDetailsFromFile.methodName,methodDetailsFromFile);
						
						
						methodDetailsFromFile = null;
					}
				}

				public void characters(char ch[], int start, int length) throws SAXException 
				{					
					if(valueStart == true && ch != null && ch.length > 0 && methodDetails != null)
					{
						String tempStr = new String(ch,start,length);
						tempStr = tempStr.trim();
						
						if(tempStr.length() > 0)
							methodDetails.parameters.add(new String(ch,start,length));
					}
					
					if(stackTraceStart == true && ch != null && ch.length > 0 && methodDetails != null)
					{
						String tempStr = new String(ch,start,length);
						tempStr = tempStr.trim();
						
						if(tempStr.length() > 0)
							methodDetails.exceptionStackTrace = new String(ch,start,length);
					}
					
					if(messageStart == true && ch != null && ch.length > 0 && methodDetails != null)
					{
						String tempStr = new String(ch,start,length);
						tempStr = tempStr.trim();
						
						if(tempStr.length() > 0)
							methodDetails.exceptionMessage = new String(ch,start,length);						
					}
				}
			};
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			String jobWorkSpaceReportLocation = null;
			
			try
			{
				numberOfSubJobs = Integer.parseInt(numberOfSubJobsStr);
				
			}
			catch(Exception exp)
			{
				System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
			}
			
			if(numberOfSubJobs == 1 )
			{
				jobWorkSpaceReportLocation = workSpaceLocation+"/job/test-automation-scripts/target/surefire-reports/testng-results.xml";
				globalCurrentJobImageLocation = "/home/test-automation/jenkins/slave2/workspace/"+scriptName+"/test-automation-scripts/logs/";
				saxParser.parse(jobWorkSpaceReportLocation, handler); 			
			}
			else
			{
				for(int i=0;i<numberOfSubJobs;i++)
				{
					//System.out.println(" Opening file "+argv[3]+"/job"+(i+1)+"/test-automation-scripts/target/surefire-reports/testng-results.xml");
					jobWorkSpaceReportLocation = workSpaceLocation+"/job"+(i+1)+"/test-automation-scripts/target/surefire-reports/testng-results.xml";
					globalCurrentJobImageLocation = null;
					// /home/test-automation/.jenkins/jobs/Test_QA1_Regression_FF_Job/builds/13
					//globalCurrentJobImageLocation = argv[3]+"/job"+(i+1)+"/test-automation-scripts/logs/";
					//globalCurrentJobImageLocation = "/home/test-automation/.jenkins/jobs/"+scriptName+"_"+(i+1)+"/builds/"+buildNumber+"/";
					globalCurrentJobImageLocation = "/home/test-automation/jenkins/slave2/workspace/"+scriptName+"_"+(i+1)+"/test-automation-scripts/logs/";
					saxParser.parse(jobWorkSpaceReportLocation, handler); 			
				}
			}
			
			if(headerDetails != null)
				printResult();
				
		} 
		catch(Exception exp)
	    {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	    }	
		
		closeReport();
		
		closeFiles();
		
		combineFiles();
		
		String fileNames[] = new String[] {"failed.html","skipped.html","pass.html","failedstacktrace.html","skippedstacktrace.html","passstacktrace.html"};
		for(int i=0;i < fileNames.length;i++)
		{
			try
			{
				File fileToDelete = new File(fileNames[i]);
				fileToDelete.delete();
			}
			catch(Exception exp)
			{
				
			}
		}
		//System.out.println("***************************************** "+methodThatNeedToReadFromFile.size());
   }
   
   public void printStackTrace()
   {
	   BufferedWriter temp = null;
	   String tableClassName = null;
	   String trClassName = null;
	   String color = null;
	   String textFromGoogleDoc = "";

	   if(excludeSucessMethods == true && methodDetails.status != null && methodDetails.status.equals("PASS") == true ) return;
	   
	   if(methodDetails.status != null && methodDetails.status.equals("FAIL") == true ) { temp = failMethodStackTraceFile; tableClassName = "failed"; trClassName = "failedodd";color="red";}
	   if(methodDetails.status != null && methodDetails.status.equals("PASS") == true ) { temp = passMethodStackTraceFile; tableClassName = "passed"; trClassName = "passedodd";color="green";}
	   if(methodDetails.status != null && methodDetails.status.equals("SKIP") == true ) { temp = skippedMethodStackTraceFile; tableClassName = "passed"; trClassName = "skippedodd";color="gray";}

	   MethodDetailsFromFile tempMethodDetailsFromFile = null;

		tempMethodDetailsFromFile = (MethodDetailsFromFile) methodThatNeedToReadFromFile.get(methodDetails.className+"."+methodDetails.name);		
		
		if( tempMethodDetailsFromFile == null  )
		{			
			tempMethodDetailsFromFile = new MethodDetailsFromFile();
		}		
	   if(methodDetails.description == null ) methodDetails.description = "";
	   
	   try
	   {
		    
			String hrefToPut = (String) methodAndHref.get(methodDetails.className+"."+methodDetails.name+" (\" "+methodDetails.description+" \")");
			String str = null;
			
			if(hrefToPut != null)
				str = hrefToPut.replace("#","");
			else
				str = "";
			
			temp.write("\t<a id=\""+str+"\"></a>");
			temp.write("\t<table cellspacing=0 cellpadding=0 width=\"100%\" >\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td colspan=\"3\" width=\"100%\">\n");
			temp.write("\t\t\t\t<table cellspacing=\"0\" cellpadding=\"0\" class=\"param\" width=\"100%\"> \n");
			temp.write("\t\t\t\t\t<tbody>\n");
			temp.write("\t\t\t\t\t\t<tr>\n");			
			temp.write("\t\t\t\t\t\t\t<td colspan=\""+methodDetails.parameters.size()+"\" align\"center\"><center><h2>"+methodDetails.className+"."+methodDetails.name+"</h2></center></td>\n");
			temp.write("\t\t\t\t\t\t</tr> \n");

			temp.write("\t\t\t\t\t\t<tr>\n");			
			for(int i = 0 ;i <  methodDetails.parameters.size();i++)
			{
				temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:1em;padding-right:1em\">Parameter #"+(i+1)+"</td>\n");
				//temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:1em;padding-right:1em\">"+methodDetails.parameters.get(i)+"</td>\n");
			}
			
			temp.write("\t\t\t\t\t\t</tr> \n");
			temp.write("\t\t\t\t\t\t<tr>\n");
			
			for(int i = 0 ;i <  methodDetails.parameters.size();i++)
			{
				//temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:1em;padding-right:1em\">Parameter #"+(i+1)+"</td>\n");
				temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:1em;padding-right:1em\">"+methodDetails.parameters.get(i)+"</td>\n");
			}
			//temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:.5em;padding-right:2em\">//*[@class='Intro---Tablet-Desktop clearfix colelem']/p</td> \n");
			//temp.write("\t\t\t\t\t\t\t<td style=\"padding-left:.5em;padding-right:2em\">__Antenna_5</td> \n");
			
				
			temp.write("\t\t\t\t\t\t</tr> \t\t\t\t\t\t\n");
			temp.write("\t\t\t\t\t</tbody>\n");
			temp.write("\t\t\t\t</table>\t\t\t\n");
			temp.write("\t\t\t</td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">TC#</td>\n");
			temp.write("\t\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\"><a href=\"https://docs.google.com/spreadsheets/d/1-RshlP0NKhhgelj6dfArJMlSRxJma2b6CSKXix1e0iY/edit?pli=1#gid=0\" target=\"_blank\">"+tempMethodDetailsFromFile.testCaseNumber+"</a></font></td>\n");
			
			////System.out.println("-------------------------------------------" + methodDetails.exceptionStackTrace);
			methodDetails.exceptionStackTrace = methodDetails.exceptionStackTrace.replaceAll("\tat ","</br> at ");
			
			////System.out.println("-------------------------------------------" + methodDetails.exceptionStackTrace);
			temp.write("\t\t\t\t<td width=\"70%\" rowspan=\"12\" style=\"font-size:12px;padding-left:10px;border-bottom:1px solid black;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+methodDetails.exceptionMessage + "</p>" + methodDetails.exceptionStackTrace+"</font> </td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Test Case</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+tempMethodDetailsFromFile.testCase+"</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">URL</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><a href=\""+tempMethodDetailsFromFile.url+"\" target=\"_blank\"><font color=\""+color+"\">"+tempMethodDetailsFromFile.url+"</font></a></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Description</td>\n");
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,9);
			}
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";		
			if(textFromGoogleDoc.length() == 0 )
				textFromGoogleDoc = methodDetails.description;
			else
				textFromGoogleDoc = textFromGoogleDoc+"( "+methodDetails.description+" )";
			
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+textFromGoogleDoc+"</font></td>\n");
			
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Expected</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+tempMethodDetailsFromFile.expected+"</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Actual</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\"></font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">User Story</td>\n");

			String tempStroryName = "";
			String storyHyperLink = "";
			
			if(tempMethodDetailsFromFile.userStory != null )
			{
				tempMethodDetailsFromFile.userStory = tempMethodDetailsFromFile.userStory.trim();
				
				if( tempMethodDetailsFromFile.userStory.startsWith("FSCOM") == true )
				{
					Scanner in = new Scanner(tempMethodDetailsFromFile.userStory).useDelimiter("[^0-9]+");
					int integer = in.nextInt();
					if(integer > 0 )
					{
						tempStroryName = "FSCOM-"+integer;
						storyHyperLink = tempStroryName;
					}
					else
					{
						tempStroryName = tempMethodDetailsFromFile.userStory;						
					}
					in = null;
				}
			}
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><a href=\"https://foxnetworksgroup.atlassian.net/browse/"+storyHyperLink+"\" target=\"_blank\"><font color=\""+color+"\">"+tempStroryName+"</font></a></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Test Method Name</font></td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+methodDetails.className+"."+methodDetails.name+"()</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Execution Time</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+methodDetails.duration_ms+" ms</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Component/</br>Category/</br>Type</td>\n");
			
			//Component/Type/Category
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,2);
			}
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";			
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+textFromGoogleDoc+"</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Layout</td>\n");
			temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-right: 1px solid black;border-top: 1px solid black;\"><font color=\""+color+"\">"+tempMethodDetailsFromFile.layout+"</font></td>\n");
			temp.write("\t\t</tr>\n");
			temp.write("\t\t<tr>\n");
			temp.write("\t\t\t<td bgcolor=\"#6699FF\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-bottom:1px solid black;border-left: 1px solid black;border-right: 1px solid black;border-top: 1px solid black;\">Screenshot</td>\n");

			String imageName = "No Screenshot";
			
			File imageFilesArray[] = findFilesForId(new File(globalCurrentJobImageLocation) ,methodDetails.className.substring(methodDetails.className.lastIndexOf(".")+1) +"-"+methodDetails.name+"-");
			
			if(imageFilesArray != null && imageFilesArray.length > 0 )
			{
				imageName = imageFilesArray[0].toString();
				imageName = imageName.substring(imageName.lastIndexOf("/")+1);
				imageName = imageName;
				imageName = imageName.replace("//","/");
				System.out.println("***************** "+imageName);
			}
			if(imageName != null && imageName.equals("No Screenshot") == true )
				temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-bottom:1px solid black;border-right: 1px solid black;border-top: 1px solid black;\"><img alt=\"No Image\" src=\"\" width=\"50\" height=\"50\"></td>\n");
			else
				temp.write("\t\t\t<td bgcolor=\"#ffffff\" width=\"12%\" style=\"font-size:12px;padding-left:10px;border-bottom:1px solid black;border-right: 1px solid black;border-top: 1px solid black;\"><a href=\""+imageName+"\" target=\"_blank\"><img alt=\"No Image\" src=\""+imageName+"\" width=\"50\" height=\"50\"></a></td>\n");
			
			temp.write("\t\t</tr>\t\t\n");
			temp.write("\t</table>\n");		   
			
			if(imageFilesArray != null && imageFilesArray.length > 0 )
			{
				for(int i=0; i < imageFilesArray.length;i++)
					imageFilesArray[i] = null;
			}
			
			imageFilesArray = null;	
	    }
		catch(Exception exp)
	    {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	    }		   	   
   }
   
   public  File[] findFilesForId(File dir, final String id) 
   {
		return dir.listFiles(new FileFilter() 
		{
			public boolean accept(File pathname) 
			{
				//System.out.println("File : "+pathname+" Index: "+pathname.getName().indexOf(id )+" '"+id+"' "+pathname.getName().endsWith(".jpg"));
				if(pathname.getName().indexOf(id ) >= 0 && pathname.getName().endsWith(".jpg") == true )
				{
					System.out.println("Accepting file : "+pathname);
					String fileName = pathname.toString();
					String destinationFileLocation = workSpaceLocation+fileName.substring(fileName.lastIndexOf("/")+1);
					destinationFileLocation = destinationFileLocation.replace("//","/");
					System.out.println("Copying to : "+destinationFileLocation);
					
					FileChannel sourceChannel = null;
					FileChannel destChannel = null;
						
					try 
					{
						
						File source = new File(fileName);
						File dest = new File(destinationFileLocation);
						
						sourceChannel = new FileInputStream(source).getChannel();
						destChannel = new FileOutputStream(dest).getChannel();
						destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
					}
					catch(Exception exp)
					{
						System.out.println("Exception occured: "+exp.getMessage());
					}
					finally
					{
						try{sourceChannel.close();}catch(Exception exp){}
						try{destChannel.close();}catch(Exception exp){}
					}
					return true;
				}
				
				return false;
			}
		});
	}
	
   public void printMethodDetails()
   {
	   BufferedWriter temp = null;
	   String tableClassName = null;
	   String trClassName = null;
	   String generatedHref = null;
	   String textFromGoogleDoc = "";
	   
	   if(excludeSucessMethods == true && methodDetails.status != null && methodDetails.status.equals("PASS") == true ) return;
	   
	   if(methodDetails.status != null && methodDetails.status.equals("FAIL") == true ) { temp = failMethodFile; tableClassName = "failed"; trClassName = "failedodd";failedHrefIndex++;mainHrefIndex = failedHrefIndex;generatedHref = "#f"+mainHrefIndex;}
	   if(methodDetails.status != null && methodDetails.status.equals("PASS") == true ) { temp = passMethodFile; tableClassName = "passed"; trClassName = "passedodd";skippedHrefIndex++;mainHrefIndex = skippedHrefIndex;generatedHref = "#s"+mainHrefIndex;}
	   if(methodDetails.status != null && methodDetails.status.equals("SKIP") == true ) { temp = skippedMethodFile; tableClassName = "passed"; trClassName = "skippedodd";passedHrefIndex++;mainHrefIndex = passedHrefIndex;generatedHref = "#p"+mainHrefIndex;}
	   
	   //if(methodDetails.signature != null ) 
		 //  methodDetails.signature = methodDetails.signature.substring(0,methodDetails.signature.indexOf("("));
	   
		MethodDetailsFromFile tempMethodDetailsFromFile = null;
		
		tempMethodDetailsFromFile = (MethodDetailsFromFile) methodThatNeedToReadFromFile.get(methodDetails.className+"."+methodDetails.name);		
				
		if( tempMethodDetailsFromFile == null  )
		{						
			tempMethodDetailsFromFile = new MethodDetailsFromFile();
		}				
		methodAndHref.put(methodDetails.className+"."+methodDetails.name+" (\" "+methodDetails.description+" \")",generatedHref);
		
	   if(methodDetails.description == null ) methodDetails.description = "";

	   
		try
		{
			temp.write("\t\t<tr class=\""+trClassName+"\">\n");
			
			//Test Case Number TC# + Layout
			//first get layout from google doc
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,3);				
			}
			
			if( textFromGoogleDoc == null || textFromGoogleDoc.length() == 0) 
			{
				temp.write("\t\t\t<td rowspan=\"1\" nowrap><a href=\"https://docs.google.com/spreadsheets/d/1-RshlP0NKhhgelj6dfArJMlSRxJma2b6CSKXix1e0iY/edit?pli=1#gid=0\" target=\"_blank\">"+tempMethodDetailsFromFile.testCaseNumber+"</a></td>\n");
				textFromGoogleDoc = "";
			}
			else
			{
				textFromGoogleDoc = textFromGoogleDoc.replace("Column","Col");
				temp.write("\t\t\t<td rowspan=\"1\" nowrap><a href=\"https://docs.google.com/spreadsheets/d/1-RshlP0NKhhgelj6dfArJMlSRxJma2b6CSKXix1e0iY/edit?pli=1#gid=0\" target=\"_blank\">"+tempMethodDetailsFromFile.testCaseNumber+" - "+textFromGoogleDoc+"</a></td>\n");
			}
			tempMethodDetailsFromFile.layout = textFromGoogleDoc;
			
			
			textFromGoogleDoc = null;
			
			//Test Case - Test Case Action 
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,5);
			}
			
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";
			temp.write("\t\t\t<td>"+textFromGoogleDoc+"</td>\n"); tempMethodDetailsFromFile.testCase = textFromGoogleDoc;textFromGoogleDoc = null;
			
			//Layout			
			//if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			//{
				//textFromGoogleDoc = readDataFromExcelFile.readData(tempMethodDetailsFromFile.testCaseNumber,3);				
			//}
			//if( textFromGoogleDoc == null || textFromGoogleDoc.length() == 0) textFromGoogleDoc = "--";			
			//temp.write("\t\t\t<td>&nbsp;</td>\n"); //tempMethodDetailsFromFile.layout = textFromGoogleDoc;textFromGoogleDoc = null;
			
			//url
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,4);								
			}
			
			if(textFromGoogleDoc == null ) textFromGoogleDoc = "";
			else textFromGoogleDoc = textFromGoogleDoc.trim();
			
			tempMethodDetailsFromFile.url = textFromGoogleDoc;
			if(textFromGoogleDoc != null && textFromGoogleDoc.length() > 0 )
			{				
				if( baseURL != null)
				{
					if(baseURL.endsWith("/") == true )
					{
						if(textFromGoogleDoc.startsWith("/") == true )
						{
							if(textFromGoogleDoc.length() > 0 )
								textFromGoogleDoc = baseURL + textFromGoogleDoc.substring(1);
						}
						else
							textFromGoogleDoc = baseURL + textFromGoogleDoc;
					}	
					else
					{
						if(textFromGoogleDoc.startsWith("/") == true && textFromGoogleDoc.length() > 0)
							textFromGoogleDoc = baseURL + textFromGoogleDoc;
						else
							textFromGoogleDoc = baseURL + "/" + textFromGoogleDoc;					
					}
				}	
			}
			
			tempMethodDetailsFromFile.url = textFromGoogleDoc;
			temp.write("\t\t\t<td><a href=\""+tempMethodDetailsFromFile.url+"\" target=\"_blank\">"+tempMethodDetailsFromFile.url+"</a></td>\n");
			textFromGoogleDoc = null;
			
			//method details
			temp.write("\t\t\t<td><a href=\""+generatedHref+"\">"+methodDetails.className+"."+methodDetails.name+" (\" "+methodDetails.description+" \")</a></td>\n");
			
			//browser
			temp.write("\t\t\t<td>"+browserName+"</td>\n");
			
			
			//Expected
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,6);
			}
			
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";	
			temp.write("\t\t\t<td>"+textFromGoogleDoc+"</td>\n");tempMethodDetailsFromFile.expected = textFromGoogleDoc;textFromGoogleDoc = null;
			
			//Actual
			temp.write("\t\t\t<td></td>\n");
			
			//known
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,12);
			}
			
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";			
			temp.write("\t\t\t<td nowrap><a href=\"https://foxnetworksgroup.atlassian.net/browse/"+tempMethodDetailsFromFile.known+"\" target=\"_blank\">"+tempMethodDetailsFromFile.known+"</a></td>\n");
			tempMethodDetailsFromFile.known = textFromGoogleDoc; textFromGoogleDoc = null;
			
			//pri - priority
			if(tempMethodDetailsFromFile.testCaseNumber != null && tempMethodDetailsFromFile.testCaseNumber.trim().length() > 0)
			{	
				textFromGoogleDoc = readData(tempMethodDetailsFromFile.testCaseNumber,8);							
			}
			
			if( textFromGoogleDoc == null ) textFromGoogleDoc = "";				
			textFromGoogleDoc = textFromGoogleDoc.toUpperCase();
			
			if( textFromGoogleDoc != null && (textFromGoogleDoc.equals("P1") == false && textFromGoogleDoc.equals("P2") == false && textFromGoogleDoc.equals("P3") == false) ) 
				textFromGoogleDoc = "";

			temp.write("\t\t\t<td>"+textFromGoogleDoc+"</td>\n"); tempMethodDetailsFromFile.pri = textFromGoogleDoc;textFromGoogleDoc = null;
			
			temp.write("\t\t</tr>\n");
		}
		catch(Exception exp)
	    {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	    }		   
   }   
   public void closeReport()
   {
		try
		{
			//mainHTMLFile.write("</body>\n");
			//mainHTMLFile.write("</html>\n");
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}		   	   
   }
   public void printResult()
   {
	   String validBuildMessage = null;
	   boolean allAreNull = true;
	   
	   try
	   {
			for(int i=0;failedJobNames != null && i<failedJobNames.length;i++)
			{
				if(failedJobNames[i] != null ) 
				{
					allAreNull = false;
					break;
				}
			}
			
			if(allAreNull == true)
			{
				validBuildMessage = "Yes (No job failed)";
			}
			else
				validBuildMessage = "No (One of sub job failed. Results can not be accurate)";
			
			mainHTMLFile.write("\t\t<table cellspacing=0 cellpadding=0 class=\"param\" width=\"100%\">\n");
			mainHTMLFile.write("\t\t\t<tr>\n");
			headerDetails.finished_at = headerDetails.finished_at.replace("T"," ");
			mainHTMLFile.write("\t\t\t\t<th colspan=\"8\" style=\"color:black;\">Automated Script Run: "+scriptName+"&nbsp;<p>Build Number: &nbsp;&nbsp;<b>"+buildNumber+"</b>&nbsp;<p>Time and Date:&nbsp;"+headerDetails.finished_at+"<p><b>Valid Build ? : "+validBuildMessage+"</th>\n");
			validBuildMessage = null;
			
			mainHTMLFile.write("\t\t\t</tr>\n");
			mainHTMLFile.write("\t\t\t<tr>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\">Test</th>\n");
			mainHTMLFile.write("<th bgcolor=\"#6699FF\" style=\"color:white;\">Total Count<br/></th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\"># Passed<br/></th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\"># Skipped</th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\"># Failed</th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\">Total<br/>Time</th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\">Included Groups</th>\n");
			mainHTMLFile.write("\t\t\t\t<th bgcolor=\"#6699FF\" style=\"color:white;\">Excluded Groups</th>\n");
			mainHTMLFile.write("\t\t\t</tr>\n");
			mainHTMLFile.write("\t\t\t<tr>\n");
			mainHTMLFile.write("\t\t\t\t<td style=\"text-align:left;padding-right:2em;color:blue\">"+jobType+"</td>\n");
			mainHTMLFile.write("\t\t\t\t<td class=\"numi\"><font color=\"black\"><b>"+headerDetails.total+"</b></font></td>\n");
			mainHTMLFile.write("\t\t\t\t<td class=\"numi\"><font color==\"green\"><b>"+headerDetails.passed+"</b></font></td>\n");
			mainHTMLFile.write("\t\t\t\t<td class=\"numi\"><font color=\"gray\">"+headerDetails.skipped+"</font></td>\n");
			mainHTMLFile.write("\t\t\t\t<td class=\"numi_attn\"><b>"+headerDetails.failed+"</b></td>\n");
			mainHTMLFile.write("\t\t\t\t<td class=\"numi\" nowrap>"+headerDetails.getFormattedTime()+"</td>\n");
			
			String includedGroups = null;
			String excludedGroups = null;
			String smokeTestYorNColumnValue = "";
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next();
			
			while(rowIterator.hasNext())
			{
				row = rowIterator.next();
								
				Cell cell = null;
				Cell smokeYNCell = null;
				
				cell = row.getCell(12);
				smokeYNCell = row.getCell(7);
				
				if(cell == null ) continue;
				
				if(smokeYNCell == null ) smokeTestYorNColumnValue = "N";
				else
				{
					smokeTestYorNColumnValue = smokeYNCell.getStringCellValue();
					
					if(smokeTestYorNColumnValue == null )
						smokeTestYorNColumnValue = smokeYNCell.getRichStringCellValue().getString();
					
					if(smokeTestYorNColumnValue == null)
						smokeTestYorNColumnValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,7);
				}
				
				if(smokeTestYorNColumnValue != null )
					smokeTestYorNColumnValue = smokeTestYorNColumnValue.trim();
				
				String stringValue = null;
				stringValue = cell.getStringCellValue();
				
				if(stringValue == null )
					stringValue = cell.getRichStringCellValue().getString();
				
				if(stringValue == null)
					stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,12);
				
				if(stringValue != null )
				{
					stringValue = stringValue.trim();
					boolean groupNameFound = false;
					
					for(int i=0; i < headerDetails.includedGroupdName.size();i++)
					{
						//xx
						String compareValue = (String)headerDetails.includedGroupdName.get(i);
						if(compareValue != null && compareValue.equalsIgnoreCase(stringValue) == true && (jobType != null && jobType.equals("smoke") && smokeTestYorNColumnValue != null && smokeTestYorNColumnValue.equalsIgnoreCase("Y") == true) )
						{
							groupNameFound = true;
							break;
						}
						if(compareValue != null && compareValue.equalsIgnoreCase(stringValue) == true && (jobType != null && jobType.equals("regression") && (smokeTestYorNColumnValue == null && smokeTestYorNColumnValue.length() == 0 || smokeTestYorNColumnValue.equalsIgnoreCase("N") == true)) )
						{
							groupNameFound = true;
							break;
						}
						
					}
					if(groupNameFound == false && (jobType != null && jobType.equals("smoke") && smokeTestYorNColumnValue != null && smokeTestYorNColumnValue.equalsIgnoreCase("Y") == true) )
						headerDetails.addExcludedGroupName(stringValue);					
					
					if(groupNameFound == false && (jobType != null && jobType.equals("regression") && (smokeTestYorNColumnValue == null || smokeTestYorNColumnValue.length() == 0 || smokeTestYorNColumnValue.equalsIgnoreCase("N") == true))  )
						headerDetails.addExcludedGroupName(stringValue);					

				}
			}

			
			
			for(int i = 0;i<headerDetails.includedGroupdName.size();i++)
			{
				if(includedGroups == null)
					includedGroups = headerDetails.includedGroupdName.get(i);
				else
					includedGroups = includedGroups + ", " +headerDetails.includedGroupdName.get(i);
			}
			
			
			
			for(int i = 0;i<headerDetails.excludedGroupdName.size();i++)
			{
				if(excludedGroups == null)
					excludedGroups = headerDetails.excludedGroupdName.get(i);
				else
					excludedGroups = excludedGroups + ", " +headerDetails.excludedGroupdName.get(i);
			}
			mainHTMLFile.write("\t\t\t\t<td>"+includedGroups+" </td>\n");
			mainHTMLFile.write("\t\t\t\t<td>"+excludedGroups+"</td>\n");
			mainHTMLFile.write("\t\t\t</tr>\n");
			mainHTMLFile.write("\t\t</table>\n");
			mainHTMLFile.write("\t</center>\n");
			
			headerDetails = null;
	   }
		catch(Exception exp)
	    {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	    }		   
   }
   public void printHTMLHeader()
   {
	   try
	   {
			mainHTMLFile.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n");
			mainHTMLFile.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
			mainHTMLFile.write("\t<head>\n");
			mainHTMLFile.write("\t<title>TestNG:  Unit Test</title>\n");
			mainHTMLFile.write("\t<style type=\"text/css\">\n");
			mainHTMLFile.write("\t\t\ttable caption,table.info_table,table.param,table.passed,table.failed {margin-bottom:10px;border:1px solid #000099;border-collapse:collapse;empty-cells:show;}\n");
			mainHTMLFile.write("\t\t\ttable.info_table td,table.info_table th,table.param td,table.param th,table.passed td,table.passed th,table.failed td,table.failed th tr.info_table {\n");
			mainHTMLFile.write("\t\t\tborder:1px solid #000099;padding:.25em .5em .25em .5em;font-size:12px;\n");
			mainHTMLFile.write("\t\t\t}\n");
			mainHTMLFile.write("\t\t\ttable.param th {vertical-align:bottom}\n");
			mainHTMLFile.write("\t\t\ttd.numi,th.numi,td.numi_attn {\n");
			mainHTMLFile.write("\t\t\ttext-align:center;\n");
			mainHTMLFile.write("\t\t\t}\n");
			mainHTMLFile.write("\t\t\ttr.total td {font-weight:bold}\n");
			mainHTMLFile.write("\t\t\ttable caption {\n");
			mainHTMLFile.write("\t\t\ttext-align:center;font-weight:bold;\n");
			mainHTMLFile.write("\t\t\t}\n");
			mainHTMLFile.write("\t\t\ttable.passed tr.stripe td,table tr.passedodd td {background-color: #33FF33;}\n");
			mainHTMLFile.write("\t\t\ttable.passed td,table tr.passedeven td {background-color: #33FF33;}\n");
			mainHTMLFile.write("\t\t\ttable.passed tr.stripe td,table tr.skippedodd td {background-color: #cccccc;}\n");
			mainHTMLFile.write("\t\t\ttable.passed td,table tr.skippedodd td {background-color: #dddddd;}\n");
			mainHTMLFile.write("\t\t\ttable.failed tr.stripe td,table tr.failedodd td,table.param td.numi_attn {background-color: #FFFFFF;color:#FF0000;}\n");
			mainHTMLFile.write("\t\t\ttable.failed td,table tr.failedeven td,table.param tr.stripe td.numi_attn {background-color: #FFFFFF;color:#FF0000;}\n");
			mainHTMLFile.write("\t\t\ttr.stripe td,tr.stripe th {background-color: #E6EBF9;}\n");
			mainHTMLFile.write("\t\t\tp.totop {font-size:85%;text-align:center;border-bottom:2px black solid}\n");
			mainHTMLFile.write("\t\t\tdiv.shootout {padding:2em;border:3px #4854A8 solid}\n");
			mainHTMLFile.write("\t\t\t.failedodd a:link {color: #FF0000;}\n");
			mainHTMLFile.write("\t\t\t.failedeven a:link {color: #FF0000;font-weight:900;}\n");
			mainHTMLFile.write("\t\t</style>\n");
			mainHTMLFile.write("\t</head>\n");
			mainHTMLFile.write("\t<body><a id=\"summary\"></a>\n");
			mainHTMLFile.write("\t<center>\n");
	   }
		catch(Exception exp)
	    {
		   System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
	    }		   	   
   }
	public  String getMergedRowData(XSSFSheet sheet,Iterator<Row> rowIterator,int prowNum ,int pcolIndex)
	{
		while (rowIterator.hasNext()) 
		{
			Row row = rowIterator.next();

			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			outer:
			while (cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();

				//will iterate over the Merged cells
				for (int i = 0; i < sheet.getNumMergedRegions(); i++) 
				{
					CellRangeAddress region = sheet.getMergedRegion(i); //Region of merged cells

					int colIndex1 = region.getFirstColumn(); //number of columns merged
					int colIndex2 = region.getLastColumn(); //number of columns merged
					int rowNum1 = region.getFirstRow();      //number of rows merged
					int rowNum2 = region.getLastRow();      //number of rows merged
					//System.out.println(".................................................. 1. " +prowNum+" "+rowNum1+" "+rowNum2);
					if(prowNum <= rowNum2 && prowNum >= rowNum1 )
					{						
						//System.out.println(".................................................. 2. " +pcolIndex+" "+colIndex1+" "+colIndex2);
						if(pcolIndex <= colIndex2 && pcolIndex >= colIndex1 )
						{
							
							String strvalue = sheet.getRow(rowNum1).getCell(colIndex1).getStringCellValue();
							//System.out.println(".................................................. 3. " +strvalue);
							if(strvalue == null )
								strvalue = sheet.getRow(rowNum1).getCell(colIndex1).getRichStringCellValue().getString();
							//System.out.println(".................................................. 4. " +strvalue);
							if(strvalue == null )
								return "";
							return strvalue;
						}
					}
				}
				//the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
				if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
					continue;
				}
				String strvalue = cell.getStringCellValue();
				if(strvalue == null )
					strvalue = cell.getRichStringCellValue().getString();
				if(strvalue == null ) strvalue = "";
				return strvalue;
			}
		}
		return "";
	}
	public String readData(String rowID,int columnIndex )
	{		
		if(rowID == null ) return "";
		
		rowID = rowID.trim();
		
		if(rowID.length() == 0 ) return "";
		
		try
		{			
			//FileInputStream file = new FileInputStream(new File("C:\\Users\\MahendraK\\Downloads\\FSCOM Regression Suite.xlsx"));

			//Get the workbook instance for XLS file 
			//XSSFWorkbook workbook = new XSSFWorkbook (file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				
				//Get iterator to all cells of current row
				Iterator<Cell> cellIterator = row.cellIterator();
				
				Cell cell = null;
				cell = cellIterator.next();
				String stringValue = null;
				
				//first column value which is test case number like FA-TC-125
				stringValue = cell.getStringCellValue();
				if(stringValue == null )
					stringValue = cell.getRichStringCellValue().getString();
				
				//System.out.println("-------------------------------------------- 1 "+stringValue);
				
				if(stringValue != null)
				{
					stringValue = stringValue.trim();
					
					if(stringValue.equals(rowID) == true )
					{						
						Cell cell1 = row.getCell(columnIndex);
						//System.out.println("-------------------------------------------- 2 ");
						if(cell1 != null )
						{														
							
							stringValue = cell1.getStringCellValue();
							
							if(stringValue == null )
								stringValue = cell.getRichStringCellValue().getString();
							
							//System.out.println("-------------------------------------------- 3 "+stringValue);
							if(stringValue == null)
							{																
								
								stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell1.getRowIndex() ,columnIndex);
								//System.out.println("-------------------------------------------- 4 "+stringValue);
								if(stringValue == null)								
									return "";								
								return stringValue;
							}			
							else								
							{
								//System.out.println("-------------------------------------------- 5 "+stringValue);
								stringValue = stringValue.trim();
								return stringValue;
								
								////System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Debug 6.1 "+columnIndex);
								//stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
								//if(stringValue == null)
									//return "";
								//return stringValue;
							}
						
						}
						else
						{							
							stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
							
							//System.out.println("-------------------------------------------- 6 "+stringValue);
							if(stringValue == null)
								return "";
							return stringValue;
						}
						
					}
				}
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		//System.out.println("-------------------------------------------- 7");
		return null;
	}
   
   public static void main(String[] argv)
   {	   
		////System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",5));
		/*//System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",1));
		//System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",2));
		//System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",5));
		//System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",8));
		//System.out.println("---------------------------------------------"+new readDataFromExcelFile().readData("FA-TC-125",9));*/
		
		//String str = "/abc";
		//System.out.println("---------------------------------------------"+str.substring(1));
		
		new ReadXMLFile(argv).parseReport();
   }
   public final static Logger logger = Logger.getLogger(ReadXMLFile.class);
}

class HeaderDetails
{
	public int skipped = 0 ;
	public int failed = 0; 
	public int total = 0; 
	public int passed = 0;
	
	public String suitename="";
	public int duration_ms=0;
	int maxDuration = 0;
	int numberOfDuration = 0;
	public String started_at="";
	public String finished_at="";
	
	java.util.ArrayList<String> includedGroupdName = new java.util.ArrayList<String>();
	java.util.ArrayList<String> excludedGroupdName = new java.util.ArrayList<String>();
	
	public void addGroupName(String pGroupName)
	{
		boolean duplicate = false;
		
		if(pGroupName == null ) return ;
		
		pGroupName = pGroupName.trim();
		
		if(pGroupName.length() <= 0 ) return;
		
		for(int i = 0;i < includedGroupdName.size();i++)
		{
			if(pGroupName.equals((String)includedGroupdName.get(i) ) == true)
			{
				duplicate = true;
				break;
			}
		}
		
		if(duplicate == false)
			includedGroupdName.add(pGroupName);
	}
	
	public void addExcludedGroupName(String pGroupName)
	{
		boolean duplicate = false;
		
		if(pGroupName == null ) return ;
		
		pGroupName = pGroupName.trim();
		
		if(pGroupName.length() <= 0 ) return;
		
		for(int i = 0;i < excludedGroupdName.size();i++)
		{
			if(pGroupName.equals((String)excludedGroupdName.get(i) ) == true)
			{
				duplicate = true;
				break;
			}
		}
		
		if(duplicate == false)
			excludedGroupdName.add(pGroupName);
	}
	
	public void addSkipped(String pStrNumber)
	{
		try
		{
			int intValue = Integer.parseInt(pStrNumber);
			skipped = skipped + intValue;
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	public void addFailed(String pStrNumber)
	{
		try
		{
			int intValue = Integer.parseInt(pStrNumber);
			failed = failed + intValue;
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	public void addPassed(String pStrNumber)
	{
		try
		{
			int intValue = Integer.parseInt(pStrNumber);
			passed = passed + intValue;
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	public void addTotal(String pStrNumber)
	{
		try
		{
			int intValue = Integer.parseInt(pStrNumber);
			total = total + intValue;
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	public void addDuration(String pStrNumber)
	{
		try
		{
			int intValue = Integer.parseInt(pStrNumber);
			duration_ms = duration_ms + intValue;
			
			if( maxDuration < intValue )
				maxDuration = intValue;
			
			numberOfDuration++;
		}
		catch(Exception exp)
		{
			System.out.println("Exception Occured: "+exp.getMessage());exp.printStackTrace();
		}
	}
	
	public String getFormattedTime()
	{
		int avgDuration = (duration_ms/numberOfDuration);
		
		long hours = TimeUnit.MILLISECONDS.toHours(avgDuration);
        avgDuration -= TimeUnit.HOURS.toMillis(hours);
		
        long minutes = TimeUnit.MILLISECONDS.toMinutes(avgDuration);
        avgDuration -= TimeUnit.MINUTES.toMillis(minutes);
		
        long seconds = TimeUnit.MILLISECONDS.toSeconds(avgDuration);

        StringBuilder sb = new StringBuilder();
		
		sb.append("Avg: ");
        sb.append(hours);
        sb.append(" hr ");
        sb.append(minutes);
        sb.append(" min ");
        sb.append(seconds);
        sb.append(" sec");
		sb.append("</br>Max: ");
		
		hours = TimeUnit.MILLISECONDS.toHours(maxDuration);
        maxDuration -= TimeUnit.HOURS.toMillis(hours);
		
        minutes = TimeUnit.MILLISECONDS.toMinutes(maxDuration);
        maxDuration -= TimeUnit.MINUTES.toMillis(minutes);
		
        seconds = TimeUnit.MILLISECONDS.toSeconds(maxDuration);

		sb.append(hours);
        sb.append(" hr ");
        sb.append(minutes);
        sb.append(" min ");
        sb.append(seconds);
        sb.append(" sec");
		
        return(sb.toString());
	}
}

class MethodDetails
{
	String className = null;
	String status = "" ;
	String signature = "" ;
	String name= "";
	String duration_ms = "";
	String started_at = "";
	String description = "";
	String finished_at = "";
	
	java.util.ArrayList<String> parameters = new java.util.ArrayList<String>();
	
	String exceptionMessage = "";
	String exceptionStackTrace = "";	
}

class MethodDetailsFromFile
{
	String className = "";
	String methodName = "";
	String testCaseNumber = "";
	String userStory = "";
	String testCase = "";
	String layout = "";
	String url = "";
	String known = "";
	String pri = "";
	String description = "";
	String expected = "";
	String actual = "";
	boolean alreadyRead = false;
}

class readDataFromExcelFile
{
	public static String getMergedRowData(XSSFSheet sheet,Iterator<Row> rowIterator,int prowNum ,int pcolIndex)
	{
		while (rowIterator.hasNext()) 
		{
			Row row = rowIterator.next();

			//For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();

			outer:
			while (cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();

				//will iterate over the Merged cells
				for (int i = 0; i < sheet.getNumMergedRegions(); i++) 
				{
					CellRangeAddress region = sheet.getMergedRegion(i); //Region of merged cells

					int colIndex1 = region.getFirstColumn(); //number of columns merged
					int colIndex2 = region.getLastColumn(); //number of columns merged
					int rowNum1 = region.getFirstRow();      //number of rows merged
					int rowNum2 = region.getLastRow();      //number of rows merged
					//System.out.println(".................................................. 1. " +prowNum+" "+rowNum1+" "+rowNum2);
					if(prowNum <= rowNum2 && prowNum >= rowNum1 )
					{						
						//System.out.println(".................................................. 2. " +pcolIndex+" "+colIndex1+" "+colIndex2);
						if(pcolIndex <= colIndex2 && pcolIndex >= colIndex1 )
						{
							
							String strvalue = sheet.getRow(rowNum1).getCell(colIndex1).getStringCellValue();
							//System.out.println(".................................................. 3. " +strvalue);
							if(strvalue == null )
								strvalue = sheet.getRow(rowNum1).getCell(colIndex1).getRichStringCellValue().getString();
							//System.out.println(".................................................. 4. " +strvalue);
							if(strvalue == null )
								return "";
							return strvalue;
						}
					}
				}
				//the data in merge cells is always present on the first cell. All other cells(in merged region) are considered blank
				if (cell.getCellType() == Cell.CELL_TYPE_BLANK || cell == null) {
					continue;
				}
				String strvalue = cell.getStringCellValue();
				if(strvalue == null )
					strvalue = cell.getRichStringCellValue().getString();
				if(strvalue == null ) strvalue = "";
				return strvalue;
			}
		}
		return "";
	}
	public static String readData(String rowID,int columnIndex )
	{		
		if(rowID == null ) return "";
		
		rowID = rowID.trim();
		
		if(rowID.length() == 0 ) return "";
		
		try
		{			
			FileInputStream file = new FileInputStream(new File("C:\\Users\\khilesh.fegade\\Downloads\\FSCOM Regression Suite.xlsx"));

			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook (file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			
			while(rowIterator.hasNext())
			{
				Row row = rowIterator.next();
				
				//Get iterator to all cells of current row
				Iterator<Cell> cellIterator = row.cellIterator();
				
				Cell cell = null;
				cell = cellIterator.next();
				String stringValue = null;
				
				//first column value which is test case number like FA-TC-125
				stringValue = cell.getStringCellValue();
				if(stringValue == null )
					stringValue = cell.getRichStringCellValue().getString();
				
				//System.out.println("-------------------------------------------- 1 "+stringValue);
				
				if(stringValue != null)
				{
					stringValue = stringValue.trim();
					
					if(stringValue.equals(rowID) == true )
					{						
						Cell cell1 = row.getCell(columnIndex);
						//System.out.println("-------------------------------------------- 2 ");
						if(cell1 != null )
						{														
							
							stringValue = cell1.getStringCellValue();
							
							if(stringValue == null )
								stringValue = cell.getRichStringCellValue().getString();
							
							//System.out.println("-------------------------------------------- 3 "+stringValue);
							if(stringValue == null)
							{																
								
								stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell1.getRowIndex() ,columnIndex);
								//System.out.println("-------------------------------------------- 4 "+stringValue);
								if(stringValue == null)								
									return "";								
								return stringValue;
							}			
							else								
							{
								//System.out.println("-------------------------------------------- 5 "+stringValue);
								stringValue = stringValue.trim();
								return stringValue;
								
								////System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Debug 6.1 "+columnIndex);
								//stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
								//if(stringValue == null)
									//return "";
								//return stringValue;
							}
						
						}
						else
						{							
							stringValue = readDataFromExcelFile.getMergedRowData(sheet,rowIterator,cell.getRowIndex() ,columnIndex);
							
							//System.out.println("-------------------------------------------- 6 "+stringValue);
							if(stringValue == null)
								return "";
							return stringValue;
						}
						
					}
				}
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		//System.out.println("-------------------------------------------- 7");
		return null;
	}
}
