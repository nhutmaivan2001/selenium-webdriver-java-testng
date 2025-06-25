package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Static_Wait {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Dont_Set_Timeouts() {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // lỗi tại dòng số 30, throw: NoSuchElementException, thời gian chạy testcase hơn 1s tí
    }

    @Test
    public void TC_02_Less_Than_Timeouts() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(4000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // lỗi tại dòng số 43, throw: NoSuchElementException, thời gian chạy testcase hơn 4s tí
    }

    @Test
    public void TC_03_Equal_Timeouts() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // pass, thời gian chạy testcase nhiều hơn 5s tí
    }

    @Test
    public void TC_04_More_Than_Timeouts() throws InterruptedException {

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();
        Thread.sleep(10000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish>h4")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
        // pass, thời gian chạy testcase nhiều hơn 10s tí
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}