package framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.abstractcomponents.ReusableComponents;

public class CheckOutPage extends ReusableComponents {


	WebDriver driver;
	
	
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = "section.ta-results")
	WebElement allavailcountries;
	
	By allavailcountries2 = By.cssSelector("section.ta-results");
	
	@FindBy(css = "span.ng-star-inserted")
	WebElement allcountriestext;
	
	By allcountries=By.cssSelector("span.ng-star-inserted");
	
	@FindBy(css = "a.action__submit")
	WebElement submit;
	
	public void selectcountry(String countrytoselect) throws InterruptedException
	{
		country.click();
		country.sendKeys(countrytoselect);
		Thread.sleep(3000);
		WebElement country = allavailcountries;
		
		waitforelementtoappear(allavailcountries2);
		System.out.println(allcountriestext.getText());
		List<WebElement> countrynames =country.findElements(allcountries);
		countrynames.stream().filter(a->a.getText().equalsIgnoreCase("India")).forEach(a->a.click());
	}
	
	public ConfirmPage submit()
	{
		submit.click();
		ConfirmPage confirmpage = new ConfirmPage(driver);
		return confirmpage;
		
	}
	


}
