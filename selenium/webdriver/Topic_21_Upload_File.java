package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_File {
    WebDriver driver;
    // Khai báo đường dẫn tương đối đến file UploadFiles
    // sử dụng File.separator để tự detect path của các hệ điều hành khác nhau
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String hoiAn = "Hoi An.jpg";
    String daNang = "Da Nang.jpg";
    String cauRong = "Cau Rong.jpg";

    String hoiAnPath = uploadFolderPath + hoiAn;
    String daNangPath = uploadFolderPath + daNang;
    String cauRongPath = uploadFolderPath + cauRong;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01_Single_File() throws InterruptedException {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        Thread.sleep(2000);
        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên
        driver.findElement(inputBy).sendKeys(hoiAnPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(daNangPath);
        Thread.sleep(2000);

        driver.findElement(inputBy).sendKeys(cauRongPath);
        Thread.sleep(2000);

        // Verify file được upload lên

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + daNang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cauRong + "']")).isDisplayed());

        //Click để upload file lên

        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons){
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify file đã được upload thành công

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + daNang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + cauRong + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() throws InterruptedException {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        Thread.sleep(2000);
        By inputBy = By.xpath("//input[@type='file']");

        // Load file lên
        driver.findElement(inputBy).sendKeys(hoiAnPath + "\n" + daNangPath + "\n" + cauRongPath);
        Thread.sleep(2000);

        // Verify file được upload lên

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + daNang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cauRong + "']")).isDisplayed());

        //Click để upload file lên

//        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));
//
//        for (WebElement startButton : startButtons){
//            startButton.click();
//            Thread.sleep(2000);
//        }

        driver.findElement(By.xpath("//span[text()='Start upload']")).click();
        Thread.sleep(2000);

        // Verify file đã được upload thành công

        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + hoiAn + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + daNang + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + cauRong + "']")).isDisplayed());
    }


    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}