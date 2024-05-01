package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utils.TestUtil;
import utils.WebEventListener;

public class base {
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static String url = "https://openweathermap.org/";
	
	
	public static void initialization(){
		
String browserName = "chrome"; // Assuming Chrome is default
        
        if(browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\yoges\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");    
            driver = new ChromeDriver();
        }

		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get(url);
		
	}
		

}


