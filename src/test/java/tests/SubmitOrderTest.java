package tests;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest extends BaseTest
{

    String productName = "ADIDAS ORIGINAL";
    @Test
    public void submitOrder() throws IOException, InterruptedException {



        ProductsPage productsPage = landingPage.loginToApp("arunprasadh.s@gmail.com","Arun@!234");

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

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistory()
    {
        ProductsPage productsPage = landingPage.loginToApp("arunprasadh.s@gmail.com","Arun@!234");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        OrdersPage ordersPage = productsPage.gotoOrders();
        Assert.assertTrue(ordersPage.verifyOrderDisplayed(productName));


    }




















}
