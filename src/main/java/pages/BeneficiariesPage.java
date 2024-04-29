package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BeneficiariesPage {
    public WebDriver driver;

    public BeneficiariesPage(WebDriver driver) {
        this.driver = driver;
    }
    private By nextButton = By.className("submitFinal");
    private By addPersonButton = By.id("btnAddBeneficiaryNatural");
    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(addPersonButton));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public SummaryPage clickNextButton(){
        driver.findElement(nextButton).click();
        return new SummaryPage(driver);
    }
}
