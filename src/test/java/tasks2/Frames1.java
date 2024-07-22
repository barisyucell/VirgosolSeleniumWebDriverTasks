package tasks2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Frames1 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void test() {
        // 1) "https://demoqa.com/frames" sayfasına gidilir:
        driver.get("https://demoqa.com/frames");

        // 2) Sayfadaki "Frames" yazısı kontrol edilir (<h1>):
        String expectedMainPageHeader = "Frames";
        String actualMainPageHeader = "";
        try {
            actualMainPageHeader = driver.findElement(By.xpath("//h1[text()='Frames']")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualMainPageHeader, expectedMainPageHeader);

        // 3) "This is a sample page" yazısı kontrol edilir (büyük frame içinde):
        driver.switchTo().frame("frame1");
        String expectedBigFrameHeader = "This is a sample page";
        String actualBigFrameHeader = "";
        try {
            actualBigFrameHeader = driver.findElement(By.id("sampleHeading")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualBigFrameHeader, expectedBigFrameHeader);

        // 4) "This is a sample page" yazısı kontrol edilir (küçük frame içinde):
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame2");
        String expectedSmallFrameHeader = "This is a sample page";
        String actualSmallFrameHeader = "";
        try {
            actualSmallFrameHeader = driver.findElement(By.id("sampleHeading")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualSmallFrameHeader, expectedSmallFrameHeader);

        // 5) Sayfada "Sample Iframe page There are 2 Iframes in this page" text'i kontrol edilir:
        driver.switchTo().defaultContent();
        String expectedMainPageText = "Sample Iframe page There are 2 Iframes in this page";
        String actualMainPageText = "";
        try {
            actualMainPageText = driver.findElement(By.xpath("//div[contains(text(), 'Sample')]")).getText();
        } catch (Exception e) {

        }
        Assert.assertTrue(actualMainPageText.contains(expectedMainPageText));
    }

}


/*
1) "https://demoqa.com/frames" sayfasına gidilir.
2) Sayfadaki "Frames" yazısı kontrol edilir (<h1>).
3) "This is a sample page" yazısı kontrol edilir (büyük frame içinde).
4) "This is a sample page" yazısı kontrol edilir (küçük frame içinde).
5) Sayfada "Sample Iframe page There are 2 Iframes in this page" text'i kontrol edilir.
*/