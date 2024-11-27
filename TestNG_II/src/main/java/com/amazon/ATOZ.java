package com.amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ATOZ {

	WebDriver driver = new ChromeDriver();
	
	public void allbuttontxt() {
		driver.get("https://thekiranacademy.com/test/online-exam");
	//	List<WebElement> element = (List<WebElement>) driver.findElement(By.className("nav-item"));
		List<WebElement> elements = driver.findElements(By.className("nav-item"));
		for (WebElement aa : elements) {
			
			WebElement element2 = aa.findElement(By.tagName("a"));
			System.out.println(element2.getText());
			
		}
	    
	}

//	public String testTKAlearn() {
//
//		driver.get("https://thekiranacademy.com/test/online-exam");
//
//		WebElement element = driver.findElement(By.xpath("//*[@id=\"MERN-STACK\"]/div/div[1]/a/p"));
//		String text = element.getText();
//		System.out.println(text);
//		return text;
//
//	}
//
//	public String testTxt2() {
//
//		driver.get("https://thekiranacademy.com/test/online-exam");
//		WebElement element = driver.findElement(By.xpath("//*[@id=\"MERN-STACK\"]/div/div[2]/a/p"));
//		String text = element.getText();
//		System.out.println(text);
//		return text;
//
//	}
//	
//	public void homeButtontxt() {
//		driver.get("https://thekiranacademy.com/test/online-exam");
//		WebElement element = driver.findElement(By.xpath("//*[@id=\"navbarResponsive\"]/ul/li[1]/a"));
//		String text = element.getText();
//		System.out.println(text);
//		
//	}	
//	public void alltext() {
//		System.out.println(1);
//		
//		driver.get("https://thekiranacademy.com/test/online-exam");
//		System.out.println(2);
//		@SuppressWarnings("unchecked")
//		List<WebElement> element = (List<WebElement>) driver.findElement(By.className("col-md-2 subject-list"));
//		System.out.println(3);
//		for (WebElement wb : element) {
//			WebElement tag = wb.findElement(By.tagName("p"));
//			System.out.println("text inside p :" + tag);
//			
//		}
//	
	// }

	public static void main(String[] args) {

		ATOZ a = new ATOZ();
		a.allbuttontxt();
	//	a.homeButtontxt();
		// a.alltext();

//		a.testTKAlearn();
//		a.testTxt2();
	}

}
