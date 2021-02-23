/*
 * Copyright 2018 The Red Flag Group (http://www.redflaggroup.com)
 *
 * You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
 *
 */

package com.psa.library;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;
import static org.openqa.selenium.By.xpath;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.net.ssl.HttpsURLConnection;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.reporter.KlovReporter;
import com.psa.pages.ObjectReference;

import static com.psa.data.GenericData.*;

@SuppressWarnings("unused")
public class FunctionReference extends Results {
	private static final Logger[] pin;
	static {
		pin = new Logger[]{
				Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake"),
				Logger.getLogger("org.freemarker.loggerLibrary"),
				Logger.getLogger("com.aventstack.extentreports")
		};
		for (Logger set : pin) {
			set.setLevel(Level.OFF);
		}
	}

	protected static ReadXmlData rxml = new ReadXmlData();
	private static final int RETRY_COUNT = 5;
	protected WebDriver driver;
	public static ScreenRecorder screenRecorder;
	public static String browser = System.getenv("BROWSER") != null ? System.getenv("BROWSER") : rxml.data("browser");
	public String checkhttp = System.getenv("CHECK_HTTP") != null ? System.getenv("CHECK_HTTP") : rxml.data("checkhttp");
	public static String headless = rxml.data("headless");
	public int resultcount = 0;
	private static int statusCode = 0;
	public static ArrayList<String> statusCodeArray = new ArrayList<String>();
	public static String reportContent;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	public static ResourceBundle _prop = ResourceBundle.getBundle("dev");
	public String screenshot = _prop.getString("screen_shot_location");
	String screenshotFilename = "Screenshot file name is: ";
	private static final String SCREENSHOT_PREFIX = "TestMethod-";

	// KLOV VARIABLE
	public static KlovReporter klovReporter;
	protected static Boolean klovReporterEnabled = klovReporterDB != null && !klovReporterDB.isEmpty();

