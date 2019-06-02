package com.amazon.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.amazon.qa.base.TestBase;

public class ShoppingCartPage extends TestBase{
	
	@FindBy(xpath="//input[@name='proceedToCheckout']")
	WebElement proceedToChkOutBtn;
	
	public ShoppingCartPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void removeItem(String product) {
		try {
			List<WebElement> delButtons = driver.findElements(By.xpath("//input[contains(@name, 'submit.delete.')]"));
			for(WebElement delBtn : delButtons) {
				if(delBtn.getAttribute("aria-label").contains(product)) {
					System.out.println(delBtn.getAttribute("aria-label"));
					delBtn.click();
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void clickProceedToCheckOut() {
		proceedToChkOutBtn.click();
	}
	
	public int getCountofTypeofItemsInCart() {
		try {
			List<WebElement> cartItems = driver.findElements(By.xpath("//div[contains(@id, 'sc-item-')]"));
			Thread.sleep(1000);
			System.out.println(cartItems.size());
			return cartItems.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public Boolean removeMsgVisible() {
		Boolean flag = false;
		try {
			List<WebElement> msgDivs = driver.findElements(By.xpath("//div[@class='sc-list-item-removed-msg a-padding-medium']"));
			for(WebElement msgDiv:  msgDivs) {
				Thread.sleep(1000);
				if(!msgDiv.getCssValue("display").contentEquals("none")) {
					System.out.println(msgDiv.getText());
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getQuantityOfItem(String productTitle) {
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[contains(@id, 'sc-item-')]"));
		int count=-1;
		try {
			for(WebElement item : cartItems) {
				if(item.getText().contains(productTitle)) {
					count = Integer.parseInt(item.getAttribute("data-quantity"));
					System.out.println(count);
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public void changeQuantityOfItem(String productTitle, int qty) {
		try {
			List<WebElement> cartItems = driver.findElements(By.xpath("//div[contains(@id, 'sc-item-')]"));
			if(qty > 0) {
				for(WebElement item : cartItems) {
					if(item.getText().contains(productTitle)) {
						int count = Integer.parseInt(item.getAttribute("data-quantity"));
						if(count == qty) {
							System.out.println("Quantity is as expected");
							break;
						}
						else {
							WebElement qtyDd = item.findElement(By.xpath("//select[@name='quantity']"));
							Select selectQty = new Select(qtyDd);
							selectQty.selectByValue(Integer.toString(qty));
							Thread.sleep(1000);
							//qtyDd.click();
							//qtyDd.sendKeys(Integer.toString(qty));
							//qtyDd.sendKeys(Keys.ENTER);
							break;
						}
					}
				}
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
