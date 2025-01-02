package com.tka.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tka.utilities.SeleniumUtilites;

import jxl.read.biff.BiffException;

public class DownloadPageTestCases {

	WebDriver driver = null;

	@BeforeMethod
	public void envSetup() throws FileNotFoundException {
		driver = SeleniumUtilites.openBrowser();
		String downurl = SeleniumUtilites.readPropertiesFile("download.url");
		SeleniumUtilites.openAnyurl(driver, downurl);
		driver.manage().window().maximize();
	}

	@Test(enabled = true)
	public void validateDownloadLinks() {
		// List<WebElement> links = driver.findElements(By.xpath("//table//a"));
		List<WebElement> links = SeleniumUtilites.GetMultipleTexts(driver, "//table//a");
		Assert.assertTrue(links.size() > 0, "No download links found!");

		for (WebElement link : links) {
			String linkText = link.getText().trim();
			String linkHref = link.getAttribute("href");

			System.out.println("Link Text: " + linkText + ", Href: " + linkHref);

			Assert.assertFalse(linkText.isEmpty(), "Link text is empty!");

			Assert.assertTrue(linkHref != null && linkHref.startsWith("http"), "Invalid link URL: " + linkHref);
		}
	}

	@Test(enabled = true)
	public void verifyFileExtensions() {
		List<WebElement> links = SeleniumUtilites.GetMultipleTexts(driver, "//table//a");

		for (WebElement link : links) {
			String linkHref = link.getAttribute("href");

			Assert.assertTrue(linkHref.endsWith(".exe") || linkHref.endsWith(".zip") || linkHref.endsWith(".msi"),
					"Unexpected file extension: " + linkHref);
		}

	}

	@Test(enabled = true)
	public void verifyDownloadListHeaders() {

		List<WebElement> allHeaders = SeleniumUtilites.GetMultipleTexts(driver,
				"//table[@class='table table-hover']//tr//th");
		for (WebElement head : allHeaders) {
			System.out.println(head.getText());
		}

		Assert.assertEquals(allHeaders.size(), 9, "size not match");
	}

	@Test(enabled = true)
	public void verifyVendorText() throws BiffException, IOException {
		List<String> exp = SeleniumUtilites.readExcel("adminmgmt.xls", 4);
		List<String> act = new ArrayList<String>();
		List<WebElement> data = SeleniumUtilites.GetMultipleTexts(driver,
				"//table[@class='table table-hover']//tr//td[3]");

		for (WebElement ele : data) {
			String text = ele.getText();
			System.out.println(text);
			act.add(text);
		}
		Assert.assertEquals(exp, act);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
