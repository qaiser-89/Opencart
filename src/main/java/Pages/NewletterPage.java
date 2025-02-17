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
    protected By newletter_field = By.xpath("//a[text()='Newsletter'][1]");
    protected By newletterPage_Header = By.xpath("//h1[normalize-space()='Newsletter Subscription']");
    protected By no_checkBox = By.xpath("//label[normalize-space()='No']");
    protected By yes_checkBox = By.xpath("//label[normalize-space()='Yes']");


    public void navigeToNewsLetterPage()
    {
        Utility.waitForElement(driver, newletter_field).click();
    }

    public boolean chcekNoNewletter()
    {
        Utility.waitForElement(driver, newletterPage_Header).isDisplayed();
      boolean radio_status = Utility.waitForElement(driver, no_checkBox).isSelected();
      return radio_status;

    }



}
