import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class Calenderspecificdates {

    @Test
    public void run() {
        String month="2";
        String day="4";
        String year="2027";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.xpath("//div[@class='react-date-picker__inputGroup']")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label__labelText")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label__labelText")).click();
        driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
        driver.findElements(By.cssSelector(".react-calendar__tile.react-calendar__year-view__months__month")).get(Integer.parseInt(month)-1).click();
        driver.findElement(By.xpath("//abbr[text()='"+day+"']")).click();

        List<WebElement> actualDates=driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));


        for(int i=0;i<actualDates.size();i++){
           System.out.print(actualDates.get(i).getAttribute("value"));
        }
        driver.quit();

    }
}
