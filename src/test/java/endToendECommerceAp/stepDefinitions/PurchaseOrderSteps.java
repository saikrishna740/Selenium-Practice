package endToendECommerceAp.stepDefinitions;



import PageObjectModel.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PurchaseOrderSteps extends BaseTest {

    LoginPage loginPage;
    ProductCatalog productCatalog;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ConfirmationPage confirmationPage;

    @Given("I landed on the Ecommerce page")
    public void i_landed_on_the_ecommerce_page() {
        setUp(); // Initialize WebDriver from BaseTest
        loginPage = new LoginPage(driver);
        loginPage.Goto();
    }

    @Given("Logged in with userName {string} and password {string}")
    public void logged_in_with_userName_and_password(String username, String password) {
        productCatalog = loginPage.LoginApplication(username, password);
    }

    @When("I add product {string} to the cart")
    public void i_add_product_to_cart(String productName) {
        productCatalog.getProductByName(productName);
        cartPage = productCatalog.goToCartPage();
    }

    @And("Checkout {string} and submit the order")
    public void checkout_and_submit_the_order(String productName) {
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match, "Product not found in the cart!");

        checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("India");

        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("Message is displayed on the confirmation page")
    public void message_is_displayed_on_the_confirmation_page() throws InterruptedException {
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
        System.out.println("🎉 Order placed successfully!");

        tearDown();
    }
}

