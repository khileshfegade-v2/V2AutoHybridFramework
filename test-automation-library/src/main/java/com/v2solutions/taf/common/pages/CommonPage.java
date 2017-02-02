package com.v2solutions.taf.common.pages;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.server.rest.ResultType;
import org.testng.Assert;

import com.v2solutions.taf.common.logs.AnalizerException;
import com.v2solutions.taf.core.IPageObjectType;
import com.v2solutions.taf.core.JavaScriptError;
import com.v2solutions.taf.core.ProfileConfiguration;
import com.v2solutions.taf.core.TestBed;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.exception.DateException;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.CommonUtil;
import com.v2solutions.taf.util.DateUtil;
import com.v2solutions.taf.util.LogUtil;
import com.v2solutions.taf.util.ScreenShotUtil;

/**
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public abstract class CommonPage {

    protected String pageUrl;
    protected WebPage webPage;
    protected String errMessage;
    JSErrorLogger jsErrorLogger = new JSErrorLogger();
    private static Log log = LogUtil.getLog(CommonPage.class);
    protected String HREF= "href";
    protected String ANCHOR= "a";
    
    /**
     * @param sbPageUrl
     * @param webPage
     */
    public CommonPage(String sbPageUrl, WebPage webPage){
	this.pageUrl=sbPageUrl;
	this.webPage=webPage;
    }

    /**
     * @return
     */
    public String getErrMessage() {
	return errMessage;
    }

    /**
     * @param errMessage
     */
    public void setErrMessage(String errMessage) {
	this.errMessage = errMessage;
    }

    public String getScreenShotPath(String errorName) { 
	String path = null;
	boolean error = false;
	try {
	    DateUtil dateUtil = new DateUtil();
	    errorName = errorName+"-"+dateUtil.getDateToString(new java.util.Date());
	    path = System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+errorName+".jpg";

	    ScreenShotUtil ssu = new ScreenShotUtil();

	    ssu.takeScreenShot(webPage.getDriver(), path);
	}
	catch(Throwable e) {
	    e.printStackTrace();
	    error = true;
	    log.error("Error while taking screenshot of "+errorName);
	}
	if(error)
	    return null;
	else
	    return path;
    }

    /**
     * @param errorType
     * @param pageElement
     * @param pageUrl
     * @param expectedResult
     * @param actualResult
     * @param messageStr
     */
    public void setErrMessage(ResultType errorType,
	    String pageElement,
	    String pageUrl,
	    String expectedResult,
	    String actualResult,
	    String messageStr)
    {

	log.info("\n\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\nPage Exception in ????????????????????????? ::" + Thread.currentThread().getStackTrace()[2].getMethodName()
		+ "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n");
	String message = "";
	try
	{
	    if(errorType != null)
	    {
		message += "\n # Error Type: " + errorType;
	    }
	    if(pageElement != null)
	    {
		message += "\n # Page Element: " + pageElement;
	    }
	    if(pageUrl != null)
	    {
		message += "\n # Page URL: " + pageUrl;
	    }
	    if(expectedResult != null)
	    {
		message += "\n # Expected Result: " + expectedResult;
	    }
	    if(actualResult != null)
	    {
		message += "\n # Actual Result: " + actualResult;
	    }
	    if(messageStr != null)
	    {
		message += "\n # Message: " + messageStr;
	    }
	    message += "";
	}
	catch (Exception ex)
	{
	    message = "An error occured while setting Error Message: " + ex.toString();
	}
	finally
	{
	    this.errMessage = message;
	}
	log.info("Got Page Exception:-" + errMessage);

    }
    

    public void captureJSErrors()
    {

	ProfileConfiguration pc = TestBed.INSTANCE.getProfile();
	System.out.println("<<--------------- started executing automation scripts --------------->>");
	if (pc != null)
	{
	    System.out.println("Start capturing JavaScript on Web Page");
	    String strJsError = pc.getJSErrorLogging();
	    if (strJsError != null)
	    {
		if ( strJsError.equalsIgnoreCase("TRUE") || strJsError.equalsIgnoreCase("YES") )
		{
		    if (BrowserInfoUtil.INSTANCE.isFF()) {
			fetchConsoleLogLocal(pc.getBrowser());
		    }

		    if (BrowserInfoUtil.INSTANCE.isCloudFF()) {
			fetchConsoleLog(pc.getBrowser());
		    }

		    if (BrowserInfoUtil.INSTANCE.isChrome()) {
			fetchConsoleLog(pc.getBrowser());
		    }

		    if (BrowserInfoUtil.INSTANCE.isCloudChrome()) {
			fetchConsoleLog(pc.getBrowser());
		    }
		}
	    }
	}

    }

    public void fetchConsoleLogLocal(String strBrowser) {
	// Check javascript errors 
	List<JavaScriptError> jsErrors = JavaScriptError
		.readErrors(webPage.getDriver());
	List<JSErrorItem> jsErrorList = new ArrayList<JSErrorItem>();
	jsErrorLogger.setPageURL(this.pageUrl);
	jsErrorLogger.setBrowserType(strBrowser);

	for (int i = 0; i < jsErrors.size(); i++) {

	    JSErrorItem jsErrorItem = new JSErrorItem();

	    jsErrorItem.setErrorMessage(jsErrors.get(i).getErrorMessage());
	    jsErrorItem.setSourceName(jsErrors.get(i).getSourceName());
	    jsErrorItem.setLineNumber(jsErrors.get(i).getLineNumber());

	    jsErrorList.add(jsErrorItem);
	    System.out.println("error line->"+jsErrorItem.getLineNumber() + "error source->"+jsErrorItem.getSourceName() + " error message->"+jsErrorItem.getErrorMessage());
	}
	jsErrorLogger.setJsErrorList(jsErrorList);
	jsErrorLogger.writeJsErrorLog();

    }

    public void fetchConsoleLog(String strBrowser) {
	LogEntries logEntries = webPage.getDriver().manage().logs()
		.get(LogType.BROWSER);
	List<JSErrorItem> jsErrorList = new ArrayList<JSErrorItem>();
	jsErrorLogger.setPageURL(this.pageUrl);
	jsErrorLogger.setBrowserType(strBrowser);

	for (LogEntry entry : logEntries) {
	    // put only severe errors in the log file
	    if (entry.getLevel().toString().equalsIgnoreCase("SEVERE"))
	    {
		JSErrorItem jsErrorItem = new JSErrorItem();
		jsErrorItem.setErrorMessage(entry.getMessage());
		jsErrorItem.setLevel(entry.getLevel().toString());
		jsErrorList.add(jsErrorItem);
	    }
	}
	jsErrorLogger.setJsErrorList(jsErrorList);
	jsErrorLogger.writeJsErrorLog();
    }

    protected void loadPage() {
	webPage.loadPage(pageUrl);
    }


    public List<String> getBrokenLinksFromPage() throws PageException{
	List<String> lst = new ArrayList<String>();
	List<WebElement> lstLnk = webPage.findPageObjectsByTagName(ANCHOR, IPageObjectType.Link);
	for(WebElement lnk : lstLnk){
	    String linkUrl = lnk.getAttribute(HREF);
	    try {

		if((linkUrl.contains("http://") || linkUrl.contains("https://")) && !linkUrl.contains("mailto")){
		    if(CommonUtil.isLinkBroken(linkUrl)){
			lst.add(linkUrl);
		    }
		}
	    } catch (PageException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return lst;
    }

    
    public List<String> listBrokenLink= new ArrayList<String>();
    public Map<String,String> mapHrefBrokenLink = new LinkedHashMap<String, String>();
    /**
     * Method to validate whether Page contains BBroken links or not 
     * @return False if Page doesn't contain any Broken Links
     * @throws PageException
     */
    public  boolean isPageContainsBrokenLinks() throws PageException{
	log.info("Start - isPageContainsBrokenLinks");
	boolean result = false;
	List<Boolean> listResult = new ArrayList<Boolean>();

	List<WebElement> allAnchorLinks = webPage.findPageObjectsByTagName(ANCHOR, IPageObjectType.Link);
	int  i = 0;
	System.out.println("Size of ALL Anchor  Links---->"+allAnchorLinks.size());
	for(WebElement testLink : allAnchorLinks){
	    
	    String href = testLink.getAttribute(HREF);
	    i++;
	    //Skip the current loop if Href doesn't contain http .For eg. 'mailto',javascript void etc.
	    if( href==null || ! href.contains("http"))
	    {
		//		System.out.println(i+" Skipped as HREF contains NULL or not contains HTTP ... CONTINUE---->"+href);
		continue;
	    }
	    try {
		URL url = new URL(href);
		HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
		httpURLConnect.setConnectTimeout(6000);
		httpURLConnect.connect();
		if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND){
		    result = true;
		    listResult.add(result);
		    listBrokenLink.add(href);
		}
		else {
		    result = false;
		    listResult.add(result)  ;
		}
	    } catch (MalformedURLException e) {
		log.error("Malformed URL - "+ href + " Message - "+e.toString());	
		e.printStackTrace();
		//			throw new PageException("Failed to check broken link - "+ href + " Message - "+e.toString());
	    } catch (IOException e) {
		log.error("IO Exception - URL - "+href);	
		//		System.out.println("IO Exception - URL - "+href);
		e.printStackTrace();
		//			throw new PageException("Failed to check broken link - "+ href + " Message - "+e.toString());
	    }finally{
		log.info("End - isPageContainsBrokenLinks");
	    }
	}	
	//Returns false,If list doesn't contains any broken links
	result = listResult.contains(true);
	System.out.println("Is Page have any Broken Links-->-->"+result);
	return result;
    }


    
    public String getDateString(String sTxt){
	String mon = null;
	String day = null;
	String hour = null;
	String minute = null;

	try {
	    StringTokenizer stok = new StringTokenizer(sTxt," ,:");
	    DateUtil dateUtil = new DateUtil();

	    mon = dateUtil.getMonthInDigit(stok.nextToken());
	    day = stok.nextToken();
	    day = (day.length()==1)?"0"+day:day;
	    hour = stok.nextToken();
	    hour = (hour.length()==1)?"0"+hour:hour;
	    minute = stok.nextToken();
	    String ampm = ""+minute.charAt(minute.length()-1);
	    minute = minute.substring(0,minute.length()-1);
	    minute = (minute.length()==1)?"0"+minute:minute;
	    if("p".equals(ampm)){
		hour = dateUtil.get24HourFormat(hour);
	    }
	} catch (DateException e) {
	    setErrMessage(e.getMessage());
	    return null;
	}
	return (mon+day+hour+minute);
    }

    /**
     * Log error and create/add a defect
     */
    public void logAndCreateADefect(Throwable e, String pageElementLabel, 
	    String pageElementLocator, String pageElementAttributeName, String actualPageElementAttributeValue,
	    String expectedPageElementAttributeValue, String defectName, String screenShotName) {
	try {
	    StackTraceElement[]  ele = Thread.currentThread().getStackTrace();
	    String javaFileName = ele[2].getFileName().replaceAll(".java", "").trim();
	    screenShotName = javaFileName+"-"+ele[2].getMethodName();
	    String screenShotPath = getScreenShotPath(screenShotName);
	    AnalizerException analizerException = new AnalizerException(e, webPage.getCurrentUrl(), pageElementLabel, 
		    pageElementLocator, pageElementAttributeName, actualPageElementAttributeValue, 
		    expectedPageElementAttributeValue, defectName, screenShotPath);
	    throw analizerException;
	}
	catch(Throwable e1) {
	    Assert.fail(e1.getMessage(), e1);
	}
    }


    /**
     * Log error and create/add a defect
     */
    public void logAndCreateADefect(Throwable e, String pageElementLabel, 
	    String pageElementLocator, String pageElementAttributeName, String actualPageElementAttributeValue,
	    String expectedPageElementAttributeValue, String defectName, String screenShotName, LinkedHashMap helpingAttributes) {
	try {
	    String screenShotPath = getScreenShotPath(screenShotName);
	    AnalizerException analizerException = new AnalizerException(e, webPage.getCurrentUrl(), pageElementLabel, 
		    pageElementLocator, pageElementAttributeName, actualPageElementAttributeValue, 
		    expectedPageElementAttributeValue, defectName, screenShotPath, helpingAttributes);
	    throw analizerException;
	}
	catch(Throwable e1) {
	    e1.printStackTrace();
	    Assert.fail(e1.getMessage(), e1);
	}
    }


    /**
     * Log error and create/add a defect
     */
    public void logAndCreateADefect(String fileName, String testcaseId, String projId, String storyId, String defectName, String errorMsg) {

	// log the error
	setErrMessage("GOT PAGE EXCEPTION :- " + errorMsg);
	log.error(getErrMessage());
	// re-throw the Assertion error
	Assert.fail(errorMsg);
    }

}
