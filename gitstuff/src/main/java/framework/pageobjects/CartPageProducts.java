package framework.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import framework.abstractcomponents.ReusableComponents;

public class CartPageProducts extends ReusableComponents{

	


	WebDriver driver;
	
	
	public CartPageProducts(WebDriver driver) 
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
	
	@FindBy(css = "li.totalRow button")
	WebElement checkout;
	
	public void verifyproducts(String cartproducts)
	{
		List<String> names=Arrays.asList(cartproducts);
		List<WebElement> cartitems = driver.findElements(By.cssSelector("div.cartSection h3"));
		for (int i=0;i<names.size();i++)
		{
			String pro=names.get(i);
			Boolean match = cartitems.stream().anyMatch(a->a.getText().equals(pro));	
			Assert.assertTrue(match);
	     //cartitems.stream().filter(a->a.getText().equals(pro));
		System.out.println(cartitems.get(i).getText());
		}
	}
	
	public CheckOutPage checkout()
	{
		WebElement cart=checkout;
		jsexicutor().executeScript("arguments[0].click();",cart);
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		return checkoutpage;
	}
	


}
