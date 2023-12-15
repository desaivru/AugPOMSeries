package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	protected WebDriver driver;
	protected ElementUtil eleUtil;
	
	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.waitForVisisbilityOfElement(By.linkText(productName), AppConstants.LARGE_DEFAULT_WAIT).click();;
		return new ProductInfoPage(driver);
	}

}
