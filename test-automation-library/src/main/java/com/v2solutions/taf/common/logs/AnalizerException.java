package com.v2solutions.taf.common.logs;

import java.util.LinkedHashMap;

/**
 * This is used to get meaningful exception when @Test method fails.
 **/
public class AnalizerException extends Throwable {
	
	private static final int ANALYSIS_FUNCTIONAL = 1;
	private static final int ANALYSIS_AUTOMATION = 2;
	private static final int ANALYSIS_ENVIORNMENT = 3;
	
	private String pageURL;
	
	private String pageElementLabel;
	private String pageElementLocator;
	private String pageElementAttributeName;
	private String actualPageElementAttributeValue;
	private String expectedPageElementAttributeValue;
	
	/**Key=Label of Page Element  Value=HashMap 
	 * 											Key=LOCATOR  		  	Value=Locator
	 *                                          Key=Name Of Attribute 	Value=Value Of Attribute
	 *                                          
	 * Key=Menu        			  Value=HashMap 
	 * 								 			Key=LOCATOR  			Value=Locator
	 * 								 			Key=Hovered  			Value=True                                         
	 */
	
	private String meaningfulMessage;
	
	private int analizedType;
	
	private String defectName;
	
	private String screenShotPath;
	
	/**Key=AttributeName  Value=Value  
	 */
	private LinkedHashMap relatedAttributes;
	
	
	
	public AnalizerException(Throwable throwable, String meaningFromMethod) {
		super(throwable);
		analizedType(throwable);
	}
	
	public AnalizerException(Throwable throwable, String pageUrl, String pageElementLabel, 
			String pageElementLocator, String pageElementAttributeName, String actualPageElementAttributeValue,
			String expectedPageElementAttributeValue, String defectName, String screenShotPath, LinkedHashMap relatedAttributes) {
		super(throwable);
		this.pageURL = pageUrl;
		if(this.pageURL == null) {
			this.pageURL = "Not Found";
		}
		this.pageElementLabel = pageElementLabel;
		this.pageElementLocator = pageElementLocator;
		this.pageElementAttributeName = pageElementAttributeName;
		this.actualPageElementAttributeValue = actualPageElementAttributeValue;
		this.expectedPageElementAttributeValue = expectedPageElementAttributeValue;
		this.defectName = defectName;
		this.screenShotPath = screenShotPath;
		this.relatedAttributes = relatedAttributes;
		analizedType(throwable);
	}
	
	public String getMessage() {
		return this.meaningfulMessage;
	}
	
	
	public AnalizerException(Throwable throwable, String pageUrl, String pageElementLabel, 
			String pageElementLocator, String pageElementAttributeName, String actualPageElementAttributeValue,
			String expectedPageElementAttributeValue, String defectName, String screenShotPath) {
		super(throwable);
		this.pageURL = pageUrl;
		if(this.pageURL == null) {
			this.pageURL = "Not Found";
		}
		this.pageElementLabel = pageElementLabel;
		this.pageElementLocator = pageElementLocator;
		this.pageElementAttributeName = pageElementAttributeName;
		this.actualPageElementAttributeValue = actualPageElementAttributeValue;
		this.expectedPageElementAttributeValue = expectedPageElementAttributeValue;
		this.defectName = defectName;
		this.screenShotPath = screenShotPath;
		analizedType(throwable);
	}
	
	public String getDefectName() {
		return this.defectName;
	}
	
