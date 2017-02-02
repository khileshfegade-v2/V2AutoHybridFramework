package com.v2solutions.taf.common;

import org.testng.*;

/**
 * This class required to put in project to get the IConfiguration object from TestNG framework. 
 * Because getConfiguration() method is declared protected which means only extending or deriving class
 * can call it and no other java code can get it
 * @author MahendraK
 *
 */
public class ExtendedTestNG extends TestNG
{
	public org.testng.internal.IConfiguration getConfiguration()
	{
		return super.getConfiguration();
	}
}
