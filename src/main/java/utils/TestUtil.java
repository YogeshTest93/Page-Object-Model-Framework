package utils;

import Base.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestUtil extends base {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String TESTDATA_SHEET_PATH = "C:/Users/yoges/eclipse-workspace/POM-Framework/src/main/java/TestData/Weather_TestData.xlsx";
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
	    FileInputStream file = null;
	    try {
	        file = new FileInputStream(TESTDATA_SHEET_PATH);
	        book = WorkbookFactory.create(file);
	        sheet = book.getSheet(sheetName);
	        int cols = sheet.getRow(0).getLastCellNum();
	        Object[][] data = new Object[1][cols];	        
	        for (int k = 0; k < cols; k++) {
	            if (sheet.getRow(0) != null && sheet.getRow(0).getCell(k) != null) {
	                data[0][k] = sheet.getRow(0).getCell(k).toString();
	            } else {
	                data[0][k] = "";
	            }
	        }

	        return data;
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (InvalidFormatException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (file != null) {
	                file.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return null; 
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}