package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;

public class LandingPageObjects {

    public final WebDriver driver;

    public LandingPageObjects (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy (how = How.LINK_TEXT, using = "Log in")
    WebElement lnkLogin;

    public LoginPageObjects clickLinkLogin() throws IOException {
        WebElementFunctions.click(lnkLogin);
        return new LoginPageObjects(driver);
    }


}
