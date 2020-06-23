package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageObjects {
    WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (how = How.ID, using = "userNameInput")
    WebElement txtUsername;

    @FindBy (how = How.ID, using = "passwordInput")
    WebElement txtPassword;

    @FindBy (how = How.ID, using = "submitButton")
    WebElement btnSubmit;

}
