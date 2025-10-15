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
        if(DataReader.readProperty("LocalExecution").equalsIgnoreCase("True"))
        {
            // LOCAL EXECUTION - Added all browsers
            if(driverName.equalsIgnoreCase("Chrome")|| driverName.equalsIgnoreCase("Google Chrome"))
            {
                ChromeOptions options = new ChromeOptions();
                if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                    options.addArguments("--headless=new");
                    Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                // Add common Chrome options
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                driver = new ChromeDriver(options);
            }
            else if (driverName.equalsIgnoreCase("Firefox")|| driverName.equalsIgnoreCase("Mozilla Firefox"))
            {
                FirefoxOptions options = new FirefoxOptions();
                if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                    options.addArguments("--headless");
                    Reporter.log("Firefox is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                driver = new FirefoxDriver(options);
            }
            else if (driverName.equalsIgnoreCase("safari") || driverName.equalsIgnoreCase("Apple Safari"))
            {
                driver = new SafariDriver();
                Reporter.log("Safari driver initialized - Note: Safari doesn't support headless mode", true);
            }
            else if (driverName.equalsIgnoreCase("Edge")|| driverName.equalsIgnoreCase("Microsoft Edge"))
            {
                EdgeOptions options = new EdgeOptions();
                if (DataReader.readProperty("Headless").equalsIgnoreCase("True")) {
                    options.addArguments("--headless=new");
                    Reporter.log("Edge is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                // Add common Edge options
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(options);
            }
            else
            {
                System.out.println("We only support Safari, Edge, Chrome and Firefox currently");
            }
        }
        else
        {
            // REMOTE EXECUTION - Added Safari and enhanced capabilities
            if(driverName.equalsIgnoreCase("Chrome")|| driverName.equalsIgnoreCase("Google Chrome"))
            {
                ChromeOptions options=new ChromeOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","chrome");
                capabilities.setCapability("browserVersion", DataReader.readProperty("chromeVersion", "132.0"));
                capabilities.setCapability("platformName", DataReader.readProperty("platform", "linux"));
                
                // Additional Chrome capabilities
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability("se:recordVideo", true);
                
                if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
                {
                    options.addArguments("--headless=new");
                    Reporter.log("Chrome is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                
                // Add common Chrome options
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
                
                options.merge(capabilities);

                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                } catch (MalformedURLException e)
                {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                    throw new RuntimeException("Failed to connect to Selenium Grid", e);
                }
            }
            else if (driverName.equalsIgnoreCase("Firefox")|| driverName.equalsIgnoreCase("Mozilla Firefox"))
            {
                FirefoxOptions options=new FirefoxOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","firefox");
                capabilities.setCapability("browserVersion", DataReader.readProperty("firefoxVersion", "134.0"));
                capabilities.setCapability("platformName", DataReader.readProperty("platform", "linux"));
                
                // Additional Firefox capabilities
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability("se:recordVideo", true);
                
                if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
                {
                    options.addArguments("--headless");
                    Reporter.log("Firefox is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                
                options.merge(capabilities);
                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                }
                catch (MalformedURLException e)
                {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                    throw new RuntimeException("Failed to connect to Selenium Grid", e);
                }
            }
            else if (driverName.equalsIgnoreCase("safari") || driverName.equalsIgnoreCase("Apple Safari"))
            {
                // Safari remote execution
                FirefoxOptions options = new FirefoxOptions(); // Using FirefoxOptions as base for capabilities
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("browserName", "safari");
                capabilities.setCapability("browserVersion", DataReader.readProperty("safariVersion", "latest"));
                capabilities.setCapability("platformName", DataReader.readProperty("platform", "mac"));
                
                // Additional Safari capabilities
                capabilities.setCapability("acceptInsecureCerts", true);
                
                options.merge(capabilities);
                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                    Reporter.log("Safari remote driver initialized", true);
                } catch (MalformedURLException e) {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                    throw new RuntimeException("Failed to connect to Selenium Grid", e);
                }
            }
            else if (driverName.equalsIgnoreCase("Edge")|| driverName.equalsIgnoreCase("Microsoft Edge"))
            {
                EdgeOptions options=new EdgeOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","MicrosoftEdge");
                capabilities.setCapability("browserVersion", DataReader.readProperty("edgeVersion", "132.0"));
                capabilities.setCapability("platformName", DataReader.readProperty("platform", "linux"));
                
                // Additional Edge capabilities
                capabilities.setCapability("acceptInsecureCerts", true);
                capabilities.setCapability("se:recordVideo", true);
                
                if(DataReader.readProperty("Headless").equalsIgnoreCase("True"))
                {
                    options.addArguments("--headless=new");
                    Reporter.log("Edge is running in Headless Mode!!, you can change the setting in Config file", true);
                }
                
                // Add common Edge options
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                
                options.merge(capabilities);

                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                } catch (MalformedURLException e)
                {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                    throw new RuntimeException("Failed to connect to Selenium Grid", e);
                }
            }
            else
            {
                System.out.println("We only support Safari, Edge, Chrome and Firefox currently");
            }
        }
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    public static void closeDriver()
    {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                Reporter.log("Error while closing driver: " + e.getMessage(), true);
            } finally {
                driver = null;
            }
        }
    }
}




/*package com.factory;

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
                Reporter.log("Chrome is running in Headless Mode!!, you can change t chf e setting in Config file", true);
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
                options.merge(capabilities);
                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                }
                catch (MalformedURLException e)
                {
                    throw new RuntimeException(e);
                }
                //driver = new FirefoxDriver(options);

            }
            else if (driverName.equalsIgnoreCase("safari") || driverName.equalsIgnoreCase("Apple Safari"))
            {
                driver = new SafariDriver();
            }
            else if (driverName.equalsIgnoreCase("Edge")|| driverName.equalsIgnoreCase("Microsoft Edge"))
            {
                EdgeOptions options=new EdgeOptions();
                DesiredCapabilities capabilities=new DesiredCapabilities();
                capabilities.setCapability("browserName","MicrosoftEdge");
                capabilities.setCapability("browserVersion","132.0");
                capabilities.setCapability("platformName","linux");
                options.merge(capabilities);

                try {
                    driver = new RemoteWebDriver(new URL(DataReader.readProperty("HubURL")), options);
                } catch (MalformedURLException e)
                {
                    Reporter.log("Could not Connect to Selenium Grid - Seems hub is not reachable "+e.getMessage());
                }

               // driver =new EdgeDriver(options);
            }
            else
            {
                System.out.println("We only support Safari, Edge, Chrome and Firefox currently");
            }

        }
        driver.manage().window().maximize();
        driver.get((URL));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
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