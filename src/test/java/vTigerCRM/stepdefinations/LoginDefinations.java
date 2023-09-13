package vTigerCRM.stepdefinations;
import org.openqa.selenium.By;
import org.testng.annotations.AfterSuite;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vTigerCRMPages.HomePage;
import vTigerCRMPages.LoginPage;

public class LoginDefinations extends BaseDefinations {

	@Before
	public void getScenario(Scenario scenario)
	{
		if(driver==null)
		{
			createReport();
		}
		TCName=scenario.getName();
		logger=extent.createTest(TCName);
	}

	@After
	public void savereport()
	{
		extent.flush();
		driver.close();
	}
	@Given("user is on login page")
	public void user_is_on_login_page() {
		init();
		lp=new LoginPage(driver,logger);
		hp=new HomePage(driver,logger);
	}
	@When("user enters invalid userid and password")
	public void user_enters_invalid_userid_and_password() {
		lp.EnterUseridPwd("admin123", "admin123");
	}
	@When("user enters valid userid and password")
	public void user_enters_valid_userid_and_password() {
		lp.EnterUseridPwd("admin", "admin");
	}
	@When("clicks on login button")
	public void clicks_on_login_button() {
		lp.ClickLogin();
	}
	@Then("user should be navigated to login page")
	public void user_should_be_navigated_to_login_page() {
		lp.verifyLogo();
	}
	@Then("user should be navigated to Home page")
	public void user_should_be_navigated_to_home_page() {
		driver.findElement(By.linkText("Home")).isDisplayed();
	}
	@Then("user can see error message")
	public void user_can_see_error_message() {
		lp.verifyErrormsg();
	}
	@Then("user can see logout link")
	public void user_can_see_logout_link() throws InterruptedException {
		driver.findElement(By.linkText("Logout")).isDisplayed();
		Thread.sleep(2000);
	}
	@Then("close browser")
	public void close_browser() {
	//driver.close();
//		driver.quit();
	}

	@When("user click on new lead link")
	public void user_click_on_new_lead_link() {
		hp.clicknewlead();
	}
	@When("user enters last name as {string} and company as {string} and click on save button")
	public void user_enters_last_name_as_and_company_as(String userid, String pwd) {
		hp.EnterLname_comp(userid, pwd);
		hp.ClickSave();
	}
	@Then("lead should be created successfully")
	public void lead_should_be_created_successfully() {
	}

	@When("user click on new contact link")
	public void user_click_on_new_contact_link() {
		hp.clicknewcontact();
	}
	@When("user enters last name,phone number and click on save button")
	public void user_enters_last_name_phone_number_and_click_on_save_button() {
		hp.contact_creation("Ajay", "Gudalkar", "9833794757");
		hp.ClickSave();
	}
	@Then("contact gets created successfully and able to search using search functionality")
	public void contact_gets_created_successfully_and_able_to_search_using_search_functionality() {
		hp.Search("Ajay");
		driver.findElement(By.xpath("(//a[contains(text(),'Ajay')])[2]")).isDisplayed();
	}

	@When("user selects nature from theme dropdown & clicks on login button")
	public void user_selects_nature_from_theme_dropdown_clicks_on_login_button() {
		lp.changetheme("nature");
		lp.ClickLogin();
	}

	@Then("user should be navigated to Home page and clicks logout")
	public void user_should_be_navigated_to_home_page_and_clicks_logout() {
		driver.findElement(By.linkText("Logout")).isDisplayed();
		hp.clickLogout();
	}

	@Then("user can see nature in theme dropdown")
	public void user_can_see_nature_in_theme_dropdown() {
		String theme="nature";
		if(lp.verifytheme().equals(theme))
		{
			System.out.println(lp.verifytheme());
		}
	}

	@When("user clicks on vtiger Customer Portal link")
	public void user_clicks_on_vtiger_customer_portal_link() {
		lp.Click_Cust_portal();
	}

	@Then("user should be navigated to customer portal in new window")
	public void user_should_be_navigated_to_customer_portal_in_new_window() {
		lp.verify_cust_port();
		String wnd=driver.getWindowHandle();
		driver.switchTo().window(wnd);
		
	}


}
