package com.helper;

import com.dataProvider.DataReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.testng.Reporter;

public class Utility
{


    public static String currentDate()
    {
        SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_MM_yyyy");
        String newFormat=myformat.format(new Date());
        return newFormat;
    }

    public static String  captureScreenshotAsByte(WebDriver driver)
    {
        TakesScreenshot ts=(TakesScreenshot)driver;
        String ScreenshotAsBase64=ts.getScreenshotAs(OutputType.BASE64);
        return ScreenshotAsBase64;
    }

    public static void sleep(int timeInSeconds)
    {
        try {
            Thread.sleep(timeInSeconds*1000);
        } catch (InterruptedException e)
        {
            Reporter.log("Log INFO: something went wrong in sleep Thread: "+e.getMessage(), true);
        }
    }


    public static WebElement highLightWebElement(WebDriver driver, WebElement element)  {
        JavascriptExecutor jsexe=(JavascriptExecutor)driver;
        jsexe.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 2px red')",element);
        sleep(1);
        jsexe.executeScript("arguments[0].setAttribute('style','border: solid 2px black')",element);
        return element;
    }

    public static WebElement waitForElement(WebDriver driver, By locator)  {
        WebDriverWait wait=new WebDriverWait( driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        String highLightcondition= DataReader.readProperty("HighLighter");
        if(highLightcondition.equalsIgnoreCase("True"))
        {
            highLightWebElement(driver, element);
        }
        return element;

    }
    
    public static WebElement waitForElement(WebDriver driver, By locator, int timeinseconds)
    {
        WebDriverWait wait=new WebDriverWait( driver, Duration.ofSeconds(timeinseconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        String highLightcondition= DataReader.readProperty("HighLighter");
        if(highLightcondition.equalsIgnoreCase("True"))
        {
            highLightWebElement(driver, element);
        }
        return element;

    }





}
