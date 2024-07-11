package tasks1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task3 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }

    @Test
    public void test1() {
        driver.get("https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html");

        WebElement redCheckBox = driver.findElement(By.xpath("//input[@value='red']"));
        WebElement greenCheckBox = driver.findElement(By.xpath("//input[@value='green']"));
        WebElement orangeCheckBox = driver.findElement(By.xpath("//input[@value='orange']"));
        WebElement blueCheckBox = driver.findElement(By.xpath("//input[@value='blue']"));

        // 2) Click red and green checkboxes:
        redCheckBox.click();
        greenCheckBox.click();

        // 3) Verify red is selected, orange is not selected:
        Assert.assertTrue(redCheckBox.isSelected() && !orangeCheckBox.isSelected());

        // 4) Verify blue is enabled, green is selected:
        Assert.assertTrue(blueCheckBox.isEnabled() && greenCheckBox.isSelected());
    }

    @Test
    public void test2() {
        driver.get("https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html");

        WebElement browserIE = driver.findElement(By.xpath("//input[@value='IE']"));
        WebElement browserMozilla = driver.findElement(By.xpath("//input[@value='Mozilla']"));
        WebElement browserOpera = driver.findElement(By.xpath("//input[@value='Opera']"));

        // 2) Click IE:
        browserIE.click();

        // 3) Verify IE is selected, Opera is not selected:
        Assert.assertTrue(browserIE.isSelected() && !browserOpera.isSelected(), "Only one checkbox can be selected!");

        // 4) Click Mozilla:
        browserMozilla.click();

        // 5) Verify Mozilla is selected, IE is not selected:
        Assert.assertTrue(browserMozilla.isSelected() && !browserIE.isSelected(), "Only one checkbox can be selected!");
    }

}


/*
• HW1:
  1) Go to "https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html"
  2) Click red and green checkboxes.
  3) Verify red is selected, orange is not selected.
  4) Verify blue is enabled, green is selected.

• HW2:
  1) Go to "https://selenium08.blogspot.com/2019/07/check-box-and-radio-buttons.html"
  2) Click IE.
  3) Verify IE is selected, Opera is not selected.
  4) Click Mozilla.
  5) Verify Mozilla is selected, IE is not selected.
*/