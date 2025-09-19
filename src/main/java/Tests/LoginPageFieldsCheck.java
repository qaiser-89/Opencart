package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import com.Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageFieldsCheck extends BaseClass
{

    String LoginEmailError="Warning: No match for E-Mail Address and/or Password.";


    @Test(priority = 0)
    public void navigateToLogin() {

        HomePage home=new HomePage(driver);
        home.navigateToLoginPage();
    }

    @Test(priority = 1)
    public void checkEmailFieldError()
    {
        LoginPage login=new LoginPage(driver);
        Assert.assertEquals(login.clickEmailField(), LoginEmailError);
    }

    @Test(priority = 2)
    public void checkPasswordFieldError()
    {
        LoginPage login=new LoginPage(driver);
       Assert.assertEquals(login.clickPasswordField(), LoginEmailError);
    }
@Test(priority = 3)
    public void readListOnRightOfPage()
    {
       List<WebElement> list = driver.findElements(By.xpath("//a[@class='list-group-item']"));

        for(WebElement ele: list)
        {
           Reporter.log(ele.getText(),true);
        }
    }
}
