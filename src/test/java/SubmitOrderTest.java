import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest
{
    public static void main(String[] args) throws InterruptedException
    {

        String productName = "ADIDAS ORIGINAL";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductsPage productsPage = landingPage.loginToApp("arunprasadh.s@gmail.com","Arun@!234");
        List<WebElement> productLists =  productsPage.getProductsList();
        productsPage.addProductToCart(productName);


        CartPage cartPage =  productsPage.goToCart();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        Boolean match = cartPage.verifyProductName(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmaionMsg  = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmaionMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER"));
        driver.close();
















    }
}
