package Interactions;
import Factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public abstract class Element {
    public By Locator ;
    // Constructor
    protected Element(By Locator) {
        this.Locator = Locator;
    }
    public WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }

}