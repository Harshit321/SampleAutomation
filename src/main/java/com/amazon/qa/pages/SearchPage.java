package com.amazon.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.amazon.qa.base.TestBase;

public class SearchPage extends TestBase{
	
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String selectNthItemFromCategory(int n) {
		String result = null;
		try{
			int count = 0;
			List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@data-cel-widget,'search_result_')]"));
			for(WebElement res: searchResults){
				if(!res.getText().contains("Sponsored"))
					count++;
				if(count == n) {
					result = res.getText();
					res.findElement(By.tagName("a")).click();
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		return result;
	}
}
