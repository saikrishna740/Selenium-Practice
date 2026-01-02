import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Claneder {
    @Test
    public void run() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();

        System.out.println(driver.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled());
        driver.quit();

    }

}
