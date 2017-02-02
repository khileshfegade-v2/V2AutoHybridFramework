package com.v2solutions.taf.core;

import org.openqa.selenium.WebElement;

/**
 * 
 * Renders Button Page Object
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public class Button extends PageElement{

	/**
	 * Initialize button page object.
	 */
	public Button(WebElement webElement){
		super(webElement);
	}
	
	/**
	 * Click button page object.
	 */
	public void clickButton(){
	  	webElement.click();    
	  	return;
	} 
	
}