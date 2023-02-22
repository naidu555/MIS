package com.Ebanking.TestCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.Ebanking.PageObjects.LoginPage;
   
public class TC_Logintest_001 extends BaseClass {
	 LoginPage lp;
	@Test
	public void Logintest() {
		driver.get(baseUrl);
		logger.info("url is opened");
		 lp=new LoginPage(driver);
		lp.setUserName(userName);
		logger.info("username entered");
		lp.setPassword(password);
		logger.info("password entered");
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 BankManger Homepage")) {
			Assert.assertTrue(true);
			logger.info("login test passed");
		}
		else
		{
			getScreenshot(driver,"Logintest");
			Assert.assertFalse(false);
			logger.error("login test failed");
		}
	}
}
