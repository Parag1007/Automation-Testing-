package com.tka.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SeleniumUtilites {
	static WebDriver driver = null;

	public static WebDriver openBrowser() {
		try {
			driver = new ChromeDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		return driver;

	}

	public WebDriver browseropen(String brw, String url) {

		if (brw.equalsIgnoreCase("ch") || brw.equalsIgnoreCase("Chrome")) {

			driver = new ChromeDriver();

		} else if (brw.equalsIgnoreCase("ff") || brw.equalsIgnoreCase("Firefox")) {

			driver = new FirefoxDriver();
		} else if (brw.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		} else {
			System.out.println("Enter valid browser name..!!");
		}

		driver.get(url);
		return driver;
	}

//	public static WebDriver closeBrowser() {
//		WebDriver driver = new ChromeDriver();
//		driver.quit();
//		return driver;
//
//	}

	public static void openAnyurl(WebDriver driver, String url) {
		driver.get(url);
	}

	public static WebElement clickButton(WebDriver driver, String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();
		return element;
	}

	public static void enterText(WebDriver driver, String xpath, String data) {
		driver.findElement(By.xpath(xpath)).sendKeys(data);
	}

	public static String GetAnyText(WebDriver driver, String xpath) {
		String text = driver.findElement(By.xpath(xpath)).getText();
		return text;
	}

	public static List<WebElement> GetMultipleTexts(WebDriver driver, String xpath) {
		List<WebElement> txt = driver.findElements(By.xpath(xpath));

		return txt;
	}

	public static List<String> readExcel(String path, int sheetNO) throws BiffException, IOException {

		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = Workbook.getWorkbook(fis);
		Sheet sheet = workbook.getSheet(sheetNO);
		List<String> exe = new ArrayList<String>();
		int rows = sheet.getRows();
		int columns = sheet.getColumns();
		for (int i = 0; i < rows; i++) {

			for (int j = 0; j < columns; j++) {

				Cell cell = sheet.getCell(j, i);
				String contents = cell.getContents();

				exe.add(contents);
			}

		}
		return exe;
	}

//	public static List<String[]> readExcelRowData(String path, int sheetNO , int size ) throws BiffException, IOException {
//		System.out.println(1);  
//		FileInputStream fis = new FileInputStream(path);
//		System.out.println(2);  
//		  XSSFWorkbook workbook = new XSSFWorkbook(fis);
//		  System.out.println(3);  
//		  
//		  XSSFSheet sheetAt = workbook.getSheetAt(sheetNO);
//		  System.out.println(4);  
//		   List<String[]> ExcelRowData = new ArrayList<>();
//	        for (Row row : sheetAt) {
//	            String[] rowData = new String[size];
//	          for (int i = 0; i < rowData.length; i++)
//	          
//	          for (int j = 0; j < rowData.length; j++) {
//				
//	        	  rowData[i] = row.getCell(j).getStringCellValue(); // Title
//			}
//	          {
//	        	  
//	        	 
//	        	  
//			}
//	           
//	            //rowData[1] = row.getCell(1).getStringCellValue(); // Description
//	            ExcelRowData.add(rowData);
//	        }
//	        System.out.println(5);
//		
//		return ExcelRowData;
//	
//	
//	}

	public static String readPropertiesFile(String Anykey) throws FileNotFoundException {

		FileInputStream fis = new FileInputStream("config.properties");
		Properties properties = new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {

			e.printStackTrace();
		}
		String property = properties.getProperty(Anykey);

		return property;
	}

	public static Object[][] getExcelData(String path) throws IOException {
		String excelFilePath = path; // Update with the correct file path
		FileInputStream fileInputStream = new FileInputStream(excelFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		List<String> D = new ArrayList<String>();

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
				data[i - 1][j] = cell.toString();
			}

		}

		workbook.close();
		return data;
	}

}