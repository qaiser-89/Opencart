package com.factory;

import com.dataProvider.DataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.time.Duration;

public class BrowserFactory
{
    public static WebDriver driver;

    public static  WebDriver getDriver(WebDriver driver)
    {
        return driver;
    }

    public static WebDriver getBrowser(String driverName, String URL)
    {
        if(driverName.equalsIgnoreCase("Chrome")|| driverName.equalsIgnoreCase("Google Chrome"))
        {
            driver =new ChromeDriver();
        } else if (driverName.equalsIgnoreCase("Firefox")|| driverName.equalsIgnoreCase("Mozila Firefox")) {
            driver = new FirefoxDriver();
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static void closeDriver()
    {
        driver.close();
        driver.quit();
    }

}
