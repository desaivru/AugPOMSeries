package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) { // call by reference from base class.
		String browserName = prop.getProperty("browser");

		// String browserName = System.getProperty("browser");
		System.out.println("Browser is: " + browserName);

		optionsManager = new OptionsManager(prop); // to call headless or incognito

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			// driver = new ChromeDriver(optionsManager.getChromeOption());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOption()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(optionsManager.getFirefoxOption());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOption()));
			break;
		case "edge":
			// driver = new EdgeDriver(optionsManager.getEdgeOption());
			tldriver.set(new EdgeDriver(optionsManager.getEdgeOption()));
			break;
		default:
			System.out.println("Please pass the right browser...." + browserName);
			throw new FrameworkException("INVALID BROWSER");

		}
		/// starting point of application
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver(); /// session id of driver is 123
	}

	public static WebDriver getDriver() {
		return tldriver.get();
	}

	public Properties initProp() {
		// mvn clean install -Denv="qa"
		// mvn clean install -> by default it should take qa
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env"); // same variable we have to use in mvn command.
		System.out.println("Environment is: " + envName);

		try {
			if (envName == null) {
				System.out.println("Your env is null hence running on qa env..");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				default:
					System.out.println("Please pass the correct environment..." + envName);
					throw new FrameworkException("INVALID ENVIRONMENT" + envName);

				}
			}
		} catch (FileNotFoundException e) {
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot(String methodName) {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
