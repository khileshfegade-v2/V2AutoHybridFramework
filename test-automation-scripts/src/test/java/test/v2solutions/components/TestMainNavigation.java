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
public class TestMainNavigation extends BaseTest{
	

		private Log log = LogUtil.getLog(TestMainNavigation.class);
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
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = true, description = "Verify main navigation section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "MainNav")
		public void verifyMainNavigationPresentInDesktopView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Main navigation not present in Desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Main navigation section with xpath", componentXpath, "Is Main Navigation rendered", String.valueOf(value), String.valueOf(true), "defect_verifyMainNavigationPresentInDesktopView", "verifyMainNavigationPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = true, description = "Verify main navigation section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Logo_In_Main_Nav")
		public void verifyLogoPresentInMainNavigationDesktopView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Logo not present on main navigation in Desktop view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Logo with xpath", componentXpath, "Is Logo present in main navigation", String.valueOf(value), String.valueOf(true), "defect_verifyLogoPresentInMainNavigationDesktopView", "verifyLogoPresentInMainNavigationDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Main_Menu_Links")
		public void validateMenuLinksAreClickable(String menuLinksXpath, String expandedSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMenuLinksClickable(menuLinksXpath, expandedSectionXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Menu link with xpath", menuLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Main_Menu_Links")
		public void verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView(String menuLinksXpath, String expandedSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMenuLinkExpanded(menuLinksXpath, expandedSectionXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Menu link with xpath", menuLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "SubMenu_Links_Highlighted")
		public void validateOnMouseHoverSubMenuLinksAreHighlighted(String menuLinksXpath, String subMenuLinksXpath, String expHighlightedColor)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateSubMenuLinksHighlightedOnMouseHover(menuLinksXpath, subMenuLinksXpath, expHighlightedColor);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Menu link with xpath", menuLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
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
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 8, enabled = true, description = "Verify main navigation section present in Tablet View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "MainNav")
		public void verifyMainNavigationPresentInTabletView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Main navigation not present in Tablet view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Main navigation section with xpath", componentXpath, "Is Main Navigation rendered", String.valueOf(value), String.valueOf(true), "defect_verifyMainNavigationPresentInTabletView", "verifyMainNavigationPresentInTabletView");
			}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = true, description = "Validate whether menu links are clickable in tablet view")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Main_Menu_Links")
		public void validateMenuLinksAreClickableInTabletView(String menuLinksXpath, String expandedSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateMenuLinksClickable(menuLinksXpath, expandedSectionXpath);
				Assert.assertTrue(value, "Menu links not clickable in tablet view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Menu link with xpath", menuLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_validateMenuLinksAreClickableInTabletView", "validateMenuLinksAreClickableInTabletView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = true, description = "Verify main navigation section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Logo_In_Main_Nav")
		public void verifyLogoPresentInMainNavigationTabletView(String componentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.checkComponentIsRendered(componentXpath);
				Assert.assertTrue(value, "Logo not present on main navigation in Tablet view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Logo with xpath", componentXpath, "Is Logo present in main navigation", String.valueOf(value), String.valueOf(true), "defect_verifyLogoPresentInMainNavigationTabletView", "verifyLogoPresentInMainNavigationTabletView");
			}
		}
		
		/**
		 * Verify page is Resized to Desktop layout
		 * @param width
		 * @param height
		 * @param expected_no_of_columns
		 */
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 11, enabled = true, description = "Verify Page is Resized to 'Mobile view' Layout")
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
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 12, enabled = true, description = "Verify hamburger button present in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "Hamburger_Button")
		public void verifyHamburgerButtonPresentInMobileView(String hamburgerXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.verifyElementPresent(hamburgerXpath);
				Assert.assertTrue(value, "Global navigation header is not present in mobile view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Hamburger button with xpath", hamburgerXpath, "Is Hamburger button present", String.valueOf(value), String.valueOf(true), "defect_verifyHamburgerButtonPresentInMobileView", "verifyHamburgerButtonPresentInMobileView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 13, enabled = true, description = "Verify hamburger button clickable in Mobile View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2MainPage", dataKey = "ClickOn_Hamburger_Button")
		public void validateHamburgerButtonClickableInMobileView(String hamburgerButtonXpath, String expandedSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutiosPageComponents.validateHamburgerButtonClickable(hamburgerButtonXpath, expandedSectionXpath);
				Assert.assertTrue(value, "Global navigation header is not present in mobile view");
			} catch (Throwable e) {
				v2SolutiosPageComponents.logAndCreateADefect(e, "Hamburger button with xpath :"+hamburgerButtonXpath+" and expanded menu links section xpath :", expandedSectionXpath, "Is Hamburger button clickable", String.valueOf(value), String.valueOf(true), "defect_validateHamburgerButtonClickableInMobileView", "validateHamburgerButtonClickableInMobileView");
			}
		}
	
}
