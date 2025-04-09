package DemoBlaze.pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import Interactions.Textbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class HomePage {
    //Locaters
    private WebDriver driver ;
    public static String[] Products = new String[10];
    public static int[] ProductsPrices = new int[10];
    private Button LogoutButton = new Button(By.id("logout2"));
    private Button SignUpBUtton = new Button(By.id("signin2"));
    private Button LoginButton= new Button(By.id("login2"));
    private Button HomeButton= new Button(By.xpath("//a[text()='Home ']"));
    private Button CartButton= new Button(By.id("cartur"));
    private Label WelcomeText= new Label(By.xpath("//a[normalize-space()='Welcome kareem1234']"));
    private Textbox usernameTextbox = new Textbox(By.id("loginusername"));
    private Textbox passwordTextbox = new Textbox(By.id("loginpassword"));
    private Button loginButton = new Button(By.xpath("//button[text()='Log in']"));
    private Button PhonesCategory = new Button(By.xpath("//a[text()='Phones']"));
    private Button LaptopsCategory = new Button(By.xpath("//a[text()='Laptops']"));
    private Button MonitorsCategory = new Button(By.xpath("//a[text()='Monitors']"));
    private Button CategoryButton = new Button(By.xpath("//a[text()='CATEGORIES']"));

    public void NavigateTO(String button){
        waitForElementToBeVisible(WebDriverFactory.getDriver(),WelcomeText.Locator);
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
    public void Login(String username, String password) {
        LoginButton.click();
        usernameTextbox.setText(username);
        passwordTextbox.setText(password);
        loginButton.click();
    }
    public HomePage ChooseCategory(String category) {
        waitForElementToBeVisible(WebDriverFactory.getDriver(),WelcomeText.Locator);
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
