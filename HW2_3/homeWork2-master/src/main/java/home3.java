import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class home3 extends home2_1 {
    public static void main(String[] args) {
        WebDriver driver = initFirfoxDriver();
        EventFiringWebDriver ffDriver = new EventFiringWebDriver(driver);
        EventHandler handler = new EventHandler();
        Frame frame = new Frame();
        ffDriver.register(handler);

        ffDriver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=AdminLogin&token=d251f363cceb5a849cb7330938c64dea");
        WebElement email = ffDriver.findElement(By.id("email"));
        WebElement password = ffDriver.findElement(By.id("passwd"));
        WebElement enter = ffDriver.findElement(By.className("ladda-label"));
        email.sendKeys("webinar.test@gmail.com");
        password.sendKeys("Xcg7299bnSmMuRLp9ITw");
        enter.click();

        WebElement menu = (new WebDriverWait(ffDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("menu")));

        WebElement catalog = menu.findElement(By.id("subtab-AdminCatalog"));
        Actions act1 = new Actions(ffDriver);
        act1.moveToElement(catalog);
        act1.perform();

        ffDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement category = menu.findElement(By.linkText("категории"));
        Actions act2 = new Actions(ffDriver);
        act2.moveToElement(category);
        act2.click();
        act2.perform();


        WebElement addCat = (new WebDriverWait(ffDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-desc-category-new_category")));
        addCat.click();

        WebElement title = ffDriver.findElement(By.id("name_1"));
        title.sendKeys("Testers");
        ffDriver.unregister(handler);
        ffDriver.register(frame);
        WebElement iframe = ffDriver.findElement(By.id("description_1_ifr"));

        ffDriver.switchTo().frame(iframe);
        WebElement editor = ffDriver.findElement(By.cssSelector("#tinymce"));
        editor.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        ffDriver.switchTo().defaultContent();
        ffDriver.unregister(frame);
        ffDriver.register(handler);
        WebElement save = ffDriver.findElement(By.id("category_form_submit_btn"));
        save.click();
        WebElement filter = ffDriver.findElement(By.name("categoryFilter_name"));
        filter.sendKeys("Testers");
        WebElement submitFilter = ffDriver.findElement(By.id("submitFilterButtoncategory"));
        submitFilter.click();
        ffDriver.close();

    }





}
