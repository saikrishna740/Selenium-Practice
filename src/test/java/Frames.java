import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Frames {

    @Test
    public void run() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://jqueryui.com/droppable/");

        // ✅ Switch to frame correctly (no need for // in class name)
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));

        // ✅ Locate elements correctly using id (remove //)
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        // ✅ Perform drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();

        Thread.sleep(2000);
        driver.quit();
    }
}
