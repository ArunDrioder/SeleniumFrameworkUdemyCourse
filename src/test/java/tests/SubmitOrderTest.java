package tests;

import TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.example.Pages.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest extends BaseTest
{

    //String productName = ;
    @Test (dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {



        ProductsPage productsPage = landingPage.loginToApp(input.get("email"),input.get("password"));

        productsPage.addProductToCart(input.get("product"));
        CartPage cartPage =  productsPage.goToCart();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean match = cartPage.verifyProductName(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMsg  = confirmationPage.getConfirmationMsg();
        System.out.println(confirmationMsg);
    }

    @Test(dependsOnMethods = {"submitOrder"},dataProvider = "getData")

    public void orderHistory(String email,String password, String productName)
    {
        ProductsPage productsPage = landingPage.loginToApp(email,password);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        OrdersPage ordersPage = productsPage.gotoOrders();
        Assert.assertTrue(ordersPage.verifyOrderDisplayed(productName));
    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        File src = new File(System.getProperty("user.dir") +"//reports//" +testCaseName+".png");
        FileUtils.copyFile(file, src);
        return System.getProperty("user.dir") +"//reports//" +testCaseName+".png";
    }

    @DataProvider
    public Object [][] getData() throws IOException {

       List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
            return new Object[][]  {{data.get(0)},{data.get(1)}};
    }




















}
