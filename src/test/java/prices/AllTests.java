package prices;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.*;
import utils.Methods;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class AllTests extends BaseTests {
    private static final String BIRTHDATE = "10/09/1997";
    private Methods methods;
    private Random random = new Random();

    @Test
    public void testQuoteInsuranceRedirectsWithValidData() {
        List<String> provinces = homePage.getProvinceList();
        String provinceToSelect = methods.selectRandomString(provinces);
        int areaCode = random.nextInt(999);
        int phoneNumber = random.nextInt(9999999);
        int height = 180;
        int weight = 80;
        String name = methods.generateRandomString(6);
        String lastname = methods.generateRandomString(10);
        int dni = random.nextInt(10000000,50000000);
        String email = "randomemail" + String.valueOf(phoneNumber) + "@gmail.com";
        String password = lastname + String.valueOf(phoneNumber);
        String street = methods.generateRandomString(12);
        int anualSalary = random.nextInt(3000000, 30000000);
        String creditCarNumber = "4970100000000055";
        homePage.completeBirthdate(BIRTHDATE);
        homePage.selectProvince(provinceToSelect);
        homePage.completeCellphone(areaCode, phoneNumber);
        homePage.moveSliderToRandomValue();
        homePage.verifyBirthdateCorrectlySet(BIRTHDATE);
        homePage.verifyProvinceIsSelected(provinceToSelect);
        homePage.verifyCellphoneIsCorrect(areaCode, phoneNumber);
        homePage.verifyHireButtonContainsCorrectPrice();
        int insuranceAmount = homePage.extractPriceFromSlider();
        int monthlyPrice = homePage.extractPriceFromHireButton();
        SecondFormPage secondFormPage = homePage.clickHireServiceButton();
        secondFormPage.isPageLoaded(Duration.ofSeconds(15));
        secondFormPage.completeHeightInput(height);
        secondFormPage.completeWeightInput(weight);
        secondFormPage.verifyHeightFilled(height);
        secondFormPage.verifyWeightFilled(weight);
        ThirdFormPage thirdFormPage = secondFormPage.clickNextButton();
        thirdFormPage.isPageLoaded(Duration.ofSeconds(15));
        List<String> sexList = thirdFormPage.getSexList();
        List<String> civilStatusList = thirdFormPage.getCivilStatusList();
        String sex = methods.selectRandomString(sexList);
        String civilStatus = methods.selectRandomString(civilStatusList);
        thirdFormPage.verifyPreviousDataLoaded(BIRTHDATE, areaCode, phoneNumber, provinceToSelect);
        thirdFormPage.completeNameField(name);
        thirdFormPage.completeLastnameField(lastname);
        thirdFormPage.completeDNIField(dni);
        thirdFormPage.completeEmailField(email);
        thirdFormPage.completePasswordField(password);
        thirdFormPage.completeStreetField(street);
        thirdFormPage.completeStreetNumber(areaCode);
        thirdFormPage.completeZIPCode(provinceToSelect);
        thirdFormPage.selectSex(sex);
        thirdFormPage.selectCivilStatus(civilStatus);
        thirdFormPage.verifyNameInput(name);
        thirdFormPage.verifyLastnameInput(lastname);
        thirdFormPage.verifyDNIInput(dni);
        thirdFormPage.verifyEmailInput(email);
        thirdFormPage.verifyPasswordInput(password);
        thirdFormPage.verifyStreetInput(street);
        thirdFormPage.verifyStreetNumberInput(areaCode);
        thirdFormPage.verifyZIPCodeInput(provinceToSelect);
        thirdFormPage.verifySexIsSelected(sex);
        thirdFormPage.verifyCivilStatusIsSelected(civilStatus);
        FourthFormPage fourthFormPage = thirdFormPage.clickNextButton();
        fourthFormPage.isPageLoaded(Duration.ofSeconds(15));
        List<String> nationalityList = fourthFormPage.getNationalityList();
        List<String> moneyOriginList = fourthFormPage.getMoneyOriginList();
        String nationality = methods.selectRandomString(nationalityList);
        String moneyOrigin = methods.selectRandomString(moneyOriginList);
        fourthFormPage.selectNationality(nationality);
        fourthFormPage.selectMoneyOrigin(moneyOrigin);
        fourthFormPage.completePlaceOfBirth(street);
        fourthFormPage.completeAnualSalary(anualSalary);
        fourthFormPage.completeOccupation(lastname);
        PaymentMethodPage paymentMethodPage = fourthFormPage.clickNextButton();
        paymentMethodPage.isPageLoaded(Duration.ofSeconds(15));
        paymentMethodPage.completeCreditCardNumber(creditCarNumber);
        paymentMethodPage.verifyCreditCarNumberInput(creditCarNumber);
        BeneficiariesPage beneficiariesPage = paymentMethodPage.clickNextButton();
        beneficiariesPage.isPageLoaded(Duration.ofSeconds(15));
        SummaryPage summaryPage = beneficiariesPage.clickNextButton();
        summaryPage.isPageLoaded(Duration.ofSeconds(15));
        summaryPage.verifyFinishButtonIsEnabled(false);
        summaryPage.verifyFullnameIsCorrect(name, lastname);
        summaryPage.verifyDNIIsCorrect(dni);
        summaryPage.verifyBirthdateIsCorrect(BIRTHDATE);
        summaryPage.verifyInsuranceAmountIsCorrect(insuranceAmount);
        summaryPage.verifyMonthlyPriceIsCorrect(monthlyPrice);
        summaryPage.checkTermsAndConditions();
        summaryPage.verifyFinishButtonIsEnabled(true);
        CongratulationsPage congratulationsPage = summaryPage.clickFinishButton();
        congratulationsPage.isPageLoaded(Duration.ofSeconds(15));
    }
}
