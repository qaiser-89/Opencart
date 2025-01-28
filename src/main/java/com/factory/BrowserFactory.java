package com.factory;

import com.dataProvider.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import javax.xml.crypto.Data;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.time.Duration;

public class BrowserFactory
{
    public static WebDriver driver;

    public static  WebDriver getDriver()
    {
        return driver;
    }

    public static WebDriver getBrowser(String driverName, String URL)
    {
        if(driverName.equalsIgnoreCase("Chrome")|| driverName.equalsIgnoreCase("Google Chrome"))
        {
            ChromeOptions options=new ChromeOptions();

            DesiredCapabilities capabilities=new DesiredCapabilities();

            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "132.0");
            capabilities.setCapability("platformName","linux");

            if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
            {
                options.addArguments("--headless=new");
                Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
            }
            options.merge(capabilities);
            // driver =new ChromeDriver(options);
            try
            {
                driver=new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")),options);
            }
            catch (MalformedURLException e)
            { 
                Reporter.log("could not connect to selenium Grid -seems hub is not reachable"+e.getMessage());
            }
        } else if (driverName.equalsIgnoreCase("Firefox")|| driverName.equalsIgnoreCase("Mozila Firefox")) {
            FirefoxOptions options1=new FirefoxOptions();
            DesiredCapabilities capabilities1=new DesiredCapabilities();
            capabilities1.setCapability("browserName", "firefox");
            capabilities1.setCapability("browserVersion", "134.0");
            capabilities1.setCapability("platformName","linux");
            if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
            {
                options1.addArguments("--headless=new");
                Reporter.log("Firefox is running in Headless Mode!!, you can change the setting in Config file", true);
            }
            options1.merge(capabilities1);
            try
            {
                driver=new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")),options1);
            }
            catch (MalformedURLException e)
            {
                Reporter.log("could not connect to selenium Grid -seems hub is not reachable"+e.getMessage());
            }

            //driver = new FirefoxDriver();
        } else if (driverName.equalsIgnoreCase("safari") || driverName.equalsIgnoreCase("Apple Safari"))
        {
            driver = new SafariDriver();
        } else if (driverName.equalsIgnoreCase("Edge")|| driverName.equalsIgnoreCase("Microsoft Edge"))
        {
            driver =new EdgeDriver();
        }
        else
        {
            System.out.println("We only support Safari, Edge, Chrome and Firefox currently");
        }
        driver.manage().window().maximize();
        driver.get((URL));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    public static void closeDriver()
    {
        driver.close();
        driver.quit();
    }

}
