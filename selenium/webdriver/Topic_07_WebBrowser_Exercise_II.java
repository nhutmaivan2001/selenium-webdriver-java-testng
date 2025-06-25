package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_WebBrowser_Exercise_II {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");


    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("$#123@123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_Password_Less_Than_6_characters() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("mvnhut@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_Login_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("mvnhut@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123234");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}