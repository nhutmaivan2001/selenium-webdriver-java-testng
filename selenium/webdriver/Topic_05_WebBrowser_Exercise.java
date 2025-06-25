package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_WebBrowser_Exercise {

    //1 - Set up

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Verify_URL() {

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

    }

    @Test
    public void TC_02_Verifu_Title() {

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_03_Navigation_Function() {

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void TC_04_Get_Page_Source_Code() {

        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }

    //3 - Clean

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}