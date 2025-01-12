package Pages;

import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage

    {
        WebDriver driver;

    public HomePage(WebDriver driver)
        {
            this.driver = driver;
        }

        protected By my_account = By.xpath("//span[normalize-space()='My Account']");
        protected By register_acc = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']");
        protected By login = By.xpath("//a[normalize-space()='Login']");

        protected By login_button = By.xpath("//button[@type='submit']");

        public void navigateToLoginPage()
        {
            Utility.waitForElement(driver, my_account).click();
            Utility.waitForElement(driver, login).click();
        }

        public RegisterPage navidateToNewUserPage()
        {
            Utility.waitForElement(driver, my_account).click();
            Utility.waitForElement(driver, register_acc).click();
            RegisterPage register=new RegisterPage(driver);
            return register;
        }
    }

