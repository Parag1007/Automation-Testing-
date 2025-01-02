package com.tka.testcases;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tka.utilities.SeleniumUtilites;

public class LoginTestCases {
	WebDriver driver = null;

	@BeforeMethod
	public void envSetup() throws FileNotFoundException {
		driver = SeleniumUtilites.openBrowser();
		String loginurl = SeleniumUtilites.readPropertiesFile("loginpage.url");
		SeleniumUtilites.openAnyurl(driver, loginurl);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void close() {
		driver.quit();

	}

	@Test(enabled = true)
	public void checkSubHeadings() {

		String Actresult = SeleniumUtilites.GetAnyText(driver, "/html/body/div/div[2]/p[1]");
		String expresult = "Sign in to start your session";
		Assert.assertEquals(Actresult, expresult);
	}

	@Test(dataProvider = "loginData" , enabled = true )
	public void TestinvalidCredentials(String email, String pass) {

		SeleniumUtilites.enterText(driver, "//*[@id=\"email\"]", email);
		SeleniumUtilites.enterText(driver, "//*[@id=\"password\"]", pass);
		SeleniumUtilites.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");
		String getAnyText1 = SeleniumUtilites.GetAnyText(driver, "//*[@id=\"email_error\"]");
		String passtext = SeleniumUtilites.GetAnyText(driver, "//*[@id=\"password_error\"]");
		 System.out.println(getAnyText1+"\n  "+ passtext);
		 
	}

	@DataProvider
	public Object[][] loginData() {

		return new Object[][] {

				new Object[] { "ffffff@gmail.com", "abcf" }, new Object[] { "ssss@gmail.com", " " },
				new Object[] { " ", "" }, new Object[] { " ", "qqqwww" }, };

	}
	
	@Test (enabled = true)
	void ValidCredential() throws FileNotFoundException {
		String id = SeleniumUtilites.readPropertiesFile("login.id");
		String pass = SeleniumUtilites.readPropertiesFile("login.pass");
		SeleniumUtilites.enterText(driver, "//*[@id=\"email\"]", id);
		SeleniumUtilites.enterText(driver, "//*[@id=\"password\"]", pass);
		SeleniumUtilites.clickButton(driver, "//*[@id=\"form\"]/div[3]/div/button");
		String acturesult = SeleniumUtilites.GetAnyText(driver, "/html/body/div/div[1]/section[1]/ol/li[2]");
		String expresult = "dashboard";
		Assert.assertEquals(acturesult,expresult);
	}
	
}
