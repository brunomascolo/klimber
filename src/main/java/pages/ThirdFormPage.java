package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Methods;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ThirdFormPage {
    public WebDriver driver;
    HashMap<String, Integer> provincesZips = new HashMap<>();

    public ThirdFormPage(WebDriver driver) {
        this.driver = driver;
        provincesZips.put("CABA", 1228);
        provincesZips.put("Buenos Aires", 1704);
        provincesZips.put("Córdoba", 5000);
        provincesZips.put("Mendoza", 5500);
        provincesZips.put("Misiones", 3300);
        provincesZips.put("Salta", 4400);
        provincesZips.put("San Juan", 5400);
        provincesZips.put("Jujuy", 4600);
        provincesZips.put("Santa Cruz", 9400);
        provincesZips.put("Catamarca", 4700);
        provincesZips.put("Chubut", 9103);
        provincesZips.put("Tucumán", 4000);
    }


    private Methods methods = new Methods();
    private By registerButton = By.id("btnRegister");
    private By birthdate = By.id("Birthday");
    private By phoneCode = By.id("PhoneCode");
    private By phoneNumber = By.id("PhoneNumber");
    private By provinceLabel = By.id("select2-province-container");
    private By nameInput = By.id("Name");
    private By lastnameInput = By.id("Surname");
    private By dniInput = By.id("ID_Number");
    private By emailInput = By.id("txtEmail");
    private By passwordInput = By.id("Password");
    private By streetInput = By.id("Street");
    private By streetNumberInput = By.id("HouseNumber");
    private By zipCodeInput = By.id("zipCode");
    private By sexWrapper = By.id("Gender");
    private By genderWrapper = By.id("IdentificationGenderType");
    private By civilStatusWrapper = By.id("CivilStatus");
    private By cityWrapper = By.id("city");


    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(registerButton));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
    public void verifyPreviousDataLoaded(String birthday, int areaCode, int cellphone, String province){
        String birthdayLoaded = driver.findElement(birthdate).getAttribute("value");
        int areaCodeLoaded = Integer.valueOf(driver.findElement(phoneCode).getAttribute("value"));
        int cellphoneLoaded = Integer.valueOf(driver.findElement(phoneNumber).getAttribute("value"));
        String provinceLoaded = driver.findElement(provinceLabel).getText();
        Assert.assertEquals(birthday, birthdayLoaded);
        Assert.assertEquals(areaCode, areaCodeLoaded);
        Assert.assertEquals(cellphone, cellphoneLoaded);
        Assert.assertEquals(province, provinceLoaded);
    }

    public void completeNameField(String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    public void verifyNameInput(String name){
        String nameLocated = driver.findElement(nameInput).getAttribute("value");
        Assert.assertEquals(nameLocated, name);
    }
    public void completeLastnameField(String lastname){
        driver.findElement(lastnameInput).sendKeys(lastname);
    }
    public void verifyLastnameInput(String lastname){
        String lastnameLocated = driver.findElement(lastnameInput).getAttribute("value");
        Assert.assertEquals(lastnameLocated, lastname);
    }
    public void completeDNIField(int dni){
        driver.findElement(dniInput).sendKeys(String.valueOf(dni));
    }
    public void verifyDNIInput(int dni){
        int dniLocated = Integer.valueOf(driver.findElement(dniInput).getAttribute("value"));
        Assert.assertEquals(dni, dniLocated);
    }
    public void completeEmailField(String email){
        driver.findElement(emailInput).sendKeys(email);
    }
    public void verifyEmailInput(String email){
        String emailLocated = driver.findElement(emailInput).getAttribute("value");
        Assert.assertEquals(emailLocated, email);
    }
    public void completePasswordField(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void verifyPasswordInput(String password){
        String passwordLocated = driver.findElement(passwordInput).getAttribute("data-validated");
        Assert.assertEquals(passwordLocated, password);
    }
    public void completeStreetField(String street){
        driver.findElement(streetInput).sendKeys(street);
    }
    public void verifyStreetInput(String street){
        String streetLocated = driver.findElement(streetInput).getAttribute("value");
        Assert.assertEquals(streetLocated, street);
    }
    public void completeStreetNumber(int streetNumber){
        driver.findElement(streetNumberInput).sendKeys(String.valueOf(streetNumber));
    }
    public void verifyStreetNumberInput(int streetNumber){
        int streetNumberLocated = Integer.valueOf(driver.findElement(streetNumberInput).getAttribute("value"));
        Assert.assertEquals(streetNumberLocated, streetNumber);
    }
    public void completeZIPCode(String province){
        int zipCode = provincesZips.get(province);
        driver.findElement(zipCodeInput).sendKeys(String.valueOf(zipCode));
    }
    public void verifyZIPCodeInput(String province){
        int zipCode = provincesZips.get(province);
        int zipCodeLocated = Integer.valueOf(driver.findElement(zipCodeInput).getAttribute("value"));
        Assert.assertEquals(zipCodeLocated, zipCode);
    }
    private Select locateDropdown(By wrapper){
        return new Select(driver.findElement(wrapper));
    }
    public List<String> getSexList(){
        List<WebElement> allOptions = locateDropdown(sexWrapper).getOptions();
        allOptions.remove(0);
        return allOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void selectSex(String sex){
        locateDropdown(sexWrapper).selectByVisibleText(sex);
    }
    public List<String> getSexSelected(){
        List<WebElement> selectedSex = locateDropdown(sexWrapper).getAllSelectedOptions();
        return selectedSex.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void verifySexIsSelected(String sex){
        List<String> sexSelectedList = getSexSelected();
        Assert.assertTrue(sexSelectedList.size() == 1);
        String sexSelected = sexSelectedList.get(0);
        Assert.assertEquals(sex, sexSelected);
    }
    public List<String> getCivilStatusList(){
        List<WebElement> allOptions = locateDropdown(civilStatusWrapper).getOptions();
        allOptions.remove(0);
        return allOptions.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void selectCivilStatus(String civilStatus){
        locateDropdown(civilStatusWrapper).selectByVisibleText(civilStatus);
    }
    public List<String> getCivilStatusSelected(){
        List<WebElement> selectedCivilStatus = locateDropdown(civilStatusWrapper).getAllSelectedOptions();
        return selectedCivilStatus.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void verifyCivilStatusIsSelected(String civilStatus){
        List<String> civilStatusSelectedList = getCivilStatusSelected();
        Assert.assertTrue(civilStatusSelectedList.size() == 1);
        String civilStatusSelected = civilStatusSelectedList.get(0);
        Assert.assertEquals(civilStatus, civilStatusSelected);
    }
    public FourthFormPage clickNextButton(){
        driver.findElement(registerButton).click();
        return new FourthFormPage(driver);
    }
}
