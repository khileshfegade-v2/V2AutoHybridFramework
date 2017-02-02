package test.v2solutions.components;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.v2solutions.common.BaseTest;
import com.v2solutions.components.V2SolutiosPageComponents;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.dataprovider.TafExcelDataProvider;
import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;
import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;
import com.v2solutions.taf.listener.SuiteListener;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

@IExcelDataFiles(excelDataFiles = { "v2solutionshomepage=src/test/resources/testdata/automation_testdata.xls"})
public class TestPageFootersSection extends BaseTest{
	
/**
 * Khilesh Fegade
 * 
 */
		private Log log = LogUtil.getLog(TestPageFootersSection.class);
		private V2SolutiosPageComponents v2SolutiosPageComponents = null;
		private String v2SolutionsMainPageURL = null;
		WebPage webPage = null;
		boolean flag = false;
		
		@BeforeClass(alwaysRun = true)
		public void prepareBeforeClass() throws Exception {
			if(classInitDone == false )	{
				super.loadDriver(); 
				loadURL();
				webPage = new WebPage(testBedManager.getDriver(), testBedManager);
				v2SolutiosPageComponents = new V2SolutiosPageComponents(v2SolutionsMainPageURL, webPage);
				webPage.waitForLoad(webPage.getDriver());
				classInitDone = true;
			}

		}

		private void loadURL() {
			v2SolutionsMainPageURL = constructURL(SuiteListener.pageURLs.getProperty("V2SolutionsHome_Page"));
		}

