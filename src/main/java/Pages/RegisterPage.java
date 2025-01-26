package Pages;

import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class RegisterPage
{
    WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver=driver;
    }
    protected By first_name = By.xpath("//input[@id='input-firstname']");
    protected By last_name = By.xpath("//input[@id='input-lastname']");
    protected By email = By.xpath("//input[@id='input-email']");
    protected By telephone = By.id("input-telephone");
    protected By password = By.xpath("//input[@id='input-password']");
    protected By confirm_password = By.xpath("//input[@id='input-confirm']");
    protected By accept_policy = By.xpath("//input[@name='agree']");
    protected By newletter_yes_radio = By.xpath("//label[normalize-space()='Yes']");

    protected By continue_button = By.xpath("//input[@value='Continue']");
    protected By successful_registration_message = By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]");

    protected By warning_message_privacy_policy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    protected By first_name_error_message = By.xpath("//div[contains(text(),'First Name must be')]");
    protected By last_name_error_message = By.xpath("//div[contains(text(),'Last Name must be')]");
    protected By email_error_message = By.xpath("//div[contains(text(),'E-Mail Address does not ')]");
    protected By telephone_error_message = By.xpath("//div[contains(text(),'Telephone must be')]");
    protected By password_error_message = By.xpath("//div[contains(text(),'Password must be between')]");

public void registerNewUser()
{
    Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated="test"+Utility.currentDate()+"@eamil.com";
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Reporter.log("Log INFO: Email Generated is-------"+emailGenerated, true);
    //Utility.waitForElement(driver, email).sendKeys("test"+Utility.currentDate()+"@gmail.com");
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@123");
    Utility.waitForElement(driver, accept_policy).click();
    Utility.waitForElement(driver, newletter_yes_radio).click();
    Utility.waitForElement(driver, continue_button).click();
}

public boolean verifySuccessMessShows()
{
   boolean status= Utility.waitForElement(driver, successful_registration_message).isDisplayed();
    return status;
}

public String verifyWarningMessagesO()
{
   String warning = Utility.waitForElement(driver, warning_message_privacy_policy).getText();
    return warning;
}

public String verifyFirstNameError()
{
    String fname= Utility.waitForElement(driver, first_name_error_message).getText();
    return fname;
}

public String verifyLastNameError()
{
    String lanme= Utility.waitForElement(driver, last_name_error_message).getText();
    return lanme;
}

public String verifyEmailError()
{
    String emailerror=Utility.waitForElement(driver, email_error_message).getText();
    return emailerror;
}
public String verifyTelephoneError()
{
    String telephoneerror =Utility.waitForElement(driver, telephone_error_message).getText();
    return telephoneerror;
}

public String verifyPassError()
{
    String passerror=Utility.waitForElement(driver, password_error_message).getText();
    return passerror;
}

}
