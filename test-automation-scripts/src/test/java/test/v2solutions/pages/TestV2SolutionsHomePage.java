package test.v2solutions.pages;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.v2solutions.common.BaseTest;
import com.v2solutions.pages.V2SolutionHomePage;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.dataprovider.TafExcelDataProvider;
import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;
import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;
import com.v2solutions.taf.listener.SuiteListener;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

@IExcelDataFiles(excelDataFiles = { "v2solutionshomepage=src/test/resources/testdata/automation_testdata.xls"})
public class TestV2SolutionsHomePage extends BaseTest{
	

		private Log log = LogUtil.getLog(TestV2SolutionsHomePage.class);
		private V2SolutionHomePage v2SolutionHomePage = null;
		private String v2SolutionsMainPageURL = null;
		WebPage webPage = null;
		boolean flag = false;
		
		@BeforeClass(alwaysRun = true)
		public void prepareBeforeClass() throws Exception {
			if(classInitDone == false )	{
				super.loadDriver(); 
				loadURL();
				webPage = new WebPage(testBedManager.getDriver(), testBedManager);
				v2SolutionHomePage = new V2SolutionHomePage(v2SolutionsMainPageURL, webPage);
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
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "desktop_layout")
		public void verifyPageIsResizedToDesktopViewLayout(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				testBedManager.getDriver().manage().window().maximize();
				v2SolutionHomePage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionHomePage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not desktop view layout");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = false, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "desktop_layout")
		public void validatePaginationInDesktopView(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePagination();
				Assert.assertTrue(value, "Pagination not working properly in Desktop view");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "GLobal Header section with xpath", "", "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInDesktopView", "verifyGlobalNavigationHeaderPresentInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters_Highlighted")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_Highlighted_On_MouseHover_In_Desktop_View(String buttonLinksXpath, String expHighlightedColor)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksHighlighted(buttonLinksXpath, expHighlightedColor);
				Assert.assertTrue(value, "Button links not highlighted on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_ClickableIn_Desktop_View(String buttonLinksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksClickable(buttonLinksXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Functionality")
		public void validateInOurSolutionSectionTabToggleFunctionalityArrangedInHorizontallyInDesktopView(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateInOurSolutionSectionTabToggleFunctionalityArrangedInHorizontally(tabSectionXpath, imageSectionXpath, contentSectionXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabSectionXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Working_Functionality")
		public void validateTabToggleFuctionalityInDesktopView(String tabsXpath, String contentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateTabToggleFunctionality(tabsXpath, contentXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 7, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validateThumbnailIconRenderHorizontallyInOurIndustryFocusSection(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateThumbnailIconRenderHorizontally(thumbnailIconsXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 8, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validatePaginationForCompaniesWorkedWithSection(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePaginationForCompaniesIconsInWorksWithSection();
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInDesktopView");
			}
		}
		
		
		
		
		
		
		
		
//	-------------------- Tabletttt viewwwwwww -----------------------
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = false, description = "Verify Page is Resized to 'Desktop view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "tablet_layout")
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
				v2SolutionHomePage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionHomePage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = false, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "desktop_layout")
		public void validatePaginationInTabletView(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePagination();
				Assert.assertTrue(value, "Pagination not working properly in Tablet view");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "GLobal Header section with xpath", "", "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInTabletView", "verifyGlobalNavigationHeaderPresentInTabletView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 11, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters_Highlighted")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_Highlighted_On_MouseHover_In_Tablet_View(String buttonLinksXpath, String expHighlightedColor)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksHighlighted(buttonLinksXpath, expHighlightedColor);
				Assert.assertTrue(value, "Button links not highlighted on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 12, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_ClickableIn_Tablet_View(String buttonLinksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksClickable(buttonLinksXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 13, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Functionality")
		public void validateInOurSolutionSectionTabToggleFunctionalityArrangedInHorizontallyInTabletView(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateInOurSolutionSectionTabToggleFunctionalityArrangmentInTablet(tabSectionXpath, imageSectionXpath, contentSectionXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabSectionXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 14, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Working_Functionality")
		public void validateTabToggleFuctionalityInTabletView(String tabsXpath, String contentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateTabToggleFunctionality(tabsXpath, contentXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 15, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validateThumbnailIconPositionInOurIndustryFocusSectionInTabletView(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateThumbnailIconRenderHorizontally(thumbnailIconsXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 16, enabled = false, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validatePaginationForCompaniesWorkedWithSectionInTabletView(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePaginationForCompaniesIconsInWorksWithSection();
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInTabletView");
			}
		}	
		
	//----------------------------------Mobile Viewwwww-----------	
		

		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 17, enabled = true, description = "Verify Page is Resized to 'Tablet view' Layout")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "mobile_layout")
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
				v2SolutionHomePage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionHomePage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not 3 column layout");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedTo3ColumnLayout");
			}
		}
		}
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 18, enabled = true, description = "Verify global footer section present in Desktop View")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "desktop_layout")
		public void validatePaginationInMobileView(String width, String height, String expected_no_of_columns)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePagination();
				Assert.assertTrue(value, "Pagination not working properly in Mobile view");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "GLobal Header section with xpath", "", "Is Global Header rendered", String.valueOf(value), String.valueOf(true), "defect_verifyGlobalNavigationHeaderPresentInMobileView", "verifyGlobalNavigationHeaderPresentInMobileView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 19, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters_Highlighted")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_Highlighted_On_MouseHover_In_Mobile_View(String buttonLinksXpath, String expHighlightedColor)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksHighlighted(buttonLinksXpath, expHighlightedColor);
				Assert.assertTrue(value, "Button links not highlighted on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 20, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Button_Links_In_Innovation_That_Matters")
		public void validate_Button_Links_In_Innovation_That_Matters_Are_ClickableIn_Mobile_View(String buttonLinksXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateButtonLinksClickable(buttonLinksXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", buttonLinksXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 21, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Functionality")
		public void validateInOurSolutionSectionTabToggleFunctionalityArrangedInHorizontallyInMobileView(String tabSectionXpath, String imageSectionXpath, String contentSectionXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateInOurSolutionSectionTabToggleFunctionalityArrangmentInMobile(tabSectionXpath, imageSectionXpath, contentSectionXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabSectionXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}
		
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 22, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "Tab_Toggle_Working_Functionality")
		public void validateTabToggleFuctionalityInMobileView(String tabsXpath, String contentXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateTabToggleFunctionality(tabsXpath, contentXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", tabsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}
		
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 23, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validateThumbnailIconPositionInOurIndustryFocusSectionInMobileView(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validateThumbnailIconRenderHorizontally(thumbnailIconsXpath);
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}
		
		
		@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 24, enabled = true, description = "Validate whether menu links expanded on mouse hover")
		@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2HomePage", dataKey = "ThumbNailIcon_In_IndustryFocus_Section")
		public void validatePaginationForCompaniesWorkedWithSectionInMobileView(String thumbnailIconsXpath)
		{

			boolean value = false;
			try{
				value = v2SolutionHomePage.validatePaginationForCompaniesIconsInWorksWithSection();
				Assert.assertTrue(value, "Menu links not expanded on mouse hover");
			} catch (Throwable e) {
				v2SolutionHomePage.logAndCreateADefect(e, "Menu link with xpath", thumbnailIconsXpath, "Menu links expanded on hover", String.valueOf(value), String.valueOf(true), "defect_verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView", "verifyOnMouseHoverMainNavigationMenuLinksExpandedInMobileView");
			}
		}	
		
		
}