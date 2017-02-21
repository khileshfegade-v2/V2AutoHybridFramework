package com.v2solutions.common;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.v2solutions.taf.TestBedManager;
import com.v2solutions.taf.core.ProfileConfiguration;
import com.v2solutions.taf.util.LogUtil;
import com.v2solutions.taf.util.MailNotificationUtil;
import com.v2solutions.taf.util.RecordingModesUtils;

/**
 * Base test that all the test classes should extend.
 * This class initializes {@link TestBedManager} based on configuration file input
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class BaseTest {

	static Log log = LogUtil.getLog(BaseTest.class);

	protected TestBedManager testBedManager = null;
	private static String TEST_CONFIG_FILE_PARAM = "configFileName";

	public String BASE_URL = null;
	public RecordingModesUtils videoRecord;
	public static long startTime = 0;
    public static int methodNum = 0;

    private static int browserOpen =0;
    
    boolean flag = false;
    DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
    Date date = new Date();
    ProfileConfiguration profile;
    boolean emailNotificationFlag = false;
    
    /**
     * Following variable is added to check if @BeforeClass method of derived class is already executed or not.
     * This is because , when calling script TestNG class from out side script project using pure TestNG APIs
     * and each method is called separately, @BeforeClass method is executed before each call from out side method.
     * This is not bug, as for TestNG one method call is automatic test call so it calls init methods every time.
     * That also result in opening and closing browser for each test method call instead of per class.
     * This variable is set to true in derived call to make one time initialization. This change is for Concordion BDD 
     */
    protected boolean classInitDone = false;
    
    /**
     * Added new constructor which just create instance of SuiteListener to force it
     * read properties file, so that proper test bed manager is initialized. This change is required,
     * as if TestNG class methods are called externally like from BDD Concordion, SuiteListener is not initialized.
     * This change makes SuiteListener initialize properly no matter what 
     */
    public BaseTest()
    {
    	new com.v2solutions.taf.listener.SuiteListener();
    }

    /**
     * This method is added to read config passed as java parameter using -D option.
     * This is required as SiteListener is not initialized when script classes are executed externally
     * 
     */
    public void loadDriver()  
    {
		try
		{
			log.info("Start - loadConfig");
			String configFile = System.getProperty(TEST_CONFIG_FILE_PARAM);
			log.info("Reading config file : " + configFile);
			System.out.println("[Important] Reading config file : " + configFile);
			if(null == configFile)
			{
				// Check on input from system property
				configFile = System.getProperty(TEST_CONFIG_FILE_PARAM);
			}
			log.info("Configuration file - " + configFile);
			
			testBedManager = TestBedManager.INSTANCE;
			testBedManager.setConfig(configFile);
			log.info("End - loadConfig");
			browserOpen++;
			log.info("Open browser ::"+browserOpen);
			
			 profile = new ProfileConfiguration(configFile);
	
			
			BASE_URL = profile.getBaseURL();
			System.out.println("[Important] BASE_URL :: " + BASE_URL);
			if(profile.getTestEnv().equalsIgnoreCase("LOCAL")){
				if((profile.getCAPTUREVIDEO().equalsIgnoreCase("YES"))||(profile.getCAPTUREVIDEO().equalsIgnoreCase("true"))){
					videoRecord = new RecordingModesUtils();
					videoRecord.startRecording(); 
					log.info("Started recording local execution...");
					System.out.println("Started recording local execution...");
					  flag = true;
				}
			}
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	}
    /**
     * @param method
     */
	@BeforeMethod(alwaysRun = true)
	public void printTimeStart(Method method) {
		methodNum = methodNum + 1;
		log.info("Starting method " + methodNum + "::" + method.getName() + "<.<.<.<");
		startTime = System.currentTimeMillis();
	}

	/**
	 * @param method
	 */
	@AfterMethod(alwaysRun = true)
	public void printTimeTaken(Method method) {
		log.info("Ending method " + methodNum + "::" + method.getName() + ">.>.>.>Time Taken ::" + (System.currentTimeMillis() - startTime) / 1000
		        + " Seconds");
	}


	
	/**
	 * Commented  @AfterClass part for Concordion BDD as this method was getting called
	 * every time as TestNG test methods called externally. Similra to @BeforeClass.
	 * To stop this behhaviour but still make script project work as is, commented it.
	 * Alternate solution is by adding one @Test method with priority 10 so its executed at
	 * end when just running TestNG and same method can be called from externally
	 * 
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() 
	{
		System.out.println(" ******************** @AfterClass tearDown() method of BaseTest.java ***************************");
		if (testBedManager.getDriver() != null)
		{
			System.out.println("-----------$$ After class method $$-----------iffff-------");
			testBedManager.getDriver().quit();
			testBedManager.setDriverNull();
		}
		browserOpen--;
		log.info("------browserOpen ::" + browserOpen);
		System.out.println("-----------$$ ------ $$------------------");
	}

	 @AfterSuite(alwaysRun = true)
	 public void shutDownDriver() 
	 {
		 try
		 {
			 log.info("browserOpen ::"+browserOpen);
			 log.info("testBedManager.getDriver() ::"+testBedManager.getDriver());
			 if (browserOpen > 0 && testBedManager.getDriver() != null)
			 {
				 testBedManager.getDriver().quit();
			 }
			 testBedManager.setDriverNull();
			 
		 }
		catch(Exception exp)
		{
			System.out.println("Exception occured: "+exp.getMessage());
		}
		
	 }


	public String constructURL(String inputURL)
	{		
		System.out.println("[Important] : inputURL  : "+inputURL);
		System.out.println("[Important] : BASE_URL  : "+BASE_URL);
		
		if(inputURL.contains("<baseurl>"))
			inputURL = inputURL.replace("<baseurl>", BASE_URL);

		return inputURL;
	}
	
	/**
	 * Following method return the WebDriver instance related to web page.
	 * This method is added for the Concordion BDD where screen shot taker requires
	 * instance of web driver
	 * 
	 * @return WebDriver
	 */
	public WebDriver getDriver()
	{
		return testBedManager.getDriver();
	}
	
	/*
	 * Following method returns true if the tests are executing locally and not on BrowserStack.
	 * This method is added as "is instance of RemoteWebDriver" condition not working correctly
	 * to augment the driver when taking screen shot and thorows the exception. This methods
	 * added to correctly get the driver for screen shot. Again this method is used in Concordion BDD project
	 * 
	 *   @return boolean 
	 */
	public boolean isLocal()
	{
		Properties configProperties = testBedManager.getConfigInfo();
		
		String value = configProperties.getProperty("TEST_ENV","local");
		
		if(value != null && value.equalsIgnoreCase("local") == true ) return true;
		if(value != null && value.equalsIgnoreCase("cloud") == true ) return false;
		
		return true;
	}

	/**
	 * This method is added for two purpose, one, it should run after all methods are executed while running
	 * in script project (high priority) and second method can be called from externally like
	 * Concordion BDD to close browserDriver gracefully
	 * @throws Exception 
	 * @throws ATUTestRecorderException 
	 */
	@Test(priority = 300,enabled=true,description = "Closes the browser instance")
	public void close() throws Exception 
	{
		System.out.println("*************************** This close method is invoked by Concordion BDD Test Class *****************************");
		if(flag){
			tearDown();
			 //To stop video recording.
			videoRecord.stopRecording();
			  log.info("Stopped recording local execution...");
			  System.out.println("Stopped recording local execution...");
			  System.out.println("Rrrrrrrrrrrrrrrrrrrrrrr ifffffff");
		}
		else{
			System.out.println("Elseeeeeeeeeeeeeeeeeee eeeeeeeeee");
			tearDown();
		}
		
	}
}
