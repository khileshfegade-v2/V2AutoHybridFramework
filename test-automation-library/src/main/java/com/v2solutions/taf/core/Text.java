package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.Color;

import com.v2solutions.taf.util.BrowserInfoUtil;
import com.v2solutions.taf.util.LogUtil;

/**
 * Renders Text PageObject. .
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public class Text extends PageElement {

	static Log log = LogUtil.getLog(Text.class);

	public Text(WebElement webElement) {
		super(webElement);
	}

	Actions action = new Actions(WebPage.getTestBedManager().getDriver());


	public void textHover(){
		Mouse mouse=((HasInputDevices)WebPage.getTestBedManager().getDriver()).getMouse();
		mouse.mouseMove(((Locatable) this.webElement).getCoordinates());
	}
	
	public String textColor() {
		if(BrowserInfoUtil.INSTANCE.isSafari()){
			action.moveToElement(webElement);
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) WebPage.getTestBedManager().getDriver();
			js.executeScript(mouseOverScript, webElement);
			log.info("In hover");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("OUT hover");
		}else{
			textHover();
		}
		String hex = Color.fromString(webElement.getCssValue("color")).asHex();
		return hex;
	}

	public String textTitle() {
		textHover();
		String titleText = webElement.getAttribute("title");
		return titleText;
	}

	public String borderColor() {
		textHover();
		String hex = Color.fromString(webElement.getCssValue("color")).asHex();
		return hex;
	}

}
