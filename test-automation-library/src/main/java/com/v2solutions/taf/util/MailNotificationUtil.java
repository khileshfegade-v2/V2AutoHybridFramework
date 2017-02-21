package com.v2solutions.taf.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.v2solutions.taf.TestBedManager;
import com.v2solutions.taf.exception.ProfileBuilderException;

import javax.mail.internet.MimeMultipart;

public class MailNotificationUtil extends LatestReportsCopyAndZipUtil{
	MailUtil mailUtil;
	protected TestBedManager testBedManager = null;
	Properties prop = new Properties();
	InputStream input = null;
	
	public void sendEmailNotification() throws MessagingException, IOException, ParseException, ProfileBuilderException {
		input = new FileInputStream(rootPath+"/src/test/resources/config.properties");
		
		// load a properties file
		prop.load(input);
		
		String body=null;
		mailUtil = new MailUtil();
		String mailSubjectLine="V2Auto Hybrid Framework: Execution Result";
		copyReports();
		newestFile();
		zippingFolder();



		@SuppressWarnings("rawtypes")
		ArrayList<String> fileName = new ArrayList<String>();
		System.out.println("Latest file name from executionreports folder - > "+Latestfile + ".zip");
		fileName.add(Latestfile+ ".zip");


		body = "This is an automated message. ***Do not reply.***<br /><br />" +""+
				"1. For Execution details, please find the	 attached Report. <br /> "+
				"2. Although you can find this report in our result repository which is @("+ execution_Reports_LogsPath+")<br /><br /><br />"+
				" Thanks, <br /> V2 Automation Team-QA";


		Message message = new MimeMessage(getSession());
		if((prop.getProperty("EMAIL_NOTIFICATION").equalsIgnoreCase("YES"))||(prop.getProperty("EMAIL_NOTIFICATION").equalsIgnoreCase("true"))){
			for (int i = 0; i < mailUtil.getToAddr().length; i++) {
				message.addRecipient(RecipientType.TO, new InternetAddress(mailUtil.getToAddr()[i]));
				System.out.println("Sending Mail to TO:" + mailUtil.getToAddr()[i]);
			}
			for (int i = 0; i < mailUtil.getCCAddr().length; i++) {
				message.addRecipient(RecipientType.TO, new InternetAddress(mailUtil.getCCAddr()[i]));
				System.out.println("Sending Mail to CC:" + mailUtil.getCCAddr()[i]);
			}
			message.addFrom(new InternetAddress[] { new InternetAddress(mailUtil.getFromEmailAddress()) });
			message.setSubject(mailSubjectLine);
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(body);
			mbp1.setContent(body, mailUtil.getContentHtml());
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			for (String file : fileName) {
				MimeBodyPart mbp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(file);
				mbp.setDataHandler(new DataHandler(fds));
				mbp.setFileName(fds.getName());
				mp.addBodyPart(mbp);
			}
			message.setContent(mp);
			message.setSentDate(new Date());
			Transport.send(message);
			System.out.println("Sent email successfully!!");
		}
		}
		private Session getSession() {
		Authenticator authenticator = new Authenticator();
		Properties properties = new Properties();

		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.host", mailUtil.getContentHost());
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		return Session.getInstance(properties, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			authentication = new PasswordAuthentication(mailUtil.getFromEmailAddress(), mailUtil.getFromEmailPwd());
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}


}
