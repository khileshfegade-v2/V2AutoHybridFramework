package test.v2solutions.nydj;

import java.awt.AWTException;
import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.v2solutions.common.BaseTest;
import com.v2solutions.taf.listener.SuiteListener;
import com.v2solutions.taf.core.WebPage;
import com.v2solutions.taf.dataprovider.TafExcelDataProvider;
import com.v2solutions.taf.dataprovider.annotations.IExcelDataFiles;
import com.v2solutions.taf.dataprovider.annotations.ITafExcelDataProviderInputs;
import com.v2solutions.taf.exception.PageException;
import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;
import com.v2solutions.nydj.NYDJ_TC;

/*
* # TC - Validate test cases of NYDJ web site
* Please write user story name FSCOM-???? Description here.
* 
* @author v2solutions pvt ltd
* Generation Date: 02/06/2017
* 
*/

@Test(groups= "NYDJ_TC")
@IExcelDataFiles(excelDataFiles = { "BaseLayoutDataFile=src/test/resources/testdata/automation_testdata.xls"})
public class TestNYDJ_TC extends BaseTest 
{
	private static Log log = LogUtil.getLog(NYDJ_TC.class);
	private String	          homePageUrl = null;
	private NYDJ_TC nYDJ_TC = null;

	@BeforeClass(alwaysRun = true)
	public void prepareBeforeClass() throws Exception
	{
		if(classInitDone == false )
		{
			super.loadDriver(); 
			loadUrl();
			WebPage webPage = new WebPage(testBedManager.getDriver(), testBedManager);
			nYDJ_TC = new NYDJ_TC(homePageUrl, webPage);
			webPage.waitForLoad(webPage.getDriver());
			classInitDone = true;
		}
	}

	private void loadUrl() 
	{
		homePageUrl = constructURL(SuiteListener.pageURLs.getProperty("NYDJ_TCPage"));
	}



