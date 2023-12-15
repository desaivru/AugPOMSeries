package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		Object productdata[][] =ExcelUtil.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
		return productdata;
	}

	@Test(dataProvider = "getSearchData")
	public void productImageCountTest(String searchKey, String productName, String imageCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(String.valueOf(productInfoPage.getProductImageCount()), imageCount);
	
	}
	
	
		
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productMap = productInfoPage.getProductDetails(); // can not verify all values at same time
//		Assert.assertEquals(productMap.get("Brand"), "Apple"); //hard assertion - if one assertion fail it will not execute ahead
//		Assert.assertEquals(productMap.get("Product Code"), "Product 18");
//		Assert.assertEquals(productMap.get("Reward Points"), "800");
//		Assert.assertEquals(productMap.get("Availability"), "In Stock");
//		
//		Assert.assertEquals(productMap.get("price"), "$2,000.00");
//		Assert.assertEquals(productMap.get("Tax"), "$2,000.00");
		
		
		softAssert.assertEquals(productMap.get("Brand"), "Apple"); //soft assertion - if one assertion fail it will execute ahead
		softAssert.assertEquals(productMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productMap.get("Reward Points"), "800");
		softAssert.assertEquals(productMap.get("Availability"), "In Stock");
		
		softAssert.assertEquals(productMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productMap.get("Tax"), "$2,000.00");
		
		softAssert.assertAll();
	}

}
