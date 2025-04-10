package DemoBlaze.pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import Interactions.Label;
import org.openqa.selenium.By;

import static DemoBlaze.Utils.WaitUtils.HandleAlert;
import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class ProductPage {

    private Button AddToCartButton = new Button(By.xpath("//a[text()='Add to cart']"));
    private Label WelcomeText = new Label(By.xpath("//a[contains(text(), 'Welcome')]"));

    public void clickAddToCart() {
        //waitForElementToBeVisible(WebDriverFactory.getDriver(),WelcomeText.Locator);
        AddToCartButton.click();
        HandleAlert(WebDriverFactory.getDriver());
    }

}

