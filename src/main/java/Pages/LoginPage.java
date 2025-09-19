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
    protected By email_field_error = By.xpath("//div[contains(text(),'Warning: No match')]");
    protected By password_field = By.xpath("//input[@id='input-password']");
    protected By password_field_error = By.xpath("//div[contains(text(),'Warning: No match')]");
    protected By login_button = By.xpath("//input[@value='Login']");
    //protected By register_user_from_left = By.xpath("//a[normalize-space()='Continue']");
    protected By register_from_right_list = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");
    protected By login_button_on_regitser = By.className("list-group-item");

    public void accessRegisterFromDifferentWays()
    {
        Utility.waitForElement(driver, login_button_on_regitser).click();

        Utility.waitForElement(driver, register_from_right_list).click();
    }

    public void ValidLoginWithCorrectDetails(String user, String pass)
    {
        Utility.waitForElement(driver, email_field).sendKeys(user);
        Utility.waitForElement(driver, password_field).sendKeys(pass);
        Utility.waitForElement(driver, login_button).click();
    }

    public String clickEmailField()
    {
        Utility.waitForElement(driver, login_button).click();
        String emailerror= Utility.waitForElement(driver, email_field_error).getText();
        return emailerror;
    }

     public String clickPasswordField()
{
    Utility.waitForElement(driver, login_button).click();
    String passworderror=Utility.waitForElement(driver, password_field_error).getText();
    return passworderror;
}
}
