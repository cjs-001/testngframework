package testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
 

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentreporter.ExtentReporterEngine;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	
	ExtentReports extent1 = ExtentReporterEngine.extentreports();
	ThreadLocal<ExtentTest> threadtest= new ThreadLocal<ExtentTest>();//to run test parallel use this
	    @Override
	    public void onTestStart(ITestResult result) {  
		
	    	test=extent1.createTest(result.getMethod().getMethodName());
	    	threadtest.set(test);
		}  
		  
		@Override  
		public void onTestSuccess(ITestResult result) {
			
			threadtest.get().log(Status.PASS, result.getMethod().getMethodName());
		}  
		  
		@Override  
		public void onTestFailure(ITestResult result) {  
			
			
			
			
			
			threadtest.get().fail(result.getThrowable());
			try {
				driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		String filepath = null;
			try {
				 filepath=takess(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			threadtest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		}  
		  
		@Override  
		public void onTestSkipped(ITestResult result) {  
		  
		}  
		  
		@Override  
		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
		  
		}  
		  
		@Override  
		public void onStart(ITestContext context) {  
		 
		}  
		  
		@Override  
		public void onFinish(ITestContext context) {  
		 
			extent1.flush();
		} 


	

	

}
