package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

public class LoginPageObjects {
    public final WebDriver driver;

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy (how = How.ID, using = "userNameInput")
    WebElement txtUsername;

    @FindBy (how = How.ID, using = "passwordInput")
    WebElement txtPassword;

    @FindBy (how = How.ID, using = "submitButton")
    WebElement btnSubmit;

    @FindBy (how = How.CLASS_NAME, using = "loginErrMsg")
    WebElement txtErrorMessage;

    public void setTxtUsername (String username) throws IOException {
        WebElementFunctions.fillField(txtUsername, username);
    }
    public void setTxtPassword (String password) throws IOException {
        WebElementFunctions.fillField(txtPassword,password);
    }
    public MyATPageObjects clickBtnSubmit() throws IOException {
        WebElementFunctions.click(btnSubmit);
        return new MyATPageObjects(driver);
    }
    public boolean getTxtErrorMessage() throws IOException {
       return WebElementFunctions.isWebelementPresent(txtErrorMessage);
    }
}
