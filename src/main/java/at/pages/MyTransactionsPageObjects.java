package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.List;

public class MyTransactionsPageObjects {
    public WebDriver driver;

    int tableTransactionsSize;
    int tableRowCount;
    int tableColumnCount;

    public MyTransactionsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindAll
            (@FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table"))
    List<WebElement> tableTransactions;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr"))
    List<WebElement> tableRowElements;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr[1]/td"))
    List<WebElement> tableColumnElements;

    public String getStringDestination() throws IOException {
        return tableColumnElements.get(0).getAttribute("innerText");
    }
}
