package DemoBlaze.pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.*;
import static DemoBlaze.Utils.AnimationHandler.waitForSuccessAnimation;
import static DemoBlaze.Utils.WaitUtils.*;


public class CartPage {
    //Locators
    private WebDriver driver = WebDriverFactory.getDriver();

    private Label TotalPrice = new Label(By.id("totalp"));
    Label PlaceOrderLabel = new Label(By.id("orderModalLabel"));
        Button ConfirmationButton = new Button(By.xpath("//button[text()='OK']"));
        private Button PlaceOrderButton = new Button(By.xpath("//button[@class='btn btn-success']"));
        private Label LogoLabel= new Label(By.xpath("//span[@class='sa-line sa-long animateSuccessLong']"));
        private Textbox NameTextbox = new Textbox(By.id("name"));
        private Textbox CountryTextbox = new Textbox(By.id("country"));
        private Textbox CityTextbox = new Textbox(By.id("city"));
        private Textbox CreditCardTextbox = new Textbox(By.id("card"));
        private Textbox MonthTextbox = new Textbox(By.id("month"));
        private Textbox YearTextbox = new Textbox(By.id("year"));
        private Button PurchaseButton = new Button(By.xpath("//button[text()='Purchase']"));
        private Button CloseButton = new Button(By.xpath("//*[@id='orderModal']/div/div/div[3]/button[1]"));
        private List<Integer> cartItems = new ArrayList<>();
        public int ActualPrice(){
            waitForElementToBeVisible(WebDriverFactory.getDriver(),TotalPrice.Locator);
            String Total_String = TotalPrice.getText();
            System.out.println("Actual Price : " + Total_String);
            return Integer.parseInt(Total_String);

        }
        public int expectedPrice(){
            getCartItems();
            int sum = 0;
            for (Integer item : cartItems) {
                sum += item;
            }
            System.out.println("Sum of cart items: " + sum);
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
            System.out.println(price);
            int x = Integer.parseInt(price);
            cartItems.add(x);
        }
    }

    public void deleteProductFromCart(String productName) {
        waitForElementToBeVisible(WebDriverFactory.getDriver(),TotalPrice.Locator);
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='success']"));

        for (WebElement row : rows) {
            String name = row.findElement(By.xpath("./td[2]")).getText().trim();
            System.out.println(name);
            System.out.println(productName);
            if (name.equalsIgnoreCase(productName)) {
                row.findElement(By.xpath("./td[4]/a")).click();
               waitForProductToDisappear(productName , driver);
                break;
            }
            else{
                System.out.println("NotFound");
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
    }
    public void clickPurchaseButton() {
        PurchaseButton.click();
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
