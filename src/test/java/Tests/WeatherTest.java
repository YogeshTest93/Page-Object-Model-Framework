package Tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Base.base;
import Pages.WeatherPage;
import utils.TestUtil;

public class WeatherTest extends base{

	WeatherPage weatherPage;
	String sheetName = "sheet1";
	
    public WeatherTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        weatherPage = new WeatherPage();
    }
      
  @DataProvider
	public Object[][] getWeatherTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
  
  @Test(priority = 1, dataProvider="getWeatherTestData")
  public void testSearchFunctionality(String place) throws InterruptedException {
      weatherPage.searchCity(place);
      Thread.sleep(2000);
      Assert.assertTrue(weatherPage.isSearchResultsDisplayed(), "Search results are not displayed");  
      Thread.sleep(2000);
      weatherPage.clickFirstCityLink();
  }

  @Test(priority = 2)
  public void testWeatherDetailsVerification() throws InterruptedException {
      weatherPage.searchCity("Delhi, IN");
      Thread.sleep(2000);
      weatherPage.clickFirstCityLink();
      Thread.sleep(2000);
      Assert.assertTrue(weatherPage.getTemperature().contains("°C"));
      Assert.assertTrue(weatherPage.getHumidity().contains("%"));
  }

  @Test(priority = 3)
  public void testWeatherInYourCitySection() throws InterruptedException {
  	weatherPage.searchCity("Delhi, IN");
  	Thread.sleep(2000);
      weatherPage.clickFirstCityLink();
      Thread.sleep(2000);
      driver.navigate().back();
      Thread.sleep(2000);
      Assert.assertTrue(weatherPage.isWeatherInYourCitySectionDisplayed());
  }
  
  @Test(priority = 4)
  public void testInvalidCityName() throws InterruptedException {
      weatherPage.searchCity("Invalid City");
      Thread.sleep(2000);
      String actualAlertText = weatherPage.getAlertText();
      String expectedPartialText = "Not found"; // Partial text to match
      Thread.sleep(2000);
      Assert.assertTrue(actualAlertText.contains(expectedPartialText), "Alert text does not contain expected partial text");
  }

  @Test(priority = 5)
  public void testMapsSection() {
      weatherPage.navigateToMapsSection();
      String[] layerNames = {" Temperature", " Pressure", " Wind speed", " Clouds", " Global Precipitation"};
      for (String layerName : layerNames) {
          weatherPage.switchToMapLayer(layerName);
      }
  }
    
 

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}


