package com.tka.testcases;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Table.Cell;
import com.tka.utilities.SeleniumUtilites;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UserPageTest {

	WebDriver driver = null;
	SoftAssert softAssert = null;

	@BeforeMethod
	void EnvSetup() throws FileNotFoundException {
		driver = SeleniumUtilites.openBrowser();
		String userurl = SeleniumUtilites.readPropertiesFile("userpage.url");
		SeleniumUtilites.openAnyurl(driver, userurl);

	}

	@AfterMethod
	public void CloseBrowser() {
		driver.quit();

	}

	@AfterClass
	public void softassert() {
		softAssert.assertAll();
	}

	@Test(enabled = true)
	public void testAddUserButton() {

		// SeleniumUtilites.clickButton(driver, "//button[@class='btn btn-block
		// btn-primary btn-sm pull-right']");
		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driverWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='btn btn-block btn-primary btn-sm pull-right']")))
				.click();
		String actualresult = SeleniumUtilites.GetAnyText(driver, "//section[@class='content-header']/h1");

		String exp = "Add User";
		Assert.assertEquals(actualresult, exp);
	}

	@Test(enabled = true)
	public void VerifyUserListHeadingText() throws BiffException, IOException {
		List<String> acturesult = new ArrayList<String>();
		List<String> expresult = SeleniumUtilites.readExcel("D:\\adminmgmt.xls", 1);

		List<WebElement> getMultipleTexts = driver
				.findElements(By.xpath("//table[@class='table table-hover']/tbody/tr/th"));

		for (WebElement act : getMultipleTexts) {

			String actual = act.getText();
			acturesult.add(actual);
		}
		assertEquals(acturesult, expresult);
	}

	@Test(enabled = true)
	public void VerifyListData() {

		List<String> Expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		for (int i = 0; i <= 8; i++) {

			List<WebElement> Listdata = SeleniumUtilites.GetMultipleTexts(driver,
					"//table[@class='table table-hover']/tbody/tr[" + i + "]/td");
			for (WebElement data : Listdata) {

				actual.add(data.getText());

			}

		}
		Assert.assertEquals(actual, Expected);

	}

	@Test(enabled = true)
	public void verifyListData() throws IOException {

		Object[][] excelData = SeleniumUtilites.getExcelData("UserData.xlsx");
		for (Object[] objects : excelData) {
			String string = Arrays.toString(objects);
			System.out.println(string);
		}

		List<String> Expected = new ArrayList<String>();
		List<String> actual = new ArrayList<String>();
		for (int i = 0; i <= 8; i++) {

			List<WebElement> Listdata = SeleniumUtilites.GetMultipleTexts(driver,
					"//table[@class='table table-hover']/tbody/tr[" + i + "]/td");

			Object[] array = Listdata.toArray();
			String string = array.toString();
			System.out.println(string.toString());

		}
		Assert.assertEquals(actual, Expected);

	}

	@Test(enabled = true)
	public void validateAddUserForm() {

		SeleniumUtilites.clickButton(driver, "//button[@class='btn btn-block btn-primary btn-sm pull-right']");
		WebDriverWait driverwait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driverwait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[@class='btn btn-block btn-primary btn-sm pull-right']")))
				.click();
		SeleniumUtilites.enterText(driver, "//input[@id=\"username\"]", "Parag2001");
		SeleniumUtilites.enterText(driver, "//input[@id=\"mobile\"]", "9422982240");
		SeleniumUtilites.enterText(driver, " //input[@id=\"email\"]", "kakadparag2001@gmail.com");
		SeleniumUtilites.enterText(driver, "//input[@id=\"course\"]", "JAVA/j2EE");
		SeleniumUtilites.clickButton(driver, "//input[@id=\"Male\"]");
		WebElement webelement = SeleniumUtilites.clickButton(driver, "//select[@class='form-control']");
		Select select = new Select(webelement);
		select.selectByIndex(1);
		SeleniumUtilites.enterText(driver, "//input[@id=\"password\"]", "pass123");
		SeleniumUtilites.clickButton(driver, "//button[@id=\"submit\"]");
		Alert alert = driver.switchTo().alert();
		String actresult = alert.getText();
		String expresult = "User Added . You can not see added user.";
		Assert.assertEquals(actresult, expresult, "pop up not found");

	}

	@Test(enabled = true)
	public void verifyCancelButton() {
		SeleniumUtilites.clickButton(driver, "//button[@class='btn btn-block btn-primary btn-sm pull-right']");
		SeleniumUtilites.clickButton(driver, "//span[@class=\"btn btn-default pull-right\"]");
		String actualresult = SeleniumUtilites.GetAnyText(driver, "//h3[@class=\"box-title\"]");
		String exp = "User List";
		Assert.assertEquals(actualresult, exp, "pop up not found");

	}

	@Test(enabled = true)
	public void validateDeletebutton() {
		SeleniumUtilites.clickButton(driver, "//span[@class='label label-danger']");
		Alert alert = driver.switchTo().alert();
		String actresult = alert.getText();

		String exp = "You can not delete Default User";
		Assert.assertEquals(actresult, exp);

	}

}