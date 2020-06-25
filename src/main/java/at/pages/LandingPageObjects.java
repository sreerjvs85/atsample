package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LandingPageObjects {

    public WebDriver driver;

    public LandingPageObjects (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindBy (how = How.LINK_TEXT, using = "Log in")
    WebElement linkLogin;

    public LoginPageObjects clickLinkLogin(){
        linkLogin.click();
        return new LoginPageObjects(driver);
    }


}
