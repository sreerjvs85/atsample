package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

public class MyATPageObjects {
    final WebDriver driver;

    public MyATPageObjects(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    }

    @FindBy (how = How.XPATH, using = "//span[@class='default-hop-balance']")
    WebElement txtMyAtBalance;

    @FindBy(how = How.LINK_TEXT, using = "Log out")
    WebElement lnkLogout;

    @FindBy(how = How.CLASS_NAME, using = "welcome_text")
    WebElement txtWelcomeMessage;

    @FindBy(how = How.LINK_TEXT, using = "View transactions")
    WebElement btnViewTransactions;

    @FindBy(how = How.ID, using = "default-hop-balance-remaining")
    WebElement txtDefaultAtBalance;

    @FindBy(how = How.LINK_TEXT, using = "View all cards")
    WebElement btnViewAllCards;

    public String getWelcomeMessage() throws IOException {
        return WebElementFunctions.getMessage(txtWelcomeMessage);
    }

    public String getMyAtBalance () throws  IOException {
        return WebElementFunctions.getMessage(txtMyAtBalance);
    }
    public String getDefaultAtBalance () throws  IOException {
        return WebElementFunctions.getMessage(txtDefaultAtBalance);
    }
    public void clickLinkLogout() throws IOException {
        WebElementFunctions.click(lnkLogout);
    }
    public MyTransactionsPageObjects clickBtnViewTransactions() throws IOException {
        WebElementFunctions.click(btnViewTransactions);
        return new MyTransactionsPageObjects(driver);
    }
    public ViewAllCardsPageObjects clickBtnViewAllCards() throws IOException {
        WebElementFunctions.click(btnViewAllCards);
        return new ViewAllCardsPageObjects(driver);
    }

}
