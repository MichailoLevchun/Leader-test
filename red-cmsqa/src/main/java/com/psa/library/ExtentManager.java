/*
* Copyright 2017 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

package com.psa.library;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.KlovReporter;
import com.psa.pages.ObjectReference;


public class ExtentManager extends TestInitReference {

	// VARIABLES
	private static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports();
		}
		return extent;
	}

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (Thread.currentThread().getId()));
	}

	public static synchronized ExtentTest startTest(String testName) {
		test = extent.createTest(testName);
		extentTestMap.put((int) (Thread.currentThread().getId()), test);
		return test;
	}

	
	@AfterMethod
	public void getResult(ITestResult testResult) throws Exception {
		
		String currentURL = driver().getCurrentUrl();
		String[] groupsList = testResult.getMethod().getGroups();
		String groups = Arrays.toString(groupsList).replace("[", "&bull; &nbsp;").replace(",", "&nbsp;|&nbsp;").replace("]", "").trim();
		
		try {
			getTest().assignCategory(groups);
			switch (testResult.getStatus()) {
			case ITestResult.SUCCESS:
				CustomListener.countPassed++;
				getTest().pass("<b><font size=\"+1\"color=\"green\">[PASS] TESTCASE IS PASSED.</b> <br /> </font>")
				 .pass("<b>TEST CLASS NAME:</b>&nbsp;" + testResult.getInstanceName()
				+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + testResult.getName());
				break;
			case ITestResult.FAILURE:
				String stackTraceList = Arrays.toString(testResult.getThrowable().getStackTrace()).replace("[", "at&nbsp;").replace(",", "<br/>at&nbsp;").replace("]", "").trim();
				String stackTrace = testResult.getThrowable().toString();
				if (stackTrace.contains("NullPointerException") || stackTrace.contains("ArrayIndexOutOfBound")
						|| stackTrace.contains("IllegalArgumentException")
						|| stackTrace.contains("IllegalStateException") || stackTrace.contains("ArithmaticException")) {
					CustomListener.countErrored++;
					getTest().error("<b><font size='+1' color='#ff6347'>[ERROR] EXCEPTION HAS OCCURED.</font></b>")
							.error("<b>TEST CLASS NAME:</b>&nbsp;" + testResult.getInstanceName()
									+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + testResult.getName())
							.error(testResult.getThrowable() + "<br/>" + stackTraceList);
				} else {
					CustomListener.countFailed++;
					getTest().fail("<b><font size='+1' color='#F7464A'>[FAIL] ASSERTION HAS FAILED.</font></b>")
							 .fail(testResult.getThrowable())//.addScreenCaptureFromPath(FunctionReference.getScreenShotInfo(testResult))
							 .fail("<b>TEST CLASS NAME:</b>&nbsp;" + testResult.getInstanceName()
							+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + testResult.getName())
							 .fail("<b> <font color='#F7464A'>" + getFailureMsg() + "</font> </b> <br/>" + testResult.getThrowable() + "<br/>" + stackTraceList);
				}
				getTest().info("<b>URL TO WHERE THE TEST FAILED:<b/><br />"
						+ "<i><strong><font color=\"blue\"> <span title=\"Click the link to open in new tab\"><a href="
						+ currentURL + " target=\"_blank\">" + currentURL + "</a> </span></i></strong></font>");
				if (mode.equals("local") || klovReporterEnabled) {
					getTest().fail("<b><font size='+1' color='#F7464A'>SEE SCREENSHOT BELOW:</font></b>")
							 .addScreenCaptureFromPath(FunctionReference.getScreenShotInfo(testResult));
				}
				break;
			case ITestResult.SKIP:
				CustomListener.countSkipped++;
				getTest().skip("<b><font size='+1' color='#FF7F00'>[SKIPPED] SKIP EXCEPTION HAS OCCURED.<br /></font></b>")
						 .skip(testResult.getThrowable().getMessage())
						 .skip("<b>TEST CLASS NAME:</b>&nbsp;" + testResult.getInstanceName()
						+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + testResult.getName());
				break;
			default:
				getTest().info("Unknown Result");
				break;
			}
		} catch (Error | Exception e) {
			klovReporterEnabled = false;
			e.printStackTrace();
			throw (e);
		}
	}
	
	public void getDependsOnResult(ITestResult result) throws Exception {
		String testDescription = result.getMethod().getDescription();
		String errorMessage = result.getThrowable().getMessage();
		String skipMethod = "[SKIPPED] DEPENDS ON NOT SUCCESSFULLY FINISHED METHOD:";
		String skipGroup = "[SKIPPED] DEPENDS ON NOT SUCCESSFULLY FINISHED METHODS IN GROUP ";
		String[] methStr = result.getMethod().getMethodsDependedUpon();
		String methodsDependedUpon = Arrays.toString(methStr).replace("[", "<br /> - ").replace(",", "<br /> -").replace("]", "").trim();
		String[] groupsList = result.getMethod().getGroups();
		String groups = Arrays.toString(groupsList).replace("[", "&bull; &nbsp;").replace(",", "&nbsp;|&nbsp;").replace("]", "").trim();
		try {
			if (errorMessage.matches("(.*)depends on not successfully finished methods") == true) {
				startTest(testDescription);
				CustomListener.countSkipped++;
				getTest().assignCategory(groups);
				getTest().skip("<b><font size='+1' color='#FF7F00'>" + skipMethod
						+ "</font> <font size='+1'>" + methodsDependedUpon + "</font>" + "<br /></b>")
					 	 .skip("<b>TEST CLASS NAME:</b>&nbsp;" + result.getInstanceName()
						+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + result.getName());
			} else if (errorMessage.matches("(.*)depends on not successfully finished methods in group(.*)") == true) {
				String groupDependedName = errorMessage.split("[\"\")]")[2];
				startTest(testDescription);
				CustomListener.countSkipped++;
				getTest().assignCategory(groups);
				getTest().skip("<b><font size='+1' color='#FF7F00'>" + skipGroup + "'" + groupDependedName
						+ "'.<br /></font></b>")
						 .skip("<b>TEST CLASS NAME:</b>&nbsp;" + result.getInstanceName()
						+ "&emsp;&emsp;&emsp;&emsp;<b>TEST METHOD NAME:</b>&nbsp;" + result.getName());
			}
		} catch (Error | Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
	
	public void startReport() {
		String extentconfig_location = _prop.getString("extentconfig_location");
		String extentreport_location = _prop.getString("email_location");
		
		String mode = _prop.getString("mode");
		if (mode.equalsIgnoreCase("standalone")) {
			extentconfig_location = _prop.getString("standaloneextentconfig_location");
			extentreport_location = _prop.getString("standaloneextentconfig_location");
		}

		htmlReporter = new ExtentHtmlReporter(extentreport_location + "extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", url);
		extent.setSystemInfo("Java Version", "1.8.0_131");
		extent.setSystemInfo("TestNG Version", "6.10.0");
		extent.setSystemInfo("Selenium Version", "3.12.0");
		htmlReporter.loadXMLConfig(extentconfig_location + "extent-config.xml");

		// Define Klov Reporter
		if (klovReporterEnabled) {
			try {
				klovReporter = new KlovReporter();
				klovReporter.initMongoDbConnection(ObjectReference.klovReporterDB, Integer.parseInt(klovReporterDBPort));
				klovReporter.setProjectName(klovReporterProjectName);
				klovReporter.setReportName("Nightly Build");
				klovReporter.setKlovUrl(ObjectReference.klovReporterURL);
				extent.attachReporter(htmlReporter, klovReporter);
			} catch (Exception ex) {
				Log.error(ex.getMessage());
			}
		}
	}
	
	public void endReport() throws IOException{
		try {
			File folder = new File(email_location);
			File extent = new File(email_location + "extentReport.html");
			if (!folder.exists()) {
				folder.mkdir();
				if (!extent.exists()) {
					extent.createNewFile();
				}
			} else {
				// do nothing, folder and files exist
			}

		} catch (Error | Exception e) {
			e.printStackTrace();
			throw (e);
		}
    }	
	
	public void finishReport() {
		getReporter().flush();
	}
	
}