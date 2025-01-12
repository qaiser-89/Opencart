package Tests;
import Pages.HomePage;
import com.Base.BaseClass;
import org.testng.annotations.Test;

public class  LoginTest extends BaseClass
{
    @Test
    public void navigateToLogin() {

        HomePage home=new HomePage(driver);
        home.navigateToLoginPage();
        //demo@opencart.com
    }


}
