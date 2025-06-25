package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_25_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFolderPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
    String hoiAn = "Hoi An.jpg";
    String daNang = "Da Nang.jpg";
    String cauRong = "Cau Rong.jpg";

    String hoiAnPath = uploadFolderPath + hoiAn;
    String daNangPath = uploadFolderPath + daNang;
    String cauRongPath = uploadFolderPath + cauRong;


    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Loading_Icon() {

        // Sử dụng Invisible để wait cho loading icon

        driver.get("https://automationfc.github.io/dynamic-loading/");

        WebElement startButton = explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
        startButton.click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading"))));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Text() throws InterruptedException {

        // Sử dụng Visible để wait cho text "Hello Word"

        driver.get("https://automationfc.github.io/dynamic-loading/");

        WebElement startButton = explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start>button")));
        startButton.click();

        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!")));
    }

    @Test
    public void TC_03_Telerik() throws InterruptedException {

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.contentWrapper")));

        By selectedDateTextBy = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(selectedDateTextBy, "No Selected Dates to display.")));

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));

        WebElement calendar = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='24']")));
        calendar.click();

        By selectedDateText = By.cssSelector("span#ctl00_ContentPlaceholder1_Label1");
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(selectedDateText, "Tuesday, June 24, 2025")));

    }

    @Test
    public void TC_04_uploadFiles_C1_Step1_Completed()  {

        driver.get("https://gofile.io/?t=uploadFiles");

        // chọn các file để upload lên
        By inputBy = By.xpath("//input[@type='file']");
        driver.findElement(inputBy).sendKeys(hoiAnPath + "\n" + daNangPath + "\n" + cauRongPath);

        // đợi cho file load lên server, loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.animate-spin")));

        // Đợi text hiển thị và verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center>h2"), "Upload Complete")));

        //đợi link sẫn sàng click và click vào downloadlink
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.linkSuccessCard"))).click();

        // đợi page load xong, loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading>div")));

        // verrify cái button play và download hiển thị
        List<WebElement> playButtons = driver.findElements(By.cssSelector("button.item_play"));
        for (WebElement playButton : explicitWait.until(ExpectedConditions.visibilityOfAllElements(playButtons))) {
            Assert.assertTrue(playButton.isDisplayed());
        }

        List<WebElement> downloadButtons = driver.findElements(By.cssSelector("button.item_download"));
        for (WebElement playDownload : explicitWait.until(ExpectedConditions.visibilityOfAllElements(playButtons))) {
            Assert.assertTrue(playDownload.isDisplayed());
        }
    }
    @Test


    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}