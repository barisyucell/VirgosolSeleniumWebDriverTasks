package tasks2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowHandles {

    WebDriver driver;
    JavascriptExecutor jse;

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
        // 1) "https://demoqa.com/browser-windows" sayfasına gidilir:
        driver.get("https://demoqa.com/browser-windows");

        jse = (JavascriptExecutor) driver;

        // 2) "Browser Windows" başlığı kontrol edilir (<h1>):
        String expectedMainPageHeader = "Browser Windows";
        String actualMainPageHeader = "";
        try {
            actualMainPageHeader = driver.findElement(By.xpath("//h1")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualMainPageHeader, expectedMainPageHeader);

        // 3) "New Tab" butonuna tıklanır:
        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        jse.executeScript("arguments[0].click();", newTabButton);
        /*
        -> Burada bazen ilgili "New Tab" butonuna "driver.findElement(locator).click()" ile tıklanamıyor ve "ElementClickInterceptedException" hatası fırlatılıyor.
           Böyle durumlarda "JavascriptExecutor" interface'ine ait olan "executeScript()" metodunu kullanarak elemente tıklama işlemi yapabiliriz.
        */

        // 4) Açılan sayfanın Url'i kontrol edilir (https://demoqa.com/sample):
        Set<String> windowHandlesSet = driver.getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(windowHandlesSet);
        String mainPageWindowHandle = windowHandlesList.get(0);
        String newTabWindowHandle = windowHandlesList.get(1);
        /*
        = 2. YÖNTEM =
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[index]);
        */

        String expectedUrl = "https://demoqa.com/sample";
        String actualUrl = "";
        try {
            actualUrl = driver.switchTo().window(newTabWindowHandle).getCurrentUrl();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualUrl, expectedUrl);

        // 5) Açılan sayfadaki “This is a sample page” yazısı kontrol edilir:
        String expectedNewTabHeader = "This is a sample page";
        String actualNewTabHeader = "";
        try {
            actualNewTabHeader = driver.findElement(By.xpath("//h1")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualNewTabHeader, expectedNewTabHeader);

        // 6) Ana sayfaya geçilip "New Window" butonuna tıklanır:
        driver.switchTo().window(mainPageWindowHandle);
        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        jse.executeScript("arguments[0].click();", newWindowButton);

        // 7) Açılan penceredeki "This is a sample page" yazısı kontrol edilir:
        windowHandlesSet = driver.getWindowHandles();
        windowHandlesList = new ArrayList<>(windowHandlesSet);
        String newWindowHandle = windowHandlesList.get(2);
        driver.switchTo().window(newWindowHandle);

        String expectedNewWindowHeader = "This is a sample page";
        String actualNewWindowHeader = "";
        try {
            actualNewWindowHeader = driver.findElement(By.xpath("//h1")).getText();
        } catch (Exception e) {

        }
        Assert.assertEquals(actualNewWindowHeader, expectedNewWindowHeader);

        // 8) Açılan pencere kapatılır:
        driver.close();
    }

}


/*
1) "https://demoqa.com/browser-windows" sayfasına gidilir.
2) "Browser Windows" başlığı kontrol edilir (<h1>).
3) "New Tab" butonuna tıklanır.
4) Açılan sayfanın Url'i kontrol edilir (https://demoqa.com/sample)
5) Açılan sayfadaki “This is a sample page” yazısı kontrol edilir.
6) Ana sayfaya geçilip "New Window" butonuna tıklanır.
7) Açılan penceredeki "This is a sample page" yazısı kontrol edilir.
8) Açılan pencere kapatılır.
*/