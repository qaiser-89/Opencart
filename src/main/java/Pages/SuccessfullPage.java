package Pages;

import com.Base.BaseClass;
import com.helper.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessfullPage  extends BaseClass
{
    WebDriver driver;
    public SuccessfullPage(WebDriver driver)
    {
        this.driver=driver;
    }

    protected By account_created_message=By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
    protected By proper_details_one = By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]");
    protected By proper_details_two = By.xpath("//p[contains(text(),'You can now take advantage of member privileges to')]");
    protected By proper_details_three = By.xpath("//p[contains(text(),'If you have ANY questions about the operation of t')]");
    protected By proper_details_four = By.xpath("//p[contains(text(),'A confirmation has been sent to the provided e-mai')]");
    protected By continue_button = By.xpath("//a[text()='Continue']");

    public String verifyTextOnSuccessfullPage() {
        String message_line1 = Utility.waitForElement(driver, account_created_message).getText();

        return message_line1;
    }

    public String verifySuccessMessLine2()
    {
       String message_line2= Utility.waitForElement(driver, proper_details_one).getText();
       return message_line2;
    }

    public String verifySuccessMessLine3()
    {
        String message_line3= Utility.waitForElement(driver, proper_details_two).getText();
        return message_line3;
    }

    public String verifySuccessMessLine4()
    {
        String message_line4= Utility.waitForElement(driver, proper_details_three).getText();
        return message_line4;
    }

    public String verifySuccessMessLine5()
    {
        String message_line5= Utility.waitForElement(driver, proper_details_four).getText();

        return message_line5;
    }

    public boolean verfiyContinueButtonDisplays()
    {
       boolean continue_button_status = Utility.waitForElement(driver, continue_button).isDisplayed();
       return continue_button_status;
    }

    public void clickContinueButton()
    {
        Utility.waitForElement(driver, continue_button).click();
    }

}