	public void takeScreenshot() throws IOException {
		SecureRandom rand = new SecureRandom();
		int num = rand.nextInt(150);
		File scrFile;
		Integer numNoRange = rand.nextInt();

		DateFormat dateformate = new SimpleDateFormat("dd-mm-yyyy_hh-mm");
		Date date = new Date();
		String currentdate = dateformate.format(date);

		if (browser.contains("Remote")) {
			try {
				WebDriver augmentedDriver = new Augmenter().augment(driver());
				scrFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
				String filename = numNoRange.toString();
				pass(screenshotFilename + filename+"_"+currentdate);
				File destFile =  new File(screenshot + filename +"_"+currentdate+ ".png");
				copyFile(scrFile, destFile);
				Reporter.log("<a href=" + destFile.getAbsolutePath() + "> <img width='100' height='100' src=" + destFile.getAbsolutePath() + "> </a>");
			} catch (Exception n) {
				try {
					scrFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);
					String filename = numNoRange.toString();
					fail(screenshotFilename + filename+"_"+currentdate);
					File destFile =  new File(screenshot + filename +"_"+currentdate+ ".png");
					copyFile(scrFile, destFile);
					Reporter.log("<a href=" + destFile.getAbsolutePath() + "> <img width='100' height='100' src=" + destFile.getAbsolutePath() + "> </a>");
				} catch (NullPointerException m) {
					fail(m.getMessage());
					throw m;
				}
			}
		} else {
			try {
				scrFile = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.FILE);
				String filename = numNoRange.toString();
				fail(screenshotFilename + filename+"_"+currentdate);
				File destFile =  new File(screenshot + filename +"_"+currentdate+ ".png");
				copyFile(scrFile, destFile);
				Reporter.log("<a href=" + destFile.getAbsolutePath() + "> <img width='100' height='100' src=" + destFile.getAbsolutePath() + "> </a>");
			} catch (NullPointerException n) {
				fail(n.getMessage());
				throw n;
			}
		}
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMdd_hh-mm").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String extentscreenshot_location = _prop.getString("extent_screenshot_location");
		String destination = System.getProperty("user.dir")+extentscreenshot_location+screenshotName+dateName+".png";
		String mode = _prop.getString("mode");
		if(mode.equals("standalone")){
			extentscreenshot_location = _prop.getString("standaloneextentscreenshot_location");
			destination = extentscreenshot_location+screenshotName+dateName+".png";
		}
		File finalDestination = new File(destination);
		copyFile(source, finalDestination);
		return destination;
	}

	public static void storeScreenshotInfo(ITestResult result, String screenshotPath) {
		result.setAttribute(SCREENSHOT_PREFIX + result.getName(), screenshotPath);
	}

	public static String getScreenShotInfo(ITestResult result) {
		return String.valueOf(result.getAttribute(SCREENSHOT_PREFIX + result.getName()));
	}

	public void clickAt(By by) {
		if (!browser.equalsIgnoreCase("InternetExplorer")) {
			Actions builder = new Actions(driver());
			try {
				WebDriverWait wait = new WebDriverWait(driver(), 5);
				WebElement tagElement = wait.until(ExpectedConditions.presenceOfElementLocated(by));
				builder.moveToElement(tagElement).click().perform();
			} catch (Exception e) {
				throw e;
			}
		} else {
			click(by);
		}
	}

	public void doubleClick(By by) {
		Actions builder = new Actions(driver());
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			builder.doubleClick(element).build().perform();
			info("Double click " + by + " was performed. - doubleClick");
		} catch (NoSuchElementException | TimeoutException e) {
			fail(by + " should be displayed. - doubleClick");
			throw e;
		}
	}

	/**
	 * Use this function to click on a button or tab or an element
	 * @param by element locator
	 */
	public void click(By by) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			((JavascriptExecutor) driver()).executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
			info(by + " was clicked. - click");
		} catch (NoSuchElementException | TimeoutException e) {
			fail(by + " should be displayed. - click");
			throw e;
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
			info(by + " was clicked. - click");
		}
	}

	/**
	 * Use this function for actual wait time
	 */
	public void sleepForSeconds(int seconds){
		info("System will wait for " + seconds + " seconds. - sleepForSeconds");
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		}
		info("System finished waiting for " + seconds + " seconds. - sleepForSeconds");
	}

	/**
	 * Use this function to enter value on a text field
	 * @param by element locator
	 * @param value string input
	 * @throws InterruptedException either before or during the activity
	 */
	public void type(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			element.clear();
			if (getValue(by) != null && !getValue(by).isEmpty()) {
				keyboardClearValue(by);
			}
			element.sendKeys(value);
			String attributeType = getAttributeValue(by, "type");
			if (attributeType != null && attributeType.contains("password")) {
				info(maskString(value) + " was entered in " + by + ". - type");
			} else {
				info( value + " was entered in " + by + ". - type");
			}
		} catch (NoSuchElementException | TimeoutException e) {
			String attributeType = getAttributeValue(by, "type");
			if (attributeType != null && attributeType.contains("password")) {
				fail(maskString(value) + " should be entered in " + by + ". - type");
				throw e;
			} else {
				fail(value + " should be entered in " + by + ". - type");
				throw e;
			}
		} catch (ElementNotInteractableException e) {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			((JavascriptExecutor) driver()).executeScript("arguments[0].click();", element);
			info(by + " was clicked via js. - click");
			element.sendKeys(value);
			String attributeType = getAttributeValue(by, "type");
			if (attributeType != null && attributeType.contains("password")) {
				info(maskString(value) + " was entered in " + by + ". - type");
			} else {
				info( value + " was entered in " + by + ". - type");
			}
		}
	}

	/**
	 * Use this function to check if the element is visible
	 * @param by element locator
	 * @return true or false
	 */
	public boolean isElementVisible(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			info(by + " was displayed. - isElementVisible");
			return true;
		} catch (TimeoutException e) {
			info(by + " was not displayed. - isElementVisible");
			return false;
		}
	}

	/**
	 * Use this function to check if the element is not visible
	 * @param by element locator
	 * @return true or false
	 */
	public boolean isElementNotVisible(final By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
			info(by + " was not displayed. - isElementNotVisible");
			return true;
		} catch (TimeoutException e) {
			info(by + " was displayed. - isElementNotVisible");
			return false;
		}
	}

	/**
	 * Use this function to get element text
	 * @param by element locator
	 * @return value of the text
	 */
	public String getText(By by) {
        waitElementToBeVisible(by);
        String elementText = driver().findElement(by).getText();
		return elementText;
	}

    /**
     * Use this function to get value of an element
     * @param by element locator
     * @param attribute string value
     * @return element attribute value
     */
    public String getAttributeValue(By by, String attribute) {
        String attributeValue = "";
        waitElementToBeVisible(by);
        attributeValue = driver().findElement(by).getAttribute(attribute);
        return attributeValue;
    }

	/**
	 * Use this function to get value of an element
	 * @param by element locator
	 * @return element attribute value
	 */
	public String getValue(By by) {
        return getAttributeValue(by,"value");
	}

	/**
	 * Use this function to move to an element and right click select
	 * @param by element locator
	 */
	public void rightClick(By by) {
		new Actions(driver()).moveToElement(driver().findElement(by)).contextClick(driver().findElement(by)).perform();
	}

	/**
	 * Use this function to find an element, highlighting it in red
	 * @param by element locator
	 * @return elem element highlighted
	 */
	public WebElement highlightElement(By by) {
		WebElement elem = driver().findElement(by);
		if (driver() instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver()).executeScript("arguments[0].style.border='3px solid red'", elem);
		}
		return elem;
	}

	/**
	 * Use this function to select element with the target value in a simple way
	 * @param by element locator
	 * @param value string value to select
	 */
	public void selectValue(By by, String value) {
		try {
			waitElementToBeVisible(by);
			Select dropdown = new Select(driver().findElement(by));
			dropdown.selectByValue(value);
			info(value + " was selected from " + by + ". - selectValue");
		} catch (NoSuchElementException | TimeoutException e) {
			fail(value + " should be selected from " + by + ". - selectValue");
			throw e;
		}
	}

	public void selectValueByText(By by, String text) {
		try {
			waitElementToBeVisible(by);
			Select dropdown = new Select(driver().findElement(by));
			dropdown.selectByVisibleText(text);
			info(text + " was selected from " + by + ". - text");
		} catch (NoSuchElementException | TimeoutException e) {
			fail(text + " should be selected from " + by + ". - text");
			throw e;
		}
	}


	public enum SpinnerType {
		LOAD, SAVE
	}

	/**
	 * Use this function to randomly generate unique data
	 * @param len
	 * @return
	 */
	public static String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	/**
	 * Use this function when you want to press tab key then hit enter to the target element
	 */
	public void tabAndEnterViaKeyboard() {
		Actions action = new Actions(driver());
		action.sendKeys(Keys.TAB).build().perform();
		action.sendKeys(Keys.ENTER).build().perform();
	}

	/**
	 * Use this function when you want to hit enter key and press tab key to proceed to next element
	 */
	public void enterAndTabViaKeyboard() {
		Actions action = new Actions(driver());
		action.sendKeys(Keys.ENTER).build().perform();
		action.sendKeys(Keys.TAB).build().perform();
	}

	/**
	 * Use this function when you want to hit enter key instead of clicking save/ update button
	 */
	public void enterViaKeyboard() {
		Actions action = new Actions(driver());
		action.sendKeys(Keys.ENTER).build().perform();
		action.sendKeys("\n");
	}

	/**
	 * Use this function to add attachment in email report
	 * @param multipart
	 * @param filePath
	 * @param filename
	 * @throws MessagingException
	 * @throws Exception either before or during the activity
	 */
	public static void addAttachment(Multipart multipart, String filePath, String filename) throws MessagingException {
		DataSource source = new FileDataSource(filePath);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		File dir = new File(filePath);
		File[] dirContents = dir.listFiles();
	}

	/**
	 * Use this function to check if file is downloaded in the Download folder
	 * If found the it deletes the file
	 * Note: This works on Chrome browser only
	 * @param downloadPath
	 *            file location for Download folder
	 * @param fileName
	 *            full file name including extensions .xls, .doc, etc...
	 * @return
	 * @return
	 * @throws InterruptedException either before or during the activity
	 */
	public void ifFileDownloadedDelete(String downloadPath, String fileName) {
		try {
			File dir = new File(downloadPath);
			File[] dirContents = dir.listFiles();
			for (File file : dirContents) {
				File getLatestFile = getLatestFilefromDir(downloadPath);
				String fileNames = getLatestFile.getName();

				if (file.getName().equals(fileName)) {
					// File has been found, it can now be deleted:
					file.delete();
					pass("File [" + fileName + "] has been deleted.");
				}
			}
		} catch (Exception e) {
			fail("File [" + fileName + "] has been deleted.");
			throw e;
		}
	}

	private File getLatestFilefromDir(String dirPath){
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

	/**
	 * Use this function to select an element on hover menu
	 * @param by element to be hovered
	 */
	public void hover(By by) {
		try{
			Actions actions = new Actions(driver());
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			actions.moveToElement(element).perform();
			info("Hover to element " + by + " successful. - hover");
		} catch (NoSuchElementException e) {
			fail("Element " + by + " should be visible. - hover");
			throw e;
		}
	}

	/**
	 * Use this function to Hover on an element and click specific target element
	 * @param selector
	 * @param target
	 * @throws InterruptedException either before or during the activity
	 */
	public void hoverMouseToAndClickTarget(By selector, By target) throws InterruptedException {
		waitElementToBeVisible(selector);
		Actions action = new Actions(driver());
		WebElement element = driver().findElement(selector);
		action.moveToElement(element).moveToElement(driver().findElement(target)).click().build().perform();
		sleepForSeconds(3);
	}

	/**
	 * Use this function to select a value on the simple drop down
	 *
	 * @param by
	 *            object location
	 * @param value
	 *            value to be selected
	 * @throws InterruptedException either before or during the activity
	 */
	public void selectValueDropdown(By by, String value) {

		try {
			WebElement identifier = driver().findElement(by);
			Select select = new Select(identifier);
			select.selectByVisibleText(value);
		} catch (TimeoutException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Use this function to wait for a Single Element to be Invisible on a page.
	 * @param by
	 * @param timeout
	 **/
	public void waitElementToBeInvisible(By by, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (TimeoutException e) {
			fail("Timeout reach Element: " + by + " should be invisible. - waitElementToBeInvisible");
			throw e;
		}
	}

	public void waitElementToBeInvisible(By by) {
		waitElementToBeInvisible(by, timeout);
	}


	/**
	 * Use this function to wait for a Single Element to be Visible on a page.
	 * @param by
	 * @param timeout
	 * @throws InterruptedException either before or during the activity
	*/
	public void waitElementToBeVisible(By by, int timeout) {
	    try {
            WebDriverWait wait = new WebDriverWait(driver(), timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            fail("Timeout reach Element: " + by + " should be visible. - waitElementToBeVisible");
			throw e;
        }
	}

	public void waitElementToBeVisible(By by) {
		waitElementToBeVisible(by, timeout);
	}

	/**
	 * Use this function to wait for a element to be clickable
	 * @param by
	 * @param timeout
	 * @throws InterruptedException either before or during the activity
	 */
	public void waitElementToBeClickable(By by, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (TimeoutException e) {
			fail("Timeout reach Element: " + by + " should be clickable. - waitElementToBeClickable");
			throw e;
		}
	}

	public void waitElementToBeClickable(By by) {
		waitElementToBeClickable(by, timeout);
	}

	public static int getResponseCode(String urlString) throws IOException{
		URL url = new URL(urlString);
		HttpsURLConnection huc = (HttpsURLConnection)url.openConnection();
		huc.setRequestMethod("GET");
		huc.connect();
		return huc.getResponseCode();
	}

	public void checkHTTPStatus() throws Exception {
		new TestInitReference();
		WebDriver driver = DriverManager.createDriver(browser);
		DriverManager.setWebDriver(driver);
		driver().get(TestInitReference.url);

		List<WebElement> links = driver().findElements(By.tagName("a"));
		Log.info("Site Status Code: ");

		for (int i = 0; i < links.size(); i++) {
			if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {

				try {

					if (links.get(i).getAttribute("href").contains("http")) {
						statusCode = getResponseCode(links.get(i).getAttribute("href").trim());
						String statusCodeAsString = Integer.toString(statusCode);
						statusCodeArray.add(statusCodeAsString);

					} else if (statusCode == 0) {
						driver().quit();
						Log.info("Unable to access PSA. Server maybe down. Exiting the Test now...");
						reportContent = "Unable to access "+ "<a href="+ TestInitReference.url +">URL</a>" +". Server down.";
						CustomListener.sendEmail("automation.ci@redflaggroup.com", sendEmailToList +","+ "qateamautomation@redflaggroup.com");
						System.exit(2);
					}
				} catch (Exception e) {
					throw e;
				}
			}
		}
		try {
			System.out.println(statusCodeArray);
			if (statusCodeArray.contains("200")) {
				//do nothing
			} else {
				driver().quit();
				Log.info("Unable to access RED-CMS. Server maybe down. " + TestInitReference.url);
				reportContent = "Unable to access "+ "<a href="+ TestInitReference.url +">URL</a>" +". Server maybe down.";
				CustomListener.sendEmail("automation.ci@redflaggroup.com", sendEmailToList +","+ "qateamautomation@redflaggroup.com");
				System.exit(2);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
		if(driver() != null)
		{
			driver().quit();
		}
	}


	public void startRecording() throws IOException, AWTException  {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		screenRecorder = new ScreenRecorder(gc, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
						CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
						Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
				new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
				null);

		screenRecorder.start();
	}

	public void stopRecording() throws IOException  {
		if (new TestInitReference().record.equalsIgnoreCase("Yes")) {
			screenRecorder.stop();
		}
	}

	public static String getKlovReportId() {
		String klovText = "";

		if (klovReporterEnabled) {
			String klovAllTests = klovReporterURL + "/build?id=" + klovReporter.getReportId();
			String klovPassed = klovAllTests + "&status=pass";
			String klovFailed = klovAllTests + "&status=fail";
			String klovError = klovAllTests + "&status=error";
			String klovSkip = klovAllTests + "&status=skip";

			klovText = "<strong> KLOV REPORTER RESULT:&ensp;&ensp;&ensp;&ensp;</strong>"
					+"<span style='font-weight:normal'>"
					+ "<a href="+klovAllTests+" style=\"text-decoration:none\">&#9632; View ALL Tests</a>"
					+ "<a href="+klovPassed+" style=\"text-decoration:none\" >&ensp;&ensp;&ensp;&ensp;&#9632; View PASSED Tests</a>"
					+ "<a href="+klovFailed+" style=\"text-decoration:none\">&ensp;&ensp;&ensp;&ensp;&#9632; View FAILED Tests</a>"
					+ "<a href="+klovError+" style=\"text-decoration:none\">&ensp;&ensp;&ensp;&ensp;&#9632; View ERROR Tests</a>"
					+ "<a href="+klovSkip+" style=\"text-decoration:none\">&ensp;&ensp;&ensp;&ensp;&#9632; View SKIPPED Tests</a> </span> <br /><br /><br />";
			return klovText;
		}
		return klovText;
	}

	public static WebDriver driver() {
		return DriverManager.getDriver();
	}

	/**
	 * Use this function to switch tab
	 * @param index tab index
	 */
	public void switchWindowTab(int index) {
		ArrayList<String> tabs = new ArrayList<String>(driver().getWindowHandles());
		driver().switchTo().window(tabs.get(index));
	}

	/**
	 * Use this function to switch and close tab
	 * @param index tab index
	 */
	public void switchWindowTabAndClose(int index) {
		ArrayList<String> tabs = new ArrayList<String>(driver().getWindowHandles());
		driver().switchTo().window(tabs.get(index));
		driver().close();
	}

	public void refreshPage() {
		driver().navigate().refresh();
	}

	/**
	 * Use this function to locate a dynamic element
	 * @param technique defaults to XPATH
	 * @param locator base element locator
	 * @param dynamicVariable unique name of the element
	 * @throws IllegalAccessException
	 */
	public static By createDynamicLocator(Bys technique, String locator, Object dynamicVariable) throws IllegalAccessException {
		String defaultLocator = String.format(locator, dynamicVariable);

		switch (technique) {
			case XPATH:
				return xpath(defaultLocator);
			case CSS:
				return cssSelector(defaultLocator);
			case ID:
				return id(defaultLocator);
			case NAME:
				return name(defaultLocator);
			case XPATH_LOCATE:
				return xpath(String.format(locate(locator), dynamicVariable));
			case CSS_LOCATE:
				return cssSelector(String.format(locate(locator), dynamicVariable));
			case ID_LOCATE:
				return id(String.format(locate(locator), dynamicVariable));
			case NAME_LOCATE:
				return name(String.format(locate(locator), dynamicVariable));
			default:
				return xpath(locator);
		}
	}

	public static String locate(String ex) throws IllegalAccessException {

		ObjectReference defaultObjectReference = new ObjectReference();
		Field[] fields = defaultObjectReference.getClass().getDeclaredFields(); // get all declared fields
		for (Field field : fields) {
			if (field.getType().equals(String.class)) { // if it is a String type field
				field.setAccessible(true);
				String fieldValue = field.get(defaultObjectReference).toString();
				if (fieldValue.equalsIgnoreCase(ex)) {
					ex = field.getName();
				}
			}
		}
		return ex;
	}

	/**
	 * Use this function to switch to alert box and compare Alert message to Expected Message Alert
	 * @param expectedText
	 * @return boolean
	 */
	public boolean verifyAlertMessageContentEqualsText(String expectedText) {
		String msgAlert = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver(), 5);
			Alert alert = driver().switchTo().alert();
			msgAlert = alert.getText();
			Assert.assertTrue(msgAlert.contentEquals(expectedText));
			info("Alert Message: '" + msgAlert + "' was equal to Expected Alert Message: '" + expectedText + "'. - verifyAlertMessageContentEqualsText");
			return true;
		} catch (AssertionError | Exception e) {
			fail("Alert Message: '" + msgAlert + "' should be equal to Expected Alert Message: '" + expectedText + "'. - verifyAlertMessageContentEqualsText");
			return false;
		}
	}

	/**
	 * Use this function to Accept Alert
	 */
	public void acceptAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), 5);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver().switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			fail("Alert Accept Button should be present. - acceptAlert");
			throw e;
		}
	}

	/**
	 * Use this function to get windows count
	 * @return count of windows open
	 */
	public int getWindowsCount() {
		Set<String> winHandles = driver().getWindowHandles();
		int numberOfWindows = winHandles.size();
		return numberOfWindows;
	}

	/**
	 * Use this function to get windows count
	 * @return count of tabs open
	 */
	public int getTabsCount() {
		ArrayList<String> tabs = new ArrayList<String>(driver().getWindowHandles());
		int numberOfTabs = tabs.size();
		return numberOfTabs;
	}

	/**
	 * Use this function to open new browser and navigates to url
	 * @param url
	 */
	public void openBrowserAndNavigateToURL(String url) {
		WebDriver driver = DriverManager.createDriver(browser);
		DriverManager.setWebDriver(driver);
		driver().get(url);
		info(browser + " was opened and navigated to '" + url + "'. - openBrowserAndNavigateToURL");
	}


	/**
	 * Use this function to navigate to any URL
	 * @param anyURL
	 */
	public void navigateToURL(String anyURL) {
		driver().navigate().to(anyURL);
		info("User was navigated to '" + anyURL + "'. - navigateToURL");
	}

	/**
	 * Use this function to check Current URL to expected URL
	 * @param expectedURL
	 * @return
	 */
	public boolean isCurrentURLEqualsURL(String expectedURL) {
		String actualURL = null;

		try{
			actualURL = driver().getCurrentUrl();
			Assert.assertTrue(actualURL.contentEquals(expectedURL));
			info("Actual URL: '" + actualURL + "' was equal to Expected URL: '" + expectedURL + "'. - isCurrentURLEqualsURL");
			return true;
		} catch (AssertionError | Exception e) {
			fail("Actual URL: '" + actualURL + "' should be equal to Expected URL: '" + expectedURL + "'. - isCurrentURLEqualsURL");
			return false;
		}
	}

	/**
	 * Use this function to select value from List
	 * @param by element locator
	 * @param value string input
	 * @throws InterruptedException either before or during the activity
	 */
	public void selectLiValueFromList(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elementList = element.findElements(By.tagName("li"));
			for (WebElement li : elementList) {
				if (li.getText().equals(value)) {
					li.click();
					info(value + " was selected in List Box: " + by + ". - selectLiValueFromList");
					return;
				}
			}
			fail(value + " should be available in List Box: " + by + ". - selectLiValueFromList");
			Assert.fail();
		} catch (NoSuchElementException | TimeoutException e) {
			fail(value + " should be selected in List Box: " + by + ". - selectLiValueFromList");
			throw e;
		}
	}

	/**
	 * Use this function to check Same Elements Text equals to Expected Text
	 * @param by element locator
	 * @param expectedText string input
	 * @return boolean
	 */
	public boolean isSameElementsContentEqualsText(By by, String expectedText) {
		try {
			waitElementToBeVisible(by);
			List<WebElement> allElements = driver().findElements(by);
			Iterator<WebElement> itr = allElements.iterator();
			while (itr.hasNext()) {
				String elementText = itr.next().getText();
				Assert.assertTrue(elementText.contentEquals(expectedText));
			}
			info("All Elements Text was equal to Expected Text: '" + expectedText + "'. - isSameElementsContentEqualsText");
			return true;
		} catch (AssertionError e) {
			info("All Elements Text should be equal to Expected Text: '" + expectedText + "'. - isSameElementsContentEqualsText");
			return false;
		}
	}

	/**
	 * Use this function to mask sensitive information
	 * @param strText
	 */
	public static String maskString(String strText) {
		int length = strText.length();
		char maskChar = '*';
		int start = 0;

		StringBuilder sbMaskString = new StringBuilder(length);

		for(int i = 0; i < length; i++){
			sbMaskString.append(maskChar);
		}

		return strText.substring(0, start)
				+ sbMaskString.toString()
				+ strText.substring(start + length);
	}

	/**
	 * Use this function to get instant date and time
	 */
	public static String getInstantDateAndTime() {
		String actualDateAndTime = String.valueOf(Clock.systemUTC().instant());
		return actualDateAndTime.replace(":","-");
	}

	/**
	 * Use this function to get substring from string
	 * @param text original text
	 * @param splitter string that divides the text and creates substrings
	 * @param subStringIndex index to return text substring
	 */
	public String splitString(String text, String splitter, int subStringIndex) {
		String[] arrSplit = text.split(splitter);
		info(arrSplit[subStringIndex] + " was taken from " + text + " - splitString");
		return arrSplit[subStringIndex];
	}

	/**
	 * Use this function to select checkbox
	 * @param by
	 * @return
	 */
	public void selectCheckBox(By by) {

		waitElementToBeVisible(by);
		String actualValue = getAttributeValue(by, CLASS);
		if (actualValue.contains(CHECKED)) {
			info(by + " CheckBox was initially selected. - selectCheckBox");
		} else {
			click(by);
		}
	}

	/**
	 * Use this function to check if the element is selected
	 * @param by element locator
	 * @return true or false
	 */
	public boolean isElementSelected(By by) {
		waitElementToBeVisible(by);
		if (driver().findElement(by).isSelected()) {
			info(by + " was selected. - isElementSelected");
			return true;
		} else {
			info(by + " was not selected. - isElementSelected");
			return false;
		}
	}

	/**
	 * Use this function to check if the element is disabled
	 * @param by element locator
	 * @return true or false
	 */
	public boolean isElementDisabled(By by) {
		waitElementToBeVisible(by);
		if (!driver().findElement(by).isEnabled()) {
			info(by + " was disabled. - isElementDisabled");
			return true;
		} else {
			info(by + " was not disabled. - isElementDisabled");
			return false;
		}
	}

    /**
     * Use this function to check if the element is disabled
     * @param by element locator
     * @return true or false
     */
    public boolean isElementEnabled(By by) {
        waitElementToBeVisible(by);
        if (driver().findElement(by).isEnabled()) {
            info(by + " was enabled. - isElementEnabled");
            return true;
        } else {
            info(by + " was not enabled. - isElementEnabled");
            return false;
        }
    }

	/**
	 * Use this function to count same Elements Identifier
	 * @param by locators that is common
	 */
	public int countSameElements(By by) {
		int countObjects = driver().findElements(by).size();
		return countObjects;
	}

	/**
	 * Use this function to compare count value from a given int value
	 * @param actualValue any integer
	 * @param value any integer
	 */
	public boolean isActualValueEqualsValue(int actualValue, int value) {
		try{
			Assert.assertEquals(actualValue,value);
			info("Actual value: " + actualValue + " was equal to value: " + value + " - isActualValueEqualsValue");
			return true;
		} catch (AssertionError e) {
			info("Actual value: " + actualValue + " was not equal to value: " + value + " - isActualValueEqualsValue");
			return false;
		}
	}

	/**
	 * Use this function to compare actual value from a given string value
	 * @param actualValue any String
	 * @param value any String
	 */
	public boolean isActualValueEqualsValue(String actualValue, String value) {
		try{
			Assert.assertEquals(actualValue,value);
			info("Actual value '" + actualValue + "' was equal to value '" + value + "' - isActualValueEqualsValue");
			return true;
		} catch (AssertionError e) {
			info("Actual value '" + actualValue + "' was not equal to value '" + value + "' - isActualValueEqualsValue");
			return false;
		}
	}

	public boolean isActualValueNotEmpty(String actualValue) {
		try{
			Assert.assertFalse(actualValue.isEmpty());
			info("Actual value '" + actualValue + "' was not empty' - isEmpty");
			return true;
		} catch (AssertionError e) {
			info("Actual value '" + actualValue + "' was empty");
			return false;
		}
	}

	public boolean isActualValueEqualsValue(List<String> actualValue, List<String>  value) {
		try{
			Assert.assertEquals(actualValue,value);
			info("Actual value '" + actualValue + "' was equal to value '" + value + "' - isActualValueEqualsValue");
			return true;
		} catch (AssertionError e) {
			info("Actual value '" + actualValue + "' was not equal to value '" + value + "' - isActualValueEqualsValue");
			return false;
		}
	}

	/**
	 * Use this function to check actual value contains a given string value
	 * @param actualValue any String
	 * @param value any String
	 */
	public boolean isActualValueContainsValue(String actualValue, String value) {
		try{
			Assert.assertTrue(actualValue.contains(value));
			info("Actual value: " + actualValue + " contains value: " + value + " - isActualValueContainsValue");
			return true;
		} catch (AssertionError e) {
			info("Actual value: " + actualValue + " does not contains value: " + value + " - isActualValueContainsValue");
			return false;
		}
	}

	/**
	 * Use this function to press ESCAPE key from keyboard
	 */
	public void keyboardEscape() {
		Actions action = new Actions(driver());
		action.sendKeys(Keys.ESCAPE).build().perform();
	}

	/**
	 * Use this function to press select all and Delete value from fields.
	 */
	public void keyboardClearValue(By by) {
		doubleClick(by);
		String selectAll = Keys.chord(Keys.CONTROL,"a");
		driver().findElement(by).sendKeys(selectAll);
		driver().findElement(by).sendKeys(Keys.BACK_SPACE);
		info("Text in " + by + " was cleared. - keyboardClearValue");
	}

	/**
	 * Use this function to check if value is in the list
	 * @param by element locator
	 * @param value string input
	 */
	public boolean isValueInTheList(By by, String value) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			List<WebElement> elementList=element.findElements(By.tagName("li"));
			for (WebElement li : elementList) {
				if (li.getText().equals(value)) {
					info(value + " was available in the List: " + by + ". - isValueInTheList");
					return true;
				}
			}
			info(value + " was not available in the List: " + by + ". - isValueInTheList");
			return false;
		} catch (NoSuchElementException | TimeoutException e) {
			fail(by + " should be visible. - isValueInTheList");
			throw e;
		}
	}

	/**
	 * Use this function to check if value is in the list
	 * @param by element locator
	 * @return hex rgb equivalent to hex
	 */
	public String getBackgroundColor(By by) {
		try {
			WebDriverWait wait = new WebDriverWait(driver(), timeout);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			String backgroundColor = element.getCssValue("background-color");
			String hex = Color.fromString(backgroundColor).asHex();
			return hex;
		} catch (NoSuchElementException | TimeoutException e) {
			fail(by + " should be visible. - getBackgroundColor");
			throw e;
		}
	}

	/**
	 * Use this function to add day in specific date format
	 * @param date starting date
	 * @param days number of days to be added
	 */
	public static Date addDays(Date date, int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * Use this function to toggle ON object
	 * @param by to toggle
	 */
	public void toggleOn(By by) {
        if (getAttributeValue(by, CLASS).contains(CHECKED)) {
            info(by + " was toggled ON. - toggleOn");
        } else {
            click(by);
        }
	}

    /**
     * Use this function to toggle OFF object
     * @param by to toggle
     */
    public void toggleOff(By by) {
        if (getAttributeValue(by, CLASS).contains(CHECKED)) {
            click(by);
        } else {
            info(by + " was toggled OFF. - toggleOff");
        }
    }

	/**
	 * Use this function to get random data from string array
	 * @param array String collection
	 */
	public static String getRandom(String[] array) {
		Random random = new Random();
		int index = random.nextInt(array.length);
		return array[index];
	}

	/**
	 * Use this function to get random data from int array
	 * @param intArray int collection
	 */
	public static int getRandom(int[] intArray) {
		Random random = new Random();
		int index = random.nextInt(intArray.length);
		return intArray[index];
	}

	/**
	 * Use this function to get random data from int array
	 * @param array any object collection
	 * @return any from object collection
	 */
	public static Object getRandom(Object[] array) {
		Random random = new Random();
		int index = random.nextInt(array.length);
		return array[index];
	}

	/**
	 * Use this function to find same elements
	 * @param by object property of element to find
	 */
	public static List<WebElement> findElements(By by) {
		return driver().findElements(by);
	}

	/**
	 * Use this function to verify value for duplicate elements is displayed
	 * @param by object property of duplicate elements
	 * @param value value expected for duplicate elements
	 */
	public boolean isValueDisplayedForDuplicateElements(By by, String value) {
		int index = 1;
		for (WebElement element : findElements(by)) {
			if (element.getText().equals(value)) {
				info(value + " value was displayed. - isValueDisplayedForDuplicateElements");
				break;
			}
			if (index == findElements(by).size()) {
				info(value + " value should be displayed. - isValueDisplayedForDuplicateElements");
				return false;
			}
			index ++;
		}
		return true;
	}

	/**
	 * Use this function generate random date from start date to end date
	 * @param startInclusive start date
	 * @param endExclusive end date
	 * @return random date in between
	 */
	public static LocalDate randomDateInBetween(LocalDate startInclusive, LocalDate endExclusive) {
		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endExclusive.toEpochDay();
		long randomDay = ThreadLocalRandom
				.current()
				.nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomDay);
	}

	/**
	 * Use this function to verify error message displayed
	 * @param by object to check error message
	 * @param errorMessage expected error message
	 */
	public void assertErrorMessageDisplayed(By by, String errorMessage) {

		try {
			Assert.assertEquals(getText(by), errorMessage);
			pass("Error related to " + by + " with error message '" + errorMessage + "' was displayed. - verifyErrorMessageDisplayed");
		} catch (AssertionError e) {
			fail("Error related to " + by + " with error message '" + errorMessage + "' should be displayed. - verifyErrorMessageDisplayed");
			throw e;
		}
	}

	public List<String> getValuesFromElements(By locator) {
		List<String> values = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver();
		String tableScrollContainer = "*[wj-part=root]";
		sleepForSeconds(2);
		int windowWidth = Integer.parseInt(js
				.executeScript("return $('" + tableScrollContainer + "').prop('scrollWidth')").toString());
		js.executeScript("$('" + tableScrollContainer + "').scrollLeft(" + windowWidth + ");");
		List<WebElement> elements = driver().findElements(locator);
		for (int i = 1; i < elements.size(); i++)
			values.add(elements.get(i).getText());
		return values;
	}
}