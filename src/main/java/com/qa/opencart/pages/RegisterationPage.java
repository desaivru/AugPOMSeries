package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	protected By firstName = By.xpath("//input[@name='firstname']");
	protected By lastName = By.xpath("//input[@name='lastname']");
	protected By email = By.xpath("//input[@name='email']");
	protected By phone = By.xpath("//input[@name='telephone']");
	protected By password = By.xpath("//input[@name='password']");
	protected By pwdConfirm = By.xpath("//input[@name='confirm']");
	protected By subscribYes = By.xpath("(//div[@class='form-group']/div/label)[1]");
	protected By subscribNo = By.xpath("(//div[@class='form-group']/div/label)[2]");
	protected By privacyPolicy = By.name("agree");
	protected By continueBtn = By.xpath("//input[@value='Continue']");
	protected By userSuccessMsg = By.xpath("//div[@id='content']/h1");
	protected By logout = By.linkText("Logout");
	protected By register = By.linkText("Register");

	public RegisterationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	
	public boolean createUser(String firstName, String lastName,  String email, String phone, String password,
			String subscrib) {
		eleUtil.waitForVisisbilityOfElement(this.firstName, AppConstants.SHORT_DEFAULT_WAIT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.phone, phone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(pwdConfirm, password);

		if (subscrib.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribYes);
		} else {
			eleUtil.doClick(subscribNo);
		}

		eleUtil.doClick(privacyPolicy);
		eleUtil.doClick(continueBtn);
		String successMsg = eleUtil.waitForVisisbilityOfElement(userSuccessMsg, AppConstants.SHORT_DEFAULT_WAIT)
				.getText();
		System.out.println(successMsg);

		if (successMsg.contains(AppConstants.REGISTERATION_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
			return true;
		} else {
			return false;
		}
	}

}
