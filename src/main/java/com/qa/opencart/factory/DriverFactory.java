package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	// code of driver initialization
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public WebDriver initDriver(Properties prop) { //call by reference from base class.													
		String browserName = prop.getProperty("browser");
		System.out.println("Browser is: " + browserName);
		
		optionsManager = new OptionsManager(prop); //to call headless or incognito

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOption());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOption());
			break;
		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOption());
			break;
		default:
			System.out.println("Please pass the right browser...." + browserName);
			throw new FrameworkException("INVALID BROWSER");

		}
		/// starting point of application
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver; /// session id of driver is 123
	}

	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
