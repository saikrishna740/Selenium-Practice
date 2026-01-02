import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.commons.io.FileUtils;  // ✅ Import this
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    @Test
    public void run() throws InterruptedException, IOException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        // Take screenshot
        File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save to destination
        FileUtils.copyFile(scr, new File("C:\\Users\\saika\\screenshot.png"));

        driver.quit();
    }
}
