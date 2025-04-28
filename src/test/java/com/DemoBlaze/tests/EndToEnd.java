package com.DemoBlaze.tests;
import DemoBlaze.Utils.CSVDataProviders;

import DemoBlaze.pages.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
public class EndToEnd extends BaseTest {

    @Test(dataProvider = "DataProvTest1", invocationCount = 1, priority = 1, dataProviderClass = CSVDataProviders.class)
    @Description("End to End Test 1")
    public void test1(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("Home");
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest2", invocationCount = 1, priority = 2, dataProviderClass = CSVDataProviders.class)
    public void test2(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        homePage.Login(Username,Password);
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
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest3", invocationCount = 1, priority = 4, dataProviderClass = CSVDataProviders.class)
    public void test3(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        homePage.NavigateTO("cart");
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);

    }


    @Test(dataProvider = "DataProvTest3", invocationCount = 1, priority = 3, dataProviderClass = CSVDataProviders.class)
    public void test4(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {

        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);

    }


    @Test(dataProvider = "DataProvTest4", invocationCount = 1, priority = 8, dataProviderClass = CSVDataProviders.class)
    public void test5(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        homePage.Login(Username,Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }





    @Test(dataProvider = "DataProvTest5", invocationCount = 1, priority = 0, dataProviderClass = CSVDataProviders.class)
    public void test7(String Username, String Password, String Catergory1, String Product1, String Catergory2, String Product2,
                      String Name, String CreditCard, String Country, String City, String Year, String Month) {

        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        for (int i = 0; i < 10; i++) {
            productPage.clickAddToCart();
        }
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


}
