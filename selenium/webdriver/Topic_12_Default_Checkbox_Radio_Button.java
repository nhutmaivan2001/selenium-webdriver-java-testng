package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Topic_12_Default_Checkbox_Radio_Button {

    WebDriver driver;
   // JavascriptExecutor js;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        //js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void TC_01_Telerik() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");
        By dualZoneAirBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        driver.findElement(dualZoneAirBy).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(dualZoneAirBy).isSelected());
        driver.findElement(dualZoneAirBy).click();
        Thread.sleep(3000);
        Assert.assertFalse(driver.findElement(dualZoneAirBy).isSelected());

        if (!driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }
        Assert.assertTrue(driver.findElement(dualZoneAirBy).isSelected());


        if (driver.findElement(dualZoneAirBy).isSelected()) {
            driver.findElement(dualZoneAirBy).click();
        }
        Assert.assertFalse(driver.findElement(dualZoneAirBy).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By radio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        if (!driver.findElement(radio).isSelected()) {
            driver.findElement(radio).click();
        }
        Assert.assertTrue(driver.findElement(radio).isSelected());
        
        driver.get("https://material.angular.dev/components/radio/examples");
        By radio1 = By.cssSelector("input[value='Summer']");
        if (!driver.findElement(radio1).isSelected()) {
            driver.findElement(radio1).click();
        }
        Assert.assertTrue(driver.findElement(radio1).isSelected());

        driver.get("https://material.angular.dev/components/checkbox/examples");

        By checkkedCheckbox = By.xpath("//label[text()='Checked']//preceding-sibling::div/input");
        By indeterminateCheckbox= By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
        if (!driver.findElement(checkkedCheckbox).isSelected() && !driver.findElement(indeterminateCheckbox).isSelected()) {
            driver.findElement(checkkedCheckbox).click();
            Thread.sleep(3000);
            driver.findElement(indeterminateCheckbox).click();
            Thread.sleep(3000);
        }
        Assert.assertTrue(driver.findElement(checkkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        if (driver.findElement(checkkedCheckbox).isSelected() && driver.findElement(indeterminateCheckbox).isSelected()) {
            driver.findElement(checkkedCheckbox).click();
            Thread.sleep(3000);
            driver.findElement(indeterminateCheckbox).click();
            Thread.sleep(3000);
        }
        Assert.assertFalse(driver.findElement(checkkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/multiple-fields/");
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        List<WebElement> allCheckbox = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        for (WebElement all: allCheckbox){
            if (!all.isSelected()) {
                all.click();
            }
        }
        for (WebElement all: allCheckbox){
            Assert.assertTrue(all.isSelected());
        }

        for (WebElement all: allCheckbox){
            if (all.isSelected()) {
                all.click();
            }
        }
        for (WebElement all: allCheckbox){
            Assert.assertFalse(all.isSelected());
        }

        //selec
        for (WebElement all: allCheckbox){
            if (!all.isSelected() && Objects.equals(all.getAttribute("value"), "Heart Attack")) {
                all.click();
            }
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("input[value='Heart Attack']")).isSelected());
        
    }

    @Test
    public void TC_03_Sort(){
        int[] a = new int[]{6, 2, 3, 4,5,1};
        int min = a[0];
        for(int i=1;i<a.length;i++){
            if(a[i]<min){
                min = a[i];
            }
        }
        System.out.println(min);
    }



    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}