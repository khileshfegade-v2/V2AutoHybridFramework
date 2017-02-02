package com.v2solutions.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;

import com.v2solutions.taf.common.pages.CommonPage;
import com.v2solutions.taf.core.ExpectationTypes;
import com.v2solutions.taf.core.IPageObjectType;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.JSUtil;
import com.v2solutions.taf.util.LogUtil;

public class V2SolutiosPageComponents extends CommonPage {
	
	private static Log log = LogUtil.getLog(V2SolutiosPageComponents.class);

	public V2SolutiosPageComponents(String sbPageUrl, WebPage webPage) 
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
	
	
	
	public boolean checkComponentIsRendered(String componentXpath) throws PageException {
		boolean value = false;
		webPage.sleep(1000);
		
		WebElement component = webPage.findPageObjectByXPath(componentXpath, 10, IPageObjectType.ComponentSection);
		if(component.isDisplayed()){
			value = true;
		}
		return value;
		
	}

	public String getElementTextValue(String elementXpath) throws PageException {
		webPage.sleep(1000);
		WebElement element = webPage.findPageObjectByXPath(elementXpath, IPageObjectType.Text);
		String actualValue = element.getText().trim();
		return actualValue;
		
	}
	
	public boolean verifyElementPresent(String elementXpath) throws PageException {
		webPage.sleep(1000);
		WebElement element = webPage.findPageObjectByXPath(elementXpath, IPageObjectType.Link);
		if(element.isDisplayed())
			return true;
		return false;
		
	}
	
	
	public boolean validateClickFunctionalityOfLinkIcon(String linkIconXpath, String urlContainsTextValue) throws PageException {
		boolean value = false;
		String urlAfterClicked = null;
		String urlBeforeClicked = webPage.getCurrentUrl();
		WebElement linkIcon = webPage.findPageObjectByXPath(linkIconXpath, IPageObjectType.Link);
		linkIcon.click();
		
		webPage.sleep(5000);
		Set<String> windows = webPage.getDriver().getWindowHandles();
		if (windows.size() == 1) {
		    urlAfterClicked = webPage.getCurrentUrl();
		} else {
		    webPage.toggleWindow();
		    urlAfterClicked = webPage.getCurrentUrl();
		    webPage.close_toggleWindow();
		    webPage.sleep(5000);
		}
		if(!urlBeforeClicked.equalsIgnoreCase(urlAfterClicked) && urlAfterClicked.contains(urlContainsTextValue))
		    value = true;
		
		return value;
	}
	
	
	public String validateALTTagValue(String elementXpath) throws PageException {
		WebElement linkIcon = webPage.findPageObjectByXPath(elementXpath, 20, IPageObjectType.Link, ExpectationTypes.ElementToBeClickable);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) linkIcon).getCoordinates());
		linkIcon = webPage.findPageObjectByXPath(elementXpath, 20, IPageObjectType.Link, ExpectationTypes.ElementToBeClickable);
		String actualALTTagValue = linkIcon.getAttribute("data-original-title");
		return actualALTTagValue;
		
	}
	
	
	public boolean validateMenuLinkExpanded(String menuLinksXpath, String expandedSectionXpath) throws PageException {
		List<WebElement> allMenuLinks = webPage.getDriver().findElements(By.xpath(menuLinksXpath));
		ArrayList<String> al = new ArrayList<String>();
		boolean value = false;
		for(int i=1;i<allMenuLinks.size();i++){
			String temp = menuLinksXpath.concat("["+i+"]");
			WebElement link = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			
			Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
			mouse.mouseMove(((Locatable) link).getCoordinates());
			webPage.sleep(5000);
			WebElement expandedSection = webPage.findPageObjectByXPath(temp.concat(expandedSectionXpath), IPageObjectType.ComponentSection);
			mouse.mouseMove(((Locatable) expandedSection).getCoordinates());
			webPage.sleep(5000);
			if(expandedSection.isDisplayed()){
				al.add(link.getText());
			}
		}
		if(al.size()>1){
			return value = true;
			
		}
		return value;
		
	}
	
	
	public boolean validateMenuLinksClickable(String menuLinksXpath, String expandedSectionXpath) throws PageException {
		List<WebElement> allMenuLinks = webPage.getDriver().findElements(By.xpath(menuLinksXpath));
		boolean value = false;
		String urlBeforeClicked = webPage.getCurrentUrl();
		ArrayList<Boolean> al = new ArrayList<Boolean>();
		for(int i=1;i<allMenuLinks.size();i++){
			String temp = menuLinksXpath.concat("["+i+"]");
			WebElement link = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			link.click();
			webPage.waitForLoad(webPage.getDriver());
			String urlAfterClicked = webPage.getCurrentUrl();
			
			if(!urlBeforeClicked.equalsIgnoreCase(urlAfterClicked)){
				al.add(true);
			}
			if(al.contains(true)){
				 value = true;
			}
			webPage.getBackToUrl();
			
			webPage.waitForLoad(webPage.getDriver());
			webPage.sleep(2000);
		}
		return value;
		
	}
	
	
	public boolean validateSubMenuLinksHighlightedOnMouseHover(String menuLinksXpath, String subMenuLinkXpath, String expHighlightedColor) throws PageException {
		List<WebElement> allMenuLinks = webPage.getDriver().findElements(By.xpath(menuLinksXpath));
		ArrayList<String> al = new ArrayList<String>();
		boolean value = false;
		for(int i=1;i<allMenuLinks.size();i++){
			String temp = menuLinksXpath.concat("["+i+"]");
			WebElement link = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
			mouse.mouseMove(((Locatable) link).getCoordinates());
			webPage.sleep(2000);
			List<WebElement> subMenuLink = webPage.getDriver().findElements(By.xpath(temp.concat(subMenuLinkXpath)));
			for(int j=1;j<subMenuLink.size();j++){
				String tempXpathForSubMenu = "("+temp.concat(subMenuLinkXpath)+")"+"["+j+"]";
				WebElement subMenu = webPage.findPageObjectByXPath(tempXpathForSubMenu, IPageObjectType.Link);
				mouse.mouseMove(((Locatable) subMenu).getCoordinates());
				webPage.sleep(2000);
				al.add(subMenu.getCssValue("background-color"));
			}
		}
		if(al.contains(expHighlightedColor)){
			value = true;
			
		}
		return value;
	}
	
	public boolean validateHamburgerButtonClickable(String hamburgerButtonXpath, String expandedSectionXpath) throws PageException {
		boolean value = false;
		WebElement hamburgerButton = webPage.findPageObjectByXPath(hamburgerButtonXpath, 20, IPageObjectType.Link, ExpectationTypes.ElementToBeClickable);
		hamburgerButton.click();
		WebElement menuLinks = webPage.findPageObjectByXPath(expandedSectionXpath, IPageObjectType.ComponentSection);
		if(menuLinks.isDisplayed())
			value = true;
		
		return value;
		
	}
	
	
	public boolean verifyFooterSectionPresentOnPage(String componentXpath) throws PageException {
		boolean value = false;
		((JavascriptExecutor) webPage.getDriver()).executeScript("window.scrollTo(0, document.body.scrollHeight)");
		webPage.sleep(1000);
		WebElement component = webPage.findPageObjectByXPath(componentXpath, 10, IPageObjectType.ComponentSection);
		if(component.isDisplayed()){
			value = true;
		}
		return value;
		
	}
	
	public boolean verifyElementContainsTextValue(String elementXpath, String expectedTextValue) throws PageException {
		boolean value = false;
		webPage.sleep(1000);
		WebElement element = webPage.findPageObjectByXPath(elementXpath, IPageObjectType.Text);
		String actualValue = element.getText().trim();
		if(actualValue.contains(expectedTextValue)){
			value = true;
		}
		return value;
	}
	
	
	public String validateClickFunctionalityOfLinks(String linksXpath) throws PageException {
		String urlAfterClicked = null;
		String urlBeforeClicked = webPage.getCurrentUrl();
			WebElement link = webPage.findPageObjectByXPath(linksXpath, IPageObjectType.Link);
			link.click();
			webPage.sleep(5000);
			Set<String> windows = webPage.getDriver().getWindowHandles();
			if (windows.size() == 1) {
			    urlAfterClicked = webPage.getCurrentUrl();
			} else {
				webPage.sleep(5000);
			   
			    if(BrowserInfoUtil.INSTANCE.isCloudAndroid() || BrowserInfoUtil.INSTANCE.isCloudIPhone()){
			    	// Switch to new window opened
			    	for(String winHandle : webPage.getDriver().getWindowHandles()){
			    		webPage.getDriver().switchTo().window(winHandle);
			    		 urlAfterClicked = webPage.getCurrentUrl();
			    	}
			    	 webPage.getDriver().get(urlBeforeClicked);
			    }else{
			    	 webPage.toggleWindow();
			    	 urlAfterClicked = webPage.getCurrentUrl();
					    webPage.close_toggleWindow();
			    }
			   
			}
			
		return urlAfterClicked;
	}
	
	
	public boolean verifyContenentDisplayedOnPage(String contenentSectionXpath) throws PageException {
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
	
	
	public boolean validateMultipleLinksPresent(String allLinksXpath, String exphrefDomainName) throws PageException {
		List<WebElement> allMenuLinks = webPage.getDriver().findElements(By.xpath(allLinksXpath));
		ArrayList<String> al = new ArrayList<String>();
		boolean value = false;
		for(int i=1;i<=allMenuLinks.size();i++){
			String temp = allLinksXpath.concat("["+i+"]");
			WebElement link = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			
			Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
			mouse.mouseMove(((Locatable) link).getCoordinates());
			webPage.sleep(2000);
			al.add(link.getAttribute("href").toString());
		}
		
		for(String hrefVal: al){
			if(hrefVal.contains(exphrefDomainName)){
				value = true;
			}
		}
		return value;
	}
	
	
	public boolean validateMultipleLinksAreClickable(String linksXpath) throws PageException {
		List<WebElement> allMenuLinks = webPage.getDriver().findElements(By.xpath(linksXpath));
		boolean value = false;
		String urlBeforeClicked = webPage.getCurrentUrl();
		String urlAfterClicked = null;
		ArrayList<Boolean> al = new ArrayList<Boolean>();
		for(int i=1;i<allMenuLinks.size();i++){
			String temp = linksXpath.concat("["+i+"]");
			WebElement link = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			link.click();
			Set<String> windows = webPage.getDriver().getWindowHandles();
			if (windows.size() == 0) {
				urlAfterClicked = webPage.getCurrentUrl();
				
			} else if(windows.size() == 1){
				 urlAfterClicked = webPage.getCurrentUrl();
			}
			else{
			    	webPage.toggleWindow();
			    	urlAfterClicked = webPage.getCurrentUrl();
					webPage.close_toggleWindow();
			}
			if(!urlBeforeClicked.equalsIgnoreCase(urlAfterClicked)){
				al.add(true);
			}
			if(al.contains(true)){
				 value = true;
			}
			webPage.getBackToUrl();
			
			webPage.waitForLoad(webPage.getDriver());
			webPage.sleep(2000);
		}
		return value;
		
	}
	
	public boolean verifyComponentLocationBasedOnPositionInDesktop(String firstComponentSectionXpath, String secondComponentSectionXpath, String thirdComponentSectionXpath, String fourthComponentSectionXpath) throws PageException {
		boolean value = false;
		WebElement firstComponent = webPage.findPageObjectByXPath(firstComponentSectionXpath, IPageObjectType.ComponentSection);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", firstComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer fistComponentYLocation = firstComponent.getLocation().getY();
		
		WebElement secondComponent = webPage.findPageObjectByXPath(secondComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer secondComponentYLocation = secondComponent.getLocation().getY();
		
		WebElement thirdComponent = webPage.findPageObjectByXPath(thirdComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer thirdComponentYLocation = thirdComponent.getLocation().getY();
		
		WebElement fourthComponent = webPage.findPageObjectByXPath(fourthComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer fourthComponentYLocation = fourthComponent.getLocation().getY();
		
		if(fistComponentYLocation.equals(thirdComponentYLocation) && secondComponentYLocation.equals(fourthComponentYLocation)){
			value = true;
		}
		
		return value;
		
	}
	
	public boolean verifyComponentLocationBasedOnPosition(String firstComponentSectionXpath, String secondComponentSectionXpath, String thirdComponentSectionXpath, String fourthComponentSectionXpath) throws PageException {
		boolean value = false;
		
		WebElement firstComponent = webPage.findPageObjectByXPath(firstComponentSectionXpath, IPageObjectType.ComponentSection);
		
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", firstComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer fistComponentYLocation = firstComponent.getLocation().getY();
		
		WebElement secondComponent = webPage.findPageObjectByXPath(secondComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer secondComponentYLocation = secondComponent.getLocation().getY();
		
		WebElement thirdComponent = webPage.findPageObjectByXPath(thirdComponentSectionXpath, IPageObjectType.ComponentSection);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", thirdComponent);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		Integer thirdComponentYLocation = thirdComponent.getLocation().getY();
		
		WebElement fourthComponent = webPage.findPageObjectByXPath(fourthComponentSectionXpath, IPageObjectType.ComponentSection);
		Integer fourthComponentYLocation = fourthComponent.getLocation().getY();
		
		if(fistComponentYLocation.equals(secondComponentYLocation) && thirdComponentYLocation.equals(fourthComponentYLocation)){
			value = true;
		}
		
		return value;
		
	}
}
