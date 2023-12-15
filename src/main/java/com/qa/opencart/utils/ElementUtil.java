package com.qa.opencart.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameworkException;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String locatorValue) {
		By by = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
		case "class":
			by = By.className(locatorValue);
		case "link":
			by = By.linkText(locatorValue);
		case "xpath":
			by = By.xpath(locatorValue);
		case "css":
			by = By.cssSelector(locatorValue);
		case "partialtext":
			by = By.partialLinkText(locatorValue);
		case "tagname":
			by = By.tagName(locatorValue);

		default:
			System.out.println("Invalid locator type" + locatorType);
			throw new FrameworkException("INVALID LOCATOR TYPE");
		// break;
		}
		return by;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);

	}

	public void doSendKeys(String locatorType, String locator, String locatorValue) {
		getElement(locator, locatorType).sendKeys(locatorValue);
	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String getElementText(By locator) {
		String getText = getElement(locator).getText();
		return getText;
	}

	public String getElementAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	// Interview question - Google lang option click - can be used in all similar
	// scenarios
	public void doClickOnElement(By locator, String elementText) {
		List<WebElement> langlist = driver.findElements(locator);
		// System.out.println(langlist.size());
		for (WebElement e : langlist) {
			String text = e.getText();
			// System.out.println(text);
			if (text.equals(elementText)) {
				e.click();
			}

		}
	}

	// Interview question -google search with suggested option
	public void search(By searchLocator, By searchOption, String searchKey, String suggSearch)
			throws InterruptedException {
		doSendKeys(searchLocator, searchKey);
		Thread.sleep(2000);
		List<WebElement> suggList = getElements(searchOption);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(suggSearch)) {
				e.click();
				break;
			}
		}

	}

	// Interview question - find all images with link from amazon

	public void totalImageLink(By tagNameLocator, String attriName, String attriLink) {
		List<WebElement> imgList = getElements(tagNameLocator);
		System.out.println(imgList.size());

		for (WebElement e : imgList) {
			String altValue = e.getAttribute(attriName);
			String srcValue = e.getAttribute(attriLink);
			System.out.println(altValue + "===" + srcValue);

		}
	}

	/// Dropdown handles metthods///////////////

	public void doSelectDropdownByIndex(By locator, int indexValue) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(indexValue);

	}

	public void doSelectDropdownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);

	}

	public void doSelectDropdownByVisisbleText(By locator, String dropDownValue) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(dropDownValue);

	}

	public int getDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions().size();

	}

	/// can select drpdown value without using select class methods
	public void getCountAndSelectDropdownValue(By locator, String dropDownValue) {
		Select select = new Select(getElement(locator));
		List<WebElement> countryList = select.getOptions();
		System.out.println(countryList.size());
		for (WebElement e : countryList) {
			String text = e.getText();
			System.out.println(text);

			if (text.equals(dropDownValue)) {
				e.click();
				break;
			}
		}

	}

	//// dropdown handle without select
	public void selectDropDownValue(By locator, String dropdownValue) {
		List<WebElement> optionList = driver.findElements(locator);
		for (WebElement e : optionList) {
			String text = e.getText();

			if (text.equals(dropdownValue)) {
				e.click();
				break;
			}
		}
	}

	// this method is used to select value from dropdown. It can select:
	// 1 single value
	// 2 multiple value
	// 3 All selection : please pass "all"as value to select all options
	public void selectDropdownMultipleValues(By locator, By optionLocator, String... values) {
		Select select = new Select(driver.findElement(locator));
		if (dropdownIsMultiple(locator)) {
			if (values[0].equalsIgnoreCase("all")) {
				List<WebElement> optionList = driver.findElements(optionLocator);
				for (WebElement e : optionList) {
					e.click();
				}

			} else {
				for (String value : values) {
					select.selectByVisibleText(value);
				}
			}
		}

	}

	/// method to check if dropdown is multiple selection
	public boolean dropdownIsMultiple(By locator) {
		WebElement ele = driver.findElement(locator);
		Select select = new Select(ele);
		if (select.isMultiple()) {
			return true;
		} else {
			return false;
		}

		// select.isMultiple()? true:false;
	}

	///// Footer links collections

	public void getFooterlinks(By locator) {
		List<WebElement> footerList = getElements(locator);
		System.out.println(footerList.size());
		for (WebElement e : footerList) {
			String linkText = e.getText();
			System.out.println(linkText);

		}
	}

	// *****************************Actions
	// Util********************************************************

	///// this method will handle multiple parent-subchild selection

	public void multiMenuHandle(By parentMenuLocator, By firstChildMenuLocator, By secondChildMenuLocator,
			By fourthChildMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		doClick(parentMenuLocator);
		Thread.sleep(1000);

		act.moveToElement(getElement(firstChildMenuLocator)).build().perform();
		Thread.sleep(1000);

		act.moveToElement(getElement(secondChildMenuLocator)).build().perform();
		Thread.sleep(1000);

		doClick(fourthChildMenuLocator);
	}

	/// this method will click on submenu of parent menu
	// two level parent subchild menu handling
	public void twoLevelMenuHandle(By locatorParent, By locatorChild) throws InterruptedException {

		Actions act = new Actions(driver);
		WebElement locatParentEle = driver.findElement(locatorParent);
		act.moveToElement(locatParentEle).build().perform();
		Thread.sleep(1000);
		driver.findElement(locatorChild).click();
	}

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();

	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	//// Interview question - type each word or character with some time interval
	public void sendKeysWithPause(By locator, String value) {
		Actions act = new Actions(driver);

		char ar[] = value.toCharArray();
		for (char c : ar) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(800).build().perform();
		}

	}

	// ***************************Waits utils***************

	// Interview question -: diff between presence of element and visibility of
	// element

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	
	public WebElement waitForPresenceOfElementWithIntervalTime(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */

	public WebElement waitForVisisbilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	
	public WebElement waitForVisisbilityOfElementWithIntervalTime(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}


	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not only
	 * displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForVisisbilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForVisisbilityOfElement(locator, timeOut).click();
	}

	public void doSendKeysWithWait(By locator, String value, int timeOut) {
		waitForVisisbilityOfElement(locator, timeOut).sendKeys(value);
	}
	
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(title + " title value is not present....");
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String waitForTitleContains(String fractionValue, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleContains(fractionValue))) {
				return (driver.getTitle());
			}
		} catch (TimeoutException e) {
			System.out.println("title is not present");
		}
		return null;

	}
	
	
	public String waitForUrlContains(String urlFractionValue, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlContains(urlFractionValue))) {
				return (driver.getCurrentUrl());
				}
		} catch (TimeoutException e) {
			System.out.println("URL Fraction Value is not present");
		}
		return null;

	}
	
	public String waitForUrlToBe(String urlToBe, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.urlToBe(urlToBe))) {
				return (driver.getCurrentUrl());
			}
		} catch (TimeoutException e) {
			System.out.println("URL Value is not present");
		}
		return null;

	}
	
	
	
	///*********************Alert with wait*********************************
	
	/**
	 * This alertIsPresent method will wait for alert and also switch to alert once available we are not required to switch to alert seperately
	 * @param timeOut
	 * @return
	 */
	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.alertIsPresent());
		
		}
	
	public void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
		
	}
	
	public void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}
	
	public String getTextOfJSAlert(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}
	
	public void enterValueOnJSAlert(String value, int timeOut) {
		waitForJSAlert(timeOut).sendKeys(value);
	}

}
