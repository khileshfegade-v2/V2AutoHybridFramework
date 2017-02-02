package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.util.LogUtil;

/**
 * Renders RadioButton PageObject. 
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class RadioButton extends PageElement{

	static Log log = LogUtil.getLog(SelectBox.class);
	
	/**
	 * Initialize radio button page object.
	 */
	public RadioButton(WebElement webElement) {
		super(webElement);
	}
	
	/**
	 * Select radio button page object.
	 */
	public void selectRadioButton(){
		if(radioButton_isSelected()==false)
		webElement.click();
		   
		
	}


	public boolean radioButton_isSelected() {
		// TODO Auto-generated method stub
		return	webElement.isSelected();
	
	}

}
