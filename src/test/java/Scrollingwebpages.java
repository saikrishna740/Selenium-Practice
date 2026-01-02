import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.List;

import static java.lang.reflect.Array.get;

public class Scrollingwebpages {

    @Test
    public void run() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //scroll to page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);

        //scroll to table
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");

        //collecting the number in table
        List<WebElement> values = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        //getting the toatl numbers and calculating the total
        int sum=0;
        for(int i=0;i<values.size();i++){

           sum=sum+ Integer.parseInt(values.get(i).getText());
        }
        System.out.println(sum);
        int total =Integer.parseInt(driver.findElement(By.xpath("//div[@class='totalAmount']")).getText().split(":")[1].trim());
        Assert.assertEquals(sum,total);
        driver.close();


    }
}
