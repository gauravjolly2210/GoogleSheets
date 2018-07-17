package com.qait.automation.GoogleApi;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginAction{
	WebElement userName;
	WebElement password;
	WebDriver driver;
	
	public LoginAction(WebDriver driver) {
		this.driver=driver;
	}
	public void loginWithInvalidCredentials(String userName,String password) {
		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.id("txtUserName")).sendKeys(userName);
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("txtPassword")).submit();
	}
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> li = driver.findElements(By.className("topbar-list"));
		li.get(0).click();
		WebElement logout = driver.findElement(By.xpath("//li//a//span[text()='Logout']"));
		Thread.sleep(3000);
		logout.click();
	}
	public void closingDriver() {
		driver.close();
	}
}