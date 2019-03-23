package myprojects.automation.assignment4.tests;

import myprojects.automation.assignment4.BaseTest;
import myprojects.automation.assignment4.model.ProductData;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class CreateProductTest extends BaseTest {
    private ProductData newProduct = ProductData.generate();
    @DataProvider(name = "auth")
    public static Object[][] credentials() {
        return new Object[][] {{ "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw" }};
    }


    @Test(dataProvider = "auth")
    public void createNewProduct(String login, String password) {
        actions.login(login, password);
        actions.selectMenu();
        actions.createProduct(newProduct);
    }

    @Test(dependsOnMethods = "createNewProduct")
    public void checkProduct() {
        actions.checkAddedItem(newProduct);
    }
}