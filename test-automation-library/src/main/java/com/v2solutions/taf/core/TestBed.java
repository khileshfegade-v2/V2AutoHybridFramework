package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import com.v2solutions.taf.exception.ProfileBuilderException;
import com.v2solutions.taf.util.LogUtil;


/**
 * This class is primarily responsible for creating {@link WebDriver}
 * based on the {@link ProfileConfiguration}
 * @author V2Solutions
 * @version 1.0
 * 
 */
public enum TestBed {
	INSTANCE;

	static Log log = LogUtil.getLog(TestBed.class);

	private WebDriver driver = null;
	private ProfileConfiguration profile = null;
	public static int MaxWaitSeconds = 100000;



	public WebDriver getDriver() {
		return driver;
	}

	public void setDriverNull(){
		log.info("in testBed setting Driver = null ");
		driver = null;
	}

	/**
	 * Returns profile configuration
	 * @return profile instance.
	 */
	public ProfileConfiguration getProfile() {
		return profile;
	}

	/**
	 * Sets Profile Configuration
	 * @param profile
	 * @throws ProfileBuilderException
	 * @throws ATUTestRecorderException 
	 */
	public void setProfile(ProfileConfiguration profile) throws ProfileBuilderException {
		if (null == profile) {
			throw new ProfileBuilderException("Profile cannot be null");
		}
		this.profile = profile;
		this.driver = DriverFactory.getDriver(profile);





	}
}