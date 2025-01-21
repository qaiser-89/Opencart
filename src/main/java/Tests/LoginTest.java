package Tests;
import Pages.HomePage;
import Pages.LoginPage;
import com.Base.BaseClass;
import com.dataProvider.DataReader;
import org.testng.annotations.Test;

public class  LoginTest extends BaseClass
{
    @Test(priority = 0)
    public void navigateToLogin() {

        HomePage home=new HomePage(driver);
        home.navigateToLoginPage();

        //demo@opencart.com
    }

    @Test(priority = 1)
    public void ValidLoginToApp()
    {
        LoginPage login=new LoginPage(driver);

        login.ValidLoginWithCorrectDetails(DataReader.readProperty("Username"),DataReader.readProperty("Password"));

    }

}
