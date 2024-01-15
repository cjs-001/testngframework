package Framework;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datas.DBConn;
import datas.ExcelData;
import framework.abstractcomponents.ReusableComponents;
import framework.pageobjects.CartPageProducts;
import framework.pageobjects.CheckOutPage;
import framework.pageobjects.ConfirmPage;
import framework.pageobjects.LandingPage;
import framework.pageobjects.OrdersPage;
import framework.pageobjects.ProductCatalog;
import testcomponents.BaseTest;

public class StandAloneTests extends BaseTest {

	//public String[] name= {"ZARA COAT 3","ADIDAS ORIGINAL"};
	@Test(dataProvider = "getdata",groups = {"purchase"})
	public void submitorder(String name,String email,String password,String productname) throws IOException, InterruptedException {
		
		 
		
		
		
		
		
		ProductCatalog productcatalog = landingpage.login(email, password);
		productcatalog.addproducttocart(productname);
		Thread.sleep(3000);
		
		
		CartPageProducts cartpageproducts = productcatalog.gotocartpage();
		Thread.sleep(3000);
		cartpageproducts.verifyproducts(productname);
		
		Thread.sleep(3000);
		
		CheckOutPage checkoutpage = cartpageproducts.checkout();
		Thread.sleep(3000);
		checkoutpage.selectcountry("India");
		
		
		ConfirmPage confirmpage =checkoutpage.submit();
		String text=confirmpage.confirmationtext();
		Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		
	    System.out.println("finale");
	    
	    System.out.println("asd");
	    
	}
	
	@Test(dependsOnMethods = {"submitorder"},dataProvider = "getdata")
	
	public void verifyorder(HashMap<String, String> input)
	{
		landingpage.login("anshika@gmail.com", "Iamking@000");
		OrdersPage orderpage=landingpage.orders();
		Assert.assertTrue(orderpage.orderedproducts(input.get("name")));
		
	}
	
//	@DataProvider
//	public Object[][] getdata()
//	{
//		return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
//	@DataProvider
//	public Object[][] getdata()
//	{
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "anshika@gmail.com");
//		map.put("password", "Iamking@000");
//		map.put("name", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("name", "ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};
//		
//		
//	}
	
	//Json data fetching
	
//	@DataProvider
//	public Object getdata() throws IOException
//	{
//		List<HashMap<String, String>> data=getjsondata(System.getProperty("user.dir")+"//src//test//java//datas//purchaseorder.json");
//		return new Object[][] {{data.get(0)},{data.get(1)}}; 
//		
//		
//
//	}
	
	//excel data fetching
	
//	@DataProvider
//	public Object getdata() throws IOException
//	{
//		
//		Object[][] data=exceldata().grtdata("Sheet1");
//		
//		return data;
//
//	}
	
	
	//DB data fetcher for 1 set data
	
	@DataProvider
	public Object getdata() throws IOException, SQLException
	{
		DBConn conn = new DBConn();
		
		HashMap<String, String> st = conn.data2("anshika");
		return new Object[][] {{st.get("name"),st.get("email"),st.get("password"),st.get("product")}};
			 
	}
	
	
	//grab all the data in sql table
	
//	@DataProvider
//	public Object[][] getdata() throws IOException, SQLException
//	{
//		
//		Object[][] data=DBConn.data3();
//		
//		return data;
//	}
	
	
	

}
