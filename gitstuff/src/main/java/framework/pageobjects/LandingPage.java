package framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.ReusableComponents;

public class LandingPage extends ReusableComponents {

	WebDriver driver;
	
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;
	
	@FindBy(id = "userPassword")
	WebElement password1;
	
	@FindBy(id = "login")
	WebElement loginbtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormsg;
	
	
	
	By errortext= By.cssSelector("[class*='flyInOut']");
	
	public ProductCatalog login(String email,String password)
	{
		username.sendKeys(email);
		password1.sendKeys(password);
		loginbtn.click();
		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;
		
	}
	
	public void goTo(String url)
	{
		driver.get(url);
	}
	
	public String errormsges()
	{
		waitforelementtoappear(errortext);
		return errormsg.getText();
	}
	

}
