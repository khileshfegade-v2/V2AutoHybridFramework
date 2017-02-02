package com.v2solutions.taf.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MailUtil  {

	final static String contentHtml = "contentHtml";
	final static String fromEmailAddress = "fromEmailAddress";
	final static String fromEmailPwd = "fromEmailPassword";
	final static String contentHost = "contentHost";
	final static String toAddr = "toAddr";
	final static String ccAddr= "ccAddr";
	static Properties prop = null;
	
	InputStream input = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//"+"mail.properties"); 	 	 	
	public MailUtil() throws IOException 
	{
		prop = new Properties();
		prop.load(input);
		
	}
	public String getContentHtml()
	{
		return prop.getProperty(contentHtml);

	}
	
	public String getFromEmailAddress()
	{
		return prop.getProperty(fromEmailAddress);
	}

	public String getFromEmailPwd()
	{
		return prop.getProperty(fromEmailPwd);
	}

	public String getContentHost()
	{
		return prop.getProperty(contentHost);

	}

	public String[] getToAddr()
	{

		String[] receiverId = prop.getProperty(toAddr).split(",");
		for (String w : receiverId) {
			prop.getProperty(toAddr);
		}

		return receiverId;
	}
	
	public String[] getCCAddr()
	{

		String[] ccID = prop.getProperty(ccAddr).split(",");
		for (String w : ccID) {
			prop.getProperty(ccAddr);
		}

		return ccID;
	}
}