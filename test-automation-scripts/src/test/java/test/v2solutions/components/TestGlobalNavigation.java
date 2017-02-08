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
public class TestGlobalNavigation extends BaseTest{
	

		private Log log = LogUtil.getLog(TestGlobalNavigation.class);
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
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 1, enabled = false, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "desktop_layout")
		public void verifyPageIsResizedToDesktopViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not Desktop layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 2, enabled = false, description = "Verify global navigation header section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "GlobalNav")
		public void verifyGlobalNavigationHeaderPresentInDesktopView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Global navigation header is not present ");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 3, enabled = false, description = " In Desktop View- Verify Contact number and contact id rendered in global navigation header")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "ContactNoAndID_In_GlobalNav")
		public void verifyContactNoAndIDPresentInGlobalHeaderInDesktopView(String elementXpath, String expectedValue)
		{

			String actualValue = null;
			try{
				actualValue = v2SolutiosPageComponents.getElementTextValue(elementXpath);
				Assert.assertEquals(actualValue, expectedValue, "Contact number and contact id not matched");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Contact Number and Contact Id with xpath", elementXpath, "As per expected contact number and contact id rendered in Global Header", actualValue, expectedValue, "defect_verifyContactNoAndIDPresentInGlobalHeaderInDesktopView", "verifyContactNoAndIDPresentInGlobalHeaderInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 4, enabled = false, description = " In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered in global navigation header")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_In_GlobalHeader")
		public void verifySocialIconRenderedInGlobalHeaderInDesktopView(String elementXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyElementPresent(elementXpath);
				Assert.assertTrue(value, "Contact number and contact id not matched");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in global header", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInGlobalHeaderInDesktopView", "verifySocialIconRenderedInGlobalHeaderInDesktopView");
			}
		}
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 5, enabled = false, description = " In Desktop View- Validate 'Facebook', 'Twitter' and 'Linkedin' social icons are clickable")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_with_exp_Content")
		public void verifySocialIconInGlobalHeaderAreClickableInDesktopView(String linkIconXpath, String urlContainsTextValue)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateClickFunctionalityOfLinkIcon(linkIconXpath, urlContainsTextValue);
				Assert.assertTrue(value, "Social icons links not clickable");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconInGlobalHeaderAreClickableInDesktopView", "verifySocialIconInGlobalHeaderAreClickableInDesktopView");
			}
		}
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 6, enabled = false, description = " In Desktop View- Verify ALT tag value for 'Facebook', 'Twitter' and 'Linkedin' social icons in global header")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "socialIcons_with_exp_Content")
		public void verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView(String linkIconXpath, String expALTtagValue)
		{

			String actualALTTagValue = null;
			try{
				actualALTTagValue = v2SolutiosPageComponents.validateALTTagValue(linkIconXpath);
				Assert.assertEquals(actualALTTagValue.toLowerCase(), expALTtagValue.toLowerCase(), "ALT Tag value of social icons are mismatched");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Social icons with xpath", linkIconXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons clickable", actualALTTagValue, expALTtagValue, "defect_verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView", "verifyALTTagNameOFSocialIconInGlobalHeaderInDesktopView");
			}
		}
		
		/**
		 * Verify page is Resized to Desktop layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 7, enabled = true, description = "Verify Page is Resized to 'Tablet view' Layout")
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
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not tablet layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defect_verifyPageIsResizedToTabletViewLayout", "verifyPageIsResizedToTabletViewLayout");
			}
		}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 8, enabled = false, description = "Verify global navigation header section present in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "GlobalNav")
		public void verifyGlobalNavigationHeaderPresentInTabletView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Global navigation header is not present in tablet view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInTabletView", "verifyGlobalNavigationHeaderPresentInTabletView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = false, description = " In Tablet View- Verify Contact number and contact id rendered in global navigation header")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "ContactNoAndID_In_GlobalNav")
		public void verifyContactNoAndIDPresentInGlobalHeaderIntabletView(String elementXpath, String expectedValue)
		{

			String actualValue = null;
			try{
				actualValue = v2SolutiosPageComponents.getElementTextValue(elementXpath);
				Assert.assertEquals(actualValue, expectedValue, "Contact number and contact id not matched in tablet view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Contact Number and Contact Id with xpath", elementXpath, "As per expected contact number and contact id rendered in Global Header", actualValue, expectedValue, "defect_verifyContactNoAndIDPresentInGlobalHeaderIntabletView", "verifyContactNoAndIDPresentInGlobalHeaderIntabletView");
			}
		}
		
		/**
		 * Verify page is Resized to Desktop layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 10, enabled = false, description = "Verify Page is Resized to 'Mobile view' Layout")
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
				v2SolutiosPageComponents.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutiosPageComponents.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not mobile layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defect_verifyPageIsResizedToMobileViewLayout", "verifyPageIsResizedToMobileViewLayout");
			}
		}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 11, enabled = false, description = "Verify global navigation header section present in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "GlobalNav")
		public void verifyGlobalNavigationHeaderPresentInMobileView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertFalse(value, "Global navigation header is not present in mobile view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "GLobal Header section with xpath", componentXpath, "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInMobileView", "verifyGlobalNavigationHeaderPresentInMobileView");
			}
		}
	
}
