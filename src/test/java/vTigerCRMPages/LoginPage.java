package vTigerCRMPages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import VtigerCRMcommon.CommonActions;

public class LoginPage {
	
	private WebDriver driver;
	public CommonActions ca;
	public String seltheme;
	private ExtentTest logger;
	
	public LoginPage(WebDriver driver,ExtentTest logger)
	{
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver,this);
		ca=new CommonActions(driver,logger);
	}
	@FindBy(name="user_name")
	WebElement username;
	
	@FindBy(name="user_password")
	WebElement password;
	
	@FindBy(name="Login")
	WebElement login;
	
	@FindBy(linkText="vtiger Customer Portal")
	WebElement cust_port_link;
	
	@FindBy(xpath="//*[contains(text(),'You must specify a valid username and password.')]")
	WebElement ErrorMsg;
	
	@FindBy(xpath="//img[@src='include/images/vtiger-crm.gif']")
	WebElement logo;
	
	@FindBy(linkText="vtiger Customer Portal")
	WebElement custportal;
	
	@FindBy(xpath="//select[@name='login_theme']")
	WebElement theme;
	
	@FindBy(xpath="//span[text()='customer portal']")
	WebElement custport;
	
	public void login(String userid,String pwd)
	{
		ca.EnterValue(username, userid,userid+" has been entered in username field");
		ca.EnterValue(password, pwd,pwd+" has been entered in username field");
		ca.ClickElement(login,"Login button clicked");
	}
	public void EnterUseridPwd(String userid,String pwd)
	{
		ca.EnterValue(username, userid,userid+" has been entered in username field");
		ca.EnterValue(password, pwd,pwd+" has been entered in username field");
	}
	public void ClickLogin()
	{
		ca.ClickElement(login,"Login button clicked");
	}
	public void Click_Cust_portal()
	{
		ca.ClickElement(cust_port_link,"Customer portal link clicked");
	}
	public void verifyErrormsg()
	{
		ca.ElementExist(ErrorMsg,"Error message has been validated successfully");
	}
	
	public void verifyLogo()
	{
		ca.ElementExist(logo,"Logo exists on Login page");
	}
	public void verifyCustomerPortalLink()
	{
		ca.ElementExist(custportal, "Customer Portal Link exist on Login Page");
	}
	public void changetheme(String themee)
	{
		try {
		Select se=new Select(theme);
		logger.pass("Nature theme is selected from dropdown");
		se.selectByVisibleText(themee);
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.fail("unable to select value from dropdown due to error : "+e.getMessage()+"<a href='"+ca.getscreenshot()+"'><span class='label end-time'>ScreenShot</span></a>");
		}
	}
	public String verifytheme()
	{
		try {
		Select se=new Select(theme);
		seltheme=se.getFirstSelectedOption().getText();
		logger.pass("Theme "+seltheme+" is selected and verified.");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.fail("unable to get value from dropdown due to error : "+e.getMessage()+"<a href='"+ca.getscreenshot()+"'><span class='label end-time'>ScreenShot</span></a>");
		}
		return seltheme;
	}
	public void verify_cust_port()
	{
		Set<String> wnd=driver.getWindowHandles();
		Iterator<String> itr=wnd.iterator();
		String wnd1=itr.next();
		String wnd2=itr.next();
		driver.switchTo().window(wnd2);
		ca.ElementExist(custport, "Customer portal tab is verfied open in new window.");
	}
	

}
