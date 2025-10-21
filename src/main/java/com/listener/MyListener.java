package com.listener;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.dataProvider.DataReader;
import com.factory.BrowserFactory;
import com.helper.Utility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyListener implements ITestListener
{
    /*ExtentReports extentReports=ExtentManager.getInstance();
    ExtentTest extentTest;*/

    public void onTestSuccess(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnSuccess").equalsIgnoreCase("True"))
        {
            String screenshotString= Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            ChainTestListener.embed(screenshotString, "image/png");

        }
        else
        {
            ChainTestListener.log("LOG: Passed - Test Passed"+result.getMethod().getMethodName());
        }}

    public void onTestFailure(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnFailure").equalsIgnoreCase("True"))
        {
            String screenshotString=Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            ChainTestListener.embed(screenshotString, "image/png");
        }
        else
        {
        	ChainTestListener.log("LOG: Failed - Test Failed"+result.getMethod().getMethodName()+" "+result.getThrowable().getMessage());   
        }
		/*WebDriver driver= BrowserFactory.getDriver();
		String screenshotString=Utility.captureScreenshotAsByte(driver);
		extentTest.fail("Test Failed"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotString).build());
	*/
    }

    public void onTestSkipped(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnSkip").equalsIgnoreCase("True"))
        {
            String screenshotString= Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            ChainTestListener.embed(screenshotString, "image/png");
        }
        else
        {
        	ChainTestListener.log("LOG: SKIPPED - Test Skipped"+result.getMethod().getMethodName()+" "+result.getThrowable().getMessage());
        }}

    /*public void onTestStart(ITestResult result)
    {
        extentTest=extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context)
    {
        extentReports.flush();

    }*/

}
