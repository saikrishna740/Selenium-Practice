import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Firstclass {

    @Test
    public void run(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/practice-project");
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("sai");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("saikavati197@gmail.com");
        driver.findElement(By.xpath("//button[@id='form-submit']")).click();
        driver.quit();
    }

}
