package tasks2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Frames2 extends BaseTest {

    @Test
    public void test() {
        // 1) "https://demoqa.com/nestedframes" sayfasına gidilir:
        driver.get("https://demoqa.com/nestedframes");

        // 2) "Nested Frames" başlığı kontrol edilir (<h1>):
        String expectedMainPageHeader = "Nested Frames";
        String actualMainPageHeader = "";
        try {
            actualMainPageHeader = driver.findElement(By.xpath("//h1")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualMainPageHeader, expectedMainPageHeader);

        // 3) "Parent frame" yazısı kontrol edilir (büyük frame içinde):
        driver.switchTo().frame("frame1");
        String expectedParentFrameText = "Parent frame";
        String actualParentFrameText = "";
        try {
            actualParentFrameText = driver.findElement(By.xpath("//body[text()='Parent frame']")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualParentFrameText, expectedParentFrameText);

        // 4) "Child Iframe" yazısı kontrol edilir (büyük frame içindeki frame içinde):
        WebElement childIFrame = driver.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
        driver.switchTo().frame(childIFrame);
        String expectedChildIFrameText = "Child Iframe";
        String actualChildIFrameText = "";
        try {
            actualChildIFrameText = driver.findElement(By.xpath("//p[text()='Child Iframe']")).getText();
        } catch (Exception e) {
        }
        Assert.assertEquals(actualChildIFrameText, expectedChildIFrameText);

        // 5) Sayfada "Sample Nested Iframe page." text'i kontrol edilir:
        driver.switchTo().defaultContent();
        String expectedMainPageText = "Sample Nested Iframe page.";
        String actualMainPageText = "";
        try {
            actualMainPageText = driver.findElement(By.xpath("//div[contains(text(), 'Sample Nested Iframe page.')]")).getText();
        } catch (Exception e) {
        }
        Assert.assertTrue(actualMainPageText.contains(expectedMainPageText));
    }

}