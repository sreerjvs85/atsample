package stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    WebDriver driver;
    LoginPageObjects loginPageObjects;
    String errorMessage;

    @Before
    public void openATAndClickLogin() {
        driver = BrowserFunctions.getDriver("chrome", "https://at.govt.nz/");
    }

    @Given("I'm on login screen of at")
    public void iMOnLoginScreenOfAt() {
        loginPageObjects = new LandingPageObjects(driver).clickLinkLogin();
    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() {
        errorMessage = loginPageObjects.getTxtErrorMessage();
        System.out.println(errorMessage);
    }
    @After
    public void quitDriver() {
        driver.quit();
    }

    @When("I enter username {string}, password {string} and submit")
    public void iEnterUsernamePasswordAndSubmit(String user, String pass) throws InterruptedException {
        loginPageObjects.setTxtUsername(user);
        loginPageObjects.setTxtPassword(pass);
        loginPageObjects.clickBtnSubmit();
    }
}
