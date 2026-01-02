import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Actionclass {

    @Test
    public void run() throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.amazon.in/?&tag=googhydrabk1-21&ref=pd_sl_6o3s351fev_e&adgrpid=150668181581&hvpone=&hvptwo=&hvadid=674842289449&hvpos=&hvnetw=g&hvrand=1800295658679002990&hvqmt=e&hvdev=c&hvdvcmdl=&hvlocint=&hvlocphy=9062135&hvtargid=kwd-300061672064&hydadcr=5621_2359492&gad_source=1");
        Thread.sleep(4000);
        Actions action=new Actions(driver);

        //mouse over on specific element and keys using action class
        //action.moveToElement(driver.findElement(By.cssSelector("//div[@id='nav-link-accountList']//a[contains(@class,'nav-progressive-attribute')]"))).contextClick().build().perform();
        action.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click().keyDown(Keys.SHIFT).sendKeys("sai").doubleClick().build().perform();
        driver.quit();
    }
}
