package at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.IOException;
import java.util.HashMap;
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
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody"))
    List<WebElement> tableBodyElements;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr"))
    List<WebElement> tableRowElements;

    @FindAll(
            @FindBy(how = How.XPATH,using = "//div[@class='table-inner']/table[2]/tbody[2]/tr[1]/td"))
    List<WebElement> tableColumnElements;

    public String getStringDestination() throws IOException {
        return tableColumnElements.get(0).getAttribute("innerText");
    }

    private int getTableTransactionsSize () {
        return tableTransactions.size();
    }

    private HashMap<String, HashMap<String, HashMap<String ,HashMap<String, String>>>> TransactionTable() {
        HashMap<String,String> TransTable = new HashMap<>();
        HashMap<String,String> TransBody = new HashMap<>();
        HashMap<String,String> TransRow = new HashMap<>();
        HashMap<String,String> TransData = new HashMap<>();

        for (int i = 1;i<tableTransactions.size();i++) {
            TransTable.put(i, TransBody);
            for (int j = 0; j<tableBodyElements.size();j++) {
                TransBody.put(tableTransactions.get(j).getAttribute("inner-text"), TransRow);
                for (int k=0; k<tableRowElements.size();k++) {
                    TransRow.put(tableRowElements.get(k).getAttribute("inner-text"), TransData);
                    TransData.put("Transaction" ,tableColumnElements.get(l).getAttribute("inner-text"));
                }
            }
        }
    }

}
