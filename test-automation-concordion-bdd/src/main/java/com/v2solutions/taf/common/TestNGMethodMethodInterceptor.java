package com.v2solutions.taf.common;

/**
 * This class is method interceptor and act as more like method filter.
 * To execute single method of TestNG script test class, all other methods need to be filter out.
 * This class does same by keeping only passed of method name and removing all other from list.
 * This class is used in BDDTestNGBaseClass base class
 */
import org.testng.IMethodInstance;
import org.testng.ITestContext;

public class TestNGMethodMethodInterceptor implements org.testng.IMethodInterceptor {

	String MethodName = null;
	
	/**
	 * Empty constructor
	 */
	public TestNGMethodMethodInterceptor(){}
	
	/**
	 * Constructor which takes method name which required to keep and filter out all other methods of
	 * TestNG script test class
	 * 
	 * @param pMethodName
	 */
	public TestNGMethodMethodInterceptor(String pMethodName)
	{
		MethodName = pMethodName;
	}
	
	/**
	 * Helper method, is constructed with empty constructor, filter method name can be set using this method
	 * @param pMethodName
	 */
	public void setMethodName(String pMethodName)
	{
		MethodName = pMethodName;
	}
	
	/**
	 * This method is called by TestNG internally to filter out methods. This method removes all other method
	 * from list and return list contaning only method name which we want to call
	 */
	public java.util.List<IMethodInstance> 	intercept(java.util.List<IMethodInstance> methods, ITestContext context) 
	{
		java.util.List<IMethodInstance> newMethods = new java.util.ArrayList<IMethodInstance>();
		for(int i=0;i < methods.size();i++)
		{
			if( methods.get(i).getMethod().getMethodName().equals(MethodName) == true )
			{	
				newMethods.add(methods.get(i));
				break;
			}
		}
		return newMethods;
	}
}
