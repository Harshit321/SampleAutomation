package com.amazon.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.amazon.qa.utils.TestUtil;
import com.amazon.qa.utils.WebEventListener;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("src/main/java/com/amazon"
			+"/qa/config/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void intialization() {
		String browserName = prop.getProperty("browser");
		String os = System.getProperty("os.name").toLowerCase();
		
		if(os.toLowerCase().contains("mac")) {
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_mac");
				driver = new ChromeDriver();
			}
			else if(browserName.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_mac");
				driver = new FirefoxDriver();
			}
		}
		
		if(os.toLowerCase().contains("linux")) {
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_linux64");
				driver = new ChromeDriver();
			}
			else if(browserName.equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "drivers/geckodriver_linux");
				driver = new FirefoxDriver();
			}
		}
		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
}
