import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class CheckBox {

    @Test
    public void run() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        //CheckBoxes

        List<WebElement> elements = driver.findElements(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']"));
        WebElement firstElement = elements.get(0); // index starts at 0
        firstElement.click();
        System.out.println(driver.findElement(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']")).isSelected());
        List<WebElement> element = driver.findElements(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']"));
        WebElement firstElements = elements.get(0); // index starts at 0
        firstElements.click();
        driver.quit();


    }
}
