package com.v2solutions.cisco;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.server.rest.ResultType;

import com.v2solutions.taf.common.pages.CommonPage;
import com.v2solutions.taf.core.IPageObjectType;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.JSUtil;
import com.v2solutions.taf.util.LogUtil;


/**
* Page Class for ?? page. It calls the Framework APIs.
* 
* Test Class resembles the test specification. Page Class resembles the test
* implementation
* 
* @author v2solutions pvt ltd
* Generation Date: 02/06/2017
* 
*/

public class CISCO_TC extends CommonPage 
{
	private static Log log = LogUtil.getLog(CISCO_TC.class);

	public CISCO_TC(String sbPageUrl, WebPage webPage) 
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
	/*
	* Please write method description here.
	* Verify page is resized according to Desktop view.
	*/
	public boolean verifyPageIsResizedAccordingToDesktopView(int expWidth,int expHeight)
	{
		int actualHeight;
		int actualWidth;
		boolean value = false;
		//Get size of opened browser window 
		Dimension dimension = webPage.getDriver().manage().window().getSize();
		actualHeight = dimension.getHeight();
		actualWidth = dimension.getWidth();
		log.info("Get the browsers dimension in (width*height)"+"("+actualWidth+"*"+actualHeight+")");
		//Compare actual width and height with expected width and height of the browser 
		if(BrowserInfoUtil.INSTANCE.isCloudChrome()){
			if(actualWidth == expWidth){
				value = true;
			}
		}else{
		if(actualHeight==expHeight && actualWidth == expWidth)
			value = true;
		else 
			value = false;
		}
		return value;
	}

	
	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	public boolean inDesktopViewVerifyUserCanLoginWithValidCredentials(String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath) throws PageException
	{
		boolean value = false;
		
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		webPage.sleep(2000);
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.sendKeys(ID);
		webPage.sleep(5000);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		signInButton.click();

		webPage.waitForLoad(webPage.getDriver());
		
		WebElement loggedUser = webPage.findPageObjectByXPath(loggedUserNameXpath, IPageObjectType.Text);
		String loggedUserName = loggedUser.getText(); 
		if(ID.contains(loggedUserName)){
			value = true;
		}
		
		return value;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user cannot login with invalid credentials.
	*/
	public String inDesktopViewVerifyUserCannotLoginWithInvalidCredentials(String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMsgXpath, String expectedErrorMessage) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.clear();
		webPage.sleep(2000);
		email.sendKeys(ID);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.clear();
		webPage.sleep(2000);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		signInButton.click();
		webPage.sleep(6000);
		WebElement errorMsg = webPage.findPageObjectByXPath(errorMsgXpath, IPageObjectType.Text);
		String errorMsgText = errorMsg.getText();
		
		return errorMsgText;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify Search field default text.
	*/
	public String inDesktopViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement searchBox = webPage.findPageObjectByXPath(searchIconXpath, IPageObjectType.Icon);
		searchBox.click();
		webPage.sleep(2000);
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		String defaultMsg = searchInputField.getAttribute("placeholder");
		
		return defaultMsg;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user can perform search.
	*/
	public boolean inDesktopViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath, String expectedText) throws PageException
	{
		boolean value = false;
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		searchInputField.click();
		searchInputField.clear();
		webPage.sleep(2000);
		searchInputField.sendKeys(inputText);
		
		WebElement searchIcon = webPage.findPageObjectByXPath(searchFinderXpath, IPageObjectType.Icon);
		searchIcon.click();
		webPage.sleep(2000);
		
		WebElement searchPageResult = webPage.findPageObjectByXPath(searchResultSetXpath, IPageObjectType.Text);
		if(searchPageResult.getText().contains(expectedText)){
			value = true;
		}
		
		return value;
	}

	
	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	public boolean inDesktopViewVerifyUserCanLoggoutSuccessfully(String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean value= false;
		WebElement logOutLink = webPage.findPageObjectByXPath(logoutLinkXpath, IPageObjectType.Link);
		logOutLink.click();
		webPage.waitForLoad(webPage.getDriver());
		WebElement logoutSuccessMsg = webPage.findPageObjectByXPath(logoutSuccessMsgXpath, IPageObjectType.ComponentSection);
		if(logoutSuccessMsg.isDisplayed())
		 value = true;
		return value;
	}

