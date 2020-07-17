package at.pages;

import at.commonLibrary.WebelementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

public class MyATPageObjects {
    WebDriver driver;

    public MyATPageObjects(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,30),this);
    }

    @FindBy (how = How.CLASS_NAME, using = "default-hop-balance")
    WebElement txtMyAtBalance;

    @FindBy(how = How.LINK_TEXT, using = "Log out")
    WebElement lnkLogout;

    @FindBy(how = How.CLASS_NAME, using = "welcome_text")
    WebElement txtWelcomeMessage;

    @FindBy(how = How.LINK_TEXT, using = "View transactions")
    WebElement btnViewTransactions;

    @FindBy(how = How.ID, using = "default-hop-balance-remaining")
    WebElement txtDefaultAtBalance;

    public String getWelcomeMessage(WebElement ele) throws IOException {
        return WebelementFunctions.getMessage(txtWelcomeMessage);
    }
}
