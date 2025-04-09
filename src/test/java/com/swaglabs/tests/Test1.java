
package com.swaglabs.tests;

import DemoBlaze.Utils.CSVDataReader;
import DemoBlaze.pages.CartPage;
import DemoBlaze.pages.HomePage;
import DemoBlaze.pages.ProductPage;
import Factory.WebDriverFactory;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 {
    WebDriver driver;

    public Test1() {
    }

    @DataProvider(name = "Test1")
    public Iterator<Object[]> getLoginData() {
        return CSVDataReader.readCsv("test1.csv");
    }
    @DataProvider(name = "Test2")
    public Iterator<Object[]> getLoginData2() {
        return CSVDataReader.readCsv("test2.csv");
    }

    @Test(dataProvider = "Test1" ,invocationCount =3 )
    public void test1(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("Home");
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        cartPage.deleteProductFromCart(Product2);
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();
        WebDriverFactory.closeDriver();
    }

    @Test(dataProvider = "Test2" ,invocationCount =3 )
    public void test2(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month){
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("Home");
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        homePage.NavigateTO("logout");
        homePage.Login(Username, Password);
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();
        WebDriverFactory.closeDriver();



    }
}
