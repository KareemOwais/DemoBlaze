package Interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import static DemoBlaze.Utils.WaitUtils.waitForElementToBeVisible;

public class Textbox extends Element {
    // Constructor
    public Textbox(By Locator , String Description) {
        super(Locator);
        this.Description = Description;
    }
    public Textbox(By Locator) {
        super(Locator);
    }

    public void setText(String text) {
        try {
            WebElement element = waitForElementToBeVisible(driver, Locator);
            element.clear(); 
            element.sendKeys(text);
            logger.info("Set text {} {} {}" , text , "' in element: " , Description);
        } catch (NoSuchElementException e) {
            logger.error("Textbox not found: {}" , Description, e);
        } catch (TimeoutException e) {
            logger.error("Textbox not visible in time: {}" , Description, e);
        } catch (Exception e) {
            logger.error("Unexpected error while setting text in textbox: {}" ,Description, e);
        }
    }

}