package Interactions;
import Factory.WebDriverFactory;
import org.openqa.selenium.By;

public class Label extends  Element{

    public Label(By Locator) {
        super(Locator);
    }

    public String getText() {
        this.driver = WebDriverFactory.getDriver();
        return driver.findElement(Locator).getText();
    }
}
