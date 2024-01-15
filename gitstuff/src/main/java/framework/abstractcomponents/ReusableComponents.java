package framework.abstractcomponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.pageobjects.CartPageProducts;
import framework.pageobjects.OrdersPage;

public class ReusableComponents {
	
	 
	WebDriver driver;
	
	@FindBy(css = "button[routerlink='/dashboard/cart']")
	WebElement cartelem;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	public ReusableComponents(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	 
		
		public void page()
		{
			driver = new ChromeDriver();
		}

	public void waitforelementtoappear(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitforelementtodisappear(WebElement animation)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.invisibilityOf(animation));
	}
	
	public JavascriptExecutor jsexicutor()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}
	
	public CartPageProducts gotocartpage()
	{
		cartelem.click();
		CartPageProducts cartpageproducts = new CartPageProducts(driver);
		return cartpageproducts;
	}
	
	public OrdersPage orders()
	{
		orders.click();
		OrdersPage orderspage=new OrdersPage(driver);
		return orderspage;
	}

}
