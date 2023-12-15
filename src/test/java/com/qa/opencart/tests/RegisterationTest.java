package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterationTest extends BaseTest {

	@BeforeClass
	public void registerationSetUp() {
		registerationPage = loginpage.navigateToRegister();
	}

	public String generateRandomEmail() {
		return "testautomation@" + System.currentTimeMillis() + "opencart.com";
		
		//return "testautomation" + UUID.randomUUID()+ "opencart.com";
	}

//	@DataProvider
//	public Object[][] getUserData() {
//		return new Object[][] { { "Naru", "Gawde", "9845612357", "naru@123", "no" },
//				{ "Naru", "Gawde", "9845612357", "naru@123", "no" } };
//	}
	
	@DataProvider
	public Object[][] getRegiUserExcelData() {
		Object regisData[][] = ExcelUtil.getTestData(AppConstants.REGISTERATION_DATA_SHEET_NAME);
		return regisData;
	}

	@Test(dataProvider = "getRegiUserExcelData")
	public void createUserTest(String firstName, String lastName, String phone, String password, String subscrib) {
		// boolean isUserCreated = registerationPage.createUser("Vrushali", "Gawade",
		// "oencart@test.com", "7894561236", "Test!741", "yes");
		boolean isUserCreated = registerationPage.createUser(firstName, lastName, generateRandomEmail(),phone, password, subscrib);

		Assert.assertTrue(isUserCreated);
	}

}
