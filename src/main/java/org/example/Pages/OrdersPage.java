package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent
{
    WebDriver driver;


    @FindBy(css = ".totalRow Button")
    WebElement checkOutBtn;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderedProductNames;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public Boolean verifyOrderDisplayed (String productName)
    {
        Boolean match =  orderedProductNames.stream().anyMatch(nameOfTheOrderedProduct->nameOfTheOrderedProduct.getText().equalsIgnoreCase(productName));
        return match;

    }

    public CheckoutPage checkOut()
    {
        checkOutBtn.click();
        return new CheckoutPage(driver);
    }


}
