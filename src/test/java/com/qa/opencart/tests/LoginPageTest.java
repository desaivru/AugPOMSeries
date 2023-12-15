package com.qa.opencart.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	WebDriver driver;

	@Test(priority =1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);

	}
	
	@Test(priority =2)
	public void loginPageUrlTest() {
		String actualUrl = loginpage.getPageURL();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
		
	}
	
	@Test(priority =3)
	public void doForgotPwdExistTest() {
		Assert.assertTrue(loginpage.isForgotPassLinkExist());
	}
	
	@Test(priority =4)
	public void doLogoExistTest() {
		Assert.assertTrue(loginpage.isLogoExist());
	}
	
	@Test(priority =5)
	public void doLoginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password")); 
		Assert.assertTrue(accPage.isLogOutExist());
	}

}
