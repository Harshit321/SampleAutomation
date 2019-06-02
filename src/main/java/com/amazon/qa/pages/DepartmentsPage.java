package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class DepartmentsPage extends TestBase{
	
	@FindBy(xpath="//a[contains(text(),'Headphones')]")
	WebElement headPhones;
	
	public DepartmentsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickHeadPhonesCategory() {
		try{
			headPhones.click();
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
	}
}
