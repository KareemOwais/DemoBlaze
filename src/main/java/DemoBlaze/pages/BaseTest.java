package DemoBlaze.pages;

import Factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://demoblaze.com/");
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        logger.info("-----------------  Test STARTED ------------");
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
        logger.info("-----------------  Test ENDED ------------");
    }

}
