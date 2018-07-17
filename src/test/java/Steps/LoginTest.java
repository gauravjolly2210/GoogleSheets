package Steps;

import java.util.Collection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.qait.automation.GoogleApi.GoogleSheetAPI;
import com.qait.automation.GoogleApi.LoginAction;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest {
	WebDriver driver;
	LoginAction login;
	String id;
	@Before
	public void gettingID(Scenario scenario) {
		
		System.out.println("executed");
	}
	
	@Given("^I open HRIS Application$")
	public void i_open_HRIS_Application() throws Throwable {
		driver=new ChromeDriver();
		driver.get("https://hris.qainfotech.com");
		login=new LoginAction(driver);
	}
	
	@When("^I enter valid credentials$")
	public void i_enter_valid_credentials() throws Throwable {
		login.loginWithInvalidCredentials("gauravjolly", "Gaurav@123#");	
	}

	@Then("^assertion for time sheet page$")
	public void assertion_for_time_sheet_page() throws Throwable {
		Assert.assertNotEquals(driver.getCurrentUrl(), "https://hris.qainfotech.com/login.php");
		login.logout();
		
	}

	@When("^I enter invalid credentials$")
	public void i_enter_invalid_credentials() throws Throwable {
		Thread.sleep(2000);
		login.loginWithInvalidCredentials("gauravjolly","Invalid_Password");
	}

	@Then("^assertion for invalid login text$")
	public void assertion_for_invalid_login_text() throws Throwable {
		Assert.assertTrue(driver.findElement(By.className("loginTxt"))
				.getText().contains("Invalid Login"));
	}

	@When("^I do not enter Password$")
	public void i_do_not_enter_Password() throws Throwable {
		Thread.sleep(2000);
		login.loginWithInvalidCredentials("gauravjolly","");
	}

	@Then("^assertion for style attribute to be red$")
	public void assertion_for_style_attribute_to_be_red() throws Throwable {
		Assert.assertTrue(driver.findElement(By.id("txtPassword")).getAttribute("style").contains("red"));
	}

	@After
	public void validation(Scenario scenario) throws Exception {
		
		id=scenario.getSourceTagNames().toString();
		String id1=id.substring(2,7);
		System.out.println(id1);
		GoogleSheetAPI.validating_Assertion_to_Sheet(id1,"Pass");
		login.closingDriver();
	}
	
}
