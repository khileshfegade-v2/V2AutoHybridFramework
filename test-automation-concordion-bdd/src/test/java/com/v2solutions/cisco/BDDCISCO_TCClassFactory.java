package com.v2solutions.cisco;

/**
*	This class is generated with code generating utility. 
*	Purpose of this class when using TestNG API through Concordion is to provide single instance of test class
*	instead of multiple (one instance per method test). This class is generated per TestNG class and kept
*	in respective folder. TestNG API's provide @Factory annotation which provides means for feeding test classes
*	to TestNG runner for test execution.
*	
*	Generation Date: 02/06/2017
*/

import org.testng.annotations.*;

public class BDDCISCO_TCClassFactory 
{
	static test.v2solutions.cisco.TestCISCO_TC temp = new test.v2solutions.cisco.TestCISCO_TC();

	@Factory
	public java.lang.Object[] getObjects()
	{
		return new Object[]{temp};
	}
}
