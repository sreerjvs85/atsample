package stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import at.pages.MyATPageObjects;
import at.pages.MyTransactionsPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;

public class LoginTest {
    WebDriver driver;

    String errorMessage;

    LoginPageObjects loginPageObjects;
    MyATPageObjects myATPageObjects;
    MyTransactionsPageObjects myTransactionsPageObjects;

    @After
    public void tearDown() throws IOException {
        myATPageObjects.clickLinkLogout();
        BrowserFunctions.quitDriver();
    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() throws IOException {

        errorMessage = loginPageObjects.getTxtErrorMessage();
    }

    @When("I enter username {string}, password {string} and submit")
    public void iEnterUsernamePasswordAndSubmit(String user, String pass) throws IOException {
        loginPageObjects.setTxtUsername(user);
        loginPageObjects.setTxtPassword(pass);
        myATPageObjects = loginPageObjects.clickBtnSubmit();
    }

    @Given("I'm on login screen of at using {string}")
    public void iMOnLoginScreenOfAtUsing(String browser) throws IOException {
        driver = BrowserFunctions.getDriver(browser, "https://at.govt.nz/");
        loginPageObjects = new LandingPageObjects(driver).clickLinkLogin();
    }

    @Then("I should be able to see my login and check {string} balance")
    public void iShouldBeAbleToSeeMyLoginAndCheckBalance(String expectedBalance) throws IOException {
        if (myATPageObjects.getWelcomeMessage().equals("Hello Sreevathsan")) {
            String actualBalance = myATPageObjects.getMyAtBalance();
            Assert.assertEquals(actualBalance, expectedBalance);
        }
    }

    @Then("I click on View Transactions button to see all my previous travels")
    public void iClickOnViewTransactionsButtonToSeeAllMyPreviousTravels() throws IOException {
        myTransactionsPageObjects = myATPageObjects.clickBtnViewTransactions();
    }

    @And("Verify the last transaction's destination as {string}")
    public void verifyTheLastTransactionSDestinationAs(String expectedDestination) throws IOException {
            String actualDestination = myTransactionsPageObjects.getStringDestination();
            Assert.assertEquals(actualDestination,expectedDestination);
    }

}
