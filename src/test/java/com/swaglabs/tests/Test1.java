package com.swaglabs.tests;
import DemoBlaze.Utils.CSVDataProviders;
import DemoBlaze.Utils.CSVDataReader;
import DemoBlaze.pages.BaseTest;
import DemoBlaze.pages.CartPage;
import DemoBlaze.pages.HomePage;
import DemoBlaze.pages.ProductPage;
import Factory.WebDriverFactory;
import java.util.Iterator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void initPages() {
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    public void assertLogin(HomePage homePage, String username, String password) {
        boolean loginSuccess = homePage.Login(username, password);
        Assert.assertTrue(loginSuccess, "Login failed for user: " + username);
    }

    public void addProductToCart(String category, String product) {
        homePage.ChooseCategory(category).ChooseProduct(product);
        productPage.clickAddToCart();
    }




    @Test(dataProvider = "DataProvTest1", invocationCount = 3, priority = 1, dataProviderClass = CSVDataProviders.class)
    public void test1(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("Home");
        addProductToCart(Catergory1, Product1);
        homePage.NavigateTO("cart");
        cartPage.deleteProductFromCart(Product2);
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest2", invocationCount = 3, priority = 2, dataProviderClass = CSVDataProviders.class)
    public void test2(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        addProductToCart(Catergory1, Product1);
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


    @Test(dataProvider = "DataProvTest3", invocationCount = 3, priority = 4, dataProviderClass = CSVDataProviders.class)
    public void test3(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {

        assertLogin(homePage, Username, Password);
        homePage.NavigateTO("cart");
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);

    }


    @Test(dataProvider = "DataProvTest3", invocationCount = 3, priority = 3, dataProviderClass = CSVDataProviders.class)
    public void test4(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {

        addProductToCart(Catergory1, Product1);
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);

    }


    @Test(dataProvider = "DataProvTest4", invocationCount = 3, priority = 8, dataProviderClass = CSVDataProviders.class)
    public void test5(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        addProductToCart(Catergory1, Product1);
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest4", invocationCount = 3, priority = 5, dataProviderClass = CSVDataProviders.class)
    public void test6(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        addProductToCart(Catergory1, Product1);
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest5", invocationCount = 3, priority = 0, dataProviderClass = CSVDataProviders.class)
    public void test7(String Username, String Password, String Catergory1, String Product1, String Catergory2, String Product2,
                      String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        for (int i = 0; i < 10; i++) {
            productPage.clickAddToCart();
        }
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }


    @Test(dataProvider = "DataProvTest5", invocationCount = 3, priority = 7, dataProviderClass = CSVDataProviders.class)
    public void test8(String Username, String Password, String Catergory1, String Product1, String Catergory2, String Product2,
                      String Name, String CreditCard, String Country, String City, String Year, String Month) {


        assertLogin(homePage, Username, Password);
        addProductToCart(Catergory1, Product1);
        homePage.NavigateTO("cart");
        homePage.NavigateTO("Home");
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        cartPage.deleteProductFromCart(Product1);
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.completePurchase(Name, Country, City, CreditCard, Month, Year);


    }
}
