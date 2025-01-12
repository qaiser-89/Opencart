package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage
{
    WebDriver driver;
    public LoginPage(WebDriver driver)
    {
        this.driver= driver;
    }

    protected By my_account = By.xpath("//span[normalize-space()='My Account']");
    protected By login = By.xpath("//a[@class='dropdown-item'][normalize-space()='Login']");
    protected By login_button = By.xpath("//button[@type='submit']");

    public void loginToApplication(String user, String pass)
    {
        driver.findElement(my_account).click();
        driver.findElement(login).click();
    }
}
