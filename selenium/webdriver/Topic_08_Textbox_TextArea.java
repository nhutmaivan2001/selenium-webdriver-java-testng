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
import java.util.Random;

public class Topic_08_Textbox_TextArea {

    WebDriver driver;
    Random rand;
    String firstName, lastName,fullName, emailAddress, password, confirmPassword;
    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        rand = new Random();
        firstName = "Mai";
        lastName = "Van Nhut";
        fullName = firstName + " " + lastName;
        emailAddress = "maivannhut" + rand.nextInt(9999) + "@gmail.com";
        password = "123456";
        confirmPassword = "123456";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_techPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(confirmPassword);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you for registering with Main Website Store.");
        String informationTextArea = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div")).getText();
        Assert.assertTrue(informationTextArea.contains(fullName) && informationTextArea.contains(emailAddress));
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();
        driver.findElement(By.id("Quality 1_5")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys("Good\nNice!");
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys("Best Phone");
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys("Nhut");
        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Your review has been accepted for moderation.");


    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}