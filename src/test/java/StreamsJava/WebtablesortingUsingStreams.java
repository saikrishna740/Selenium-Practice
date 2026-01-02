package StreamsJava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WebtablesortingUsingStreams {

    WebDriver driver;

    @Test
    public void run() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");

        driver.findElement(By.xpath("//tr//th[1]")).click();
        Thread.sleep(2000);

        List<WebElement> elementList = driver.findElements(By.xpath("//tr//td[1]"));

        // Capture the text of all elements into original list
        List<String> originalList = elementList.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        // Sort the list
        List<String> sortedList = originalList.stream()
                .sorted()
                .collect(Collectors.toList());

        // Compare both lists
        Assert.assertTrue(originalList.equals(sortedList), "Table is not sorted!");

        // Find price for 'Beans'
        List<String> price = elementList.stream()
                .filter(s -> s.getText().contains("Beans"))
                .map(WebtablesortingUsingStreams::getVeggies)  // static method reference
                .collect(Collectors.toList());

        price.forEach(System.out::println);
    }

    private static String getVeggies(WebElement s) {
        return s.findElement(By.xpath("following-sibling::td[1]")).getText();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
