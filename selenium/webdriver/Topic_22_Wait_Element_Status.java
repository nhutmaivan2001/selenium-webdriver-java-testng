package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    @Test
    public void TC_01_Visible() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // element có trên UI và có trong DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));
    }

    @Test
    public void TC_02_Invisible() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // element có trên UI và có trong DOM
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // element không có trên UI và có trong DOM
        driver.findElement(By.cssSelector("input#password_step_input")).sendKeys("nhut");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='New password']")));

        // click link quay lại trang login
        driver.findElement(By.cssSelector("a[aria-label='Already have an account?']")).click();

        // element không có trên UI và không có trong DOM
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='New password']")));
    }

    @Test
    public void TC_03_Presence() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // element có trên UI và có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // sendkeys password để ẩn placeholder
        driver.findElement(By.cssSelector("input#password_step_input")).sendKeys("nhut");

        // element không có trên UI và có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='New password']")));
    }

    @Test
    public void TC_04_Staleness() {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        // element có trên UI và có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email__']")));

        // Element đã từng có trong DOM
        WebElement placeholderPasswordBy = driver.findElement(By.xpath("//div[text()='New password']"));

        // click link quay lại trang login
        driver.findElement(By.cssSelector("a[aria-label='Already have an account?']")).click();

        explicitWait.until(ExpectedConditions.stalenessOf(placeholderPasswordBy));
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}