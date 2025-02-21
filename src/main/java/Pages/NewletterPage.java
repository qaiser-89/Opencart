package Pages;

import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.Reporter;

public class NewletterPage
{
    WebDriver driver;

    public NewletterPage(WebDriver driver)
    {
        this.driver=driver;
    }
    protected By newletter_field = By.xpath("//a[text()='Newsletter'][1]");
    protected By newletterPage_Header = By.xpath("//h1[normalize-space()='Newsletter Subscription']");
    protected By no_checkBox = By.xpath("//input[@value='0']");
    protected By yes_checkBox = By.xpath("//label[normalize-space()='Yes']");
    protected By continue_bbutton = By.xpath("//input[@value='Continue']");
    protected By expected_NewletterMessage = By.xpath("//div[@class='alert alert-success alert-dismissible']");



    public void navigeToNewsLetterPage()
    {
        Utility.waitForElement(driver, newletter_field).click();
    }

    public String chcekNoNewletter()
    {
        Utility.waitForElement(driver, newletterPage_Header).isDisplayed();
        Utility.waitForElement(driver, no_checkBox).isSelected();
        Utility.waitForElement(driver, continue_bbutton).click();
        String mess=Utility.waitForElement(driver, expected_NewletterMessage).getText();
        Reporter.log(mess, true);
        return mess;
    }



}
