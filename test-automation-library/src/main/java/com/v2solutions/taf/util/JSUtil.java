package com.v2solutions.taf.util;

import org.apache.commons.logging.Log;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class contains utility methods for executing javascript.
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class JSUtil {

	private static Log log = LogUtil.getLog(JSUtil.class);

	/**
	 * JSClickById will take in the id and use Javascript to generate a click to an element
	 * not accessible by normal selenium click
	 * @param driver current webdriver instance
	 * @param id the element id
	 */
	public static void JSClickById (WebDriver driver, String id) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("$('#" + id + "')[0].click();");
	}

	/**
	 * JSClickById will take in the id and use Javascript to generate a click to an element not accessible by normal selenium click
	 * @param driver current webdriver instance
	 * @param id the element id
	 */
	public static void JSClickByElement (WebDriver driver, WebElement el) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", el);
	}

	public static void JSResize(WebDriver driver, Dimension d){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.resize(500,500)", driver.getWindowHandle());
	}


	/**
	 * JSExecuteScript is a generic method to execute the script using JavascriptExecutor
	 * @param driver current webdriver instance
	 * @param jsCode Javascript Code to be passed as first argument of the executeScript method(for instance window.scrollBy(0,250))
	 * @param arg2 second argument of the executeScript method(for instance any webelement, if not required just initialise to a blank "")
	 * */
	public static Object jsExecuteScript(WebDriver driver, String jsCode, String arg2){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(jsCode, arg2);

	}


}
