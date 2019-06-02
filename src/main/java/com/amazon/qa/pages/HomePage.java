package com.amazon.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(id="nav-link-accountList")
	WebElement signInDiv;
	
	@FindBy(xpath="//span[contains(text(),'Sign Out')]")
	WebElement signOutBtn;
	
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBox;
	
	@FindBy(xpath="//a[@id='nav-cart']")
	WebElement cart;
	
	@FindBy(xpath="//span[contains(text(),'Departments')]")
	WebElement departmentBtn;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}
	
	public void clickSignInBtn() {
		signInDiv.click();
	}
	
	public String getsignInDivText() {
		return signInDiv.getText();
	}
	
	public void logout() {
		try {
			Actions hover = new Actions(driver);
			hover.moveToElement(signInDiv).build().perform();
			signOutBtn.click();
		}
		catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}
	
	public void searchProduct(String productName) {
		try{
			searchBox.click();
			searchBox.sendKeys(productName);
			searchBox.sendKeys(Keys.ENTER);
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
		}
	}
	
	public void goToCart() {
		try {
			cart.click();
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
		}
	}
	
	public void clickDepartmentButton() {
		departmentBtn.click();
	}
	
}
