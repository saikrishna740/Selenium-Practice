package PageObjectModel;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RegistrationPage extends AbstractComponent {

    private final WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(25);

    // Use By locators instead of PageFactory WebElement proxies
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By userEmail = By.id("userEmail");
    private final By userMobile = By.id("userMobile");
    private final By occupationSelect = By.cssSelector("select[formcontrolname='occupation']");
    private final By genderMaleRadio = By.cssSelector("input[type='radio'][value='Male']");
    private final By genderFemaleRadio = By.cssSelector("input[type='radio'][value='Female']");
    private final By userPassword = By.id("userPassword");
    private final By confirmPassword = By.id("confirmPassword");
    private final By ageCheckbox = By.cssSelector("input[formcontrolname='required']");
    private final By registerBtn = By.id("login");
    private final By toastMessage = By.cssSelector("#toast-container");
    private final By spinner = By.cssSelector(".ngx-spinner-overlay");

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Robust navigation with diagnostics - uses By-based waits
    public RegistrationPage goTo() {
        driver.get("https://rahulshettyacademy.com/client");
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        By[] candidateFirstNameBy = new By[]{
                firstName,
                By.cssSelector("input[placeholder*='First']"),
                By.cssSelector("input[name*='first']"),
                By.cssSelector("input[id*='first']"),
                By.cssSelector("form input")
        };

        try {
            waitUntilDocumentReady();

            // quick presence checks
            for (By b : candidateFirstNameBy) {
                if (isElementPresent(b)) {
                    wait.until(ExpectedConditions.presenceOfElementLocated(b));
                    return this;
                }
            }

            // try clicking known signup triggers
            String[] triggerSelectors = new String[]{
                    "a[href*='signup']",
                    "a[href*='#/signup']",
                    "button[routerlink*='signup']",
                    "button[id*='signup']",
                    "a[routerlink*='/signup']"
            };

            for (String sel : triggerSelectors) {
                List<WebElement> els = driver.findElements(By.cssSelector(sel));
                if (!els.isEmpty()) {
                    try {
                        WebElement el = els.get(0);
                        wait.until(ExpectedConditions.elementToBeClickable(el));
                        el.click();
                    } catch (Exception e) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", els.get(0));
                    }
                    for (By b : candidateFirstNameBy) {
                        if (waitForPresence(b, 5)) {
                            return this;
                        }
                    }
                }
            }

            // check if content inside iframe
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            for (WebElement frame : iframes) {
                try {
                    driver.switchTo().frame(frame);
                    for (By b : candidateFirstNameBy) {
                        if (isElementPresent(b)) {
                            wait.until(ExpectedConditions.presenceOfElementLocated(b));
                            driver.switchTo().defaultContent();
                            return this;
                        }
                    }
                } catch (Exception ignored) {
                } finally {
                    driver.switchTo().defaultContent();
                }
            }

            // fallback to hash route
            driver.get("https://rahulshettyacademy.com/client/#/signup");
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(firstName),
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder*='First']"))
            ));
            return this;

        } catch (Exception e) {
            try {
                saveDebugSnapshot("registration_goto_error");
            } catch (Exception ignored) {}
            throw new RuntimeException("RegistrationPage.goTo() failed to locate registration form", e);
        }
    }

    private boolean waitForPresence(By by, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isElementPresent(By by) {
        try {
            return !driver.findElements(by).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    private void waitUntilDocumentReady() {
        WebDriverWait w = new WebDriverWait(driver, TIMEOUT);
        w.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    private void saveDebugSnapshot(String prefix) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path dir = Path.of("target", "debug_registration");
        Files.createDirectories(dir);

        Path srcHtml = dir.resolve(prefix + "_" + timestamp + ".html");
        Files.writeString(srcHtml, driver.getPageSource());

        if (driver instanceof TakesScreenshot) {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = dir.resolve(prefix + "_" + timestamp + ".png");
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    // Helper to return a WebElement after waiting for visibility
    private WebElement getVisibleElement(By by) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    private void waitForVisibility(By by) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void waitForClickable(By by) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(by));
    }

    // Action methods now use By-based waits + driver.findElement
    public void enterFirstName(String fn) {
        WebElement el = getVisibleElement(firstName);
        el.clear();
        el.sendKeys(fn);
    }

    public void enterLastName(String ln) {
        WebElement el = getVisibleElement(lastName);
        el.clear();
        el.sendKeys(ln == null ? "" : ln);
    }

    public void enterEmail(String email) {
        WebElement el = getVisibleElement(userEmail);
        el.clear();
        el.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        WebElement el = getVisibleElement(userMobile);
        el.clear();
        el.sendKeys(mobile == null ? "9999999999" : mobile);
    }

    public void selectOccupationByVisibleText(String occupationText) {
        waitForVisibility(occupationSelect);
        new Select(driver.findElement(occupationSelect)).selectByVisibleText(occupationText);
    }

    public void selectGender(String gender) {
        By by = "female".equalsIgnoreCase(gender) ? genderFemaleRadio : genderMaleRadio;
        WebElement el = getVisibleElement(by);
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    public void enterPassword(String pwd) {
        WebElement el = getVisibleElement(userPassword);
        el.clear();
        el.sendKeys(pwd);
    }

    public void enterConfirmPassword(String pwd) {
        WebElement el = getVisibleElement(confirmPassword);
        el.clear();
        el.sendKeys(pwd);
    }

    public void setAgeConfirmation(boolean shouldBeChecked) {
        WebElement el = getVisibleElement(ageCheckbox);
        if (el.isSelected() != shouldBeChecked) {
            try {
                el.click();
            } catch (ElementClickInterceptedException e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
            }
        }
    }

    public void submitRegistration() {
        waitForVisibility(registerBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(registerBtn));
        waitForClickable(registerBtn);
        try {
            driver.findElement(registerBtn).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(registerBtn));
        }
        waitForVisibility(toastMessage);
        waitForElementToDisappear(spinner);
    }

    public void register(String firstNameValue, String email, String password, String confirmPassword) {
        enterFirstName(firstNameValue);
        enterLastName("");
        enterEmail(email);
        enterMobile("9999999999");
        selectOccupationByVisibleText("Engineer");
        selectGender("Male");
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        setAgeConfirmation(true);
        submitRegistration();
    }

    public String getToastText() {
        waitForVisibility(toastMessage);
        return driver.findElement(toastMessage).getText();
    }

    // keep AbstractComponent signature compatibility (if required)
    public void waitForElement(WebElement element) {
        // no-op: kept to avoid breaking code that expects this signature,
        // but avoid using it. Prefer By-based waits in this class.
    }

    // implement waitForElementToDisappear used above (delegates to AbstractComponent if available)
    public void waitForElementToDisappear(By by) {
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception ignored) {}
    }
}
