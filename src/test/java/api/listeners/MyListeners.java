package api.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import api.utilities.ExtentManager;

public class MyListeners implements ITestListener{
	public ExtentReports reports  ;
	
	public ExtentTest extentTest;
	public String testname;
	
	@Override
	public void onStart(ITestContext context) {
		
		
		reports=  ExtentManager.getExtentReport();
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		testname = result.getName();
		extentTest = reports.createTest(testname);
		extentTest.log(Status.INFO,testname+" ----------------- The test was started executing");
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		testname = result.getName();
		extentTest = reports.createTest(testname);
		extentTest.log(Status.PASS,testname+" ----------------- The test was executed successfully!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		testname = result.getName();
		extentTest = reports.createTest(testname);
		extentTest.log(Status.FAIL,testname+" ----------------- The test was failed");
		extentTest.log(Status.INFO, result.getThrowable());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		testname = result.getName();
		extentTest = reports.createTest(testname);
		extentTest.log(Status.FAIL,testname+" ----------------- The test was Skipped");
		
		
	}
	

	@Override
	public void onFinish(ITestContext context) {
		
		reports.flush();
	}
	

}
