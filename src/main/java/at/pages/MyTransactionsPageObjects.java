package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class MyTransactionsPageObjects {
    public final WebDriver driver;

    public MyTransactionsPageObjects(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    @FindAll
            (@FindBy(how = How.XPATH,using = "//table[@class='transactions-table ng-scope']"))
    List<WebElement> listTableTransactions;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr[1]/td"))
    List<WebElement> tableColumnElements;

    public String getStringDestination(){
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private String[] Transactions(WebElement element) {
        String[] transactions;
        transactions = element.getText().split("\n");
        return transactions;
    }

    public String[] TargettedTransactions(String arg0) {
        String[] targetStringList;
        int index = 0;
        switch (arg0.toLowerCase()) {
            case "first":
                index = 0;
                break;
            case "second":
                index = 1;
                break;
            case "third":
                index = 2;
                break;
            case "fourth":
                index = 3;
                break;
            case "fifth":
                index = 4;
                break;
        }
        targetStringList = Transactions(listTableTransactions.get(index));
        return targetStringList;
    }
}
