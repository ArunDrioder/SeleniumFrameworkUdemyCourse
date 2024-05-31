package tests;

import TestComponents.BaseTest;

import org.example.Pages.CartPage;
import org.example.Pages.CheckoutPage;
import org.example.Pages.ConfirmationPage;
import org.example.Pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ErrorValidations extends BaseTest
{
    @Test (groups = {"ErrorHandling"})
    public void loginValidation() throws IOException, InterruptedException
    {
        landingPage.loginToApp("Shiju@gmail.com","Saibaba@!234");
        String expectedMessage = "Incorrect email or password.";
        String actualMessage = landingPage.getErrorMessage();
        System.out.println(actualMessage);
        Assert.assertEquals(actualMessage,expectedMessage);
    }

    @Test
    public void productValidation() throws InterruptedException {
        String productName = "ADIDAS ORIGINAL";
        ProductsPage productsPage = landingPage.loginToApp("arunprasadh.s@gmail.com","Arun@!234");
        productsPage.addProductToCart(productName);
        CartPage cartPage =  productsPage.goToCart();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Boolean match = cartPage.verifyProductName("handbag");
        Assert.assertFalse(match);

    }




















}
