package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LandingPageObjects {

    WebDriver driver;

    public LandingPageObjects (WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (how = How.LINK_TEXT, using = "Log in")
    WebElement linkLogin;

    public LoginPageObjects clickLinkLogin(){
        linkLogin.click();
        return new LoginPageObjects(driver);
    }


}
