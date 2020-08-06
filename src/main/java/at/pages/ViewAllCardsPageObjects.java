package at.pages;

import at.commonLibrary.WebElementFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.List;

public class ViewAllCardsPageObjects {

    public final WebDriver driver;

    public ViewAllCardsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    public String  getTxtCardsTitle() throws IOException {
        return WebElementFunctions.getMessage(txtCardsTitle);
    }

    @FindBy(how = How.XPATH, using = "//h1[@class='page-title ng-binding ng-scope']")
    WebElement txtCardsTitle;

    @FindAll(
            @FindBy(how = How.XPATH, using = "//div[@class='myhopcards ng-scope']//div[@class='hopcard-title-wrapper']"))
    List<WebElement> listHopCards;



}
