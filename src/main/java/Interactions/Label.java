package Interactions;
import org.openqa.selenium.By;

public class Label extends  Element{

    public Label(By Locator) {
        super(Locator);
    }

    public String getText() {
        return driver.findElement(Locator).getText();
    }
}
