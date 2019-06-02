package com.amazon.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.DepartmentsPage;
import com.amazon.qa.pages.HomePage;
import com.amazon.qa.pages.LoginPage;
import com.amazon.qa.pages.ProductPage;
import com.amazon.qa.pages.SearchPage;
import com.amazon.qa.pages.ShoppingCartPage;
import com.amazon.qa.utils.TestUtil;

public class ShoppingCartPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage; 
	DepartmentsPage departmentsPage;
	ProductPage productPage;
	SearchPage searchPage;
	ShoppingCartPage shoppingCartPage;
	String sheetName = "Sheet1";

	public ShoppingCartPageTest() throws IOException{
		super();
	}
	
	@BeforeMethod
	public void setup() throws IOException {
		intialization();
		homePage = new HomePage();
		loginPage = new LoginPage();
		departmentsPage = new DepartmentsPage();
		productPage = new ProductPage();
		searchPage = new SearchPage();
		shoppingCartPage = new ShoppingCartPage();
	}
	
	@DataProvider
	public Object[][] getProductToSearch(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@Test(dataProvider="getProductToSearch")
	public void shoppingCartTest(String product){
		try {
			homePage.clickDepartmentButton();
			departmentsPage.clickHeadPhonesCategory();
			String headPhoneName = searchPage.selectNthItemFromCategory(1);
			Assert.assertEquals(headPhoneName.contains(productPage.validateProductTitle()), true, "Selected Headphones are not opened on product page");
			headPhoneName = productPage.validateProductTitle();
			productPage.clickAddToCartBtn();
			Thread.sleep(2000);
			productPage.closePopup();
			homePage.searchProduct(product);
			String macTitle = searchPage.selectNthItemFromCategory(2);
			Assert.assertEquals(macTitle.contains(productPage.validateProductTitle()), true, "Selected macbook is not opened on product page");
			macTitle = productPage.validateProductTitle();
			productPage.select_quantity(2);
			productPage.clickAddToCartBtn();
			Thread.sleep(2000);
			productPage.closePopup();
			homePage.goToCart();
			Assert.assertEquals(shoppingCartPage.getCountofTypeofItemsInCart(), 2, "Cart doesn't have required items");
			Assert.assertEquals(shoppingCartPage.getQuantityOfItem(macTitle), 2, "Cart doesn't have designated macbook quantity");
			Assert.assertEquals(shoppingCartPage.getQuantityOfItem(headPhoneName), 1, "Cart doesn't have designated headphone quantity");
			shoppingCartPage.removeItem(headPhoneName);
			Assert.assertTrue(shoppingCartPage.removeMsgVisible(), "Cart doesn't have msg visible");
			shoppingCartPage.changeQuantityOfItem(macTitle, 1);
			Assert.assertEquals(shoppingCartPage.getQuantityOfItem(macTitle), 1, "Cart doesn't have designated macbook quantity");
			shoppingCartPage.clickProceedToCheckOut();
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
