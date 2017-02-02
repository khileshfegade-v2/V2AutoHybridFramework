package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.util.LogUtil;

/**
 * Renders Video PageObject. . 
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class Video extends PageElement{

	static Log log = LogUtil.getLog(Video.class);
	
	public Video(WebElement webElement){
		super(webElement);
	}
	
	

}