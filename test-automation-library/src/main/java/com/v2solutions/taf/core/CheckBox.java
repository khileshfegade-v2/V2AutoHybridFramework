package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.util.LogUtil;

/**
 * Renders CheckBox PageObject. . 
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class CheckBox extends PageElement{
	
	static Log log = LogUtil.getLog(TextBox.class);
	
	/**
	 * Initialize check box page object.
	 */
	public CheckBox(WebElement webElement){
		super(webElement);
	}
	
	/**
	 * Select check box page object.
	 */
	public void checkBox_click(){
	if(checkBox_isSelected()==false)
		webElement.click();    
		
	}
	
	/**
	 * Returns true if check box selected.
	 * @return true if check box selected.
	 */
	public boolean checkBox_isSelected() {
		return	webElement.isSelected();
	}
	
}
