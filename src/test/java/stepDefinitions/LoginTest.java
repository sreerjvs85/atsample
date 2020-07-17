package stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginTest {
    WebDriver driver;
    LoginPageObjects loginPageObjects;
    String errorMessage;

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() throws IOException {
        errorMessage = loginPageObjects.getTxtErrorMessage();
        System.out.println(errorMessage);
    }

    @When("I enter username {string}, password {string} and submit")
    public void iEnterUsernamePasswordAndSubmit(String user, String pass) throws InterruptedException, IOException {
        loginPageObjects.setTxtUsername(user);
        loginPageObjects.setTxtPassword(pass);
        loginPageObjects.clickBtnSubmit();
    }

    @Given("I'm on login screen of at using {string}")
    public void iMOnLoginScreenOfAtUsing(String browser) throws IOException {
        driver = BrowserFunctions.getDriver(browser, "https://at.govt.nz/");
        loginPageObjects = new LandingPageObjects(driver).clickLinkLogin();
    }
}
