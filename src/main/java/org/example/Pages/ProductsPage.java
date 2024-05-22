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

    By productElement = By.cssSelector(".mb-3");

    public List<WebElement> getProductsList()
    {
        waitForElementToAppear(productElement);

        return products;
    }
}
