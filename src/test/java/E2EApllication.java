import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.foreign.SymbolLookup;
import java.util.List;

public class E2EApllication {

    @Test
    public void run() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        //select tag with dropdown
        //satic dropdown (fixed dropdowns)


        Thread.sleep(2000);
        WebElement staticdropDown = driver.findElement(By.xpath("//select[@id='ctl00_mainContent_DropDownListCurrency']"));
     //   staticdropDown.click();
        Select dropdown = new Select(staticdropDown);
        dropdown.selectByIndex(2);
        System.out.println(dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("USD");
        System.out.println(dropdown.getFirstSelectedOption().getText());


     // updated dropdown
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000);
       /* int i=1;
        while(i<2){

            driver.findElement(By.id("hrefIncAdt")).click();
            i++;
        }*/
        for(int i=1;i<2;i++)
        {
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());


        //dymanic dropdown
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='HYD']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("(//a[@text='Bengaluru (BLR)'])[2]")).click();

        //calender of cuurent date
        //driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();

        //Auto Suggestion Drpdowns
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(1000);
       List<WebElement> Options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
       for (WebElement option : Options) {
           if(option.getText().equalsIgnoreCase("India"))
           {
               option.click();
               break;
           }
       }
        List<WebElement> elements = driver.findElements(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']"));
        WebElement firstElement = elements.get(0); // index starts at 0
        firstElement.click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']")).isSelected());
        //System.out.println(driver.findElement(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']")).isSelected());
         /*driver.findElements(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']"));
        WebElement firstElements = elements.get(0); // index starts at 0
        firstElements.click();

          */
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='ctl00_mainContent_chk_friendsandfamily']")).isSelected());
       // Assert.assertTrue(driver.findElement(By.id("ctl00_mainContent_view_date2")).isEnabled());
        Thread.sleep(2000);
        driver.findElement(By.name("ctl00$mainContent$btn_FindFlights")).click();
        driver.quit();




    }
}
