package com.v2solutions.taf.util;

import com.v2solutions.taf.core.BrowserType;
import com.v2solutions.taf.core.TestBed;

/**
 * This enum class checks on browser type.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public enum BrowserInfoUtil {
	INSTANCE;
	
	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public boolean isIE() {
		return TestBed.INSTANCE.getProfile().getBrowser().toUpperCase().contains("IE");
	}

	

	/**
	 * check the current test is running on IE9 or non-IE9 browsers
	 * 
	 * @return return true if it's a IE9 browser test, else it will return false
	 */
	public boolean isIE9() {
		return BrowserType.IE9.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on IE10 or non-IE10 browsers
	 * 
	 * @return return true if it's a IE10 browser test, else it will return false
	 */
	public boolean isIE10() {
		return BrowserType.IE10.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}

	/**
	 * check the current test is running on IE11 or non-IE browsers
	 * 
	 * @return return true if it's a IE11 browser test, else it will return false
	 */
	public boolean isIE11() {
		return BrowserType.IE11.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on Edge or non-Edge browsers
	 * 
	 * @return return true if it's a Edge browser test, else it will return false
	 */
	public boolean isEdge() {
		return BrowserType.Edge.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}

	/**
	 * check the current test is running on Firefox or non-Firefox browsers
	 * 
	 * @return return true if it's a Firefox browser test, else it will return false
	 */
	public boolean isFF() {
		return BrowserType.Firefox.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}

	/**
	 * check the current test is running on Chrome or non-Chrome browsers
	 * 
	 * @return return true if it's a Chrome browser test, else it will return false
	 */
	public boolean isChrome() {
		return BrowserType.Chrome.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}

	/**
	 * check the current test is running on Safari or non-Safari browsers
	 * 
	 * @return return true if it's a Safari browser test, else it will return false
	 */
	public boolean isSafari() {
		return BrowserType.Safari.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on Android or non-Android browsers
	 * 
	 * @return return true if it's a Android browser test, else it will return false
	 */
	public boolean isCloudAndroid() {
		return BrowserType.Android.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudIPhone or non-CloudIPhone browsers
	 * 
	 * @return return true if it's a CloudIPhone browser test, else it will return false
	 */
	public boolean isCloudIPhone() {
		return BrowserType.CloudIPhone.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudIPad or non-CloudIPad browsers
	 * 
	 * @return return true if it's a CloudIPad browser test, else it will return false
	 */
	public boolean isCloudIPad() {
		return BrowserType.CloudIPad.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudIE or non-CloudIE browsers
	 * 
	 * @return return true if it's a CloudIE browser test, else it will return false
	 */
	public boolean isCloudIE(){
		return BrowserType.CloudIE.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudChrome or non-CloudChrome browsers
	 * 
	 * @return return true if it's a CloudChrome browser test, else it will return false
	 */
	public boolean isCloudChrome(){
		return BrowserType.CloudChrome.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudSafari or non-CloudChrome browsers
	 * 
	 * @return return true if it's a CloudSafari browser test, else it will return false
	 */
	public boolean isCloudSafari(){
		return BrowserType.CloudSafari.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
	/**
	 * check the current test is running on CloudFF or non-CloudFF browsers
	 * 
	 * @return return true if it's a CloudFF browser test, else it will return false
	 */
	public boolean isCloudFF(){
		return BrowserType.CloudFF.getBrowser().equalsIgnoreCase(TestBed.INSTANCE.getProfile().getBrowser());
	}
	
}

