package com.common.automation.framework.functionLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * The class initializes driver
 * 
 * @author vibhor
 *
 * @param <T> - Type of Driver
 */

public class DriverInitializer<T extends WebDriver> {

	private T driver = null;

	/**
	 * To initialize the WebDriver
	 * 
	 * @param browser - {@link String} browser name
	 */
	@SuppressWarnings("unchecked")
	public void setWebDriver(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("headless");
//			options.addArguments("window-size=1920x1080");
			this.driver = (T) new ChromeDriver();

		}

		if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.marionette",
					System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			this.driver = (T) new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * 
	 * @return - {@link RemoteWebDriver} object
	 */
	public T getDriver() {

		return driver;
	}

}
