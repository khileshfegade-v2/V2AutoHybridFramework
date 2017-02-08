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
public class BDDTestMainNavigation extends BDDTestNGBaseClass
{
	public BDDTestMainNavigation()
	{
		super("com.v2solutions.components.TestMainNavigationClassFactory");
	}
	public boolean validateMenuLinksAreClickable() throws Throwable
	{
		return callMethod("validateMenuLinksAreClickable");
	}
	public boolean verifyPageIsResizedToDesktopViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToDesktopViewLayout");
	}
	public boolean verifyMainNavigationPresentInDesktopView() throws Throwable
	{
		return callMethod("verifyMainNavigationPresentInDesktopView");
	}
	public boolean verifyLogoPresentInMainNavigationDesktopView() throws Throwable
	{
		return callMethod("verifyLogoPresentInMainNavigationDesktopView");
	}
	public boolean validateOnMouseHoverSubMenuLinksAreHighlighted() throws Throwable
	{
		return callMethod("validateOnMouseHoverSubMenuLinksAreHighlighted");
	}
	public boolean verifyPageIsResizedToTabletViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToTabletViewLayout");
	}
	public boolean verifyMainNavigationPresentInTabletView() throws Throwable
	{
		return callMethod("verifyMainNavigationPresentInTabletView");
	}
	public boolean validateMenuLinksAreClickableInTabletView() throws Throwable
	{
		return callMethod("validateMenuLinksAreClickableInTabletView");
	}
	public boolean verifyLogoPresentInMainNavigationTabletView() throws Throwable
	{
		return callMethod("verifyLogoPresentInMainNavigationTabletView");
	}
	public boolean verifyPageIsResizedToMobileViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToMobileViewLayout");
	}
	public boolean verifyHamburgerButtonPresentInMobileView() throws Throwable
	{
		return callMethod("verifyHamburgerButtonPresentInMobileView");
	}
	public boolean validateHamburgerButtonClickableInMobileView() throws Throwable
	{
		return callMethod("validateHamburgerButtonClickableInMobileView");
	}
	public boolean verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView() throws Throwable
	{
		return callMethod("verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
	}
}
