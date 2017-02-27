package test.v2solutions.cisco;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.v2solutions.cisco.CISCO_TC;
import com.v2solutions.common.BaseTest;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.dataprovider.TafExcelDataProvider;
import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;
import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.listener.SuiteListener;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

/**
* # TC - Validate test cases of NYDJ web site
* Please write user story name FSCOM-???? Description here.
* 
* @author v2solutions pvt ltd
* Generation Date: 02/06/2017
* 
*/

@Test(groups= "CISCO_TC")
@IExcelDataFiles(excelDataFiles = { "BaseLayoutDataFile=src/test/resources/testdata/automation_testdata.xls"})
public class TestCISCO_TC extends BaseTest 
{
	private static Log log = LogUtil.getLog(CISCO_TC.class);
	private String	          homePageUrl = null;
	private CISCO_TC cisco_TC = null;
	boolean flag = false;
	@BeforeClass(alwaysRun = true)
	public void prepareBeforeClass() throws Exception
	{
		if(classInitDone == false )
		{
			super.loadDriver(); 
			loadUrl();
			WebPage webPage = new WebPage(testBedManager.getDriver(), testBedManager);
			cisco_TC = new CISCO_TC(homePageUrl, webPage);
			webPage.waitForLoad(webPage.getDriver());
			classInitDone = true;
		}
	}

	private void loadUrl() 
	{
		homePageUrl = constructURL(SuiteListener.pageURLs.getProperty("CISCO_TCPage"));
	}



