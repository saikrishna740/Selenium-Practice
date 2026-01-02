package Selenium_4.O;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class InvokingmultipleWindiwTab {
    @Test
    public void windowTab() throws InterruptedException {

        //new introduce selenium 4.O version locators

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.switchTo().newWindow(WindowType.TAB);

        Set<String> handles =driver.getWindowHandles();
        Iterator<String> it=handles.iterator();
        String parentWindow=it.next();
        String childWindow=it.next();
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
        Thread.sleep(3000);
        WebElement couseName=driver.findElement(By.xpath("//a[text()='Core java for Automation Testers + Interview Programs']"));
        System.out.println(couseName.getText());
        driver.switchTo().window(parentWindow);
        driver.findElement(By.cssSelector("[name='name']"));
        Thread.sleep(2000);
        driver.quit();
    }
}
