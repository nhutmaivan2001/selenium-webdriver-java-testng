package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class Topic_11_Button {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        By loginButton = By.cssSelector("button.fhs-btn-login");
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        // cách 1: viết kiểu tường minh
//        String loginBackgroundColor = driver.findElement((loginButton)).getCssValue("background-color");
//        Color loginColor = Color.fromString(loginBackgroundColor);
//        System.out.println(loginColor);
//        Assert.assertEquals(loginColor.asHex().toUpperCase(), "#000000");

        // cách 2, viết ngắn gọn
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),"#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("mvnhut2001@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("1234567");

        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement((loginButton)).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");

    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}