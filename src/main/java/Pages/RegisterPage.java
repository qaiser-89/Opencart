package Pages;

import com.dataProvider.DataReader;
import com.helper.Utility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class RegisterPage
{
    WebDriver driver;

    public RegisterPage(WebDriver driver)
    {
        this.driver=driver;
    }
    protected By first_name = By.xpath("//input[@id='input-firstname']");
    protected By last_name = By.xpath("//input[@id='input-lastname']");
    protected By email = By.xpath("//input[@id='input-email']");
    protected By telephone = By.id("input-telephone");
    protected By password = By.xpath("//input[@id='input-password']");
    protected By confirm_password = By.xpath("//input[@id='input-confirm']");
    protected By accept_policy = By.xpath("//input[@name='agree']");
    protected By newletter_yes_radio = By.xpath("//label[normalize-space()='Yes']");
    protected By newletter_no_radio = By.xpath("//label[normalize-space()='No']");
    protected By continue_button = By.xpath("//input[@value='Continue']");
    protected By successful_registration_message = By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]");
    protected By warning_message_privacy_policy = By.xpath("//div[@class='alert alert-danger alert-dismissible']");
    protected By first_name_error_message = By.xpath("//div[contains(text(),'First Name must be')]");
    protected By last_name_error_message = By.xpath("//div[contains(text(),'Last Name must be')]");
    protected By email_error_message = By.xpath("//div[contains(text(),'E-Mail Address does not ')]");
    protected By telephone_error_message = By.xpath("//div[contains(text(),'Telephone must be')]");
    protected By password_error_message = By.xpath("//div[contains(text(),'Password must be between')]");
    protected By form_screenshot = By.xpath("form-horizontal");
    protected By email_error = By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]");
    protected By wrong_telephone_mess = By.xpath("//input[@id='input-telephone']/following-sibling::div");

    protected By fnLabel = By.cssSelector("label[for='input-firstname']");
    protected By lnLale = By.cssSelector("label[for='input-lastname']");
    protected By email_Label = By.cssSelector("label[for='input-email']");
    protected By telephone_Label = By.cssSelector("label[for='input-telephone']");
    protected By password_Label = By.cssSelector("label[for='input-password']");
    protected By confirm_password_Lable = By.cssSelector("label[for='input-confirm']");

public void registerNewUser()
{
    Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated = "test" + Utility.currentDate() + "@eamil.com";
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Reporter.log("Log INFO: Email Generated is-------" + emailGenerated, true);
    //Utility.waitForElement(driver, email).sendKeys("test"+Utility.currentDate()+"@gmail.com");
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@123");
    Utility.waitForElement(driver, accept_policy).click();

    if(DataReader.readProperty("NewletterNoOption").equalsIgnoreCase("True")) {
        Utility.waitForElement(driver, newletter_yes_radio).click();
        Utility.waitForElement(driver, continue_button).click();
    }
    else
    {
        Utility.waitForElement(driver, continue_button).click();
    }
}


public boolean verifySuccessMessShows()
{
   boolean status= Utility.waitForElement(driver, successful_registration_message).isDisplayed();
    return status;
}

public String verifyWarningMessagesO()
{
   String warning = Utility.waitForElement(driver, warning_message_privacy_policy).getText();
    return warning;
}

public String verifyFirstNameError()
{
    String fname= Utility.waitForElement(driver, first_name_error_message).getText();
    return fname;
}

public String verifyLastNameError()
{
    String lanme= Utility.waitForElement(driver, last_name_error_message).getText();
    return lanme;
}

public String verifyEmailError()
{
    String emailerror=Utility.waitForElement(driver, email_error_message).getText();
    return emailerror;
}
public String verifyTelephoneError()
{
    String telephoneerror =Utility.waitForElement(driver, telephone_error_message).getText();
    return telephoneerror;
}

public String verifyPassError()
{
    String passerror=Utility.waitForElement(driver, password_error_message).getText();
    return passerror;
}

public void registerNewUserWithOutPasswordMatch()
{
	Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated = "test" + Utility.currentDate() + "@eamil.com";
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Reporter.log("Log INFO: Email Generated is-------" + emailGenerated, true);
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@1234");
    Utility.waitForElement(driver, accept_policy).click();
}


