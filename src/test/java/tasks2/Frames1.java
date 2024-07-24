package tasks2;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Frames1 extends BaseTest {

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