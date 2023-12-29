package com.qa.opencart.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public String getTitleByJs() {
	return js.executeScript("return document.title").toString();
	}
	
	
	public String getURLByJs() {
		return js.executeScript("return document.URL").toString();
	}
	
	
	public void generateJsAlert(String msg) {
		js.executeScript("alert('"+msg+"')");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}
	
	
	public void generateJsPrompt(String msg, String input) {
		js.executeScript("prompt('"+msg+"')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(input);
		alert.accept();
	}
	
	public void generateJsConfirm(String msg) {
		js.executeScript("confirm('"+msg+"')");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	
	
	
	
	
	}	
}
