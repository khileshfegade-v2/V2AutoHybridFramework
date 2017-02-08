package com.v2solutions.pages;

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
public class BDDTestV2SolutionsHomePage extends BDDTestNGBaseClass
{
	public BDDTestV2SolutionsHomePage()
	{
		super("com.v2solutions.pages.TestV2SolutionsHomePageClassFactory");
	}
	public boolean validateTabToggleWorkingFuctionalityInDesktopView() throws Throwable
	{
		return callMethod("validateTabToggleWorkingFuctionalityInDesktopView");
	}
	public boolean validateThumbnailIconRenderHorizontallyInOurIndustryFocusSection() throws Throwable
	{
		return callMethod("validateThumbnailIconRenderHorizontallyInOurIndustryFocusSection");
	}
	public boolean validatePaginationInOurCustomerSaysSlideSectionInDesktopView() throws Throwable
	{
		return callMethod("validatePaginationInOurCustomerSaysSlideSectionInDesktopView");
	}
	public boolean validatePaginationForCompaniesWorkedWithSectionInDesktopView() throws Throwable
	{
		return callMethod("validatePaginationForCompaniesWorkedWithSectionInDesktopView");
	}
	public boolean validateReadMoreLinkClickableInLatestBlogSectionDesktopView() throws Throwable
	{
		return callMethod("validateReadMoreLinkClickableInLatestBlogSectionDesktopView");
	}
	public boolean validateWorkingOfTabToggleFuctionalityInTabletView() throws Throwable
	{
		return callMethod("validateWorkingOfTabToggleFuctionalityInTabletView");
	}
	public boolean validatePaginationForCompaniesWorkedWithSectionInTabletView() throws Throwable
	{
		return callMethod("validatePaginationForCompaniesWorkedWithSectionInTabletView");
	}
	public boolean validatePaginationInOurCustomerSaysSlideSectionInTabletView() throws Throwable
	{
		return callMethod("validatePaginationInOurCustomerSaysSlideSectionInTabletView");
	}
	public boolean validateReadMoreLinkClickableInLatestBlogSectionTabletView() throws Throwable
	{
		return callMethod("validateReadMoreLinkClickableInLatestBlogSectionTabletView");
	}
	public boolean validateWorkingOfTabToggleFuctionalityInMobileView() throws Throwable
	{
		return callMethod("validateWorkingOfTabToggleFuctionalityInMobileView");
	}
	public boolean validatePaginationForCompaniesWorkedWithSectionInMobileView() throws Throwable
	{
		return callMethod("validatePaginationForCompaniesWorkedWithSectionInMobileView");
	}
	public boolean validatePaginationInOurCustomerSaysSlideSectionInMobileView() throws Throwable
	{
		return callMethod("validatePaginationInOurCustomerSaysSlideSectionInMobileView");
	}
	public boolean validateReadMoreLinkClickableInLatestBlogSectionMobileView() throws Throwable
	{
		return callMethod("validateReadMoreLinkClickableInLatestBlogSectionMobileView");
	}
	public boolean verifyPageIsResizedToDesktopViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToDesktopViewLayout");
	}
	public boolean validateLatestBlogSectionRenderedInDesktopView() throws Throwable
	{
		return callMethod("validateLatestBlogSectionRenderedInDesktopView");
	}
	public boolean verifyPageIsResizedToTabletViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToTabletViewLayout");
	}
	public boolean validateLatestBlogSectionRenderedInTabletView() throws Throwable
	{
		return callMethod("validateLatestBlogSectionRenderedInTabletView");
	}
	public boolean verifyPageIsResizedToMobileViewLayout() throws Throwable
	{
		return callMethod("verifyPageIsResizedToMobileViewLayout");
	}
	public boolean validateLatestBlogSectionRenderedInMobileView() throws Throwable
	{
		return callMethod("validateLatestBlogSectionRenderedInMobileView");
	}
	public boolean validateButtonLinksInInnovationThatMattersSectionAreHighlightedOnMouseHoverInDesktopView() throws Throwable
	{
		return callMethod("validateButtonLinksInInnovationThatMattersSectionAreHighlightedOnMouseHoverInDesktopView");
	}
	public boolean validateInOurSolutionsSectionTabToggleFunctionalityArrangeHorizontallynDesktopView() throws Throwable
	{
		return callMethod("validateInOurSolutionsSectionTabToggleFunctionalityArrangeHorizontallynDesktopView");
	}
	public boolean validateButtonLinksInInnovationThatMattersSectionAreClickableInDesktopView() throws Throwable
	{
		return callMethod("validateButtonLinksInInnovationThatMattersSectionAreClickableInDesktopView");
	}
	public boolean validateButtonLinksInInnovationThatMattersSectionAreClickableInTabletView() throws Throwable
	{
		return callMethod("validateButtonLinksInInnovationThatMattersSectionAreClickableInTabletView");
	}
	public boolean validateInOurSolutionsSectionTabToggleFunctionalityDisplayedInTabletView() throws Throwable
	{
		return callMethod("validateInOurSolutionsSectionTabToggleFunctionalityDisplayedInTabletView");
	}
	public boolean validateThumbnailIconRenderedInOurIndustryFocusSectionInTabletView() throws Throwable
	{
		return callMethod("validateThumbnailIconRenderedInOurIndustryFocusSectionInTabletView");
	}
	public boolean validateButtonLinksInInnovationThatMattersSectionAreClickableInMobileView() throws Throwable
	{
		return callMethod("validateButtonLinksInInnovationThatMattersSectionAreClickableInMobileView");
	}
	public boolean validateInOurSolutionsSectionTabToggleFunctionalityDisplayedInMobileView() throws Throwable
	{
		return callMethod("validateInOurSolutionsSectionTabToggleFunctionalityDisplayedInMobileView");
	}
	public boolean validateThumbnailIconRenderedInOurIndustryFocusSectionInMobileView() throws Throwable
	{
		return callMethod("validateThumbnailIconRenderedInOurIndustryFocusSectionInMobileView");
	}
}
