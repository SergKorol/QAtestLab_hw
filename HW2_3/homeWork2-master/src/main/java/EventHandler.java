import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;





public class EventHandler implements WebDriverEventListener{


    public void beforeAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertAccept(WebDriver webDriver) {

    }

    public void afterAlertDismiss(WebDriver webDriver) {

    }

    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {

    }

    public void afterNavigateTo(String s, WebDriver webDriver) {

        System.out.println("WebDriver navigated to " + s);
    }

    public void beforeNavigateBack(WebDriver webDriver) {

    }

    public void afterNavigateBack(WebDriver webDriver) {

    }

    public void beforeNavigateForward(WebDriver webDriver) {

    }

    public void afterNavigateForward(WebDriver webDriver) {

    }

    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
       System.out.println("Should be find " + by);
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        if(webElement instanceof WebElement) {
            System.out.println("Find" + webDriver.toString());}
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
       System.out.println("Should click - " + webElement.getTagName());
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        if(webElement instanceof WebElement) {
           System.out.println("Clicked"); }
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    public void beforeScript(String s, WebDriver webDriver) {

    }

    public void afterScript(String s, WebDriver webDriver) {

    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        //System.out.println("Exception occured at " + throwable.getMessage());
    }
}