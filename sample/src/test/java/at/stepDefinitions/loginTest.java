package at.stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class loginTest {
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

    @When("I enter username, password and submit")
    public void iEnterUsernamePasswordAndSubmit() {
        loginPageObjects.setTxtUsername("sreerjvs@gmail.com");
        loginPageObjects.setTxtPassword("sreerjvs85");
        loginPageObjects.clickBtnSubmit();
    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() {
        errorMessage = loginPageObjects.getTxtErrorMessage();
        System.out.println(errorMessage);
    }
}
