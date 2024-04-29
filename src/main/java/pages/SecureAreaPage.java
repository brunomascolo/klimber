package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    public WebDriver driver;
    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
    }

    private By statusAlert = By.id("flash");
    private By signOutButton = By.className("icon-signout");
    public String getAlertText(){
        return driver.findElement(statusAlert).getText();
    }
    public boolean getSignOutButton(){
        return driver.findElement(signOutButton).isDisplayed();
    }
}
