package com.v2solutions.taf.core;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DBPropertyFile  {

	final static String DBType = "DBTYPE";
	final static String DBConnectionString = "DBCONNECTION_STRING";
	final static String URL = "URL";
	final static String USER_NAME = "USER_NAME";
	final static String PASSWORD = "PASSWORD";
	final static String QUERY= "QUERY";
	final static String DBCOLUMN = "DBCOLUMN";
	static Properties prop = null;
	final static String dbConfigPath =  System.getProperty("user.dir")+"//src//test//resources//database.properties";
	
// InputStream input = ReadDBPropertyFile.class.getClassLoader().getResourceAsStream("src/database_sample.properties");
	InputStream input = new FileInputStream(dbConfigPath); 	 	 	
	public DBPropertyFile() throws IOException 
	{
		prop = new Properties();
		prop.load(input);
		
	}
	public String getDBType()
	{
		return prop.getProperty(DBType);

	}
	
	public String getConnectionString()
	{
		return prop.getProperty(DBConnectionString);
	}

	public String getUrl()
	{
		return prop.getProperty(URL);
	}

	public String getUsername()
	{
		return prop.getProperty(USER_NAME);

	}


	public String getPassword()
	{
		return prop.getProperty(PASSWORD);
	}

	public String getQuery()
	{
		return prop.getProperty(QUERY);
	}

	public String[] getColumn()
	{

		String[] dbColumns = prop.getProperty(DBCOLUMN).split(",");
		for (String w : dbColumns) {
			prop.getProperty(DBCOLUMN);
		}

		return dbColumns;
	}
}