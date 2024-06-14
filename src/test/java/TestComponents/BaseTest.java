package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.Pages.CheckoutPage;
import org.example.Pages.LandingPage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest
{

    public  WebDriver driver;
    public LandingPage landingPage;
    //public CheckoutPage outPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
         //prop.getProperty("browser");
        if (browserName.contains("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
           if (browserName.contains("headless"))
           {
               options.addArguments("headless");
           }
           driver = new ChromeDriver(options);
           driver.manage().window().setSize(new Dimension(1920,1080));
           driver.manage().window().fullscreen();

        } else if (browserName.equalsIgnoreCase("firefox"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("headless");
            WebDriverManager.firefoxdriver().setup();
             driver = new FirefoxDriver();


        } else if (browserName.equalsIgnoreCase("edge"))
        {
            WebDriverManager.edgedriver().setup();
             driver =new EdgeDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;

    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;

    }



    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File file = ts.getScreenshotAs(OutputType.FILE);
        File src = new File(System.getProperty("user.dir") +"//reports//" +testCaseName+".png");
        FileUtils.copyFile(file, src);
        return System.getProperty("user.dir") +"//reports//" +testCaseName+".png";
    }

    @BeforeMethod (alwaysRun = true)

    public LandingPage launchApp() throws IOException {
         driver = initializeDriver();
         landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }
}
