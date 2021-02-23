/*
* Copyright 2018 The Red Flag Group (http://www.redflaggroup.com)
*
* You may not use this file except in compliance with the terms of your service agreement with The Red Flag Group
*
*/

package com.psa.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverManager extends TestInitReference {

	protected static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public synchronized static WebDriver createDriver(String browser) {
		WebDriver driver = null;

		if (mode.equals("standalone")) {
			binaryRootFolder = _prop.getString("standaloneBinaryRootFolder");
			uploadFiles = _prop.getString("standaloneUploadFiles_location");
		}

		switch (browser){
			case "Firefox":
				WebDriverManager.firefoxdriver().setup();
				if (headless.equalsIgnoreCase("Yes")) {
					FirefoxBinary firefoxBinary = new FirefoxBinary();
					firefoxBinary.addCommandLineOptions("--headless");
					FirefoxOptions options = new FirefoxOptions();
					options.setBinary(firefoxBinary);
					driver = new FirefoxDriver(options);
				} else {
					driver = new FirefoxDriver();
				}
			case "Chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();

				if (mode.equals("standalone")) {
					System.setProperty("webdriver.chrome.logfile", "/usr/automation/test-output/chromedriver.log");
				} else {
					System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
				}
				if (headless.equalsIgnoreCase("Yes")) {
					options.addArguments("--headless");
				}

				options.addArguments("start-maximized");
				options.addArguments("disable-infobars");
				options.addArguments("--disable-infobars");
				options.addArguments("disable-extensions");
				options.addArguments("no-sandbox");
				options.addArguments("--no-sandbox");
				options.addArguments("disable-plugins");
				options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				driver = new ChromeDriver(options);
				break;
		}

		return driver;
	}
	/**
	 * Get the driver session to ThreadLocal
	 * 
	 * @return webDriver
	 */
	public synchronized static WebDriver getDriver() {
		return webDriver.get();
	}

	/**
	 * Set the current driver session to ThreadLocal
	 *
	 * @param driver
	 */
	public static synchronized void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
		if (TestInitReference.mode.equals("standalone")) {
			driver.manage().window().setSize(new Dimension(1271, 937));
		} else {
			driver.manage().window().maximize();
		}
	}

	/**
	 * Closes the browser and remove the driver session to ThreadLocal
	 */
	public void removeDriver() {
		if (getDriver() != null) {
			getDriver().close();
			getDriver().quit();
			webDriver.remove();
		}
	}
}
