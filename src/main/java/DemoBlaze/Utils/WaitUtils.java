package DemoBlaze.Utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {


    public static WebElement waitForElementToBePresent(WebDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver1 -> driver1.findElement(Locator));
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver1 -> {
                    WebElement element = waitForElementToBePresent(driver, Locator);
                    return element.isDisplayed() ? element : null;
                }
        );
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By Locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver1 -> {
                    WebElement element = waitForElementToBeVisible(driver, Locator);
                    return element.isEnabled() ? element : null;
                }
        );
    }
    public static void HandleAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    public static void waitForProductToDisappear(String productName,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        By locator = By.xpath("//tr[@class='success']/td[2][text()='" + productName + "']");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}