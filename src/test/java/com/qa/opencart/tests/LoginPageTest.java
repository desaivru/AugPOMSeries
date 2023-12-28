package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design opencart login page")
@Story("US 101: Login page features")
@Feature("F50:Feature Login Page")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	WebDriver driver;

	@Description("Verifying login page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Description("Vrifying URL")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actualUrl = loginpage.getPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("Verifying forgot password link")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void doForgotPwdExistTest() {
		Assert.assertTrue(loginpage.isForgotPassLinkExist());
	}

	@Test(priority = 4)
	public void doLogoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}

	@Test(priority = 5)
	public void doLoginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogOutExist());
	}

}
