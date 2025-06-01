package Interactions;
import Factory.WebDriverFactory;
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
            this.driver = WebDriverFactory.getDriver();
            WebElement element = waitForElementToBeClickable(driver, Locator);
            element.click();
            logger.info("Clicked on element: {}" , Description);
        }  catch (ElementClickInterceptedException e) {
            logger.error("Click intercepted: {}" , Description, e);
        } catch (NoSuchElementException e) {
            logger.error("Element not found: {}", Description, e);
        } catch (TimeoutException e) {
            logger.error("Timeout waiting for element to be clickable: {}", Description, e);
        }catch (StaleElementReferenceException e){
            logger.error("Stale element reference: {}", Description, e);
            try {
                WebElement element = waitForElementToBeClickable(driver, Locator);
                element.click();
                logger.info("Retried and clicked on element: {}", Description);
            } catch (Exception retryException) {
                logger.error("Retry failed for clicking: {}", Description, retryException);
            }
        }
        catch (Exception e) {
            logger.error("Unexpected error clicking: {}", Description, e);
        }
    }



}