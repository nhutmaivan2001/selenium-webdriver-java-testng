package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class Topic_13_Custom_Checkbox_Radio_Button {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        By accountRadio= By.cssSelector("input#id_new_user");
        By acceptCheckbox= By.cssSelector("input#id_accept_tos");

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(accountRadio));
        Assert.assertTrue(driver.findElement(accountRadio).isSelected());
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600)");

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(acceptCheckbox));
        Assert.assertTrue(driver.findElement(acceptCheckbox).isSelected());

    }

    @Test
    public void TC_02_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canThoCheckbox= By.cssSelector("div[aria-label='Cần Thơ']");

       // if(driver.findElement(canThoCheckbox).getAttribute("aria-checked").equals("false"))
        if (Objects.equals(driver.findElement(canThoCheckbox).getAttribute("aria-checked"), "false")) {
            driver.findElement(canThoCheckbox).click();
        }

        Assert.assertEquals(driver.findElement(canThoCheckbox).getAttribute("aria-checked"),"true");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}