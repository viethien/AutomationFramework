package com.common.automation.framework.functionLibrary;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains all selenium functions
 * 
 * @author vibhor
 *
 */
public abstract class SeleniumLibrary {

	protected WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Send text input to a input box
	 * 
	 * @param element - {@link WebElement}
	 * @param text    - {@link String}
	 */
	protected void sendKeys(WebElement element, String text) {

		element.sendKeys(text);
	}

	/**
	 * Click a web element
	 * 
	 * @param element - {@link WebElement}
	 */
	protected void click(WebElement element) {

		element.click();
	}

	/**
	 * Wait for visibility of a web element
	 * 
	 * @param element          - {@link WebElement}
	 * @param timeoutInSeconds - {@link Long}
	 */
	protected void WaitForElementVisible(WebElement element, long timeoutInSeconds) {

		this.wait = new WebDriverWait(this.driver, timeoutInSeconds);
		this.wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for invisibility of a web element
	 * 
	 * @param element          - {@link WebElement}
	 * @param timeoutInSeconds - {@link Long}
	 */
	protected void WaitForElementInvisible(WebElement element, long timeoutInSeconds) {

		this.wait = new WebDriverWait(this.driver, timeoutInSeconds);
		this.wait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * Wait for DOM to load completely
	 * 
	 * @param driver           - {@link WebDriver}
	 * @param timeoutInSeconds - {@link Long}
	 */
	public void waitForDocumentReady(long timeoutInSeconds) {

		this.wait = new WebDriverWait(this.driver, timeoutInSeconds);
		this.wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
	}

	protected boolean isDisplayed(WebElement element) {

		return element.isDisplayed();
	}

	protected String getText(WebElement element) {

		return element.getText();
	}

	protected void clickUsingJS(WebElement element) {

		JavascriptExecutor executor = (JavascriptExecutor) this.driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public String getCurrentURL() {

		return this.driver.getCurrentUrl();
	}

	public List<String> getItemsListText(List<WebElement> elements) {
		List<String> text = new ArrayList<String>();

		for (WebElement element : elements) {

			text.add(this.getTextUsingJS(element));
		}

		return text;
	}

	public String getTextUsingJS(WebElement element) {

		JavascriptExecutor jse = (JavascriptExecutor) this.driver;

		return jse.executeScript("return arguments[0].textContent;", element).toString();

	}

	public String getAttribute(WebElement element, String attributeName) {

		return element.getAttribute(attributeName);
	}
}
