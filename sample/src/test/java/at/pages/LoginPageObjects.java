package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjects {
    WebDriver driver;

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
        txtUsername.sendKeys(username);
    }
    public void setTxtPassword (String password) {
        txtPassword.sendKeys(password);
    }
    public void clickBtnSubmit() {
        btnSubmit.click();
    }
    public String getTxtErrorMessage() {
        try {
            return txtErrorMessage.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


}
