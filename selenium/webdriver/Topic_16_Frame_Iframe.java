package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Frame_Iframe {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01_Frame() throws InterruptedException {

        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        Thread.sleep(2000);

        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
        driver.findElement(By.cssSelector("input.form-control.text-muted")).sendKeys("MaiVanNhut");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        Thread.sleep(5000);

        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("a#loginBtn")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.error-msg ")).getText(), "Customer ID/IPIN (Password) is invalid. Please try again.");
    }

    @Test
    public void TC_02_Iframe_ToiDiCodeDao() throws InterruptedException {

        driver.get("https://toidicodedao.com/");
        Thread.sleep(2000);

        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page>span>iframe")));
        Thread.sleep(2000);

        WebElement followerText = driver.findElement(By.xpath("//a[text()='Tôi đi code dạo']//parent::div//following-sibling::div"));
        actions.scrollToElement(followerText).perform();
        Thread.sleep(2000);
        Assert.assertEquals(followerText.getText(), "399,991 followers");
    }

    @Test
    public void TC_03_Formsite_Iframe() throws InterruptedException {

        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div[aria-label='Cookie Consent Banner'] svg[role='img']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div#imageTemplateContainer img")).click();
        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Freshman");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("West Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("a.fs-btn.fs-btn--transparent-kashmir ")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button#login")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}