package endToendECommerceAp;

import PageObjectModel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StandaloneTest {

    @Test
    public void placeOrderTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Goto();

        ProductCatalog productCatalog = loginPage.LoginApplication("saikavati197@gmail.com", "@Bunny1436");

        productCatalog.getProductByName("ZARA COAT 3");
        CartPage cartPage = productCatalog.goToCartPage();

        boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

        System.out.println("🎉 Order placed successfully!");
        driver.quit();
    }
}