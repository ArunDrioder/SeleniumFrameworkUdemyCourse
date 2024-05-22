import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.Pages.LandingPage;
import org.example.Pages.ProductsPage;
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

public class StandAloneTest
{



    public static void main(String[] args)
    {

        String productName = "ADIDAS ORIGINAL";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginToApp("arunprasadh.s@gmail.com","Arun@!234");


        ProductsPage productsPage = new ProductsPage(driver);
        List<WebElement> productLists =  productsPage.getProductsList();




      WebElement singleProduct =   productLists.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);



      singleProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        System.out.println("Waiting for Banner");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        System.out.println("Banner Shown & Hidden, now showing animation");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        System.out.println("animation also disappeared");

        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        System.out.println("Cart Button Clicked!!!.....");

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

       Boolean match =  cartProducts.stream().anyMatch(nameOfTheProduct->nameOfTheProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);

        driver.findElement(By.cssSelector(".totalRow button")).click();

      Actions actions = new Actions(driver);
      actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
      driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

      driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));












    }
}
