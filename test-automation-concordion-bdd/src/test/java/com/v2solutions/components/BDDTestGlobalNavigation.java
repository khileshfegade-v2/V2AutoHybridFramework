package com.v2solutions.components;

/**
*	This class is generated with code generating utility.
*	This is act as Concordion proxy glue code. What that means is, TestNG test methods 
*	are called from Concordion runner class directly. In other word, Concordion implementation
*	has no code of itself and it calls already implemented code in TestNG class.
*	Each of method in following class is embedded in html file which is called by Concordion.
*	Internally each method executed TestNG method by calling 'callMethod ' function (from Base class).
*	Code of initializing TestNG and calling its test methods/function (API's) is common to
*	all proxy glue code and hence kept in base class called BDDTestNGBaseClass. That's why
*	following class is extended from BDDTestNGBaseClass java class
*
*	Company: v2solutions
*	Author: v2solutions
*	Generation Date: 01/31/2017
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
public class BDDTestGlobalNavigation extends BDDTestNGBaseClass
{
	public BDDTestGlobalNavigation()
	{
		super("com.v2solutions.components.TestGlobalNavigationClassFactory");
	}
	public boolean verifyContactNoAndIDPresentInGlobalHeaderInDesktopView() throws Throwable
	{
		return callMethod("verifyContactNoAndIDPresentInGlobalHeaderInDesktopView");
	}
	public boolean verifySocialIconRenderedInGlobalHeaderInDesktopView() throws Throwable
	{
		return callMethod("verifySocialIconRenderedInGlobalHeaderInDesktopView");
	}
	public boolean verifySocialIconInGlobalHeaderAreClickableInDesktopView() throws Throwable
	{
		return callMethod("verifySocialIconInGlobalHeaderAreClickableInDesktopView");
	}
	public boolean verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView() throws Throwable
	{
		return callMethod("verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView");
	}
	public boolean verifyContactNoAndIDPresentInGlobalHeaderIntabletView() throws Throwable
	{
		return callMethod("verifyContactNoAndIDPresentInGlobalHeaderIntabletView");
	}
	public boolean verifyPageIsResizedToDesktopViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToDesktopViewLayout");
	}
	public boolean verifyGlobalNavigationHeaderPresentInDesktopView() throws Throwable
	{
		return callMethod("verifyGlobalNavigationHeaderPresentInDesktopView");
	}
	public boolean verifyPageIsResizedToTabletViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToTabletViewLayout");
	}
	public boolean verifyGlobalNavigationHeaderPresentInTabletView() throws Throwable
	{
		return callMethod("verifyGlobalNavigationHeaderPresentInTabletView");
	}
	public boolean verifyPageIsResizedToMobileViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToMobileViewLayout");
	}
	public boolean verifyGlobalNavigationHeaderPresentInMobileView() throws Throwable
	{
		return callMethod("verifyGlobalNavigationHeaderPresentInMobileView");
	}
}
