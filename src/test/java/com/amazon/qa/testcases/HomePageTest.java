package com.amazon.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage; 
	
	public HomePageTest() throws IOException{
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		intialization();
		homePage = new HomePage();
		loginPage = new LoginPage();
	}
	

	@Test(priority=1)
	public void loginTest(){
		try {
			homePage.clickSignInBtn();
			String title = loginPage.validateLoginPageTitle();
			Assert.assertEquals(title, "Amazon Sign In", "Login Page not found");
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			String loggedinUser = homePage.getsignInDivText();
			Assert.assertFalse(loggedinUser.contains("Sign in"), "Login failed.");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	@Test(priority=2)
	public void logOutTest() {
		try {
			homePage.clickSignInBtn();
			String title = loginPage.validateLoginPageTitle();
			Assert.assertEquals(title, "Amazon Sign In", "Login Page not found");
			loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
			String loggedinUser = homePage.getsignInDivText();
			Assert.assertFalse(loggedinUser.contains("Sign in"), "Login failed.");
			homePage.logout();
			title = loginPage.validateLoginPageTitle();
			Assert.assertEquals(title, "Amazon Sign In", "LogOut Failed");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
