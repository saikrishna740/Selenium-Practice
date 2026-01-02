package PageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    public ProductCatalog LoginApplication(String email, String pass) {
        userEmail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
        return new ProductCatalog(driver);
    }

    public void Goto() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}