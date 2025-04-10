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
        int attempts = 0;
        while (attempts < 2) {
            try {
                WebElement element = waitForElementToBeClickable(getDriver(), Locator);
                element.click();
                break;
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                attempts++;
                System.out.println("⚠️ Retrying click due to stale element: " + Locator);
            }
        }
    }


}