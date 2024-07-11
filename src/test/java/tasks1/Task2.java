package tasks1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Task2 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://petstore.octoperf.com/actions/Catalog.action");
            driver.manage().window().maximize();

            // 2) Enter "fish" in input box and Click search box:
            driver.findElement(By.name("keyword")).sendKeys("fish");
            driver.findElement(By.name("searchProducts")).click();

            // 3) Click productID "FI-FW-02" Goldfish:
            driver.findElement(By.xpath("//font[normalize-space()='FI-FW-02']")).click();

            // 4) Select "EST-20" item and Add to Cart:
            driver.findElement(By.xpath("//a[text()='EST-20']")).click();
            driver.findElement(By.xpath("//a[text()='Add to Cart']")).click();

            // 5) Click "Fish" module:
            driver.findElement(By.xpath("//a[@href='/actions/Catalog.action?viewCategory=&categoryId=FISH']")).click();

            // 6) Click productID "FI-SW-01" Angelfish:
            driver.findElement(By.xpath("//a[text()='FI-SW-01']")).click();

            // 7) Select "Large Angelfish" and Add to Cart:
            driver.findElement(By.xpath("//a[text()='EST-1']")).click();
            driver.findElement(By.xpath("//a[text()='Add to Cart']")).click();

            // 8) Verify total cost "$22.00":
            String actualCostStr = driver.findElement(By.xpath("//td[contains(text(), 'Total')]")).getText();
            double actualCost = Double.parseDouble(actualCostStr.substring(actualCostStr.indexOf("$")+1));
            double expectedCost = 22.00;
            Assert.assertEquals(actualCost, expectedCost, "Total costs are not matching!");
        } finally {
            driver.close();
        }
    }

}


/*
1) Go to "https://petstore.octoperf.com/actions/Catalog.action"
2) Enter "fish" in input box and Click search box.
3) Click productID "FI-FW-02" Goldfish.
4) Select "EST-20" item and Add to Cart.
5) Click "Fish" module.
6) Click productID "FI-SW-01" Angelfish.
7) Select "Large Angelfish" and Add to Cart.
8) Verify total cost "$22.00".
*/