package tasks2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Exercise extends BaseTest {

    @Test
    public void test() {
        // 1)  Tarayıcı açılır (https://katalon-demo-cura.herokuapp.com):
        driver.get("https://katalon-demo-cura.herokuapp.com");

        // 2) "Make Appointment" butonuna tıklanır:
        driver.findElement(By.id("btn-make-appointment")).click();

        // 3) Kullanıcı adı ve şifre girilir (kullanıcı adı ve şifre "Demo account" alanından "get()" metodu kullanılarak alınıp girilir):
        String username = driver.findElement(By.xpath("//input[@value='John Doe']")).getAttribute("value");
        String password = driver.findElement(By.xpath("//input[@value='ThisIsNotAPassword']")).getAttribute("value");
        driver.findElement(By.id("txt-username")).sendKeys(username);
        driver.findElement(By.id("txt-password")).sendKeys(password);

        // 4) "Login" butonuna tıklanır:
        driver.findElement(By.id("btn-login")).click();

        // 5) "Şifrenizi değiştirin" popup'ı içerisindeki "Tamam" butonuna tıklanır:
        try {
            driver.switchTo().alert().accept();
        } catch (Exception e) {
        }

        // 6) "Facility -> Honkong" seçilir:
        WebElement dropdown = driver.findElement(By.id("combo_facility"));
        Select select = new Select(dropdown);
        select.selectByValue("Hongkong CURA Healthcare Center");

        // 7) "Apply for hospital readmission" checkbox'ı seçilir:
        WebElement checkbox = driver.findElement(By.id("chk_hospotal_readmission"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // 8) "Healthcare Program -> Medicare" radiobutton'ı seçilir:
        driver.findElement(By.id("radio_program_medicare")).click();

        // 9) "Visit Date (Required)" alanına tarih girilir:
        driver.findElement(By.id("txt_visit_date")).sendKeys("30/07/2024");

        // 10) "Comment" girilir:
        driver.findElement(By.id("txt_comment")).sendKeys("New Comment!");

        // 11) "Book Appointment" butonuna tıklanır:
        driver.findElement(By.id("btn-book-appointment")).click();

        // 12) "Appointment Confirmation" yazısı kontrol edilir:
        String expectedConfirmationMsg = "Appointment Confirmation";
        String actualConfirmationMsg = "";
        try {
            actualConfirmationMsg = driver.findElement(By.xpath("//h2")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualConfirmationMsg, expectedConfirmationMsg);

        // 13) Sağ üst köşedeki üç çizgi olan menü butonuna tıklanır:
        driver.findElement(By.id("menu-toggle")).click();

        // 14) "Logout" butonuna tıklanır:
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // 15) Url kontrol edilir (https://katalon-demo-cura.herokuapp.com/):
        String expectedUrl = "https://katalon-demo-cura.herokuapp.com/";
        String actualUrl = "";
        try {
            actualUrl = driver.getCurrentUrl();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualUrl, expectedUrl);

        // 16) "We Care About Your Health" yazısı kontrol edilir:
        String expectedHeader = "We Care About Your Health";
        String actualHeader = "";
        try {
            actualHeader = driver.findElement(By.xpath("//h3")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualHeader, expectedHeader);
    }

}