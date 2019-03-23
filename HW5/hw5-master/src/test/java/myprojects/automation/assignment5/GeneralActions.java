package myprojects.automation.assignment5;
import myprojects.automation.assignment5.utils.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public String title;
    public String price;


    public String titleCart;
    public String priceCart;
    public String qtyCart;

    public String orderMessage;
    public String orderedTitle;
    public String orderedPrice;
    public String orderedQty;

    public String remainder;


    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    /** Check site version, PC or mobile
     *
     * @param url main site link
     */

    public void checkSiteVersion(String url){
        driver.navigate().to(url);
    }

    /**
     * Open some random product from list
     */


    public void openRandomProduct() {
        WebElement node;

        node = driver.findElement(By.cssSelector(".all-product-link"));
        scrollDown(node);
        node.click();

        waitForContentLoad(By.tagName("main"));
        List<WebElement> products = driver.findElements(By.tagName("article"));
        Random random = (new Random());
        int index = random.nextInt(products.size()) + 1;

        System.out.println("Producst index: " + index);
        node = products.get(index).findElement(By.tagName("a"));
        node.click();

        waitForContentLoad(By.id("product"));
    }

    /**
     * Save info product finded
     */

    public void saveInfo(){
        WebElement node;

        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/h1"));
        title = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[1]/div[1]/div/span"));
        price = node.getText();


    }


    public void addToCart(){
        WebElement node;

        node = driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div[1]/div[2]/button"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/a"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[2]/ul/li/div/div[2]/div[1]/a"));
        titleCart = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[2]/ul/li/div/div[2]/div[2]/span"));
        priceCart = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div[1]/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input"));
        qtyCart = node.getAttribute("value");

        }

        public void createOrder() {
        WebElement node;

        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div/div[2]/div/a"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"customer-form\"]/section/div[2]/div[1]/input"));
        node.sendKeys("John");
        node = driver.findElement(By.xpath("//*[@id=\"customer-form\"]/section/div[3]/div[1]/input"));
        node.sendKeys("Doe");
        node = driver.findElement(By.xpath("//*[@id=\"customer-form\"]/section/div[4]/div[1]/input"));
        node.sendKeys("test@text.net");

        node = driver.findElement(By.xpath("//*[@id=\"customer-form\"]/footer/button"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[5]/div[1]/input"));
        node.sendKeys("ул. Тестировочная, кв. undefined ");
        node = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[7]/div[1]/input"));
        node.sendKeys("55555");
        node = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/section/div[8]/div[1]/input"));
        node.sendKeys("Киев");

        node = driver.findElement(By.xpath("//*[@id=\"delivery-address\"]/div/footer/button"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button"));
        node.click();

        node = driver.findElement(By.id("payment-option-2"));
        node.click();
        node = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        node.click();

        node = driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button"));
        node.click();
        }

        public void getCheckoutInfo() {
        WebElement node;

        node = driver.findElement(By.xpath("//*[@id=\"content-hook_order_confirmation\"]/div/div/div/h3"));
        orderMessage = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"order-items\"]/div/div/div[2]/span"));
        orderedTitle = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"order-items\"]/div/div/div[3]/div/div[1]"));
        orderedPrice = node.getText();
        node = driver.findElement(By.xpath("//*[@id=\"order-items\"]/div/div/div[3]/div/div[2]"));
        orderedQty = node.getText();
        }

        public void checkStock() {
        WebElement node;

        node = driver.findElement(By.xpath("//*[@id=\"search_widget\"]/form/input[2]"));
        node.sendKeys(titleCart);
        node = wait.until(ExpectedConditions.elementToBeClickable(By.id("ui-id-1")));
        node.click();
        node = driver.findElement(By.xpath("//*[@id=\"main\"]/div[1]/div[2]/div[2]/div[3]/ul/li[2]/a"));
        node.click();
        node = driver.findElement(By.xpath("//*[@id=\"product-details\"]/div[3]/span"));
        remainder = node.getText();
        }



    /**
     * Open site
     */

    public void open() {
        driver.navigate().to(Properties.getBaseUrl());
        waitForContentLoad(By.id("main"));
    }
    /**
     * Waiting for locator loading
     * @param locator searched locator
     */
    public WebElement waitForContentLoad(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void scrollDown(WebElement elem) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elem);
    }
}