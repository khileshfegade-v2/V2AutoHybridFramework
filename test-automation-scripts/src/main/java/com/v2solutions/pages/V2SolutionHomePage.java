package com.v2solutions.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;

import com.v2solutions.components.V2SolutiosPageComponents;
import com.v2solutions.taf.common.pages.CommonPage;
import com.v2solutions.taf.core.IPageObjectType;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.JSUtil;
import com.v2solutions.taf.util.LogUtil;

public class V2SolutionHomePage extends CommonPage{
	
	private static Log log = LogUtil.getLog(V2SolutiosPageComponents.class);

	public V2SolutionHomePage(String sbPageUrl, WebPage webPage) 
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
	
	
	public boolean validatePagination() {
		return false;
		
	}
	
	
	public boolean validateButtonLinksHighlighted(String buttonLinksXpath, String expHighlightedColor) throws PageException {
		List<WebElement> bothButtons = webPage.getDriver().findElements(By.xpath(buttonLinksXpath));
		ArrayList<String> al = new ArrayList<String>();
		boolean value = false;
		for(int i=1;i<=bothButtons.size();i++){
			String temp = buttonLinksXpath.concat("["+i+"]");
			WebElement buttonLink = webPage.findPageObjectByXPath(temp, IPageObjectType.Link);
			Actions action = new Actions(webPage.getDriver());
			webPage.sleep(2000);
			action.moveToElement(buttonLink).build().perform();
			webPage.sleep(2000);
			
			al.add(buttonLink.getCssValue("background-color"));
			
		}
		System.out.println("Al-11 "+al.get(0));
		System.out.println("Al--22 "+al.get(1));
		
		if(al.contains(expHighlightedColor)){
			value = true;
			
		}
		return value;
	}
	
	
	public boolean validateButtonLinksClickable(String menuLinksXpath) throws PageException {
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
	
	
	public boolean validateInOurSolutionSectionTabToggleFunctionalityArrangedInHorizontally(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath) throws PageException {
		boolean value = false;
		
		WebElement tabSection = webPage.getDriver().findElement(By.xpath(tabSectionXpath));
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tabSection);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		
		Integer tabSectionYPosition = tabSection.getLocation().getY();
		System.out.println("tabSectionYPosition"+tabSectionYPosition);
		
		WebElement imageSection = webPage.getDriver().findElement(By.xpath(imageSectionXpath));
		Integer imageSectionYPosition = imageSection.getLocation().getY();
		System.out.println("imageSectionYPosition"+imageSectionYPosition);
		
		WebElement contentSection = webPage.getDriver().findElement(By.xpath(contentSectionXpath));
		Integer contentSectionYPosition = contentSection.getLocation().getY();
		System.out.println("contentSectionYPosition"+contentSectionYPosition);
		
		if((tabSectionYPosition.equals(imageSectionYPosition)) && tabSectionYPosition.equals(contentSectionYPosition)){
			value=true;
		}
		
		return value;
	}
	
	public boolean validateInOurSolutionSectionTabToggleFunctionalityArrangmentInTablet(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath) throws PageException {
		boolean value = false;
		
		WebElement tabSection = webPage.getDriver().findElement(By.xpath(tabSectionXpath));
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tabSection);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		
		Integer tabSectionYPosition = tabSection.getLocation().getY();
		System.out.println("tabSectionYPosition"+tabSectionYPosition);
		
		WebElement imageSection = webPage.getDriver().findElement(By.xpath(imageSectionXpath));
		Integer imageSectionYPosition = imageSection.getLocation().getY();
		System.out.println("imageSectionYPosition"+imageSectionYPosition);
		
		WebElement contentSection = webPage.getDriver().findElement(By.xpath(contentSectionXpath));
		Integer contentSectionYPosition = contentSection.getLocation().getY();
		System.out.println("contentSectionYPosition"+contentSectionYPosition);
		
		if(tabSectionYPosition.equals(imageSectionYPosition)){
			value=true;
		}
		
