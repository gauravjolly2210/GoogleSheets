package com.qait.automation.GoogleApi;

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
}