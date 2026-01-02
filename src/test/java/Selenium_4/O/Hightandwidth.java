package Selenium_4.O;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Hightandwidth {

    @Test
    public void TakinfWebelementScreanShot() throws InterruptedException, IOException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement userfield=driver.findElement(By.cssSelector("[name='name']"));
        userfield.sendKeys("Sai krishna");

        //taking the screanshot on webelements
        File file=userfield.getScreenshotAs(OutputType.FILE);
        //and saving the screanshot
        FileUtils.copyFile(file, new File("Sai.png"));
        Thread.sleep(2000);


        //this will print width hight od webElements
        System.out.println(userfield.getRect().getDimension().getHeight());
        System.out.println(userfield.getRect().getDimension().getWidth());
        driver.quit();
    }
}
