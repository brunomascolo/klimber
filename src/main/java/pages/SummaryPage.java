package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Methods;

import java.time.Duration;
import java.util.List;

public class SummaryPage {
    public WebDriver driver;

    public SummaryPage(WebDriver driver) {
        this.driver = driver;
    }
    Methods methods = new Methods();
    private By termsCheckbox = By.id("chkTC");
    private By fullnameInput = By.className("form-control");
    private By dniInput = By.id("ID_Number");
    private By monthlyPriceInput = By.id("txtSummaryTotalPremium");
    private By finishButton = By.id("btnSummarySubmit");
    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(termsCheckbox));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public void verifyFinishButtonIsEnabled(boolean enabled){
        WebElement finishBtn = driver.findElement(finishButton);
        if (enabled){
            Assert.assertTrue(finishBtn.isEnabled());
        } else {
            Assert.assertTrue(!finishBtn.isEnabled());
        }
    }
    public void verifyFullnameIsCorrect(String name, String lastname){
        String fullname = driver.findElement(fullnameInput).getAttribute("value");
        Assert.assertEquals(name + " " + lastname, fullname);
    }
    public void verifyDNIIsCorrect(int dni){
        int dniLocated = Integer.valueOf(driver.findElement(dniInput).getAttribute("value"));
        Assert.assertEquals(dni, dniLocated);
    }
    public void verifyBirthdateIsCorrect(String birthday){
        List<WebElement> formControlElements = driver.findElements(fullnameInput);
        String birthdate = formControlElements.get(2).getAttribute("value");
        Assert.assertEquals(birthday, birthdate);
    }
    public void verifyInsuranceAmountIsCorrect(int insuranceAmount){
        List<WebElement> formControlElements = driver.findElements(fullnameInput);
        int insuranceAmountLocated = methods.parseStringToInt(formControlElements.get(3).getAttribute("value"));
        Assert.assertEquals(insuranceAmount, insuranceAmountLocated);
    }
    public void verifyMonthlyPriceIsCorrect(int monthlyPrice){
        int monthlyPriceLocated = methods.parseStringToInt(driver.findElement(monthlyPriceInput).getAttribute("value"));
        Assert.assertEquals(monthlyPrice, monthlyPriceLocated);
    }
    public void checkTermsAndConditions(){
        driver.findElement(termsCheckbox).click();
    }
    public CongratulationsPage clickFinishButton(){
        driver.findElement(finishButton).click();
        return new CongratulationsPage(driver);
    }
}
