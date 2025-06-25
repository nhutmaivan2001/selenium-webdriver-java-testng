package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_WebBrowser_Exercise_I {

    //1 - Set up

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
        if (emailTextbox.isDisplayed()){
            emailTextbox.sendKeys("automation");
            System.out.println("Email textbox is displayed");
        }else {
            System.out.println("Email textbox is not displayed");
        }

        WebElement ageUnder18Radio = driver.findElement(By.xpath("//label[text()=('Under 18')]"));
        if (ageUnder18Radio.isDisplayed()){
            ageUnder18Radio.click();
            System.out.println("Age Under 18 is displayed");
        }else {
            System.out.println("Age Under 18 is not displayed");
        }

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));
        if (emailTextbox.isDisplayed()){
            educationTextbox.sendKeys("automation");
            System.out.println("Education textbox is displayed");
        }else {
            System.out.println("Education textbox is not displayed");
        }

        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
        if (user5Text.isDisplayed()){
            System.out.println("User 5 text is displayed");
        }else {
            System.out.println("User 5 text is not displayed");
        }
    }

    @Test
    public void TC_02_Enabled() {
        // Enable

        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
        if (emailTextbox.isEnabled()){
            System.out.println("Email textbox is enabled");
        }else {
            System.out.println("Email textbox disabled");
        }

        WebElement ageUnder18Radio = driver.findElement(By.xpath("//label[text()=('Under 18')]"));
        if (ageUnder18Radio.isEnabled()){
            System.out.println("Age Under 18 is enabled");
        }else {
            System.out.println("Age Under 18 disabled");
        }

        WebElement educationTextbox = driver.findElement(By.cssSelector("textarea#edu"));
        if (educationTextbox.isEnabled()){
            System.out.println("Education textbox is enabled");
        }else {
            System.out.println("Education textbox disabled");
        }

        WebElement jobRole01Text = driver.findElement(By.cssSelector("select#job1"));
        if (jobRole01Text.isEnabled()){
            System.out.println("Job Role 01 text is enabled");
        }else {
            System.out.println("Job Role 01 text disabled");
        }

        WebElement interestsDevelopmentCheckbox = driver.findElement(By.cssSelector("input#development"));
        if (interestsDevelopmentCheckbox.isEnabled()){
            System.out.println("Interests Development checkbox is enabled");
        }else {
            System.out.println("Interest Development checkbox disabled");
        }

        WebElement slider01 = driver.findElement(By.cssSelector("input#slider-1"));
        if (slider01.isEnabled()){
            System.out.println("Slider 01 is enabled");
        }else {
            System.out.println("Slider 01 disabled");
        }

        //disabled

        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
        if (passwordTextbox.isEnabled()){
            System.out.println("Password textbox is enabled");
        }else {
            System.out.println("Password textbox disabled");
        }

        WebElement ageRadio = driver.findElement(By.cssSelector("input#radio-disabled"));
        if (ageRadio.isEnabled()){
            System.out.println("Age radio is enabled");
        }else {
            System.out.println("Age radio disabled");
        }

        WebElement biographyTextbox = driver.findElement(By.cssSelector("textarea#bio"));
        if (biographyTextbox.isEnabled()){
            System.out.println("Biography textbox is enabled");
        }else {
            System.out.println("Biography textbox disabled");
        }

        WebElement jobRole03Text = driver.findElement(By.cssSelector("select#job3"));
        if (jobRole03Text.isEnabled()){
            System.out.println("Job Role 03 text is enabled");
        }else {
            System.out.println("Job Role 03 text disabled");
        }

        WebElement interestsCheckboxisDisplayed = driver.findElement(By.cssSelector("input#check-disbaled"));
        if (interestsCheckboxisDisplayed.isEnabled()){
            System.out.println("Interests checkbox is enabled");
        }else {
            System.out.println("Interest checkbox disabled");
        }

        WebElement slider02 = driver.findElement(By.cssSelector("input#slider-2"));
        if (slider02.isEnabled()){
            System.out.println("Slider 02 is enabled");
        }else {
            System.out.println("Slider 02 disabled");
        }

    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement under18Radio = driver.findElement(By.cssSelector("input#under_18"));
        under18Radio.click();
        if (under18Radio.isSelected()){
            System.out.println("Under 18 radio is selected");
        }else {
            System.out.println("Under 18 radio is de-selected");
        }

        WebElement javaLanguagesCheckbox = driver.findElement(By.cssSelector("input#java"));
        javaLanguagesCheckbox.click();
        if (javaLanguagesCheckbox.isSelected()){
            System.out.println("Java Languages checkbox is selected");
        }else {
            System.out.println("Java Languages checkbox is de-selected");
        }
        javaLanguagesCheckbox.click();
        if (javaLanguagesCheckbox.isSelected()){
            System.out.println("Java Languages checkbox is selected");
        }else {
            System.out.println("Java Languages checkbox is de-selected");
        }

    }

    @Test
    public void TC_04_MailChimp_Register_Validate() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
        WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
        emailTextbox.sendKeys("mvnhut2001@gmail.com");
        emailTextbox.sendKeys(Keys.TAB);
        Thread.sleep(3000);

        // number
        passwordTextbox.sendKeys("123");
        passwordTextbox.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // lowercase
        passwordTextbox.clear();
        passwordTextbox.sendKeys("dgdf");
        passwordTextbox.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // uppercase
        passwordTextbox.clear();
        passwordTextbox.sendKeys("HGHFG");
        passwordTextbox.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // special character
        passwordTextbox.clear();
        passwordTextbox.sendKeys("*&^%");
        passwordTextbox.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // more than 8 characters
        passwordTextbox.clear();
        passwordTextbox.sendKeys("123456789");
        passwordTextbox.sendKeys(Keys.TAB);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}