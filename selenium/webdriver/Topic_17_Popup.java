package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Popup {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01_Ngoaingu_Pixed_Popup_Not_In_Dom() throws InterruptedException {

        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div[role='dialog']")).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div[class='auth-form'] button[type='submit']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("h2#mui-3>button")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(By.cssSelector("div[role='dialog']")).size(), 0);
    }

    @Test
    public void TC_02_Kyna_Fixed_Popup_In_Dom() throws InterruptedException {

        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.right")).isDisplayed());
        driver.findElement(By.cssSelector("input#user-login")).sendKeys("AutomationFC");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki_Pixed_Popup_Not_In_Dom() throws InterruptedException {

        driver.get("https://tiki.vn/");
        Thread.sleep(6000);

//        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
//        alert.accept();

        Assert.assertTrue(driver.findElement(By.cssSelector("div#VIP_BUNDLE")).isDisplayed());
        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModalPortal div[role='dialog']>div")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModalPortal div[role='dialog']>div")).size(), 0);
    }

    @Test
    public void TC_04_Facebook_Pixed_Popup_Not_In_Dom() throws InterruptedException {

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.xpath("//img[@class='fb_logo _agiv img']/parent::div/following-sibling::div/div")).isDisplayed());

    }

    @Test
    public void TC_05_VNK_Edu_Random_Popup_In_Dom() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By markettingPopupBy = By.cssSelector("div.popmake ");

        //Kiểm tra popup có hiển thị hay không, có thì close và action tiếp
        if (!driver.findElements(markettingPopupBy).isEmpty() && driver.findElements(markettingPopupBy).getFirst().isDisplayed()){
            driver.findElement(By.cssSelector("div.popmake button.popmake-close")).click();
            Thread.sleep(2000);
        }

        //action tiếp
        driver.findElement(By.xpath("//ul[@id='mega-menu-primary']//a[text()='Liên hệ']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Liên hệ | VNK EDU");
    }

    @Test
    public void TC_06_De_Hieu_Random_Popup_In_Dom() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        By markettingPopupBy = By.cssSelector("div.modal-content");

        //Kiểm tra popup có hiển thị hay không, có thì close và action tiếp
        if (!driver.findElements(markettingPopupBy).isEmpty() && driver.findElements(markettingPopupBy).getFirst().isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
            Thread.sleep(2000);
        }

        //action tiếp
        driver.findElement(By.xpath("//a[text()=' Đăng nhập']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Đăng nhập");
    }

    @Test
    public void TC_07_Javacodegeeks_Random_Popup_Not_In_Dom() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
       // Thread.sleep(15000);
        By newsletterPopupBy = By.xpath("//div[@data-title='Newsletter-Books Anime Brief' and not(contains(@style, 'display:none'))]");

        //Kiểm tra popup có hiển thị hay không, có thì close và action tiếp
        if (!driver.findElements(newsletterPopupBy).isEmpty() && driver.findElements(newsletterPopupBy).getFirst().isDisplayed()){
            driver.findElement(By.cssSelector("div[data-animation-in='fadeIn'] a[onclick='return lepopup_close();']")).click();
            Thread.sleep(2000);
        }

        //action tiếp
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("api");
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("nav#breadcrumb span[class='current']")).getText(), "Search Results for: api");
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}