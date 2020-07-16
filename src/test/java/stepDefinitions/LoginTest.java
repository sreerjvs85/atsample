package stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginTest {
    WebDriver driver;
    LoginPageObjects loginPageObjects;
    String errorMessage;

//    @Before
//    public void openATAndClickLogin() {
//        driver = BrowserFunctions.getDriver("chrome", "https://at.govt.nz/");
//    }

    @After
    public void quitDriver() {
        System.out.println("After method - Thread Id "+ Thread.currentThread().getId());
        driver.quit();
    }

//    @Given("I'm on login screen of at")
//    public void iMOnLoginScreenOfAt() {
//        loginPageObjects = new LandingPageObjects(driver).clickLinkLogin();
//    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() {
        errorMessage = loginPageObjects.getTxtErrorMessage();
        System.out.println(errorMessage);
        System.out.println("Then method - Thread Id "+ Thread.currentThread().getId());
    }

    @When("I enter username {string}, password {string} and submit")
    public void iEnterUsernamePasswordAndSubmit(String user, String pass) throws InterruptedException {
        loginPageObjects.setTxtUsername(user);
        loginPageObjects.setTxtPassword(pass);
        loginPageObjects.clickBtnSubmit();
        System.out.println("When method - Thread Id "+ Thread.currentThread().getId());
    }

    @Given("I'm on login screen of at using {string}")
    public void iMOnLoginScreenOfAtUsing(String browser) {
        driver = BrowserFunctions.getDriver(browser, "https://at.govt.nz/");
        loginPageObjects = new LandingPageObjects(driver).clickLinkLogin();
        System.out.println("Given method - Thread Id "+ Thread.currentThread().getId());
    }
}
