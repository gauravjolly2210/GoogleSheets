package Steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.qait.automation.GoogleApi.GoogleSheetAPI;
import com.qait.automation.GoogleApi.LoginAction;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginTest {
	WebDriver driver;
	LoginAction login;	
	
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
		Thread.sleep(2000);
		List<WebElement> li = driver.findElements(By.className("topbar-list"));
		li.get(0).click();
		String id="ID_01";
		GoogleSheetAPI.validating_Assertion_to_Sheet(id);
		
		WebElement logout = driver.findElement(By.xpath("//li//a//span[text()='Logout']"));
		Thread.sleep(3000);
		logout.click();
		driver.close();
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
		String id="ID_02";
		GoogleSheetAPI.validating_Assertion_to_Sheet(id);
		driver.close();
	}

	@When("^I do not enter Password$")
	public void i_do_not_enter_Password() throws Throwable {
		Thread.sleep(2000);
		login.loginWithInvalidCredentials("gauravjolly","");
	}

	@Then("^assertion for style attribute to be red$")
	public void assertion_for_style_attribute_to_be_red() throws Throwable {
		Assert.assertTrue(driver.findElement(By.id("txtPassword")).getAttribute("style").contains("red"));
		driver.close();
		String id="ID_03";
		GoogleSheetAPI.validating_Assertion_to_Sheet(id);
	}

	
}
