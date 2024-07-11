package tasks1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Task5 {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://demoqa.com/select-menu");
            driver.manage().window().maximize();

            // 2) Select "Old Style Select Menu" using the element id:
            // 3) Print all the options texts of the dropdown:
            WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
            Select select = new Select(dropdown);
            for (WebElement option : select.getOptions()) {
                System.out.println("option.getText() = " + option.getText());
            }

            // 4) Select "Purple" using the index and get text:
            select.selectByIndex(4);
            System.out.println("Selected option should be \"Purple\" == " + select.getFirstSelectedOption().getText());

            Thread.sleep(2000);

            // 5) Select "Magenta" using visible text and get text:
            select.selectByVisibleText("Magenta");
            System.out.println("Selected option should be \"Magenta\" == " + select.getFirstSelectedOption().getText());

            Thread.sleep(2000);

            // 6) Select an option using value of "White" and get text:
            select.selectByValue("6");
            System.out.println("Selected option should be \"White\" == " + select.getFirstSelectedOption().getText());
        } finally {
            driver.close();
        }
    }

}


/*
• "Select" Task:
  1) Open "https://demoqa.com/select-menu"
  2) Select "Old Style Select Menu" using the element id.
  3) Print all the options texts of the dropdown.
  4) Select "Purple" using the index and get text.
  5) Select "Magenta" using visible text and get text.
  6) Select an option using value of "White" and get text.
*/