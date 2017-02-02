package com.v2solutions.pages;

import java.util.List;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.v2solutions.taf.common.pages.CommonPage;
import com.v2solutions.taf.core.IPageObjectType;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.JSUtil;
import com.v2solutions.taf.util.LogUtil;

public class V2SolutionContactUsPage extends CommonPage{
	
	private static Log log = LogUtil.getLog(V2SolutionContactUsPage.class);

	public V2SolutionContactUsPage(String sbPageUrl, WebPage webPage) 
	{
		super(sbPageUrl, webPage);
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		captureJSErrors();
	}
	
	/**
	 *  Method for resizing the browser according to given width and height 
	 * @param width
	 * @param height
	 */
	public void columnLayout(int width, int height) {
		
		log.info("Resizing the browser to (width*height)"+"("+width+"*"+height+")");
		webPage.resize(width, height);
		webPage.sleep(3000);
	}
	
	/**
	 *  Method will return true if exp and actual size of browser is match
	 * @param expWidth
	 * @param expHeight
	 * @return Number of column in opened browser
	 */
	public boolean getColumnCountOfColLayout(int expWidth,int expHeight) {
		int actualHeight;
		int actualWidth;
		
		//Get size of opened browser window 
		Dimension dimension = webPage.getDriver().manage().window().getSize();
		actualHeight = dimension.getHeight();
		actualWidth = dimension.getWidth();
		log.info("Get the browsers dimension in (width*height)"+"("+actualWidth+"*"+actualHeight+")");
		//Compare actual width and height with expected width and height of the browser 
		if(actualHeight==expHeight && actualWidth == expWidth)
			return true;
		else 
			return false;
	}
	
	public String getElementTextValue(String elementXpath) throws PageException {
		String actualTextValue = null;
		WebElement element = webPage.findPageObjectByXPath(elementXpath, IPageObjectType.Text);
		actualTextValue = element.getText();
		
		return actualTextValue;
	}
	
	/**
	 * Method to check whether content is present in component section
	 * @param contenentSectionXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifyContentDisplayedOnPage(String contenentSectionXpath) throws PageException {
		boolean value = false;
		webPage.sleep(1000);
		StringBuilder textValue = new StringBuilder();
		List<WebElement> component = webPage.findPageObjectsByXPath(contenentSectionXpath, IPageObjectType.ComponentSection);
		for (WebElement componentSection : component) {
		    textValue.append(componentSection.getText());
		}
		if(!textValue.toString().isEmpty()){
			value = true;
		}
		return value;
		
	}
	
	/**
	 * Method will return true value if footer section present
	 * @param componentXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifySectionPresentOnPage(String componentXpath) throws PageException {
		boolean value = false;
		webPage.sleep(1000);
		WebElement component = webPage.findPageObjectByXPath(componentXpath, 10, IPageObjectType.ComponentSection);
		if(component.isDisplayed()){
			value = true;
		}
		return value;
		
	}
	
	
	/**
	 * Method will return true value if footer section present
	 * @param componentXpath
	 * @return
	 * @throws PageException
	 */
	public String getTextBoxDefaultValue(String textboxXpath) throws PageException {
		String actualdefaultTextBoxValue = null;
		WebElement textbox = webPage.findPageObjectByXPath(textboxXpath, 10, IPageObjectType.TextBox);
		actualdefaultTextBoxValue = textbox.getAttribute("placeholder");
		return actualdefaultTextBoxValue;
		
	}
	
	
	/**
	 * Method will return true value if footer section present
	 * @param componentXpath
	 * @return
	 * @throws PageException
	 */
	public String getErrorMessageFromTextBox(String textboxXpath, String errorMsgXpath, String testData) throws PageException {
		String actualErrorMsg = null;
		WebElement textbox = webPage.findPageObjectByXPath(textboxXpath, 10, IPageObjectType.TextBox);
		textbox.sendKeys(testData);
		webPage.sleep(2000);
		WebElement errorMsg = webPage.findPageObjectByXPath(errorMsgXpath, IPageObjectType.ComponentSection);
		actualErrorMsg = errorMsg.getText();
		
		return actualErrorMsg;
		
	}
	
	
	/**
	 * Method to verify component rendered as per expected position for Desktop view
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifyComponentPositionInDesktop(String firstComponentSectionXpath, String secondComponentSectionXpath) throws PageException {
		boolean value = false;
		WebElement firstComponent = webPage.findPageObjectByXPath(firstComponentSectionXpath, IPageObjectType.ComponentSection);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", firstComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer fistComponentYLocation = firstComponent.getLocation().getY();
		
		WebElement secondComponent = webPage.findPageObjectByXPath(secondComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer secondComponentYLocation = secondComponent.getLocation().getY();
		
		if(fistComponentYLocation.equals(secondComponentYLocation)){
			value = true;
		}
		
		return value;
	}
	
	
	/**
	 * Method to verify component rendered as per expected position for Tablet view
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifyComponentPositionInTablet(String firstComponentSectionXpath, String secondComponentSectionXpath) throws PageException {
		boolean value = false;
		WebElement firstComponent = webPage.findPageObjectByXPath(firstComponentSectionXpath, IPageObjectType.ComponentSection);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", firstComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer fistComponentXLocation = firstComponent.getLocation().getX();
		
		WebElement secondComponent = webPage.findPageObjectByXPath(secondComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer secondComponentXLocation = secondComponent.getLocation().getX();
		
		if(fistComponentXLocation.equals(secondComponentXLocation)){
			value = true;
		}
		
		return value;
	}
	
	
	/**
	 * Method to verify component rendered as per expected position for Mobile view
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifyComponentPositionInMobile(String firstComponentSectionXpath, String secondComponentSectionXpath) throws PageException {
		boolean value = false;
		WebElement firstComponent = webPage.findPageObjectByXPath(firstComponentSectionXpath, IPageObjectType.ComponentSection);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", firstComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer fistComponentXLocation = firstComponent.getLocation().getX();
		
		WebElement secondComponent = webPage.findPageObjectByXPath(secondComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer secondComponentXLocation = secondComponent.getLocation().getX();
		
		if(fistComponentXLocation.equals(secondComponentXLocation)){
			value = true;
		}
		
		return value;
	}
	/**
	 * Method return true value if web element is present on web page 
	 * @param elementXpath
	 * @return
	 * @throws PageException
	 */
	public boolean verifyElementPresent(String elementXpath) throws PageException {
		webPage.sleep(1000);
		WebElement element = webPage.findPageObjectByXPath(elementXpath, IPageObjectType.Link);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		if(element.isDisplayed())
			return true;
		return false;
	}
}
