/*
* Copyright 2017 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

package com.psa.library;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.psa.pages.ObjectReference;
import com.psa.data.GenericData;
import com.sun.mail.smtp.SMTPSendFailedException;

public class CustomListener extends TestListenerAdapter implements ISuiteListener {

	// ~ Instance fields ------------------------------------------------------

	static ReadXmlData rxml = new ReadXmlData();
	public static ResourceBundle _prop = ResourceBundle.getBundle("dev");
	public static String mode = _prop.getString("mode");
	public static int countFailed = 0;
	public static int countPassed = 0;
	public static int countSkipped = 0;
	public static int countErrored = 0;
	public static String testDescription;
	public static ArrayList<String> modules = new ArrayList<String>();
	public static ArrayList<String> scripts = new ArrayList<String>();
	public static ArrayList<String> cind = new ArrayList<String>();
	public static ArrayList<String> FailedArrayScreenhot = new ArrayList<String>();
	public String testSummary;
	public String methodName;
	public static String ScreenshotPath;
	static long totalTime;
	public String pattern = "dd/MM/yyyy HH:mm:ss SS";
	public SimpleDateFormat format = new SimpleDateFormat(pattern);
	public static String emailSender = rxml.data("emailSender");
	public static String emailPassword = rxml.data("emailPassword");
	static String email_location = _prop.getString("email_location");
	static String extentreport_location = _prop.getString("email_location");
	
	static String projectName = new ObjectReference().projectName;
	static String dateName = new SimpleDateFormat("EEE, d MMM yyyy").format(new Date());
	String htmlFileName = "Test-Report.html";
	static String testLogsFileName = "test-automation.log";
	static String standalone = "standalone";
	static String standaloneEmailLoc = "standaloneEmail_location";
	DateFormat dateformate = new SimpleDateFormat("dd-mm-yyyy_hh-mm");
	Date date = new Date();
	String currentdate = dateformate.format(date);
	public String screenshot = _prop.getString("screen_shot_location");

	public static Map<String, String> inlineImages = new HashMap<String, String>();
	static int totalTests;

	@SuppressWarnings("static-access")
	String url = new TestInitReference().url;
	
	// ~ Methods --------------------------------------------------------------

	/**
	 * Get test description of failed tests
	 * 
	 * @return List of failed test: Test Scenario, Method Name, Screenshot
	 * @throws Exception
	 */
	public String getTestDescription() {
		StringBuilder builder = new StringBuilder();
		for (String value : scripts) {
			builder.append(value);
		}
		String text = builder.toString();
		if (countFailed > 0) {
			return "<strong><center>LIST OF  FAILED TESTS:</center></strong> <br /> \n"
					+ "<table style='width:100%'> <tr>\n"
					// " <th>Method Name</th>\n" +
					+ "<th>Test Scenario</th>\n"
					+ "<th>Screenshot   <br /> <span style='font-weight:normal'>(tap image to view in fullscreen)</span></th>\n"
					+ "</tr>" + text + "</table>";
		} else {
			return "";
		}
	}

	/**
	 * Get screenshots of failed tests
	 * 
	 * @return text
	 * @throws Exception
	 */
	public String getPictureId() {
		StringBuilder builder = new StringBuilder();
		for (String value : cind) {
			builder.append(value);
		}
		String text = builder.toString();
		return text;
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String cidRand = new FunctionReference().randomString(5);
		try {
			ScreenshotPath = FunctionReference.getScreenshot(DriverManager.getDriver(), result.getName());
			FunctionReference.storeScreenshotInfo(result, ScreenshotPath);
			cind.add(cidRand);
			inlineImages.put(getPictureId(), ScreenshotPath);
			
			testDescription = Results.getDescription();

			scripts.add("<center><tr>"
					// "<td>" + methodName + "</td> \n"
					+ "<td>" + testDescription + "</td> \n " + "<td width='20%'>" + "<a href='cid:" + getPictureId()
					+ "' download> <img src=\"cid:" + getPictureId()
					+ "\" alt=\"screenshot\" width=\"15%\" height=\"15%\"> </a> " + "</td> \n " + "</tr></center>");
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		FailedArrayScreenhot.add(screenshot + methodName + "_" + currentdate + ".png");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("[PASSED] METHOD NAME: "+ result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			new ExtentManager().getDependsOnResult(result);
		}  catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	/**
	 * Generates total test (Passed, Failed, Skipped)
	 * 
	 * @return totalTests
	 */
	public int getTotalTests() {
		totalTests = countPassed + countFailed + countErrored + countSkipped;
		return totalTests;
	}

	/**
	 * Generates total test percentage (Passed, Failed, Skipped)
	 *
	 * @return totalPercentage
	 */
	public String getPercentage(ObjectReference.ResultCount results) {
		switch (results) {
		case PASS:
			return Integer.toString((int)Math.round(countPassed * 100.0 / getTotalTests()));
		case FAIL:
			return Integer.toString((int)Math.round(countFailed * 100.0 / getTotalTests()));
		case ERROR:
			return Integer.toString((int)Math.round(countErrored * 100.0 / getTotalTests()));
		case SKIP:
			return Integer.toString((int)Math.round(countSkipped * 100.0 / getTotalTests()));
		default:
			return "0";
		}
	}

	/**
	 * Generates Email Subject (Passed, Failed, Skipped)
	 * 
	 * @return emaiLSubject
	 */
	public static String getEmailSubject() {
		String emailSubject;
		
		if (countFailed > 0 || countPassed == 0) {
			emailSubject = projectName + ": Automation Test Result - " + dateName + " - FAILED";
		} else {
			emailSubject = projectName + ": Automation Test Result - " + dateName + " - PASSED";
		}
		return emailSubject;
	}


	/** Cleans screenshot folder before starting the test report */
	@Override
	public void onStart(ISuite suite) {
		
		@SuppressWarnings("unused")
		String extentscreenshot_location = _prop.getString("relative_extent_screenshot_location");
		
		if (mode.equals(standalone)) {
			extentscreenshot_location = _prop.getString("standaloneextentscreenshot_location1");
		}
		try {

			for (String screenshotfailed : FailedArrayScreenhot) {
				File file = new File(screenshotfailed);
				file.delete();
			}

		} catch (Exception e) {
			Log.error(e.getMessage());
			throw e;
		}
	}

	/** Generates the report after test */
	@Override
	public void onFinish(ISuite suite) {
		if (mode.equals(standalone)) {
			// do nothing
		}
		try {
			sendReport(suite);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
	}

	/** Sends the report */
	public void sendReport(ISuite suite) throws Exception {
		
	String sendEmailToList = new ObjectReference().sendEmailToList;

		if (mode.equals(standalone)) {
			email_location = _prop.getString(standaloneEmailLoc);
		}
		send("automation.ci@redflaggroup.com", sendEmailToList, suite,
				inlineImages);
		new FunctionReference().ifFileDownloadedDelete(email_location, "screenshots.zip");
	}

	/**
	 * Generates email and attachments
	 *
	 */
	public void send(String from, String sendTo, ISuite suite, Map<String, String> mapInlineImages) throws Exception {

		if (mode.equals(standalone)) {
			extentreport_location = _prop.getString("standaloneextentconfig_location");
			email_location = _prop.getString(standaloneEmailLoc);
		}

		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// Get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// Set sender email address and password
				return new PasswordAuthentication(emailSender, emailPassword);
			}
		});

		// Compose message
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));

			String toList = sendTo; // set e-mail recipients here.
			String[] to = toList.split(",");
			InternetAddress[] addresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addresses[i] = new InternetAddress(to[i]);
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject(getEmailSubject());
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// Time taken
			for (ISuiteResult eachResult : suite.getResults().values()) {
				ITestContext ctx = eachResult.getTestContext();
				Date start = ctx.getStartDate();
				Date end = ctx.getEndDate();
				long ms = end.getTime() - start.getTime();
				totalTime += ms;
				Log.info("Test started at [" + format.format(start) + "] and " + "ended at ["
						+ format.format(end) + "] and took [" + ms + "] ms to run.");
			}
			long second = (totalTime / 1000) % 60;
			long minute = (totalTime / (1000 * 60)) % 60;
			long hour = (totalTime / (1000 * 60 * 60)) % 24;
			String time = String.format("%02dh:%02dm:%02ds", hour, minute, second);
			Log.info("Total Test Time Taken = " + time);

			messageBodyPart.setContent(
					"<style> table, th, td { border: 1px solid black; border-collapse: collapse;} </style>"
							+ "<h3><center>PSA TEST REPORT</center></h3><br /> "
							+ "<strong>Test run completed on URL: &nbsp; </strong>" + url + "<br /> <br /> "

							+ "<strong>" + "TEST REPORT SUMMARY: </strong>" + "<ul style='list-style-type:circle'> "
							+ "<li> <strong> <font color='00cc44'>" + getPercentage(ObjectReference.ResultCount.PASS)
							+ "% PASSED: </font> </strong> &nbsp;" + "	" + countPassed + " test/s </li>"
							+ "<li> <strong> <font color='#ff0000'>" + getPercentage(ObjectReference.ResultCount.FAIL)
							+ "% ASSERTION FAILED: </font> </strong> &nbsp; &nbsp;" + "	" + countFailed + " test/s </li>"
							+ "<li><strong><font color='#ff6347'>"+ getPercentage(ObjectReference.ResultCount.ERROR)
							+ "% THREW AN ERROR: </font> </strong> &nbsp; &nbsp;" + "	" + countErrored + " test/s </li>"
							+ "<li> <strong> <font color='ff8000'>" + getPercentage(ObjectReference.ResultCount.SKIP)
							+ "% SKIPPED: </font> </strong> &nbsp; " + "	" + countSkipped + " test/s </li>"
							+ "<li> <strong>Total Number of Tests Executed:</strong> &nbsp; " + "	" + totalTests
							+ " count/s </li>" + "<li><strong>Test Duration Time:</strong> &nbsp;" + time
							+ "<br /></li> </ul>" + TestInitReference.getKlovReportId() + getTestDescription()
							+ "<br />" + "<strong> NOTES: </strong>"
							+ "<p> Please contact the assigned SDET of this product for questions and validation.</p>"
							+ GenericData.PSA_POC
							+ "<br /> <br /> © 2018 The Red Flag Group | Software Development",
					"text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();

			if (mapInlineImages != null && mapInlineImages.size() > 0) {
				Set<String> setImageID = mapInlineImages.keySet();

				for (String contentId : setImageID) {
					MimeBodyPart imagePart = new MimeBodyPart();
					imagePart.setHeader("Content-ID", "<" + contentId + ">");
					imagePart.setDisposition(MimeBodyPart.INLINE);

					String imageFilePath = mapInlineImages.get(contentId);
					try {
						imagePart.attachFile(imageFilePath);
					} catch (IOException ex) {
						Log.error(ex.getMessage());
					}

					multipart.addBodyPart(imagePart);
				}
			}

			@SuppressWarnings("unused")
			String xscreen_shot_location = _prop.getString("rootxtent_screenshot_location");
			
			if (mode.equals(standalone)) {
				xscreen_shot_location = _prop.getString("standaloneextentscreenshot_location");
				email_location = _prop.getString(standaloneEmailLoc);
			}

			String file1 = extentreport_location + "extentReport.html";

			FunctionReference.addAttachment(multipart, file1, htmlFileName);
			FunctionReference.addAttachment(multipart, new TestInitReference().logFile, testLogsFileName);

			message.setContent(multipart);

			try {
				Transport.send(message);
			} catch (SMTPSendFailedException e) {
				MimeMessage messageException = new MimeMessage(session);
				messageException.setFrom(new InternetAddress(from));
				messageException.setRecipients(Message.RecipientType.TO, addresses);
				messageException.setSubject(getEmailSubject());

				MimeBodyPart messageBodyPartException = new MimeBodyPart();
				messageBodyPartException.setContent(
						"<style> table, th, td { border: 1px solid black; border-collapse: collapse;} </style>"
								+ "<h3><center>PSA TEST REPORT</center></h3><br /> "
								+ "<strong>Test run completed on URL: &nbsp; </strong>" + url + "<br /> <br /> "
								+ "<strong>" + "TEST REPORT SUMMARY: </strong>" + "<ul style='list-style-type:circle'> "
								+ "<li> <strong> <font color='00cc44'>" + getPercentage(ObjectReference.ResultCount.PASS)
								+ "% PASSED: </font> </strong> &nbsp;" + "	" + countPassed + " test/s </li>"
								+ "<li> <strong> <font color='#ff0000'>" + getPercentage(ObjectReference.ResultCount.FAIL)
								+ "% ASSERTION FAILED: </font> </strong> &nbsp; &nbsp;" + "	" + countFailed + " test/s </li>"
								+ "<li><strong><font color='#ff6347'>"+ getPercentage(ObjectReference.ResultCount.ERROR)
								+ "% THREW AN ERROR: </font> </strong> &nbsp; &nbsp;" + "	" + countErrored + " test/s </li>"
								+ "<li> <strong> <font color='ff8000'>" + getPercentage(ObjectReference.ResultCount.SKIP)
								+ "% SKIPPED: </font> </strong> &nbsp; " + "	" + countSkipped + " test/s </li>"
								+ "<li><strong>Total Number of Tests Executed:</strong> &nbsp; " + "	" + totalTests
								+ " count/s </li>" + "<li><strong>Test Duration Time:</strong> &nbsp;" + time
								+ "</ul> <br />" + TestInitReference.getKlovReportId()
								+ "<strong>  NOTICE TO THE TEAM:</strong><br />"
								+ "There were lots of Unusual Failed Issues that was screen captured which causes us to reached the limit size "
								+ "attaching screenshots. As a result of this, we had to detached the attachment of failed screenshots,"
								+ " and just give you the full test report summary while we are investigating the issues. &nbsp;&nbsp;&nbsp; -Automation Team"
								+ "<br /> <br /> "
								+ "<p> Should you have any questions, Please contact the assigned SDET of this product.</p>"
								+ GenericData.PSA_POC
								+ "<br /> <br /> © 2018 The Red Flag Group | Software Development",
						"text/html; charset=utf-8");

				Multipart multipartException = new MimeMultipart();
				multipartException.addBodyPart(messageBodyPartException);

				messageBodyPartException = new MimeBodyPart();
				FunctionReference.addAttachment(multipartException, file1, htmlFileName);
				FunctionReference.addAttachment(multipartException, new TestInitReference().logFile, testLogsFileName);

				messageException.setContent(multipartException);
				Transport.send(messageException);
			}
			Log.info("Test Report has been sent successfully.");
		} catch (MessagingException e) {
			Log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Generates email if unable to access server
	 * 
	 * @param from email sender
	 * @param sendTo email send to 
	 */
	public static void sendEmail(String from, String sendTo) throws Exception {

		if (mode.equals(standalone)) {
			extentreport_location = _prop.getString("standaloneextentconfig_location");
			email_location = _prop.getString(standaloneEmailLoc);
		}

		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		// Get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// Set sender email address and password
				return new PasswordAuthentication(emailSender, emailPassword);
			}
		});

		// Compose message
		try {

			String toList = sendTo; // set e-mail recipients here.
			String[] to = toList.split(",");
			InternetAddress[] addresses = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++)
				addresses[i] = new InternetAddress(to[i]);

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, addresses);
			message.setSubject(CustomListener.getEmailSubject());

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					"<style> table, th, td { border: 1px solid black; border-collapse: collapse;} </style>"
							+ "<h3><center>PSA TEST REPORT</center></h3> <br />" + "<h5><center>"
							+ TestInitReference.reportContent + "</center></h5><br /> "
							+ "<strong>  NOTICE TO THE TEAM:</strong><br />"
							+ "<p> Should you have any questions, Please contact the assigned SDET of this product.</p>"
							+ GenericData.PSA_POC
							+ "<br /> <br /> © 2018 The Red Flag Group | Software Development",
					"text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			FunctionReference.addAttachment(multipart, new TestInitReference().logFile, testLogsFileName);

			message.setContent(multipart);

			Transport.send(message);
			Log.info("Test Report has been sent successfully.");
		} catch (MessagingException e) {
			Log.error(e.getMessage());
			throw e;
		}
	}

}
