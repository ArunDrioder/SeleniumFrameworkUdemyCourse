package org.example.Pages;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent
{

    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userEmail")

    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginBtn;


    public ProductsPage loginToApp(String loginEmail, String loginPassword)
    {
        userEmail.sendKeys(loginEmail);
        userPassword.sendKeys(loginPassword);
        loginBtn.click();
        ProductsPage productsPage = new ProductsPage(driver);
        return productsPage;
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
