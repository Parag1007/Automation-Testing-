package com.tka.testcases;

import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tka.utilities.SeleniumUtilites;

public class UsefulLinksTestCases {
	WebDriver driver = null;

	@BeforeMethod
	public void envSetup() throws FileNotFoundException {
		driver = SeleniumUtilites.openBrowser();
		String linkurl = SeleniumUtilites.readPropertiesFile("link.url");
		SeleniumUtilites.openAnyurl(driver, linkurl);
		driver.manage().window().maximize();
	}

	@Test
	public void validateLinks() {
		List<WebElement> links = driver.findElements(By.xpath("//table//a"));
		Assert.assertTrue(links.size() > 0, "No links found on the page!");

		for (WebElement link : links) {
			String linkText = link.getText().trim();
			String linkHref = link.getAttribute("href");

			System.out.println("Link Text: " + linkText + ", Href: " + linkHref);

			Assert.assertFalse(linkText.isEmpty(), "Link text is empty!");

			Assert.assertTrue(linkHref != null && linkHref.startsWith("http"), "Invalid link URL: " + linkHref);
		}
	}

	@AfterClass
	public void browserClose() {
		driver.quit();
	}
}
