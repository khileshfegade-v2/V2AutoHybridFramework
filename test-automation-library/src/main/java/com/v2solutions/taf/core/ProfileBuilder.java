package com.v2solutions.taf.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
//import org.openqa.selenium.iphone.IPhoneDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.v2solutions.taf.exception.ProfileBuilderException;
import com.v2solutions.taf.util.LogUtil;
/**
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class ProfileBuilder {

	ProfileConfiguration profile = null;
	protected String osName = null;
	protected String osVersion = null;
	protected String browserName = null;
	protected String testEnv = null;
	protected String hubLocation = null;
	protected DesiredCapabilities capabilities = null;
	protected WebDriver driver = null;
	protected String driverlocation=null;
	protected String browserVersion = null;
	protected String deviceName=null;
	protected String cloudUrl=null;
	protected String resolution=null;
	protected String jsErrorLogging=null; 
	protected String realMobile = null;
	protected String captureVideo = null;
	protected String emailnotification = null;

	static Log log = LogUtil.getLog(ProfileBuilder.class);
	DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
	Date date = new Date();

	public ProfileBuilder(ProfileConfiguration profile) throws ProfileBuilderException {

		this.profile = profile;
		setOsName(profile.getPlatform());
		setOsVersion(profile.getPlatformVersion());
		setBrowserVersion(profile.getVersion());
		setBrowserName(profile.getBrowser());
		setTestEnv(profile.getTestEnv());
		setHubAndPort();
		setDeviceName(profile.getDevice());
		setCloudUrl(profile.getCloudUrl());
		setDriverlocation(profile.getDriverlocation());
		setResolution(profile.getResolution());
		setJSErrorLogging(profile.getJSErrorLogging());
		setRealMobile(profile.getRealMobile());
		setCaptureVideo(profile.getCAPTUREVIDEO());
		setEmailNotification(profile.getEmailNotification());
		createDriver();		
	}


	public String getDriverlocation() {

		return driverlocation;
	}

	public void setDriverlocation(String driverlocation) {
		this.driverlocation = driverlocation;
	}

	public String getOsName() {
		log.info("osName existed as: "+ osName);
		return osName;

	}

	private void setOsName(String osName) {
		this.osName = osName;
		log.info("osName coming: "+osName+ "osName set to: "+this.osName);
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}


	public String getTestEnv() {
		return testEnv;
	}

	public void setTestEnv(String testEnv) {
		this.testEnv = testEnv;
	}

	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getHubLocation() {
		return hubLocation;
	}

	public void setHubLocation(String hubLocation) {
		this.hubLocation = hubLocation;
	}

	public String getDeviceName(){
		return deviceName;
	}

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getCloudUrl(){
		return cloudUrl;
	}

	public void setCloudUrl(String cloudUrl){
		this.cloudUrl = cloudUrl;
	}

	public String getResolution(){
		return resolution;
	}

	public void setResolution(String resolution){
		this.resolution = resolution;
	}

	public String getJSErrorLogging(){
		return jsErrorLogging;
	}

	public void setJSErrorLogging(String jsErrorLogging){
		this.jsErrorLogging = jsErrorLogging;
	}

	protected void setHubAndPort() {
		String hub = profile.getHub();
		String port = profile.getPort();

		// defalut hub to localhost
		if (hub == null || hub.isEmpty()) {
			hub = "localhost";
		}

		// default port to 4444
		if (port == null || port.isEmpty()) {
			port = "4444";
		}

		// add proxy setting to the webdriver
		String proxyString = profile.getProxy();
		String noProxyString = profile.getNoProxy();
		if (proxyString != null && !proxyString.isEmpty()) {
			Proxy proxysetting = new Proxy();
			proxysetting.setHttpProxy(proxyString)
			.setFtpProxy(proxyString).setSslProxy(proxyString);
			if (noProxyString != null && !proxyString.isEmpty()) {
				proxysetting.setNoProxy(noProxyString);
			}

			capabilities.setCapability(CapabilityType.PROXY,
					proxysetting);
		}

		hubLocation = "http://" + hub + ":" + port + "/wd/hub";
	}

	/**
	 * Creates Drivers depending upon the browser type
	 * @throws ProfileBuilderException
	 * @throws ATUTestRecorderException 
	 */
	public void createDriver() throws ProfileBuilderException{
		
		if (BrowserType.Firefox.getBrowser().equalsIgnoreCase(profile.getBrowser())
				|| profile.getBrowser().toUpperCase().contains("FIREFOX")) {

			createFirefoxDriver();

		} else if (BrowserType.IE9.getBrowser().equalsIgnoreCase(profile.getBrowser())
				|| BrowserType.IE10.getBrowser().equalsIgnoreCase(profile.getBrowser())
				|| BrowserType.IE11.getBrowser().equalsIgnoreCase(profile.getBrowser())
				|| profile.getBrowser().toUpperCase().contains("IE")
				|| profile.getBrowser().toUpperCase().contains("INTERNET EXPLORER")) {

			createIEDriver();

		} else if (profile.getBrowser().toUpperCase().contains("CHROME")) {
			createChromeDriver();

		} else if (profile.getBrowser().toUpperCase().contains("SAFARI")) {
			log.info("HEREHERE - 1");
			createSafariDriver();
			log.info("HEREHERE - 2");
		} else if (profile.getBrowser().toUpperCase().contains("IOS")
				|| profile.getBrowser().toUpperCase().contains("IPHONE")
				|| profile.getBrowser().toUpperCase().contains("IPAD")) {

			createiOSDriver();

		} else if (profile.getBrowser().toUpperCase().contains("ANDROID")) {
			log.info("in android driver setup");
			createAndroidDriver();
		}  else {

			createGenericDriver();
		}

	}


	/**
	 * Creates Driver for IE9,IE10,IE11
	 * @throws ProfileBuilderException
	 * @throws ATUTestRecorderException 
	 */
	private  void createIEDriver() throws ProfileBuilderException
	{

		if (TestEnvironmentType.LOCAL.getTestEnv().equalsIgnoreCase(
				getTestEnv())) {
			
			System.setProperty("webdriver.ie.driver", getDriverlocation());
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.LOG_FILE,"IEDriverLog.txt");
			capabilities.setCapability(InternetExplorerDriver.LOG_LEVEL,"DEBUG");

			driver = new InternetExplorerDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		} else if (TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(
				getTestEnv())) {
			capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);

			if (BrowserType.IE9.getBrowser().equalsIgnoreCase(
					getBrowserName())) {
				capabilities.setVersion("9");
			}else if (BrowserType.IE10.getBrowser().equalsIgnoreCase(
					getBrowserName())) {
				capabilities.setVersion("10");
			}
			else if (BrowserType.IE10.getBrowser().equalsIgnoreCase(
					getBrowserName())) {
				capabilities.setVersion("11");
			}

			setCommonRemoteDetails();

		} else {
			capabilities = DesiredCapabilities.internetExplorer();
			setCommonDetails();
		}

	}


	/**
	 * Creates driver for Chrome
	 * @throws ProfileBuilderException
	 * @throws ATUTestRecorderException 
	 */
	public void createChromeDriver() throws ProfileBuilderException
	{
		if(TestEnvironmentType.LOCAL.getTestEnv().equals(getTestEnv()))
		{
			
			if(getJSErrorLogging() != null)
			{
				if  ((getJSErrorLogging().equalsIgnoreCase("TRUE"))||(getJSErrorLogging().equalsIgnoreCase("YES")))
				{
					System.setProperty("webdriver.chrome.driver", getDriverlocation());
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			        LoggingPreferences loggingprefs = new LoggingPreferences();
			        loggingprefs.enable(LogType.BROWSER, Level.ALL);
			        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
			        driver = new ChromeDriver(capabilities);
				}
				else
				{
					System.setProperty("webdriver.chrome.driver", getDriverlocation());
					capabilities = DesiredCapabilities.chrome();
					LoggingPreferences loggingprefs = new LoggingPreferences();
					loggingprefs.enable(LogType.BROWSER, Level.ALL);
					loggingprefs.enable(LogType.PERFORMANCE, Level.ALL);    //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////

					capabilities.setCapability("logfile", "phantom.log");
					capabilities.setCapability("loglevel", "DEBUG");
					capabilities.setCapability("enable-logging", "scriptlog.log");
					capabilities.setCapability("enablelogging", "scriptlog.log");
					capabilities.setCapability("debug-print","true");
					capabilities.setCapability("log-net-log","true"); 
					capabilities.setCapability("login-profile","true"); 
					capabilities.setCapability("net-log-level","0");
					capabilities.setCapability("trace-to-file","true" );
					capabilities.setCapability("trace-to-file-name","trace.log");
					capabilities.setCapability("debugprint","true");
					capabilities.setCapability("lognetlog","true"); 
					capabilities.setCapability("loginprofile","true"); 
					capabilities.setCapability("netloglevel","0");
					capabilities.setCapability("tracetofile","true" );
					capabilities.setCapability("tracetofilename","trace.log");
					capabilities.setCapability("v","1");

					capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);

					driver = new ChromeDriver(capabilities);
				}
			}
			else
			{

				System.setProperty("webdriver.chrome.driver", getDriverlocation());
				capabilities = DesiredCapabilities.chrome();
				LoggingPreferences loggingprefs = new LoggingPreferences();
				loggingprefs.enable(LogType.BROWSER, Level.ALL);
				loggingprefs.enable(LogType.PERFORMANCE, Level.ALL);    //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////

				capabilities.setCapability("logfile", "phantom.log");
				capabilities.setCapability("loglevel", "DEBUG");
				capabilities.setCapability("enable-logging", "scriptlog.log");
				capabilities.setCapability("enablelogging", "scriptlog.log");
				capabilities.setCapability("debug-print","true");
				capabilities.setCapability("log-net-log","true"); 
				capabilities.setCapability("login-profile","true"); 
				capabilities.setCapability("net-log-level","0");
				capabilities.setCapability("trace-to-file","true" );
				capabilities.setCapability("trace-to-file-name","trace.log");
				capabilities.setCapability("debugprint","true");
				capabilities.setCapability("lognetlog","true"); 
				capabilities.setCapability("loginprofile","true"); 
				capabilities.setCapability("netloglevel","0");
				capabilities.setCapability("tracetofile","true" );
				capabilities.setCapability("tracetofilename","trace.log");
				capabilities.setCapability("v","1");

				capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
				driver = new ChromeDriver(capabilities);
			}

		}
		else if(TestEnvironmentType.REMOTE.getTestEnv().equals(getTestEnv()))
		{
			System.out.println("REMOTE CHROME");
			capabilities = DesiredCapabilities.chrome();
			if(getJSErrorLogging() != null)
			{
				if  ((getJSErrorLogging().equalsIgnoreCase("TRUE"))||(getJSErrorLogging().equalsIgnoreCase("YES")))
				{
					System.out.println("REMOTE CHROME JS_ERROR_LOGGING" + getJSErrorLogging());

					LoggingPreferences loggingprefs = new LoggingPreferences();
					// loggingprefs.enable(LogType.BROWSER, Level.ALL);
					loggingprefs.enable(LogType.BROWSER, Level.ALL);
					loggingprefs.enable(LogType.SERVER, Level.ALL);
					loggingprefs.enable(LogType.DRIVER, Level.ALL);
					loggingprefs.enable(LogType.PROFILER, Level.ALL);
					loggingprefs.enable(LogType.CLIENT, Level.ALL);
					loggingprefs.enable(LogType.PERFORMANCE, Level.ALL);    //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////

					capabilities
					.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
				}
			}
			setCommonRemoteDetails();
		}
		else
		{
			LoggingPreferences loggingprefs = new LoggingPreferences(); //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////
			loggingprefs.enable(LogType.PERFORMANCE, Level.ALL);    //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////
			capabilities = DesiredCapabilities.chrome();
			capabilities
			.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
			setCommonDetails();

		}

	}
	/**
	 * Creates Driver for Firefox
	 * @throws ProfileBuilderException
	 * @throws ATUTestRecorderException 
	 */
	public void createFirefoxDriver() throws ProfileBuilderException
	{
		
		FirefoxProfile ffProfile = null;
		if (profile.getFireFoxProfilePath() != null) {
			ffProfile = new FirefoxProfile(new File(
					profile.getFireFoxProfilePath()));
		} else {
			ffProfile = new FirefoxProfile();
		}

		ffProfile.setEnableNativeEvents(false);
		if(TestEnvironmentType.LOCAL.getTestEnv().equals(getTestEnv())){
			if(getJSErrorLogging() != null)
			{	
				if  ((getJSErrorLogging().equalsIgnoreCase("TRUE"))||(getJSErrorLogging().equalsIgnoreCase("YES")))
				{
					try {
						JavaScriptError.addExtension(ffProfile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			driver = new FirefoxDriver(ffProfile);
		}
		else if(TestEnvironmentType.REMOTE.getTestEnv().equals(getTestEnv()))
		{
			
			System.out.println("REMOTE FIREFOX");
			capabilities = DesiredCapabilities.firefox();

			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			setCommonRemoteDetails();
			

		}
		else
		{
			capabilities = DesiredCapabilities.firefox();
			setCommonDetails();

		}
	}
	
	
	
	
	
	/**
	 * Creates Driver for Safari
	 * @throws ProfileBuilderException
	 */
	public void createSafariDriver() throws ProfileBuilderException
	{
		if (TestEnvironmentType.LOCAL.getTestEnv().equalsIgnoreCase(getTestEnv())) {
			driver = new SafariDriver();
		} else if (TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(getTestEnv())) {
			capabilities = DesiredCapabilities.safari();
			capabilities.setBrowserName(getBrowserName());
			capabilities.setPlatform(Platform.valueOf(profile.getPlatform()));
			setCommonRemoteDetails();
		} else {

			capabilities = DesiredCapabilities.safari();
			setCommonDetails();
		}
	}


	/**
	 * Create Driver for IOS Devices
	 * @throws ProfileBuilderException
	 */
	@SuppressWarnings("deprecation")
	public void createiOSDriver() throws ProfileBuilderException
	{

		if (TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(
				getTestEnv())) {
			capabilities = DesiredCapabilities.iphone();
			setCommonRemoteDetails();
		} else	{

			if (getBrowserName().contains("iPhone")){

				capabilities = DesiredCapabilities.iphone();

			} else {
				capabilities = DesiredCapabilities.ipad();

			}
			capabilities.setCapability("realMobile",profile.getRealMobile());

			capabilities.setCapability("device", getDeviceName());
			setCommonDetails();

		}

	}
	
	
	public void createEdgeDriver() throws ProfileBuilderException
	{

		if (TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(
				getTestEnv())) {
			capabilities = DesiredCapabilities.edge();
			setCommonRemoteDetails();
		} else	{

			if (getBrowserName().contains("Edge")){

				capabilities = DesiredCapabilities.edge();

			} else
			{
				capabilities = DesiredCapabilities.edge();
				setCommonDetails();

			}

		}

	}
	
	/**
	 * Creates GenericDriver
	 * @throws ProfileBuilderException
	 */
	public void createGenericDriver() throws ProfileBuilderException
	{
		try {
			capabilities = DesiredCapabilities.htmlUnit();
			capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
			driver = new RemoteWebDriver(new URL(hubLocation), capabilities);
		} catch (MalformedURLException e) {
			log.error("failed to create driver : " + e.getMessage());
			throw new ProfileBuilderException("failed to create driver : " + e.getMessage());
		}
	}
	
	
	/**
	 * Creates driver for Android
	 * @throws ProfileBuilderException
	 */
	public void createAndroidDriver() throws ProfileBuilderException
	{
		try{
			if (TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(
					getTestEnv())) {
				DesiredCapabilities browser = DesiredCapabilities.android();
				driver = new RemoteWebDriver(new URL(hubLocation), browser);
			} else {

				capabilities = DesiredCapabilities.android();
				capabilities.setCapability("device", getDeviceName());
				setCommonDetails();

			}


		} catch (MalformedURLException e) {
			log.error("failed to create driver for android : " + e.getMessage());
			throw new ProfileBuilderException("Could not create driver :: " + e.getMessage());
		}

	}
	/**
	 * This method set the details for BrowserStackDriver
	 * @throws ProfileBuilderException
	 */
	public void setCommonDetails() throws ProfileBuilderException
	{

		capabilities.setCapability("osVersion", getOsVersion());
		capabilities.setCapability("os", getOsName());
		capabilities.setCapability("version", getBrowserVersion());
		capabilities.setCapability("browserName", getBrowserName());
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("resolution", getResolution());
		capabilities.setCapability("enablePersistentHover", false);
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability("requireWindowFocus", true);

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, java.util.logging.Level.ALL);
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);     //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

		try {
			driver = new RemoteWebDriver(new URL(getCloudUrl()), capabilities);
		} catch (MalformedURLException e) {
			log.error("failed to create driver : " + e.getMessage());
			throw new ProfileBuilderException("failed to create driver : " + e.getMessage());
		}


	}
	/**
	 * This method set details for RemoteWebDriver
	 * @throws ProfileBuilderException
	 */
	public void setCommonRemoteDetails() throws ProfileBuilderException
	{
		
		capabilities.setCapability("resolution", getResolution());
		capabilities.setCapability("os", getOsName());

		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, java.util.logging.Level.ALL);
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);    	 //////////////NETWORK_PERFORMANCE_LOGS////////////////////////////
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		
		try {
			driver = new RemoteWebDriver(new URL(hubLocation), capabilities);
			
		} catch (MalformedURLException e) {
			log.error("failed to create driver : " + e.getMessage());
			throw new ProfileBuilderException("failed to create driver : " + e.getMessage());
		}
		
	}

	public String getRealMobile() {
		return realMobile;
	}

	public void setRealMobile(String realMobile) {
		this.realMobile = realMobile;
	}

	public void setCaptureVideo(String captureVideo) {
		this.captureVideo = captureVideo;
	}

	public void setEmailNotification(String emailNotification) {
		this.emailnotification = emailNotification;
	}
}
