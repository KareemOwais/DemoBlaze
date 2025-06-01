package Interactions;
import Factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class Element {
    public By Locator ;
    protected static final Logger logger = LoggerFactory.getLogger(Element.class);
    String Description ;
    protected WebDriver driver;
    // Constructor
    protected Element(By Locator) {
        this.Locator = Locator;

    }

}