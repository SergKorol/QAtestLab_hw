import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class home2_1 {
    public static void main(String[] args) {
        WebDriver ffDriver = initFirfoxDriver();
        ffDriver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/index.php?controller=AdminLogin&token=d251f363cceb5a849cb7330938c64dea");
        WebElement email = ffDriver.findElement(By.id("email"));
        WebElement password = ffDriver.findElement(By.id("passwd"));
        WebElement enter = ffDriver.findElement(By.className("ladda-label"));
        email.sendKeys("webinar.test@gmail.com");
        password.sendKeys("Xcg7299bnSmMuRLp9ITw");
        enter.click();

        pause(3000);

        WebElement avatar = ffDriver.findElement(By.id("header_employee_box"));
        avatar.click();
        WebElement logout = ffDriver.findElement(By.id("header_logout"));
        logout.click();
        ffDriver.close();

    }
    public static WebDriver initFirfoxDriver() {
        System.setProperty("webdriver.gecko.driver", home2_1.class.getResource("geckodriver").getPath());
        return new FirefoxDriver();
    }

    public static void pause(int time) {
        try {
            java.lang.Thread.sleep(time);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
