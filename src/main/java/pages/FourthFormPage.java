package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class FourthFormPage {
    public WebDriver driver;

    public FourthFormPage(WebDriver driver) {
        this.driver = driver;
    }
    private By nextButton = By.id("btnSaveInfo");
    private By nationalityWrapper = By.id("Nationality");
    private By placeOfBirthInput = By.id("PlaceOfBirth");
    private By occupationInput = By.id("txtOccupation");
    private By anualSalaryInput = By.id("txtSalaryAnual");
    private By moneyOriginWrapper = By.id("txtAnnualIncome");
    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(nextButton));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    private Select locateDropdown(By wrapper){
        return new Select(driver.findElement(wrapper));
    }
    public List<String> getNationalityList(){
        List<WebElement> allOptions = locateDropdown(nationalityWrapper).getOptions();
        allOptions.remove(0);
        return allOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void selectNationality(String nationality){
        locateDropdown(nationalityWrapper).selectByVisibleText(nationality);
    }
    public List<String> getNationalitySelected(){
        List<WebElement> selectedNationality = locateDropdown(nationalityWrapper).getAllSelectedOptions();
        return selectedNationality.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void verifyNationalityIsSelected(String nationality){
        List<String> nationalitySelectedList = getNationalitySelected();
        Assert.assertTrue(nationalitySelectedList.size() == 1);
        String nationalitySelected = nationalitySelectedList.get(0);
        Assert.assertEquals(nationality, nationalitySelected);
    }
    public List<String> getMoneyOriginList(){
        List<WebElement> allOptions = locateDropdown(moneyOriginWrapper).getOptions();
        allOptions.remove(0);
        return allOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void selectMoneyOrigin(String moneyOrigin){
        locateDropdown(moneyOriginWrapper).selectByVisibleText(moneyOrigin);
    }
    public List<String> getMoneyOriginSelected(){
        List<WebElement> selectedMoneyOrigin = locateDropdown(moneyOriginWrapper).getAllSelectedOptions();
        return selectedMoneyOrigin.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void verifyMoneyOriginIsSelected(String moneyOrigin){
        List<String> moneyOriginSelectedList = getNationalitySelected();
        Assert.assertTrue(moneyOriginSelectedList.size() == 1);
        String moneyOriginSelected = moneyOriginSelectedList.get(0);
        Assert.assertEquals(moneyOrigin, moneyOriginSelected);
    }
    public void completePlaceOfBirth(String placeOfBirth){
        driver.findElement(placeOfBirthInput).sendKeys(placeOfBirth);
    }
    public void verifyPlaceOfBirthInput(String placeOfBirth){
        String placeOfBirthLocated = driver.findElement(placeOfBirthInput).getAttribute("value");
        Assert.assertEquals(placeOfBirthLocated, placeOfBirth);
    }
    public void completeOccupation(String occupation){
        driver.findElement(occupationInput).sendKeys(occupation);
    }
    public void verifyOccupationInput(String occupation){
        String occupationLocated = driver.findElement(occupationInput).getAttribute("value");
        Assert.assertEquals(occupationLocated, occupation);
    }
    public void completeAnualSalary(int salary){
        driver.findElement(anualSalaryInput).sendKeys(String.valueOf(salary));
    }
    public void verifyAnualSalaryInput(int salary){
        int salaryLocated = Integer.valueOf(driver.findElement(anualSalaryInput).getAttribute("value"));
        Assert.assertEquals(salaryLocated, salary);
    }
    public PaymentMethodPage clickNextButton(){
        driver.findElement(nextButton).click();
        return new PaymentMethodPage(driver);
    }
}
