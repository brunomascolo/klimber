package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Methods;

import java.time.Duration;

public class PaymentMethodPage {
    public WebDriver driver;

    public PaymentMethodPage(WebDriver driver) {
        this.driver = driver;
    }
    private Methods methods = new Methods();
    private By creditCardNumberInput = By.id("CardNumber");
    private By nextButton = By.id("btnSubmitStep4");
    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(creditCardNumberInput));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public void completeCreditCardNumber(String number){
        driver.findElement(creditCardNumberInput).sendKeys(number);
    }
    public void verifyCreditCarNumberInput(String number){
        String numberLocated = driver.findElement(creditCardNumberInput).getAttribute("value").replace(" ", "");
        Assert.assertEquals(number, numberLocated);
    }
    public BeneficiariesPage clickNextButton(){
        driver.findElement(nextButton).click();
        return new BeneficiariesPage(driver);
    }
}
