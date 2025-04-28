package DemoBlaze.pages;


import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.testng.Assert;

import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;


public class HomePage {
    //Locators
    private static final Logger logger = LoggerFactory.getLogger(HomePage.class);

    private final Button LogoutButton = new Button(By.id("logout2"), "Logout Button");
    private Button SignUpBUtton = new Button(By.id("signin2"),"SignUp Button");
    private Button LoginButton= new Button(By.id("login2"),"Login Button");
    private Button HomeButton= new Button(By.xpath("//a[text()='Home ']"),"Home Button");
    private Button CartButton= new Button(By.id("cartur"),"Cart Button");
    private Label CheckLabel = new Label(By.xpath("(//img[@class='card-img-top img-fluid'])[1]"));
    private Label WelcomeText = new Label(By.id("nameofuser"));
    private Textbox usernameTextbox = new Textbox(By.id("loginusername"),"Username Textbox");
    private Textbox passwordTextbox = new Textbox(By.id("loginpassword"),"Password Textbox");
    private Button loginButtonForm = new Button(By.xpath("//button[text()='Log in']"),"Login Button Form");
    private Button PhonesCategory = new Button(By.xpath("//a[text()='Phones']"),"Phones Category");
    private Button LaptopsCategory = new Button(By.xpath("//a[text()='Laptops']"),"Laptops Category");
    private Button MonitorsCategory = new Button(By.xpath("//a[text()='Monitors']"),"Monitors Category");
    private Button CategoryButton = new Button(By.xpath("//a[text()='CATEGORIES']"),"All Categories Buttons");

    public void NavigateTO(String button){
        switch (button.toLowerCase()) {
            case "signup":
                SignUpBUtton.click();
                break;
            case "login":
                LoginButton.click();
                break;
            case "cart":
                CartButton.click();
                break;
            case "home":
                HomeButton.click();
                break;
            case "logout":
                LogoutButton.click();
                break;
            default:
                logger.error("Invalid button clicked");
        }
    }
    public void Login(String username, String password) {
        LoginButton.click();
        usernameTextbox.setText(username);
        passwordTextbox.setText(password);
        loginButtonForm.click();
        waitForElementToBeVisible(WebDriverFactory.getDriver(),WelcomeText.Locator);
        Assert.assertEquals("Welcome " + username, WelcomeText.getText());
        logger.info("User logged in successfully");
    }
    public HomePage ChooseCategory(String category) {
        waitForElementToBeVisible(WebDriverFactory.getDriver(), CheckLabel.Locator);
        CategoryButton.click();
        switch (category.toLowerCase()) {
            case "phones":
                PhonesCategory.click();
                break;
            case "laptops":
                LaptopsCategory.click();
                break;
            case "monitors":
                MonitorsCategory.click();
                break;
            default:
                logger.error("Invalid category clicked");
        }
        return this;
    }
    public void ChooseProduct(String product) {
            waitForElementToBeVisible(WebDriverFactory.getDriver(), CheckLabel.Locator);
            String xpath = "//a[text()='" + product + "']";
            Button productButton = new Button(By.xpath(xpath),"Product Button");
            productButton.click();

    }
    

}
