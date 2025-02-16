package Pages;

import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewletterPage
{
    WebDriver driver;

    public NewletterPage(WebDriver driver)
    {
        this.driver=driver;
    }

    protected By newletterPage_Header = By.xpath("//h1[normalize-space()='Newsletter Subscription']");
    protected By no_checkBox = By.xpath("//label[normalize-space()='No']");
    protected By yes_checkBox = By.xpath("//label[normalize-space()='Yes']");


    public void chcekNoNewletter()
    {
        Utility.waitForElement(driver, newletterPage_Header).isDisplayed();
        Utility.waitForElement(driver, no_checkBox).isSelected();
        Utility.waitForElement(driver, yes_checkBox).click();

    }



}
