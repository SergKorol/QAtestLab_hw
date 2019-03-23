package myprojects.automation.assignment4;
import myprojects.automation.assignment4.utils.logging.CustomReporter;

import myprojects.automation.assignment4.model.ProductData;
import myprojects.automation.assignment4.utils.Properties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    protected GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /**
     * Sing in to Admin Panel.
     * @param login user login
     * @param password user password
     */
    public void login(String login, String password) {
        CustomReporter.log("CHeck user login - " + login);
        driver.navigate().to(Properties.getBaseAdminUrl());
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("main")));

    }

    public void selectMenu() {
        driver.findElement(By.id("subtab-AdminCatalog")).click();
        waitForContentLoad(By.id("page-header-desc-configuration-add")).click();
    }

    /**
     *
     * @param newProduct object of product, contain all properties for filling forms
     */

    public void createProduct(ProductData newProduct) {

        CustomReporter.log(String.format("Product %s, Price: %s, QTY: %s", newProduct.getName(), newProduct.getPrice(), newProduct.getQty()));
        WebElement node;

        node = waitForContentLoad(By.id("form_step1_name_1"));
        node.sendKeys(newProduct.getName());

        node = driver.findElement(By.id("form_step1_qty_0_shortcut"));
        node.sendKeys(Integer.toString(newProduct.getQty()));


        node = waitForContentLoad(By.id("form_step1_price_shortcut"));
        scrollDown(node);
        node.sendKeys(Keys.chord(Keys.CONTROL, "a"), newProduct.getPrice());

        node = driver.findElement(By.className("switch-input "));
        node.click();
        wait.until(ExpectedConditions.textToBe(By.className("growl-message"), "Настройки обновлены."));
        driver.findElement(By.cssSelector(".growl-close")).click();

        node = driver.findElement(By.xpath(".//button[@class='btn btn-primary js-btn-save']"));
        node.click();

        wait.until(ExpectedConditions.textToBe(By.className("growl-message"), "Настройки обновлены."));
        node = waitForClickable(By.cssSelector(".growl-close"));
        node.click();

    }

    public void checkAddedItem(ProductData newProduct) {
        WebElement node;

        driver.navigate().to(myprojects.automation.assignment4.utils.Properties.getBaseUrl());
        waitForContentLoad(By.id("main"));

        node = driver.findElement(By.cssSelector(".all-product-link"));
        scrollDown(node);
        node.click();

        node = waitForContentLoad(By.linkText(newProduct.getName()));
        scrollDown(node);

        node = waitForContentLoad(By.cssSelector("h1[itemprop='name']"));
        CustomReporter.log("Check item name - " + node.getText());
        Assert.assertEquals(node.getText(), node.getText());

        node = driver.findElement(By.cssSelector(".current-price span"));
        CustomReporter.log("check the price - " + node.getAttribute("content"));
        String price = newProduct.getPrice();
        String cost = node.getAttribute("content");
        if(cost.indexOf(".") == -1) {
            price = price.replace('.', ',');
        }
        Assert.assertEquals(node.getAttribute("content"), price);

        node = driver.findElement(By.cssSelector(".product-quantities span"));
        CustomReporter.log("Check items quantity  - " + node.getText());
        Assert.assertEquals(node.getText(), newProduct.getQty() + " Товары");

    }

    /**
     * Waits until page loader disappears from the page
     */
    private WebElement waitForContentLoad(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }
    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }



        private void scrollDown(WebElement elem) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
        }
}
