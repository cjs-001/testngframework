package Framework;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.pageobjects.LandingPage;

public class StandAloneTestsoriginal {

	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		 String[] name= {"ZARA COAT 3","ADIDAS ORIGINAL"};
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/sweth/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.get("http://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));
		
//		Stream<WebElement> cartproduct=products.stream().filter(product->product.findElement(By.cssSelector("div.card-body b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
//		cartproduct.forEach(add->add.findElement(By.cssSelector("div.card-body button.btn.w-10")).click());
		
		//above one also works but trying to do it in single line 
		List<String> names=Arrays.asList(name);
		
		for (int i=0;i<names.size();i++)
		{
			String pro=names.get(i);
			//Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		products.stream().filter(product->product.findElement(By.cssSelector("div.card-body b")).getText().equals(pro))
		.forEach(add->add.findElement(By.cssSelector("div.card-body button.btn.w-10")).click());
		}
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartitems = driver.findElements(By.cssSelector("div.cartSection h3"));
		for (int i=0;i<names.size();i++)
		{
			String pro=names.get(i);
			Boolean match = cartitems.stream().anyMatch(a->a.getText().equals(pro));	
			Assert.assertTrue(match);
	     //cartitems.stream().filter(a->a.getText().equals(pro));
		System.out.println(cartitems.get(i).getText());
		}
		Thread.sleep(3000);
		
		WebElement cart=driver.findElement(By.cssSelector("li.totalRow button"));
		js.executeScript("arguments[0].click();",cart);
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).click();
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
		Thread.sleep(3000);
		WebElement country = driver.findElement(By.cssSelector("section.ta-results"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results")));
		System.out.println(country.findElement(By.cssSelector("span.ng-star-inserted")).getText());
		List<WebElement> countrynames =country.findElements(By.cssSelector("span.ng-star-inserted"));
		countrynames.stream().filter(a->a.getText().equalsIgnoreCase("India")).forEach(a->a.click());
		driver.findElement(By.cssSelector("a.action__submit")).click();
		String finale=driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		System.out.println(finale);
	}

}
