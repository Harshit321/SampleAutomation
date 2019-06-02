package com.amazon.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.TestBase;

public class ProductPage extends TestBase{

	// Page Factory / Object Repository
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement productTitle;
	
	@FindBy(xpath="//input[@id='add-to-cart-button']")
	WebElement addToCartBtn;
	
	@FindBy(xpath="//select[@id='quantity']")
	WebElement quantityDiv;
	
	@FindBy(xpath="//a[@id='attach-close_sideSheet-link']")
	WebElement closeCartPoupBtn;
	
	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateProductTitle() {
		String title = null;
		try {
			title = productTitle.getText();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception: "+e.getMessage());
		}
		return title;
	}
	
	public void clickAddToCartBtn() {
		try {
			if(addToCartBtn.isDisplayed() && addToCartBtn.isEnabled())
			addToCartBtn.click();
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
		}
	}
	
	public void select_quantity(int qty) {
		try {
			if(driver.findElements(By.xpath("//select[@id='quantity']")).size() != 0) {
				Select quantity = new Select(quantityDiv);
				quantity.selectByValue(Integer.toString(qty));
			}
			else {
				System.out.println("Only one item can be selected");
				throw new Exception("Only one item can be selected");
			}
		}
		catch(Exception ex) {
			System.out.println("Exception: "+ex.getMessage());
		}
	}
	
	public void closePopup() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	public void closeCartPoup() {
		try {
			if(closeCartPoupBtn.isDisplayed())
				closeCartPoupBtn.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
