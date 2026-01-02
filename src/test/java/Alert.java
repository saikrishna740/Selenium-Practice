import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Alert {

    @Test
    public void run() throws InterruptedException {

        String text="Sai Krishna";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']")).sendKeys(text);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Confirm']")).click();
        driver.switchTo().alert().dismiss();
        Thread.sleep(2000);
        getAlert(driver);

    }
    public static void getAlert(WebDriver driver) throws InterruptedException {
        String text="Sai Krishna";
        driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']")).sendKeys(text);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@value='Alert']")).click();
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.quit();
    }
}
