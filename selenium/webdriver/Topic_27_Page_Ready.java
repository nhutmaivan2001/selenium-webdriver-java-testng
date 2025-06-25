package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_27_Page_Ready {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Dynamic_Loading_GetText() {

        driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.email")));

        driver.findElement(By.cssSelector("input.email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input.password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        // đợi cho trang dashboard loading xong
        Assert.assertTrue(isPageLoadedSuccess());

        // logout về trang đăng kí
        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        // đợi cho page login hiển thị
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.login-page"))).isDisplayed());

    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}