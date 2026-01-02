package PageObjectModel;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInput;

    @FindBy(css = ".action__submit")
    WebElement checkoutBtn;

    By resultsBy = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectCountry(String countryName) {
        // Type the country
        countryInput.sendKeys(countryName.substring(0, 3));

        // Wait for results and select India
        waitForElement(resultsBy);
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][normalize-space()='India']")).click();
    }

    public ConfirmationPage submitOrder() {
        try {
            // ✅ Scroll into view using JavaScript before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

            // ✅ Wait until clickable
            waitForClickable(checkoutBtn);

            // ✅ Click via JavaScript (more reliable if UI overlaps)
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);

        } catch (ElementClickInterceptedException e) {
            System.out.println("⚠️ Click intercepted, retrying via JavaScript...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        }

        return new ConfirmationPage(driver);
    }
}