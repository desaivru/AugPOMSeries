package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;  // making it private so that nobody can access outside the class
	private ElementUtil eleUtil; // reference of element util class to create oject.
	
	
	//By locators or we can called as Object repository
	//making locators private because no one can access login class locators these are specific to login page
	
	//we are using private variable in public method so here we are achieving encapsulation. private driver/locators is using in public method 
	//so encapsulation is not only using getter and setter here also we are using encapsulation
	protected By emailId = By.id("input-email");
	protected By pwd = By.id("input-password");
	protected By loginBtn = By.xpath("//input[@value='Login']");
	protected By forgotPassword = By.linkText("Forgotten Password");
	protected By logo = By.xpath("//img[@title='naveenopencart']");
	protected By allRigtSideLink = By.xpath("//div[@class='list-group']//a"); //all 13 links
	
	protected By newCustomerText = By.xpath("(//div[@class='well']/h2)[1]");
	
	protected By registerLink = By.linkText("Register");
	
	
	//to get the driver from DriverFactory driver we need to create constructor of the page
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver); //object of elementUtil class - session id 123 given to eleUtil
		
		
	}
	
	//page action/page method
	@Step("Fetching login title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE,AppConstants.SHORT_DEFAULT_WAIT);
		
//		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	@Step("Fetching url")
	public String getPageURL() {
		String Url = eleUtil.waitForUrlContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		
		//String Url = driver.getCurrentUrl();
		System.out.println(Url);
		return Url;
	}
	
	public boolean isForgotPassLinkExist() {
		return eleUtil.waitForVisisbilityOfElement(forgotPassword, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		//return driver.findElement(forgotPassword).isDisplayed();
		
	}
	
	public boolean isLogoExist() {
		return eleUtil.waitForVisisbilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		
		//return driver.findElement(logo).isDisplayed();
	}

	public AccountPage doLogin(String username, String password) {
		eleUtil.waitForVisisbilityOfElement(emailId, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		return new AccountPage(driver);
	}
	
	
	public RegisterationPage navigateToRegister() {
		eleUtil.waitForVisisbilityOfElement(registerLink, AppConstants.SHORT_DEFAULT_WAIT).click();
		 return new RegisterationPage(driver);
		
	}
}
		
		//whenever you click on any button and that land you on new page then it is mandatory to handle the navigation of landing page in that method itself.
		//to handle the navigation we have to create the class object of new landing page and return it.
		//pass the driver so that account page constructor will get the same driver.
		
//		driver.findElement(emailId).sendKeys(username);
//		driver.findElement(pwd).sendKeys(password);
//		driver.findElement(loginBtn).click();
		
	
