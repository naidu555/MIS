package com.Ebanking.TestCases;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.Ebanking.Utilities.Readconfig;

public class BaseClass 
	{
	Readconfig readconfig=new Readconfig();
	public String baseUrl=readconfig.getApplicationUrl();
	public String userName=readconfig.getuserName();
	public String password=readconfig.getpassword();
	public static WebDriver driver;
	public Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) 
	{	
		logger=Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
	if(br.equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getchropath());
		driver=new ChromeDriver();
		}
	else if(br.equals("Firefox"))
	{
		System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
		driver=new FirefoxDriver();
		
	}
	else if(br.equals("IE"))
	{
		System.setProperty("webdriver.IE.driver",readconfig.getIEpath());
		driver=new InternetExplorerDriver();
	}
	
		
	} 
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
	public void getScreenshot(WebDriver driver,String tname) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"//Screenshots//"+tname+".png");
		System.out.println("Screeenshot taken");
}

}
