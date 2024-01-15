package framework.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.abstractcomponents.ReusableComponents;

public class OrdersPage extends ReusableComponents {

	
WebDriver driver;
	
	
	public OrdersPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderedproducts;
	
	
	
	
	
	
	public boolean match;
	public boolean orderedproducts(String products)
	{
		 
		List<String> names=Arrays.asList(products);
		for (int i=0;i<names.size();i++)
		{
			String pro=names.get(i);
			//Thread.sleep(5000);
			
			match = orderedproducts.stream().anyMatch(product->product.getText().equals(pro));
		    
		}
		return match;
	}
	
}
