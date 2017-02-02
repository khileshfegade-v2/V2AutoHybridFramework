package com.v2solutions.taf.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.v2solutions.taf.common.logs.ExcelCompatibleXMLFile;
import com.v2solutions.taf.common.logs.ITestResultLogger;
import com.v2solutions.taf.util.FileUtils;
import com.v2solutions.taf.util.LogUtil;

/**
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */

public class SuiteListener implements ISuiteListener {
	
	static Log log = LogUtil.getLog(SuiteListener.class);
	
	static public Properties pageURLs = null;
	
	public static ITestResultLogger excelXlsTestResultLogger;
	
	/**
	 * Added new constructor to read config/prperties file. Config/properties file name and location
	 * is mentioned on command line using -D option.
	 */
	public SuiteListener() 
	{		
		try 
		{
			
			if(pageURLs == null || pageURLs.size() <= 0 )
			{
				pageURLs = FileUtils.readPropertyFile(System.getProperty("pageurlsFileName"));
			}
		} 
		catch (FileNotFoundException e) 
		{
			log.error(e.toString());
		} 
		catch (IOException e) 
		{
			log.error(e.toString());
		}
		
    	
		if(excelXlsTestResultLogger == null) 
		{
			excelXlsTestResultLogger = new ITestResultLogger();
			excelXlsTestResultLogger.startLogging();
			log.info("Log error listener object started.");
		}    	
    }
	
    public void onStart(ISuite arg0) 
    {
    	log.info("Suite Name :"+ arg0.getName() + " - Start");
     	//Print HostName
    	log.info(arg0.getParameter("pageurlsFileName"));
		try 
		{
			if(pageURLs == null || pageURLs.size() <= 0 )
			{
				pageURLs = FileUtils.readPropertyFile(arg0.getParameter("pageurlsFileName"));
				log.info(pageURLs);
			}
		} 
		catch (FileNotFoundException e) 
		{
			log.error(e.toString());
		} 
		catch (IOException e) 
		{
			log.error(e.toString());
		}
		
    	log.info("Host Name:"+arg0.getHost());
    	
    	//	Returns null if it runs locally
		if(excelXlsTestResultLogger == null) 
		{
			excelXlsTestResultLogger = new ITestResultLogger();
			excelXlsTestResultLogger.startLogging();
			log.info("Log error listener object started.");
		}    	
    }
    
    
    public void onFinish(ISuite arg0) 
    {
     	log.info("Suite Name :"+ arg0.getName() + " - End");
    	log.info("********Results*******");
    	
		if(excelXlsTestResultLogger != null) 
		{

			excelXlsTestResultLogger.stopLogging();
		}
		log.info("Log error listener object stopped.");
		System.out.println("**********Started to complete xml**********");
    	ExcelCompatibleXMLFile.main(null);
		System.out.println("**********Stopped xml**********");
    	
    }
    
}
