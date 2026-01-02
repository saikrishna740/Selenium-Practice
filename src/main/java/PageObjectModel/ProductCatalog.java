package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends PageObjectModel.AbstractComponent {

    WebDriver driver;

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductByName(String productName) {
        waitForElement(productsBy);
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equals(productName))
                .findFirst()
                .orElse(null);

        if (prod != null) {
            prod.findElement(addToCart).click();
            waitForElement(toastMessage);
            waitForElementToDisappear(spinner);
        }

        return prod;
    }

    public CartPage goToCartPage() {
        WebElement cartBtn = driver.findElement(By.cssSelector("[routerlink*='cart']"));
        waitForClickable(cartBtn);
        cartBtn.click();
        return new CartPage(driver);
    }
}