		/**
		 * Verify page is Resized to Desktop layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 1, enabled = true, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "desktop_layout")
		public void verifyPageIsResizedToDesktopViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "Page is not resized according to Desktop layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		
		
		
		/**
		 * Verify page footer section present in Desktop View
		 * @param componentXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = true, description = "Verify page footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterSection")
		public void verifyPageFooterSectionPresentInDesktopView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Page footer section not present in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer section with xpath", componentXpath, "Is Page footer rendered in desktop", String.valueOf(value), String.valueOf(true), "defect_verifyPageFooterSectionPresentInDesktopView", "verifyPageFooterSectionPresentInDesktopView");
			}
		}
		
		
		/**
		 * Verify page footer component placed horizontally in Desktop View
		 * @param firstComponentSectionXpath
		 * @param secondComponentSectionXpath
		 * @param thirdComponentSectionXpath
		 * @param fourthComponentSectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = true, description = "Verify page footer component placed horizontally in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterComponentSections")
		public void verifyPageFooterComponentsHorizontallyPlacedInDesktopView(String firstComponentSectionXpath, String secondComponentSectionXpath, String thirdComponentSectionXpath, String fourthComponentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyComponentLocationBasedOnPositionInDesktop(firstComponentSectionXpath, secondComponentSectionXpath, thirdComponentSectionXpath, fourthComponentSectionXpath);
				Assert.assertTrue(value, "Page footer component not placed horizontally in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer first component xpath :"+firstComponentSectionXpath+", second component xpath :"+secondComponentSectionXpath+", third component xpath :"+thirdComponentSectionXpath+", fourth component xpath :", fourthComponentSectionXpath, "page footer component rendered horizontally in desktop view", String.valueOf(value), String.valueOf(true), "defect_verifyPageFooterComponentHorizontallyPlacedInDesktopView", "verifyPageFooterComponentsHorizontallyPlacedInDesktopView");
			}
		}
		
		
		/**
		 * Verify text content present in value delivered section in Desktop View
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = true, description = "Verify text content present in value delivered section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyInValueDeliveredSectionTextIsPresentInDesktopView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Text content not present in value delivered section in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyInValueDeliveredSectionTextIsPresentInDesktopView", "verifyInValueDeliveredSectionTextIsPresentInDesktopView");
			}
		}
		
		/**
		 * Validate twitter section containing multiple twitter navigating links in desktop view
		 * @param allLinksXpath
		 * @param exphrefDomainName
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = true, description = "Validate twitter section containing multiple twitter navigating links in desktop view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "TwitterLinks")
		public void validateTwitterSectionContainingTwitterLinksInDesktopView(String allLinksXpath, String exphrefDomainName)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksPresent(allLinksXpath, exphrefDomainName);
				Assert.assertTrue(value, "Twitter section not containing multiple twitter navigating links in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Locating all twitter links with xpath: ", allLinksXpath, "Twitter section containing multiple no. of links", String.valueOf(value), String.valueOf(true), "defect_validateTwitterSectionContainingTwitterLinksInDesktopView", "validateTwitterSectionContainingTwitterLinksInDesktopView");
			}
		}
		
		
		/**
		 * Validate whether quick links are clickable in desktop view
		 * @param linksXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = true, description = "Validate whether quick links are clickable in desktop view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "QuickLinks")
		public void validateQuickLinksAreClickableInDesktopView(String linksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksAreClickable(linksXpath);
				Assert.assertTrue(value, "Quick links are not clickable in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "All quick links with xpath", linksXpath, "Are quick links clickable", String.valueOf(value), String.valueOf(true), "defect_validateQuickLinksAreClickableInDesktopView", "validateQuickLinksAreClickableInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 7, enabled = true, description = "Verify logo present in page footer in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "LogoInPageFooter")
		public void verifyLogoDisplayedInPageFooter(String logoXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(logoXpath);
				Assert.assertTrue(value, "Logo not present in page footer in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Logo in page footer with xpath", logoXpath, "Is logo present in page footer section", String.valueOf(value), String.valueOf(true), "defect_verifyLogoDisplayedInPageFooter", "verifyLogoDisplayedInPageFooter");
			}
		}
		
		
		/**
		 * Verify company address displayed in page footer below contact us section
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 8, enabled = true, description = "Verify company address displayed in page footer below contact us section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyAddressPresentBelowContactUsSectionInDesktopView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Company address not displayed in page footer below contact us section");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Address below contact us section rendered with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyAddressPresentBelowContactUsSectionInDesktopView", "verifyAddressPresentBelowContactUsSectionInDesktopView");
			}
		}
		
		
		/**
		 * Verify page is Resized to Tablet layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = true, description = "Verify Page is Resized to 'Tablet view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "tablet_layout")
		public void verifyPageIsResizedToTabletViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "Page is not resized according to Tablet layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedToTabletViewLayout", "verifyPageIsResizedToTabletViewLayout");
			}
		}
		
		
		
		/**
		 * Verify page footer section present in Tablet View
		 * @param componentXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = true, description = "Verify page footer section present in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterSection")
		public void verifyPageFooterSectionPresentInTabletView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Page footer section not present in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer section with xpath", componentXpath, "Is Page footer rendered in desktop", String.valueOf(value), String.valueOf(true), "defect_verifyPageFooterSectionPresentInTabletView", "verifyPageFooterSectionPresentInTabletView");
			}
		}
		
		
		/**
		 * Verify page footer component placed horizontally in Tablet View
		 * @param firstComponentSectionXpath
		 * @param secondComponentSectionXpath
		 * @param thirdComponentSectionXpath
		 * @param fourthComponentSectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 11, enabled = true, description = "Verify page footer component placed horizontally in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterComponentSections")
		public void verifyPageFooterComponentsHorizontallyPlacedInTabletView(String firstComponentSectionXpath, String secondComponentSectionXpath, String thirdComponentSectionXpath, String fourthComponentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyComponentLocationBasedOnPosition(firstComponentSectionXpath, secondComponentSectionXpath, thirdComponentSectionXpath, fourthComponentSectionXpath);
				Assert.assertTrue(value, "Page footer component not placed horizontally in Tablet View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer first component xpath :"+firstComponentSectionXpath+", second component xpath :"+secondComponentSectionXpath+", third component xpath :"+thirdComponentSectionXpath+", fourth component xpath :", fourthComponentSectionXpath, "page footer component rendered horizontally in Tablet view", String.valueOf(value), String.valueOf(true), "defect_verifyPageFooterComponentHorizontallyPlacedInTabletView", "verifyPageFooterComponentsHorizontallyPlacedInTabletView");
			}
		}
		
		
		/**
		 * Verify text content present in value delivered section in Tablet View
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 12, enabled = true, description = "Verify text content present in value delivered section in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyInValueDeliveredSectionTextIsPresentInTabletView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Text content not present in value delivered section in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyInValueDeliveredSectionTextIsPresentInTabletView", "verifyInValueDeliveredSectionTextIsPresentInTabletView");
			}
		}
		
		/**
		 * Validate twitter section containing multiple twitter navigating links in Tablet view
		 * @param allLinksXpath
		 * @param exphrefDomainName
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 13, enabled = true, description = "Validate twitter section containing multiple twitter navigating links in Tablet view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "TwitterLinks")
		public void validateTwitterSectionContainingTwitterLinksInTabletView(String allLinksXpath, String exphrefDomainName)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksPresent(allLinksXpath, exphrefDomainName);
				Assert.assertTrue(value, "Twitter section not containing multiple twitter navigating links in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Locating all twitter links with xpath: ", allLinksXpath, "Twitter section containing multiple no. of links", String.valueOf(value), String.valueOf(true), "defect_validateTwitterSectionContainingTwitterLinksInTabletView", "validateTwitterSectionContainingTwitterLinksInTabletView");
			}
		}
		
		
		/**
		 * Validate whether quick links are clickable in desktop view
		 * @param linksXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 14, enabled = true, description = "Validate whether quick links are clickable in Tablet view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "QuickLinks")
		public void validateQuickLinksAreClickableInTabletView(String linksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksAreClickable(linksXpath);
				Assert.assertTrue(value, "Quick links are not clickable in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "All quick links with xpath", linksXpath, "Are quick links clickable", String.valueOf(value), String.valueOf(true), "defect_validateQuickLinksAreClickableInTabletView", "validateQuickLinksAreClickableInTabletView");
			}
		}
		
		/**
		 * Verify logo present in page footer in Tablet View
		 * @param logoXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 15, enabled = true, description = "Verify logo present in page footer in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "LogoInPageFooter")
		public void verifyLogoDisplayedInPageFooterInTabletView(String logoXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(logoXpath);
				Assert.assertTrue(value, "Logo not present in page footer in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Logo in page footer with xpath", logoXpath, "Is logo present in page footer section", String.valueOf(value), String.valueOf(true), "defect_verifyLogoDisplayedInPageFooterInTabletView", "verifyLogoDisplayedInPageFooterInTabletView");
			}
		}
		
		
		/**
		 * Verify company address displayed in page footer below contact us section in Tablet view
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 16, enabled = true, description = "Verify company address displayed in page footer below contact us section in Tablet view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyAddressPresentBelowContactUsSectionInTabletView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Company address not displayed in page footer below contact us section");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Address below contact us section rendered with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyAddressPresentBelowContactUsSectionInTabletView", "verifyAddressPresentBelowContactUsSectionInTabletView");
			}
		}
		
		//--------------------- mobileeeeeeeeee --------------
		
		
		/**
		 * Verify page is Resized to Mobile layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 17, enabled = true, description = "Verify Page is Resized to 'Mobile view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "mobile_layout")
		public void verifyPageIsResizedToMobileViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "Page is not resized according to Tablet layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedToMobileViewLayout", "verifyPageIsResizedToMobileViewLayout");
			}
		}
		
		
		
		/**
		 * Verify page footer section present in Mobile View
		 * @param componentXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 18, enabled = true, description = "Verify page footer section present in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterSection")
		public void verifyPageFooterSectionPresentInMobileView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Page footer section not present in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer section with xpath", componentXpath, "Is Page footer rendered in desktop", String.valueOf(value), String.valueOf(true), "defect_verifyPageFooterSectionPresentInMobileView", "verifyPageFooterSectionPresentInMobileView");
			}
		}
		
		
		/**
		 * Verify page footer component placed horizontally in Mobile View
		 * @param firstComponentSectionXpath
		 * @param secondComponentSectionXpath
		 * @param thirdComponentSectionXpath
		 * @param fourthComponentSectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 19, enabled = true, description = "verify two page footer components placed horizonatlly and other two below it in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "PageFooterComponentSections")
		public void verifyTwoPageFooterComponentsPlacedHorizonatllyAndOtherTwoBelowItInMobileView(String firstComponentSectionXpath, String secondComponentSectionXpath, String thirdComponentSectionXpath, String fourthComponentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyComponentLocationBasedOnPosition(firstComponentSectionXpath, secondComponentSectionXpath, thirdComponentSectionXpath, fourthComponentSectionXpath);
				Assert.assertTrue(value, "Page footer component not placed horizontally in Tablet View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Page footer first component xpath :"+firstComponentSectionXpath+", second component xpath :"+secondComponentSectionXpath+", third component xpath :"+thirdComponentSectionXpath+", fourth component xpath :", fourthComponentSectionXpath, "page footer component rendered horizontally in Mobile view", String.valueOf(value), String.valueOf(true), "defect_verifyTwoPageFooterComponentsPlacedHorizonatllyAndOtherTwoBelowItInMobileView", "verifyTwoPageFooterComponentsPlacedHorizonatllyAndOtherTwoBelowItInMobileView");
			}
		}
		
		
		/**
		 * Verify text content present in value delivered section in Mobile View
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 20, enabled = true, description = "Verify text content present in value delivered section in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyInValueDeliveredSectionTextIsPresentInMobileView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Text content not present in value delivered section in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyInValueDeliveredSectionTextIsPresentInMobileView", "verifyInValueDeliveredSectionTextIsPresentInMobileView");
			}
		}
		
		/**
		 * Validate twitter section containing multiple twitter navigating links in Mobile view
		 * @param allLinksXpath
		 * @param exphrefDomainName
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 21, enabled = true, description = "Validate twitter section containing multiple twitter navigating links in Mobile view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "TwitterLinks")
		public void validateTwitterSectionContainingTwitterLinksInMobileView(String allLinksXpath, String exphrefDomainName)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksPresent(allLinksXpath, exphrefDomainName);
				Assert.assertTrue(value, "Twitter section not containing multiple twitter navigating links in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Locating all twitter links with xpath: ", allLinksXpath, "Twitter section containing multiple no. of links", String.valueOf(value), String.valueOf(true), "defect_validateTwitterSectionContainingTwitterLinksInMobileView", "validateTwitterSectionContainingTwitterLinksInMobileView");
			}
		}
		
		
		/**
		 * Validate whether quick links are clickable in desktop view
		 * @param linksXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 22, enabled = true, description = "Validate whether quick links are clickable in Mobile view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "QuickLinks")
		public void validateQuickLinksAreClickableInMobileView(String linksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMultipleLinksAreClickable(linksXpath);
				Assert.assertTrue(value, "Quick links are not clickable in desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "All quick links with xpath", linksXpath, "Are quick links clickable", String.valueOf(value), String.valueOf(true), "defect_validateQuickLinksAreClickableInMobileView", "validateQuickLinksAreClickableInMobileView");
			}
		}
		
		/**
		 * Verify logo present in page footer in Mobile View
		 * @param logoXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 23, enabled = true, description = "Verify logo present in page footer in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "LogoInPageFooter")
		public void verifyLogoDisplayedInPageFooterInMobileView(String logoXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(logoXpath);
				Assert.assertTrue(value, "Logo not present in page footer in Desktop View");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Logo in page footer with xpath", logoXpath, "Is logo present in page footer section", String.valueOf(value), String.valueOf(true), "defect_verifyLogoDisplayedInPageFooterInMobileView", "verifyLogoDisplayedInPageFooterInMobileView");
			}
		}
		
		
		/**
		 * Verify company address displayed in page footer below contact us section in Mobile view
		 * @param sectionXpath
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 24, enabled = true, description = "Verify company address displayed in page footer below contact us section in Mobile view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Verify_ValueDelivered_content")
		public void verifyAddressPresentBelowContactUsSectionInMobileView(String sectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyContenentDisplayedOnPage(sectionXpath);
				Assert.assertTrue(value, "Company address not displayed in page footer below contact us section");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Address below contact us section rendered with xpath", sectionXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyAddressPresentBelowContactUsSectionInMobileView", "verifyAddressPresentBelowContactUsSectionInMobileView");
			}
		}
		
		
}
