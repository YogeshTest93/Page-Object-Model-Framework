package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.base;

public class WeatherPage extends base {
	
	@FindBy(name = "q")
    WebElement searchInput;

    @FindBy(xpath = "//button[@data-request='onDecline']")
    WebElement declineButton;

    @FindBy(xpath = "//*[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/b[1]/a")
    WebElement firstWeatherCityLink;

    @FindBy(xpath = "//span[@class='heading']")
    WebElement temperatureLabel;

    @FindBy(xpath = "//span[text()='Humidity:']//ancestor::li")
    WebElement humidityLabel;

    @FindBy(xpath = "//h2[text()='Weather in your city']")
    WebElement weatherInYourCitySection;

    @FindBy(xpath = "//*[@id='search_str']")
    WebElement searchInputMap;

    @FindBy(xpath = "//div[@id='forecast_list_ul']/div")
    WebElement alertText;

    @FindBy(xpath = "(//a[text()='Maps'])[1]")
    WebElement mapsSectionLink;
    
	@FindBy(xpath = "//*[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/b[1]/a")
	WebElement searchResults;

	public WeatherPage() {
		PageFactory.initElements(driver, this);
	}

	public void searchCity(String place) {
		searchInput.sendKeys(place);
		searchInput.sendKeys(Keys.ENTER);
	}
	

	public void clickFirstCityLink() {
		firstWeatherCityLink.click();
	}

	public String getTemperature() {
		return temperatureLabel.getText();
	}

	public String getHumidity() {
		return humidityLabel.getText();
	}

	public boolean isWeatherInYourCitySectionDisplayed() {
		return weatherInYourCitySection.isDisplayed();
	}

	public String getAlertText() {
		return alertText.getText();
	}

	public void navigateToMapsSection() {
		mapsSectionLink.click();
	}

	public void switchToMapLayer(String layerName) {
		WebElement layer = driver.findElement(By.xpath("//span[contains(text(), '" + layerName + "')]"));
		layer.click();
	}

	public boolean isSearchResultsDisplayed() {
        return searchResults.isDisplayed();
    }

	public void searchCityOnMap(String cityName) {
		searchInputMap.clear();
		searchInputMap.sendKeys(cityName);
		searchInputMap.sendKeys(Keys.ENTER);
	}
}
