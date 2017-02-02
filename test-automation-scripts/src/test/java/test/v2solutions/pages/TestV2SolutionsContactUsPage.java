package test.v2solutions.pages;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.v2solutions.common.BaseTest;
import com.v2solutions.pages.V2SolutionContactUsPage;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.dataprovider.TafExcelDataProvider;
import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;
import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;
import com.v2solutions.taf.listener.SuiteListener;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

@IExcelDataFiles(excelDataFiles = { "v2solutionshomepage=src/test/resources/testdata/automation_testdata.xls"})
public class TestV2SolutionsContactUsPage extends BaseTest{


	private Log log = LogUtil.getLog(TestV2SolutionsContactUsPage.class);
	private V2SolutionContactUsPage v2SolutionContactUsPage = null;
	private String v2SolutionsContactusPageURL = null;
	WebPage webPage = null;
	boolean flag = false;

	@BeforeClass(alwaysRun = true)
	public void prepareBeforeClass() throws Exception {
		if(classInitDone == false )	{
			super.loadDriver(); 
			loadURL();
			webPage = new WebPage(testBedManager.getDriver(), testBedManager);
			v2SolutionContactUsPage = new V2SolutionContactUsPage(v2SolutionsContactusPageURL, webPage);
			webPage.waitForLoad(webPage.getDriver());
			classInitDone = true;
		}

	}

	private void loadURL() {
		v2SolutionsContactusPageURL = constructURL(SuiteListener.pageURLs.getProperty("V2SolutionsContactUs_Page"));
	}

