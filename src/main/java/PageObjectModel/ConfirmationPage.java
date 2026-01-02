package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends PageObjectModel.AbstractComponent {

    WebDriver driver;
    By confirmationMessage = By.cssSelector(".hero-primary");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        waitForElement(confirmationMessage);
        return driver.findElement(confirmationMessage).getText();
    }
}