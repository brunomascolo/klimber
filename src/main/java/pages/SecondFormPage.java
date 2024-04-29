package pages;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondFormPage {
    public WebDriver driver;

    public SecondFormPage(WebDriver driver) {
        this.driver = driver;
    }
    private By nextButton = By.id("btnSaveStep2");
    private By heightInput = By.id("txtHeight");
    private By weightInput = By.id("txtWeight");

    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(nextButton));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public void completeHeightInput(int height){
        driver.findElement(heightInput).sendKeys(String.valueOf(height));
    }
    public void completeWeightInput(int weight){
        driver.findElement(weightInput).sendKeys(String.valueOf(weight));
    }

    public void verifyHeightFilled(int heightExpected){
        int heightPresent = Integer.valueOf(driver.findElement(heightInput).getAttribute("value"));
        Assert.assertEquals(heightExpected, heightPresent);
    }
    public void verifyWeightFilled(int weightExpected){
        int weightPresent = Integer.valueOf(driver.findElement(weightInput).getAttribute("value"));
        Assert.assertEquals(weightExpected, weightPresent);
    }
    public ThirdFormPage clickNextButton(){
        driver.findElement(nextButton).click();
        return new ThirdFormPage(driver);
    }

}
