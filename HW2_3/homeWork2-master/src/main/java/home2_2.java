import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class home2_2 extends home2_1 {
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

        List<String> proofs = new ArrayList<String>();
        List<String> titles = new ArrayList<String>();
        List<String> curUrls = new ArrayList<String>();

        WebElement menu = ffDriver.findElement(By.className("menu"));
        List<WebElement> links = menu.findElements(By.cssSelector(".menu > li > a"));

        

        for (WebElement link:links) {
            proofs.add(link.getAttribute("href"));
            titles.add(link.getText());
        }

        for (String proof:proofs) {
            ffDriver.get(proof);
            ffDriver.navigate().refresh();
            curUrls.add(ffDriver.getCurrentUrl());
            int index = proofs.indexOf(proof);
            if(curUrls.get(index).equals(proofs.get(index))) {
                System.out.println(titles.get(index));
                System.out.println("OK");
            }
        }

        ffDriver.close();
        }
}
