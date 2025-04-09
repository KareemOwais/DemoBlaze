package com.swaglabs.tests;

import Factory.WebDriverFactory;
import DemoBlaze.pages.CartPage;
import DemoBlaze.pages.HomePage;
import DemoBlaze.pages.ProductPage;
import org.openqa.selenium.WebDriver;


import org.testng.Assert;
import org.testng.annotations.Test;


public class Test1 {

    WebDriver driver ;

    @Test
    public void test1() {
        this.driver = WebDriverFactory.getDriver();
        driver.get("https://demoblaze.com/");
        driver.manage().window().maximize();
        HomePage homePage = new HomePage();
        homePage.Login("kareem1234","1234");
        ProductPage productPage = new ProductPage();
        homePage.ChooseCategory("phones").
                ChooseProduct("Samsung galaxy s7");
        productPage.clickAddToCart();
        homePage.NavigateTO("Home");
        homePage.ChooseCategory("phones").
                ChooseProduct("Samsung galaxy s7");
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        CartPage cartPage = new CartPage();
        cartPage.deleteProductFromCart("Samsung galaxy s7");
        Assert.assertEquals(cartPage.ActualPrice(),cartPage.expectedPrice());
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails("kareem","1234567890","Cairo","Egypt","123456","2024");
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();
        WebDriverFactory.closeDriver();
    }
}
