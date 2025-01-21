package Pages;

import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver= driver;
    }

    protected By email_field = By.xpath("//input[@id='input-email']");
    protected By password_field = By.xpath("//input[@id='input-password']");
    protected By login_button = By.xpath("//input[@value='Login']");

    public void ValidLoginWithCorrectDetails(String user, String pass)
    {
        Utility.waitForElement(driver, email_field).sendKeys(user);
        Utility.waitForElement(driver, password_field).sendKeys(pass);
        Utility.waitForElement(driver, login_button).click();
    }
}
