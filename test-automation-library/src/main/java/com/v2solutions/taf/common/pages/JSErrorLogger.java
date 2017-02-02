package com.v2solutions.taf.common.pages;

import java.util.List;

import org.apache.commons.logging.Log;

import com.v2solutions.taf.util.LogUtil;

public class JSErrorLogger {
	private static Log log = LogUtil.getLog(JSErrorLogger.class);
	private List<JSErrorItem> jsErrorList;
    private String pageURL;
    private String browserType;
    
	public List<JSErrorItem> getJsErrorList() {
		return jsErrorList;
	}

	public void setJsErrorList(List<JSErrorItem> jsErrorList) {
		this.jsErrorList = jsErrorList;
	}

	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	
	public void writeJsErrorLog() {
		
	    // write log in jserror.log file
		log.debug("------ START CONSOLE LOG -------");
		
		log.debug("PAGE URL = " + this.pageURL + " BROWSER TYPE = "+ this.browserType);		
		for (JSErrorItem JSErrorItem : jsErrorList) {
			log.debug(JSErrorItem.getLevel()+":"+JSErrorItem.getLineNumber()+":"+JSErrorItem.getSourceName()+":"+JSErrorItem.getErrorMessage());
		}
		log.debug("------ END CONSOLE LOG -------");
		
	}

}
