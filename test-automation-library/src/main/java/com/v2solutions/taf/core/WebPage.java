package com.v2solutions.taf.core;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.v2solutions.taf.TestBedManager;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

/**
 * This class finds and renders page objects , drivers.
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class WebPage {

	private static Log log = LogUtil.getLog(WebPage.class);

	private static TestBedManager testBedManager = null;
	protected WebDriver driver = null;

	//value for the wait loop
	private int WAIT_TO_CHECK = 500;

	/**
	 * Initializes web driver and test bed manager.
	 * @param driver Web Driver
	 * @param manager Test Bed Manager
	 *
	 */
	public WebPage(WebDriver driver, TestBedManager manager) {
		if (this.driver == null) {
			this.driver = driver;
		}
		testBedManager = manager;
	}

	/**
	 * Returns test bed manager.
	 * @return instance of test bed manager
	 *
	 */
	public static TestBedManager getTestBedManager() {
		return testBedManager;
	}

	/**
	 *
	 * Sets test bed manager.
	 * @param testBedManager Test bed manager
	 *
	 */
	public static void setTestBedManager(TestBedManager testBedManager) {
		WebPage.testBedManager = testBedManager;
	}

	/**
	 *
	 * Returns driver.
	 * @return instance of web driver
	 *
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 *
	 * Sets web driver.
	 * @param driver web driver
	 *
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Loads web page URL.
	 * @param pageUrl web page URL
	 */
	public void loadPage(String pageUrl) {
		this.driver.get(pageUrl);
		this.driver.manage().deleteAllCookies();
	}



	/**
	 * find the specified element by id.
	 *
	 * @param id target element id
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 */
	public WebElement findPageObjectById(String id,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.id(id));
		}catch (Exception e){
			log.error("failed to find page object by id, message : " + e.toString());
        	throw new PageException("failed to find page object by id , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits for specified time to find the element by id.
	 *
	 * @param id target element id
	 * @param maxWaitSeconds maximum wait seconds
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 *
 	 */
	public WebElement findPageObjectById(String id,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.id(id), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits for specified time to find the element by id using the Expected condition.
	 * @param id target element id
	 * @param maxWaitSeconds maximum wait seconds
	 * @param objType target object type
	 * @param expectation based on the Expected Condition
	 * @return instance of page object based on object type
	 * @throws PageException throws this exception if page object not found
	 */

	public WebElement findPageObjectById(String id,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.id(id), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}

	/**
	 * Waits for specified time to find the element by id.
	 *
	 * @param id target element id
	 * @param maxWaitSeconds maximum wait seconds
	 * @param frame name of the frame
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 *
 	 */
	public WebElement findPageObjectById(String id,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.id(id), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 * Waits for specified time to find the element by id using the Expected condition.
	 * @param id target element id
	 * @param maxWaitSeconds maximum wait seconds
	 * @param frame name of the frame
	 * @param objType target object type
	 * @param expectation based on the Expected Condition
	 * @return instance of page object based on object type
	 * @throws PageException throws this exception if page object not found
	 */
	public WebElement findPageObjectById(String id,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.id(id), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}
	/**
	 * find the specified element by name.
	 *
	 * @param name target element name attribute value
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 */
	public WebElement findPageObjectByName(String name,String objType) throws PageException{
		WebElement webElement = null;
		try{
			driver.findElement(By.name(name));
		}catch (Exception e){
			log.error("failed to find page object by name, message : " + e.toString());
        	throw new PageException("failed to find page object by name , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits for specified time to find the element by name.
	 *
	 * @param name target element name attribute value
	 * @param maxWaitSeconds maximum wait seconds
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 *
 	 */
	public WebElement findPageObjectByName(String name,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.name(name), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 *  Waits for specified time to find the element by id using the Expected condition.
	 * @param name target element name attribute value
	 * @param maxWaitSeconds maximum wait seconds
	 * @param objType target object type
	 * @param expectation based on the Expected Condition
	 * @return instance of page object based on object type
	 * @throws PageException throws this exception if page object not found
	 */
	public WebElement findPageObjectByName(String name,int maxWaitSeconds, String objType,ExpectationTypes expectation ) throws PageException{
		WebElement webElement = waitOnElement(By.name(name), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}
	/**
	 * Waits for specified time to find the element by name.
	 *
	 * @param name target element name attribute value
	 * @param maxWaitSeconds maximum wait seconds
	 * @param frame name of the frame
	 * @param objType target object type
	 * @return instance of page object based on object type
	 * @exception PageException throws this exception if page object not found
	 *
 	 */
	public WebElement findPageObjectByName(String name,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.name(name), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 *  Waits for specified time to find the element by id using the Expected condition.
	 * @param name target element name attribute value
	 * @param maxWaitSeconds  maximum wait seconds
	 * @param frame name of the fram
	 * @param objType target object type
	 * @param expectation based on the Expected Condition
	 * @return instance of page object based on object type
	 * @throws PageException throws this exception if page object not found
	 */
	public WebElement findPageObjectByName(String name,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation ) throws PageException{
		WebElement webElement = waitOnElement(By.name(name), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}
	
	
	/**
	 * find the specified element by xpath.
	 *
	 * @param xpath
	 * @param objType
	 * @return page object if WebDriver is able to find an element with the given xpath
	 */
	

	public WebElement findPageObjectByXPath(String xpath,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.xpath(xpath));
		}catch (Exception e){
			log.error("failed to find page object by xpath, message : " + e.toString());
        	throw new PageException("failed to find page object by xpath , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits until webdriver can find the element specified by xpath.
	 *
	 * @param xpath
	 * @param maxWaitSeconds
	 * @return page object if WebDriver is able to find an element with the given xpath
	 */
	public WebElement findPageObjectByXPath(String xpath,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.xpath(xpath), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits until webdriver can find the element specified by xpath based on the expected condition
	 * @param xpath
	 * @param maxWaitSeconds
	 * @param objType
	 * @param expectation
	 * @return page object if WebDriver is able to find an element with the given xpath
	 * @throws PageException
	 */
	public WebElement findPageObjectByXPath(String xpath,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{

		if(maxWaitSeconds < 0 )
		{
			maxWaitSeconds = 20;
		}
		WebElement webElement = waitOnElement(By.xpath(xpath), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}
	/**
	 * Waits until webdriver can find the element specified by xpath.
	 *
	 * @param path
	 * @param maxWaitSeconds
	 * @param frame
	 * @return page object if WebDriver is able to find an element with the given xpath
	 */
	

	public WebElement findPageObjectByXPath(String xpath,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.xpath(xpath), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 *
	 * @param xpath
	 * @param maxWaitSeconds
	 * @param frame
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	public WebElement  findPageObjectByXPath(String xpath,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
//		maxWaitSeconds=20;
		if(maxWaitSeconds < 0 )
		{
			maxWaitSeconds = 20;
		}
		WebElement webElement = waitOnElement(By.xpath(xpath), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}


	/**
	 * find the specified element by css.
	 *
	 * @param css
	 * @param objType
	 * @return page object if WebDriver is able to find an element with the given css
	 */
	
	public WebElement findPageObjectByCSS(String css,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.cssSelector(css));
		}catch (Exception e){
			log.error("failed to find page object by css selector, message : " + e.toString());
        	throw new PageException("failed to find page object by css selector , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by css.
	 *
	 * @param css
	 * @param maxSeconds
	 * @return page object if WebDriver is able to find an element with the given css
	 */
	

	public WebElement findPageObjectByCSS(String css,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.cssSelector(css), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by css for the Expected Type.
	 * @param css
	 * @param maxWaitSeconds
	 * @param objType
	 * @return page object if WebDriver is able to find an element with the given css
	 * @throws PageException
	 */
	public WebElement findPageObjectByCSS(String css,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.cssSelector(css), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}
	/**
	 * Waits until web driver can find the element specified by css.
	 *
	 * @param css
	 * @param maxSeconds
	 * @param frame
	 * @return page object if WebDriver is able to find an element with the given css
	 */
	

	public WebElement findPageObjectByCSS(String css,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.cssSelector(css), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by css for the Expected Type.
	 * @param css
	 * @param maxWaitSeconds
	 * @param frame
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	

	public WebElement findPageObjectByCSS(String css,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
		if(maxWaitSeconds < 0 )
		{
			maxWaitSeconds = 20;
		}
		WebElement webElement = waitOnElement(By.cssSelector(css), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}

	/**
	 * find the specified element by link text.
	 *
	 * @param linkText
	 * @param objType
	 * @return page object if WebDriver is able to find an element with the given linkText
	 */


	public WebElement findPageObjectByLinkText(String linkText,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.linkText(linkText));
		}catch (Exception e){
			log.error("failed to find page object by link text, message : " + e.toString());
        	throw new PageException("failed to find page object by link text , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by linkText.
	 *
	 * @param linkText
	 * @param maxWaitSeconds
	 * @return page object if WebDriver is able to find an element with the given linkText
	 */
	

	public WebElement findPageObjectByLinkText(String linkText,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.linkText(linkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by linkText on the Expected Types.
	 *
	 * @param linkText
	 * @param maxWaitSeconds
	 * @param objType
	 * @param expectation
	 * @return page object if WebDriver is able to find an element with the given linkText
	 * @throws PageException
	 */

	

	public WebElement findPageObjectByLinkText(String linkText,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.linkText(linkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by linkText.
	 *
	 * @param linkText
	 * @param maxWaitSeconds
	 * @param frame
	 * @return page object if WebDriver is able to find an element with the given partialLinkText
	 */
	

	public WebElement findPageObjectByLinkText(String linkText,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.linkText(linkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by linkText on the Expected Types.
	 * @param linkText
	 * @param maxWaitSeconds
	 * @param frame
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	

	public WebElement findPageObjectByLinkText(String linkText,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.linkText(linkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}

	/**
	 * find the specified element by partial link text.
	 *
	 * @param partialLinkText
	 * @param objType
	 * @return page object if WebDriver is able to find an element with the given partialLinkText
	 */
	
	public WebElement findPageObjectByPartialLinkText(String partialLinkText,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.partialLinkText(partialLinkText));
		}catch (Exception e){
			log.error("failed to find page object by partial link text, message : " + e.toString());
        	throw new PageException("failed to find page object by partial link text , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by partial link text.
	 *
	 * @param partialLinkText
	 * @param maxWaitSeconds
	 * @return page object if WebDriver is able to find an element with the given partialLinkText
	 */
	public WebElement findPageObjectByPartialLinkText(String partialLinkText,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.partialLinkText(partialLinkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits until web driver can find the element specified by partial link text for a specified Expected Type.
	 * @param partialLinkText
	 * @param maxWaitSeconds
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	

	public WebElement findPageObjectByPartialLinkText(String partialLinkText,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.partialLinkText(partialLinkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}

	

	public WebElement findPageObjectByPartialLinkText(String partialLinkText,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.partialLinkText(partialLinkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	
	public WebElement findPageObjectByPartialLinkText(String partialLinkText,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.partialLinkText(partialLinkText), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}


	public WebElement findPageObjectByClassName(String className,String objType) throws PageException{
		WebElement webElement = null;
		try{
			webElement = driver.findElement(By.className(className));
		}catch (Exception e){
			log.error("failed to find page object by class name, message : " + e.toString());
        	throw new PageException("failed to find page object by class name , message : " + e.toString());
		}

		return webElement;
	}

	/**
	 * Waits until webdriver can find the element specified by className.
	 *
	 * @param className
	 * @param maxWaitSeconds
	 * @return page object if WebDriver is able to find an element with the given className
	 */
	public WebElement findPageObjectByClassName(String className,int maxWaitSeconds, String objType) throws PageException{
		WebElement webElement = waitOnElement(By.className(className), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null);
		return webElement;
	}

	/**
	 * Waits until webdriver can find the element specified by className.
	 *
	 * @param className
	 * @param maxWaitSeconds
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	public WebElement findPageObjectByClassName(String className,int maxWaitSeconds, String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.className(className), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, null,expectation);
		return webElement;
	}
	/**
	 * Waits until webdriver can find the element specified by className.
	 *
	 * @param className
	 * @param maxWaitSeconds
	 * @param frame
	 * @return page object if WebDriver is able to find an element with the given className
	 */
	public WebElement findPageObjectByClassName(String className,int maxWaitSeconds, String frame,String objType) throws PageException{
		WebElement webElement = waitOnElement(By.className(className), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame);
		return webElement;
	}

	/**
	 *
	 * @param className
	 * @param maxWaitSeconds
	 * @param frame
	 * @param objType
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	public WebElement findPageObjectByClassName(String className,int maxWaitSeconds, String frame,String objType,ExpectationTypes expectation) throws PageException{
		WebElement webElement = waitOnElement(By.className(className), (maxWaitSeconds==-1)?TestBed.MaxWaitSeconds:maxWaitSeconds, (frame==null||"".equals(frame))?null:frame,expectation);
		return webElement;
	}

	/**
	 * find the elements by tagName.
	 *
	 * @param tagName
	 * @param objType
	 * @return list of page object if WebDriver is able to find elements with the given tagName
	 */
	public List<WebElement> findPageObjectsByTagName(String tagName,String objType) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = driver.findElements(By.tagName(tagName));
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	/**
	 * find the elements by tagName.
	 *
	 * @param tagName
	 * @param objType
	 * @return list of page object if WebDriver is able to find elements with the given tagName
	 */
	public List<WebElement> findPageObjectsByTagName(String tagName,String objType,int maxWaitSeconds, ExpectationTypes expectation) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst =  waitOnElements(By.tagName(tagName),maxWaitSeconds,expectation);
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	

	public List<WebElement> findPageObjectsByXPath(String xpath,String objType) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = driver.findElements(By.xpath(xpath));
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}



	public List<WebElement> findPageObjectsByXPath(String xpath,String objType, int maxWaitSeconds, ExpectationTypes expectation) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = waitOnElements(By.xpath(xpath),maxWaitSeconds,expectation);

		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}
	/**
	 * find the elements by linktext.
	 *
	 * @param linktext
	 * @param objType
	 * @return list of page object if WebDriver is able to find elements with the given linktext
	 */
	public List<WebElement> findPageObjectsByLinktext(String linktext,String objType) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = driver.findElements(By.linkText(linktext));
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	/**
	 *
	 * @param linktext
	 * @param objType
	 * @param maxWaitSeconds
	 * @param expectation
	 * @return
	 * @throws PageException
	 */
	public List<WebElement> findPageObjectsByLinktext(String linktext,String objType,int maxWaitSeconds, ExpectationTypes expectation) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = waitOnElements(By.linkText(linktext),maxWaitSeconds,expectation);
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}


	/**
	 * find the elements by partial link text.
	 *
	 * @param partialLinkText
	 * @param objType
	 * @return list of page object if WebDriver is able to find elements with the given partialLinkText
	 */
	public List<WebElement> findPageObjectsByPartialLinktext(String partialLinkText,String objType) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = driver.findElements(By.partialLinkText(partialLinkText));
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	

	public List<WebElement> findPageObjectsByPartialLinktext(String partialLinkText,String objType,int maxWaitSeconds, ExpectationTypes expectation) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = waitOnElements(By.partialLinkText(partialLinkText),maxWaitSeconds,expectation);
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	/**
	 * find the elements by className.
	 *
	 * @param className
	 * @param objType
	 * @return list of page object if WebDriver is able to find elements with the given className
	 */
	public List<WebElement> findPageObjectsByClassName(String className,String objType) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = driver.findElements(By.className(className));
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}
	public List<WebElement> findPageObjectsByClassName(String className,String objType,int maxWaitSeconds, ExpectationTypes expectation) throws PageException{
		List<WebElement> l = new ArrayList<WebElement>();
		List<WebElement> lst = waitOnElements(By.className(className),maxWaitSeconds,expectation);
		for(WebElement we : lst){
			l.add(we);
		}
		return l;
	}

	/**
	 * Wait on element.
	 * @param element
	 * @param maxSeconds
	 * @param frame
	 * @return
	 */
	private WebElement waitOnElement(By element, int maxSeconds, String frame) throws PageException{
		WebElement identifier = null;
		for (int i = 0; i < maxSeconds; i++) {
			try {
				if (frame == null)
					identifier = driver.findElement(element);
				else {
					driver.switchTo().defaultContent();
					identifier = driver.switchTo().frame(frame).findElement(element);
				}

				//if element is found return
				if (identifier != null)
					break;

				Thread.sleep(WAIT_TO_CHECK);
			} catch (Exception e) {
				identifier = null;
				log.error("failed to find element, message : " + e.toString());
	        	throw new PageException("failed to find element, message : " + e.toString());
			}
		}
		return identifier;
	}

	/**
	 * Waits on web element.
	 * @param element
	 * @param maxSeconds
	 * @return
	 */
	public WebElement waitOnElement(By element, int maxSeconds) throws PageException{
		return waitOnElement(element, maxSeconds, null);
	}


	/**
	 * Wait on element to get loaded based on the Expected Condition.
	 * @param element
	 * @param maxSeconds
	 * @param frame
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private WebElement waitOnElement(By element, int maxSeconds, String frame,ExpectationTypes expectedCondition) throws PageException{
		WebElement identifier = null;
		try{
			Method method = ExpectedConditions.class.getMethod(expectedCondition.getExpectation(), By.class);
			WebDriverWait wait = new WebDriverWait(driver, maxSeconds);

			if(frame != null)
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));

			identifier = wait.until((ExpectedCondition<WebElement>)method.invoke(ExpectedConditions.class,  element)) ;

		} catch (Exception e) {
			identifier = null;
			log.error("failed to find element, message : " + e.toString());
			throw new PageException("failed to find element, message : " + e.toString());
		}
		return identifier;
	}
	/**
	 * Wait on element.
	 * @param element
	 * @param maxSeconds
	 * @param frame
	 * @return
	 */
	@SuppressWarnings("unchecked")
    private List<WebElement> waitOnElements(By element, int maxSeconds,ExpectationTypes expectedCondition) throws PageException{
		List<WebElement> identifiers = null;
		try{

			Method method = ExpectedConditions.class.getMethod(expectedCondition.getExpectation(), By.class);

			WebDriverWait wait = new WebDriverWait(driver, maxSeconds);
			identifiers = wait.until( (ExpectedCondition<List<WebElement>>)method.invoke(ExpectedConditions.class,  element));

		} catch (Exception e) {
			identifiers = null;
			log.error("failed to find element, message : " + e.toString());
			throw new PageException("failed to find element, message : " + e.toString());
		}
		return identifiers;
	}
	/**
	 * Click on "Continue to this Website(not recommended)" link on Certificate Error Page for IE browser
	 */
	public void  certificateErrorClick() {
		if(BrowserInfoUtil.INSTANCE.isIE()){
			driver.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
	}

	/**
	 * sleep time
	 * @param timeout
	 */
	public void sleep(long timeout){
		try {
//Limiting wait period to 85 Secs as browser stack times out for 90 Secs idle time.
			if(timeout > 20000){
				timeout = 20000;
			}
			log.info("waiting for ::"+timeout/1000+" seconds");
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Resize window to given width and height.
	 * @param width
	 * @param height
	 */
	public void resize(int width,int height){
		driver.manage().window().setSize(new org.openqa.selenium.Dimension(width,height));
		return;
	}

	/**
	 * Resize window to given height width and sleep time.
	 * @param width
	 * @param height
	 * @param sleep
	 */
	public void resize(int width,int height,int sleep){
		driver.manage().window().setSize(new org.openqa.selenium.Dimension(width,height));
		sleep(10000);
		return;
	}


	public void scrollWindow(int xscroll, int yscroll) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("window.scrollBy(" + xscroll + "," + yscroll + ")", "");
	}


	public Point getScrollPosition() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		Long ycoordinate = (Long) js.executeScript("return window.pageYOffset", "");
		Long xcoordinate = (Long) js.executeScript("return window.pageXOffset", "");
		return new Point(xcoordinate.intValue(), ycoordinate.intValue());
	}

	/**
	 * Returns current URL.
	 * @return
	 */
	public String getCurrentUrl(){
		return driver.getCurrentUrl();
	}

	public String getTransitionUrl(final String previousUrl, int driverWait,int implicitWait){
		WebDriverWait wait = new WebDriverWait(driver, driverWait);
	    driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
	    ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
	    	public Boolean apply(WebDriver d) {
	    		return (d.getCurrentUrl() != previousUrl);
	    	}
	    };
	    wait.until(e);
		return driver.getCurrentUrl();
	}

	/**
	 * Returns true if current URL has parameters appended.
	 * @return true if current URL has parameters appended.
	 */
	public boolean StringParameterAppend(){
		String url=driver.getCurrentUrl();
		boolean flag = url.indexOf("?")!= -1;
		if(flag)
			return false;
		else
			return true;
	}

	/**
	 * Navigates to the given URL.
	 * @param url URL to navigate.
	 */
	public void navigateToUrl(String url){
		driver.navigate().to(url);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log.error("Exception in thread sleep, message, " + e.toString());
		}
	}

	/**
	 * Switch to new tab.
	 */
	public void openNewTab(){
		Set<String> windows = driver.getWindowHandles();
		log.info("After click no. of windows:"+windows.size());
		log.info("Before Switch "+driver.getWindowHandle());
		for(String window: windows){
			driver.switchTo().window(window);
			log.info("After Switch "+driver.getWindowHandle());
			log.info("Page URL:"+driver.getCurrentUrl());
			log.info("Switched Page title is:"+driver.getTitle());
		}
	}

	/**
	 * Navigates back.
	 */
	public void getBackToUrl(){
		driver.navigate().back();
		return;
	}

	/**
    *
    */
    public void toggleWindow() {
           String currentHandle = driver.getWindowHandle();
           log.info("currentHandle:: " + currentHandle);
           Set<String> windows = driver.getWindowHandles();
           log.info("no. of windows::" + windows.size());
           windows.remove(currentHandle);
           log.info("no. of windows after remove current handle ::" + windows.size());
           if (windows.size() == 1) {
                  log.info("switching to window ::" + (String) ((windows.toArray())[0]));
                  driver.switchTo().window((String) ((windows.toArray())[0]));
           }
    }

    /**
    *
    */
    public void close_toggleWindow() {
           String currentHandle = driver.getWindowHandle();
           log.info("currentHandle:: " + currentHandle);
           Set<String> windows = driver.getWindowHandles();
           log.info("no. of windows::" + windows.size());
           if(windows.size()>1){
           driver.switchTo().window(currentHandle).close();
           windows.remove(currentHandle);
           log.info("no. of windows after remove current handle ::" + windows.size());
           if (windows.size() == 1) {
                  log.info("switching to window ::" + (String) ((windows.toArray())[0]));
                  driver.switchTo().window((String) ((windows.toArray())[0]));
           }
           }
    }



    /**
     * Utility method to switch the frame
     * @param nameOrId specify name or id
     * */
    public void switchFrame(String nameOrId, int timeOutInSeconds)
    {

    	log.info("waiting for frame: "+nameOrId);
    	WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
    	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));

    	log.info("Focus switched to frame :"+nameOrId);
    }


    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
                
            WebDriverWait wait = new WebDriverWait(driver, 90);
            wait.until(pageLoadCondition);
        }
    
 
    public void createScreenShotAt(String filePath,String caseName){
    	log.info("Creating Screeen Shot At:"+filePath);
    	try{
    		if(filePath != null && caseName != null){
    			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        		Date d = new Date();
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'('HH_mm_ss')'");
        		String destFileName = sdf.format(d)+"_"+caseName+".jpg";
        		log.info("Screen Shot Name:"+destFileName);
        		org.apache.commons.io.FileUtils.copyFile(scrFile, new File(filePath+destFileName));
    		}
    	}catch(IOException ioe){
    		log.info("Got IO Exception: while copying page:"+ioe.getMessage());
    	}catch(Exception e){
    		log.info("Got Exception in WebPage method createScreenShotAt():"+e.getMessage());
    	}
    }

}