	/*
	* Please write method description here.
	* Verify page is resized according to Tablet view.
	*/
	public boolean verifyPageIsResizedAccordingToTabletView(int expWidth,int expHeight)
	{
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


	/*
	* Please write method description here.
	* In Tablet view - Verify user can login with valid credentials.
	*/
	public boolean inTabletViewVerifyUserCanLoginWithValidCredentials(String hambergerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		boolean value = false;
		WebElement hambergerButton = webPage.findPageObjectByXPath(hambergerButtonXpath, IPageObjectType.Button);
		hambergerButton.click();
		webPage.sleep(2000);
		
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", loginButton);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		webPage.sleep(2000);
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.sendKeys(ID);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", signInButton);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		signInButton.click();
		
		
		WebElement loggedUser = webPage.findPageObjectByXPath(loggedUserNameXpath, IPageObjectType.Text);
		String loggedUserName = loggedUser.getText(); 
		if(ID.contains(loggedUserName)){
			value = true;
		}
		
		return value;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user cannot login with invalid credentials.
	*/
	public String inTabletViewVerifyUserCannotLoginWithInvalidCredentials(String hamburgerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMsgXpath, String expectedErrorMessage) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		WebElement hambergerButton = webPage.findPageObjectByXPath(hamburgerButtonXpath, IPageObjectType.Button);
		hambergerButton.click();
		webPage.sleep(2000);
		
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.clear();
		webPage.sleep(2000);
		email.sendKeys(ID);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.clear();
		webPage.sleep(2000);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		signInButton.click();
		webPage.sleep(6000);
		WebElement errorMsg = webPage.findPageObjectByXPath(errorMsgXpath, IPageObjectType.Text);
		String errorMsgText = errorMsg.getText();
		
		return errorMsgText;
	}

	/*
	* Please write method description here.
	* In Tablet view - Verify Search field default text.
	*/
	public String inTabletViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement searchBox = webPage.findPageObjectByXPath(searchIconXpath, IPageObjectType.Icon);
		searchBox.click();
		webPage.sleep(2000);
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		String defaultMsg = searchInputField.getAttribute("placeholder");
		
		return defaultMsg;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user can perform search.
	*/
	public boolean inTabletViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath, String expectedText) throws PageException
	{
		boolean value = false;
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		searchInputField.click();
		searchInputField.clear();
		webPage.sleep(2000);
		searchInputField.sendKeys(inputText);
		
		WebElement searchIcon = webPage.findPageObjectByXPath(searchFinderXpath, IPageObjectType.Icon);
		webPage.sleep(2000);
		searchIcon.click();
		webPage.sleep(2000);
		
		WebElement searchPageResult = webPage.findPageObjectByXPath(searchResultSetXpath, IPageObjectType.Text);
		if(searchPageResult.isDisplayed()){
			value = true;
		}
		
		return value;
	}

	
	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	public boolean inTabletViewVerifyUserCanLoggoutSuccessfully(String hamburgerButtonXpath, String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean value= false;
		WebElement hamburgerButton = webPage.findPageObjectByXPath(hamburgerButtonXpath, IPageObjectType.Link);
		hamburgerButton.click();
		webPage.sleep(2000);
		WebElement logOutLink = webPage.findPageObjectByXPath(logoutLinkXpath, IPageObjectType.Link);
		logOutLink.click();
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement logoutSuccessMsg = webPage.findPageObjectByXPath(logoutSuccessMsgXpath, IPageObjectType.ComponentSection);
		if(logoutSuccessMsg.isDisplayed())
			 value = true;
		return value;
	}

