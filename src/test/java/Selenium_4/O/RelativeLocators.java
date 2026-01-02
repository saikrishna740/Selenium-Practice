package Selenium_4.O;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {

    @Test
    public void relativeLocators() throws InterruptedException {

        //new introduce selenium 4.O version locators

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));

        // relative locator syntax and above
        WebElement label = driver.findElement(with(By.tagName("label")).above(nameEditBox));

        System.out.println(label.getText());

        WebElement dob =driver.findElement(By.cssSelector("[for='dateofBirth']"));
        // relative locator syntax and below
        driver.findElement(with(By.tagName("input")).below(dob)).click();
        Thread.sleep(4000);

        // relative locator syntax and toLeftof
        WebElement icecream =driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(icecream)).click();

        // relative locator syntax and toRightof
        WebElement StuntCheck=driver.findElement(By.id("inlineRadio1"));
        driver.findElement(with(By.tagName("label")).toRightOf(StuntCheck)).click();
        Thread.sleep(3000);


        driver.quit();
    }
}

