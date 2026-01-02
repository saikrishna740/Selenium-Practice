package PageObjectModel;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;

    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductDisplay(String productName) {
        return cartProducts.stream()
                .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        // ✅ Wait for spinner to disappear before clicking
        waitForElementToDisappear(spinner);

        // ✅ Scroll into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

        // ✅ Try normal click, fallback to JS click if intercepted
        try {
            waitForClickable(checkoutBtn);
            checkoutBtn.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutBtn);
        }

        return new CheckoutPage(driver);
    }
}