public void registerNewUserWithExistingEmail()
{
	driver.navigate().refresh();
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
	Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    Utility.waitForElement(driver, email).sendKeys(DataReader.readProperty("Username"));
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@1234");
    Utility.waitForElement(driver, accept_policy).click();
}
public void verifyEmailFieldErrors()//In this testcase we will take screenshot of our form and compare it with actual pixel by pixel!
{
	driver.navigate().refresh();
	Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated = "test" + Utility.currentDate();
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@123");
    Utility.waitForElement(driver, accept_policy).click();
    Utility.waitForElement(driver, continue_button).click();
    File actualScreenShotFile= Utility.waitForElement(driver, form_screenshot).getScreenshotAs(OutputType.FILE);
    try {
		FileHandler.copy(actualScreenShotFile, new File(System.getProperty("./screenshot/actualscreenshot.png")));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void verifyEmailDoesNotAppearVaild()
{
	driver.navigate().refresh();
	Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated = "test" + Utility.currentDate();
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Utility.waitForElement(driver, telephone).sendKeys("079797978098");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@123");
    Utility.waitForElement(driver, accept_policy).click();
    Utility.waitForElement(driver, continue_button).click();
}

public String registerNewUserWithWrongPhoneNumber()
{
	driver.navigate().refresh();
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
	Utility.waitForElement(driver, first_name).sendKeys("Test");
    Utility.waitForElement(driver, last_name).sendKeys("Automation");
    String emailGenerated = "test" + Utility.currentDate() + "@eamil.com";
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Utility.waitForElement(driver, email).sendKeys(emailGenerated);
    Utility.waitForElement(driver, telephone).sendKeys("ASDTRER");
    Utility.waitForElement(driver, password).sendKeys("Test@123");
    Utility.waitForElement(driver, confirm_password).sendKeys("Test@1234");
    Utility.waitForElement(driver, accept_policy).click();
    return Utility.waitForElement(driver, wrong_telephone_mess).getText();
}

    public void verfifyAstrikSymbolByEmailFields()
    {
        Utility.waitForElement(driver,fnLabel);
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        //String fnContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", fnLabel);
        //String fnColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", fnLabel);
        //System.out.println(fnContent);
        //System.out.println(fnColor);
    }
    
    
    public Boolean verifyHeightAndWeidthOfFNameField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, fnLabel).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, fnLabel).getCssValue("width");
    	return null;
    }
    
    public Boolean verifyHeightAndWeidthOfLNameField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, lnLale).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, lnLale).getCssValue("width");
    	return null;
    } 
    
    public Boolean verifyHeightAndWeidthOfEmailField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, email_Label).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, email_Label).getCssValue("width");
    	return null;
    } 
    
    public Boolean verifyHeightAndWeidthOfTelephoneField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, telephone_Label).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, telephone_Label).getCssValue("width");
    	return null;
    } 
    
    public Boolean verifyHeightAndWeidthOfPasswordField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, password_Label).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, password_Label).getCssValue("width");
    	return null;
    } 
    
    public Boolean verifyHeightAndWeidthOfConfirmPasswordField()
    {
    	String expectedFNHeight = "34px";
    	String expectedFNWeridth = "701.25px";
    	
    	String fnameHeight	= Utility.waitForElement(driver, confirm_password_Lable).getCssValue("height");
    	String fnameWidth = Utility.waitForElement(driver, confirm_password_Lable).getCssValue("width");
    	return null;
    } 
    
    public void verifyRegisterAccountWithLeadingAndTrailingSpaces()
    {
    	Utility.waitForElement(driver, first_name).sendKeys("    Test     ");
        Utility.waitForElement(driver, last_name).sendKeys("    Automation    ");
        String emailGenerated = "test" + Utility.currentDate() + "@eamil.com";
        Utility.waitForElement(driver, email).sendKeys("    "+emailGenerated+"    ");
        Reporter.log("Log INFO: Email Generated is-------" + emailGenerated, true);
        //Utility.waitForElement(driver, email).sendKeys("test"+Utility.currentDate()+"@gmail.com");
        Utility.waitForElement(driver, telephone).sendKeys("    079797978098    ");
        Utility.waitForElement(driver, password).sendKeys("    Test@123    ");
        Utility.waitForElement(driver, confirm_password).sendKeys("    Test@123    ");
        Utility.waitForElement(driver, accept_policy).click();

        if(DataReader.readProperty("NewletterNoOption").equalsIgnoreCase("True")) {
            Utility.waitForElement(driver, newletter_yes_radio).click();
            Utility.waitForElement(driver, continue_button).click();
        }
        else
        {
            Utility.waitForElement(driver, continue_button).click();
        }
    	
    	
    	
    }
    

}
