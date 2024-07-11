package tasks1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Task1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://petstore.octoperf.com/actions/Catalog.action");
            driver.manage().window().maximize();

            driver.findElement(By.name("keyword")).sendKeys("fish");
            driver.findElement(By.name("searchProducts")).click();

            String actualID = driver.findElement(By.xpath("//font[normalize-space()='FI-FW-02']")).getText();
            String expectedID = "FI-FW-02";
            Assert.assertEquals(actualID, expectedID, "IDs are not matching!");
        } finally {
            driver.close();
        }
    }

}


/*
1) Go to "https://petstore.octoperf.com/actions/Catalog.action"
2) Enter "fish" in input box and Click search box.
3) Verify product ID is "FI-FW-02".
*/