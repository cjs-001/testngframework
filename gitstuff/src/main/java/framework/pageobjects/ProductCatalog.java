package framework.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.abstractcomponents.ReusableComponents;

public class ProductCatalog extends ReusableComponents {

	WebDriver driver;
	
	
	public ProductCatalog(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "div.col-lg-4")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement animation2;
	
	By find=By.cssSelector("div.col-lg-4");
	By animation1=By.cssSelector("div.col-lg-4");
	
	
	
	public List<WebElement> products()
	{
		waitforelementtoappear(find);
		return products;
	}
	
	public void addproducttocart(String products) throws InterruptedException
	{
		List<String> names=Arrays.asList(products);
		for (int i=0;i<names.size();i++)
		{
			String pro=names.get(i);
			Thread.sleep(5000);
//			waitforelementtoappear(animation1);
//			waitforelementtodisappear(animation2);
			products().stream().filter(product->product.findElement(By.cssSelector("div.card-body b")).getText().equals(pro))
		    .forEach(add->add.findElement(By.cssSelector("div.card-body button.btn.w-10")).click());
		}
	}
	
	
	
	

}