	/**
	 * Verify page is resized according to Desktop view
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 1, enabled = true, description = "Verify page is resized according to Desktop view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "desktop_layout")
	public void verifyPageIsResizedAccordingToDesktopView(String width, String height, String expected_no_of_columns)
	{
		boolean value = false;
		try{
			testBedManager.getDriver().manage().window().maximize();
			cisco_TC.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
			value = cisco_TC.verifyPageIsResizedAccordingToDesktopView(Integer.valueOf(width),Integer.valueOf(height));
			Assert.assertTrue(value, "This is not desktop view layout");
		} catch (Throwable e) {
			cisco_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToDesktopView", "verifyPageIsResizedToDesktopViewLayout");
		}
	}

	/**
	 * In Desktop view - Verify user can login with valid credentials.
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param loggedUserNameXpath
	 * @throws PageException 
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 2, enabled = true, description = "In Desktop view - Verify user can login with valid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithValidCredentials")
	public void inDesktopViewVerifyUserCanLoginWithValidCredentials(String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath, String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inDesktopViewVerifyUserCanLoginWithValidCredentials(loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, loggedUserNameXpath);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find logged user name with xpath ", loggedUserNameXpath, "User able to log in successfully with valid credentials", String.valueOf(returnValue), String.valueOf(true), "defectName_inDesktopViewVerifyUserCanLoginWithValidCredentials", "inDesktopViewVerifyUserCanLoginWithValidCredentials");
		}finally{
			returnValue = cisco_TC.inDesktopViewVerifyUserCanLoggoutSuccessfully(logoutLinkXpath, logoutSuccessMsgXpath);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can failed to logout.");
		}
	}


	/**
	 *  In Desktop view - Verify user cannot login with invalid credentials.
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param errorMessageXpath
	 * @param expectedErrorMessage
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 3, enabled = true, description = "In Desktop view - Verify user cannot login with invalid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithInValidCredentials")
	public void inDesktopViewVerifyUserCannotLoginWithInvalidCredentials(String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMessageXpath, String expectedErrorMessage)
	{
		String actualErrorMsgValue = null;
		try
		{
			actualErrorMsgValue = cisco_TC.inDesktopViewVerifyUserCannotLoginWithInvalidCredentials(loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, errorMessageXpath, expectedErrorMessage);
			Assert.assertEquals(actualErrorMsgValue, expectedErrorMessage, "In Desktop view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find error message with xpath ", expectedErrorMessage, "Error message", actualErrorMsgValue, expectedErrorMessage, "defectName_inDesktopViewVerifyUserCannotLoginWithInvalidCredentials", "inDesktopViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}


	/**
	 * In Desktop view - Verify Search field default text.
	 * @param searchIconXpath
	 * @param searchInputFieldXpath
	 * @param expectedDefaultMsg
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 4, enabled = true, description = "In Desktop view - Verify Search field default text")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "DefaultTextValueInSearchField")
	public void inDesktopViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath, String expectedDefaultMsg)
	{
		String actualDefaultMsg = null;
		try
		{
			actualDefaultMsg = cisco_TC.inDesktopViewVerifySearchFieldDefaultText(searchIconXpath, searchInputFieldXpath);
			Assert.assertEquals(actualDefaultMsg, expectedDefaultMsg, "In Desktop view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchIconXpath+", search input field xpath :",searchInputFieldXpath, "Default text in search input field", actualDefaultMsg, expectedDefaultMsg, "defectName_inDesktopViewVerifySearchFieldDefaultText", "inDesktopViewVerifySearchFieldDefaultText");
		}
	}


	/**
	 * In Desktop view - Verify user can perform search.
	 * @param searchInputFieldXpath
	 * @param inputText
	 * @param searchFinderXpath
	 * @param searchResultSetXpath
	 * @param expectedText
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 5, enabled = true, description = "In Desktop view - Verify user can perform search")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "PerformSearch")
	public void inDesktopViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath, String expectedText)
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inDesktopViewVerifyUserCanPerformSearch(searchInputFieldXpath, inputText, searchFinderXpath, searchResultSetXpath, expectedText);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchFinderXpath+", search input field xpath :",searchInputFieldXpath, "Perfomr search", String.valueOf(returnValue), String.valueOf(true), "defectName_inDesktopViewVerifyUserCanPerformSearch", "inDesktopViewVerifyUserCanPerformSearch");
		}
	}

	
	/**
	 * Verify page is resized according to Tablet view.
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 6, enabled = true, description = "Verify page is resized according to Tablet view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "tablet_layout")
	public void verifyPageIsResizedAccordingToTabletView(String width, String height, String expected_no_of_columns)
	{
		if(BrowserInfoUtil.INSTANCE.isCloudAndroid() || BrowserInfoUtil.INSTANCE.isCloudIPad())
	    {
		flag = true;
	    }
	    else{
		boolean value = false;
		try{
			testBedManager.getDriver().manage().window().maximize();
			cisco_TC.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
			value = cisco_TC.verifyPageIsResizedAccordingToTabletView(Integer.valueOf(width),Integer.valueOf(height));
			Assert.assertTrue(value, "This is not desktop view layout");
		} catch (Throwable e) {
			cisco_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToTabletView", "verifyPageIsResizedAccordingToTabletView");
		}
	}
	}

	/**
	 * In Tablet view - Verify user can login with valid credentials
	 * @param hambergerButtonXpath
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param loggedUserNameXpath
	 * @throws PageException 
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 7, enabled = true, description = "In Tablet view - Verify user can login with valid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithValidCredentialsInMobile")
	public void inTabletViewVerifyUserCanLoginWithValidCredentials(String hambergerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath, String hamburgerButtonXpath, String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inTabletViewVerifyUserCanLoginWithValidCredentials(hambergerButtonXpath, loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, loggedUserNameXpath);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find logged user name with xpath ", loggedUserNameXpath, "User able to log in successfully with valid credentials", String.valueOf(returnValue), String.valueOf(true), "defectName_inTabletViewVerifyUserCanLoginWithValidCredentials", "inTabletViewVerifyUserCanLoginWithValidCredentials");
		}finally{
			returnValue = cisco_TC.inTabletViewVerifyUserCanLoggoutSuccessfully(hamburgerButtonXpath,logoutLinkXpath, logoutSuccessMsgXpath);
			Assert.assertTrue(returnValue,"In Tablet view - Verify user can failed to logout.");
		}
	}
	
	/**
	 * In Tablet view - Verify user cannot login with invalid credentials.
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param errorMessageXpath
	 * @param expectedErrorMessage
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 8, enabled = true, description = "In Tablet view - Verify user cannot login with invalid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithInValidCredentialsMobileAndTablet")
	public void inTabletViewVerifyUserCannotLoginWithInvalidCredentials(String hamburgerXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMessageXpath, String expectedErrorMessage)
	{
		String actualErrorMsgValue = null;
		try
		{
			actualErrorMsgValue = cisco_TC.inTabletViewVerifyUserCannotLoginWithInvalidCredentials(hamburgerXpath, loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, errorMessageXpath, expectedErrorMessage);
			Assert.assertEquals(actualErrorMsgValue, expectedErrorMessage, "In Tablet view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find error message with xpath ", expectedErrorMessage, "Error message", actualErrorMsgValue, expectedErrorMessage, "defectName_inTabletViewVerifyUserCannotLoginWithInvalidCredentials", "inTabletViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}
	
	/**
	 * In Tablet view - Verify Search field default text.
	 * @param searchIconXpath
	 * @param searchInputFieldXpath
	 * @param expectedDefaultMsg
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 9, enabled = true, description = "In Tablet view - Verify Search field default text")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "DefaultTextInSearchFieldTabletAndMobile")
	public void inTabletViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath, String expectedDefaultMsg)
	{
		String actualDefaultMsg = null;
		try
		{
			actualDefaultMsg = cisco_TC.inTabletViewVerifySearchFieldDefaultText(searchIconXpath, searchInputFieldXpath);
			Assert.assertEquals(actualDefaultMsg, expectedDefaultMsg, "In Desktop view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchIconXpath+", search input field xpath :",searchInputFieldXpath, "Default text in search input field", actualDefaultMsg, expectedDefaultMsg, "defectName_inTabletViewVerifySearchFieldDefaultText", "inTabletViewVerifySearchFieldDefaultText");
		}
	}
	
	
	/**
	 * In Tablet view - Verify user can perform search.
	 * @param searchInputFieldXpath
	 * @param inputText
	 * @param searchFinderXpath
	 * @param searchResultSetXpath
	 * @param expectedText
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Tablet", "Regression"}, priority = 10, enabled = true, description = "In Tablet view - Verify user can perform search")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "PerformSearchInTabletAndMobile")
	public void inTabletViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath, String expectedText)
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inTabletViewVerifyUserCanPerformSearch(searchInputFieldXpath, inputText, searchFinderXpath, searchResultSetXpath, expectedText);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchFinderXpath+", search input field xpath :",searchInputFieldXpath, "Perfomr search", String.valueOf(returnValue), String.valueOf(true), "defectName_inTabletViewVerifyUserCanPerformSearch", "inTabletViewVerifyUserCanPerformSearch");
		}
	}
	
	/**
	 * Verify page is resized according to Mobile view.
	 * @param width
	 * @param height
	 * @param expected_no_of_columns
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 11, enabled = true, description = "Verify page is resized according to Mobile view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "mobile_layout")
	public void verifyPageIsResizedAccordingToMobileView(String width, String height, String expected_no_of_columns)
	{
		
		if(BrowserInfoUtil.INSTANCE.isCloudAndroid() || BrowserInfoUtil.INSTANCE.isCloudIPhone() || BrowserInfoUtil.INSTANCE.isCloudIPad())
	    {
		flag = true;
	    }
	    else{
		boolean value = false;
		try{
			testBedManager.getDriver().manage().window().maximize();
			cisco_TC.columnLayout(Integer.valueOf(width), Integer.valueOf(height));
			value = cisco_TC.verifyPageIsResizedAccordingToMobileView(Integer.valueOf(width),Integer.valueOf(height));
			Assert.assertTrue(value, "This is not desktop view layout");
		} catch (Throwable e) {
			cisco_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(value), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToMobileView", "verifyPageIsResizedAccordingToMobileView");
		}
	}
	}
	
	/**
	 * In Mobile view - Verify user can login with valid credentials.
	 * @param hambergerButtonXpath
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param loggedUserNameXpath
	 * @throws PageException 
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 12, enabled = true, description = "In Mobile view - Verify user can login with valid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithValidCredentialsInMobile")
	public void inMobileViewVerifyUserCanLoginWithValidCredentials(String hambergerButtonXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String loggedUserNameXpath, String hamburgerButtonXpath, String logoutLinkXpath, String logoutSuccessMsgXpath) throws PageException
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inMobileViewVerifyUserCanLoginWithValidCredentials(hambergerButtonXpath, loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, loggedUserNameXpath);
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find logged user name with xpath ", loggedUserNameXpath, "User able to log in successfully with valid credentials", String.valueOf(returnValue), String.valueOf(true), "defectName_inMobileViewVerifyUserCanLoginWithValidCredentials", "inMobileViewVerifyUserCanLoginWithValidCredentials");
		}finally{
			returnValue = cisco_TC.inMobileViewVerifyUserCanLoggoutSuccessfully(hamburgerButtonXpath,logoutLinkXpath, logoutSuccessMsgXpath);
			Assert.assertTrue(returnValue,"In Tablet view - Verify user can failed to logout.");
		}
	}
	
	
	/**
	 * In Mobile view - Verify user cannot login with invalid credentials.
	 * @param loginButtonXpath
	 * @param emailTextFieldXpath
	 * @param ID
	 * @param pwdTextFieldXpath
	 * @param pwd
	 * @param loginButtonXPath
	 * @param errorMessageXpath
	 * @param expectedErrorMessage
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 13, enabled = true, description = "In Mobile view - Verify user cannot login with invalid credentials")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "LoginWithInValidCredentialsMobileAndTablet")
	public void inMobileViewVerifyUserCannotLoginWithInvalidCredentials(String hamburgerXpath, String loginButtonXpath, String emailTextFieldXpath, String ID, String pwdTextFieldXpath, String pwd, String loginButtonXPath, String errorMessageXpath, String expectedErrorMessage)
	{
		String actualErrorMsgValue = null;
		try
		{
			actualErrorMsgValue = cisco_TC.inMobileViewVerifyUserCannotLoginWithInvalidCredentials(hamburgerXpath, loginButtonXpath, emailTextFieldXpath, ID, pwdTextFieldXpath, pwd, loginButtonXPath, errorMessageXpath, expectedErrorMessage);
			Assert.assertEquals(actualErrorMsgValue, expectedErrorMessage, "In Tablet view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Login button having xpath value : "+loginButtonXpath+" and submit button xpath :"+loginButtonXPath+", and find error message with xpath ", expectedErrorMessage, "Error message", actualErrorMsgValue, expectedErrorMessage, "defectName_inMobileViewVerifyUserCannotLoginWithInvalidCredentials", "inMobileViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}

	/**
	 * In Mobile view - Verify Search field default text.
	 * @param searchIconXpath
	 * @param searchInputFieldXpath
	 * @param expectedDefaultMsg
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 14, enabled = true, description = "In Mobile view - Verify Search field default text")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "DefaultTextInSearchFieldTabletAndMobile")
	public void inMobileViewVerifySearchFieldDefaultText(String searchIconXpath, String searchInputFieldXpath, String expectedDefaultMsg)
	{
		String actualDefaultMsg = null;
		try
		{
			actualDefaultMsg = cisco_TC.inMobileViewVerifySearchFieldDefaultText(searchIconXpath, searchInputFieldXpath);
			Assert.assertEquals(actualDefaultMsg, expectedDefaultMsg, "In Mobile view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchIconXpath+", search input field xpath :",searchInputFieldXpath, "Default text in search input field", actualDefaultMsg, expectedDefaultMsg, "defectName_inMobileViewVerifySearchFieldDefaultText", "inMobileViewVerifySearchFieldDefaultText");
		}
	}


	/**
	 * In Mobile view - Verify user can perform search.
	 * @param searchInputFieldXpath
	 * @param inputText
	 * @param searchFinderXpath
	 * @param searchResultSetXpath
	 * @param expectedText
	 */
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Mobile", "Regression"}, priority = 15, enabled = true, description = "In Mobile view - Verify user can perform search")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "CISCO_Site", dataKey = "PerformSearchInTabletAndMobile")
	public void inMobileViewVerifyUserCanPerformSearch(String searchInputFieldXpath, String inputText, String searchFinderXpath, String searchResultSetXpath, String expectedText)
	{
		boolean returnValue = false;
		try
		{
			returnValue = cisco_TC.inMobileViewVerifyUserCanPerformSearch(searchInputFieldXpath, inputText, searchFinderXpath, searchResultSetXpath);
			Assert.assertTrue(returnValue,"In Mobile view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			cisco_TC.logAndCreateADefect(e, "Search icon with xpath :"+searchFinderXpath+", search input field xpath :",searchInputFieldXpath, "Perfomr search", String.valueOf(returnValue), String.valueOf(true), "defectName_inMobileViewVerifyUserCanPerformSearch", "inMobileViewVerifyUserCanPerformSearch");
		}
	}

}
