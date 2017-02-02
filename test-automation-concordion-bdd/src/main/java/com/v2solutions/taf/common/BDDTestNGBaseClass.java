package com.v2solutions.taf.common;

/**
 * This is base class of all Concordion BDD test classes. This class encapsulates all the TestNG
 * API related functionality and provide minimum proxy code through for derived class.
 * This class initializes TestNG required objects and API and provide one method 'callMethod' for derived class to
 * call TestNG script projects test class method. Concordion BDD is run using the JUnit runner and hence uses
 * JUnit annotations. Importantly @Before which is used for TestNG initialization and @After to close the
 * browser by calling close method of TestNG script base class BaseTest.java.
 * Another important method in this class is 'printMethodResult'. This method checks the return from called
 * TestNG script test class and accordingly returns value (true) or throws excepton. Thrown exception
 * is caught by Concordion test runner and shown it result  
 */

import org.testng.*;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import org.junit.After;
import org.testng.xml.XmlClass;
import org.testng.SuiteRunner;
import org.testng.xml.XmlTest;
import org.testng.xml.XmlSuite;
import org.testng.TestRunner;
import java.util.*;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.Extension;
import org.concordion.ext.ScreenshotExtension;

public class BDDTestNGBaseClass
{
	/**
	 * Constructor of class. This class takes name of factory class wich will provide instance of
	 * actual Concordion BDD class. Each Concordion BDD class has corresponding Factory class. 
	 * In TestNG API, Factory class facility is used to provide or inject actual test class. This feature is used
	 * so that only one instance of TestNG script class is returned every time. Else for each method call
	 * of TestNG script class it will create new instance and call @BeforeClass method. This will result in
	 * opening browser , opening url and closing it in @AfterClasss. This will be very costly behavior for single method call.
	 * So to come around this problem, Factory class is used which provide single instance of TestNG script class
	 * as its declared static in factory class.
	 * 
	 * @param pFactoryClassName
	 */
	protected String actualTestNGClassName = null;
	
	public BDDTestNGBaseClass(String pFactoryClassName)
	{
		factoryClassName = pFactoryClassName;
	}

	/**
	 * This variable is custom screen shot taker
	 */
	private SeleniumScreenshotTaker screenshotTaker = new SeleniumScreenshotTaker();
	
	@Extension
    public ConcordionExtension extension = new ScreenshotExtension().setScreenshotTaker(screenshotTaker).setScreenshotOnAssertionSuccess(false);
	
	//web driver instance variable holder. Instance of it taken from TestNG script class
	WebDriver driver = null;
							
	String factoryClassName = null;

	ExtendedTestNG testng = null;
	
	List<XmlClass> testClasses = new ArrayList<XmlClass>();
	
	XmlClass OnetestClass = null;
	
	SuiteRunner suiteRunner =  null;
	
	XmlTest test = null;
	
	com.v2solutions.taf.common.TestNGMethodMethodInterceptor methodFilter = new com.v2solutions.taf.common.TestNGMethodMethodInterceptor();

	TestRunner methodTestRunner = null;
	java.util.List<java.lang.Class> listeners = null;
	XmlSuite oneSuite = null;
	List<XmlSuite> suites = null;
	
	/*
	 * Before method to initialize TestNG API and makes it ready to call actual TestNG script test class methods
	 */
	@Before
	public  void setup() 
	{ 					
		OnetestClass = new XmlClass(factoryClassName);
		
		testng = new ExtendedTestNG();
				
		listeners = new java.util.ArrayList<java.lang.Class>();
		
		listeners.add(com.v2solutions.taf.listener.LogErrorListener.class);
		listeners.add(com.v2solutions.taf.listener.SuiteListener.class);
		listeners.add(com.v2solutions.taf.listener.TestListener.class);		
		testng.setListenerClasses(listeners);
		
		oneSuite = new XmlSuite();
		oneSuite.setName("TmpSuite");
 
		test = new XmlTest(oneSuite);
		test.setName("TmpTest");
		
		testClasses.add(OnetestClass);
		test.setXmlClasses(testClasses) ;

		suites = new ArrayList<XmlSuite>();
		suites.add(oneSuite);
		
		testng.setXmlSuites(suites);
		
		suiteRunner = new SuiteRunner(testng.getConfiguration(), oneSuite, "./output");		
	}
	
	/**
	 * Important helper method. Derived class call this method by passing TestNG script test class method name as string
	 * This method also calls printMethodResult to report the result of TestNG script test method execution
	 * 
	 * @param pMethodName - TestNG script test class method name
	 * @return
	 * @throws Throwable
	 */
	public boolean callMethod(String pMethodName) throws Throwable
	{ 	
		methodFilter.setMethodName(pMethodName);
		methodTestRunner = new TestRunner(testng.getConfiguration(),suiteRunner,test,false,null);
		methodTestRunner.setMethodInterceptor(methodFilter);		
		methodTestRunner.run();		
		return printMethodResult(pMethodName,methodTestRunner);		
	} 
	
