package com.tka;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AAA {

	
	
	public  String tetNGAutomation()  throws Exception {
		
		FileInputStream fis = new FileInputStream("configuration.properties");
		WebDriver driver = new ChromeDriver();
		Properties properties = new Properties();
		properties.load(fis);
		
		String url = properties.getProperty("url");
		driver.get(url);
		WebElement element = driver.findElement(By.xpath("/html/body/div/div/h1"));
		String text = element.getText();
		System.out.println(text);
		return text; 
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		AAA c = new AAA();
		c.tetNGAutomation();
		
	}
	
	
	
	
	
}
