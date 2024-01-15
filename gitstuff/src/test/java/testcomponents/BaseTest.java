package testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import datas.ExcelData;
import framework.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public Properties prop;
	
	public WebDriver initializedriver() throws IOException 
	{
		
		prop = new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//globaldata.properties");
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if(browsername.contains("chrome"))
		{
			
			ChromeOptions options = new ChromeOptions();
		
		options.addArguments("headless");
		if(browsername.contains("headless"))
		{
			driver = new ChromeDriver(options);
		}
		else {
		driver = new ChromeDriver();
		}
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/sweth/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe");
		driver.manage().window().setSize(new Dimension(1440,900));//full screen in headless
		
		}
		else if(browsername.equalsIgnoreCase("edge"))
		{
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\sweth\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		}
		else if(browsername.equalsIgnoreCase("firefox"))
		{
		 driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.setProperty("webdriver.gecko.driver",
				"C:/Users/sweth/Downloads/geckodriver-v0.34.0-win32/geckodriver.exe");
		
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchapplicationpage() throws IOException
	{
		driver=initializedriver();
	    landingpage = new LandingPage(driver);
	    
		landingpage.goTo(prop.getProperty("url"));
		return landingpage;
	}
	
	@AfterMethod (alwaysRun = true)
	public void close() throws IOException
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getjsondata(String filepath) throws IOException
	{
		String json=FileUtils.readFileToString(new File(filepath));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=  mapper.readValue(json, new TypeReference<List<HashMap<String,String>>>(){} );
		
		return data;
		
	}
	
	public ExcelData exceldata() throws IOException
	{
		ExcelData data = new ExcelData();
		return data;
	}
	
	public String takess(String testcasename, WebDriver driver) throws IOException
	{
		System.out.println("2ndbranch");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testcasename+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testcasename+".png";
	}
}
