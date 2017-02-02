package com.v2solutions.taf;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;

import com.v2solutions.taf.core.ProfileConfiguration;
import com.v2solutions.taf.core.TestBed;
import com.v2solutions.taf.core.TestEnvironmentType;
import com.v2solutions.taf.exception.ProfileBuilderException;
import com.v2solutions.taf.util.CommonUtil;
import com.v2solutions.taf.util.LogUtil;


/**
 * Test Bed Manager loads the profile for the test environment. 
 * This class initializes {@link TestBed}
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public enum TestBedManager {
	INSTANCE;
	
	static Log log = LogUtil.getLog(TestBedManager.class);
	
	/**
	 * Reads configuration file and create profile and sets in test bed.
	 * 
	 * @param configFileName configuration file name.
	 * @throws ProfileBuilderException throws exception if error in building profile
	 * @throws ATUTestRecorderException 
	 */
	public void setConfig(String configFileName) throws ProfileBuilderException {
		if(CommonUtil.isNull(configFileName)){
			log.error("Cofiguration file name is null -> "+configFileName);			
			throw new ProfileBuilderException("failed to read profile configuration, file name is missing");
		}
		TestBed.INSTANCE.setProfile(new ProfileConfiguration(configFileName));
	}
	
	/**
	 * Returns profile.
	 * @return Profile configuration instance.
	 */
	public ProfileConfiguration getProfile() {
		return TestBed.INSTANCE.getProfile();
	}
	
	/**
	 * Returns browser information.
	 * @return profile browser.
	 */
	public String getTestBedBrowser() {
		return TestBed.INSTANCE.getProfile().getBrowser();
	}
	
	/**
	 * Returns profile properties
	 * @return profile properties
	 */
	public Properties getConfigInfo() {
		return TestBed.INSTANCE.getProfile().getAllProperties();
	}
	
	/**
	 * Returns web driver.
	 * @return web driver
	 */
	public WebDriver getDriver() {
		return TestBed.INSTANCE.getDriver();
	}
	
	public void setDriverNull() {
		TestBed.INSTANCE.setDriverNull();
	}
	

	/**
	 * Returns base URL.
	 * @return profile base URL
	 */
	public String loadBaseURL() {
		if(TestEnvironmentType.REMOTE.getTestEnv().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getTestEnv())){
			return TestBed.INSTANCE.getProfile().getBaseURL();
		}
		return null;
	}

	/**
	 * Returns Test bed.
	 * @return TestBed
	 */
	public static TestBed getTestBed() {
		return TestBed.INSTANCE;
	}
	
	
}
