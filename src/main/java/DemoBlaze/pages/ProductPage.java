package DemoBlaze.pages;

import Factory.WebDriverFactory;
import Interactions.Button;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static DemoBlaze.Utils.WaitUtils.HandleAlert;
import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class ProductPage {

    private Button AddToCartButton = new Button(By.xpath("//a[text()='Add to cart']"),"Add to cart button");
    public void clickAddToCart() {

        AddToCartButton.click();
        HandleAlert(WebDriverFactory.getDriver());
    }

}

