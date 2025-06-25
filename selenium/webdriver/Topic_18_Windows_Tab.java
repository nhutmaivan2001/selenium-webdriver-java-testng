package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Windows_Tab {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01() throws InterruptedException {

        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(2000);

        //ID của windows hiện tại
        String githubWindowID = driver.getWindowHandle();

        // Title của windowns hiện tại
        String githubTitle = driver.getTitle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(2000);

        //swith qua google
        switchToWindowById(githubWindowID);
        Thread.sleep(2000);

        //kiểm tra title = google
        Assert.assertEquals(driver.getTitle(), "Google");

        // switch về github
        switchToWindowByTitle(githubTitle);
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");


        //click và switch qua Facebook
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Facebook – log in or sign up");
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        // switch về github
        switchToWindowByTitle(githubTitle);
        Thread.sleep(2000);

        //click và switch qua Tiki
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        // đóng hết tất cả các window trừ github
        closeAllWindowWithoutParentWindow(githubWindowID);
    }

    @Test
    public void TC_02_Techpanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2//following-sibling::div//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/catalog/product_compare/index/");

        // đóng
        driver.close();
        Thread.sleep(2000);

        switchToWindowByTitle("Mobile");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Dictionary_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");
        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Login");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input[placeholder='Email *']~span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("input[placeholder='Password *']~span")).getText(), "This field is required");

        driver.close();
        Thread.sleep(2000);

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input[aria-label='Tìm kiếm']")).sendKeys("automation");
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#cald4-1~div.pos-header span.headword >span")).getText(), "automation");
    }

    @Test
    public void TC_04_Course_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String courseshomeID = driver.getWindowHandle();

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(4000);

        //switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        switchToWindowById(courseshomeID);
        Thread.sleep(4000);

        Assert.assertTrue(driver.findElement(By.xpath("//img[@id='prompt-logo-center']/parent::header/parent::div")).isDisplayed());
        driver.close();
        Thread.sleep(3000);

        switchToWindowByTitle("DCE Course Search");
        Thread.sleep(3000);

        // verify màn hình authentication was not successful
        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());
        driver.findElement(By.cssSelector("button.sam-wait__close")).click();
        driver.close();
    }

    // switch to window by id ( chỉ đúng với 2 window/tab)
    public  void switchToWindowById(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if (!runWindow.equals(parentID)){
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    // switch to window by title
    public void switchToWindowByTitle(String expectedTitle) throws InterruptedException {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            Thread.sleep(2000);
            String currentWindows = driver.getTitle();
            Assert.assertNotNull(currentWindows);
            if (currentWindows.equals(expectedTitle))
                break;
        }
    }

    // close all window without parent window
    public void closeAllWindowWithoutParentWindow(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows){
            if (!runWindow.equals(parentID)){
                //switch qua window hiện tại
                driver.switchTo().window(runWindow);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        // kiểm tra còn duy nhất 1 window
        driver.getWindowHandles();
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}