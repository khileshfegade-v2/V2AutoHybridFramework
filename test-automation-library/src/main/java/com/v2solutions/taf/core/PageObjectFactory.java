package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.CommonUtil;
import com.v2solutions.taf.util.LogUtil;

/**
 * Create page object from the page object factory. 
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class PageObjectFactory {
	
	static Log log = LogUtil.getLog(PageObjectFactory.class);
	
	/**
	 * Returns page object based on page object type.
	 * @param webElement initializes page object.
	 * @param type page object type.
	 * @return page element instance based on page object type.
	 * @throws PageException on error in creating page object.
	 */
	public static PageElement createPageObject (WebElement webElement,String type) throws PageException{
		
		if(CommonUtil.isNull(webElement) || CommonUtil.isNull(type)){
			log.error("Falied to create object, target element or page object type is missing (" + webElement + "," + type +")");			
			throw new PageException("Falied to create object, target element or page object type is missing");
		}		

		if (IPageObjectType.Text.equalsIgnoreCase(type)){
			return new Text(webElement);
		}else if (IPageObjectType.TextBox.equalsIgnoreCase(type)){
				return new TextBox(webElement);
		}else if (IPageObjectType.SelectBox.equalsIgnoreCase(type)){
			return new SelectBox(webElement);
		}else if (IPageObjectType.Link.equalsIgnoreCase(type)){
			return new Link(webElement);
		}else if (IPageObjectType.Button.equalsIgnoreCase(type)){
			return new Button(webElement);	
		}else if (IPageObjectType.Video.equalsIgnoreCase(type)){
                return new Video(webElement);
		}else if (IPageObjectType.Image.equalsIgnoreCase(type)){
            return new Image(webElement);
		}else if (IPageObjectType.RadioButton.equalsIgnoreCase(type)){
            return new RadioButton(webElement);
		}else if (IPageObjectType.CheckBox.equalsIgnoreCase(type)){
            return new CheckBox(webElement);
		}else{
			throw new PageException("No such object exist");
		}
	}
}	
