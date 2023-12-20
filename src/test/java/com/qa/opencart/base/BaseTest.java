package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationPage;
import com.qa.opencart.pages.SearchResultPage;

public class BaseTest {

	protected WebDriver driver; // the session id 123 also given to this driver
	DriverFactory df; // reference variable of DriverFactory
	protected LoginPage loginpage; // reference variable of LoginPage class/ making it protected because only
									// child class in other package can access it not all the member.and also making
									// it basetest class ref variable because all Base test class child can access
									// it.

	protected AccountPage accPage;
	protected Properties prop;
	protected SearchResultPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterationPage registerationPage;

	protected SoftAssert softAssert;

	// we are not going to initialize driver here again because it is already in
	// initialize in driver factory class
	// we just need to call driver initialize method here
	// so to call the method we have to create object of driver factory class
	// creating driver factory object reference at the class level so that can use
	// it
	// throughout the class.

	@Parameters("browser")
	@BeforeTest
	public void setup(String browserName) // this browsername comes from testng.xml
	{
		df = new DriverFactory(); // object of driver factory
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop); // the same session id 123 given to this driver
		loginpage = new LoginPage(driver); // session id 123 given to Loginpage driver
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
