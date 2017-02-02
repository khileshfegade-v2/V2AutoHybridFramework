package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;

import com.v2solutions.taf.exception.ProfileBuilderException;
import com.v2solutions.taf.util.CommonUtil;
import com.v2solutions.taf.util.LogUtil;

/**
 *
 * Gets the driver based on the profile information
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class DriverFactory {

	static Log log = LogUtil.getLog(DriverFactory.class);

	/**
	 * Returns web driver.
	 * @param profile profile instance
	 * @return web driver
	 * @throws ProfileBuilderException throws when exception on creating driver
	 * @throws ATUTestRecorderException 
	 */
	public static WebDriver getDriver(ProfileConfiguration profile) throws ProfileBuilderException {
		log.info("Start - getDriver");
		WebDriver webDriver = null;
		if(CommonUtil.isNull(profile)){
			log.error("failed to create driver, profile - "+profile);
		throw new ProfileBuilderException("failed to create driver, profile is missing")
;		}

		if(CommonUtil.isNull(profile.getBrowser())){
			log.info("Browser information is missing, browser - "+ profile.getBrowser());
		}

		ProfileBuilder pfBuilder = new ProfileBuilder(profile);
		webDriver = pfBuilder.getDriver();
		log.info("End - getDriver");
		log.info("END OF DRIVER FACTORY");
		return webDriver;
	}
}