package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import utils.Methods;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HomePage {
    public WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
    }
    private Methods methods = new Methods();
    private By form = By.id("frmIndex");
    private By birthday = By.id("BirthdayStep1");
    private By provinceDropdown = By.id("province");
    private By phoneCodeInput = By.id("txtPhoneCode");
    private By phoneNumberInput = By.id("txtPhoneNumber");
    private By monthlyPriceValue = By.className("monthlyPrice");
    private By hireServiceButton = By.id("btnSaveStep1");
    private By insuranceAmountValue = By.id("suma_aseguradatotal");
    public boolean isPageLoaded(Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(form));
            return element != null;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public void completeBirthdate(String date){
        driver.findElement(birthday).sendKeys(String.valueOf(date));
    }
    public String getBirthdateSet(){
        String birthdate = driver.findElement(birthday).getAttribute("value");
        return birthdate;
    }
    public void verifyBirthdateCorrectlySet(String date){
        String birthdaySet = getBirthdateSet();
        Assert.assertEquals(birthdaySet, date);
    }
    private Select locateProvinceDropdown(){
        return new Select(driver.findElement(provinceDropdown));
    }
    public List<String> getProvinceList(){
        List<WebElement> allProvinces = locateProvinceDropdown().getOptions();
        allProvinces.remove(0);
        return allProvinces.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void selectProvince(String province){
        locateProvinceDropdown().selectByVisibleText(province);
    }
    public List<String> getProvinceSelected(){
       List<WebElement> selectedProvince = locateProvinceDropdown().getAllSelectedOptions();
       return selectedProvince.stream().map(e->e.getText()).collect(Collectors.toList());
    }
    public void verifyProvinceIsSelected(String province){
        List<String> provinceSelectedList = getProvinceSelected();
        Assert.assertTrue(provinceSelectedList.size() == 1);
        String provinceSelected = provinceSelectedList.get(0);
        Assert.assertEquals(province, provinceSelected);
    }
    public void completeCellphone(int phoneCode, int phoneNumber){
        driver.findElement(phoneCodeInput).sendKeys(String.valueOf(phoneCode));
        driver.findElement(phoneNumberInput).sendKeys(String.valueOf(phoneNumber));
    }
    public void verifyCellphoneIsCorrect(int phoneCode, int phoneNumber){
        int phoneCodeSet = Integer.parseInt(driver.findElement(phoneCodeInput).getAttribute("value"));
        int phoneNumberSet = Integer.parseInt(driver.findElement(phoneNumberInput).getAttribute("value"));
        Assert.assertEquals(phoneCodeSet, phoneCode);
        Assert.assertEquals(phoneNumberSet, phoneNumber);
    }
    public void moveSliderToRandomValue() {
        Random random = new Random();

        WebElement slider = driver.findElement(By.id("montoAsegurado"));
        WebElement sliderButton = driver.findElement(By.className("slider-handle"));

        // Get min and max values
        int minValue = Integer.parseInt(slider.getAttribute("data-slider-min"));
        int maxValue = Integer.parseInt(slider.getAttribute("data-slider-max"));

        int randomValue = random.nextInt(maxValue - minValue + 1) + minValue;;

        int sliderWidth = driver.findElement(By.className("slider-horizontal")).getSize().getWidth();

        // Calculate offset based on value
        int xOffset = (int) ((sliderWidth / (double) (maxValue - minValue)) * (randomValue - minValue));

        Actions actions = new Actions(driver);
        actions.clickAndHold(sliderButton).moveByOffset(xOffset, 0).release().perform();
    }

    public int extractPriceFromHireButton() {
        Pattern pattern = Pattern.compile("\\$(\\d+(\\.\\d+)?)"); // Matches $ followed by digits and optional decimal point
        Matcher matcher = pattern.matcher(driver.findElement(hireServiceButton).getText());
        if (matcher.find()) {
            String priceString = matcher.group(1);
            priceString = priceString.replace(".", "");
            int price = Integer.parseInt(priceString);
            return price;
        }
        return -1;
    }
    public int extractPriceFromSlider(){
        return methods.parseStringToInt(driver.findElement(insuranceAmountValue).getText());
    }
    public void verifyHireButtonContainsCorrectPrice(){
        int princeInSlider = methods.parseStringToInt(driver.findElement(monthlyPriceValue).getText());
        int priceInButton = extractPriceFromHireButton();
        Assert.assertEquals(princeInSlider, priceInButton);
    }

    public SecondFormPage clickHireServiceButton(){
        driver.findElement(hireServiceButton).click();
        return new SecondFormPage(driver);
    }

}
