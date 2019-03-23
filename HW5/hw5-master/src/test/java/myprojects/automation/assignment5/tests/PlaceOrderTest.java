package myprojects.automation.assignment5.tests;
import myprojects.automation.assignment5.BaseTest;
import myprojects.automation.assignment5.utils.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PlaceOrderTest extends BaseTest {


    @Test
    public void checkSiteVersion() {
        actions.checkSiteVersion(Properties.getBaseUrl());
        if(isMobileTesting){
            System.out.println("test using mobile browser");
        }else {
            System.out.println("test using browser");
        }
    }

    @Test
    public void createNewOrder() {

        //open shop

        actions.open();

        // open random product

        actions.openRandomProduct();

        // save product parameters

        actions.saveInfo();

        // add product to Cart and validate product information in the Cart

        actions.addToCart();
        Assert.assertTrue(actions.title.contains(actions.titleCart));
        Assert.assertTrue(actions.price.contains(actions.priceCart));
        Assert.assertTrue(actions.qtyCart.contains("1"));

        // proceed to order creation, fill required information
        actions.createOrder();

        // place new order and validate order summary

        actions.getCheckoutInfo();
        Assert.assertTrue(actions.orderMessage.contains("ВАШ ЗАКАЗ ПОДТВЕРЖДЁН"));
        Assert.assertTrue(actions.orderedTitle.contains(actions.titleCart));
        Assert.assertTrue(actions.orderedPrice.contains(actions.priceCart));
        Assert.assertTrue(actions.orderedQty.contains("1"));

        // check updated In Stock value

        actions.checkStock();
        Assert.assertTrue(actions.remainder.contains("299"));
    }

}
