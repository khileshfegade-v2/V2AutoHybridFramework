package com.v2solutions.taf.common;

import java.io.IOException;
import java.io.OutputStream;

import org.concordion.ext.ScreenshotTaker;
import org.concordion.ext.ScreenshotUnavailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class is custom screen shot taker. Method of this class is called from BDDTestNGBaseClass.printMethodResult
 * method to take screen shot.Actual screen shot is saved by Concordon to appropriate file name
 * 
 * @author MahendraK
 *
 */
public class SeleniumScreenshotTaker implements ScreenshotTaker {

    private  WebDriver driver = null;
	private boolean isLocal = true;
	
	public SeleniumScreenshotTaker(){}
	
	/**
	 * Method to set run location, local on developers machine or remote on BrowserStack
	 * Required to take screen shots using web driverdepending on location
	 * @param pIsLocal
	 */
	public void setLocal(boolean pIsLocal)
	{
		isLocal = pIsLocal;
	}
	
	/**
	 * This method set the web driver required to take screen shot. Web driver instance is extracted from 
	 * TestNG script test class 
	 * 
	 * @param pDriver
	 */
    public void setDriver(WebDriver pDriver) 
	{
        WebDriver baseDriver = pDriver;
        while (baseDriver instanceof EventFiringWebDriver) 
		{
            baseDriver = ((EventFiringWebDriver)baseDriver).getWrappedDriver();
        }
        this.driver = baseDriver;
    }

    /**
     * This method casts the driver instance to appropriate screen shot taker and return the length of
     * the image in bytes to Concordion
     */
    @Override
    public int writeScreenshotTo(OutputStream outputStream) throws IOException 
	{
        byte[] screenshot;
		
		try
		{
			if(driver == null ) return 0;
						
			try 
			{				
				
				if(isLocal == false) 
				{				
					if(driver instanceof FirefoxDriver) 
					{
						screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
					}
					else 
					{
						WebDriver augmentedDriver = new Augmenter().augment( driver );
						screenshot = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.BYTES);
					}
				}
				else					
					screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				
			} 
			catch (ClassCastException e) 
			{
				throw new ScreenshotUnavailableException("driver does not implement TakesScreenshot");
			}
			
			outputStream.write(screenshot);
			outputStream.flush();
			
			int returnValue =  ((Long)((JavascriptExecutor)driver).executeScript("return document.body.clientWidth")).intValue() + 2; //window.outerWidth"));
						
			return returnValue;
		}
		catch(Exception  exp)
		{
			System.out.println("Exception occured ********************************************* "+exp.getMessage());
			exp.printStackTrace();
		}
		return 0;
    }

    @Override
    public String getFileExtension() {
        return "png";
    }
}