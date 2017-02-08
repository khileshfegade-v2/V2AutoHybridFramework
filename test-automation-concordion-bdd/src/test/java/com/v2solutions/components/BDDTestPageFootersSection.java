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
public class BDDTestPageFootersSection extends BDDTestNGBaseClass
{
	public BDDTestPageFootersSection()
	{
		super("com.v2solutions.components.TestPageFootersSectionClassFactory");
	}
	public boolean verifyPageIsResizedToDesktopViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToDesktopViewLayout");
	}
	public boolean verifyPageFooterSectionPresentInDesktopView() throws Throwable
	{
		return callMethod("verifyPageFooterSectionPresentInDesktopView");
	}
	public boolean validateQuickLinksAreClickableInDesktopView() throws Throwable
	{
		return callMethod("validateQuickLinksAreClickableInDesktopView");
	}
	public boolean verifyPageIsResizedToTabletViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToTabletViewLayout");
	}
	public boolean verifyPageFooterSectionPresentInTabletView() throws Throwable
	{
		return callMethod("verifyPageFooterSectionPresentInTabletView");
	}
	public boolean validateQuickLinksAreClickableInTabletView() throws Throwable
	{
		return callMethod("validateQuickLinksAreClickableInTabletView");
	}
	public boolean verifyLogoDisplayedInPageFooterInTabletView() throws Throwable
	{
		return callMethod("verifyLogoDisplayedInPageFooterInTabletView");
	}
	public boolean verifyPageIsResizedToMobileViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToMobileViewLayout");
	}
	public boolean verifyPageFooterSectionPresentInMobileView() throws Throwable
	{
		return callMethod("verifyPageFooterSectionPresentInMobileView");
	}
	public boolean validateQuickLinksAreClickableInMobileView() throws Throwable
	{
		return callMethod("validateQuickLinksAreClickableInMobileView");
	}
	public boolean verifyLogoDisplayedInPageFooterInMobileView() throws Throwable
	{
		return callMethod("verifyLogoDisplayedInPageFooterInMobileView");
	}
	public boolean verifyTwoPageFooterComponentsPlacedHorizonatllyAndOtherTwoBelowItInMobileView() throws Throwable
	{
		return callMethod("verifyTwoPageFooterComponentsPlacedHorizonatllyAndOtherTwoBelowItInMobileView");
	}
	public boolean verifyPageFooterComponentsHorizontallyPlacedInDesktopView() throws Throwable
	{
		return callMethod("verifyPageFooterComponentsHorizontallyPlacedInDesktopView");
	}
	public boolean verifyInValueDeliveredSectionTextIsPresentInDesktopView() throws Throwable
	{
		return callMethod("verifyInValueDeliveredSectionTextIsPresentInDesktopView");
	}
	public boolean validateTwitterSectionContainingTwitterLinksInDesktopView() throws Throwable
	{
		return callMethod("validateTwitterSectionContainingTwitterLinksInDesktopView");
	}
	public boolean verifyAddressPresentBelowContactUsSectionInDesktopView() throws Throwable
	{
		return callMethod("verifyAddressPresentBelowContactUsSectionInDesktopView");
	}
	public boolean verifyPageFooterComponentsHorizontallyPlacedInTabletView() throws Throwable
	{
		return callMethod("verifyPageFooterComponentsHorizontallyPlacedInTabletView");
	}
	public boolean verifyInValueDeliveredSectionTextIsPresentInTabletView() throws Throwable
	{
		return callMethod("verifyInValueDeliveredSectionTextIsPresentInTabletView");
	}
	public boolean validateTwitterSectionContainingTwitterLinksInTabletView() throws Throwable
	{
		return callMethod("validateTwitterSectionContainingTwitterLinksInTabletView");
	}
	public boolean verifyAddressPresentBelowContactUsSectionInTabletView() throws Throwable
	{
		return callMethod("verifyAddressPresentBelowContactUsSectionInTabletView");
	}
	public boolean verifyInValueDeliveredSectionTextIsPresentInMobileView() throws Throwable
	{
		return callMethod("verifyInValueDeliveredSectionTextIsPresentInMobileView");
	}
	public boolean validateTwitterSectionContainingTwitterLinksInMobileView() throws Throwable
	{
		return callMethod("validateTwitterSectionContainingTwitterLinksInMobileView");
	}
	public boolean verifyAddressPresentBelowContactUsSectionInMobileView() throws Throwable
	{
		return callMethod("verifyAddressPresentBelowContactUsSectionInMobileView");
	}
	public boolean verifyLogoDisplayedInPageFooter() throws Throwable
	{
		return callMethod("verifyLogoDisplayedInPageFooter");
	}
}
