package webdriver;

import com.google.common.base.Function;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_26_Fluent_Wait {
    WebDriver driver;
    WebDriverWait fluentWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Dynamic_Loading_GetText() {

        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(getTextElement(By.cssSelector("div#finish>h4")), "Hello World!");


    }

    @Test
    public void TC_02_Dynamic_Loading_IsDisplay() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start>button")).click();
        Assert.assertTrue(isDisplay(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

    }

    @Test
    public void TC_03_CountDown() throws InterruptedException {
        driver.get("https://automationfc.github.io/fluent-wait/");

        Assert.assertTrue(isSecondMatching(By.cssSelector("div#javascript_countdown_time")));

    }

    public WebElement findElement (By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    public String getTextElement (By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
    }

    public boolean isSecondMatching (By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).getText().endsWith("00");
            }
        });
    }

    public boolean isDisplay (By by){
        FluentWait<WebDriver> driverFluentWait = new FluentWait<>(driver);

        driverFluentWait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }




    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}