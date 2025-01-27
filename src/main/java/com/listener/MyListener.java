package com.listener;
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
    ExtentReports extentReports=ExtentManager.getInstance();
    ExtentTest extentTest;

    public void onTestSuccess(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnSuccess").equalsIgnoreCase("True"))
        {
            String screenshotString= Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            extentTest.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotString).build());

        }
        else
        {
            extentTest.pass("Test Passed");
        }}

    public void onTestFailure(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnSuccess").equalsIgnoreCase("True"))
        {
            String screenshotString=Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            extentTest.fail("Test failed"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotString).build());

        }
        else
        {
            extentTest.fail("Test Failed"+result.getThrowable().getMessage());
        }
		/*WebDriver driver= BrowserFactory.getDriver();
		String screenshotString=Utility.captureScreenshotAsByte(driver);
		extentTest.fail("Test Failed"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotString).build());
	*/
    }

    public void onTestSkipped(ITestResult result)
    {
        if(DataReader.readProperty("ScreenshotOnSuccess").equalsIgnoreCase("True"))
        {
            String screenshotString= Utility.captureScreenshotAsByte(BrowserFactory.getDriver());
            extentTest.skip("Test Skipped"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotString).build());

        }
        else
        {
            extentTest.skip("Test Skipped"+result.getThrowable().getMessage());
        }}

    public void onTestStart(ITestResult result)
    {
        extentTest=extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context)
    {
        extentReports.flush();

    }

}
