package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditAccountPage {
	
	WebDriver driver;
	
	public EditAccountPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	protected By continue_button = By.xpath("//input[@value='Continue']");
	

}