	/*
	* Please write method description here.
	* Verify page is resized according to Desktop view.
	*/
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 1, enabled = true, description = "Verify page is resized according to Desktop view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "NYDJ_Site", dataKey = "desktop_layout")
	public void verifyPageIsResizedAccordingToDesktopView(String width, String height, String expected_no_of_columns)
	{
		boolean returnValue = false;
		try
		{
		 returnValue = nYDJ_TC.verifyPageIsResizedAccordingToDesktopView();
			Assert.assertTrue(returnValue,"Verify page is resized according to Desktop view failed.");
		}
		catch (Throwable e)
		{
			nYDJ_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(returnValue), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToDesktopView", "verifyPageIsResizedAccordingToDesktopView");
		}
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user can login with valid credentials.
	*/
	@Test( priority = 2, enabled = true, description = "In Desktop view - Verify user can login with valid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 2, enabled = true, description = "In Desktop view - Verify user can login with valid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inDesktopViewVerifyUserCanLoginWithValidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inDesktopViewVerifyUserCanLoginWithValidCredentials();
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inDesktopViewVerifyUserCanLoginWithValidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user cannot login with invalid credentials.
	*/
	@Test( priority = 3, enabled = true, description = "In Desktop view - Verify user cannot login with invalid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 3, enabled = true, description = "In Desktop view - Verify user cannot login with invalid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inDesktopViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inDesktopViewVerifyUserCannotLoginWithInvalidCredentials();
			Assert.assertTrue(returnValue,"In Desktop view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inDesktopViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify Search field default text.
	*/
	@Test( priority = 4, enabled = true, description = "In Desktop view - Verify Search field default text")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 4, enabled = true, description = "In Desktop view - Verify Search field default text")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inDesktopViewVerifySearchFieldDefaultText()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inDesktopViewVerifySearchFieldDefaultText();
			Assert.assertTrue(returnValue,"In Desktop view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inDesktopViewVerifySearchFieldDefaultText");
		}
	}


	/*
	* Please write method description here.
	* In Desktop view - Verify user can perform search.
	*/
	@Test( priority = 5, enabled = true, description = "In Desktop view - Verify user can perform search")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 5, enabled = true, description = "In Desktop view - Verify user can perform search")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inDesktopViewVerifyUserCanPerformSearch()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inDesktopViewVerifyUserCanPerformSearch();
			Assert.assertTrue(returnValue,"In Desktop view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inDesktopViewVerifyUserCanPerformSearch");
		}
	}


	/*
	* Please write method description here.
	* Verify page is resized according to Tablet view.
	*/
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 6, enabled = true, description = "Verify page is resized according to Tablet view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "NYDJ_Site", dataKey = "tablet_layout")
	public void verifyPageIsResizedAccordingToTabletView(String width, String height, String expected_no_of_columns)
	{
		boolean returnValue = false;
		try
		{
		 returnValue = nYDJ_TC.verifyPageIsResizedAccordingToDesktopView();
			Assert.assertTrue(returnValue,"Verify page is resized according to Desktop view failed.");
		}
		catch (Throwable e)
		{
			nYDJ_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(returnValue), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToTabletView", "verifyPageIsResizedAccordingToTabletView");
		}
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user can login with valid credentials.
	*/
	@Test( priority = 7, enabled = true, description = "In Tablet view - Verify user can login with valid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 7, enabled = true, description = "In Tablet view - Verify user can login with valid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inTabletViewVerifyUserCanLoginWithValidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inTabletViewVerifyUserCanLoginWithValidCredentials();
			Assert.assertTrue(returnValue,"In Tablet view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inTabletViewVerifyUserCanLoginWithValidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user cannot login with invalid credentials.
	*/
	@Test( priority = 8, enabled = true, description = "In Tablet view - Verify user cannot login with invalid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 8, enabled = true, description = "In Tablet view - Verify user cannot login with invalid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inTabletViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inTabletViewVerifyUserCannotLoginWithInvalidCredentials();
			Assert.assertTrue(returnValue,"In Tablet view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inTabletViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify Search field default text.
	*/
	@Test( priority = 9, enabled = true, description = "In Tablet view - Verify Search field default text")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 9, enabled = true, description = "In Tablet view - Verify Search field default text")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inTabletViewVerifySearchFieldDefaultText()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inTabletViewVerifySearchFieldDefaultText();
			Assert.assertTrue(returnValue,"In Tablet view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inTabletViewVerifySearchFieldDefaultText");
		}
	}


	/*
	* Please write method description here.
	* In Tablet view - Verify user can perform search.
	*/
	@Test( priority = 10, enabled = true, description = "In Tablet view - Verify user can perform search")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 10, enabled = true, description = "In Tablet view - Verify user can perform search")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inTabletViewVerifyUserCanPerformSearch()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inTabletViewVerifyUserCanPerformSearch();
			Assert.assertTrue(returnValue,"In Tablet view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inTabletViewVerifyUserCanPerformSearch");
		}
	}


	/*
	* Please write method description here.
	* Verify page is resized according to Mobile view.
	*/
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "Desktop", "Regression"}, priority = 11, enabled = true, description = "Verify page is resized according to Mobile view")
	@ITafExcelDataProviderInputs(excelFile = "BaseLayoutDataFile", excelsheet = "NYDJ_Site", dataKey = "mobile_layout")
	public void verifyPageIsResizedAccordingToMobileView(String width, String height, String expected_no_of_columns)
	{
		boolean returnValue = false;
		try
		{
		 returnValue = nYDJ_TC.verifyPageIsResizedAccordingToDesktopView();
			Assert.assertTrue(returnValue,"Verify page is resized according to Desktop view failed.");
		}
		catch (Throwable e)
		{
			nYDJ_TC.logAndCreateADefect(e, "", "", "Is page resized", String.valueOf(returnValue), String.valueOf(true), "defectName_verifyPageIsResizedAccordingToMobileView", "verifyPageIsResizedAccordingToMobileView");
		}
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user can login with valid credentials.
	*/
	@Test( priority = 12, enabled = true, description = "In Mobile view - Verify user can login with valid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 12, enabled = true, description = "In Mobile view - Verify user can login with valid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inMobileViewVerifyUserCanLoginWithValidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inMobileViewVerifyUserCanLoginWithValidCredentials();
			Assert.assertTrue(returnValue,"In Mobile view - Verify user can login with valid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inMobileViewVerifyUserCanLoginWithValidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user cannot login with invalid credentials.
	*/
	@Test( priority = 13, enabled = true, description = "In Mobile view - Verify user cannot login with invalid credentials")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 13, enabled = true, description = "In Mobile view - Verify user cannot login with invalid credentials")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inMobileViewVerifyUserCannotLoginWithInvalidCredentials()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inMobileViewVerifyUserCannotLoginWithInvalidCredentials();
			Assert.assertTrue(returnValue,"In Mobile view - Verify user cannot login with invalid credentials failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inMobileViewVerifyUserCannotLoginWithInvalidCredentials");
		}
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify Search field default text.
	*/
	@Test( priority = 14, enabled = true, description = "In Mobile view - Verify Search field default text")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 14, enabled = true, description = "In Mobile view - Verify Search field default text")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inMobileViewVerifySearchFieldDefaultText()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inMobileViewVerifySearchFieldDefaultText();
			Assert.assertTrue(returnValue,"In Mobile view - Verify Search field default text failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inMobileViewVerifySearchFieldDefaultText");
		}
	}


	/*
	* Please write method description here.
	* In Mobile view - Verify user can perform search.
	*/
	@Test( priority = 15, enabled = true, description = "In Mobile view - Verify user can perform search")
	//@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, groups = { "??", "??"}, priority = 15, enabled = true, description = "In Mobile view - Verify user can perform search")
	//@ITafExcelDataProviderInputs(excelFile = "??", excelsheet = "??", dataKey = "??")
	public void inMobileViewVerifyUserCanPerformSearch()
	{
		try
		{
			boolean returnValue = nYDJ_TC.inMobileViewVerifyUserCanPerformSearch();
			Assert.assertTrue(returnValue,"In Mobile view - Verify user can perform search failed.");
		}
		catch (Throwable e)
		{
			//Place holder ?? left for developer to fill in. String data type shown/kept for each parameter so that developer knows what type of parameters to fill. 
			//Example: nYDJ_TC.logAndCreateADefect(e, "Three Coloumn SocialBox with id  ", idofSocialBox, "Result of Social Box is present or not  ", String.valueOf(value), String.valueOf(true),defectName, "isSocialBoxRenderedOnPage"
			//Please remove this three comment lines when proper values are filled in. 
			nYDJ_TC.logAndCreateADefect(e, " "," ", " ", String.valueOf(""), String.valueOf(true), "defectName", "inMobileViewVerifyUserCanPerformSearch");
		}
	}

}
