package Tests;
import Pages.*;
import com.Base.BaseClass;
import com.dataProvider.DataReader;
import com.helper.Utility;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends BaseClass
{

    String String1="Your Account Has Been Created!";
    String String2="Congratulations! Your new account has been successfully created!";
    String String3="You can now take advantage of member privileges to enhance your online shopping experience with us.";
    String String4="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
    String String5="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.";
    String expected_newletterSubMessage = "Success: Your newsletter subscription has been successfully updated!";

    @Test(priority = 1)
    public void newUserPageNavigationByDifferentWays()
    {
        HomePage home=new HomePage(driver);
        LoginPage login=new LoginPage(driver);
        home.navidateToNewUserPage();
        login.accessRegisterFromDifferentWays();
    }

    //@Test(priority = 1)
    public void navigateToNewUserPage()
    {
        HomePage home=new HomePage(driver);
        home.navidateToNewUserPage();
    }

    @Test(priority = 2)
    public void registerNewUser()
    {
        HomePage home=new HomePage(driver);
        home.navidateToNewUserPage();
        RegisterPage register=new RegisterPage(driver);
        register.registerNewUser();
        String expected_message = "Your Account Has Been Created!";
        Assert.assertEquals("Your Account Has Been Created!", expected_message);
        Assert.assertTrue(register.verifySuccessMessShows());
        if(DataReader.readProperty("NewletterNoOption").equalsIgnoreCase("True"))
        {
            Reporter.log("Created the user with NewsLetter option set to Yes, If needed as No Please change in the Config File  ");
    }
        else
        {
            NewletterPage news=new NewletterPage(driver);
            news.navigeToNewsLetterPage();
           String actual_mess= news.chcekNoNewletter();
           Assert.assertEquals(actual_mess, expected_newletterSubMessage);
            Utility.goBackToPriviousPage(driver);
            Utility.goBackToPriviousPage(driver);
        }
    }


    @Test(priority = 4)
    public void verifyAccountCreatedText()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertEquals(succ.verifyTextOnSuccessfullPage(), String1);
    }

    @Test(priority = 5)
    public void verifyCongratulationsText()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertEquals(succ.verifySuccessMessLine2(), String2);
    }

    @Test(priority = 6)
    public void verifyAdvantageText()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertEquals(succ.verifySuccessMessLine3(), String3);
    }

    @Test(priority = 7)
    public void verifyAnyQuestionText()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertEquals(succ.verifySuccessMessLine4(), String4);
    }

    @Test(priority = 8)
    public void verifyConfermationHasBeenSentText()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertEquals(succ.verifySuccessMessLine5(), String5);
    }
    @Test(priority = 9)
    public void checkContinueButtonDisplays()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        Assert.assertTrue(succ.verfiyContinueButtonDisplays());
    }

    @Test(priority = 10)
    public void clickOnContinueButton()
    {
        SuccessfullPage succ=new SuccessfullPage(driver);
        succ.clickContinueButton();
    }

}
