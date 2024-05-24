package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AbstractComponent
{
    WebDriver driver;

    public ProductsPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
     WebElement loaderElement;


    By productElement = By.cssSelector(".mb-3");
    By addToCartButton = By.cssSelector(".card-body button:last-of-type");
    By bannerElement = By.cssSelector("#toast-container");

    public List<WebElement> getProductsList()
    {
        waitForElementToAppear(productElement);

        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement singleProduct =   getProductsList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        return singleProduct;


    }

    public void addProductToCart(String productName)
    {
        WebElement singleProduct = getProductByName(productName);
        singleProduct.findElement(addToCartButton).click();
        waitForElementToAppear(bannerElement);
        waitForElementToDisAppear(loaderElement);

    }
}
