package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CheckoutPage extends AbstractComponent
{

    WebDriver driver;


    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
    WebElement submit;

    @FindBy(xpath = "//button[contains(@class,'ta-item')][2]")
    WebElement selectOption;

   By dropDownOptionElement = By.cssSelector(".ta-results");

    public void selectCountry(String countryName)
    {
        Actions actions = new Actions(driver);
        actions.sendKeys(selectCountry,countryName).build().perform();
        waitForElementToAppear(dropDownOptionElement);
       selectOption.click();
    }

    public ConfirmationPage submitOrder()
    {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        submit.click();
        return new ConfirmationPage(driver);
    }
}
