import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class Windowhandles {

    @Test
    public void run() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.findElement(By.xpath("//a[@class='blinkingText']")).click();

        Set<String> window = driver.getWindowHandles();
        Iterator<String> it = window.iterator();
        String parent = it.next();
        String child = it.next();

        // Switch to child window
        driver.switchTo().window(child);
        String text = driver.findElement(By.xpath("//div[@class='col-md-8']/p[@class='im-para red']")).getText();
        String emailId = text.split("at")[1].trim().split(" ")[0];
        System.out.println("Extracted Email: " + emailId);

        // Switch back to parent window
        driver.switchTo().window(parent);
        Thread.sleep(3000);

        
        driver.findElement(By.id("username")).sendKeys(emailId);

        Thread.sleep(2000);
        driver.quit();
    }
}
