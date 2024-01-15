package framework.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.ReusableComponents;

public class ConfirmPage extends ReusableComponents {

WebDriver driver;
	
	
	public ConfirmPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "h1.hero-primary")
	WebElement text;
	
	
	
	
	public String confirmationtext()
	{
		String confirmationtext=text.getText();
		return confirmationtext.toUpperCase();
	}

}
