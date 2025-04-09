package Interactions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static DemoBlaze.Utils.WaitUtils.waitForElementToBeClickable;

public class Button extends Element {
    // Constructor
    public Button(By Locator) {
        super(Locator);
    }

    public void click() {
        waitForElementToBeClickable(getDriver() , Locator);
        WebElement element = getDriver().findElement(Locator);
        element.click();
    }
}