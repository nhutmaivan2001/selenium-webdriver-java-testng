package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_10_Custom_Dropdown {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_JQuery() throws InterruptedException {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInCustomDropdown("span#speed-button","li.ui-menu-item", "Slower");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");

        selectItemInCustomDropdown("span#speed-button","li.ui-menu-item", "Medium");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Medium");

        selectItemInCustomDropdown("span#speed-button","li.ui-menu-item", "Fast");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Fast");
    }
    @Test
    public void TC_02_ReactJS() throws InterruptedException {

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInCustomDropdown("div.dropdown","div.visible.menu>div", "Justen Kitsune");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Justen Kitsune");

    }
    @Test
    public void TC_03_VueJS() throws InterruptedException {

        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInCustomDropdown("li.dropdown-toggle","ul.dropdown-menu>li", "Second Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

    }
    @Test
    public void TC_04_EditableDropdown() throws InterruptedException {

        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        senkeysItemInEditableDropdown("input.search","div.visible.menu", "Albania");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Albania");

        senkeysItemInEditableDropdown("input.search","div.visible.menu", "Afghanistan");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
    }

    private void selectItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {

        // 1: chờ dropdown có thể thao tác lên được (Clickable)
        // 2: click vào element để nó xổ ra cái dropdown
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss))).click();
        Thread.sleep(2000);

        // 3: chờ cho tất cả các item được load ra(presence)
        // 4: tìm cái item nào đúng với mong đợi
        List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for (WebElement item : allItem){
            if (item.getText().equals(textItem)){
                item.click();
                // 5: click lên item đó
                break;
            }
        }

    }

    private  void senkeysItemInEditableDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {

        // 1: chờ dropdown có thể thao tác lên được (senkeys)
        // 2: click vào element để nó xổ ra cái dropdown
        WebElement textboxDropdown = explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(parentCss)));
        textboxDropdown.clear();
        textboxDropdown.sendKeys(textItem);
        Thread.sleep(2000);

        // 3: chờ cho tất cả các item được load ra(presence)
        // 4: tìm cái item nào đúng với mong đợi
        List<WebElement> allItem = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        for (WebElement item : allItem){
            if (item.getText().equals(textItem)){
                // 5: click lên item đó
                item.click();
                break;
            }
        }


    }

    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }
}