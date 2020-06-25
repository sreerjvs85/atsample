package at.pages;

import at.commonLibrary.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
    public WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (how = How.ID, using = "userNameInput")
    WebElement txtUsername;

    @FindBy (how = How.ID, using = "passwordInput")
    WebElement txtPassword;

    @FindBy (how = How.ID, using = "submitButton")
    WebElement btnSubmit;

    @FindBy (how = How.CLASS_NAME, using = "loginErrMsg")
    WebElement txtErrorMessage;

    public void setTxtUsername (String username) {
        Helper.waitForElement(driver,txtUsername).sendKeys(username);
    }
    public void setTxtPassword (String password) {
        Helper.waitForElement(driver, txtPassword).sendKeys(password);
    }
    public void clickBtnSubmit() {
        Helper.waitForElement(driver, btnSubmit).click();
    }
    public String getTxtErrorMessage() {
        try {
            return txtErrorMessage.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
