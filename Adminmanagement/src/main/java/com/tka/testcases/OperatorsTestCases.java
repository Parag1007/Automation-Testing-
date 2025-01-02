package com.tka.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.tka.utilities.SeleniumUtilites;

import jxl.read.biff.BiffException;

public class OperatorsTestCases {
	WebDriver driver = null;
	SoftAssert softAssert = null;

	@BeforeMethod
	void EnvSetup() throws FileNotFoundException {
		driver = SeleniumUtilites.openBrowser();
		String operatorurl = SeleniumUtilites.readPropertiesFile("operator.url");
		SeleniumUtilites.openAnyurl(driver, operatorurl);

	}

	@Test(priority = 1, enabled = false)
	public void verifyPageTitle() {
		String expectedTitle = "Operators - JavaByKiran";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
	}

	@Test(priority = 2, enabled = false)
	public void VerifyTableHeading() throws BiffException, IOException {
		List<String> Expected = SeleniumUtilites.readExcel("adminmgmt.xls", 2);
		List<String> Actual = new ArrayList<String>();
		List<WebElement> Allheaders = SeleniumUtilites.GetMultipleTexts(driver,
				" //table[@class='table table-hover']//th");
		for (WebElement headers : Allheaders) {
			String actualresult = headers.getText();
			Actual.add(actualresult);

		}
		Assert.assertEquals(Actual, Expected);

	}

	@Test(priority = 3, enabled = false)
	public void VerifyPersonColData() {
		List<WebElement> PersonAlldata = SeleniumUtilites.GetMultipleTexts(driver,
				"//table[@class='table table-hover']//tr//td[2]");
		System.out.println(" Total count of data in person col : " + PersonAlldata.size());
		for (WebElement data : PersonAlldata) {

			System.err.println(data.getText());

		}
		Assert.assertEquals(PersonAlldata.size(), 5, "size not match");

	}

	@Test
	public void VerifyThirdColData() throws BiffException, IOException {
		List<String> Expected = SeleniumUtilites.readExcel("adminmgmt.xls", 3);
		List<String> Actual = new ArrayList<String>();
		List<WebElement> PersonAlldata = SeleniumUtilites.GetMultipleTexts(driver,
				"//table[@class='table table-hover']//tr//td[3]");
		System.out.println(" Total count of data in for col : " + PersonAlldata.size());
		for (WebElement data : PersonAlldata) {

			String thirdcoldata = data.getText();
			Actual.add(thirdcoldata);

		}
		Assert.assertEquals(Actual, Expected);

	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
