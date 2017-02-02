package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.util.LogUtil;

/**
 * Renders TextBox PageObject. . 
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class TextBox extends PageElement{
	
	static Log log = LogUtil.getLog(TextBox.class);
	
	public TextBox(WebElement webElement){
		super(webElement);
	}
	
	/**
	 * Enters text in the textbox.
	 * @param text text to be entered.
	 */
	public void enterText(String text){
		webElement.clear();
		webElement.sendKeys(text);    
		return;
	}
	
}
