package webdriver;

import org.bouncycastle.math.ec.custom.djb.Curve25519;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Actions {
    WebDriver driver;
    Actions action;
    //lấy tên hệ điều hành
    String osName = System.getProperty("os.name");
    Keys keys;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        action = new Actions(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // nếu tên hệ điều hành bắt đầu bằng Windows thì trả Keys.CONTROL ngược lại thì trả về Keys.COMMAND
        keys = osName.startsWith("Windows") ? Keys.CONTROL : Keys.COMMAND;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Myntra() throws InterruptedException {
        driver.get("http://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        Thread.sleep(3000);
        // click() của webelement
        // driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();

        // click() của action
        action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());

    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        //Thread.sleep(3000);
        action.click(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#ves-breadcrumbs li>strong")).getText(), "SÁCH TIẾNG VIỆT");
        Assert.assertTrue(driver.findElement(By.cssSelector("div#ves-breadcrumbs li>strong")).isDisplayed());
    }

    @Test
    public void TC_04_Click_And_Hold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
        Assert.assertEquals(allNumber.size(),20);

        //click vào 1 và giữ, kéo tới 4, sau đó thả chuột và thực thi câu lệnh
        action.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(3)).release().perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),4);
    }

    @Test
    public void TC_05_Click_And_Click_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("ol#selectable li"));
        Assert.assertEquals(allNumber.size(),20);

        //nhấn và giữ phím control
        action.keyDown(keys).perform();

        //click vào 1, 5, 8, 11, 17, 20
        action.click(allNumber.get(0))
                .click(allNumber.get(7))
                .click(allNumber.get(10))
                .click(allNumber.get(17))
                .click(allNumber.get(19))
                .perform();

        // nhả phím control
        action.keyUp(keys).perform();

        //verify
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),5);
    }

    @Test
    public void TC_06_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        Thread.sleep(5000);
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        action.scrollToElement(doubleClickButton).perform();  // nếu dùng firefox thì phải dùng JS để chạy do elememt không nằm trong view port
        action.doubleClick(doubleClickButton).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_Right_Click() throws InterruptedException {

        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        By quitMenu = By.xpath("//span[text()='Quit']");
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(quitMenu).isDisplayed());

        action.moveToElement(driver.findElement(quitMenu)).perform();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover")).isDisplayed());

        action.click(driver.findElement(quitMenu)).perform();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(quitMenu).isDisplayed());

    }

    @Test
    public void TC_08_Drap_And_Drop_HTML5() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
    }
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}