package com.v2solutions.pages;

/**
*	This class is generated with code generating utility. 
*	Purpose of this class when using TestNG API through Concordion is to provide single instance of test class
*	instead of multiple (one instance per method test). This class is generated per TestNG class and kept
*	in respective folder. TestNG API's provide @Factory annotation which provides means for feeding test classes
*	to TestNG runner for test execution.
*	
*	Company: v2solutions
*	Author: v2Solutions
*	Generation Date: 02/06/2017
*/

import org.testng.annotations.*;

public class TestV2SolutionsHomePageClassFactory 
{
	static test.v2solutions.pages.TestV2SolutionsHomePage temp = new test.v2solutions.pages.TestV2SolutionsHomePage();

	@Factory
	public java.lang.Object[] getObjects()
	{
		return new Object[]{temp};
	}
}
