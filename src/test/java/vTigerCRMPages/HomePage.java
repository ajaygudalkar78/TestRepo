package vTigerCRMPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import VtigerCRMcommon.CommonActions;

public class HomePage {

	private WebDriver driver;
	private ExtentTest logger;
	public CommonActions ca;
	public HomePage(WebDriver driver,ExtentTest logger)
	{
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver,this);
		ca = new CommonActions(driver,logger);
	}
	@FindBy(linkText="Logout")
	WebElement logout;
	@FindBy(linkText="New Lead")
	WebElement newlead;
	@FindBy(linkText="New Contact")
	WebElement newcontact;
	@FindBy(name="firstname")
	WebElement fname;
	@FindBy(name="lastname")
	WebElement lname;
	@FindBy(name="company")
	WebElement cmpny;
	@FindBy(name="query_string")
	WebElement querysearch;
	@FindBy(xpath="(//input[contains(@value,'Save')])[1]")
	WebElement save;
	@FindBy(xpath="(//input[@value='Search'])[1]")
	WebElement search;
	
	public void clickLogout()
	{
		ca.ClickElement(logout, "Logout link clicked");
	}
	public void clicknewlead()
	{
		ca.ClickElement(newlead, "New Lead link clicked");
	}
	public void clicknewcontact()
	{
		ca.ClickElement(newcontact, "New Contact link clicked");
	}
	public void contact_creation(String first_name,String last_name, String telno)
	{
		ca.EnterValue(fname, first_name,first_name+" has been entered in First name field");
		ca.EnterValue(lname, last_name,last_name+" has been entered in last name field");
		ca.EnterValue(lname, telno,telno+" has been entered in Phone Number field");
	}
	public void Search(String first_name)
	{
		ca.EnterValue(querysearch,first_name,first_name+" has been entered in Search field");
		ca.ClickElement(search, "Search button clicked");
	}
	public void verifyLogout()
	{
		ca.ElementExist(logout, "Logout link exist on page");
	}
	public void EnterLname_comp(String userid,String pwd)
	{
		ca.EnterValue(lname, userid,userid+" has been entered in last name field");
		ca.EnterValue(cmpny, pwd,pwd+" has been entered in company field");
	}
	public void ClickSave()
	{
		ca.ClickElement(save,"Create  button clicked");
	}
	
}
