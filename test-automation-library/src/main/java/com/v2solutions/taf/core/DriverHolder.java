package com.v2solutions.taf.core;


import org.openqa.selenium.WebDriver;

/**
* 
* @author V2Solutions
* @version 1.0
* 
*/

public class DriverHolder {
	
	static DriverHolder tempDriverHolder = new DriverHolder();
	
	public static DriverHolder getInstance()
	{
		return tempDriverHolder;
	}
	static WebDriver webDriver = null;
	public static void setDriver(WebDriver pWebDriver){ webDriver = pWebDriver;}
	public static WebDriver getDriver(){ return webDriver;}

}
