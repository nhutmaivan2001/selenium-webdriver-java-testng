package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_14_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    String user = "admin";
    String password = "admin";

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        //js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        //switch qua
        // Alert alert = driver.switchTo().alert();
        // vừa wait vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        //switch qua
        // Alert alert = driver.switchTo().alert();
        // vừa wait vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_03_Confirm_Alert() {
        driver.get("https://tiki.vn/");
        //driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        //switch qua
        // Alert alert = driver.switchTo().alert();
        // vừa wait vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        //Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();
        //Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        //switch qua
        // Alert alert = driver.switchTo().alert();
        // vừa wait vừa switch qua
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Mai Nhut");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: Mai Nhut");
    }

    @Test
    public void TC_04_Authentication_URL(){
        driver.get("http://"+ user + ":" + password + "@" +"the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");

    }

    @Test
    public void TC_05_Authentication_Navigation(){
        driver.get("http://the-internet.herokuapp.com/");
        String basicAuth = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        Assert.assertNotNull(basicAuth);
        driver.get(getAuthenticationURL(basicAuth, user, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#content p")).getText(), "Congratulations! You must have the proper credentials.");
    }
    public String getAuthenticationURL(String link, String user, String password){
        String[] linkArray = link.split("//");
        return linkArray[0] + "//" + user + ":" + password + "@" + linkArray[1];
    }


//    @AfterClass
//    public void cleanBrowser(){
//        driver.quit();
//    }
}