	/**
	 * Verify page is Resized to Desktop layout
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression", "Smoke"}, priority = 1, enabled = true, description = "Verify Page is Resized to 'Desktop view' Layout")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "desktop_layout")
	public void verifyPageIsResizedToDesktopViewLayout(String width, String height, String expected_no_of_columns)
	{

		boolean value = false;
		try{
			testBedManager.getDriver().manage().window().maximize();
			v2SolutionContactUsPage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
			value = v2SolutionContactUsPage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
			Assert.assertTrue(value, "This is not desktop view layout");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName", "verifyPageIsResizedToDesktopViewLayout");
		}
	}

	/**
	 * Verify H2 header in Desktop view
	 * @param h2headerXpath
	 * @param expectedHeaderValue
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = true, description = "Verify H2 header in Desktop view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "H2Header")
	public void verifyH2HeaderTextInDesktopView(String h2headerXpath, String expectedHeaderValue)
	{

		String  actualHeaderValue = null;
		try{
			actualHeaderValue = v2SolutionContactUsPage.getElementTextValue(h2headerXpath);
			Assert.assertEquals(actualHeaderValue, expectedHeaderValue, "Header value mismatched in Desktop view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "H2 header with xpath", h2headerXpath, "Header text value", actualHeaderValue, expectedHeaderValue, "defect_verifyH2HeaderTextInDesktopView", "verifyH2HeaderTextInDesktopView");
		}
	}

	
	/**
	 * Verify Enquiry form details section not displayed in Desktop View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = true, description = "Verify Enquiry form details section not displayed in Desktop View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EnquiryFormSection")
	public void verifyEnquiryFormSectionDisplayedInDesktopView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Enquiry form section not present in Desktop View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Enquiry form details section with xpath", sectionXpath, "Enquiry form details section", String.valueOf(value), String.valueOf(true), "defect_verifyEnquiryFormSectionDisplayedInDesktopView", "verifyEnquiryFormSectionDisplayedInDesktopView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Desktop View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = true, description = "Verify default textbox value of 'First Name' in Desktop View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "FirstNametextBox_In_EnquiryForm")
	public void verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInDesktopView(String textboxValue, String expectedTextBoxDefaultValue)
	{

		String actualDefaultTextBoxValue = null;
		try{
			actualDefaultTextBoxValue = v2SolutionContactUsPage.getTextBoxDefaultValue(textboxValue);
			Assert.assertEquals(actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "Default textbox value of 'First Name' is mismatched in Desktop View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Textbox in enquiry with xpath", textboxValue, "deafult value of first name text box", actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "defect_verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInDesktopView", "verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInDesktopView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Desktop View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = true, description = "Verify default textbox value of 'First Name' in Desktop View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EmailErrorMessage")
	public void validateEmailErrorMessageInEnquiryDetailsFormInDesktopView(String textBoxXpath, String errorMsgXpath, String testData, String expectedErrorMessage)
	{

		String actualErrorMessage = null;
		try{
			actualErrorMessage = v2SolutionContactUsPage.getErrorMessageFromTextBox(textBoxXpath, errorMsgXpath, testData);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Email error message mismatched in Desktop view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Email Textbox in enquiry with xpath"+textBoxXpath+" and error mesage with xpath", errorMsgXpath, "error message", actualErrorMessage, expectedErrorMessage, "defect_validateEmailErrorMessageInEnquiryDetailsFormInDesktopView", "validateEmailErrorMessageInEnquiryDetailsFormInDesktopView");
		}
	}
	
	
	
	/**
	 * US and India contact location section not present in Desktop View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = true, description = "US and India contact location section not present in Desktop View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "ConctactLocationSection")
	public void verifyUSAndIndiaContactLocationDisplayedInDesktopView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Us and India contact location section not present in Desktop View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "US and India contact location section with xpath", sectionXpath, "Contact location", String.valueOf(value), String.valueOf(true), "defect_verifyUSAndIndiaContactLocationDisplayedInDesktopView", "verifyUSAndIndiaContactLocationDisplayedInDesktopView");
		}
	}

	
	/**
	 * Verify page footer component placed horizontally in Desktop View
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @param thirdComponentSectionXpath
	 * @param fourthComponentSectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 7, enabled = true, description = "Verify Map and Address section rendered parally in horizontal manner in Desktop view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "MapAndAddressSection")
	public void verifyMapAndAddressRepresentHorizontallyInDesktopView(String firstComponentSectionXpath, String secondComponentSectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyComponentPositionInDesktop(firstComponentSectionXpath, secondComponentSectionXpath);
			Assert.assertTrue(value, "Page footer component not placed horizontally in Desktop View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Map in first component xpath :"+firstComponentSectionXpath+" and second address component xpath :", secondComponentSectionXpath, "map and address sections", String.valueOf(value), String.valueOf(true), "defect_verifyMapAndAddressRepresentHorizontallyInDesktopView", "verifyMapAndAddressRepresentHorizontallyInDesktopView");
		}
	}
	
	
	/**
	 * In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered
	 * @param elementXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = {  "Desktop", "Regression", "Smoke"}, priority = 8, enabled = true, description = "In Desktop View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "socialIcons_In_ContactUsPage")
	public void verifySocialIconRenderedInDesktopView(String elementXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyElementPresent(elementXpath);
			Assert.assertTrue(value, "Contact number and contact id not matched");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in Desktop view", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInDesktopView", "verifySocialIconRenderedInDesktopView");
		}
	}

//--Tablet----------------------

	/**
	 * Verify Page is Resized to 'Tablet view' Layout
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression", "Smoke"}, priority = 9, enabled = true, description = "Verify Page is Resized to 'Tablet view' Layout")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "tablet_layout")
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
				v2SolutionContactUsPage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionContactUsPage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not Tablet View layout");
			} catch (Throwable e) {
				v2SolutionContactUsPage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedToTabletViewLayout", "verifyPageIsResizedToTabletViewLayout");
			}
		}
	}
	
	
	/**
	 * Verify H2 header in Tablet view
	 * @param h2headerXpath
	 * @param expectedHeaderValue
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = true, description = "Verify H2 header in Tablet view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "H2Header")
	public void verifyH2HeaderTextInTabletView(String h2headerXpath, String expectedHeaderValue)
	{

		String  actualHeaderValue = null;
		try{
			actualHeaderValue = v2SolutionContactUsPage.getElementTextValue(h2headerXpath);
			Assert.assertEquals(actualHeaderValue, expectedHeaderValue, "Header value mismatched in Tablet view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "H2 header with xpath", h2headerXpath, "Header text value", actualHeaderValue, expectedHeaderValue, "defect_verifyH2HeaderTextInTabletView", "verifyH2HeaderTextInTabletView");
		}
	}

	
	/**
	 * Verify Enquiry form details section not displayed in Tablet View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 11, enabled = true, description = "Verify Enquiry form details section not displayed in Tablet View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EnquiryFormSection")
	public void verifyEnquiryFormSectionDisplayedInTabletView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Enquiry form section not present in Tablet View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Enquiry form details section with xpath", sectionXpath, "Enquiry form details section", String.valueOf(value), String.valueOf(true), "defect_verifyEnquiryFormSectionDisplayedInTabletView", "verifyEnquiryFormSectionDisplayedInTabletView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Tablet View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 12, enabled = true, description = "Verify default textbox value of 'First Name' in Tablet View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "FirstNametextBox_In_EnquiryForm")
	public void verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInTabletView(String textboxValue, String expectedTextBoxDefaultValue)
	{

		String actualDefaultTextBoxValue = null;
		try{
			actualDefaultTextBoxValue = v2SolutionContactUsPage.getTextBoxDefaultValue(textboxValue);
			Assert.assertEquals(actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "Default textbox value of 'First Name' is mismatched in Tablet View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Textbox in enquiry with xpath", textboxValue, "deafult value of first name text box", actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "defect_verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInTabletView", "verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInTabletView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Tablet View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 13, enabled = true, description = "Verify default textbox value of 'First Name' in Tablet View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EmailErrorMessage")
	public void validateEmailErrorMessageInEnquiryDetailsFormInTabletView(String textBoxXpath, String errorMsgXpath, String testData, String expectedErrorMessage)
	{

		String actualErrorMessage = null;
		try{
			actualErrorMessage = v2SolutionContactUsPage.getErrorMessageFromTextBox(textBoxXpath, errorMsgXpath, testData);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Email error message mismatched in Tablet view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Email Textbox in enquiry with xpath"+textBoxXpath+" and error mesage with xpath", errorMsgXpath, "error message", actualErrorMessage, expectedErrorMessage, "defect_validateEmailErrorMessageInEnquiryDetailsFormInTabletView", "validateEmailErrorMessageInEnquiryDetailsFormInTabletView");
		}
	}
	
	
	
	/**
	 * US and India contact location section not present in Tablet View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 14, enabled = true, description = "US and India contact location section not present in Tablet View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "ConctactLocationSection")
	public void verifyUSAndIndiaContactLocationDisplayedInTabletView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Us and India contact location section not present in Tablet View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "US and India contact location section with xpath", sectionXpath, "Contact location", String.valueOf(value), String.valueOf(true), "defect_verifyUSAndIndiaContactLocationDisplayedInTabletView", "verifyUSAndIndiaContactLocationDisplayedInTabletView");
		}
	}

	
	/**
	 * Verify page footer component placed horizontally in Tablet View
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @param thirdComponentSectionXpath
	 * @param fourthComponentSectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 15, enabled = true, description = "Verify Map and Address section rendered parally in horizontal manner in Tablet view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "MapAndAddressSection")
	public void verifyMapAndAddressRepresentHorizontallyInTabletView(String firstComponentSectionXpath, String secondComponentSectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyComponentPositionInTablet(firstComponentSectionXpath, secondComponentSectionXpath);
			Assert.assertTrue(value, "Page footer component not placed horizontally in Tablet View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Map in first component xpath :"+firstComponentSectionXpath+" and second address component xpath :", secondComponentSectionXpath, "map and address sections", String.valueOf(value), String.valueOf(true), "defect_verifyMapAndAddressRepresentHorizontallyInTabletView", "verifyMapAndAddressRepresentHorizontallyInTabletView");
		}
	}
	
	
	/**
	 * In Tablet View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered
	 * @param elementXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = {  "Tablet", "Regression", "Smoke"}, priority = 16, enabled = true, description = "In Tablet View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "socialIcons_In_ContactUsPage")
	public void verifySocialIconRenderedInTabletView(String elementXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyElementPresent(elementXpath);
			Assert.assertTrue(value, "Contact number and contact id not matched");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in Tablet view", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInTabletView", "verifySocialIconRenderedInTabletView");
		}
	}
//-------------------------

	/**
	 * Verify Page is Resized to 'Mobile view' Layout
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression", "Smoke"}, priority = 17, enabled = true, description = "Verify Page is Resized to 'Mobile view' Layout")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "mobile_layout")
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
				v2SolutionContactUsPage.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
				value = v2SolutionContactUsPage.getColumnCountOfColLayout(Integer.valueOf(width),Integer.valueOf(height));
				Assert.assertTrue(value, "This is not Mobile view layout");
			} catch (Throwable e) {
				v2SolutionContactUsPage.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedToMobileViewLayout", "verifyPageIsResizedToMobileViewLayout");
			}
		}
	}

	/**
	 * Verify H2 header in Mobile view
	 * @param h2headerXpath
	 * @param expectedHeaderValue
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 18, enabled = true, description = "Verify H2 header in Mobile view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "H2Header")
	public void verifyH2HeaderTextInMobileView(String h2headerXpath, String expectedHeaderValue)
	{

		String  actualHeaderValue = null;
		try{
			actualHeaderValue = v2SolutionContactUsPage.getElementTextValue(h2headerXpath);
			Assert.assertEquals(actualHeaderValue, expectedHeaderValue, "Header value mismatched in Mobile view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "H2 header with xpath", h2headerXpath, "Header text value", actualHeaderValue, expectedHeaderValue, "defect_verifyH2HeaderTextInMobileView", "verifyH2HeaderTextInMobileView");
		}
	}

	
	/**
	 * Verify Enquiry form details section not displayed in Mobile View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 19, enabled = true, description = "Verify Enquiry form details section not displayed in Mobile View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EnquiryFormSection")
	public void verifyEnquiryFormSectionDisplayedInMobileView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Enquiry form section not present in Mobile View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Enquiry form details section with xpath", sectionXpath, "Enquiry form details section", String.valueOf(value), String.valueOf(true), "defect_verifyEnquiryFormSectionDisplayedInMobileView", "verifyEnquiryFormSectionDisplayedInMobileView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Mobile View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 20, enabled = true, description = "Verify default textbox value of 'First Name' in Mobile View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "FirstNametextBox_In_EnquiryForm")
	public void verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInMobileView(String textboxValue, String expectedTextBoxDefaultValue)
	{

		String actualDefaultTextBoxValue = null;
		try{
			actualDefaultTextBoxValue = v2SolutionContactUsPage.getTextBoxDefaultValue(textboxValue);
			Assert.assertEquals(actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "Default textbox value of 'First Name' is mismatched in Mobile View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Textbox in enquiry with xpath", textboxValue, "deafult value of first name text box", actualDefaultTextBoxValue, expectedTextBoxDefaultValue, "defect_verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInMobileView", "verifyFirstNameTextboxInEnquiryFormRenderedWithDefaultValueInMobileView");
		}
	}
	
	
	
	
	/**
	 * Verify default textbox value of 'First Name' in Mobile View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 21, enabled = true, description = "Verify default textbox value of 'First Name' in Mobile View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "EmailErrorMessage")
	public void validateEmailErrorMessageInEnquiryDetailsFormInMobileView(String textBoxXpath, String errorMsgXpath, String testData, String expectedErrorMessage)
	{

		String actualErrorMessage = null;
		try{
			actualErrorMessage = v2SolutionContactUsPage.getErrorMessageFromTextBox(textBoxXpath, errorMsgXpath, testData);
			Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Email error message mismatched in Mobile view");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Email Textbox in enquiry with xpath"+textBoxXpath+" and error mesage with xpath", errorMsgXpath, "error message", actualErrorMessage, expectedErrorMessage, "defect_validateEmailErrorMessageInEnquiryDetailsFormInMobileView", "validateEmailErrorMessageInEnquiryDetailsFormInMobileView");
		}
	}
	
	
	
	/**
	 * US and India contact location section not present in Mobile View
	 * @param sectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 22, enabled = true, description = "US and India contact location section not present in Mobile View")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "ConctactLocationSection")
	public void verifyUSAndIndiaContactLocationDisplayedInMobileView(String sectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifySectionPresentOnPage(sectionXpath);
			Assert.assertTrue(value, "Us and India contact location section not present in Mobile View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "US and India contact location section with xpath", sectionXpath, "Contact location", String.valueOf(value), String.valueOf(true), "defect_verifyUSAndIndiaContactLocationDisplayedInMobileView", "verifyUSAndIndiaContactLocationDisplayedInMobileView");
		}
	}

	
	/**
	 * Verify page footer component placed horizontally in Mobile View
	 * @param firstComponentSectionXpath
	 * @param secondComponentSectionXpath
	 * @param thirdComponentSectionXpath
	 * @param fourthComponentSectionXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 23, enabled = true, description = "Verify Map and Address section rendered parally in horizontal manner in Mobile view")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "MapAndAddressSection")
	public void verifyMapAndAddressRepresentHorizontallyInMobileView(String firstComponentSectionXpath, String secondComponentSectionXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyComponentPositionInMobile(firstComponentSectionXpath, secondComponentSectionXpath);
			Assert.assertTrue(value, "Page footer component not placed horizontally in Mobile View");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Map in first component xpath :"+firstComponentSectionXpath+" and second address component xpath :", secondComponentSectionXpath, "map and address sections", String.valueOf(value), String.valueOf(true), "defect_verifyMapAndAddressRepresentHorizontallyInMobileView", "verifyMapAndAddressRepresentHorizontallyInMobileView");
		}
	}
	
	
	/**
	 * In Mobile View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered
	 * @param elementXpath
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = {  "Mobile", "Regression", "Smoke"}, priority = 24, enabled = true, description = "In Mobile View- Verify 'Facebook', 'Twitter' and 'Linkedin' social icons rendered")
	@ITafExcelDataProviderInputs(excelFile = "v2solutionshomepage", excelsheet = "V2Pages", dataKey = "socialIcons_In_ContactUsPage")
	public void verifySocialIconRenderedInMobileView(String elementXpath)
	{

		boolean value = false;
		try{
			value = v2SolutionContactUsPage.verifyElementPresent(elementXpath);
			Assert.assertTrue(value, "Contact number and contact id not matched");
		} catch (Throwable e) {
			v2SolutionContactUsPage.logAndCreateADefect(e, "Social icons with xpath", elementXpath, "'Facbook', 'Twitter' and 'Linkdin' socials icons present in Mobile view", String.valueOf(value), String.valueOf(true), "defect_verifySocialIconRenderedInMobileView", "verifySocialIconRenderedInMobileView");
		}
	}

		
}