	/*
	* Please write method description here.
	* Verify page is resized according to Mobile view.
	*/
	public boolean verifyPageIsResizedAccordingToMobileView(int expWidth,int expHeight)
	{
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


	/*
	* Please write method description here.
	* In Mobile view - Verify user can login with valid credentials.
	*/
	public boolean inMobileViewVerifyUserCanLoginWithValidCredentials(String hambergerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath) throws PageException
	{
		boolean value = false;
		WebElement hambergerButton = webPage.findPageObjectByXPath(hambergerButtonXpath, IPageObjectType.Button);
		hambergerButton.click();
		
		
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", loginButton);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		webPage.sleep(2000);
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.sendKeys(ID);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", signInButton);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		signInButton.click();
		
		
		WebElement loggedUser = webPage.findPageObjectByXPath(loggedUserNameXpath, IPageObjectType.Text);
		String loggedUserName = loggedUser.getText(); 
		if(ID.contains(loggedUserName)){
			value = true;
		}
		
		return value;
	}

	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	public boolean inMobileViewVerifyUserCanLoggoutSuccessfully(String hamburgerButtonXpath, String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean value= false;
		WebElement hamburgerButton = webPage.findPageObjectByXPath(hamburgerButtonXpath, IPageObjectType.Link);
		hamburgerButton.click();
		webPage.sleep(2000);
		WebElement logOutLink = webPage.findPageObjectByXPath(logoutLinkXpath, IPageObjectType.Link);
		logOutLink.click();
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement logoutSuccessMsg = webPage.findPageObjectByXPath(logoutSuccessMsgXpath, IPageObjectType.ComponentSection);
		if(logoutSuccessMsg.isDisplayed())
			 value = true;
		return value;
	}

	/*
	* Please write method description here.
	* In Mobile view - Verify user cannot login with invalid credentials.
	*/
	public String inMobileViewVerifyUserCannotLoginWithInvalidCredentials(String hamburgerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMsgXpath, String expectedErrorMessage) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		WebElement hambergerButton = webPage.findPageObjectByXPath(hamburgerButtonXpath, IPageObjectType.Button);
		hambergerButton.click();
		webPage.sleep(2000);
		
		WebElement loginButton = webPage.findPageObjectByXPath(loginButtonXpath, IPageObjectType.Link);
		loginButton.click();
		webPage.waitForLoad(webPage.getDriver());
		WebElement email = webPage.findPageObjectByXPath(emailTextFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) email).getCoordinates());
		webPage.sleep(2000);
		email.click();
		email.clear();
		webPage.sleep(2000);
		email.sendKeys(ID);
		
		WebElement password = webPage.findPageObjectByXPath(pwdTextFieldXpath, IPageObjectType.TextBox);
		password.clear();
		webPage.sleep(2000);
		password.sendKeys(pwd);
		
		WebElement signInButton = webPage.findPageObjectByXPath(loginButtonXPath, IPageObjectType.Button);
		((JavascriptExecutor) webPage.getDriver()).executeScript("arguments[0].scrollIntoView(true);", signInButton);
		webPage.sleep(2000);
		JSUtil.jsExecuteScript(webPage.getDriver(), "window.scrollBy(0,-250)","");
		webPage.sleep(2000);
		signInButton.click();
		webPage.sleep(6000);
		WebElement errorMsg = webPage.findPageObjectByXPath(errorMsgXpath, IPageObjectType.Text);
		String errorMsgText = errorMsg.getText();
		
		return errorMsgText;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify Search field default text.
	*/
	public String inMobileViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath) throws PageException
	{
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
		
		WebElement searchBox = webPage.findPageObjectByXPath(searchIconXpath, IPageObjectType.Icon);
		searchBox.click();
		webPage.sleep(2000);
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		String defaultMsg = searchInputField.getAttribute("placeholder");
		
		return defaultMsg;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user can perform search.
	*/
	public boolean inMobileViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath) throws PageException
	{
boolean value = false;
		
		WebElement searchInputField = webPage.findPageObjectByXPath(searchInputFieldXpath, IPageObjectType.TextBox);
		Mouse mouse = ((HasInputDevices) webPage.getDriver()).getMouse();
		mouse.mouseMove(((Locatable) searchInputField).getCoordinates());
		webPage.sleep(2000);
		searchInputField.click();
		searchInputField.clear();
		webPage.sleep(2000);
		searchInputField.sendKeys(inputText);
		
		WebElement searchIcon = webPage.findPageObjectByXPath(searchFinderXpath, IPageObjectType.Icon);
		searchIcon.click();
		webPage.sleep(2000);
		
		WebElement searchPageResult = webPage.findPageObjectByXPath(searchResultSetXpath, IPageObjectType.Text);
		if(searchPageResult.getText().contains(inputText)){
			value = true;
		}
		
		return value;
	}

}
