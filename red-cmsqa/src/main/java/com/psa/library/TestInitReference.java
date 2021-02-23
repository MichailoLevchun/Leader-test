/*
* Copyright 2018 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

 package com.psa.library;

 import java.lang.reflect.Method;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.*;

 import com.psa.pages.HomePage;
 import com.psa.utilities.AssociateProfileUtil;
 import com.psa.utilities.CommonUtil;
 import com.psa.utilities.EditCaseUtil;
 import com.psa.utilities.ExistingProfileUtil;
 import com.psa.utilities.HomePageUtils;
 import com.psa.utilities.LoginUtil;
 import com.psa.utilities.NavigateUtil;
 import com.psa.utilities.NewCaseUtil;
 import org.openqa.selenium.WebDriver;
 import org.testng.ITestResult;
 import org.testng.annotations.AfterMethod;
 import org.testng.annotations.AfterSuite;
 import org.testng.annotations.AfterTest;
 import org.testng.annotations.BeforeClass;
 import org.testng.annotations.BeforeMethod;
 import org.testng.annotations.BeforeSuite;
 import org.testng.annotations.Listeners;
 import org.testng.annotations.Test;
 import org.testng.log4testng.Logger;

 import com.aventstack.extentreports.ExtentReports;
 import com.aventstack.extentreports.ExtentTest;
 import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
 import java.lang.reflect.Constructor;

 import javassist.*;

 import org.testng.IAnnotationTransformer;
 import org.testng.annotations.ITestAnnotation;

@SuppressWarnings("unused")
@Listeners({CustomListener.class, CustomizedEmailableReport.class})
public class TestInitReference extends FunctionReference implements IAnnotationTransformer {

	protected static final Logger LOGGER = Logger.getLogger(TestInitReference.class);

    public String record = System.getenv("SHOULD_RECORD") != null ? System.getenv("SHOULD_RECORD") : rxml.data("record");
    public String remoteUrl = System.getenv("REMOTE_URL") != null ? System.getenv("REMOTE_URL") : rxml.data("remoteUrl");
    public static String url =  System.getenv("TEST_URL") != null ? System.getenv("TEST_URL") : rxml.data(
            "devSandboxURL");
    
    static DateFormat dateformate = new SimpleDateFormat("dd-mm-yyyy_hh-mm-ss");
    static Date date = new Date();
    static String currentdate = dateformate.format(date);
    protected String ipAddress;
    public static ResourceBundle _prop = ResourceBundle.getBundle("dev");
    public static String binaryRootFolder = _prop.getString("binaryRootFolder");
    public static String mode = _prop.getString("mode");
	public String email_location = _prop.getString("email_location");
	public  String testLog_location = _prop.getString("testLog_location");
	public String logFile =  testLog_location + "psa-test-automation.log";
	public String screen_shot_location = _prop.getString("screen_shot_location"); 
	public static String uploadFiles = _prop.getString("localUploadFiles_location");
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	static ClassPool getClassPool = ClassPool.getDefault();
	static PrintStream fileStream = null;
	static PrintStream outStream   = null;
    static OutputStream os = null;
    public String testngDescription;
    private static Map<Integer, String> testDescriptions = new HashMap<Integer, String>();

    public static AssociateProfileUtil associateProfileUtil = new AssociateProfileUtil();
    public static CommonUtil commonUtil = new CommonUtil();
    public static EditCaseUtil editCaseUtil = new EditCaseUtil();
    public static LoginUtil loginUtil = new LoginUtil();
    public static HomePageUtils homePageUtils = new HomePageUtils();

    public static NavigateUtil navigateUtil = new NavigateUtil();
    public static ExistingProfileUtil existingProfileUtil = new ExistingProfileUtil();
    public static NewCaseUtil newCaseUtil = new NewCaseUtil();

    @BeforeSuite(alwaysRun = true)
   	public void beforeSuite() throws Exception{

   		new ExtentManager().startReport();
   		
   		if (record.equalsIgnoreCase("Yes")) {
   			startRecording();
   		}
   		
		Log.info("Platform: " + System.getProperties().getProperty("os.name") + " "  + System.getProperties().getProperty("os.version") + " " + System.getProperties().getProperty("os.arch") + " ");
		Log.info("Test URL: " + url);
   	}
    
    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws Exception {
		Log.info("Current TestCase Class Running: " + this.getClass().getName());
		if (checkhttp.equalsIgnoreCase("Yes")) {
			checkHTTPStatus();
		}
	}

    @BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
        WebDriver driver = DriverManager.createDriver(browser);
		DriverManager.setWebDriver(driver);
		driver().get(url);
		
		Test testClass = method.getAnnotation(Test.class);
		testngDescription = testClass.description();
		Log.info("Method name: " + method.getName());
		Log.info("Test scenario name: " + testngDescription);
		test = ExtentManager.startTest(testngDescription);
		setTestDescription(testngDescription);
	}
    
    @AfterTest(alwaysRun = true)
	public void afterTest() throws IOException {
		new ExtentManager().endReport();
    }	
    
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) throws Exception{
		new ExtentManager().getResult(result);
		new DriverManager().removeDriver();
	}
	
    @AfterSuite(alwaysRun = true)
	public void afterSuite() throws Exception {
		if (record.equalsIgnoreCase("Yes")) {
			stopRecording();
		}
		new ExtentManager().finishReport();
	}
    
    public synchronized String getTestDescription() {
		return (String) testDescriptions.get((int) (long) (Thread.currentThread().getId()));
	}
	
	public synchronized void setTestDescription(String testName) {
		testDescriptions.put((int) (long) (Thread.currentThread().getId()), testName);
	}
    
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod)
    {
        annotation.setPriority(getMethodLineNumber(testMethod));
    }
	 
    private int getMethodLineNumber(Method testMethod)
    {
        try
        {
            CtClass cc = getClassPool.get(testMethod.getDeclaringClass().getCanonicalName());
            CtMethod methodX = cc.getDeclaredMethod(testMethod.getName());
            return methodX.getMethodInfo().getLineNumber(0);        
        }
        catch(Exception e)
        {
            throw new RuntimeException("Getting of line number of method "+testMethod+" failed", e);
        }
    }
  
}