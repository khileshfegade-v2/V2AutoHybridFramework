package com.v2solutions.taf.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;

import com.v2solutions.taf.exception.ProfileBuilderException;
import com.v2solutions.taf.util.LogUtil;

/**
 * ProfileConfiguration creates profiles based on configuration input.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class ProfileConfiguration {

	static Log log = LogUtil.getLog(ProfileConfiguration.class);
	ProfileConfiguration profile = null;
	
	final static String BASE_URL = "BASE_URL";
	final static String MAX_WAIT_TIME_SECONDS = "MAX_WAIT_TIME_SECONDS";
	final static String BROWSER = "BROWSER";
	final static String HUB = "HUB";
	final static String PORT = "PORT";
	final static String TEST_ENV = "TEST_ENV";
	final static String FIREFOX_PROFILE_PATH = "FIREFOX_PROFILE_PATH";
	final static String PROXY = "PROXY";
	final static String VERSION = "VERSION";
	final static String PLATFORM = "PLATFORM";
	final static String PLATFORM_VERSION = "PLATFORM_VERSION";
	final static String DEVICE = "DEVICE";
	final static String CLOUDURL = "CLOUD_URL";
	final static String RESOLUTION = "RESOLUTION";
	final static String JS_ERROR_LOGGING = "JS_ERROR_LOGGING";
	final static String CAPTURE_VIDEO = "CAPTURE_VIDEO";
	final static String EMAIL_NOTIFICATION = "EMAIL_NOTIFICATION";
	
	protected Properties props;
	protected String browser = null;
	protected String baseUrl = null;
	protected int maxWaitTime = 30;
	protected String testEnv = null;
	protected String hub = null;
	protected String port = "4444";
	protected String fireFoxProfilePath = null;
	protected String proxy = null;
	protected String noProxy = null;
	protected String driverlocation=null;
	protected String version=null;
	protected String platform=null;
	protected String platformVersion=null;
	protected String device=null;
	protected String cloudUrl=null;
	protected String resolution = null;
	protected String jsErrorLogging = null;
	protected String realMobile = null;
	protected String captureVideo = null;
	protected String emailnotification = null;
	protected String driverPath = System.getProperty ("user.dir");
	
	
	/**
	 * Initializes Profile configuration.
	 * @param fileName property file.
	 * @throws ProfileBuilderException on error creating profile.
	 */
	public ProfileConfiguration(String fileName) throws ProfileBuilderException {

		if(null == fileName){
			throw new ProfileBuilderException("Config file cannot be null");
		}
		props = new Properties();
		File configFile = new File(fileName);
		log.info(configFile.getAbsolutePath()
				+ " config file name:  " + fileName);

		FileReader configReader = null;
		try {
			configReader = new FileReader(configFile.getAbsolutePath());
			if(configReader!=null){
				props.load(configReader);				
			}
		} catch (FileNotFoundException e) {
			throw new ProfileBuilderException("Config does not exist at location : " + configFile.getAbsolutePath() + " :: " + e.getMessage());
		} catch (IOException e) {
			throw new ProfileBuilderException("Could not load config file : " + configFile.getAbsolutePath());
		}

		// TODO: Validate each value from properties and fail if value is incorrect or assign defaults
		setTestEnv(props.getProperty(TEST_ENV));

		if(TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(getTestEnv())){
			setBaseURL(props.getProperty(BASE_URL));
			setHub(props.getProperty(HUB));
			setPort(props.getProperty(PORT));
			setProxy(props.getProperty(PROXY));

		} else if(TestEnvironmentType.LOCAL.getTestEnv().equalsIgnoreCase(getTestEnv())) {

			if(props.getProperty(BROWSER).equalsIgnoreCase("CHROME")){
				driverPath = driverPath+"\\exe\\chromedriver.exe";
				log.info("driverlocation:"+driverPath);
				setDriverlocation(driverPath);
			}
			if(props.getProperty(BROWSER).contains("IE")){
				driverPath = driverPath+"\\exe\\IEDriverServer.exe";
				log.info("driverlocation:"+driverPath);
				setDriverlocation(driverPath);
			}

		} else {
			log.info("INSIDE PROFILE CONFIG.....");
			setDevice(props.getProperty(DEVICE));
			setCloudUrl(props.getProperty(CLOUDURL));		
		}
		
		setBaseURL(props.getProperty("BASE_URL"));
		setMaxWaitTime(props.getProperty(MAX_WAIT_TIME_SECONDS));
		setBrowser(props.getProperty(BROWSER));
		setVersion(props.getProperty(VERSION));
		setPlatform(props.getProperty(PLATFORM));
		setPlatformVersion(props.getProperty(PLATFORM_VERSION));
		setResolution(props.getProperty(RESOLUTION));
		setJSErrorLogging(props.getProperty(JS_ERROR_LOGGING));
		setRealMobile(props.getProperty("REAL_MOBILE"));
		setCaptureVideo(props.getProperty(CAPTURE_VIDEO));
		setEmailNotification(props.getProperty(EMAIL_NOTIFICATION));
		if(BrowserType.Firefox.getBrowser().equalsIgnoreCase(getBrowser())){
			setFireFoxProfilePath(props.getProperty(FIREFOX_PROFILE_PATH));
		}
		log.info("HEREHERE - 1");
	}

	/**
	 * Returns browser
	 * @return browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * Sets browser
	 * @param browser
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * Sets version
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Returns version
	 * @return version
	 */
	public String getVersion() {
		return version;
	}


	public void setPlatform(String platform) {
		if(platform != null && !platform.isEmpty()) {
			this.platform=platform;
		}
		else this.platform="ANY";
	}

	/**
	 * Returns platform
	 * @return platform
	 */
	public String getPlatform() {
		return platform;
	}


	/**
	 * Returns platform version
	 * @return platformVersion
	 */
	public String getPlatformVersion() {
		return platformVersion;
	}

	/**
	 * Sets platform version
	 * @param platformVersion
	 */
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}

	/**
	 * Returns base URL
	 * @return base URL
	 */
	public String getBaseURL() {
		return baseUrl;
	}

	/**
	 * Sets base URL
	 * @param baseURL base URL
	 */
	public void setBaseURL(String baseURL) {
		this.baseUrl = baseURL;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(String maxWatiTimeString) {
		if (maxWatiTimeString != null && maxWatiTimeString != "") {
			this.maxWaitTime = Integer.parseInt(maxWatiTimeString);
		}
	}

	public String getTestEnv() {
		return testEnv;
	}

	public void setTestEnv(String testEnv) {
		this.testEnv = testEnv;
	}

	public String getHub() {
		return hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFireFoxProfilePath() {
		return fireFoxProfilePath;
	}

	public void setFireFoxProfilePath(String fireFoxProfilePath) {
		this.fireFoxProfilePath = fireFoxProfilePath;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getNoProxy() {
		return noProxy;
	}

	/**
	 * Returns all properties
	 * @return all properties.
	 */
	public Properties getAllProperties() {
		return props;
	}

	/**
	 * Returns driver location
	 * @return driver location.
	 */
	public String getDriverlocation() {
		return driverlocation;
	}

	/**
	 * sets driver location.
	 * @param driverlocation driver location
	 */
	public void setDriverlocation(String driverlocation) {
		this.driverlocation = driverlocation;
	}
	/**
	 * Get capture video
	 * @return
	 */
	public String getCAPTUREVIDEO() {
		return captureVideo;
	}

	/**
	 * sets capture video
	 * @param port
	 */
	public void setCaptureVideo(String captureVideo) {
		this.captureVideo = captureVideo;
	}
	
	/**
	 * Get capture video
	 * @return
	 */
	public String getEmailNotification() {
		return emailnotification;
	}

	/**
	 * sets capture video
	 * @param port
	 */
	public void setEmailNotification(String emailNotification) {
		this.emailnotification = emailNotification;
	}
	
	
	/**
	 * Returns device name
	 * @return device name
	 */
	public String getDevice(){
		return device;
	}

	/**
	 * sets device name.
	 * @param device name
	 */
	public void setDevice(String device){
		this.device = device;
	}

	/**
	 * Returns cloud url
	 * @return cloud url
	 */
	public String getCloudUrl(){
		return cloudUrl;
	}

	/**
	 * sets cloud url
	 * @param cloud url
	 */
	public void setCloudUrl(String cloudUrl){
		this.cloudUrl = cloudUrl;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getJSErrorLogging() {
		return jsErrorLogging;
	}

	public void setJSErrorLogging(String jsErrorLogging) {
		this.jsErrorLogging = jsErrorLogging;
	}

	public String getRealMobile() {
		return realMobile;
	}

	public void setRealMobile(String realMobile) {
		this.realMobile = realMobile;
	}



}
