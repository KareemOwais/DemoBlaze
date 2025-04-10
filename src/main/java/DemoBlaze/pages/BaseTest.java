package DemoBlaze.pages;

import Factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com/");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }

}
