package DemoBlaze.pages;
import Factory.WebDriverFactory;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected HomePage homePage;
    protected ProductPage productPage;
    protected CartPage cartPage;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        WebDriverFactory.InitDriver(browser);
        String threadName = Thread.currentThread().getName().replaceAll("[^a-zA-Z0-9-_]", "_");
        ThreadContext.put("threadName", threadName);
        WebDriverFactory.getDriver().get("https://demoblaze.com/");
        System.out.println("Title: " + WebDriverFactory.getDriver().getTitle());
        homePage = new HomePage();
        productPage = new ProductPage();
        cartPage = new CartPage();
    }

    @AfterMethod
    public void tearDown() {
        WebDriverFactory.closeDriver();
    }

}
