package Interactions;
import org.openqa.selenium.*;

import static DemoBlaze.Utils.WaitUtils.waitForElementToBeClickable;

public class Button extends Label {
    // Constructor

    public Button(By Locator , String Description) {
        super(Locator);
        this.Description=Description;
    }
    public Button(By Locator ) {
        super(Locator);
    }

    public void click() {

        try {
            WebElement element = waitForElementToBeClickable(driver, Locator);
            element.click();
            logger.info("Clicked on element: {}" , Description);
        }  catch (ElementClickInterceptedException e) {
            logger.error("Click intercepted: {}" , Description, e);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {}", Description, e);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be clickable: {}", Description, e);
        } catch (Exception e) {
            logger.error("Unexpected error clicking: {}", Description, e);
        }
    }



}