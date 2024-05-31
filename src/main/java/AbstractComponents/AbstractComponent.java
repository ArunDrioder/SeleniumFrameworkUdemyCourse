package AbstractComponents;

import org.example.Pages.CartPage;
import org.example.Pages.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent
{
    WebDriver driver;


    public AbstractComponent(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeaderBtn;
    @FindBy(css = "[routerlink*='myorders']")
    WebElement ordersHeaderBtn;


    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitFoWebElementToAppear(WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisAppear(WebElement webElement) throws InterruptedException {
        Thread.sleep(2000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public CartPage goToCart()
    {
        cartHeaderBtn.click();

        CartPage cartPage = new CartPage(driver);
        return cartPage;

    }
    public OrdersPage gotoOrders()
    {

        ordersHeaderBtn.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }

}
