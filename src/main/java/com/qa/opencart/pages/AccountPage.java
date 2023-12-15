package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage{
	
	protected WebDriver driver;
	protected ElementUtil eleUtil;
	
	
	//Page constant..
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		
	}
	
	
	private By myAccountOrderHeader = By.xpath("(//div[@id='content']/h2)[2]");
	private By myAffiAccountHeader= By.xpath("(//div[@id='content']/h2)[3]");
	private By newsLetterHeader = By.xpath("(//div[@id='content']/h2)[4]");
	
	private By myAccountHeaderList = By.xpath("//div[@id='content']/h2");
	private By search = By.name("search");
	private By searchLocator= By.cssSelector("div#search button");
	private By logOut = By.linkText("Logout");
	
	
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println(title);
		return title;
		
	}
	
	public boolean isLogOutExist() {
		return eleUtil.waitForVisisbilityOfElement(logOut, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logOut() {
		if(isLogOutExist()) {
			eleUtil.doClick(logOut);
		}
	}
	
	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisisbilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
	}
	
	public List<String> getAccountHeaders() {
		List<WebElement> accHeaderList = eleUtil.waitForVisisbilityOfElements(myAccountHeaderList, AppConstants.SHORT_DEFAULT_WAIT);
		List<String> headersList = new ArrayList<String>(); // to store all the header we created arraylist.
		for(WebElement e: accHeaderList) {
			String header = e.getText();
			headersList.add(header);
		}
		return headersList;
			
	}
	
	//Search the product
	public SearchResultPage doSearch(String searchKey) {
		eleUtil.waitForVisisbilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doClick(searchLocator);
		return new SearchResultPage(driver); // TDD approach as we created object first andten created class.
		
		
	}
	
	

}
