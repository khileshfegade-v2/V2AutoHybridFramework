package com.v2solutions.taf.core;


import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.CommonUtil;
import com.v2solutions.taf.util.LogUtil;

/**
 * 
 * Renders Link Page Object
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public class Link extends PageElement{
	
	static Log log = LogUtil.getLog(Link.class);
	
	/**
	 * Initialize link page object.
	 */
	public Link(WebElement webElement){
		super(webElement);
	}
	
	/**
	 * check link is valid or not.
	 * @param linkUrl link URL
	 * @return true for valid link
	 */
	public boolean isLink(String linkUrl) throws PageException{
		if(CommonUtil.isNull(linkUrl)){
			log.error("Link URL is missing - "+ linkUrl);			
			throw new PageException("Link URL is missing");
		}
		return ((linkUrl.contains("http://") || linkUrl.contains("https://")) && !linkUrl.contains("mailto"));
	}			
	
	/**
	 * checks broken link is valid or not.
	 * @param linkUrl link URL
	 * @return true for broken link found.
	 */
	public boolean isLinkBroken(String linkUrl)  throws PageException{
		boolean result = false; 
		result = CommonUtil.isLinkBroken(linkUrl);
		return result;
	}

}