	public String getScreenShotPath() {
		return screenShotPath;
	}
	
	
	private void analizedType(Throwable throwable) {
		if(throwable instanceof java.lang.AssertionError) {
			analizedType = ANALYSIS_FUNCTIONAL;
			meaningfulMessage = " Locator of '"+this.pageElementLabel+"' is '"+this.pageElementLocator+"' Error-"+
			throwable.getMessage();
			appendRelatedAttributesToMeaningfulMessage();
			return;
		}
		
		
		if(throwable instanceof java.lang.NullPointerException) { 
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "This error is caused due to use of null value. Please refer line number in java file mentioned into this row.";
		}
		else if(throwable instanceof org.openqa.selenium.NoSuchElementException) {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = getParsedNoSuchElementException(throwable)+" Reasons-Wrong Locator. Or " +
					"element may not yet be on the screen at the time of the find operation, due to slow internet.";
		}
		else if(throwable instanceof org.openqa.selenium.ElementNotVisibleException) {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "Element with "+pageElementLocator+" not visible. Reasons-Although an" +
					" element is present, it is not visible, and so is not able to" +
					" be interacted with.";
		}
		else if(throwable instanceof org.openqa.selenium.StaleElementReferenceException) {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "'"+pageElementLocator+"'"+" is staler. Reasons-"+
			       "You are no longer on the same page, or the page may have refreshed since " +
			       "the element was located. The element may have been removed and re-added to" +
			       " the screen, since it was located. Such as an element being relocated. " +
			       "This can happen typically with a javascript framework when values are " +
			       "updated and the node is rebuilt. Element may have been inside an iframe or " +
			       "another context which was refreshed.";
		}
		else if(throwable instanceof org.openqa.selenium.TimeoutException) {
			analizedType = ANALYSIS_ENVIORNMENT;

			meaningfulMessage = getParsedTimeoutException(throwable)+" Reasons-Wrong Locator. Or " +
					"element may not yet be on the screen at the time of the find operation, due to slow internet.";
		}
		else if(throwable instanceof org.openqa.selenium.NoSuchWindowException) {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "The window target "+pageElementLocator+" to be switched doesn�t exist.";
		}
		else if(throwable instanceof org.openqa.selenium.NoSuchFrameException) {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "The window frame "+pageElementLocator+" to be switched doesn�t exist.";
		}
		else if(throwable instanceof org.openqa.selenium.remote.UnreachableBrowserException) {
			analizedType = ANALYSIS_ENVIORNMENT;
			meaningfulMessage = "The provided server address to RemoteWebDriver is invalid, so the connection could not be established. "+
    		"The browser has died mid-test.";
		}

		else if(throwable instanceof org.openqa.selenium.NoAlertPresentException) {
			analizedType = ANALYSIS_FUNCTIONAL;
			meaningfulMessage = "Indicates that a user has tried to access an alert "+pageElementLocator+" when one is not present.";
		}
		else if(throwable instanceof com.v2solutions.taf.exception.PageException) {
			analizedType = ANALYSIS_AUTOMATION;

			meaningfulMessage = getParsedPageException(throwable)+" Reasons-Wrong Locator. Or " +
					"element may not yet be on the screen at the time of the find operation, due to slow internet.";
		}
		else {
			analizedType = ANALYSIS_AUTOMATION;
			meaningfulMessage = "Exception not checked by AnalizerException.";
		}
		
		appendRelatedAttributesToMeaningfulMessage();
		
	}
	
	private void appendRelatedAttributesToMeaningfulMessage() {
		if(this.relatedAttributes != null) {
			if(!this.relatedAttributes.isEmpty()) {
				this.meaningfulMessage = this.meaningfulMessage+" Other attributes used into this tests are as - " +
						this.relatedAttributes.toString();
			}
		}
		
	}
	
	
	public String getMeaningfulMessage() {
		return meaningfulMessage;
	}
	
	public String getPageURL() {
		return pageURL;
	}
	
	public String getPageElementLabel() {
		return pageElementLabel;
	}
	
	public String getPageElementLocator() {
		return pageElementLocator;
	}
	
	public String getPageElementAttributeName() {
		return pageElementAttributeName;
	}
	
	public String getActualPageElementAttributeValue() {
		return actualPageElementAttributeValue;
	}
	
	public String getExpectedPageElementAttributeValue() {
		return expectedPageElementAttributeValue;
	}

	public String getAnalizedType() {
		String retVal = null;
		if(analizedType == ANALYSIS_FUNCTIONAL) {
			retVal = "Functional";
		}
		if(analizedType == ANALYSIS_AUTOMATION) {
			retVal = "Automation";
		}
		if(analizedType == this.ANALYSIS_ENVIORNMENT) {
			retVal = "Enviornment";
		}
		return retVal;
	}
	
	private String getParsedNoSuchElementException(Throwable throwable) {
		String message = throwable.getMessage();
		int indexOfCurlyLeftBracket = message.indexOf('}');
		String parsedMessage = message.substring(0, indexOfCurlyLeftBracket+1);
		return parsedMessage;
	}

	private String getParsedTimeoutException(Throwable throwable) {
		String message = throwable.getMessage();
		int indexOfBuildInfo = message.indexOf("Build info:");
		String parsedMessage = message.substring(0, indexOfBuildInfo+1);
		return parsedMessage;
	}
	
	private String getParsedPageException(Throwable throwable) {
		String message = throwable.getMessage();
		int indexOfBuildInfo = message.indexOf("Build info:");
		String parsedMessage = message.substring(0, indexOfBuildInfo+1);
		return parsedMessage;
	}
	
}
