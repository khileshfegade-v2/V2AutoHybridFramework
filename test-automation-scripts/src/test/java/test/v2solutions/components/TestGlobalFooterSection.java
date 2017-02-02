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
public class TestGlobalFooterSection extends BaseTest{
	

		private Log log = LogUtil.getLog(TestGlobalFooterSection.class);
		private V2SolutiosPageComponents v2SolutionsPageComponents = null;
		private String v2SolutionsMainPageURL = null;
		WebPage webPage = null;
		boolean flag = false;
		
		@BeforeClass(alwaysRun = true)
		public void prepareBeforeClass() throws Exception {
			if(classInitDone == false )	{
				super.loadDriver(); 
				loadURL();
				webPage = new WebPage(testBedManager.getDriver(), testBedManager);
				v2SolutionsPageComponents = new V2SolutiosPageComponents(v2SolutionsMainPageURL, webPage);
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
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 1, enabled = false, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "desktop_layout")
		public void verifyPageIsResizedToDesktopViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutionsPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionsPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = false, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection")
		public void verifyGlobalFooterSectionPresentInDesktopView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = false, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection_CopyRightsMsg")
		public void verifyInGlobalFooterSectionCopyRightsMessageDisplayedInDesktopView(String elementXpath, String expectedTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementContainsTextValue(elementXpath,expectedTextValue);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", elementXpath, "Is Copy rights text rendered in footer section", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = false, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "CopyRights_links")
		public void validateCopyRightsLinksClickableInDesktopView(String linkXpath, String expectedURLValue)
		{

			String actualURLValue = null;
			try{
				actualURLValue = v2SolutionsPageComponents.validateClickFunctionalityOfLinks(linkXpath);
				Assert.assertEquals(actualURLValue, expectedURLValue, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", linkXpath, "Is Copy rights text rendered in footer section", actualURLValue, expectedURLValue, "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = false, description = " In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_In_Footer")
		public void verifySocialIconRenderedInFooterSectionOfDesktopView(String elementXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementPresent(elementXpath);
				Assert.assertTrue(value, "'Facbook', 'Twitter' and 'Linkdin' socials icons not present in global footer section");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in global footer section", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInGlobalHeaderInDesktopView", "verifySocialIconRenderedInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = false, description = " In Desktop View- Verify ALT tag value for 'Facebook', 'Twitter' and 'Linkedin' social icons in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifyALTTagNameOFSocialIconInFooterSectionOfDesktopView(String linkIconXpath, String expALTtagValue)
		{

			String actualALTTagValue = null;
			try{
				actualALTTagValue = v2SolutionsPageComponents.validateALTTagValue(linkIconXpath);
				Assert.assertEquals(actualALTTagValue.toLowerCase(), expALTtagValue.toLowerCase(), "ALT Tag value of social icons are mismatched");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", actualALTTagValue, expALTtagValue, "defect_verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView", "verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 7, enabled = false, description = " In Desktop View- Validate 'Facebook', 'Twitter' and 'Linkedin' social icons are clickable in footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifySocialIconInFooterSectionAreClickableInDesktopView(String linkIconXpath, String urlContainsTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.validateClickFunctionalityOfLinkIcon(linkIconXpath, urlContainsTextValue);
				Assert.assertTrue(value, "Social icons links not clickable");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconInGlobalHeaderAreClickableInDesktopView", "verifySocialIconInGlobalHeaderAreClickableInDesktopView");
			}
		}
		
		
//	-------------------------------------------
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 8, enabled = false, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "tablet_layout")
		public void verifyPageIsResizedToTabletViewLayout(String width, String height, String expected_no_of_columns)
		{
			if(BrowserInfoUtil.INSTANCE.isCloudAndroid() || BrowserInfoUtil.INSTANCE.isCloudIPad())
		    {
			flag = true;
		    }
		    else{
			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutionsPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionsPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = false, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection")
		public void verifyGlobalFooterSectionPresentInTabletView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = false, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection_CopyRightsMsg")
		public void verifyInGlobalFooterSectionCopyRightsMessageDisplayedInTabletView(String elementXpath, String expectedTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementContainsTextValue(elementXpath,expectedTextValue);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", elementXpath, "Is Copy rights text rendered in footer section", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 11, enabled = false, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "CopyRights_links")
		public void validateCopyRightsLinksClickableInTabletView(String linkXpath, String expectedURLValue)
		{

			String actualURLValue = null;
			try{
				actualURLValue = v2SolutionsPageComponents.validateClickFunctionalityOfLinks(linkXpath);
				Assert.assertEquals(actualURLValue, expectedURLValue, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", linkXpath, "Is Copy rights text rendered in footer section", actualURLValue, expectedURLValue, "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 12, enabled = false, description = " In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_In_Footer")
		public void verifySocialIconRenderedInFooterSectionOfTabletView(String elementXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementPresent(elementXpath);
				Assert.assertTrue(value, "'Facbook', 'Twitter' and 'Linkdin' socials icons not present in global footer section");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in global footer section", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInGlobalHeaderInDesktopView", "verifySocialIconRenderedInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 13, enabled = false, description = " In Desktop View- Verify ALT tag value for 'Facebook', 'Twitter' and 'Linkedin' social icons in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifyALTTagNameOFSocialIconInFooterSectionOfTabletView(String linkIconXpath, String expALTtagValue)
		{

			String actualALTTagValue = null;
			try{
				actualALTTagValue = v2SolutionsPageComponents.validateALTTagValue(linkIconXpath);
				Assert.assertEquals(actualALTTagValue.toLowerCase(), expALTtagValue.toLowerCase(), "ALT Tag value of social icons are mismatched");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", actualALTTagValue, expALTtagValue, "defect_verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView", "verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 14, enabled = false, description = " In Desktop View- Validate 'Facebook', 'Twitter' and 'Linkedin' social icons are clickable in footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifySocialIconInFooterSectionAreClickableInTabletView(String linkIconXpath, String urlContainsTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.validateClickFunctionalityOfLinkIcon(linkIconXpath, urlContainsTextValue);
				Assert.assertTrue(value, "Social icons links not clickable");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconInGlobalHeaderAreClickableInDesktopView", "verifySocialIconInGlobalHeaderAreClickableInDesktopView");
			}
		}
		
	//----------------------------------Mobile-----------	
		

		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 15, enabled = true, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "mobile_layout")
		public void verifyPageIsResizedToMobileViewLayout(String width, String height, String expected_no_of_columns)
		{
			if(BrowserInfoUtil.INSTANCE.isCloudAndroid() || BrowserInfoUtil.INSTANCE.isCloudIPhone() || BrowserInfoUtil.INSTANCE.isCloudIPad())
		    {
			flag = true;
		    }
		    else{
			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutionsPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionsPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 16, enabled = true, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection")
		public void verifyGlobalFooterSectionPresentInMobileView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyFooterSectionPresentOnPage(componentXpath);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 17, enabled = false, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "FooterSection_CopyRightsMsg")
		public void verifyInGlobalFooterSectionCopyRightsMessageDisplayedInMobileView(String elementXpath, String expectedTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementContainsTextValue(elementXpath,expectedTextValue);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", elementXpath, "Is Copy rights text rendered in footer section", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 18, enabled = true, description = "Verify Copy rights message text displayed in footer section in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "CopyRights_links")
		public void validateCopyRightsLinksClickableInMobileView(String linkXpath, String expectedURLValue)
		{

			String actualURLValue = null;
			try{
				actualURLValue = v2SolutionsPageComponents.validateClickFunctionalityOfLinks(linkXpath);
				Assert.assertEquals(actualURLValue, expectedURLValue, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", linkXpath, "Is Copy rights text rendered in footer section", actualURLValue, expectedURLValue, "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 19, enabled = false, description = " In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_In_Footer")
		public void verifySocialIconRenderedInFooterSectionOfMobileView(String elementXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.verifyElementPresent(elementXpath);
				Assert.assertTrue(value, "'Facbook', 'Twitter' and 'Linkdin' socials icons not present in global footer section");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in global footer section", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInGlobalHeaderInDesktopView", "verifySocialIconRenderedInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 20, enabled = false, description = " In Desktop View- Verify ALT tag value for 'Facebook', 'Twitter' and 'Linkedin' social icons in global footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifyALTTagNameOFSocialIconInFooterSectionOfMobileView(String linkIconXpath, String expALTtagValue)
		{

			String actualALTTagValue = null;
			try{
				actualALTTagValue = v2SolutionsPageComponents.validateALTTagValue(linkIconXpath);
				Assert.assertEquals(actualALTTagValue.toLowerCase(), expALTtagValue.toLowerCase(), "ALT Tag value of social icons are mismatched");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", actualALTTagValue, expALTtagValue, "defect_verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView", "verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 21, enabled = false, description = " In Desktop View- Validate 'Facebook', 'Twitter' and 'Linkedin' social icons are clickable in footer section")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "footer_Social_Icons_With_ExpData")
		public void verifySocialIconInFooterSectionAreClickableInMobileView(String linkIconXpath, String urlContainsTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutionsPageComponents.validateClickFunctionalityOfLinkIcon(linkIconXpath, urlContainsTextValue);
				Assert.assertTrue(value, "Social icons links not clickable");
			} catch (Throwable e) {
				v2SolutionsPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconInGlobalHeaderAreClickableInDesktopView", "verifySocialIconInGlobalHeaderAreClickableInDesktopView");
			}
		}
		
}