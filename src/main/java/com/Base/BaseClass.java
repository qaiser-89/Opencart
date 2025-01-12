package com.Base;
import com.dataProvider.DataReader;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class BaseClass
{

   public WebDriver driver;

    @BeforeClass
    public void setDriver()
    {
        String BrowserName = DataReader.readProperty("Browser");
        String appURL = DataReader.readProperty("BaseURL");
        Reporter.log("Log: INFO Settingup driver");

      driver= BrowserFactory.getBrowser(BrowserName, appURL);

    }

    @AfterClass
    public void driverTearDown()
    {
        Reporter.log("Log: INFO Closing driver");

        BrowserFactory.closeDriver();

    }

}
