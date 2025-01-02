package com.tka.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tka.utilities.SeleniumUtilites;

import jxl.read.biff.BiffException;

@Listeners(com.tka.listeners.test.TestNGListners.class)
public class DashboardTestCases {
	WebDriver driver = null;
	SoftAssert softAssert = null;

	@BeforeClass
	void EnvSetup() throws IOException {

		driver = SeleniumUtilites.openBrowser();
		String url = SeleniumUtilites.readPropertiesFile("dashboard.url");
		SeleniumUtilites.openAnyurl(driver, url);

	}

	@AfterClass
	public void CloseBrowser() {
		driver.quit();

	}

	@Test(enabled = true, priority = 1)
	public void verfiyPageTitle() {

		@Nullable
		String actresult = driver.getTitle();

		String expesult = "JavaByKiran | Dashboard";
		Assert.assertEquals(actresult, expesult, "title not found");

	}

	@Test(enabled = true, priority = 2)
	public void verifyNavigationMenuLinks() {
		Assert.assertTrue(driver.findElement(By.linkText("Dashboard")).isDisplayed(),
				"Dashboard link is not displayed!");
		Assert.assertTrue(driver.findElement(By.linkText("Users")).isDisplayed(), "Users link is not displayed!");
		Assert.assertTrue(driver.findElement(By.linkText("Operators")).isDisplayed(),
				"Operators link is not displayed!");
		Assert.assertTrue(driver.findElement(By.linkText("UsefulLinks")).isDisplayed(),
				"Useful Links link is not displayed!");
		Assert.assertTrue(driver.findElement(By.linkText("Downloads")).isDisplayed(),
				"Downloads link is not displayed!");
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Logout link is not displayed!");
	}

	@Test(enabled = true, priority = 3)
	public void verifyAllCourseCardHedings() throws BiffException, IOException {
		List<String> actresult = new ArrayList<String>();
		List<String> excel = SeleniumUtilites.readExcel("D:\\adminmgmt.xls", 0);
		for (int i = 1; i <= 4; i++) {
			String Actresult = SeleniumUtilites.GetAnyText(driver,
					"/html/body/div/div[1]/section[2]/div/div[" + i + "]/div/div[1]/h3");

			actresult.add(Actresult);
		}
		Assert.assertEquals(actresult, excel);
	}

	@Test(enabled = false, priority = 4)
	public void verifyAllCourseCardSubHedings() throws BiffException, IOException {
		List<String> actresult = new ArrayList<String>();
		List<String> excel = SeleniumUtilites.readExcel("D:\\adminmgmt.xls", 0);
		for (int i = 1; i <= 4; i++) {
			String Actresult = SeleniumUtilites.GetAnyText(driver,
					"/html/body/div/div[1]/section[2]/div/div[" + i + "]/div/div[1]/p");

			actresult.add(Actresult);
		}
		Assert.assertEquals(actresult, excel);

	}

	@Test(enabled = false, priority = 5)
	public void verifyNEvigationMenuText() throws BiffException, IOException {
		List<String> actresult = new ArrayList<String>();

		List<String> excel = SeleniumUtilites.readExcel("D:\\adminmgmt.xls", 1);
		for (int i = 2; i <= excel.size(); i++) {
			String Act = SeleniumUtilites.GetAnyText(driver, "/html/body/div/aside/section/ul/li[" + i + "]/a/span");

			actresult.add(Act);
		}

		Assert.assertEquals(actresult, excel);
	}

}
