package tests;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import org.example.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest extends BaseTest
{

    //String productName = ;
    @Test (dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {



        ProductsPage productsPage = landingPage.loginToApp(email,password);

        productsPage.addProductToCart(productName);
        CartPage cartPage =  productsPage.goToCart();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean match = cartPage.verifyProductName(productName);
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

    @DataProvider
    public Object [][] getData()
    {
            return new Object[][]  {{"arunprasadh.s@gmail.com","Arun@!234","ADIDAS ORIGINAL"},{"shinku@gmail.com","aarun","IPHONE 13 PRO"}};
    }




















}
