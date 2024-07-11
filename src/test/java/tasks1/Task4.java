package tasks1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task4 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://selenium08.blogspot.com/2019/11/dropdown.html");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void test1() {
        // 2) Get the size of dropdown menu:
        WebElement countryDropdown = driver.findElement(By.name("country"));
        Select select = new Select(countryDropdown);
        List<WebElement> options = select.getOptions();
        System.out.println("options.size() = " + options.size());

        // 3) Get all texts:
        for (WebElement option : options) {
            System.out.println("option.getText() = " + option.getText());
        }
    }

    @Test
    public void test2() throws InterruptedException {
        WebElement monthDropdown = driver.findElement(By.name("Month"));
        Select select = new Select(monthDropdown);

        // 4) Select "March" by value:
        select.selectByValue("Ma");

        Thread.sleep(2000);

        // 5) Select "April" by index:
        select.selectByIndex(4);

        Thread.sleep(2000);

        // 6) Select "October" by text:
        select.selectByVisibleText("October");

        Thread.sleep(2000);

        // 7) Verify: get selected options size = 3
        int selectedOptionsSize = select.getAllSelectedOptions().size();
        Assert.assertEquals(selectedOptionsSize, 3);
    }

}


/*
• "Multi-Select" Task:
  1) Go to "https://selenium08.blogspot.com/2019/11/dropdown.html"
  2) Get the size of dropdown menu.
  3) Get all texts.
  4) Select "March" by value.
  5) Select "April" by index.
  6) Select "October" by text.
  7) Verify: get selected options size = 3
*/