package DemoBlaze.pages;

import DemoBlaze.Utils.WaitUtils;
import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class HomePage {
    //Locaters
    private Button LogoutButton = new Button(By.id("logout2"));
    private Button SignUpBUtton = new Button(By.id("signin2"));
    private Button LoginButton= new Button(By.id("login2"));
    private Button HomeButton= new Button(By.xpath("//a[text()='Home ']"));
    private Button CartButton= new Button(By.id("cartur"));
    private Label WelcomeText = new Label(By.xpath("//a[contains(text(), 'Welcome')]"));
    private Textbox usernameTextbox = new Textbox(By.id("loginusername"));
    private Textbox passwordTextbox = new Textbox(By.id("loginpassword"));
    private Button loginButton = new Button(By.xpath("//button[text()='Log in']"));
    private Button PhonesCategory = new Button(By.xpath("//a[text()='Phones']"));
    private Button LaptopsCategory = new Button(By.xpath("//a[text()='Laptops']"));
    private Button MonitorsCategory = new Button(By.xpath("//a[text()='Monitors']"));
    private Button CategoryButton = new Button(By.xpath("//a[text()='CATEGORIES']"));

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
                System.out.println("Invalid button");
        }
    }
    public boolean Login(String username, String password) {
        LoginButton.click();
        usernameTextbox.setText(username);
        passwordTextbox.setText(password);
        loginButton.click();

        try {
            Alert alert = new WebDriverWait(WebDriverFactory.getDriver(), Duration.ofSeconds(3))
                    .until(ExpectedConditions.alertIsPresent());

            String alertText = WebDriverFactory.getDriver().switchTo().alert().getText();
            System.out.println("Alert appeared: " + alertText);
            WebDriverFactory.getDriver().switchTo().alert().accept();
            return false; // Login failed
        } catch (TimeoutException e) {
            // No alert = login success → wait for Welcome
            waitForElementToBeVisible(WebDriverFactory.getDriver(), WelcomeText.Locator);
            return true;
        }
    }

    public HomePage ChooseCategory(String category) {
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
                System.out.println("Invalid category");
        }
        return this;
    }

    public void ChooseProduct(String product) {
        String xpath = "//a[text()='" + product + "']";
        Button productButton = new Button(By.xpath(xpath));
        productButton.click();
    }
    

}
