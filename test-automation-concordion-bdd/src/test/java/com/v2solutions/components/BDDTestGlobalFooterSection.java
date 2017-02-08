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
public class BDDTestGlobalFooterSection extends BDDTestNGBaseClass
{
	public BDDTestGlobalFooterSection()
	{
		super("com.v2solutions.components.TestGlobalFooterSectionClassFactory");
	}
	public boolean verifySocialIconRenderedInFooterSectionOfDesktopView() throws Throwable
	{
		return callMethod("verifySocialIconRenderedInFooterSectionOfDesktopView");
	}
	public boolean verifyALTTagNameOFSocialIconInFooterSectionOfDesktopView() throws Throwable
	{
		return callMethod("verifyALTTagNameOFSocialIconInFooterSectionOfDesktopView");
	}
	public boolean verifySocialIconInFooterSectionAreClickableInDesktopView() throws Throwable
	{
		return callMethod("verifySocialIconInFooterSectionAreClickableInDesktopView");
	}
	public boolean verifySocialIconRenderedInFooterSectionOfTabletView() throws Throwable
	{
		return callMethod("verifySocialIconRenderedInFooterSectionOfTabletView");
	}
	public boolean verifyALTTagNameOFSocialIconInFooterSectionOfTabletView() throws Throwable
	{
		return callMethod("verifyALTTagNameOFSocialIconInFooterSectionOfTabletView");
	}
	public boolean verifySocialIconInFooterSectionAreClickableInTabletView() throws Throwable
	{
		return callMethod("verifySocialIconInFooterSectionAreClickableInTabletView");
	}
	public boolean verifySocialIconRenderedInFooterSectionOfMobileView() throws Throwable
	{
		return callMethod("verifySocialIconRenderedInFooterSectionOfMobileView");
	}
	public boolean verifyALTTagNameOFSocialIconInFooterSectionOfMobileView() throws Throwable
	{
		return callMethod("verifyALTTagNameOFSocialIconInFooterSectionOfMobileView");
	}
	public boolean verifySocialIconInFooterSectionAreClickableInMobileView() throws Throwable
	{
		return callMethod("verifySocialIconInFooterSectionAreClickableInMobileView");
	}
	public boolean verifyInGlobalFooterSectionCopyRightsMessageDisplayedInDesktopView() throws Throwable
	{
		return callMethod("verifyInGlobalFooterSectionCopyRightsMessageDisplayedInDesktopView");
	}
	public boolean verifyInGlobalFooterSectionCopyRightsMessageDisplayedInTabletView() throws Throwable
	{
		return callMethod("verifyInGlobalFooterSectionCopyRightsMessageDisplayedInTabletView");
	}
	public boolean verifyInGlobalFooterSectionCopyRightsMessageDisplayedInMobileView() throws Throwable
	{
		return callMethod("verifyInGlobalFooterSectionCopyRightsMessageDisplayedInMobileView");
	}
	public boolean verifyPageIsResizedToDesktopViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToDesktopViewLayout");
	}
	public boolean verifyGlobalFooterSectionPresentInDesktopView() throws Throwable
	{
		return callMethod("verifyGlobalFooterSectionPresentInDesktopView");
	}
	public boolean validateCopyRightsLinksClickableInDesktopView() throws Throwable
	{
		return callMethod("validateCopyRightsLinksClickableInDesktopView");
	}
	public boolean verifyPageIsResizedToTabletViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToTabletViewLayout");
	}
	public boolean verifyGlobalFooterSectionPresentInTabletView() throws Throwable
	{
		return callMethod("verifyGlobalFooterSectionPresentInTabletView");
	}
	public boolean validateCopyRightsLinksClickableInTabletView() throws Throwable
	{
		return callMethod("validateCopyRightsLinksClickableInTabletView");
	}
	public boolean verifyPageIsResizedToMobileViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToMobileViewLayout");
	}
	public boolean verifyGlobalFooterSectionPresentInMobileView() throws Throwable
	{
		return callMethod("verifyGlobalFooterSectionPresentInMobileView");
	}
	public boolean validateCopyRightsLinksClickableInMobileView() throws Throwable
	{
		return callMethod("validateCopyRightsLinksClickableInMobileView");
	}
}
