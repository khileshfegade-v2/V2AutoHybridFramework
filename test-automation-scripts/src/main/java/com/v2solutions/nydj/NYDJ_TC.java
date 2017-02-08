package com.v2solutions.nydj;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.rest.ResultType;

import com.v2solutions.taf.common.pages.CommonPage;
import com.v2solutions.taf.core.WebPage;
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

public class NYDJ_TC extends CommonPage 
{
	private static Log log = LogUtil.getLog(NYDJ_TC.class);

	public NYDJ_TC(String sbPageUrl, WebPage webPage) 
	{
		super(sbPageUrl, webPage);
		loadPage();
		webPage.waitForLoad(webPage.getDriver());
	}

	/*
	* Following method(s) code is generator by code generator for helping purpose to avoid duplication of works and speed up some of repeated task.
	* Developer is free to modify anything related to method including return type, method parameters, number of parameters, type of parameters and so on.
	*/


	/*
	* Please write method description here.
	* Verify page is resized according to Desktop view.
	*/
	public boolean verifyPageIsResizedAccordingToDesktopView()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	public boolean inDesktopViewVerifyUserCanLoginWithValidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user cannot login with invalid credentials.
	*/
	public boolean inDesktopViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify Search field default text.
	*/
	public boolean inDesktopViewVerifySearchFieldDefaultText()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user can perform search.
	*/
	public boolean inDesktopViewVerifyUserCanPerformSearch()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* Verify page is resized according to Tablet view.
	*/
	public boolean verifyPageIsResizedAccordingToTabletView()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user can login with valid credentials.
	*/
	public boolean inTabletViewVerifyUserCanLoginWithValidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user cannot login with invalid credentials.
	*/
	public boolean inTabletViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify Search field default text.
	*/
	public boolean inTabletViewVerifySearchFieldDefaultText()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user can perform search.
	*/
	public boolean inTabletViewVerifyUserCanPerformSearch()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* Verify page is resized according to Mobile view.
	*/
	public boolean verifyPageIsResizedAccordingToMobileView()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user can login with valid credentials.
	*/
	public boolean inMobileViewVerifyUserCanLoginWithValidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user cannot login with invalid credentials.
	*/
	public boolean inMobileViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify Search field default text.
	*/
	public boolean inMobileViewVerifySearchFieldDefaultText()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user can perform search.
	*/
	public boolean inMobileViewVerifyUserCanPerformSearch()
	{
		/* Actual logic of method and return true or false or any other data type depending on method logic.*/
		/* Method is returning true for completion purpose only.*/
		return true;
	}

}
