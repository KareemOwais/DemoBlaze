package Factory;

import org.apache.commons.io.output.BrokenWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

public class WebDriverFactory {

    private static final  ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver not initialized. Call InitDriver() first.");
        }
        return driver.get();
    }
    public static void InitDriver(String Browser) {
        if (driver.get() == null) {
            switch (Browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--window-size=1920,1080");
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":

                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("-headless");
                    options.addArguments("-window-size=1920,1080");
                    driver.set(new FirefoxDriver(options));
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--headless=new");
                    edgeOptions.addArguments("-window-size=1920,1080");
                     driver.set(new EdgeDriver(edgeOptions));
                     break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + Browser);
            }
        }
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
