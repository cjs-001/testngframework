package Framework;


import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import framework.pageobjects.ProductCatalog;
import testcomponents.BaseTest;
import testcomponents.ReRun;

public class ErrorValidation extends BaseTest {

	@Test
	public void submitorder()
	{
		landingpage.login("anshika@gmail.com", "Iamking@00");
		Assert.assertEquals("Incorrect email or password.", landingpage.errormsges());
		
	}
	
	@Test (retryAnalyzer= ReRun.class)
	public void submitorder2()
	{
		landingpage.login("anshika@gmail.com", "Iamking@00");
		Assert.assertEquals("Incorrect email or password", landingpage.errormsges());
		
	}
	
}
