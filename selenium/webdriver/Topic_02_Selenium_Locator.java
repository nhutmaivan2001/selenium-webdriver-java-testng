package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {

    //1 - Set up

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://www.nopcommerce.com/en/demo");
    }

    //2 - Action/Excute

    @Test
    public void TC_01_Id() {
    }

    @Test
    public void TC_02_Class() {
        // Giá trị trong class không có khoản trắng thì lấy toàn bộ
        // Có khoảng trằng thì lấy phần nào là duy nhất
    }

    @Test
    public void TC_03_Name() {
    }

    @Test
    public void TC_04_LinkText() {

        // Chỉ làm việc với element là link và có text
        // Có thẻ a và có thuộc tính href
        // Phải lấy toàn bộ text, không chừa cái nào hết (Tuyệt đối)
    }

    @Test
    public void TC_05_Partial_Link_Text() {

        // Chỉ làm việc với element là link và có text
        // Có thể lấy toàn bộ text hoặc 1 phần ( Tương đối)
    }

    @Test
    public void TC_06_Tagname() {
    }

    @Test
    public void TC_07_Css() {
        // Có thể cover 6 loại ở trên
    }

    @Test
    public void TC_08_Xpath() {
        // Có thể cover tất cả 7 loại ở trên
    }

    @Test
    public void TC_09_Relative_Locator() {
        // Đây là loại locator mới xuất hiện trên phiên bản Selenium 4
    }

    //3 - Clean

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}