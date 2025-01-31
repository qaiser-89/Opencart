package com.factory;

import com.dataProvider.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;

import javax.xml.crypto.Data;
import java.net.MalformedURLException;
import java.net.URL;
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
        if(DataReader.readProperty("LocalEcecution").equalsIgnoreCase("True"))
        {
            ChromeOptions options = new ChromeOptions();

            if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                options.addArguments("--headless=new");
                Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
            }
                driver = new ChromeDriver(options);
        }
        else
        {
            if(driverName.equalsIgnoreCase("Chrome")|| driverName.equalsIgnoreCase("Google Chrome"))
            {
                ChromeOptions options=new ChromeOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","chrome");
                capabilities.setCapability("browserVersion","132.0");
                capabilities.setCapability("platformName","linux");
            if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
                {
                    options.addArguments("--headless=new");
                    Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                options.merge(capabilities);

                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                } catch (MalformedURLException e)
                {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                }
            }











            else if (driverName.equalsIgnoreCase("Firefox")|| driverName.equalsIgnoreCase("Mozila Firefox"))
            {
                FirefoxOptions options=new FirefoxOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","Firefox");
                capabilities.setCapability("browserVersion","134.0");
                capabilities.setCapability("platformName","linux");
                //options.merge(capabilities);
                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new FirefoxDriver(options);

            }
            else if (driverName.equalsIgnoreCase("safari") || driverName.equalsIgnoreCase("Apple Safari"))
            {
                driver = new SafariDriver();
            }
            else if (driverName.equalsIgnoreCase("Edge")|| driverName.equalsIgnoreCase("Microsoft Edge"))
            {
                EdgeOptions options=new EdgeOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","Firefox");
                capabilities.setCapability("browserVersion","134.0");
                capabilities.setCapability("platformName","linux");
                //options.merge(capabilities);


                driver =new EdgeDriver(options);
            }
            else
            {
                System.out.println("We only support Safari, Edge, Chrome and Firefox currently");
            }

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

/*
//************************************************************************************************************************************************************************************************
public static WebDriver getBrowser(String driverName, String URL) {
    // Check if we're running locally or remotely
    if (DataReader.readProperty("LocalExecution").equalsIgnoreCase("True")) {
        // For local execution, switch based on the passed driver name
        if (driverName.equalsIgnoreCase("Chrome") || driverName.equalsIgnoreCase("Google Chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                options.addArguments("--headless=new");
                Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
            }
            driver = new ChromeDriver(options);
        }
        else if (driverName.equalsIgnoreCase("Firefox") || driverName.equalsIgnoreCase("Mozilla Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
        else if (driverName.equalsIgnoreCase("Edge") || driverName.equalsIgnoreCase("Microsoft Edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        else if (driverName.equalsIgnoreCase("Safari") || driverName.equalsIgnoreCase("Apple Safari")) {
            driver = new SafariDriver();
        }
        else {
            System.out.println("We only support Safari, Edge, Chrome and Firefox currently for Local Execution");
            return null;
        }
    }
    else {
        // For remote execution (Selenium Grid)
        if (driverName.equalsIgnoreCase("Chrome") || driverName.equalsIgnoreCase("Google Chrome")) {
            ChromeOptions options = new ChromeOptions();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("browserName", "chrome");
            capabilities.setCapability("browserVersion", "132.0");
            capabilities.setCapability("platformName", "linux");

            if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                options.addArguments("--headless=new");
                Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
            }
            options.merge(capabilities);

            try {
                driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
            } catch (MalformedURLException e) {
                Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable " + e.getMessage());
            }
        }
        // Repeat for Firefox, Edge, etc., as you have done in your original code for remote execution
        else if (driverName.equalsIgnoreCase("Firefox") || driverName.equalsIgnoreCase("Mozilla Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            try {
                driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        // Other browsers...
    }

    // Common configuration for all cases
    driver.manage().window().maximize();
    driver.get(URL);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    return driver;
}

********************************************************************************************************************************************************************************************************************************
 */