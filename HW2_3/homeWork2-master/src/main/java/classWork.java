import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class classWork {
    public static void main(String[] args) {

        WebDriver driver = initFirfoxDriver();
        driver.get("https://google.com.ua");
        WebElement search = driver.findElement(By.id("lst-ib"));
        search.sendKeys("selenium");
        WebElement button = driver.findElement(By.name("btnK"));
        button.click();
        List<WebElement> list = driver.findElements(By.className("g"));
        System.out.println(list.size());
        driver.quit();
    }
    private static WebDriver initFirfoxDriver() {
        System.setProperty("webdriver.gecko.driver", classWork.class.getResource("geckodriver").getPath());
        return new FirefoxDriver();
    }
}
