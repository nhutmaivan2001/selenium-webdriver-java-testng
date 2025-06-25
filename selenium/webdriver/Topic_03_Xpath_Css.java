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

public class Topic_03_Xpath_Css {

    //1 - Set up

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        //driver.get("https://www.nopcommerce.com/en/demo");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //2 - Action/Excute

    @Test
    public void Register_01_Empty_Data() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtFirstname-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");

        driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");

        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");

        driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");

        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");

        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("dsfdzgfd@");

        driver.findElement(By.id("txtCEmail")).sendKeys("dsfdzgfd@");

        driver.findElement(By.id("txtPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtPhone")).sendKeys("0777566820");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");

        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");

    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtCEmail")).sendKeys("maivan@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtPhone")).sendKeys("0777566820");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtCEmail-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Invalib_Password() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtCEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("123");

        driver.findElement(By.id("txtCPassword")).sendKeys("123");

        driver.findElement(By.id("txtPhone")).sendKeys("0777566820");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");

        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_05_Incorect_Confirm_Password() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtCEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtCPassword")).sendKeys("12354678");

        driver.findElement(By.id("txtPhone")).sendKeys("0777566820");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtCPassword-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_06_Invalib_Phone_Number() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtCEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtPhone")).sendKeys("0777566");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.id("txtFirstname")).sendKeys("Mai Van Nhut");

        driver.findElement(By.id("txtEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtCEmail")).sendKeys("maivannhut@gmail.com");

        driver.findElement(By.id("txtPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtCPassword")).sendKeys("12345678");

        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("123456");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.findElement(By.id("txtPhone-error")).getText();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    //3 - Clean

    @AfterClass
    public void cleanBrowser(){

        driver.quit();
    }
}