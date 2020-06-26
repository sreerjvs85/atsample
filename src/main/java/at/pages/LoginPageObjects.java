package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPageObjects {
    public WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindBy (how = How.ID, using = "userNameInput")
    WebElement txtUsername;

    @FindBy (how = How.ID, using = "passwordInput")
    WebElement txtPassword;

    @FindBy (how = How.ID, using = "submitButton")
    WebElement btnSubmit;

    @FindBy (how = How.CLASS_NAME, using = "loginErrMsg")
    WebElement txtErrorMessage;

    public void setTxtUsername (String username) throws InterruptedException {
//        Thread.sleep(3000);
        txtUsername.sendKeys(username);
//        Helper.waitForElement(driver,txtUsername).sendKeys(username);
    }
    public void setTxtPassword (String password) throws InterruptedException {
//        Thread.sleep(3000);
        txtPassword.sendKeys(password);
//        Helper.waitForElement(driver, txtPassword).sendKeys(password);
    }
    public void clickBtnSubmit() throws InterruptedException {
//        Thread.sleep(3000);
        btnSubmit.click();
//        Helper.waitForElement(driver, btnSubmit).click();
    }
    public String getTxtErrorMessage() {
       if (isTxtErrorMessageVisible()) {
           return txtErrorMessage.getText();
       } else {
           return new Exception().getMessage();
       }
    }
    public boolean isTxtErrorMessageVisible(){
        if (!txtErrorMessage.isDisplayed()){
            return false;
        } else {
            return true;
        }
    }

}