		return value;
	}
	
	
	public boolean validateInOurSolutionSectionTabToggleFunctionalityArrangmentInMobile(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath) throws PageException {
		boolean value = false;
		
		WebElement tabSection = webPage.getDriver().findElement(By.xpath(tabSectionXpath));
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", tabSection);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		
		Integer tabSectionXPosition = tabSection.getLocation().getX();
		System.out.println("tabSectionXPosition"+tabSectionXPosition);
		
		WebElement imageSection = webPage.getDriver().findElement(By.xpath(imageSectionXpath));
		Integer imageSectionXPosition = imageSection.getLocation().getX();
		System.out.println("imageSectionXPosition"+imageSectionXPosition);
		
		WebElement contentSection = webPage.getDriver().findElement(By.xpath(contentSectionXpath));
		Integer contentSectionXPosition = contentSection.getLocation().getX();
		System.out.println("contentSectionXPosition"+contentSectionXPosition);
		
		if((tabSectionXPosition.equals(imageSectionXPosition)) && tabSectionXPosition.equals(contentSectionXPosition)){
			value=true;
		}
		
		return value;
	}
	
	public boolean validateTabToggleFunctionality(String tabsXpath, String contentXpath) throws PageException {
		boolean value = false;
		List<WebElement> tabs = webPage.findPageObjectsByXPath(tabsXpath, IPageObjectType.ComponentSection);
		WebElement contentTitle = null;
		WebElement eachTab = null;
		ArrayList<String> tabName = new ArrayList<String>();
		ArrayList<String> contentName = new ArrayList<String>(); 
		for(int i=1;i<=tabs.size();i++){
			webPage.sleep(2000);
			
			String temp2 = contentXpath.concat("["+i+"]");
			System.out.println("Temp 22 :"+temp2);
			contentTitle = webPage.findPageObjectByXPath(temp2, IPageObjectType.Text);
			System.out.println("Content text :"+contentTitle.getText());
			contentName.add(contentTitle.getText());
			
			
			String temp1 = tabsXpath.concat("["+i+"]");
			 eachTab = webPage.findPageObjectByXPath(temp1, IPageObjectType.Link);
			System.out.println("Tab Nameeee :"+eachTab.getText());
			tabName.add(eachTab.getText());
			
			webPage.sleep(2000);
			eachTab.click();
		}
		
		for(int i=0;i<tabName.size();i++){
			if(tabName.contains(contentName.get(i))){
				System.out.println("Exist : "+contentName.get(i));
				value = true;
			}else{
				System.out.println("Not Exist : "+contentName.get(i));
				value = false;
			}
		}
		System.out.println("Tab Size ::"+tabName.size());
		System.out.println("Content Size ::"+contentName.size());
		return value;
		
	}
	
	
	
	public boolean validateThumbnailIconRenderHorizontally(String thumbnailIconsXpath) throws PageException {
		boolean value = false;
		((JavascriptExecutor) webPage.getDriver()).executeScript("window.scrollBy(0,500)", "");
		webPage.sleep(3000);
		List<WebElement> thumbnailIcons = webPage.findPageObjectsByXPath(thumbnailIconsXpath, IPageObjectType.ComponentSection);
		ArrayList<Integer> position = new ArrayList<Integer>();
		WebElement icon= null;
		for(int i=1;i<=thumbnailIcons.size();i++){
			webPage.sleep(2000);
			
			String iconXpath = thumbnailIconsXpath.concat("["+i+"]");
			icon = webPage.findPageObjectByXPath(iconXpath, IPageObjectType.Text);
			Integer YPosition = icon.getLocation().getY();
			position.add(YPosition);
		}
		if(position.get(0).equals(position.get(2)) ){
			value = true;
		}
		return value;
	}
	
	
	public boolean validatePaginationForCompaniesIconsInWorksWithSection() throws PageException {
		boolean value = false;
		ArrayList<String> attr = new ArrayList<String>();
		WebElement paginationSection = webPage.getDriver().findElement(By.xpath("(//div[@class='col-md-12'])[3]"));
		
		((JavascriptExecutor) webPage.getDriver()).executeScript(
                "arguments[0].scrollIntoView();", paginationSection);
		
		webPage.sleep(2000);
		
		List<WebElement> pages = webPage.getDriver().findElements(By.xpath("(//div[@class='partners flexslider']//ol//a)"));
		System.out.println("Size of pages : "+pages.size());
		for(int i=1; i<=pages.size(); i++){
			webPage.sleep(5000);
			WebElement page = webPage.getDriver().findElement(By.xpath("(//ol[@class='flex-control-nav flex-control-paging']//a)["+i+"]"));
			System.out.println("Before class value = "+page.getAttribute("class"));
			page.click();
			webPage.sleep(5000);
			page = webPage.getDriver().findElement(By.xpath("(//ol[@class='flex-control-nav flex-control-paging']//a)["+i+"]"));
			System.out.println("After class value = "+page.getAttribute("class"));
			attr.add(page.getAttribute("class"));
		}
		if(attr.contains("flex-active")){
			value = true;
		}
		return value;
	}
}
