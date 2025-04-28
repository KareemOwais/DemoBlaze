package DemoBlaze.pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import static DemoBlaze.Utils.AnimationHandler.waitForSuccessAnimation;
import static DemoBlaze.Utils.WaitUtils.*;


public class CartPage {
    //Locators
    private WebDriver driver = WebDriverFactory.getDriver();
    private static final Logger logger = LoggerFactory.getLogger(CartPage.class);

    private Label TotalPrice = new Label(By.id("totalp"));
    Label PlaceOrderLabel = new Label(By.id("orderModalLabel"));
        Button ConfirmationButton = new Button(By.xpath("//button[text()='OK']"),"Confirmation Button");
        private Button PlaceOrderButton = new Button(By.xpath("//button[@class='btn btn-success']"),"Place Order Button");
        private Label LogoLabel= new Label(By.xpath("//span[@class='sa-line sa-long animateSuccessLong']"));
        private Textbox NameTextbox = new Textbox(By.id("name"),"Name TextBox in Payment");
        private Textbox CountryTextbox = new Textbox(By.id("country"),"Country TextBox in Payment");
        private Textbox CityTextbox = new Textbox(By.id("city"),"City Textbox in Payment");
        private Textbox CreditCardTextbox = new Textbox(By.id("card"),"Card number TextBox");
        private Textbox MonthTextbox = new Textbox(By.id("month" ),"Month Textbox");
        private Textbox YearTextbox = new Textbox(By.id("year") , "Year TextBox");
        private Button PurchaseButton = new Button(By.xpath("//button[text()='Purchase']"),"Purchase Button");
        private Button CloseButton = new Button(By.xpath("//*[@id='orderModal']/div/div/div[3]/button[1]"),"Close Payment from Button");
        private List<Integer> cartItems = new ArrayList<>();
        public int ActualPrice(){
            waitForElementToBeVisible(WebDriverFactory.getDriver(),TotalPrice.Locator);
            String Total_String = TotalPrice.getText();
            logger.info("Actual price Calculated : " + Total_String);
            return Integer.parseInt(Total_String);

        }

        public int expectedPrice(){
            getCartItems();
            int sum = 0;
            for (Integer item : cartItems) {
                sum += item;
            }
            return sum;
        }
        public int getProductCount() {
            return cartItems.size();
        }
        public void ClickPlaceOrderButton() {
                PlaceOrderButton.click();
        }
        public void getCartItems() {
        cartItems.clear(); // clear previous content
        waitForElementToBeVisible(WebDriverFactory.getDriver(),TotalPrice.Locator);
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='success']"));

        for (WebElement row : rows) {
            String price = row.findElement(By.xpath("./td[3]")).getText().trim();
            int x = Integer.parseInt(price);
            cartItems.add(x);
        }
    }
    @Step("Delete product from cart: {productName}")
    public void deleteProductFromCart(String productName) {
        waitForElementToBeVisible(WebDriverFactory.getDriver(),TotalPrice.Locator);
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='success']"));

        for (WebElement row : rows) {
            String name = row.findElement(By.xpath("./td[2]")).getText().trim();
            if (name.equalsIgnoreCase(productName)) {
                row.findElement(By.xpath("./td[4]/a")).click();
               waitForProductToDisappear(productName , driver);
                logger.info("Product deleted from cart successfully");
                break;
            }
            else{
                logger.error("Product not found in cart");
            }
        }
        getCartItems();
    }
    public void fillOrderDetails(String name, String country, String city, String creditCard, String month, String year) {
        waitForElementToBeVisible(WebDriverFactory.getDriver(), PlaceOrderLabel.Locator);
        NameTextbox.setText(name);
        CountryTextbox.setText(country);
        CityTextbox.setText(city);
        CreditCardTextbox.setText(creditCard);
        MonthTextbox.setText(month);
        YearTextbox.setText(year);
        logger.info("Order details filled successfully");
    }

    public void clickPurchaseButton() {
        PurchaseButton.click();
        logger.info("Purchase button clicked successfully");
    }

    public void clickCloseButton() {
        CloseButton.click();
    }

    public void clickConfirmationButton() {
        waitForSuccessAnimation(driver, LogoLabel.Locator);
        ConfirmationButton.click();
    }
    public void completePurchase(String name, String country, String city, String card, String month, String year) {
        ClickPlaceOrderButton();
        fillOrderDetails(name, country, city, card, month, year);
        clickPurchaseButton();
        clickConfirmationButton();
    }
}
