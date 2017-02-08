package com.v2solutions.components;

/**
*	This class is generated with code generating utility. 
*	Purpose of this class when using TestNG API through Concordion is to provide single instance of test class
*	instead of multiple (one instance per method test). This class is generated per TestNG class and kept
*	in respective folder. TestNG API's provide @Factory annotation which provides means for feeding test classes
*	to TestNG runner for test execution.
*	
*	Company: v2solutions
*	Author: v2Solutions
*	Generation Date: 01/31/2017
*/

import org.testng.annotations.*;

public class TestGlobalFooterSectionClassFactory 
{
	static test.v2solutions.components.TestGlobalFooterSection temp = new test.v2solutions.components.TestGlobalFooterSection();

	@Factory
	public java.lang.Object[] getObjects()
	{
		return new Object[]{temp};
	}
}
