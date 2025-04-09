package DemoBlaze.Utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {


    public static WebElement waitForElementToBePresent(WebDriver driver, By Locater) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver1.findElement(Locater));
    }

    public static WebElement waitForElementToBeVisible(WebDriver driver, By Locater) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> {
                    WebElement element = waitForElementToBePresent(driver, Locater);
                    return element.isDisplayed() ? element : null;
                }
        );
    }

    public static WebElement waitForElementToBeClickable(WebDriver driver, By Locater) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> {
                    WebElement element = waitForElementToBeVisible(driver, Locater);
                    return element.isEnabled() ? element : null;
                }
        );
    }
    public static void HandleAlert(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    public static void waitForProductToDisappear(String productName,WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By locator = By.xpath("//tr[@class='success']/td[2][text()='" + productName + "']");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}