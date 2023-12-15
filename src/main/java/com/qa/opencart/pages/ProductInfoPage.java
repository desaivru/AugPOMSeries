package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;



public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	protected By productHeadr = By.cssSelector("div#content h1");
	protected By productImages = By.cssSelector("ul.thumbnails li");
	protected By metaData = By.xpath("(//div[@id='content']//ul)[3]/li");
	protected By metPrice = By.xpath("(//div[@id='content']//ul)[4]/li");

	//Map<String, String> productMap = new HashMap<String, String>(); //does not maintain order
	//Map<String, String> productMap = new LinkedHashMap<String, String>();
	Map<String, String> productMap = new TreeMap<String, String>(); //give you based on alphabetical order

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getProductHeader() {
		String productheadervalue = eleUtil.getElementText(productHeadr);
		return productheadervalue;
	}

	public int getProductImageCount() {
		List<WebElement> productImageCount = eleUtil.waitForVisisbilityOfElements(productImages,
				AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Image count are: " + productImageCount.size());
		return productImageCount.size();
	}

	public void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisisbilityOfElements(metaData, AppConstants.SHORT_DEFAULT_WAIT);

		for (WebElement e : metaDataList) {
			String data = e.getText();
			String metaDataKey = data.split(":")[0].trim();
			String metaDataValue = data.split(":")[1].trim();
			productMap.put(metaDataKey, metaDataValue);
		}
	}
	
	
	public void getProductPriceData() {
		List<WebElement> metaPriceDataList =eleUtil.waitForVisisbilityOfElements(metPrice, AppConstants.SHORT_DEFAULT_WAIT);
		String productPrice = metaPriceDataList.get(0).getText();
		String productTax = metaPriceDataList.get(1).getText().split(":")[1].trim();
	
		productMap.put("price", productPrice); //we can define our own key
		productMap.put("Tax", productTax);
			}
	
	public Map<String, String> getProductDetails() {
		productMap.put("ProductName", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("Prodcut name:" +getProductHeader()+ productMap );
		return productMap;
	}
	
	
}

