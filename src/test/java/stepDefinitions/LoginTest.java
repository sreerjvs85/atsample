package stepDefinitions;

import at.browserLibrary.BrowserFunctions;
import at.pages.LandingPageObjects;
import at.pages.LoginPageObjects;
import at.pages.MyATPageObjects;
import at.pages.MyTransactionsPageObjects;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.math3.util.Precision;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Date;

public class LoginTest {
    WebDriver driver;

    String errorMessage;
    String actualBalance;
    String actualDestination;

    LoginPageObjects loginPageObjects;
    MyATPageObjects myATPageObjects;
    MyTransactionsPageObjects myTransactionsPageObjects;

    @Before
    public void timerStart(){
        System.out.println("TC Start: " + new Date());
    }

    @After
    public void tearDown() throws IOException {
        myATPageObjects.clickLinkLogout();
        BrowserFunctions.quitDriver();
        System.out.println("TC Stop: " + new Date());
    }

    @Then("If i get error message, capture it.")
    public void ifIGetErrorMessageCaptureIt() throws IOException {
        if (!myATPageObjects.getWelcomeMessage().equals("Hello Sreevathsan")) {
            errorMessage = loginPageObjects.getTxtErrorMessage();
        }
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
            actualBalance = myATPageObjects.getMyAtBalance();
            Assert.assertEquals(actualBalance, expectedBalance);
        }
    }

    @Then("I click on View Transactions button to see all my previous travels")
    public void iClickOnViewTransactionsButtonToSeeAllMyPreviousTravels() throws IOException {
        myTransactionsPageObjects = myATPageObjects.clickBtnViewTransactions();
    }

    @And("Verify the last transaction's destination as {string}")
    public void verifyTheLastTransactionSDestinationAs(String expectedDestination) {
        actualDestination = myTransactionsPageObjects.getStringDestination();
        Assert.assertEquals(actualDestination,expectedDestination);
    }

    @And("Verify the {string} transaction details like tag on, tag off and hop balance")
    public void verifyTheTransactionDetailsLikeTagOnTagOffAndHopBalance(String arg0) {
        String[] transaction = myTransactionsPageObjects.TargettedTransactions(arg0);
        double hopBalance = 0, previousBalace = 0, debit = 0, credit = 0;
        String destination = null, source = null, journeyDate = null;
        int totalJourneys = 0;
        int destStartIndex = 10, destEndIndex = 25, srcStartIndex = 9, srcEndIndexRef = 34, srcEndIndex = 19,
                autoStartIndex = 14, autoEndIndex = 22;

        for (String str: transaction) {
           int index = str.lastIndexOf("$");
           if (index<0 && !str.startsWith("Tag")) {
               journeyDate = str;
           }
           if (index>0 && str.startsWith("Tag off")) {
               hopBalance = Double.parseDouble(str.substring(index+1));
               index = str.indexOf("$");
               debit = Double.parseDouble(str.substring(index+1, str.lastIndexOf("$")-1));
               destination = str.substring(destStartIndex,str.length()-destEndIndex);
           }
           if (index >0 && str.startsWith("Tag on")) {
               if (str.contains("refund")) {
                   source = str.substring(srcStartIndex, str.length() - srcEndIndexRef);
               } else {
                   source = str.substring(srcStartIndex, str.length() - srcEndIndex);
               }
               previousBalace = Double.parseDouble(str.substring(index + 1));
               totalJourneys++;
               Assert.assertEquals(hopBalance, Precision.round((previousBalace - debit),2));
           }
           if (str.startsWith("Auto")) {
               source = destination = str.substring(autoStartIndex, str.length()-autoEndIndex);
               hopBalance = Double.parseDouble(str.substring(index+1));
               index = str.indexOf("$");
               credit = Double.parseDouble(str.substring(index+1, str.lastIndexOf("$")-1));
               Assert.assertEquals(hopBalance, previousBalace);
           }
           if (source!= null && destination!= null) {
               System.out.println("Journey date: " + journeyDate
                       + " Hop Balance: " + hopBalance
                       + " Debit: " + debit
                       + " Credit: " + credit
                       + " Previous Balance: " + previousBalace
                       + " Source: " + source
                       + " Destination: " + destination
                       + " Total journeys: " + totalJourneys);
               source=destination=null;
               debit=credit=0;
           }
        }
    }
}
