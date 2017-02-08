package com.v2solutions.cisco;

/**
*	This class is generated with code generating utility.
*	This class act as Concordion proxy glue code. What that means is, TestNG test methods 
*	are called from Concordion runner class directly. In other word, Concordion implementation
*	has no code of itself and it calls already implemented code in TestNG class.
*	Each of method in following class is embedded in html file which is called by Concordion.
*	Internally each method executed TestNG method by calling 'callMethod ' function (from Base class).
*	Code of initializing TestNG and calling its test methods/function (API's) is common to
*	all proxy glue code and hence kept in base class called BDDTestNGBaseClass. That's why
*	following class is extended from BDDTestNGBaseClass java class
*
*	Company: eTouch
*	Author: Mahendra Kale
*	Generation Date: 02/06/2017
*/

import com.v2solutions.taf.common.*;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import org.concordion.ext.runtotals.RunTotalsExtension;
import org.concordion.api.extension.Extensions;
import org.concordion.ext.LoggingTooltipExtension;
import org.concordion.ext.inputstyle.InputStyleExtension;
import org.concordion.ext.TimestampFormatterExtension;

@RunWith(ConcordionRunner.class)
@Extensions({RunTotalsExtension.class,LoggingTooltipExtension.class,InputStyleExtension.class,TimestampFormatterExtension.class})
public class BDDCISCO_TC extends BDDTestNGBaseClass
{
	public BDDCISCO_TC()
	{
		super("com.v2solutions.nydj.BDDNYDJ_TCClassFactory");
	}
	public boolean verifyPageIsResizedAccordingToDesktopView() throws Throwable
	{
		return callMethod("verifyPageIsResizedAccordingToDesktopView");
	}

	public boolean inDesktopViewVerifyUserCanLoginWithValidCredentials() throws Throwable
	{
		return callMethod("inDesktopViewVerifyUserCanLoginWithValidCredentials");
	}

	public boolean inDesktopViewVerifyUserCannotLoginWithInvalidCredentials() throws Throwable
	{
		return callMethod("inDesktopViewVerifyUserCannotLoginWithInvalidCredentials");
	}

	public boolean inDesktopViewVerifySearchFieldDefaultText() throws Throwable
	{
		return callMethod("inDesktopViewVerifySearchFieldDefaultText");
	}

	public boolean inDesktopViewVerifyUserCanPerformSearch() throws Throwable
	{
		return callMethod("inDesktopViewVerifyUserCanPerformSearch");
	}

	public boolean verifyPageIsResizedAccordingToTabletView() throws Throwable
	{
		return callMethod("verifyPageIsResizedAccordingToTabletView");
	}

	public boolean inTabletViewVerifyUserCanLoginWithValidCredentials() throws Throwable
	{
		return callMethod("inTabletViewVerifyUserCanLoginWithValidCredentials");
	}

	public boolean inTabletViewVerifyUserCannotLoginWithInvalidCredentials() throws Throwable
	{
		return callMethod("inTabletViewVerifyUserCannotLoginWithInvalidCredentials");
	}

	public boolean inTabletViewVerifySearchFieldDefaultText() throws Throwable
	{
		return callMethod("inTabletViewVerifySearchFieldDefaultText");
	}

	public boolean inTabletViewVerifyUserCanPerformSearch() throws Throwable
	{
		return callMethod("inTabletViewVerifyUserCanPerformSearch");
	}

	public boolean verifyPageIsResizedAccordingToMobileView() throws Throwable
	{
		return callMethod("verifyPageIsResizedAccordingToMobileView");
	}

	public boolean inMobileViewVerifyUserCanLoginWithValidCredentials() throws Throwable
	{
		return callMethod("inMobileViewVerifyUserCanLoginWithValidCredentials");
	}

	public boolean inMobileViewVerifyUserCannotLoginWithInvalidCredentials() throws Throwable
	{
		return callMethod("inMobileViewVerifyUserCannotLoginWithInvalidCredentials");
	}

	public boolean inMobileViewVerifySearchFieldDefaultText() throws Throwable
	{
		return callMethod("inMobileViewVerifySearchFieldDefaultText");
	}

	public boolean inMobileViewVerifyUserCanPerformSearch() throws Throwable
	{
		return callMethod("inMobileViewVerifyUserCanPerformSearch");
	}

}
