package Interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class Textbox extends Element {
    // Constructor
    public Textbox(By Locator) {
        super(Locator);
    }

    public void setText(String text) {
        waitForElementToBeVisible(getDriver(), Locator);
        WebElement element = getDriver().findElement(Locator);
        element.sendKeys(text);
    }
}