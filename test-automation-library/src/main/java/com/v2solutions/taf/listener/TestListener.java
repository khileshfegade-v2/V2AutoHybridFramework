package com.v2solutions.taf.listener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */

public class TestListener extends TestListenerAdapter {
	
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	java.util.Date currentDateTime = null;
	
	
    @Override
    public void onTestFailure(ITestResult tr) 
    {
    	currentDateTime = new java.util.Date(); 
        log(tr.getTestClass()+"."+tr.getName()+ " --Test method failed "+df.format(currentDateTime)+"\n");
        currentDateTime = null;
    }
	 
    @Override
    public void onTestSkipped(ITestResult tr) 
    {
    	currentDateTime = new java.util.Date();
        log(tr.getTestClass()+"."+tr.getName()+ " --Test method skipped "+df.format(currentDateTime)+"\n");
        currentDateTime = null;
    }
	 
    @Override
    public void onTestSuccess(ITestResult tr)
    {
    	currentDateTime = new java.util.Date();
        log(tr.getTestClass()+"."+tr.getName()+ " --Test method success "+df.format(currentDateTime)+"\n");
        currentDateTime = null;
    }
	 
    private void log(String string) {
    	System.setOut(System.err);
        System.out.print(string);
    }
    
    @Override
    public void 	onTestStart(ITestResult tr)
    {
    	currentDateTime = new java.util.Date();
    	log(tr.getTestClass()+"."+tr.getName()+ " --Test method starting "+df.format(currentDateTime)+"\n");
    	currentDateTime = null;
    }
}
