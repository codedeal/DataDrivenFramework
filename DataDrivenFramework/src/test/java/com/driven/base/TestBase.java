package com.driven.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.driven.utilities.Log;

public class TestBase 
{
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static String browser;
	public static WebDriverWait wait;
	
   // Logger log = Logger.getLogger("devpinoyLogger");
	
	 // configure log4j properties file

	@BeforeSuite
	public void setUp() {
		

		if (driver == null) {

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) 
			{
				Log.debug("Exception is --->"+e);
				e.printStackTrace();
			}
			try {
				Log.debug("loading config file");
				config.load(fis);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = config.getProperty("browser");
				
			}
			Log.info("Browser current is "+ config.getProperty("browser"));
			config.setProperty("browser", browser);
			
			
			
			Log.info("starting browser");
			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", "gecko.exe");
				
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");
				
				
				driver = new ChromeDriver();
				Log.info("started chrome browser");
				
			} else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/resources/executablesIEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}

			driver.get(config.getProperty("testsiteurl"));
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}
	
	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}

	}

}
