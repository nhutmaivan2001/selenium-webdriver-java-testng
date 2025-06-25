package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_09_Dropdown {

    WebDriver driver;
    String firstName, lastName, email, company, password, confirmPassword;
    Random rand;

    @BeforeClass
    public void initialBrowser() {
        //handle flodfare
        // 1: tạo 1 profile mới ở chrome và edge
        // 2: Mở app lên và accept Cloudflare
        // 3: Lấy đường dẫn của profile => chrome://version/
        // C:\Users\mvnhu\AppData\Local\Google\Chrome\User Data\Profile 6
        // 4: code

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/mvnhu/AppData/Local/Microsoft/Edge/User Data");
        edgeOptions.addArguments("--profile-directory=Profile 6");
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        rand = new Random();
        firstName ="Mai";
        lastName = "Nhut";
        email = "mvnhut" + rand.nextInt(9999) + "@gmail.com";
        company = "fpt";
        password = "@Maihfu2463";

    }

    @Test
    public void TC_01_Nopcommerce()  {

        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(company);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        driver.findElement(By.xpath("//a[text()='Continue']")).click();


    }

    @Test
    public void TC_02_Role() {
        driver.get("https://www.rode.com/wheretobuy");
        // kiểm tra xem có phải là Multiple Select
        Assert.assertFalse(new Select(driver.findElement(By.cssSelector("select#country"))).isMultiple());
        //
        new Select(driver.findElement(By.cssSelector("select#country"))).selectByVisibleText("Vietnam");
        driver.findElement(By.cssSelector("input#map_search_query")).sendKeys("HO CHI MINH");

        List<WebElement> delears = driver.findElements(By.xpath("//h3[text()='Dealers']//parent::div//h4"));
        Assert.assertEquals(delears.size(), 16);
        System.out.println("Tổng số Delears là:" + delears.size());

        for (WebElement element: delears){
            System.out.println(element.getText());
        }

    }

    @Test
    public void TC_03_Nopcommerce() {

        driver.get("https://demo.nopcommerce.com");
        driver.findElement(By.cssSelector("input#Email")).sendKeys("mvnhut2015@gmail.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("@Maivannhut2001");
        driver.findElement(By.cssSelector("button.button-1.login-button")).click();

}
    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}