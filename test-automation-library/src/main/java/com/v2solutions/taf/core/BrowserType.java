 package com.v2solutions.taf.core;

/**
 * List the valid Browsers supported by the test framework
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public enum BrowserType {
	
	Firefox("FF"), 
	IE9("IE9"), 
	IE10("IE10"), 
	IE11("IE11"),
	Edge("Edge"),
	Chrome("Chrome"), 
	Safari("Safari"),
	CloudIPhone("iPhone"),
	CloudIPad("iPad"),
    Android("Android"),
	CloudIE("internet explorer"),
	CloudSafari("safari"),
	CloudChrome("chrome"),
	CloudFF("firefox");
    
	
	private String browser;

	private BrowserType(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return browser;
	}

}
