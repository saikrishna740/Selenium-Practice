import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Ecommerce {
    @Test
    public void run() throws InterruptedException {

        String text = "Sai Krishna";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        String[] listofproducts ={"Cucumber","Apple", "Strawberry"};
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        Thread.sleep(3000);

        //if multiple web elements on page identifying the single web element
        List<WebElement> product=driver.findElements(By.cssSelector("h4.product-name"));

        for(int i=0;i<product.size();i++){
            String[] productName=product.get(i).getText().split("-");
            String fromatedName=productName[0].trim();

            // convert array into array list for easy search
            List listofProductsNeedtoAdd= Arrays.asList(listofproducts);

            if(listofProductsNeedtoAdd.contains(fromatedName))
            {
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                Thread.sleep(2000);

            }

        }
        driver.quit();

    }
}
