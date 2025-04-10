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

    @DataProvider(name = "DataProvTest1")
    public Iterator<Object[]> getLoginData() {
        return CSVDataReader.readCsv("test1.csv");
    }

    @DataProvider(name = "DataProvTest2")
    public Iterator<Object[]> getLoginData2() {
        return CSVDataReader.readCsv("test2.csv");
    }

    @DataProvider(name = "DataProvTest3")
    public Iterator<Object[]> getLoginData3() {
        return CSVDataReader.readCsv("test3" +
                ".csv");
    }
    @DataProvider(name = "DataProvTest4")
    public Iterator<Object[]> getLoginData4() {
        return CSVDataReader.readCsv("test4" +
                ".csv");
    }
    @DataProvider(name = "DataProvTest5")
    public Iterator<Object[]> getLoginData5() {
        return CSVDataReader.readCsv("test5" +
                ".csv");
    }


// ============================================================================
// 🔹 SCENARIO 1 – FULL E2E: LOGIN → ADD TWO PRODUCTS → DELETE ONE → PURCHASE
// ============================================================================


    @Test(dataProvider = "DataProvTest1", invocationCount =3 , priority = 1)
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
// ============================================================================
// 🔚 End of Scenario 1
// ============================================================================



// ============================================================================
// 🔹 SCENARIO 2 – LOGIN, ADD PRODUCTS, LOGOUT → LOGIN AGAIN → PURCHASE
// ============================================================================

    @Test(dataProvider = "DataProvTest2", invocationCount =3 , priority = 2)
    public void test2(String Username, String Password, String Catergory1, String Product1, String Catergory2,
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

// ============================================================================
// 🔚 End of Scenario 2
// ============================================================================



// ============================================================================
// 🔹 SCENARIO 3 – LOGIN → GO TO CART DIRECTLY → PLACE ORDER (EMPTY CART)
// ============================================================================


    @Test(dataProvider = "DataProvTest3", invocationCount =3 , priority = 4)
    public void test3(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        driver.get("https://demoblaze.com/");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage();
        homePage.Login(Username, Password);
        homePage.NavigateTO("cart");

        CartPage cartPage = new CartPage();
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();

        WebDriverFactory.closeDriver();
    }

// ============================================================================
// 🔚 End of Scenario 3
// ============================================================================




// ============================================================================
// 🔹 SCENARIO 4 – ADD ONE PRODUCT → VALIDATE CART → PLACE ORDER
// ============================================================================


    @Test(dataProvider = "DataProvTest3", invocationCount =3 , priority = 3)
    public void test4(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        driver.get("https://demoblaze.com/");
        driver.manage().window().maximize();

        HomePage homePage = new HomePage();
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);

        ProductPage productPage = new ProductPage();
        productPage.clickAddToCart();

        homePage.NavigateTO("cart");

        CartPage cartPage = new CartPage();
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());

        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();

        WebDriverFactory.closeDriver();
    }

// ============================================================================
// 🔚 End of Scenario 4
// ============================================================================


// ============================================================================
// 🔹 SCENARIO 5 – INCOMPLETE USER INFO: MISSING CITY AND COUNTRY
// ============================================================================

    @Test(dataProvider = "DataProvTest4", invocationCount =3 , priority = 8)
    public void test5(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();

        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();

        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());

        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, "", "", CreditCard, "", "");
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();

        WebDriverFactory.closeDriver();
    }

// ============================================================================
// 🔚 End of Scenario 5
// ============================================================================




// ============================================================================
// 🔹 SCENARIO 6 – INCOMPLETE USER INFO: MISSING NAME AND CARD
// ============================================================================


    @Test(dataProvider = "DataProvTest4", invocationCount =3 , priority = 5)
    public void test6(String Username, String Password, String Catergory1, String Product1, String Catergory2,
                      String Product2, String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();

        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();

        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();

        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());

        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(" ", Country, City, " ", Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();

        WebDriverFactory.closeDriver();
    }
// ============================================================================
// 🔚 End of Scenario 6
// ============================================================================





// ============================================================================
// 🔹 SCENARIO 7 – STRESS TEST: ADD SAME PRODUCT 10 TIMES
// ============================================================================

    @Test(dataProvider = "DataProvTest5" ,invocationCount =3 , priority = 0)
    public void test7(String Username, String Password, String Catergory1, String Product1, String Catergory2, String Product2,
                      String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();
        WebDriverFactory.closeDriver();
    }

// ============================================================================
// 🔚 End of Scenario 7
// ============================================================================



// ============================================================================
// 🔹 SCENARIO 8 – ADD ONE PRODUCT → NAVIGATE AWAY → ADD ANOTHER PRODUCT
// ============================================================================

    @Test(dataProvider = "DataProvTest5" ,invocationCount =3 , priority = 7 )
    public void test8(String Username, String Password, String Catergory1, String Product1, String Catergory2, String Product2,
                      String Name, String CreditCard, String Country, String City, String Year, String Month) {
        this.driver = WebDriverFactory.getDriver();
        this.driver.get("https://demoblaze.com/");
        this.driver.manage().window().maximize();
        HomePage homePage = new HomePage();
        ProductPage productPage = new ProductPage();
        CartPage cartPage = new CartPage();
        homePage.Login(Username, Password);
        homePage.ChooseCategory(Catergory1).ChooseProduct(Product1);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        homePage.NavigateTO("Home");
        homePage.ChooseCategory(Catergory2).ChooseProduct(Product2);
        productPage.clickAddToCart();
        homePage.NavigateTO("cart");
        cartPage.deleteProductFromCart(Product1);
        Assert.assertEquals(cartPage.ActualPrice(), cartPage.expectedPrice());
        cartPage.ClickPlaceOrderButton();
        cartPage.fillOrderDetails(Name, Country, City, CreditCard, Month, Year);
        cartPage.clickPurchaseButton();
        cartPage.clickConfirmationButton();
        WebDriverFactory.closeDriver();
    }

// ============================================================================
// 🔚 End of Scenario 8
// ============================================================================
}