	/**
	 * Another important method which call the TestNG Script test class close method.
	 * close method is implemented in TestNG Script test class base class which is BaseTest.java
	 * 
	 * @throws Throwable
	 */
	@After
	public void tearDown()  throws Throwable
	{
		methodFilter.setMethodName("close");
		methodTestRunner = new TestRunner(testng.getConfiguration(),suiteRunner,test,false,null);
		methodTestRunner.setMethodInterceptor(methodFilter);
		methodTestRunner.run();
		
		//call finalize to release all variables
		finalize();
	}		
	
	/**
	 * This method return true of TestNG script test class method execution is success.
	 * In case of fail or skip, a exception is reported from TestNG script test class.
	 * This method simply forwards that exception by rethrowing it again.
	 * In case of fail or skip this method also takes the screen shot.
	 * The custom screen shot taker is initialized on first execution of this method.
	 * Custom screen shot taker can not be initialized in constructor as web driver instance is only
	 * available from TestNG script test class once @BeforeClass method is executed
	 * 
	 * @param pStep
	 * @param pMethodTestRunner
	 * @return
	 * @throws Throwable
	 */
	public boolean printMethodResult(String pStep,TestRunner pMethodTestRunner) throws Throwable
	{	
		String status = null;
		
		if( pMethodTestRunner == null )
		{
			return true;
		}
		
		if(  driver == null )
		{
			java.util.Collection<ITestClass> itestClasses = pMethodTestRunner.getTestClasses();
		
			Object array[] = itestClasses.toArray();
		
			for(int i=0;i<array.length;i++)			
			{
				if(((org.testng.ITestClass)array[i]).getName() != null && ((org.testng.ITestClass)array[i]).getName().indexOf("ClassFactory") > -1 )
					continue;

				Object instancesArray[] = ((org.testng.ITestClass)array[i]).getInstances(false);
				for(int j = 0; instancesArray != null && j < instancesArray.length;j++)
				{
					driver = ((com.v2solutions.common.BaseTest)instancesArray[j]).getDriver();
					if(driver != null )
					{
						screenshotTaker.setDriver(driver);
						screenshotTaker.setLocal(((com.v2solutions.common.BaseTest)instancesArray[j]).isLocal());
						
						((ScreenshotExtension)extension).setScreenshotTaker(screenshotTaker);
						((ScreenshotExtension)extension).setScreenshotOnAssertionSuccess(false);			
						actualTestNGClassName = instancesArray[j].getClass().getName();
						break;
					}
				}
				for(int j = 0; instancesArray != null && j < instancesArray.length;j++) instancesArray[j] = null;
				instancesArray = null;
			}
			
			try{itestClasses.clear();}catch(Exception exp){exp = null;}
			itestClasses = null;
			for(int i=0;i<array.length;i++) array[i] = null;
			array = null;
		}		
		
		
		org.testng.IResultMap tempmap[] = new org.testng.IResultMap[3];
		
		tempmap[0] = pMethodTestRunner.getSkippedTests() ;
		tempmap[1] = pMethodTestRunner.getFailedTests() ;
		tempmap[2] = pMethodTestRunner.getPassedTests();

		for(int i=0;i < 3;i++)
		{
			java.util.Set<org.testng.ITestResult> 	tempresult = tempmap[i].getAllResults() ;

			
			for (org.testng.ITestResult obj : tempresult) 
			{
				status = null;
				switch( obj.getStatus() )
				{
					case org.testng.ITestResult.FAILURE: status = "Failed";break; 
					case org.testng.ITestResult.SKIP : status = "Skipped";break;
					case org.testng.ITestResult.STARTED : status = "Started";break;
					case org.testng.ITestResult.SUCCESS : status = "Sucess";break;
					case org.testng.ITestResult.SUCCESS_PERCENTAGE_FAILURE : status = "Sucess percentage failure";break;
					default: status ="unknown";break;
				};
				
				System.out.println("[ Class : "+actualTestNGClassName+" ] [ Method: "+obj.getMethod().getMethodName() +" ] : "+status);
				
				if( obj.getThrowable() != null  )
				{				
					status = null;
					pMethodTestRunner = null;
					tempmap[0] = null;tempmap[1] = null;tempmap[2] = null;
					tempmap = null;
					throw obj.getThrowable();
				}
			}			
			try{tempresult.clear();}catch(Exception exp){exp = null;}
			tempresult = null;
		}
		pMethodTestRunner = null;
		status = null;
		tempmap[0] = null;tempmap[1] = null;tempmap[2] = null;
		tempmap = null;
		
		return true;
	}

	/**
	 * Finalize method to clear all used variables
	 */
	@Override
	public void finalize()
	{
		screenshotTaker = null;
		extension = null;
		driver = null;						
		factoryClassName = null;
		testng = null;
		try{testClasses.clear();}catch(Exception exp){exp = null;}
		testClasses = null;
		OnetestClass = null;
		suiteRunner =  null;
		test = null;
		methodFilter = null;
		try{listeners.clear();}catch(Exception exp){exp = null;}
		listeners = null;
		oneSuite = null;
		try{suites.clear();}catch(Exception exp){exp = null;}
		suites = null;
		actualTestNGClassName = null;
	}
}
