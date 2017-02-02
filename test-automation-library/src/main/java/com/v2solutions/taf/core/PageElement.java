package com.v2solutions.taf.core;

import java.util.List;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;


/**
 * PageElement that all PageObjectType should extend. This class wraps around
 * the WebElement
 *
 * @author V2Solutions
 * @version 1.0
 * 
 */
public abstract class PageElement {
	private static Log	 log	       = LogUtil.getLog(PageElement.class);
	protected WebElement	webElement	= null;
	WebPage webPage;

	public WebElement getWebElement() {
		return webElement;
	}

	public PageElement(WebElement webElement) {
		this.webElement = webElement;
	}

	/**
	 * Double click on web element
	 *
	 * @param driver
	 *            selenium web driver instance.
	 */
	public void doubleClick(WebDriver driver) {
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(webElement).click().doubleClick(webElement).build();
		mouseOverHome.perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			log.error("Exception in thread sleep, message, " + e.toString());
		}
		return;
	}

	/**
	 * Returns true if element is visible.
	 *
	 * @return true if elements is visible.
	 */
	public boolean isElementVisible() {
		try {
			if (webElement == null)
				return false;
			if (!webElement.isEnabled())
				return false;
			webElement.sendKeys(" ");
		} catch (org.openqa.selenium.ElementNotVisibleException ex) {
			log.error("Element No Visible - Error is " + ex.getMessage());
			return false;
		} catch (org.openqa.selenium.NoSuchElementException ex) {
			log.error("No Such Element - Error is " + ex.getMessage());
			return false;
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			log.error("Stale Element Reference for element - Error is " + ex.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Click on web element
	 */
	public void click() {
		webElement.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			log.error("Exception in thread sleep, message, " + e.toString());
		}
	}

	/**
	 * Click on web element
	 *
	 * @param browserInfoUtil
	 *            carries browser information.
	 */
	public void click(BrowserInfoUtil browserInfoUtil) {
		if (browserInfoUtil.isIE10() || browserInfoUtil.isIE9() || browserInfoUtil.isIE11() || browserInfoUtil.isIE() ) {
			click();
		} else {
			webElement.click();
		}
	}

	/**
	 * Return coordinates for given web element.
	 *
	 * @return coordinates
	 */
	public Point getCoordinates() {
		return webElement.getLocation();
	}

	/**
	 * Returns size of the element.
	 *
	 * @return size of web element.
	 */
	public Dimension getSize() {
		return webElement.getSize();
	}

	/**
	 *
	 * Returns element count.
	 *
	 * @param Page
	 *            elements list.
	 * @return count page elements.
	 */
	public int getElementCount(List<PageElement> pageElements) {
		int count = 0;
		count = pageElements.size();
		return count;
	}

	/**
	 * Returns element text
	 *
	 * @return element text
	 */
	public String getText() {
		return webElement.getText();
	}

	/**
	 * Return attribute value.
	 *
	 * @param name
	 *            name of the attribute
	 * @return attribute value
	 */
	public String getAttribute(String attrName) {
		return webElement.getAttribute(attrName);
	}

	/**
	 * Returns true if element is displayed.
	 *
	 * @return true if element displayed
	 */
	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	/**
	 * Returns the css property of a text
	 *
	 * @param name
	 *            name of the property
	 * @return property value
	 */
	public String getCssValue(String property_name) {
		return webElement.getCssValue(property_name);
	}

	
	
	
	
	/**
	 * This method waits of 20 seconds for the webElement to be visible. If webElement is not visible
	 * this method throws ElementNotVisible  Exception.
	 * 
	 * Note: This method is suppose to call after scrolling the page to make webElement visible.
	 * 
	 * @param driver
	 * @param xOffset This parameter represent x coordinate (where mouse is suppose to hover) with respect to the webElement(means inside webElement) and NOT with respect to 
	 * window, rendered page, tab which contains the webElement.
	 * @param yOffset This parameter represent y coordinate (where mouse is suppose to hover) with respect to the webElement(means inside webElement) and NOT with respect to 
	 * window, rendered page, tab which contains the webElement.
	 * 
	 */	
public void hover(WebDriver driver , int xOffset , int yOffset) {
	if(BrowserInfoUtil.INSTANCE.isCloudSafari()) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		+ "arguments[0].dispatchEvent(evObj);";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript, webElement);
		try {
		Thread.sleep(10000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	  else {
		//Make sure element is visible
		WebElement myDynamicElement = (new WebDriverWait(driver, 20))
				  .until(ExpectedConditions.visibilityOf(webElement));
		Actions builder = new Actions(driver);
		builder.moveToElement(myDynamicElement,xOffset ,yOffset).build().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}	
	
/**
 * hovers on the element.
 *
 * @param driver selenium web driver instance
 * @throws InterruptedException 
 */
	public void hover(WebDriver driver) {
		  if (BrowserInfoUtil.INSTANCE.isCloudSafari()) {
		   String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		           + "arguments[0].dispatchEvent(evObj);";
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript(javaScript, webElement);
		   try {
		    Thread.sleep(10000);
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		   }
		   return;
		  }
		  
		  else {
			   Actions builder = new Actions(driver);
		       Action mouseOver = null;
		      
		       mouseOver = builder.moveToElement(webElement).build();
		       mouseOver.perform();
		   try {
		    Thread.sleep(10000);
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		   }
		   return;
		  }
		 }
	
	public void clicknhold(WebDriver driver)
	{
		  Actions builder = new Actions(driver);
	       Action mouseOver = null;
	       mouseOver = builder.moveToElement(webElement).clickAndHold(webElement).build();
	       mouseOver.perform();
	   
	       try {
	    	     Thread.sleep(10000);
	           } catch (InterruptedException e) {
	        	 e.printStackTrace();
	           }

	}
	
	
	
	public void clickEvent(WebDriver driver) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');" + "evObj.initMouseEvent(\"click\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		        + "arguments[0].dispatchEvent(evObj);";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript, webElement);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void performClick(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).click().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * This method is to enter text in the body.
	 * @param text text to be entered.
	 */
	public void enterText(String text){
		webElement.sendKeys(text);
		return;
	}
	
	public void performBuild(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).build().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}
	
	
	public void performClickBuild(WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(webElement).click().build().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return;
	}

}
