package Tests;

import Pages.HomePage;
import Pages.RegisterPage;
import com.Base.BaseClass;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserPageFieldCheck  extends BaseClass
{

    String warning_Text="Warning: You must agree to the Privacy Policy!";
    String firstname_errorText = "First Name must be between 1 and 32 characters!";
    String lastname_errorText = "Last Name must be between 1 and 32 characters!";
    String email_errorText= "E-Mail Address does not appear to be valid!";
    String telephone_errorText= "Telephone must be between 3 and 32 characters!";
    String password_errorText = "Password must be between 4 and 20 characters!";

    @Test(priority = 1)
    public void navigateToNewUserPage()
    {
        HomePage home=new HomePage(driver);
        home.navidateToNewUserPage();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
    }

    @Test(priority = 2)
    public void verifyWarningError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyWarningMessagesO();
        Assert.assertEquals(acturl, warning_Text);
    }

    @Test(priority = 3)
    public void verifyFNameError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyFirstNameError();
        Assert.assertEquals(acturl, firstname_errorText);
    }
    @Test(priority = 4)
    public void verifyLNameError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyLastNameError();
        Assert.assertEquals(acturl, lastname_errorText);
    }

    @Test(priority = 5)
    public void verifyEmailError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyEmailError();
        Assert.assertEquals(acturl, email_errorText);
    }

    @Test(priority = 6)
    public void verifyTelephoneError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyTelephoneError();
        Assert.assertEquals(acturl, telephone_errorText);
    }

    @Test(priority = 7)
    public void verifyPasswordError()
    {
        RegisterPage registerP=new RegisterPage(driver);
        String acturl= registerP.verifyPassError();
        Assert.assertEquals(acturl, password_errorText);
    }
}
