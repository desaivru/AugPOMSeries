package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountPageTest extends BaseTest {

	WebDriver driver;

	// for account page precondition is - user should be logged in first.We have
	// login method in login page
	// so to call login method from LoginPage class we have to create account setup
	// method with BeforeClass annotation and can access login method with account
	// class refe.

	@BeforeClass
	public void accountSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

		// accPage = new AccountPage(driver); no need to write or create account object
		// because loginmethod is returning account object.
		// we are creating the accountpage reference so that we can call the methods
		// from AccountPage class with that reference
	}
	
	@Description("Verifying Account page title")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void accountPageTitleTest() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void isLogoutExist() {
		Assert.assertTrue(accPage.isLogOutExist());

	}

	@Test
	public void isSearchFieldExist() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> headersList = accPage.getAccountHeaders();
		System.out.println(headersList);
		Assert.assertEquals(headersList.size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	}

	@Test
	public void accPageHeadersTest() {
		List<String> headersList = accPage.getAccountHeaders();
		Assert.assertEquals(headersList, AppConstants.ACC_PAGE_HEADERS_LIST);
	}
	
	
	@Test
	public void searchTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeader();
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}

}
