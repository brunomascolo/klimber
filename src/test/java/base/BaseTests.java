package base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;

import java.time.Duration;
import java.util.Collections;

public class BaseTests {
    private EventFiringWebDriver driver;
    protected HomePage homePage;
    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        goHome();

    }
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    @BeforeMethod
    public void goHome(){
        driver.get("https://purchase-testing.klimber.com/ar/GroupLife/Index");
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(Duration.ofSeconds(15)), "Home page is not opened");
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--remote-allow-origins=*");
        return options;
    }

}
