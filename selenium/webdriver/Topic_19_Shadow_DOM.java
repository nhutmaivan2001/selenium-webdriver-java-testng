package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01_Shadow_DOM() throws InterruptedException {

        driver.get("https://automationfc.github.io/shadow-dom/");
        Thread.sleep(2000);

        // Verify các text (some text, nested text) hiển thị và checkbox chưa được select

        // Element cha chứa shadow root
        WebElement firstShadowHost = driver.findElement(By.cssSelector("div#shadow_host"));
        // đến shadow root thành công
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        //từ shadow root, tìm các element trong shadow root
        Assert.assertTrue(firstShadowRoot.findElement(By.cssSelector("span.info")).isDisplayed());
        Thread.sleep(5000);

        // Từ shadow cha đi đến shadow con
        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));
        // từ shadow con, tìm các element cần action
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();
        Assert.assertTrue(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content")).isDisplayed());

        // verify checkbox chưa được select từ shadow cha
        Assert.assertFalse(firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());
        firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());
    }

    @Test
    public void TC02_Book_Pwakit() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);

        WebElement firstShadowHost = driver.findElement(By.xpath("//book-app[@apptitle='BOOKS']"));
        SearchContext firstShadowRoot = firstShadowHost.getShadowRoot();

        firstShadowRoot.findElement(By.cssSelector("#input")).sendKeys("Harry Potter");
        Thread.sleep(3000);
        firstShadowRoot.findElement(By.cssSelector("#input")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement secondShadowHost = firstShadowRoot.findElement(By.cssSelector("book-explore[class='_page']"));
        SearchContext secondShadowRoot = secondShadowHost.getShadowRoot();

        List<WebElement> thirdShadowHostList = secondShadowRoot.findElements(By.cssSelector("ul[class='books']>li>book-item"));

        for (WebElement thirdShadowHost : thirdShadowHostList) {
            SearchContext thirdShadowRoot = thirdShadowHost.getShadowRoot();
            System.out.println(thirdShadowRoot.findElement(By.cssSelector("div[class='info'] h2[class='title']")).getText());
        }
    }



    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}